package tech.schoolapi.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.schoolapi.core.config.modelMapper.ModelMapperService;
import tech.schoolapi.dto.response.user.UserResponse;
import tech.schoolapi.entity.User;
import tech.schoolapi.repository.UserRepository;
import tech.schoolapi.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapperService modelMapperService;

    @Override
    public ResponseEntity updateUser(User user) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Optional<User> optionalUser = userRepository.findById(user.getId());
        try {
            if(optionalUser.isPresent()) {
                userRepository.saveAndFlush(user);
                hashMap.put("Status", true);
                hashMap.put("Message", "Record has been updated!");
                return new ResponseEntity<>(hashMap, HttpStatus.OK);
            }
            hashMap.put("Status", false);
            hashMap.put("Message", "Record Not Found!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Record could not be updated!");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity deleteUser(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        boolean hasUser = userRepository.existsById(id);
        try {
            if(hasUser) {
                userRepository.deleteById(id);
                hashMap.put("Status", true);
                hashMap.put("Message", "Record has been deleted!");
                return new ResponseEntity<>(hashMap, HttpStatus.OK);
            }
            hashMap.put("Status", false);
            hashMap.put("Message", "Record Not Found!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Record could not be deleted!");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity findUser(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        User user = userRepository.findById(id).orElse(null);
        if(user != null) {
            hashMap.put("Status", true);
            hashMap.put("Result", modelMapperService.forResponse().map(user, UserResponse.class));
            return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
        }
        hashMap.put("Status", false);
        hashMap.put("Message", "Record Not Found!");
        return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity findAllUsers() {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<User> users = userRepository.findAll();
        List<UserResponse> converted = new ArrayList<>();
        if(users.isEmpty()){
            hashMap.put("Status", false);
            hashMap.put("Message", "Records Not Found!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        }
        hashMap.put("Status", true);
        for(User user : users){
            converted.add( modelMapperService.forResponse().map(user, UserResponse.class));
        }
        hashMap.put("Result", converted);
        return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
    }
}
