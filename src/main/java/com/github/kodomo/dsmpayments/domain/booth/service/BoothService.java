package com.github.kodomo.dsmpayments.domain.booth.service;

import com.github.kodomo.dsmpayments.domain.booth.entity.Menu;
import com.github.kodomo.dsmpayments.domain.receipt.service.dto.ReceiptDTO;

import java.util.List;

public interface BoothService {
    public String login(String boothId, String password);

    public Menu createMenu(String boothId, String menuName, Integer price);

    public List<Menu> getMenus(String boothId);

    public Integer deleteMenu(String boothId, Integer menuId);

    public ReceiptDTO pay(Integer menuId, String userUuid);
}