package model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "package")
public class Package {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue()
    private Integer id;

    @Column
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column
    private Date start;

    @Column
    private String details;

    @OneToOne
    @JoinColumn(name = "destination")
    private Destination destination;

    @Column
    private Status status;

    @Column(nullable = false)
    private Integer limitt;

    @Column
    private Date end;

    public Package(String name, Double price, Integer limit, String details, Date start, Date end) {
        this.name = name;
        this.price = price;
        this.start = start;
        this.end = end;
        this.details = details;
        this.limitt = limit;
    }

    public Package() {

    }

    public Object[] getPackage() {
        return new Object[]{name, price, start, end, details, destination.getHotel(), destination.getCity(), destination.getCountry(), status};
    }

    public Object[] getPackageAgency() {
        return new Object[]{id, name, price, start, end, details, limitt, status};
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Integer getLimitt() {
        return limitt;
    }

    public void setLimitt(Integer limit) {
        this.limitt = limit;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
