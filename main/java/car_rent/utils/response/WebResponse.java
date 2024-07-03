package car_rent.utils.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WebResponse<T> {
    private String status;
    private String message;
    private T data;
}
