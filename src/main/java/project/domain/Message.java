package project.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "message")
public class Message extends MessageModel{
    @Column(name = "checked")
    private boolean checked;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_to")
    private User userTo;

    public Message() {
    }

    public Message(User userFrom,
                   User userTo,
                   @Length(max = 2048, message = "MessageLengSout") @NotNull String message) {
        this.setUserFrom(userFrom);
        this.userTo = userTo;
        this.setMessage(message);
        this.checked = false;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public User getUserTo() {
        return userTo;
    }

    public void setUserTo(User userTo) {
        this.userTo = userTo;
    }
}
