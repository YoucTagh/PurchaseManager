package com.youctagh.purchasemanager.frontend.view.statistics;

import com.youctagh.purchasemanager.frontend.controller.StatController;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import lombok.Getter;

/**
 * @author YoucTagh
 */

@Getter
public class StatView {
    @FXML
    private BarChart barChart;
    @FXML
    private ComboBox<String> pieChartFilterCB;
    @FXML
    private ComboBox<StatController.BarChartFilter> barChartFilterByCB;
    @FXML
    private Label infoLabel;
    @FXML
    private ComboBox<StatController.PieChartFilter> pieChartFilterByCB;
    @FXML
    private PieChart pieChart;
}
