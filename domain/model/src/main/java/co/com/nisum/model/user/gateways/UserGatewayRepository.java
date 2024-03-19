package co.com.nisum.model.user.gateways;

import co.com.nisum.model.user.User;

import java.util.List;

public interface UserGatewayRepository {
    User saveUser(User user);
    List<User> getUsers();
    User getUserEmail(String email);
    User updateUser(User user, User existingUser);
}
