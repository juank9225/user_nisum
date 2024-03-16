package co.com.nisum.api;
import co.com.nisum.model.user.User;
import co.com.nisum.usecase.user.UserUseCase;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UserController {

    @Autowired
    private UserUseCase userUseCase;

    @PostMapping(path = "/save")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        return new ResponseEntity<>(userUseCase.saveUser(user), HttpStatus.OK);
    }
    @GetMapping(path = "/get")
    public ResponseEntity<List<User>> getUser(){
        return new ResponseEntity<>(userUseCase.getUser(), HttpStatus.OK);
    }
}
