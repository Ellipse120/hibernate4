package spittr.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Lusai on 8/31/16.
 */
@Entity
public class Spittle {

    public Spittle() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "spittr")
    private Spitter spitter;

    @Column
    private String message;

    @Column
    private Date postedTime;

    public Spittle(Long id,Spitter spitter, String message, Date postedTime) {
        this.id = id;
        this.spitter = spitter;
        this.message = message;
        this.postedTime = postedTime;
    }

    public Long getId() {
        return id;
    }

    public Spitter getSpitter() {
        return spitter;
    }

    public String getMessage() {
        return message;
    }

    public Date getPostedTime() {
        return postedTime;
    }
}
