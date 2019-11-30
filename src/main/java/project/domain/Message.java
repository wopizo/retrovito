package project.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "message")
public class Message extends TableModel{

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userFrom")
    private User userFrom;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userTo")
    private User userTo;

    @Length(max = 2048, message = "MessageLengSout")
    @Column(name = "message")
    @NotNull
    private String message;

    @Column(name = "checked")
    private boolean checked;

    @Column(name = "date")
    private Date date;

    @PrePersist
    private void onCreate(){
        if(date == null){
            date = new Date();
        }
    }

    public Message() {
    }

    public Message(User userFrom,
                   User userTo,
                   @Length(max = 2048, message = "MessageLengSout") @NotNull String message) {
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.message = message;
        this.checked = false;
    }

    public User getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(User userFrom) {
        this.userFrom = userFrom;
    }

    public User getUserTo() {
        return userTo;
    }

    public void setUserTo(User userTo) {
        this.userTo = userTo;
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

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
