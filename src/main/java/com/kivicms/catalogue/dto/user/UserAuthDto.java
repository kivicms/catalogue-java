package com.kivicms.catalogue.dto.user;

import com.kivicms.catalogue.enums.user.UserRole;
import com.kivicms.catalogue.enums.user.UserStatus;
import com.kivicms.catalogue.models.user.User;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Data
public class UserAuthDto {
    private UUID id;
    private String email;
    private String avatar;
    private double balance;
    private String firstName;
    private String lastName;
    private String middleName;
    private UserRole role;
    private UserStatus status;

    public UserAuthDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.avatar = user.getAvatar();
        this.balance = user.getBalance();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.middleName = user.getMiddleName();
        this.status = user.getStatus();
    }
}
