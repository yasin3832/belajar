package car_rent.utils.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RentRequestDTO {
    private Boolean completed;
    private Integer car_id;
    private Integer user_id;
    private Integer id;
}
