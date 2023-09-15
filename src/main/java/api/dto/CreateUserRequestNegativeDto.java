package api.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class CreateUserRequestNegativeDto {
    Integer id;
    String username;
    String firstName;
    String lastName;
    String email;
    String password;
    String phone;
    Value userStatus;
}
