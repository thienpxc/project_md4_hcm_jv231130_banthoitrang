package rikkei.academy.modules.customer.dto.loginDto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LoginForm {
    private String username;
    private String password;
}
