package project.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@MappedSuperclass
public abstract class MessageModel extends TableModel {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_from")
    private User userFrom;

    @Length(max = 2048, message = "MessageLengSout")
    @Column(name = "message")
    @NotNull
    private String message;

    @Column(name = "date")
    private Date date;

    @PrePersist
    private void onCreate(){
        if(date == null){
            date = new Date();
        }
    }

    public User getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(User userFrom) {
        this.userFrom = userFrom;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
