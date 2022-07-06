package com.johnBryce.couponAppPhase2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Entity
@Table(name="coupons")


public class Coupon {
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

	@Column(name = "category")
	@Enumerated(EnumType.ORDINAL)
    private Category category;
@JsonIgnore
    @ManyToOne (fetch = FetchType.EAGER)
    private Company company;

    @Column(name = "title")
    private String title;
	@Column(name = "description")
    private String description;
	
	@Column(name = "startDate")
    private Date startDate;
	
	@Column(name = "endDate")
    private Date endDate;
	
	@Column(name = "amount")
    private long amount;
	
	@Column(name = "price")
    private Double price;
	
	@Column(name = "image")
    private String image;
@JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "customers_coupons" ,joinColumns = @JoinColumn(name = "coupon_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id"))
    private List<Customer> customerPurchase ;

    public Coupon( Company companyId, Category category, String title,
                  String description, long amount,
                  Double price, String image) {
this.id=0L;
        this.company = companyId;
        this.category = category;
        this.title = title;
        this.description = description;
        this.startDate = new Date(System.currentTimeMillis());
        this.endDate = new Date(System.currentTimeMillis()+ TimeUnit.DAYS.toMillis(90));
        this.amount = amount;
        this.price = price;
        this.image = image;
    }

    public Coupon(Category category, String title, String description, long amount, Double price, String image) {
        this.category = category;
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.price = price;
        this.image = image;
        this.startDate = new Date(System.currentTimeMillis());
        this.endDate = new Date(System.currentTimeMillis()+ TimeUnit.DAYS.toMillis(90));
    }

    public Coupon(Category category, String title, String description, Date startDate, Date endDate, long amount, Double price, String image, Company company) {
        this.id = 0L;
        this.category = category;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.price = price;
        this.image = image;
        this.company = company;
    }

    public Coupon() {

    }

    public void addCustomer(Customer customer){
        if (customerPurchase.isEmpty()){
            customerPurchase = new ArrayList<>();
        }
        customerPurchase.add(customer);
    }


    public void setId(long id) {
        this.id = id;
    }



    public long getId() {
        return id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company companyId) {
        this.company = companyId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Customer> getCustomerPurchase() {
        return customerPurchase;
    }

    public void setCustomerPurchase(List<Customer> customerPurchase) {
        this.customerPurchase = customerPurchase;
    }
    public void deleteAllCustomers (){
        if (!customerPurchase.isEmpty()){
        for (Customer customer: customerPurchase) {
            customerPurchase.remove(customer);
        }
        }
    }




    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result +  id.intValue();
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
        Coupon other = (Coupon) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", compantId=" + company +
                ", category=" + category +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", amount=" + amount +
                ", price=" + price +
                ", image='" + image + '\'' +
                '}'+
                '\n';

    }
}
