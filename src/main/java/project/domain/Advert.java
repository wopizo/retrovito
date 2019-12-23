package project.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "advert")
public class Advert  extends TableModel{
    @NotNull
    @Column(name = "tittle")
    private String tittle;

    @Length(max = 2048, message = "DescLengSout")
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "cost")
    private Long cost;

    @NotNull
    @Column(name = "type")
    private String type;

    @NotNull
    @Column(name = "company")
    private String company;

    @Column(name = "picture")
    private String picture;

    @NotNull
    @Column(name = "city")
    private String city;

    @Column(name = "active")
    private boolean active;

    @Column(name = "date")
    private Date date;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author")
    private User author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "buyer")
    private User buyer;

    @PrePersist
    private void onCreate(){
        if(date == null){
            date = new Date();
        }
    }

    public Advert() {
    }

    public Advert(User author,
                  User buyer,
                  String tittle,
                  Long cost,
                  String type,
                  String company,
                  String city,
                  String description) {
        this.tittle = tittle;
        this.description = description;
        this.cost = cost;
        this.type = type;
        this.company = company;
        this.city = city;
        this.author = author;
        this.active = true;
        this.buyer = buyer;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public boolean isBlocked(){
        return !active;
    }
}
