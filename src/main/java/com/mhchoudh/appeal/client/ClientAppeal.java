package com.mhchoudh.appeal.client;

import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mhchoudh.appeal.model.Item;
import com.mhchoudh.appeal.model.Appeal;
import com.mhchoudh.appeal.model.AppealStatus;
import com.mhchoudh.appeal.representations.Representation;
import java.util.logging.Level;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@XmlRootElement(name = "appeal", namespace = Representation.APPEALS_NAMESPACE)
public class ClientAppeal {
    
    private static final Logger LOG = LoggerFactory.getLogger(ClientAppeal.class);
    
    @XmlElement(name = "item", namespace = Representation.APPEALS_NAMESPACE)
    private List<Item> items;
    @XmlElement(name = "status", namespace = Representation.APPEALS_NAMESPACE)
    private AppealStatus status;
    
    private ClientAppeal(){}
    
    public ClientAppeal(Appeal appeal) throws InterruptedException {
        LOG.debug("Executing ClientAppeal constructor");
    //    Thread.sleep(1000);
        System.out.println();
        this.items = appeal.getItems();
    }
    
    public Appeal getAppeal() throws InterruptedException {
        LOG.debug("Executing ClientAppeal.getAppeal");
    //    Thread.sleep(1000);
        return new Appeal(status, items);
    }
    
    public List<Item> getItems() throws InterruptedException {
        LOG.debug("Executing ClientAppeal.getItems");
    //    Thread.sleep(1000);
        return items;
    }

    @Override
    public String toString() {
        LOG.debug("Executing ClientAppeal.toString");
        
        try {
            JAXBContext context = JAXBContext.newInstance(ClientAppeal.class);
            Marshaller marshaller = context.createMarshaller();

            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(this, stringWriter);

            return stringWriter.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public AppealStatus getStatus() throws InterruptedException {
        LOG.debug("Executing ClientAppeal.getStatus");
//        Thread.sleep(1000);
        return status;
    }

    public double getCost() throws InterruptedException {
        LOG.debug("Executing ClientAppeal.getCost");
//        Thread.sleep(1000);
        double total = 0.0;
        
        return total;
    }
}