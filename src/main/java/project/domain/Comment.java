package project.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "comment")
public class Comment extends MessageModel{

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "advert")
    private Advert advert;

    @Column(name = "edited")
    private boolean edited;

    public Comment() {
    }

    public Comment(User userFrom,
                   Advert advert,
                   @Length(max = 2048, message = "MessageLengSout") @NotNull String message) {
        this.setUserFrom(userFrom);
        this.advert = advert;
        this.setMessage(message);
    }

    public Advert getAdvert() {
        return advert;
    }

    public void setAdvert(Advert advert) {
        this.advert = advert;
    }

    public boolean isEdited() {
        return edited;
    }

    public void setEdited(boolean edited) {
        this.edited = edited;
    }
}
