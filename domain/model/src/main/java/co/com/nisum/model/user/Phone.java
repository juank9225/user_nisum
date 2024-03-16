package co.com.nisum.model.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Phone {
    private String number;
    private String cityCode;
    private String countryCode;
}
