package rikkei.academy.modules.customer;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "Customer")
@Entity
public class Customer {

    public enum Gender {
        MALE,
        FEMALE
    }
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Id
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

    @Override
    public String toString() {
        return "Customer{" +
                "customer_id=" + customer_id +
                ", customer_name='" + customer_name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", address='" + address + '\'' +
                ", avatar='" + avatar + '\'' +
                ", role=" + role +
                ", status=" + status +
                ", created_at=" + created_at + '\'' + "}";
    }
}
