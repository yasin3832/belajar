package car_rent.utils.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarRequestDTO {
    private String name;
    private Integer brand_id;
    private Boolean available;
}
