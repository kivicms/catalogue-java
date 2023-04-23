package com.kivicms.catalogue.controllers.user.payload;

import com.kivicms.catalogue.enums.user.UserRole;
import com.kivicms.catalogue.enums.user.UserStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterPayload extends CommonUserPayload{
    private String name;
    private String email;
    private String lastName;
    private String firstName;
    private String middleName;
    private String password;
    private String confirmPassword;
    private UserStatus status;
    private UserRole role;
}
