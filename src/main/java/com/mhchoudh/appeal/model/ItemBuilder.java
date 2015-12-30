package com.mhchoudh.appeal.model;

import java.util.Random;


public class ItemBuilder {
    public static ItemBuilder item() {
        return new ItemBuilder();
    }

    private RewordAppeal marksExpected = RewordAppeal.Added_Description1;    
    private Images reason = Images.Attached_Image1;
    
    public Item build() {
        return new Item(reason, marksExpected);
    }
    
    public ItemBuilder withMarksExpected(RewordAppeal marksExpected) {
        this.marksExpected  = marksExpected;
        return this;
    }
    
    public ItemBuilder withReason(Images reason) {
        this.reason = reason;
        return this;
    }

    public ItemBuilder random() {
       
        Random r = new Random();
        reason = Images.values()[r.nextInt(Images.values().length)];
        marksExpected = RewordAppeal.values()[r.nextInt(RewordAppeal.values().length)];
      
        return this;
    }
}
