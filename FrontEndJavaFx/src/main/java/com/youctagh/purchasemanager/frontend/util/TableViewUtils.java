package com.youctagh.purchasemanager.frontend.util;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * @author YoucTagh
 */
public class TableViewUtils {

    public static <T, S> TableColumn<T, S> getColumn(TableView tableView, Class<T> clazz
            , Class<S> type, String columnName, int minWidth
            , int prefWidthBind, String propertyFactory) {
        TableColumn<T, S> column = new TableColumn<T, S>(columnName);
        column.setMinWidth(minWidth);
        column.prefWidthProperty().bind(tableView.widthProperty().divide(prefWidthBind));
        column.setCellValueFactory(new PropertyValueFactory<>(propertyFactory));
        return column;
    }
}
