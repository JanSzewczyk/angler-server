package pl.angler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendDto {
    private Long id;
    private Integer status;
    private LocalDate date;
    private Long userId;
    private String userNick;
}
