package com.youctagh.purchasemanager.backend.init;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.youctagh.purchasemanager.backend.controller.v1.api.CategoryController;
import com.youctagh.purchasemanager.backend.controller.v1.api.ProductController;
import com.youctagh.purchasemanager.backend.controller.v1.api.StoreController;
import com.youctagh.purchasemanager.backend.controller.v1.api.TicketController;
import com.youctagh.purchasemanager.backend.controller.v1.request.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @author YoucTagh
 */
//@Component
public class InitDB implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(InitDB.class);

    private final CategoryController categoryController;
    private final StoreController storeController;
    private final ProductController productController;
    private final TicketController ticketController;

    public InitDB(CategoryController categoryController, StoreController storeController,
                  ProductController productController, TicketController ticketController) {
        this.categoryController = categoryController;
        this.storeController = storeController;
        this.productController = productController;
        this.ticketController = ticketController;
    }

    @Override
    public void run(String... args) {
        logger.info("Init data base ...");

        final List<CategoryRequest> categoryRequests = loadObjectList(CategoryRequest.class, "database/categories.csv");
        final List<StoreRequest> storeRequests = loadObjectList(StoreRequest.class, "database/stores.csv");
        final List<ProductRequest> productRequests = loadObjectList(ProductRequest.class, "database/products.csv");
        final List<ItemRequest> itemRequests = loadObjectList(ItemRequest.class, "database/items.csv");
        final List<TicketRequest> ticketRequests = loadObjectList(TicketRequest.class, "database/tickets.csv");

        HashMap<Long, HashSet<ItemRequest>> itemTicketMap = new HashMap<>();
        itemRequests.forEach(itemRequest -> {
            HashSet<ItemRequest> tmp = itemTicketMap.getOrDefault(itemRequest.getTicket_id(),
                    new HashSet<>());
            tmp.add(itemRequest);
            itemTicketMap.put(itemRequest.getTicket_id(), tmp);
        });

        categoryRequests.forEach(categoryController::addNewCategory);
        storeRequests.forEach(storeController::addNewStore);
        productRequests.forEach(productController::addNewProduct);

        ticketRequests.forEach(ticketRequest -> {
            ticketRequest.setItems(itemTicketMap.get(ticketRequest.getId()));
            ticketController.addNewTicket(ticketRequest);
        });

    }


    private <T> List<T> loadObjectList(Class<T> type, String fileName) {
        try {
            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
            CsvMapper mapper = new CsvMapper();
            mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
            File file = new ClassPathResource(fileName).getFile();

            MappingIterator<T> readValues =
                    mapper.readerFor(type).with(bootstrapSchema).readValues(file);
            return readValues.readAll();
        } catch (Exception e) {
            logger.error("Error occurred while loading object list from file " + fileName, e);
            return Collections.emptyList();
        }
    }
}
