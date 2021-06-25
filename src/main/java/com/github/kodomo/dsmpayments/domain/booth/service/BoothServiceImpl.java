package com.github.kodomo.dsmpayments.domain.booth.service;

import com.corundumstudio.socketio.SocketIOServer;
import com.github.kodomo.dsmpayments.domain.booth.entity.Booth;
import com.github.kodomo.dsmpayments.domain.booth.entity.Menu;
import com.github.kodomo.dsmpayments.domain.booth.exception.*;
import com.github.kodomo.dsmpayments.domain.booth.repository.BoothRepository;
import com.github.kodomo.dsmpayments.domain.booth.repository.MenuRepository;
import com.github.kodomo.dsmpayments.domain.receipt.entity.ReceiptSender;
import com.github.kodomo.dsmpayments.domain.receipt.integrate.ReceiptIntegrate;
import com.github.kodomo.dsmpayments.domain.receipt.service.dto.ReceiptDTO;
import com.github.kodomo.dsmpayments.domain.user.entity.User;
import com.github.kodomo.dsmpayments.domain.user.exception.UserNotFoundException;
import com.github.kodomo.dsmpayments.domain.user.repository.UserRepository;
import com.github.kodomo.dsmpayments.infra.token.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BoothServiceImpl implements BoothService {

    private final MenuRepository menuRepository;
    private final BoothRepository boothRepository;
    private final UserRepository userRepository;
    private final ReceiptIntegrate receiptIntegrate;
    private final TokenProvider tokenProvider;
    private final SocketIOServer socketIOServer;

    @Override
    public String login(String boothId, String password) {
        Booth booth = boothRepository.findById(boothId).orElseThrow(BoothAuthenticateFailed::new);

        if (!booth.getPassword().equals(password)) { throw new BoothAuthenticateFailed(); }

        return tokenProvider.generateAccessToken(boothId, "booth");
    }

    @Override
    public Menu createMenu(String boothId, String menuName, Integer price) {
        Menu menu = Menu.builder()
                .boothId(boothId)
                .name(menuName)
                .price(price)
                .build();

        return menuRepository.save(menu);
    }

    @Override
    public List<Menu> getMenus(String boothId) {
        return menuRepository.findAllByBoothId(boothId);
    }

    @Override
    public Integer deleteMenu(String boothId, Integer menuId) {
        Menu menu = menuRepository.findById(menuId).orElseThrow(MenuNotFoundException::new);
        if (!menu.getBoothId().equals(boothId)) { throw new UnauthorizedBoothException(); }
        menuRepository.delete(menu);
        return menuId;
    }

    @Override
    public ReceiptDTO pay(String boothId, Integer menuId, String userUuid) {
        Menu menu = menuRepository.findById(menuId).orElseThrow(MenuNotFoundException::new);
        User user = userRepository.findByUserUuid(userUuid).orElseThrow(UserNotFoundException::new);
        Booth booth = boothRepository.findById(boothId).orElseThrow(BoothNotFoundException::new);

        if (!menu.getBoothId().equals(boothId)) { throw new UnauthorizedBoothException(); }

        if (!(user.isValidPayment(menu.getPrice()) && booth.isValidPayment(menu.getPrice()))) {
            throw new InvalidRequestException();
        }

        ReceiptDTO receiptDTO = receiptIntegrate.registerReceipt(new ReceiptDTO(
                user,
                booth,
                menu.getPrice(),
                ReceiptSender.USER
        ));

        user.takeCoin(receiptDTO.getFinalValue());
        booth.giveCoin(receiptDTO.getRequestValue());

        userRepository.save(user);
        boothRepository.save(booth);

        return receiptDTO;
    }

    @Override
    public void permitPayment(String boothId, String userUuid) {
        System.out.println(userUuid);
        socketIOServer.getRoomOperations(boothId).sendEvent(
                "booth-payment-permission", userUuid);
    }
}
