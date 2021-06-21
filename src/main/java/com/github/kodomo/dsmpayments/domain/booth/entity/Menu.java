package com.github.kodomo.dsmpayments.domain.booth.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="tbl_menu")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    @Id
    private Integer MenuId;

    @Column(nullable = false)
    private String boothId;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean isRemit;

    @Column(nullable = false)
    private Integer price;
}
