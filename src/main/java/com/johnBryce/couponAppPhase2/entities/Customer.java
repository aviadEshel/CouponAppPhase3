package com.johnBryce.couponAppPhase2.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="customers")


public class Customer {
	
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
	
	@Column(name="first_name")
    private String firstName;
	
	@Column(name="last_name")
    private String lastName;
	
	@Column(name="email", unique = true)
    private String email;
	
	@Column(name="password")
    private String password;


@ManyToMany( mappedBy = "customerPurchase",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "customers_coupons" ,joinColumns = @JoinColumn (name = "customer_id"),
//            inverseJoinColumns = @JoinColumn(name = "coupon_id"))
   private List<Coupon> purchasedCoupons;

    public Customer( String firstName, String lastName, String email, String password) {
        super();
        this.id =0L;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;

    }


    public Customer() {
        super();
    }
    public Customer(Customer customer){
        customer = new Customer();
    }


    public Customer(String email, String password) {
        super();
        this.email = email;
        this.password = password;
    }
    public void addCupon(Coupon coupon){
        if (purchasedCoupons.isEmpty()){
            purchasedCoupons = new ArrayList<>();
        }
        this.purchasedCoupons.add(coupon);
    }





//    public Customer(String firstName, String lastName, String email, String password) {
//        super();
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.password = password;
//    }


    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public List<Coupon> getPurchasedCoupons() {
        return purchasedCoupons;
    }

    public void setPurchasedCoupons(List<Coupon> purchasedCoupons) {
        this.purchasedCoupons = purchasedCoupons;
    }

    //    public List<Coupon> getCoupons() {
//        return coupons;
//    }

//    public void setCoupons(List<Coupon> coupons) {
//        this.coupons = coupons;
//    }

    @Override
    public String toString() {
        return "Customers [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
                + ", password=" + password + "]\n" +
                "";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Customer other = (Customer) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        return true;
    }



}
