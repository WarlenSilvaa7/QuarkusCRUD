package project.warlen.service;

import jakarta.enterprise.context.ApplicationScoped;
import project.warlen.entity.UserEntity;

@ApplicationScoped
public class UserService {

    public UserEntity createUser(UserEntity userEntity) {
        UserEntity.persist(userEntity);
        return userEntity;
    }
}
