package pl.edu.pb.wi.grafika.UI.Elements;

import pl.edu.pb.wi.grafika.DataStorage.Storage;
import pl.edu.pb.wi.grafika.UI.prefabs.Button;
import pl.edu.pb.wi.grafika.utils.FiltersUtils;

import javax.swing.*;
import java.awt.*;

public class FilterMaskJFrame extends JFrame {

    public FilterMaskJFrame() throws HeadlessException {
        super();

        setLayout(new GridBagLayout());
        setTitle("Mask Filter");
        setSize(new Dimension(400,400));
        setResizable(true);
        setBackground(Color.DARK_GRAY);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 1;
        gc.weighty = 0.95f;

        JTextArea textArea = new JTextArea();
        add(textArea, gc);

        gc.gridy = 1;
        gc.weighty = 0.05f;
        JButton apply = new Button("Apply");
        add(apply, gc);

        apply.addActionListener(actionEvent ->  {
            if(!Storage.isOriginalImagePresent()){
                Storage.setErrorMessage("No image found");
                return;
            }
            try {
                Storage.setEditedImage(
                        FiltersUtils.mask(
                                Storage.getEditedImage(),
                                textArea.getText()
                        )
                );
            } catch (Exception e){
                Storage.setErrorMessage(e.toString());
            }
        });

        setVisible(true);
    }
}
