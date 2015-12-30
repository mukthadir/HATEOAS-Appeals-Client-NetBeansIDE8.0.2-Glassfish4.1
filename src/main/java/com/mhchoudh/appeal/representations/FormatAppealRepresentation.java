package com.mhchoudh.appeal.representations;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mhchoudh.appeal.model.AppealFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@XmlRootElement(name = "format", namespace = Representation.APPEALS_NAMESPACE)
public class FormatAppealRepresentation extends Representation {
    
    private static final Logger LOG = LoggerFactory.getLogger(FormatAppealRepresentation.class);

    @XmlElement(namespace = FormatAppealRepresentation.APPEALS_NAMESPACE) private String receipientName;
    @XmlElement(namespace = FormatAppealRepresentation.APPEALS_NAMESPACE) private String senderName;
    @XmlElement(namespace = FormatAppealRepresentation.APPEALS_NAMESPACE) private String subject;
    @XmlElement(namespace = FormatAppealRepresentation.APPEALS_NAMESPACE) private int body;
    
    
    /**
     * For JAXB :-(
     */
     FormatAppealRepresentation() throws InterruptedException{
        LOG.debug("In FormatAppeal Representation Constructor");
//        Thread.sleep(1000);
        System.out.println();
     }
    
     
    public FormatAppealRepresentation(AppealFormat format, Link...links) throws InterruptedException {
        LOG.info("Creating a FormatAppeal Representation with the format = {} and links = {}", format, links);
//        Thread.sleep(1000);
        System.out.println();
        

        receipientName = format.getEmailReceipient();
        senderName = format.getEmailSender();
        subject = format.getSubject();
        body = format.getExpiryYear();
        this.links = java.util.Arrays.asList(links);
        
        LOG.info("Printing the header format of the eMail of the appeal which will be sent to the Professor:");
//        Thread.sleep(1000);
        System.out.println();
        LOG.info("To: {}", receipientName);
        LOG.info("From: {}", senderName);
        LOG.info("Subject: {}", subject);
//        Thread.sleep(1000);
        System.out.println();
        LOG.debug("Created the FormatAppeal Representation {}", this);
//        Thread.sleep(1000);
        System.out.println();
    }

    public AppealFormat getFormat() {
        return new AppealFormat(receipientName, senderName, subject, body);
    }
    
    public Link getReplyLink() {
        return getLinkByName(Representation.RELATIONS_URI + "reply");
    }
    
    public Link getAppealLink() {
        return getLinkByName(Representation.RELATIONS_URI + "appeal");
    }
    
}
