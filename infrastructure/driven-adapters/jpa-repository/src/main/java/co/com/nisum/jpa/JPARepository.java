package co.com.nisum.jpa;

import co.com.nisum.jpa.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface JPARepository extends CrudRepository<UserEntity, String>, QueryByExampleExecutor<UserEntity> {

}
