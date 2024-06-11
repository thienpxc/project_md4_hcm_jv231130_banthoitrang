package rikkei.academy.modules.customer.dto.loginDto;

import lombok.*;
import rikkei.academy.modules.customer.Customer;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RegisterForm {
    private String username;
    private String password;
    private String email;
}
