package co.com.nisum.usecase.user;

import co.com.nisum.model.user.User;
import co.com.nisum.model.user.exceptionClass.EnumError;
import co.com.nisum.model.user.exceptionClass.BadRequestException;
import co.com.nisum.model.user.gateways.UserGatewayRepository;
import lombok.RequiredArgsConstructor;;

import java.util.List;

@RequiredArgsConstructor
public class UserUseCase {

    private final UserGatewayRepository userGatewayRepository;

    public User saveUser(User user){
        User userGet = userGatewayRepository.getUserEmail(user.getEmail());
        if (userGet!=null){
            throw new BadRequestException(EnumError.ERROR_400.getCodigo(),EnumError.ERROR_400.getMesage());
        }
        return userGatewayRepository.saveUser(user);
    }

    public List<User> getUsers(){
        return userGatewayRepository.getUsers();
    }

    public User getUser(String email){
        User user = userGatewayRepository.getUserEmail(email);
        if (user==null){
            throw new BadRequestException(EnumError.ERROR_404.getCodigo(),EnumError.ERROR_404.getMesage());
        }
        return user;
    }

    public User updateUser(User user){
        User existingUser = userGatewayRepository.getUserEmail(user.getEmail());
        if (existingUser==null){
            throw new BadRequestException(EnumError.ERROR_404.getCodigo(),EnumError.ERROR_404.getMesage());
        }
        return userGatewayRepository.updateUser(user,existingUser);
    }

    public User disableUser(String email){
        User existingUser = getUser(email);
        if (existingUser==null){
            throw new BadRequestException(EnumError.ERROR_404.getCodigo(),EnumError.ERROR_404.getMesage());
        }
        return userGatewayRepository.disableUser(existingUser);
    }
}
