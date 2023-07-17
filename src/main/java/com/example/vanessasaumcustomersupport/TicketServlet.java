package com.example.vanessasaumcustomersupport;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "ticket", value = "/")
@MultipartConfig(fileSizeThreshold = 5_242_880, maxFileSize = 20_971_520L, maxRequestSize = 41_943_040L)
public class TicketServlet extends HttpServlet {
    private volatile int TICKET_ID = 1;
    private Map<Integer, Ticket> ticketDB = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String action = request.getParameter("action");

        if (action == null) {
            action = "listTickets";
        }

        switch (action) {
            case "createTicket":
                showTicketForm(request, response);
                break;
            case "viewTicket":
                viewTicket(request, response);
                break;
            case "downloadAttachment":
                downloadAttachment(request, response);
                break;
            default:
                listTickets(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String action = request.getParameter("action");

        if (action == null) {
            action = "listTickets";
        }

        switch (action) {
            case "createTicket":
                createTicket(request, response);
                break;
            default:
                response.sendRedirect("ticket");
        }
    }

    private void listTickets(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("ticketDatabase", ticketDB);
        request.getRequestDispatcher("WEB-INF/jsp/view/listTickets.jsp").forward(request, response);
    }

    private void createTicket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Ticket ticket = new Ticket();
        ticket.setId(TICKET_ID++);
        ticket.setCustomerName(request.getParameter("customerName"));
        ticket.setSubject(request.getParameter("subject"));
        ticket.setTicketBody(request.getParameter("ticketBody"));

        Part file = request.getPart("attachment");
        if (file != null) {
            Attachment attachment = processAttachment(file);
            if (attachment != null) {
                ticket.addAttachment(1, attachment);
            }
        }

        synchronized (this) {
            ticketDB.put(ticket.getId(), ticket);
        }

        response.sendRedirect("ticket?action=viewTicket&ticketId=" + ticket.getId());
    }

    private Attachment processAttachment(Part file) throws IOException {
        InputStream in = file.getInputStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        int read;
        final byte[] bytes = new byte[1024];
        while ((read = in.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }

        Attachment attachment = new Attachment();
        attachment.setName(file.getSubmittedFileName());
        attachment.setContents(out.toByteArray());

        return attachment;
    }

    private void viewTicket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int ticketId = Integer.parseInt(request.getParameter("ticketId"));
        Ticket ticket = getTicket(ticketId);
        request.setAttribute("ticket", ticket);
        request.setAttribute("ticketId", ticketId);
        request.getRequestDispatcher("WEB-INF/jsp/view/viewTicket.jsp").forward(request, response);
        }

    private void downloadAttachment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int ticketId = Integer.parseInt(request.getParameter("ticketId"));
        Ticket ticket = getTicket(ticketId);

        if (ticket != null && ticket.getNumberOfAttachments() > 0) {
            Attachment attachment = ticket.getAttachment(1);

            response.setHeader("Content-Disposition", "attachment; filename=\"" + attachment.getName() + "\"");

            ServletOutputStream stream = response.getOutputStream();
            stream.write(attachment.getContents());
            stream.flush();
        } else {
            response.sendRedirect("ticket");
        }
    }

    private Ticket getTicket(int ticketId) {
        return ticketDB.get(ticketId);
    }

    private void showTicketForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/jsp/view/ticketForm.jsp").forward(request, response);
    }
}
