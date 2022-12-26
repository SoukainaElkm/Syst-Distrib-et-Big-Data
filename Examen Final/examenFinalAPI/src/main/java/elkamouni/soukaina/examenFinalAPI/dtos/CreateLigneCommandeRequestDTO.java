package elkamouni.soukaina.examenFinalAPI.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateLigneCommandeRequestDTO {
    private int quantity;
    private double prixUnite;
    private double remise;
}
