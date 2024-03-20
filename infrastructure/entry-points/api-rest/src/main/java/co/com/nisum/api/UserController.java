package co.com.nisum.api;

import co.com.nisum.api.dto.TokenDTO;
import co.com.nisum.api.dto.UserRequestDTO;
import co.com.nisum.api.dto.UserResponseDTO;
import co.com.nisum.api.validations.ValidationMessage;
import co.com.nisum.api.validations.Validations;
import co.com.nisum.model.user.exceptionClass.BadRequestException;
import co.com.nisum.model.user.exceptionClass.EnumError;
import co.com.nisum.api.jwt.JwtUtil;
import co.com.nisum.api.mapper.UserMapper;
import co.com.nisum.model.user.User;
import co.com.nisum.model.user.exceptionClass.InternalServerException;
import co.com.nisum.model.user.exceptionClass.InvalidRequestException;
import co.com.nisum.usecase.user.UserUseCase;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user/", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UserController {

    @Autowired
    private UserUseCase userUseCase;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private Validations validations;

    @PostMapping(path = "/save")
    public ResponseEntity<UserResponseDTO> saveUser(@RequestBody UserRequestDTO user,
                                                    @RequestHeader("token") String token) {
        User response = null;
        if (!validations.validateEmail(user.getEmail())){
           throw new BadRequestException(EnumError.ERROR_400.getCodigo(), ValidationMessage.EMAILERROR.getMesage());
        }
        if (!validations.validatePassword(user.getPassword())){
            throw new BadRequestException(EnumError.ERROR_400.getCodigo(),ValidationMessage.PASSWORERROR.getMesage());
        }
        if (jwtUtil.validateToken(token)){
            response = userUseCase.saveUser(UserMapper.userRequestDTOToUser(user));
        }
        return new ResponseEntity<>(UserMapper.userToUserResponse(response), HttpStatus.OK);
    }
    @GetMapping(path = "/get")
    public ResponseEntity<List<UserResponseDTO>> getUsers(@RequestHeader("token") String token){
        List<User> users = null;
        if (jwtUtil.validateToken(token)){
             users = userUseCase.getUsers();
        }
        return new ResponseEntity<>(users.stream().map(UserMapper::userToUserResponse).toList(), HttpStatus.OK);
    }

    @GetMapping(path = "/get/{email}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable("email") String email,
                                                   @RequestHeader("token") String token){
        User response = null;
        if (jwtUtil.validateToken(token)){
            response = userUseCase.getUser(email);
        }
        return new ResponseEntity<>(UserMapper.userToUserResponse(response), HttpStatus.OK);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UserRequestDTO user,
                                                    @RequestHeader("token") String token) {
        User response = null;
        if (!validations.validateEmail(user.getEmail())){
            throw new BadRequestException(EnumError.ERROR_400.getCodigo(), ValidationMessage.EMAILERROR.getMesage());
        }
        if (!validations.validatePassword(user.getPassword())){
            throw new BadRequestException(EnumError.ERROR_400.getCodigo(),ValidationMessage.PASSWORERROR.getMesage());
        }
        if (jwtUtil.validateToken(token)){
            response = userUseCase.updateUser(UserMapper.userRequestDTOToUser(user));
        }
        return new ResponseEntity<>(UserMapper.userToUserResponse(response), HttpStatus.OK);
    }

    @PutMapping(path = "/disable/{email}")
    public ResponseEntity<UserResponseDTO> disableUser(@PathVariable("email") String email,
                                                   @RequestHeader("token") String token){
        User response = null;
        if (jwtUtil.validateToken(token)){
            response = userUseCase.disableUser(email);
        }
        return new ResponseEntity<>(UserMapper.userToUserResponse(response), HttpStatus.OK);
    }

    @GetMapping("/token")
    public ResponseEntity<TokenDTO> getToken(
            @RequestHeader("Secret") String secret) {
        try {
            var jwt= jwtUtil.generateToken(secret);
            if (jwt == null) {
                throw new InvalidRequestException(EnumError.ERROR_401.getCodigo(),EnumError.ERROR_401.getMesage());
            }
            return new ResponseEntity<>(new TokenDTO(jwt),HttpStatus.OK);
        } catch (Exception ex) {
            throw new InternalServerException(EnumError.ERROR_500.getCodigo(),EnumError.ERROR_500.getMesage());
        }
    }
}
