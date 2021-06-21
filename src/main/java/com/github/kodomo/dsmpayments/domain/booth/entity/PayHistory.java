package com.github.kodomo.dsmpayments.domain.booth.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="tbl_history")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PayHistory {
    @Id
    private String HistoryId;

    @Column(nullable = false)
    private String boothId;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private Boolean isRemit;

    @Column(nullable = false)
    private Integer requestedPrice;

    @Column(nullable = false)
    private Integer tax;

    @Column(nullable = false)
    private Integer finalPrice;
}
