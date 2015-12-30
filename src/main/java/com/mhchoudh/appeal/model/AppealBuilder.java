package com.mhchoudh.appeal.model;

import static com.mhchoudh.appeal.model.ItemBuilder.item;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppealBuilder {
    
    private static final Logger LOG = LoggerFactory.getLogger(AppealBuilder.class);
    
    public static AppealBuilder appeal() {
        return new AppealBuilder();
    }

    private ArrayList<Item> items = null;
    private AppealStatus status = AppealStatus.NotSent;
    
    private void defaultItems() {
        LOG.debug("Executing AppealBuilder.defaultItems");
        ArrayList<Item> items = new ArrayList<Item>();
        items.add(item().build());
        this.items = items;
    }
    
    
    
    private void corruptItems() {
        LOG.debug("Executing AppealBuilder.corruptItems");
        ArrayList<Item> items = new ArrayList<Item>();
        items.add(null);
        items.add(null);
        items.add(null);
        items.add(null);
        this.items = items;
    }
   
    
    public Appeal build() throws InterruptedException {
        LOG.debug("Executing AppealBuilder.build");
//        Thread.sleep(1000);
        System.out.println();
        if(items == null) {
            defaultItems();
        }
        return new Appeal(status, items);
    }

    public AppealBuilder withItem(Item item) {
        LOG.debug("Executing AppealBuilder.withItem");
        if(items == null) {
            items = new ArrayList<Item>();
        }
        items.add(item);
        return this;
    }


    public AppealBuilder withCorruptedValues() {
        LOG.debug("Executing AppealBuilder.withCorruptedValues");
        corruptItems();
        return this;
    }
    
    public AppealBuilder withStatus(AppealStatus status) throws InterruptedException {
        LOG.debug("Executing AppealBuilder.withStatus");
//        Thread.sleep(1000);
        this.status = status;
        return this;
    }

    public AppealBuilder withRandomItems() throws InterruptedException {
        LOG.debug("Executing AppealBuilder.withRandomItems");
//        Thread.sleep(1000);
        System.out.println();
        int numberOfItems = (int) (System.currentTimeMillis() % 6 + 1);
        this.items = new ArrayList<Item>();
        for(int i = 0; i < numberOfItems; i++) {
            items.add(item().random().build());
        }
        return this;
    }

}
