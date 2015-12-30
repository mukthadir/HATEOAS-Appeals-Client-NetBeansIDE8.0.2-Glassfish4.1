package com.mhchoudh.appeal.representations;

import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.joda.time.DateTime;

import com.mhchoudh.appeal.model.AppealFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@XmlRootElement(name = "reply", namespace = Representation.APPEALS_NAMESPACE)
public class ReplyRepresentation extends Representation {
    
    private static final Logger LOG = LoggerFactory.getLogger(ReplyRepresentation.class);

    @XmlElement(name = "amount", namespace = Representation.APPEALS_NAMESPACE)
    private double amountPaid;
    @XmlElement(name = "paid", namespace = Representation.APPEALS_NAMESPACE)
    private String formatDate;
    
    ReplyRepresentation() throws InterruptedException{
        LOG.debug("In ReplyRepresentation Constructor");
//        Thread.sleep(1000);
        System.out.println();
    } 
    
    public ReplyRepresentation(AppealFormat format, Link appealLink) {
        LOG.info("Creating an Reply Representation with the format = {} and links = {}", format, links);
        
//        this.amountPaid = format.getAmount();
        this.formatDate = format.getAppealDate().toString();
        this.links = new ArrayList<Link>();
        links.add(appealLink);
        
        LOG.debug("Created the Reply Representation {}", this);
    }

    public DateTime getPaidDate() {
        return new DateTime(formatDate);
    }
    
    public double getAmountPaid() {
        return amountPaid;
    }
    
    public String getReply(){
        return "Appeal Approved";
    }
    public String getRejectReply(){
        return "Appeal Rejected";
    }

    public Link getAppealLink() {
        return getLinkByName(Representation.RELATIONS_URI + "appeal");
    }
    
    public String toString() {
        try {
            JAXBContext context = JAXBContext.newInstance(ReplyRepresentation.class);
            Marshaller marshaller = context.createMarshaller();

            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(this, stringWriter);

            return stringWriter.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
