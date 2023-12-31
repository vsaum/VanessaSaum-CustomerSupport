package com.example.vanessasaumcustomersupport.site;

import com.example.vanessasaumcustomersupport.entities.Attachment;

import java.io.Serializable;

public class Ticket implements Serializable {
    private long id;
    private String customerName;
    private String subject;
    private String ticketBody;
    private Attachment attachment;

    public Ticket() { super(); }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTicketBody() {
        return ticketBody;
    }

    public void setTicketBody(String ticketBody) {
        this.ticketBody = ticketBody;
    }

    public Attachment getAttachment() { return attachment; }
    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    public boolean hasAttachment() {
        return attachment != null && attachment.getName().length() > 0 && attachment.getContents().length > 0;
    }

    @Override
    public String toString() {
    return "Ticket{" +
            "id='" + id + '\'' +
            ", customerName='" + customerName +  '\'' +
            ", subject='" + subject + '\'' +
            ", ticketBody='" + ticketBody + '\'' +
            ", attachment=" + attachment +
            '}';
    }
}

