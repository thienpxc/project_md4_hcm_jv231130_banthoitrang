package rikkei.academy.modules.customer;

import lombok.*;
import rikkei.academy.modules.order.Orders;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "Customer")
@Entity
public class Customer {


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

    @OneToMany(mappedBy = "customer",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Orders> orders;
    private Boolean gender;




}
