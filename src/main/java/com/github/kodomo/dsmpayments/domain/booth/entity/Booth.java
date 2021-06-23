package com.github.kodomo.dsmpayments.domain.booth.entity;

import com.github.kodomo.dsmpayments.domain.user.entity.User;
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
    private String boothId;

    @Column(length = 60, nullable = false)
    private String password;

    @Column(length = 30, nullable = false)
    private String boothName;

    @Column(nullable = false)
    private Integer coin;

    @Column(nullable = false)
    private Integer totalCoin;

    public Booth takeCoin(Integer coin) {
        this.coin -= coin;
        return this;
    }

    public Booth giveCoin(Integer coin) {
        this.coin += coin;
        return this;
    }

    public Boolean isValidPayment(Integer coin) {
        return this.coin - coin >= 0;
    };
}
