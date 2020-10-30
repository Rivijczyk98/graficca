package pl.edu.pb.wi.grafika.UI.Elements;

import pl.edu.pb.wi.grafika.DataStorage.Storage;
import pl.edu.pb.wi.grafika.UI.prefabs.Button;
import pl.edu.pb.wi.grafika.utils.FiltersUtils;

import javax.swing.*;
import java.awt.*;

public class HistogramsBinarization extends JPanel {

    private JButton histograms = new Button("Histograms");
    private JButton binarization = new Button("Binarisation");

    public HistogramsBinarization() {
        super(new GridBagLayout());

        setBackground(Color.DARK_GRAY);
        GridBagConstraints gc = new GridBagConstraints();

        gc.fill = GridBagConstraints.BOTH;
        JLabel title = new JLabel("Subject 5");
        title.setForeground(Color.WHITE);
        add(title, gc);

        gc.gridy = 1;
        add(histograms, gc);
        histograms.addActionListener(actionEvent -> {
            if(!Storage.isOriginalImagePresent()){
                Storage.setErrorMessage("No image found");
                return;
            }
            try {
                new HistogramsWindow();
            } catch (Exception e){
                Storage.setErrorMessage(e.toString());
            }
        });

        gc.gridx = 1;
        add(binarization, gc);
        binarization.addActionListener(actionEvent -> {
            if(!Storage.isOriginalImagePresent()){
                Storage.setErrorMessage("No image found");
                return;
            }
            try {
                new BinarisationWindow();
            } catch (Exception e){
                Storage.setErrorMessage(e.toString());
            }
        });
    }
}
