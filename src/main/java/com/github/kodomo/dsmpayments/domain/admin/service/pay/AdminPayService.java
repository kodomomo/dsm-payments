package com.github.kodomo.dsmpayments.domain.admin.service.pay;

public interface AdminPayService {
    void depositToUser(int userNumber, int value);
    void depositTOBooth(String boothId, int value);
}
