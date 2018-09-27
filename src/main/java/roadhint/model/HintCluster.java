package roadhint.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ardab on 27.06.2017.
 */
public class HintCluster implements Serializable {

    private int numberOfHints;
    private Circle circle;
    private ArrayList<Hint> hints;

    public int getNumberOfHints() {
        return numberOfHints;
    }

    public void setNumberOfHints(int numberOfHints) {
        this.numberOfHints = numberOfHints;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public ArrayList<Hint> getHints() {
        return hints;
    }

    public void setHints(ArrayList<Hint> hints) {
        this.hints = hints;
    }
}
