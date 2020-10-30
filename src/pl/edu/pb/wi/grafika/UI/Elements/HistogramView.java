package pl.edu.pb.wi.grafika.UI.Elements;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;
import pl.edu.pb.wi.grafika.DataStorage.Storage;
import pl.edu.pb.wi.grafika.utils.HistogramUtils;

import javax.swing.*;
import java.awt.*;

public class HistogramView extends JPanel {

    private JFreeChart grayHistogram;
    private JFreeChart redHistogram;
    private JFreeChart greenHistogram;
    private JFreeChart blueHistogram;

    private JLabel grayLabel;
    private JLabel redLabel;
    private JLabel greenLabel;
    private JLabel blueLabel;

    public HistogramView() {
        super(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        Storage.setHistograms(HistogramUtils.calculateHistograms(Storage.getEditedImage()));

        gc.fill = 1;
        gc.weighty = .5f;
        gc.weightx = .5f;
        grayLabel = new JLabel(setGray(Storage.getHistograms()[3]));
        add(grayLabel, gc);

        gc.gridx = 1;
        redLabel = new JLabel(setRed(Storage.getHistograms()[0]));
        add(redLabel, gc);

        gc.gridy = 1;
        blueLabel = new JLabel(setBlue(Storage.getHistograms()[2]));
        add(blueLabel, gc);

        gc.gridx = 0;
        greenLabel = new JLabel(setGreen(Storage.getHistograms()[1]));
        add(greenLabel, gc);

    }

    private ImageIcon setRed(int[] histogram){
        DefaultCategoryDataset redHD = new DefaultCategoryDataset();
        for(int i = 0; i < histogram.length; i++ ){
            redHD.addValue(histogram[i], "value", i + "");
        }

        redHistogram = ChartFactory.createBarChart(
                "Red",
                "",
                "Value",
                redHD,
                PlotOrientation.VERTICAL,
                false, false, false);

        CategoryPlot plotRed = redHistogram.getCategoryPlot();
        plotRed.setRenderer( new CustomRenderer(Color.RED));

        return new ImageIcon(redHistogram.createBufferedImage(600, 400));
    }

    private ImageIcon setGreen(int[] histogram){
        DefaultCategoryDataset greenHD = new DefaultCategoryDataset();
        for(int i = 0; i < histogram.length; i++ ){
            greenHD.addValue(histogram[i], "value", i + "");
        }

        greenHistogram = ChartFactory.createBarChart(
                "Green",
                "",
                "Value",
                greenHD,
                PlotOrientation.VERTICAL,
                false, false, false);

        CategoryPlot greenPlot = greenHistogram.getCategoryPlot();
        greenPlot.setRenderer( new CustomRenderer(Color.GREEN));

        return new ImageIcon(greenHistogram.createBufferedImage(600, 400));
    }

    private ImageIcon setBlue(int[] histogram){
        DefaultCategoryDataset blueHD = new DefaultCategoryDataset();
        for(int i = 0; i < histogram.length; i++ ){
            blueHD.addValue(histogram[i], "value", i + "");
        }

        blueHistogram = ChartFactory.createBarChart(
                "Blue",
                "",
                "Value",
                blueHD,
                PlotOrientation.VERTICAL,
                false, false, false);

        CategoryPlot plotBlue = blueHistogram.getCategoryPlot();
        plotBlue.setRenderer( new CustomRenderer(Color.BLUE));

        return new ImageIcon(blueHistogram.createBufferedImage(600, 400));
    }

    private ImageIcon setGray(int[] histogram){
        DefaultCategoryDataset grayHD = new DefaultCategoryDataset();
        for(int i = 0; i < histogram.length; i++ ){
            grayHD.addValue(histogram[i], "value", i + "");
        }

        grayHistogram = ChartFactory.createBarChart(
                "Grayscale",
                "",
                "Value",
                grayHD,
                PlotOrientation.VERTICAL,
                false, false, false);

        CategoryPlot plotGray = grayHistogram.getCategoryPlot();
        plotGray.setRenderer( new CustomRenderer(Color.DARK_GRAY));

        return new ImageIcon(grayHistogram.createBufferedImage(600, 400));
    }

    public void setHistograms(){
        redLabel.setIcon(setRed(Storage.getHistograms()[0]));
        greenLabel.setIcon(setGreen(Storage.getHistograms()[1]));
        blueLabel.setIcon(setBlue(Storage.getHistograms()[2]));
        grayLabel.setIcon(setGray(Storage.getHistograms()[3]));
    }
}

class CustomRenderer extends BarRenderer {

    Paint color;

    public CustomRenderer(Paint _color) {
        super();
        color = _color;
    }

    @Override
    public Paint getItemPaint(int row, int column) {
        return color;
    }
}
