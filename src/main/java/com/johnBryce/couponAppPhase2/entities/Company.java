package com.johnBryce.couponAppPhase2.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name="companies")



public class Company {
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
//	@OneToOne(fetch = FetchType.EAGER, mappedBy = "coupon")
//	@JoinColumn(name = "id")
   private Long id  ;
	
	@Column(name="name", updatable = false, unique = true)
    private String Name;
	
	@Column(name="email", unique = true)
private     String email;
	
	@Column(name="password")
    private String password;

    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            mappedBy = "company")
   private List<Coupon> coupons = new ArrayList<Coupon>();

    public Company(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Company() {
        super();
    }

    public Company(Long id, String name, String email, String password) {
        this.id = id;
        Name = name;
        this.email = email;
        this.password = password;
      //  this.coupons = new ArrayList<>();
    }

    public Company(String name, String email, String password) {
        super();
        this.id= 0L;
        Name = name;
        this.email = email;
        this.password = password;
    }

    public void addList(Coupon coupon){
        coupons.add(coupon);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    @Override
    public String toString() {
        return "Companies [id=" + id + ", Name=" + Name + ", email=" + email + ", password=" + password + "] \n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        Company company = (Company) o;
        return getEmail().equals(company.getEmail()) &&
                getPassword().equals(company.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getPassword());
    }
}
