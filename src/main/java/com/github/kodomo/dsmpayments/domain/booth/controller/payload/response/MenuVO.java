package com.github.kodomo.dsmpayments.domain.booth.controller.payload.response;

import com.github.kodomo.dsmpayments.domain.booth.entity.Menu;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class MenuVO {
    private Integer menuId;

    private String boothId;

    private String name;

    private Integer price;

    public static MenuVO of(Menu menu) {
        return MenuVO.builder()
                .menuId(menu.getMenuId())
                .boothId(menu.getBoothId())
                .name(menu.getName())
                .price(menu.getPrice())
                .build();
    }
}
