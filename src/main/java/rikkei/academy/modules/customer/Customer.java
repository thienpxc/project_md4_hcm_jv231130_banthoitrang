package rikkei.academy.modules.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Customer {

    public enum Gender {
        MALE,
        FEMALE
    }


    private Integer customer_id;
    private String customer_name;
    private String email;
    private String password;
    private String phone_number;
    private String address;
    private String avatar;
    private Boolean role = false;
    private Boolean status = true;
    private Date created_at;
    private Gender gender;
}
