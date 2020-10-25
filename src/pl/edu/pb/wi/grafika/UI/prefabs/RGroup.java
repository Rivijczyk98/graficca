package pl.edu.pb.wi.grafika.UI.prefabs;

import javafx.scene.Group;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;

public class RGroup extends Group {

    private Rotate rotate = new Rotate();
    private Transform transform = new Rotate();

    public void rotateByX(int angle){
        transform = new Rotate(angle, Rotate.X_AXIS);
        transform = transform.createConcatenation(rotate);
        this.getTransforms().addAll(transform);
    }

    public void rotateByY(int angle){
        transform = new Rotate(angle, Rotate.Y_AXIS);
        transform = transform.createConcatenation(rotate);
        this.getTransforms().addAll(transform);
    }

    public void zoom(int amount){
        this.translateZProperty().set(this.getTranslateZ() + amount);
    }

}
