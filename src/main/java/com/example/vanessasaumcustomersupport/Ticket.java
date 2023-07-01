package com.example.vanessasaumcustomersupport;

import java.util.HashMap;
import java.util.Map;

public class Ticket {
    private int id;
    private String customerName;
    private String subject;
    private String ticketBody;
    private Map<Integer, Attachment> attachments;

    public Ticket() {
        attachments = new HashMap<>();
    }

    public Ticket(int id, String customerName, String subject, String ticketBody) {
        this.id = id;
        this.customerName = customerName;
        this.subject = subject;
        this.ticketBody = ticketBody;
        attachments = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public void addAttachment(int index, Attachment attachment) {
        attachments.put(index, attachment);
    }

    public int getNumberOfAttachments() {
        return attachments.size();
    }

    public Attachment getAttachment(int index) {
        return attachments.get(index);
    }

    public Map<Integer, Attachment> getAllAttachments() {
        return attachments;
    }
}
