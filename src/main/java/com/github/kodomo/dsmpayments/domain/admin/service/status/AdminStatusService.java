package com.github.kodomo.dsmpayments.domain.admin.service.status;

import com.github.kodomo.dsmpayments.domain.admin.service.status.dto.BoothStatus;
import com.github.kodomo.dsmpayments.domain.admin.service.status.dto.CoinStatus;
import com.github.kodomo.dsmpayments.domain.admin.service.status.dto.UserStatus;

import java.util.List;

public interface AdminStatusService {
    CoinStatus getAllStatus();
    List<UserStatus> findAllUserStatus(String searchWord);
    List<BoothStatus> findAllBoothStatus(String searchWord);
}
