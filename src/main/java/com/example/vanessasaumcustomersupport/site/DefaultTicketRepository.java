package com.example.vanessasaumcustomersupport.site;

import com.example.vanessasaumcustomersupport.entities.TicketEntity;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultTicketRepository extends GenericJpaRepository<Long, TicketEntity> implements TicketRepository {
}
