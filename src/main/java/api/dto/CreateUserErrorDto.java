package api.dto;

import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class CreateUserErrorDto {

    Integer code;
    String type;
    String message;


}
