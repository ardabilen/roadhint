package roadhint.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import roadhint.enums.HintType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "message_hint")
@PrimaryKeyJoinColumn(name = "hint_id",referencedColumnName = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MessageHint extends Hint implements Serializable {

    public MessageHint() {
        setType(HintType.MESSAGE);
    }

    @Column(name = "message")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}