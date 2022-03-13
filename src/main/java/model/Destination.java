package model;


import javax.persistence.*;

@Entity
@Table(name = "destination")
public class Destination {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue()
    private Integer id;

    @Column
    private String city;

    @Column
    private String country;

    @Column
    private String hotel;

    @Column
    private Boolean safe;

    public Destination(String city, String country, String hotel, Boolean safe) {
        this.city = city;
        this.country = country;
        this.hotel = hotel;
        this.safe = safe;
    }

    public Destination() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public Boolean getSafe() {
        return safe;
    }

    public void setSafe(Boolean safe) {
        this.safe = safe;
    }

    public Object[] getDestination() {
        return new Object[]{id, hotel, city, country, safe};
    }
}
