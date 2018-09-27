package roadhint.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import roadhint.enums.HintType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "photo_hint")
@PrimaryKeyJoinColumn(name = "hint_id",referencedColumnName = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PhotoHint extends Hint {

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="Photos", joinColumns=@JoinColumn(name="id"))
    @Column(name="photo")
    private List<String> base64photos;

    public PhotoHint(){
        setType(HintType.PHOTO);
    }
    

    public List<String> getBase64photos() {
        return base64photos;
    }

    public void setBase64photos(List<String> base64photos) {
        this.base64photos = base64photos;
    }
}