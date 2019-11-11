package pl.angler.dto;

import lombok.Data;

@Data
public class RetrieveAccountDto {
    private String email;
    private String newPassword;
}
