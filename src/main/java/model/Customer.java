package model;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    private Integer id;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column
    private Integer age;

    @Column
    private Boolean partnered;

    @OneToOne
    @JoinColumn(name = "user")
    private User user;
}
