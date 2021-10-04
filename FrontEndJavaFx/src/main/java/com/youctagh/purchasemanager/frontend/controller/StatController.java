package com.youctagh.purchasemanager.frontend.controller;

import com.youctagh.purchasemanager.frontend.domain.Item;
import com.youctagh.purchasemanager.frontend.domain.Ticket;
import com.youctagh.purchasemanager.frontend.model.StatModel;
import com.youctagh.purchasemanager.frontend.view.statistics.StatView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;

@Getter
@Setter
@Accessors(chain = true)
public class StatController {

    private GeneralController generalController;

    private StatView statView;
    private StatModel statModel;

    public StatController init() {
        initPieCB();
        initBarCB();
        return this;
    }

    private void initPieCB() {

        statView.getPieChartFilterByCB().getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldValue, newValue) -> {
                    if (newValue == null) {
                        statView.getPieChartFilterByCB().getSelectionModel().select(oldValue);
                        return;
                    }
                    System.out.println(oldValue + " " + newValue);
                    createPieChart(newValue);
                });

        statView.getPieChartFilterByCB().getItems().addAll(PieChartFilter.store
                , PieChartFilter.category
                , PieChartFilter.product);
        statView.getPieChartFilterByCB().getSelectionModel().select(0);
    }

    private void initBarCB() {

        statView.getBarChartFilterByCB().getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldValue, newValue) -> {
                    if (newValue == null) {
                        statView.getBarChartFilterByCB().getSelectionModel().select(oldValue);
                        return;
                    }
                    System.out.println(oldValue + " " + newValue);
                    createBarChart(newValue);
                });

        statView.getBarChartFilterByCB().getItems().addAll(BarChartFilter.monthly
                , BarChartFilter.yearly);
        statView.getBarChartFilterByCB().getSelectionModel().select(0);
    }

    private void createPieChart(PieChartFilter filter) {
        final HashMap<String, Double> data = getPieChartData(filter);

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        data.forEach((s, d) -> {
            pieChartData.add(new PieChart.Data(s, d));
        });
        statView.getPieChart().setData((pieChartData));
        statView.getPieChart().setTitle("Filter By " + filter.name());

    }

    private HashMap<String, Double> getPieChartData(PieChartFilter filter) {
        HashMap<String, Double> data = new HashMap<>();
        final ObservableList<Ticket> items = generalController.getTicketController().getTicketView().getDataTV().getItems();
        switch (filter) {
            case store:
                items.forEach(ticket -> {
                    Double totalPrice = data.getOrDefault(ticket.getStoreName(), 0D);
                    totalPrice += ticket.getItems().stream().map(Item::getPrice).reduce(0D, Double::sum);
                    data.put(ticket.getStoreName(), totalPrice);
                });
                break;
            case category:
                items.forEach(ticket -> {
                    ticket.getItems().forEach(item -> {
                        Double totalPrice = data.getOrDefault(item.getProduct().getCategoryName(), 0D);
                        data.put(item.getProduct().getCategoryName(), totalPrice + item.getPrice());
                    });
                });
                break;

            case product:
                items.forEach(ticket -> {
                    ticket.getItems().forEach(item -> {
                        Double totalPrice = data.getOrDefault(item.getProduct().getName(), 0D);
                        data.put(item.getProduct().getName(), totalPrice + item.getPrice());
                    });
                });
                break;
        }

        return data;
    }

    private void createBarChart(BarChartFilter filter) {
        final TreeMap<String, Double> data = getBarChartData(filter);

        statView.getBarChart().setTitle("Stat");
        statView.getBarChart().getXAxis().setLabel("Euros");
        statView.getBarChart().getYAxis().setLabel("Months");

        statView.getBarChart().getData().clear();
        XYChart.Series serie1 = new XYChart.Series();
        serie1.setName(filter.name());

        statView.getBarChart().getData().add(serie1);

        data.forEach((s, d) -> {
            serie1.getData().add(new XYChart.Data(s, d));
        });
        statView.getBarChart().setTitle("Filter By " + filter.name());

    }

    private TreeMap<String, Double> getBarChartData(BarChartFilter filter) {
        TreeMap<String, Double> data = new TreeMap<>();
        final ObservableList<Ticket> items = generalController.getTicketController().getTicketView().getDataTV().getItems();
        switch (filter) {
            case monthly:
                items.forEach(ticket -> {
                    Date date = ticket.getDate();
                    LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    int year = localDate.getYear();
                    int month = localDate.getMonthValue();
                    String key = year + " / " + String.format("%02d",month);
                    Double totalPrice = data.getOrDefault(key, 0D);
                    totalPrice += ticket.getItems().stream().map(Item::getPrice).reduce(0D, Double::sum);
                    data.put(key, totalPrice);
                });
                break;
            case yearly:
                items.forEach(ticket -> {
                    Date date = ticket.getDate();
                    LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    String key = String.valueOf(localDate.getYear());
                    Double totalPrice = data.getOrDefault(key, 0D);
                    totalPrice += ticket.getItems().stream().map(Item::getPrice).reduce(0D, Double::sum);
                    data.put(key, totalPrice);
                });
                break;
        }

        return data;
    }

    public enum PieChartFilter {
        product, store, category
    }

    public enum BarChartFilter {
        yearly, monthly
    }

}
