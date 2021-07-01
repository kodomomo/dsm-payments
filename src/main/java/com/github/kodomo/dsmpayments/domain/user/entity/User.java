package com.github.kodomo.dsmpayments.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="tbl_user")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String userNumber;

    @Column(length = 5, nullable = false)
    private String userName;

    @Column(length = 6, nullable = false)
    private String userUuid;

    @Column( nullable = false)
    private Integer coin;

    public User takeCoin(Integer coin) {
        this.coin -= coin;
        return this;
    }

    public User giveCoin(Integer coin) {
        this.coin += coin;
        return this;
    }

    public Boolean isValidPayment(Integer coin) {
        return this.coin - coin >= 0;
    }
}