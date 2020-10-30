package pl.edu.pb.wi.grafika.UI.Elements;

import pl.edu.pb.wi.grafika.DataStorage.Storage;
import pl.edu.pb.wi.grafika.UI.prefabs.Button;
import pl.edu.pb.wi.grafika.utils.BinarisationUtils;

import javax.swing.*;
import java.awt.*;

public class BinarisationWindow extends JFrame {

    public BinarisationWindow() throws HeadlessException {
        super();

        setLayout(new GridBagLayout());
        setTitle("Histograms");
        setSize(200, 300);
        setResizable(false);
        setBackground(Color.DARK_GRAY);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        GridBagConstraints gc = new GridBagConstraints();

        gc.fill = 1;
        gc.weightx = 1f;
        gc.weighty = 1f;
        gc.gridy = 0;

        JTextField input = new JTextField("10");
        add(input, gc);

        gc.gridy = 1;
        JButton manualButton = new Button("Manual");
        manualButton.setPreferredSize(new Dimension(150, 32));
        add(manualButton, gc);

        manualButton.addActionListener(actionEvent -> {
            if(!Storage.isOriginalImagePresent()){
                Storage.setErrorMessage("No image found");
                return;
            }
            try {
                Storage.setEditedImage(
                        BinarisationUtils.manual(
                                Storage.getEditedImage(),
                                Integer.parseInt(input.getText())
                        )
                );
            } catch (Exception e){
                Storage.setErrorMessage(e.toString());
            }
        });

        gc.gridy = 2;
        JButton percentageButton = new Button("Percentage");
        percentageButton.setPreferredSize(new Dimension(150, 32));
        add(percentageButton, gc);

        percentageButton.addActionListener(actionEvent -> {
            if(!Storage.isOriginalImagePresent()){
                Storage.setErrorMessage("No image found");
                return;
            }
            try {
                Storage.setEditedImage(
                        BinarisationUtils.percentage(
                                Storage.getEditedImage(),
                                Float.parseFloat(input.getText())
                        )
                );
            } catch (Exception e){
                Storage.setErrorMessage(e.toString());
            }
        });

        gc.gridy = 3;
        JButton otsuButton = new Button("Otsu");
        otsuButton.setPreferredSize(new Dimension(150, 32));
        add(otsuButton, gc);

        otsuButton.addActionListener(actionEvent -> {
            if(!Storage.isOriginalImagePresent()){
                Storage.setErrorMessage("No image found");
                return;
            }
            try {
                Storage.setEditedImage(
                        BinarisationUtils.otsu( Storage.getEditedImage() )
                );
            } catch (Exception e){
                Storage.setErrorMessage(e.toString());
            }
        });

        setVisible(true);
    }
}
