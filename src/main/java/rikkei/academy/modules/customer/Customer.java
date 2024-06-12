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
    private Integer customerId;
    private String customerName;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private String avatar;
    private Boolean role = false;
    private Boolean status = true;
    private Date createdAt;
    private Gender gender;

}
