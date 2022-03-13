package model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue()
    private Integer id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column
    private String email;

    @Column
    private Integer age;

    @Column
    private String password;

    @Column
    private Boolean customer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "booking",
    joinColumns = { @JoinColumn(name = "customer") },
    inverseJoinColumns = { @JoinColumn(name = "package") })
    List<Package> packages = new ArrayList<>();

    public  User() {}

    public User(String username, String password, String firstname, String lastname, String email, Integer age) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.password = password;
        this.email = email;
        this.customer = true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean isCustomer() {
        return customer;
    }

    public void setCustomer(Boolean customer) {
        this.customer = customer;
    }

    public void addPackage(Package pack) {
        packages.add(pack);
    }

    public List<Package> getPackages() {return packages;}
}
