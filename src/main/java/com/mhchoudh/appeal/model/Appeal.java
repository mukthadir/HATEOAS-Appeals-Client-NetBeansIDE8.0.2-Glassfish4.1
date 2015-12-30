package com.mhchoudh.appeal.model;

import java.util.List;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlTransient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Appeal {
    
    private static final Logger LOG = LoggerFactory.getLogger(Appeal.class);
    
    private final List<Item> items;
    @XmlTransient
    private AppealStatus status = AppealStatus.NotSent;
    

    public Appeal(AppealStatus status, List<Item> items) throws InterruptedException {
        this.items = items;
        this.status = status;
        LOG.debug("Executing Appeal constructor: status = {} and appeal items = {}", status, items);
        System.out.println();
//        Thread.sleep(1000);
        LOG.debug("appeal along with it's status is as below \n{}", this);
        Thread.sleep(1000);
        System.out.println();
    }
    
    public List<Item> getItems() throws InterruptedException {
        LOG.debug("Executing Appeal.getItems");
//        Thread.sleep(1000);
        System.out.println();
        LOG.debug("items = {}", items);
//        Thread.sleep(1000);
        System.out.println();
        return items;
    }

    public void setStatus(AppealStatus status) throws InterruptedException {
        LOG.debug("Executing Appeal.setStatus");
//        Thread.sleep(1000);
        this.status = status;
    }

    public AppealStatus getStatus() throws InterruptedException {
        LOG.debug("Executing Appeal.getStatus");
//        Thread.sleep(1000);
        return status;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t\tStatus of the appeal: " + status + "\n");
        int count = 0;
        for(Item i : items) {
            sb.append("\t\t\tAppeal item " + count++ +": " + i.toString()+ "\n");
        }
        return sb.toString();
    }
}