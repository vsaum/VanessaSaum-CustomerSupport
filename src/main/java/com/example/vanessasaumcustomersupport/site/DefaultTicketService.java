package com.example.vanessasaumcustomersupport.site;

import com.example.vanessasaumcustomersupport.entities.TicketEntity;
import jakarta.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultTicketService implements TicketService {

    @Inject
    TicketRepository ticketRepo;
    @Inject
    AttachmentRepository attachmentRepo;

    @Override
    @Transactional
    public List<Ticket> getAllTickets() {
        List<Ticket> list = new ArrayList<>();
        ticketRepo.getAll().forEach(e -> list.add(this.convert(e)));
        return list;
    }

    @Override
    public Ticket getTicket(long id) {
        TicketEntity entity = ticketRepo.get(id);
        return entity == null ? null : this.convert(entity);
    }

    private Ticket convert(TicketEntity entity) {
        Ticket ticket = new Ticket();
        ticket.setId(entity.getId());
        ticket.setCustomerName(entity.getCustomerName());
        ticket.setSubject(entity.getSubject());
        ticket.setTicketBody(entity.getTicketBody());
        ticket.setAttachment(attachmentRepo.getByTicketId(entity.getId()));

        return ticket;
    }

    @Override
    @Transactional
    public void save(Ticket ticket) {
        TicketEntity entity = new TicketEntity();
        entity.setId(ticket.getId());
        entity.setCustomerName(ticket.getCustomerName());
        entity.setSubject(ticket.getSubject());
        entity.setTicketBody(entity.getTicketBody());

        if (ticket.getId() < 1) {
            ticketRepo.add(entity);
            ticket.setId(entity.getId());
            if (ticket.hasAttachment()) {
                ticket.getAttachment().setTicketId(entity.getId());
                attachmentRepo.add(ticket.getAttachment());
            }
        } else {
            ticketRepo.update(entity);
        }

    }

    @Override
    @Transactional
    public void deleteTicket(long id) {
        ticketRepo.deleteById(id);

    }
}

