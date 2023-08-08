package com.example.vanessasaumcustomersupport.site;

import com.example.vanessasaumcustomersupport.entities.UserPrincipal;

public interface AuthenticationService {
    UserPrincipal authenticate(String username, String password);
    void saveUser(UserPrincipal principal, String newPassword);
}
