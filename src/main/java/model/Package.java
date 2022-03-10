package model;

import javax.persistence.*;

@Entity
@Table(name = "package")
public class Package {

    @Id
    private Integer id;

    @Column
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column
    private String period;

    @Column
    private String details;

    @OneToOne
    @JoinColumn(name = "destination")
    private Destination destination;

    @OneToOne
    @JoinColumn(name = "agency")
    private Agency agency;

    @Column(nullable = false)
    private Integer limit;

    @Column
    private Status status;

    @Column
    private Boolean type;
}
