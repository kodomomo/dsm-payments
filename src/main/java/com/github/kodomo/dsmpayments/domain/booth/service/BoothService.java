package com.github.kodomo.dsmpayments.domain.booth.service;

import com.github.kodomo.dsmpayments.domain.booth.entity.Booth;
import com.github.kodomo.dsmpayments.domain.booth.entity.Menu;
import com.github.kodomo.dsmpayments.domain.receipt.service.dto.ReceiptDTO;

import java.util.List;

public interface BoothService {
    public String login(String boothId, String password);

    public Menu createMenu(String boothId, String menuName, Integer price);

    public List<Menu> getMenus(String boothId);

    public Integer deleteMenu(String boothId, Integer menuId);

    public ReceiptDTO pay(String boothId, Integer menuId, String userUuid);

    public void permitPayment(String boothId, String userUuid);

    public Booth getBooth(String boothId);
}
