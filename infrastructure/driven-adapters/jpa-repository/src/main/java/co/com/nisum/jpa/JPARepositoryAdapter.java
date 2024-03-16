package co.com.nisum.jpa;

import co.com.nisum.jpa.entity.UserEntity;
import co.com.nisum.jpa.helper.AdapterOperations;
import co.com.nisum.jpa.mapper.UserMapper;
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
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, UserMapper::userEntityToUser);
    }

    @Override
    public User saveUser(User user) {
        var userEntity = repository.save(UserMapper.userToUserEntity(user));
        return UserMapper.userEntityToUser(userEntity);
    }

    @Override
    public List<User> getUser() {
        List<UserEntity> usersEntity = (List<UserEntity>) repository.findAll();
        return usersEntity.stream().map(UserMapper::userEntityToUser).toList();
    }
}
