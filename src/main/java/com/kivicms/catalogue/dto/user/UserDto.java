package com.kivicms.catalogue.dto.user;

import com.kivicms.catalogue.dto.AbstractFullDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto extends AbstractFullDto {
    @NotEmpty(message = "Обязательное поле.")
    @Email(message = "Недействительный адрес.", flags = { Pattern.Flag.CASE_INSENSITIVE })
    private String email;
    private String password;
}
