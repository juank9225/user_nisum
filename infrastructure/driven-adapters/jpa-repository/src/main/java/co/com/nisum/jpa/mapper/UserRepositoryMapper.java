package co.com.nisum.jpa.mapper;

import co.com.nisum.jpa.entity.PhoneEntity;
import co.com.nisum.jpa.entity.UserEntity;
import co.com.nisum.model.user.Phone;
import co.com.nisum.model.user.User;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class UserRepositoryMapper {

    public static User userEntityToUser(UserEntity userEntitys){
        return User.builder().id(userEntitys.getId())
                .name(userEntitys.getName())
                .email(userEntitys.getEmail())
                .password(userEntitys.getPassword())
                .phones(phonesEntityToModel(userEntitys.getPhones()))
                .created(userEntitys.getCreated())
                .modified(userEntitys.getModified())
                .lastLogin(userEntitys.getLastLogin())
                .token(userEntitys.getToken())
                .isActive(userEntitys.getIsActive()).build();
    }

    private static List<Phone> phonesEntityToModel(List<PhoneEntity> phones){
        return phones.stream().map(phone -> Phone.builder()
                .number(phone.getNumber())
                .cityCode(phone.getCityCode())
                .countryCode(phone.getCountryCode())
                .build()
        ).toList();
    }

    public static UserEntity userToUserEntity(User user){
        var userEntity = UserEntity.builder()
                .id(UUID.randomUUID().toString())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .created(new Date())
                .modified(new Date())
                .lastLogin(new Date())
                .token(UUID.randomUUID().toString())
                .isActive(true).build();
        userEntity.setPhones(phonesModelToEntity(user.getPhones(),userEntity));
        return userEntity;
    }

    private static List<PhoneEntity> phonesModelToEntity(List<Phone> phones, UserEntity userEntity){
        return phones.stream().map(phone -> PhoneEntity.builder()
                .number(phone.getNumber())
                .cityCode(phone.getCityCode())
                .countryCode(phone.getCountryCode())
                .user(userEntity)
                .build()
        ).toList();
    }

}
