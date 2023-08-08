package com.example.vanessasaumcustomersupport.site;

import com.example.vanessasaumcustomersupport.entities.UserPrincipal;

public interface UserPrincipalRepository extends GenericRepository<Long, UserPrincipal> {
    UserPrincipal getByUsername(String username);
}