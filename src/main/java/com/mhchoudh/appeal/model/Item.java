package com.mhchoudh.appeal.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mhchoudh.appeal.representations.Representation;

@XmlRootElement
public class Item {
    @XmlElement(namespace = Representation.APPEALS_NAMESPACE)
    private RewordAppeal marksExpected;
    @XmlElement(namespace = Representation.APPEALS_NAMESPACE)
    private Images reason;

    
    /**
     * For JAXB :-(
     */
    Item(){}
    
    public Item(Images reason, RewordAppeal marksExpected) {
        this.marksExpected = marksExpected;
        this.reason = reason;       
    }
    
    public RewordAppeal getMarksExpected() {
        return marksExpected;
    }

    public Images getReason() {
        return reason;
    }
    
    public String toString() {
        return reason + " " + marksExpected;
    }
}