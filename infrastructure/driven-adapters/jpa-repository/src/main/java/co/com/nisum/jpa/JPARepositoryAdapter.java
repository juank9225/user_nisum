package co.com.nisum.jpa;

import co.com.nisum.jpa.entity.UserEntity;
import co.com.nisum.jpa.helper.AdapterOperations;
import co.com.nisum.jpa.mapper.UserRepositoryMapper;
import co.com.nisum.model.user.User;
import co.com.nisum.model.user.gateways.UserGatewayRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JPARepositoryAdapter extends AdapterOperations<User, UserEntity, String, JPARepository>
implements UserGatewayRepository
{

    public JPARepositoryAdapter(JPARepository repository, ObjectMapper mapper) {
        super(repository, mapper, UserRepositoryMapper::userEntityToUser);
    }

    @Override
    public User saveUser(User user) {
        var userEntity = repository.save(UserRepositoryMapper.userToUserEntity(user));
        return UserRepositoryMapper.userEntityToUser(userEntity);
    }

    @Override
    public List<User> getUsers() {
        List<UserEntity> usersEntity = (List<UserEntity>) repository.findAll();
        return usersEntity.stream().map(UserRepositoryMapper::userEntityToUser).toList();
    }

    @Override
    public User getUserEmail(String email) {
        var user = repository.findById(email);
        return user.map(UserRepositoryMapper::userEntityToUser).orElse(null);
    }

    @Override
    public User updateUser(User user, User existingUser) {
        var userUpdate = repository.save(UserRepositoryMapper.userToUpdateToUserEntity(user,existingUser));
        return UserRepositoryMapper.userEntityToUser(userUpdate);
    }

    @Override
    public User disableUser(User user) {
        user = getUserEmail(user.getEmail().toString());
        var response = UserRepositoryMapper.userToDisableToUserEntity(user);
        var responseUpdate = repository.save(response);
        return UserRepositoryMapper.userEntityToUser(responseUpdate);
    }
}
