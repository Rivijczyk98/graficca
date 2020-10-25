package pl.edu.pb.wi.grafika.UI;

import javafx.application.Platform;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import pl.edu.pb.wi.grafika.DataStorage.Storage;
import pl.edu.pb.wi.grafika.Handlers.MouseMovementStatistics;
import pl.edu.pb.wi.grafika.UI.Elements.*;
import pl.edu.pb.wi.grafika.UI.prefabs.RGroup;
import pl.edu.pb.wi.grafika.utils.ColorUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainWindow extends JFrame {

    TopNav topNav;
    ToolsNav tools = new ToolsNav();
    PaintPanel paintPanel = new PaintPanel();
    StatisticsNav statisticsNav = new StatisticsNav();
    EditorNav editorNav = new EditorNav();
    JfxCubePanel jfxCubePanel = new JfxCubePanel();

    public MainWindow() throws HeadlessException {
        super();

        Storage.mainWindow = this;

        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        setTitle("Graphicca");
        setSize(1280, 720);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 1f;
        gc.weighty = 0.05f;
        gc.gridwidth = 2;

        topNav = new TopNav(paintPanel, tools);
        add(topNav, gc);

        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 1f;
        gc.weighty = 0.05f;
        gc.gridy = 1;
        gc.gridwidth = 2;
        add(editorNav, gc);

        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 0.1f;
        gc.weighty = 0.88f;
        gc.gridx = 0;
        gc.gridy = 2;
        gc.gridwidth = 1;
        add(tools, gc);

//        gc.fill = GridBagConstraints.BOTH;
//        gc.weightx = 0.9f;
//        gc.gridx = 1;
//        add(paintPanel, gc);

        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 1f;
        gc.weighty = 0.02f;
        gc.gridx = 0;
        gc.gridy = 3;
        gc.gridwidth = 2;
        add(statisticsNav, gc);

        paintPanel.addMouseMotionListener(new MouseMovementStatistics(statisticsNav));

        setVisible(true);

        tools.getRgbPicker()
                .setImage(ColorUtil.generateGradient(200, Color.BLACK, Color.WHITE, Color.BLACK, Color.RED));

        tools.getRgbPicker()
                .setColors(ColorUtil.getMultiColor(30, 200));

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                jfxCubePanel = new JfxCubePanel();
                PerspectiveCamera camera = new PerspectiveCamera();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        RGroup stack = new RGroup();
                        Scene scene = new Scene(stack,600,300);

                        scene.setFill(javafx.scene.paint.Color.DARKGRAY);
                        scene.setCamera(camera);

                        jfxCubePanel.setScene(scene);
                        stack.getChildren().add(jfxCubePanel.cube);

                        stack.translateXProperty().set(500);
                        stack.translateYProperty().set(300);
                        jfxCubePanel.addKeyListener(new KeyAdapter() {
                            @Override
                            public void keyPressed(KeyEvent e) {
                                super.keyPressed(e);

                                switch(e.getKeyCode()){
                                    case 37: //left
                                        stack.rotateByY(-20);
                                        break;
                                    case 38: //up
                                        stack.rotateByX(20);
                                        break;
                                    case 39: //right
                                        stack.rotateByY(20);
                                        break;
                                    case 40: //down
                                        stack.rotateByX(-20);
                                        break;
                                    case 107: //num -
                                        stack.zoom(-20);
                                        break;
                                    case 109: // num +
                                        stack.zoom(20);
                                        break;
                                }
                            }
                        });

                        gc.fill = GridBagConstraints.BOTH;
                        gc.weightx = 0.9f;
                        gc.gridwidth = 1;
                        gc.gridx = 1;
                        gc.gridy = 2;
                        add(jfxCubePanel, gc);
                    }
                });

            }
        });
    }

    public void setErrorText (String errorText){
        editorNav.changeErrorText(errorText);
    }

    public String getErrorMessage(){
        return editorNav.getError();
    }
}
