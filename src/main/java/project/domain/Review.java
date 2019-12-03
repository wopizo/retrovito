package project.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "review")
public class Review extends MessageModel{

    @Column(name = "mark")
    private boolean mark;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_to")
    private User userTo;

    public Review() {
    }

    public Review(User userFrom,
                   User userTo,
                   boolean mark,
                   @Length(max = 2048, message = "MessageLengSout") @NotNull String message) {
        this.setUserFrom(userFrom);
        this.userTo = userTo;
        this.setMessage(message);
        this.mark = mark;
    }

    public boolean isMark() {
        return mark;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }

    public User getUserTo() {
        return userTo;
    }

    public void setUserTo(User userTo) {
        this.userTo = userTo;
    }
}

