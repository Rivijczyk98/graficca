package pl.edu.pb.wi.grafika.Handlers;

import pl.edu.pb.wi.grafika.DataStorage.Storage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetImageHandler implements ActionListener {

    public ResetImageHandler() {
        super();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Storage.resetImage();
    }
}
