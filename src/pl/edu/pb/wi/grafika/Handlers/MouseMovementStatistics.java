package pl.edu.pb.wi.grafika.Handlers;

import pl.edu.pb.wi.grafika.DataStorage.Storage;
import pl.edu.pb.wi.grafika.UI.Elements.StatisticsNav;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MouseMovementStatistics extends MouseMotionAdapter {

    private StatisticsNav statisticsNav;

    public MouseMovementStatistics(StatisticsNav _statisticsNav) {
        super();

        this.statisticsNav = _statisticsNav;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);

        Storage.mousePosition = e.getPoint();
        statisticsNav.updateMousePosition();
    }
}
