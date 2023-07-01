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
        PrintWriter out = response.getWriter();

        out.println("<html><body><h2>Ticket List</h2>");
        out.println("<a href=\"ticket?action=createTicket\">Create Ticket</a><br><br>");

        if (ticketDB.isEmpty()) {
            out.println("There are no tickets yet...");
        } else {
            for (int id : ticketDB.keySet()) {
                Ticket ticket = ticketDB.get(id);
                out.println("Ticket #" + id);
                out.println(": <a href=\"ticket?action=viewTicket&ticketId=" + id + "\">");
                out.println(ticket.getSubject() + "</a><br>");
            }
        }

        out.println("</body></html>");
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
        PrintWriter out = response.getWriter();

        int ticketId = Integer.parseInt(request.getParameter("ticketId"));
        Ticket ticket = getTicket(ticketId);

        if (ticket != null) {
            out.println("<html><body><h2>Ticket Details</h2>");
            out.println("<b>ID:</b> " + ticket.getId() + "<br>");
            out.println("<b>Customer Name:</b> " + ticket.getCustomerName() + "<br>");
            out.println("<b>Subject:</b> " + ticket.getSubject() + "<br>");
            out.println("<b>Ticket Body:</b> " + ticket.getTicketBody() + "<br>");
            out.println("<b>Attachment:</b> " + ticket.getAttachment(1).getName() + "<br>");

            if (ticket.getNumberOfAttachments() > 0) {
                out.println("<a href=\"ticket?action=downloadAttachment&ticketId=" + ticket.getId() + "\">Download Attachment</a>");
            }

            out.println("<br><br><a href=\"ticket\">Back to Ticket List</a>");
            out.println("</body></html>");
        } else {
            out.println("<html><body><h2>Ticket Not Found</h2>");
            out.println("<a href=\"ticket\">Back to Ticket List</a>");
            out.println("</body></html>");
        }
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
        PrintWriter out = response.getWriter();

        out.println("<html><body><h2>Create Ticket</h2>");
        out.println("<form method=\"post\" action=\"ticket\" enctype=\"multipart/form-data\">");
        out.println("<input type=\"hidden\" name=\"action\" value=\"createTicket\">");
        out.println("<label for=\"customerName\">Customer Name:</label><br>");
        out.println("<input type=\"text\" id=\"customerName\" name=\"customerName\" required><br><br>");
        out.println("<label for=\"subject\">Subject:</label><br>");
        out.println("<input type=\"text\" id=\"subject\" name=\"subject\" required><br><br>");
        out.println("<label for=\"ticketBody\">Ticket Body:</label><br>");
        out.println("<textarea id=\"ticketBody\" name=\"ticketBody\" required></textarea><br><br>");
        out.println("<label for=\"attachment\">Attachment:</label><br>");
        out.println("<input type=\"file\" id=\"attachment\" name=\"attachment\"><br><br>");
        out.println("<input type=\"submit\" value=\"Submit\">");
        out.println("</form>");
        out.println("</body></html>");
    }
}
