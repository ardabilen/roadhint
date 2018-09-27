package roadhint.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

/**
 * Created by ardab on 24.06.2017.
 */
public enum HintType implements Serializable {
    UNKNOWN("Unknown"),
    MESSAGE("Message"),
    PHOTO("Photo");

    private String value;


    @JsonValue
    public String getValue(){
        return value;
    }

    HintType(String value) {
        this.value = value;
    }

    public static HintType fromValue(String value) {
        for (HintType type : HintType.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return UNKNOWN;
    }
}
