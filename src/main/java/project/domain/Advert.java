package project.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "advert")
public class Advert  extends TableModel{
    @Column(name = "tittle")
    private String tittle;
    @Column(name = "description")
    private String description;
    @Column(name = "cost")
    private String cost;
    @Column(name = "type")
    private String type;
    @Column(name = "company")
    private String company;
    @Column(name = "picture")
    private String picture;
    @Column(name = "city")
    private String city;
    @Column(name = "active")
    private boolean active;
    @Column(name = "hasClient")
    private boolean hasClient;
    @Column(name = "date")
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author")
    private User author;

    @PrePersist
    private void onCreate(){
        if(date == null){
            date = new Date();
        }
    }

    public Advert() {
    }

    public Advert(User author,
                  String tittle,
                  String cost,
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
        this.hasClient = true;
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

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
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

    public boolean isHasClient() {
        return hasClient;
    }

    public void setHasClient(boolean hasClient) {
        this.hasClient = hasClient;
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
}
