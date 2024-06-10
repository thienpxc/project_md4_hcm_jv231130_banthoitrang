package rikkei.academy.modules.deliverInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DeliverInfo {
    private Integer id;
    private Integer userId;
    private String name;
    private String phoneNumber;
    private String address;
}
