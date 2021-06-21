package com.github.kodomo.dsmpayments.domain.booth.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="tbl_booth")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Booth {
    @Id
    private Integer boothId;

    @Column(length = 60, nullable = false)
    private String password;

    @Column(length = 30, nullable = false)
    private String boothName;

    @Column(nullable = false)
    private Integer coin;

    @Column(nullable = false)
    private Integer totalCoin;
}
