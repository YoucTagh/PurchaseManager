package com.youctagh.purchasemanager.frontend.controller;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.youctagh.purchasemanager.frontend.domain.*;
import com.youctagh.purchasemanager.frontend.mapper.*;
import com.youctagh.purchasemanager.frontend.model.GeneralModel;
import com.youctagh.purchasemanager.frontend.model.StatModel;
import com.youctagh.purchasemanager.frontend.model.category.CategoryModelImpl;
import com.youctagh.purchasemanager.frontend.model.product.ProductModelImpl;
import com.youctagh.purchasemanager.frontend.model.store.StoreModelImpl;
import com.youctagh.purchasemanager.frontend.model.ticket.TicketModelImpl;
import com.youctagh.purchasemanager.frontend.request.*;
import com.youctagh.purchasemanager.frontend.service.category.CategoryServiceImpl;
import com.youctagh.purchasemanager.frontend.service.product.ProductServiceImpl;
import com.youctagh.purchasemanager.frontend.service.store.StoreServiceImpl;
import com.youctagh.purchasemanager.frontend.service.ticket.TicketServiceImpl;
import com.youctagh.purchasemanager.frontend.view.general.GeneralView;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Accessors(chain = true)
public class GeneralController {
    private GeneralView generalView;
    private GeneralModel generalModel;

    private CategoryController categoryController;
    private StoreController storeController;
    private ProductController productController;
    private TicketController ticketController;

    public void init() {
        initControllers();
        initButtons();
    }

    private void initControllers() {
        categoryController = new CategoryController(this,
                generalView.getCategoryView(),
                new CategoryModelImpl(new CategoryServiceImpl()));

        storeController = new StoreController(this,
                generalView.getStoreView(),
                new StoreModelImpl(new StoreServiceImpl()));

        productController = new ProductController(this,
                generalView.getProductView(),
                new ProductModelImpl(new ProductServiceImpl(), new CategoryServiceImpl()));

        ticketController = new TicketController(this,
                generalView.getTicketView(),
                new TicketModelImpl(new TicketServiceImpl(), new ProductServiceImpl(), new StoreServiceImpl()));

    }

    private void initButtons() {
        generalView.getShowStatButton().addEventHandler(ActionEvent.ACTION, actionEvent -> {
            showStatView();
        });

        generalView.getExportDateButton().addEventHandler(ActionEvent.ACTION, actionEvent -> {
            exportCSVData();
        });

    }

    private void exportCSVData() {
        final List<Category> categories = generalView.getCategoryView().getDataTV().getItems();
        final List<Product> products = generalView.getProductView().getDataTV().getItems();
        final List<Store> stores = generalView.getStoreView().getDataTV().getItems();
        final List<Ticket> tickets = generalView.getTicketView().getDataTV().getItems();
        final Set<Item> items = tickets
                .stream()
                .map(Ticket::getItems)
                .reduce(new HashSet<>(), (items1, items2) -> {
                    final HashSet<Item> objects = new HashSet<>();
                    objects.addAll(items1);
                    objects.addAll(items2);
                    return objects;
                });

        final List<CategoryRequest> categoryRequests = categories
                .stream()
                .map(CategoryMapper::toCategoryRequest)
                .collect(Collectors.toList());

        final List<ProductRequest> productRequests = products
                .stream()
                .map(ProductMapper::toProductRequest)
                .collect(Collectors.toList());

        final List<StoreRequest> storeRequests = stores
                .stream()
                .map(StoreMapper::toStoreRequest)
                .collect(Collectors.toList());

        final List<TicketRequest> ticketRequests = tickets
                .stream()
                .map((Ticket ticket) -> TicketMapper.toTicketRequest(ticket, false))
                .collect(Collectors.toList());

        final List<ItemRequest> itemRequests = items
                .stream()
                .map((Item item) -> ItemMapper.toItemRequest(item,false))
                .collect(Collectors.toList());

        createCSVs(CategoryRequest.class, "data/categories.csv", categoryRequests);

        createCSVs(ProductRequest.class, "data/products.csv", productRequests);

        createCSVs(StoreRequest.class, "data/stores.csv", storeRequests);

        createCSVs(TicketRequest.class, "data/tickets.csv", ticketRequests);

        createCSVs(ItemRequest.class, "data/items.csv", itemRequests);

    }




    private boolean createCSVs(Class clazz, String fileName, List data) {
        // create mapper and schema
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(clazz);
        schema = schema.withColumnSeparator(',');

        // output writer
        ObjectWriter myObjectWriter = mapper.writer(schema.withHeader());
        File tempFile = new File(fileName);
        FileOutputStream tempFileOutputStream;
        try {
            tempFile.getParentFile().getAbsoluteFile().mkdir();
            tempFileOutputStream = new FileOutputStream(tempFile);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(tempFileOutputStream, 1024);
            OutputStreamWriter writerOutputStream = new OutputStreamWriter(bufferedOutputStream, "UTF-8");
            myObjectWriter.writeValue(writerOutputStream, data);

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void showStatView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/youctagh/purchasemanager/frontend/view/statistics/StatView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Statistics");
            final Scene scene = new Scene(root);
            stage.setScene(scene);

            StatController statController = new StatController()
                    .setStatModel(new StatModel())
                    .setStatView(loader.getController())
                    .setGeneralController(this);
            statController.init();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
