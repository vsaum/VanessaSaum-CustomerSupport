package com.example.vanessasaumcustomersupport.site;

import com.example.vanessasaumcustomersupport.Attachment;
import com.example.vanessasaumcustomersupport.Ticket;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping("ticket")
public class TicketController {
    private volatile int TICKET_ID = 1;
    private Map<Integer, Ticket> ticketDB = new LinkedHashMap<>();

    @RequestMapping(value = {"list", ""})
    public String listTickets(Model model) {
        model.addAttribute("ticketDatabase", ticketDB);
        return "listTickets";
    }

    @GetMapping("create")
    public ModelAndView createTicket() {
        return new ModelAndView("ticketForm", "ticket", new TicketForm());
    }

    @PostMapping("create")
    public View createTicket(@ModelAttribute("ticket") TicketForm form) throws IOException {
        Ticket ticket = new Ticket();
        ticket.setSubject(form.getSubject());
        ticket.setTicketBody(form.getTicketBody());
        ticket.setCustomerName(form.getCustomerName());

        MultipartFile file = form.getAttachment();
        Attachment attachment = new Attachment();
        attachment.setName(file.getOriginalFilename());
        attachment.setContents(file.getBytes());
        if ((attachment.getName() != null && attachment.getName().length() > 0) ||
                (attachment.getContents() != null && attachment.getContents().length > 0)) {
            ticket.setAttachment(attachment);
        }

        int id;
        synchronized (this) {
            id = this.TICKET_ID++;
            ticketDB.put(id, ticket);
        }

        return new RedirectView("view/"+id, true, false);

    }

     public static class TicketForm {
         private String customerName;
         private String subject;
         private String ticketBody;
         private MultipartFile attachment;

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

         public MultipartFile getAttachment() {
             return attachment;
         }

         public void setAttachment(MultipartFile attachment) {
             this.attachment = attachment;
         }
     }

}
