package com.example.vanessasaumcustomersupport.site;

import com.example.vanessasaumcustomersupport.entities.Attachment;

public interface AttachmentRepository extends GenericRepository<Long, Attachment> {
    Attachment getByTicketId(Long ticketId);
}
