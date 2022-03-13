package model;

import javax.persistence.*;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue()
    private Integer id;

    @OneToOne
    @JoinColumn(name = "customer")
    private User user;

    @OneToOne
    @JoinColumn(name = "package")
    private Package Package;
}
