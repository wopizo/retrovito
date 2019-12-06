package project.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "deal")
public class Deal extends TableModel{

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "advert")
    private Advert advert;

    @Column(name = "date")
    private Date date;

    @PrePersist
    private void onCreate(){
        if(date == null){
            date = new Date();
        }
    }

    public Deal() {
    }

    public Deal(Advert advert) {
        this.advert = advert;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Advert getAdvert() {
        return advert;
    }

    public void setAdvert(Advert advert) {
        this.advert = advert;
    }
}
