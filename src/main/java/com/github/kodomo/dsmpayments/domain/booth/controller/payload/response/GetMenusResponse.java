package com.github.kodomo.dsmpayments.domain.booth.controller.payload.response;

import com.github.kodomo.dsmpayments.domain.booth.entity.Menu;
import lombok.Builder;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
@Builder
public class GetMenusResponse {
    private List<MenuVO> menus;

    public static GetMenusResponse of(List<Menu> menus) {
        List<MenuVO> menuVOList = new java.util.ArrayList<>(Collections.emptyList());

        for (Menu menu: menus) {
            menuVOList.add(MenuVO.of(menu));
        }
        return  GetMenusResponse.builder()
                .menus(menuVOList)
                .build();
    }
}
