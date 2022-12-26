package elkamouni.soukaina.examenFinalAPI.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CreateCustumerRequestDTO {
    private String name;
    private String email;
    private String tel;
}
