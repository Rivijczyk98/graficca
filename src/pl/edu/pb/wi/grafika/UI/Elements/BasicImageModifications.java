package pl.edu.pb.wi.grafika.UI.Elements;

import pl.edu.pb.wi.grafika.DataStorage.Storage;
import pl.edu.pb.wi.grafika.Handlers.NumericInputHandler;
import pl.edu.pb.wi.grafika.UI.prefabs.Button;
import pl.edu.pb.wi.grafika.utils.FiltersUtils;
import pl.edu.pb.wi.grafika.utils.ImageBasicsUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.text.ParseException;

public class BasicImageModifications extends JPanel {

    private JButton addButton = new Button("+");
    private JButton subButton = new Button("-");
    private JButton multiButton = new Button("*");
    private JButton divButton = new Button("/");

    private JButton brightenButton = new Button("Brighten");
    private JButton darkenButton = new Button("Darken");
    private JButton grayscaleButton = new Button("Grayscale");

    private JButton median3x3Button = new Button("Median 3x3");
    private JButton sobel3x3Button = new Button("Random Sobel");
    private JButton smoothButton = new Button("Smooth");
    private JButton sharpenButton = new Button("Sharpen");
    private JButton gaussianBlurButton = new Button("Gaussian Blur");
    private JButton maskButton = new Button("Mask");

    public BasicImageModifications() {
        super(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();
        setBackground(Color.DARK_GRAY);

        gc.fill = GridBagConstraints.BOTH;
        gc.gridy = 0;
        gc.gridx = 0;
        gc.gridwidth = 2;

        JTextField input = new JTextField("10");
        input.setPreferredSize(new Dimension(150, 32));
        add(input, gc);

        gc.gridy = 1;
        gc.gridwidth = 1;
        add(addButton, gc);
        addButton.addActionListener(actionEvent -> {
            if(!Storage.isOriginalImagePresent()){
                Storage.setErrorMessage("No image found");
                return;
            }
            try {
                Storage.setEditedImage(ImageBasicsUtils.add(Storage.getEditedImage(), Integer.parseInt(input.getText()))); ;
            } catch (Exception e){
                Storage.setErrorMessage(e.toString());
            }
        });

        gc.gridx = 1;
        add(subButton, gc);
        subButton.addActionListener(actionEvent -> {
            if(!Storage.isOriginalImagePresent()){
                Storage.setErrorMessage("No image found");
                return;
            }
            try {
                Storage.setEditedImage(ImageBasicsUtils.subtract(Storage.getEditedImage(), Integer.parseInt(input.getText()))); ;
            } catch (Exception e){
                Storage.setErrorMessage(e.toString());
            }
        });

        gc.gridx = 0;
        gc.gridy = 2;

        add(multiButton, gc);
        multiButton.addActionListener(actionEvent -> {
            if(!Storage.isOriginalImagePresent()){
                Storage.setErrorMessage("No image found");
                return;
            }
            try {
                Storage.setEditedImage(ImageBasicsUtils.multiply(Storage.getEditedImage(), Integer.parseInt(input.getText()))); ;
            } catch (Exception e){
                Storage.setErrorMessage(e.toString());
            }
        });

        gc.gridx = 1;

        add(divButton, gc);
        divButton.addActionListener(actionEvent -> {
            if(!Storage.isOriginalImagePresent()){
                Storage.setErrorMessage("No image found");
                return;
            }
            try {
                Storage.setEditedImage(ImageBasicsUtils.divide(Storage.getEditedImage(), Integer.parseInt(input.getText()))); ;
            } catch (Exception e){
                Storage.setErrorMessage(e.toString());
            }
        });

        gc.gridx = 0;
        gc.gridy = 3;
        add(brightenButton, gc);

        brightenButton.addActionListener(action -> {
            if(!Storage.isOriginalImagePresent()){
                Storage.setErrorMessage("No image found");
                return;
            }
            try {
                Storage.setEditedImage(ImageBasicsUtils.brighten(Storage.getEditedImage()));
            } catch (Exception e){
                Storage.setErrorMessage(e.toString());
            }
        });

        gc.gridx = 1;
        add(darkenButton, gc);

        darkenButton.addActionListener(action -> {
            if(!Storage.isOriginalImagePresent()){
                Storage.setErrorMessage("No image found");
                return;
            }
            try {
                Storage.setEditedImage(ImageBasicsUtils.darken(Storage.getEditedImage()));
            } catch (Exception e){
                Storage.setErrorMessage(e.toString());
            }
        });

        gc.gridy = 4;
        gc.gridx = 0;
        gc.gridwidth = 2;
        add(grayscaleButton, gc);

        grayscaleButton.addActionListener(action -> {
            if(!Storage.isOriginalImagePresent()){
                Storage.setErrorMessage("No image found");
                return;
            }
            try {
                Storage.setEditedImage(ImageBasicsUtils.convertToGrayscale(Storage.getEditedImage()));
            } catch (Exception e){
                Storage.setErrorMessage(e.toString());
            }
        });

        gc.gridwidth = 1;
        gc.gridy = 5;
        JLabel section = new JLabel("Filters");
        section.setForeground(Color.WHITE);
        add(section, gc);

        gc.gridy = 6;
        add(median3x3Button, gc);

        median3x3Button.addActionListener(actionEvent -> {
            if(!Storage.isOriginalImagePresent()){
                Storage.setErrorMessage("No image found");
                return;
            }
            try {
                Storage.setEditedImage(FiltersUtils.medianFilter(Storage.getEditedImage()));
            } catch (Exception e){
                Storage.setErrorMessage(e.toString());
            }
        });

        gc.gridx = 1;
        add(sobel3x3Button, gc);
        sobel3x3Button.addActionListener(actionEvent -> {
            if(!Storage.isOriginalImagePresent()){
                Storage.setErrorMessage("No image found");
                return;
            }
            try {
                Storage.setEditedImage(FiltersUtils.sobel(Storage.getEditedImage()));
            } catch (Exception e){
                Storage.setErrorMessage(e.toString());
            }
        });

        gc.gridx = 0;
        gc.gridy = 7;
        add(gaussianBlurButton, gc);

        gaussianBlurButton.addActionListener(actionEvent -> {
            if(!Storage.isOriginalImagePresent()){
                Storage.setErrorMessage("No image found");
                return;
            }
            try {
                Storage.setEditedImage(FiltersUtils.gaussianBlur(Storage.getEditedImage()));
            } catch (Exception e){
                Storage.setErrorMessage(e.toString());
            }
        });

        gc.gridx = 1;
        add(smoothButton,gc);
        smoothButton.addActionListener(actionEvent -> {
            if(!Storage.isOriginalImagePresent()){
                Storage.setErrorMessage("No image found");
                return;
            }
            try {
                Storage.setEditedImage(FiltersUtils.smooth(Storage.getEditedImage()));
            } catch (Exception e){
                Storage.setErrorMessage(e.toString());
            }
        });

        gc.gridx = 0;
        gc.gridy = 8;
        add(sharpenButton, gc);

        sharpenButton.addActionListener(actionEvent -> {
            if(!Storage.isOriginalImagePresent()){
                Storage.setErrorMessage("No image found");
                return;
            }
            try {
                Storage.setEditedImage(FiltersUtils.sharpen(Storage.getEditedImage()));
            } catch (Exception e){
                Storage.setErrorMessage(e.toString());
            }
        });

        gc.gridx = 1;
        add(maskButton, gc);

        maskButton.addActionListener(actionEvent -> {
            if(!Storage.isOriginalImagePresent()){
                Storage.setErrorMessage("No image found");
                return;
            }
            try {
                new FilterMaskJFrame();
            } catch (Exception e){
                Storage.setErrorMessage(e.toString());
            }
        });
    }

}
