package com.github.kodomo.dsmpayments.domain.user.entity.xquare;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Data
public class XquareUser {

    private String name;

    private UUID id;

    private Date birth_day;

    private Integer grade_class_number;

    private String profile_file_name;

    private String password;

    private String account_id;

    private Integer num;

    public XquareUser(
            String name, UUID id, Date birth_day,
            Integer grade, Integer class_num, Integer num,
            String profile_file_name, String account_id, String password) {
        this.name = name;
        this.id = id;
        this.birth_day = birth_day;
        this.grade_class_number = grade * 1000 + class_num * 100 + num;
        this.profile_file_name = profile_file_name;
        this.account_id = account_id;
        this.password = password;
    }

    public Integer getIntGradeClassNumber(){
        return this.grade_class_number;
    }

    public String getStrGradeClassNumber() {
        return this.grade_class_number.toString();
    }
}
