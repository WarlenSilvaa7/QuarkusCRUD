package project.warlen.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import project.warlen.entity.UserEntity;

@ApplicationScoped
public class UserService {

    public UserEntity createUser(UserEntity userEntity) {
        UserEntity.persist(userEntity);
        return userEntity;
    }

    public List<UserEntity> findAll(Integer page, Integer pageSize) {
        return UserEntity.findAll()
                .page(page, pageSize)
                .list();
    }
}
