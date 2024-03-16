package co.com.nisum.usecase.user;

import co.com.nisum.model.user.User;
import co.com.nisum.model.user.gateways.UserGatewayRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserUseCase {

    private final UserGatewayRepository userGatewayRepository;

    public User saveUser(User user){
        return userGatewayRepository.saveUser(user);
    }

    public List<User> getUser(){
        return userGatewayRepository.getUser();
    }
}
