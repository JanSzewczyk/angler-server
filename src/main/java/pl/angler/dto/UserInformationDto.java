package pl.angler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sun.util.resources.LocaleData;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInformationDto {
    private String nick;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private List<String> roles;
}
