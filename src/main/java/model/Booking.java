package model;

import javax.persistence.*;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    private Integer id;

    @OneToOne
    @JoinColumn(name = "customer")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "package")
    private Package Package;
}
