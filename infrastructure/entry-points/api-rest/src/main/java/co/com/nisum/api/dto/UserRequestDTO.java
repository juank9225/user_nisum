package co.com.nisum.api.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserRequestDTO {
    private String name;
    private String email;
    private String password;
    private List<PhoneDTO> phones;

}
