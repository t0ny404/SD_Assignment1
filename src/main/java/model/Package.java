package model;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "package")
public class Package {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue()
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "start")
    private Date start;

    @Column(name = "details")
    private String details;

    @ManyToOne
    @JoinColumn(name = "destination")
    private Destination destination;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "limitt", nullable = false)
    private Integer limitt;

    @Column(name = "end")
    private Date end;

    @Column(name = "bookedBy")
    private Integer bookedBy;

    @ManyToMany(mappedBy = "packages", fetch = FetchType.EAGER)
    private List<User> users = new ArrayList<>();


    public Package(String name, Double price, Integer limit, String details, Date start, Date end) {
        this.name = name;
        this.price = price;
        this.start = start;
        this.end = end;
        this.details = details;
        this.limitt = limit;
        this.bookedBy = 0;
        this.status = Status.NOT_BOOKED;
    }

    public Package() {

    }

    public Package(Integer id, String name, Double price, Date start, Date end, String details, Integer limitt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.start = start;
        this.details = details;
        this.limitt = limitt;
        this.end = end;
    }

    public Object[] getPackage() {
        return new Object[]{name, price, start, end, details, destination.getHotel(), destination.getCity(), destination.getCountry()};
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

    public String getStatus() {
        return status.name();
    }

    public void setStatus(String status) {
        this.status = Status.valueOf(status);
    }

    public void update() {
        if (bookedBy == 0)
            status = Status.NOT_BOOKED;
        else if (bookedBy.equals(limitt))
            status = Status.BOOKED;
        else status = Status.IN_PROGRESS;
    }

    public long getPeriod() {
        return ChronoUnit.DAYS.between(LocalDate.parse(start.toString()),LocalDate.parse(end.toString()));
    }

    public void addUser(User user) {
        users.add(user);
        bookedBy++;
        update();
    }
}
