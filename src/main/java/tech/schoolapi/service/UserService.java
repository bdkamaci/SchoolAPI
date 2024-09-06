package tech.schoolapi.service;

import org.springframework.http.ResponseEntity;
import tech.schoolapi.entity.User;

public interface UserService {

    public ResponseEntity updateUser(User user);

    public ResponseEntity deleteUser(Long id);

    public ResponseEntity findUser(Long id);

    public ResponseEntity findAllUsers();
}
