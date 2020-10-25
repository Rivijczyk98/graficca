package pl.edu.pb.wi.grafika.UI.Elements;

import javafx.embed.swing.JFXPanel;
import javafx.scene.shape.Box;

public class JfxCubePanel extends JFXPanel {

    public int size = 200;
    public Box cube = new Box(size, size, size );

    public JfxCubePanel() {
        super();
    }

    public void zoomCube(int amount) {
        cube.translateZProperty().set(cube.getTranslateZ() + amount);
    }
}
