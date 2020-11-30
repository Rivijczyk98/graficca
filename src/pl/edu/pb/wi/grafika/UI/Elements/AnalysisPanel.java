package pl.edu.pb.wi.grafika.UI.Elements;

import pl.edu.pb.wi.grafika.DataStorage.Storage;

import javax.swing.*;
import java.awt.*;

public class AnalysisPanel extends PaintPanel {

    public AnalysisPanel() {
        super();

        setImage(Storage.getEditedImage());
    }

}
