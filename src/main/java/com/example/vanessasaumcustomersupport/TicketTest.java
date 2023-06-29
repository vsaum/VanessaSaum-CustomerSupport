package com.example.vanessasaumcustomersupport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class TicketTest {

    @Test
    public void testTicket() {
        Ticket ticket = new Ticket();
        ticket.setCustomerName("Bart Simpson");
        ticket.setSubject("Issue");
        ticket.setTicketBody("Body of the ticket");

        Assertions.assertEquals("Bart Simpson", ticket.getCustomerName());
        Assertions.assertEquals("Issue", ticket.getSubject());
        Assertions.assertEquals("Body of the ticket", ticket.getTicketBody());
        Assertions.assertEquals(0, ticket.getNumberOfAttachments());
        Assertions.assertNotNull(ticket.getAllAttachments());
        Assertions.assertNull(ticket.getAttachment(1)); // Check for null attachment at index 1

        Attachment attachment1 = new Attachment();
        attachment1.setName("Attachment 1");
        attachment1.setContents(new byte[]{});

        Attachment attachment2 = new Attachment();
        attachment2.setName("Attachment 2");
        attachment2.setContents(new byte[]{});

        ticket.addAttachment(2, attachment1);
        ticket.addAttachment(3, attachment2);

        Assertions.assertEquals(2, ticket.getNumberOfAttachments());
        Assertions.assertNotNull(ticket.getAttachment(2));
        Assertions.assertNotNull(ticket.getAttachment(3));
    }
}
