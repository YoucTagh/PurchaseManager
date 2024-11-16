module FrontEndJavaFx {
    requires javafx.graphics;
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.controls;

    requires org.controlsfx.controls;
    requires lombok;
    requires java.desktop;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires java.sql;
    requires com.fasterxml.jackson.dataformat.csv;
    requires com.fasterxml.jackson.core;

    exports com.youctagh.purchasemanager.frontend.view.general;
    exports com.youctagh.purchasemanager.frontend.view.statistics;
    exports com.youctagh.purchasemanager.frontend.view.ticket;
    exports com.youctagh.purchasemanager.frontend.view.category;
    exports com.youctagh.purchasemanager.frontend.view.store;
    exports com.youctagh.purchasemanager.frontend.view.product;

    exports com.youctagh.purchasemanager.frontend.domain;
    exports com.youctagh.purchasemanager.frontend.request;
    exports com.youctagh.purchasemanager.frontend.domain.response;

    opens com.youctagh.purchasemanager.frontend.application;

    opens com.youctagh.purchasemanager.frontend.domain;

    opens com.youctagh.purchasemanager.frontend;

    opens com.youctagh.purchasemanager.frontend.view.statistics;
    opens com.youctagh.purchasemanager.frontend.view.general;

    opens com.youctagh.purchasemanager.frontend.view.ticket;
    opens com.youctagh.purchasemanager.frontend.view.category;
    opens com.youctagh.purchasemanager.frontend.view.store;
    opens com.youctagh.purchasemanager.frontend.view.product;



}
