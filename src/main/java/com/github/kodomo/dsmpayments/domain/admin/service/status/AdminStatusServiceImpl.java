package com.github.kodomo.dsmpayments.domain.admin.service.status;

import com.github.kodomo.dsmpayments.domain.admin.service.status.dto.BoothStatus;
import com.github.kodomo.dsmpayments.domain.admin.service.status.dto.CoinStatus;
import com.github.kodomo.dsmpayments.domain.admin.service.status.dto.UserStatus;
import com.github.kodomo.dsmpayments.domain.booth.repository.BoothRepository;
import com.github.kodomo.dsmpayments.domain.user.repository.DsmPaymentsUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdminStatusServiceImpl implements AdminStatusService {

    private final DsmPaymentsUserRepository userRepository;
    private final BoothRepository boothRepository;

    @Override
    public CoinStatus getAllStatus() {
        return null;
    }

    @Override
    public List<UserStatus> findAllUserStatus(String searchWord) {
        return userRepository.findAllUserStatus(searchWord)
                .stream().map(UserStatus::of)
                .collect(Collectors.toList());
    }

    @Override
    public List<BoothStatus> findAllBoothStatus(String searchWord) {
        return boothRepository.findAllBoothStatus(searchWord)
                .stream().map(BoothStatus::of)
                .collect(Collectors.toList());
    }

}
