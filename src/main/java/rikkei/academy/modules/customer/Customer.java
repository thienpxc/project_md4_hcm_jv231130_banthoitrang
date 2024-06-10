package rikkei.academy.modules.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Customer {
    private Integer customer_id;
    private String customer_name;
    private String email;
    private String password;
    private String phone_number;
    private String address;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private char gender;
}
