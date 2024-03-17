package co.com.nisum.api.mapper;

import co.com.nisum.api.dto.PhoneDTO;
import co.com.nisum.api.dto.UserRequestDTO;
import co.com.nisum.api.dto.UserResponseDTO;
import co.com.nisum.model.user.Phone;
import co.com.nisum.model.user.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    public static User userRequestDTOToUser(UserRequestDTO userRequestDTO){
        return User.builder()
                .name(userRequestDTO.getName())
                .email(userRequestDTO.getEmail())
                .password(userRequestDTO.getPassword())
                .phones(phonesDTOToModel(userRequestDTO.getPhones()))
                .build();

    }

    private static List<Phone> phonesDTOToModel(List<PhoneDTO> phones){
        return phones.stream().map(phone -> Phone.builder()
                .number(phone.getNumber())
                .cityCode(phone.getCityCode())
                .countryCode(phone.getCountryCode())
                .build()
        ).toList();
    }

    public static UserResponseDTO userResponseDTOToUser(User user){
        var userResponseDTO = UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .created(user.getCreated())
                .modified(user.getModified())
                .lastLogin(user.getLastLogin())
                .token(user.getToken())
                .isActive(user.getIsActive()).build();
        userResponseDTO.setPhones(phonesModelToResponse(user.getPhones()));
        return userResponseDTO;
    }

    private static List<PhoneDTO> phonesModelToResponse(List<Phone> phones){
        return phones.stream().map(phone -> PhoneDTO.builder()
                .number(phone.getNumber())
                .cityCode(phone.getCityCode())
                .countryCode(phone.getCountryCode())
                .build()
        ).toList();
    }
}
