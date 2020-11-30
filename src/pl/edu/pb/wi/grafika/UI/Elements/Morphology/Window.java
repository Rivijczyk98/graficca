package pl.edu.pb.wi.grafika.UI.Elements.Morphology;

import pl.edu.pb.wi.grafika.DataStorage.Storage;
import pl.edu.pb.wi.grafika.UI.prefabs.Button;
import pl.edu.pb.wi.grafika.models.Method;
import pl.edu.pb.wi.grafika.utils.MathematicalMorphology;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public Window() throws HeadlessException {
        super();

        setTitle("Mathematical Morphology");
        setResizable(true);
        setLayout(new GridBagLayout());
        setSize(150, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBackground(Color.DARK_GRAY);

        GridBagConstraints gc = new GridBagConstraints();

        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 1f;
        gc.weighty = 1f;
        gc.gridy = 0;
        JButton dilationButton = new Button("Dilation");
        dilationButton.addActionListener(actionEvent -> {
            Storage.setEditedImage(
                    MathematicalMorphology.morphology(
                            Storage.getEditedImage(), Method.DILATION
                    )
            );
        });
        add(dilationButton, gc);

        gc.gridy = 1;
        JButton erosionButton = new Button("Erosion");
        erosionButton.addActionListener(actionEvent -> {
            Storage.setEditedImage(
                    MathematicalMorphology.morphology(
                            Storage.getEditedImage(), Method.EROSION
                    )
            );
        });
        add(erosionButton, gc);

        gc.gridy = 2;
        JButton openingButton = new Button("Opening");
        openingButton.addActionListener(actionEvent -> {
            Storage.setEditedImage(
                    MathematicalMorphology.opening(
                            Storage.getEditedImage()
                    )
            );
        });
        add(openingButton, gc);

        gc.gridy = 3;
        JButton closingButton = new Button("Closing");
        closingButton.addActionListener(actionEvent -> {
            Storage.setEditedImage(
                    MathematicalMorphology.closing(
                            Storage.getEditedImage()
                    )
            );
        });
        add(closingButton, gc);

        gc.gridy = 4;
        JButton hitOrMissThinningButton = new Button("Hit-Or-Miss Thinning");
        hitOrMissThinningButton.addActionListener(actionEvent -> {
            Storage.setEditedImage(
                    MathematicalMorphology.hitOrMiss(
                            Storage.getEditedImage(), Method.HIT_OR_MISS_THINNING
                    )
            );
        });
        add(hitOrMissThinningButton, gc);

        gc.gridy = 5;
        JButton hitOrMissThickeningButton = new Button("Hit-Or-Miss Thickening");
        hitOrMissThickeningButton.addActionListener(actionEvent -> {
            Storage.setEditedImage(
                    MathematicalMorphology.hitOrMiss(
                            Storage.getEditedImage(), Method.HIT_OR_MISS_THICKENING
                    )
            );
        });
        add(hitOrMissThickeningButton, gc);

        setVisible(true);
    }
}
