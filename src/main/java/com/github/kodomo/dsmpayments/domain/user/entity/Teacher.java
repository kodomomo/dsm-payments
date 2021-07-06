package com.github.kodomo.dsmpayments.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="tbl_teacher")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    @Id
    private String id;

    @Column(length = 60, nullable = false)
    private String password;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 4, nullable = false)
    private Integer number;

    public Boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}
