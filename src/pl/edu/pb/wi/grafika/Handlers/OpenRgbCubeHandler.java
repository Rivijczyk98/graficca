package pl.edu.pb.wi.grafika.Handlers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.nio.file.FileSystems;

public class OpenRgbCubeHandler implements ActionListener {

    public OpenRgbCubeHandler() {
        super();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                String s = FileSystems.getDefault().getPath("").toAbsolutePath().toString();
                s = s.replace(" ", "%20");
                desktop.browse(
                        URI.create("file:///" + s + "/src/pl/edu/pb/wi/grafika/UI/Elements/RgbCube.html")
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
