package api.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class CreateUserNameErrorDto {

    Integer username;

    Integer code;
    String type;
    String message;


}
