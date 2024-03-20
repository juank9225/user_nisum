package co.com.nisum.api;

import co.com.nisum.api.dto.PhoneDTO;
import co.com.nisum.api.dto.UserRequestDTO;
import co.com.nisum.api.jwt.JwtUtil;
import co.com.nisum.api.validations.Validations;
import co.com.nisum.model.user.Phone;
import co.com.nisum.model.user.User;
import co.com.nisum.usecase.user.UserUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class UserControllerTest {
    private final UserUseCase userUseCase = Mockito.mock(UserUseCase.class);
    private final JwtUtil jwtUtil = Mockito.mock(JwtUtil.class);
    private final Validations validations = Mockito.mock(Validations.class);
    private final UserController controller = new UserController(userUseCase, jwtUtil, validations);

    @BeforeEach
    void init() {
        ReflectionTestUtils.setField(validations, "passwordRegex", "^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$");
        ReflectionTestUtils.setField(validations, "emailRegex", "^[A-Za-z0-9+_.-]+@(.+)$");
    }

    @Test
    public void testSavePost(){
        PhoneDTO phoneDTO = PhoneDTO.builder()
                .number("1234567").cityCode("2")
                .countryCode("59").build();

        List<PhoneDTO> phoneDTOS = new ArrayList<>();
        phoneDTOS.add(phoneDTO);

        UserRequestDTO userRequestDTO = UserRequestDTO.builder()
                .name("Juan Salcedo")
                .email("juank@gmail.com")
                .password("OnePince123")
                .phones(phoneDTOS).build();

        Mockito.when(jwtUtil.validateToken(anyString())).thenReturn(true);
        Mockito.when(validations.validateEmail(anyString())).thenReturn(true);
        Mockito.when(validations.validatePassword(anyString())).thenReturn(true);
        Mockito.when(userUseCase.saveUser(any(User.class))).thenReturn(User.builder().email("juan@salcedo.co").phones(List.of()).build());

        var resultultado = controller.saveUser(userRequestDTO, "test");

        Assertions.assertNotNull(resultultado);
        Assertions.assertEquals(resultultado.getStatusCode(), HttpStatusCode.valueOf(200));
    }

    @Test
    public void testGetUser() {
        PhoneDTO phoneDTO = PhoneDTO.builder()
                .number("1234567").cityCode("2")
                .countryCode("59").build();

        List<PhoneDTO> phoneDTOS = new ArrayList<>();
        phoneDTOS.add(phoneDTO);

        UserRequestDTO userRequestDTO = UserRequestDTO.builder()
                .name("Juan Salcedo")
                .email("juank@gmail.com")
                .password("OnePince123")
                .phones(phoneDTOS).build();

        User user = new User();
        user.setId("fm880ea0-5636-44ee-8c59-0242ac140007");
        user.setName("JJuan Salcedo");
        user.setEmail("juank@gmail.com");
        user.setPassword("OnePince123");
        user.setCreated(new Date());
        user.setModified(new Date());
        user.setLastLogin(new Date());
        user.setToken("prueba-5636-44ee-8c59-0242ac140007");
        user.setIsActive(true);
        List<Phone> phones = new ArrayList<>();
        Phone phone = new Phone("1234567", "1", "56");
        phones.add(phone);
        user.setPhones(phones);

        Mockito.when(jwtUtil.validateToken(anyString())).thenReturn(true);
        Mockito.when(userUseCase.getUser(anyString())).thenReturn(user);

        var result = controller.getUser(userRequestDTO.getEmail(), "test");

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getStatusCode(), HttpStatusCode.valueOf(200));
    }

    @Test
    public void testGetUsers() {
        PhoneDTO phoneDTO = PhoneDTO.builder()
                .number("1234567").cityCode("2")
                .countryCode("59").build();

        List<PhoneDTO> phoneDTOS = new ArrayList<>();
        phoneDTOS.add(phoneDTO);

        UserRequestDTO userRequestDTO = UserRequestDTO.builder()
                .name("Juan Salcedo")
                .email("juank@gmail.com")
                .password("OnePince123")
                .phones(phoneDTOS).build();

        User user = new User();
        user.setId("fm880ea0-5636-44ee-8c59-0242ac140007");
        user.setName("JJuan Salcedo");
        user.setEmail("juank@gmail.com");
        user.setPassword("OnePince123");
        user.setCreated(new Date());
        user.setModified(new Date());
        user.setLastLogin(new Date());
        user.setToken("prueba-5636-44ee-8c59-0242ac140007");
        user.setIsActive(true);
        List<Phone> phones = new ArrayList<>();
        Phone phone = new Phone("1234567", "1", "56");
        phones.add(phone);
        user.setPhones(phones);

        List<User> users = new ArrayList<>();
        users.add(user);

        Mockito.when(jwtUtil.validateToken(anyString())).thenReturn(true);
        Mockito.when(userUseCase.getUsers()).thenReturn(users);

        var result = controller.getUsers("test");

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getStatusCode(), HttpStatusCode.valueOf(200));
    }

    @Test
    public void testUpdatePost(){
        PhoneDTO phoneDTO = PhoneDTO.builder()
                .number("1234567").cityCode("2")
                .countryCode("59").build();

        List<PhoneDTO> phoneDTOS = new ArrayList<>();
        phoneDTOS.add(phoneDTO);

        UserRequestDTO userRequestDTO = UserRequestDTO.builder()
                .name("Juan Salcedo")
                .email("juank@gmail.com")
                .password("OnePince123")
                .phones(phoneDTOS).build();

        User user = new User();
        user.setId("fm880ea0-5636-44ee-8c59-0242ac140007");
        user.setName("JJuan Salcedo");
        user.setEmail("juank@gmail.com");
        user.setPassword("OnePince123");
        user.setCreated(new Date());
        user.setModified(new Date());
        user.setLastLogin(new Date());
        user.setToken("prueba-5636-44ee-8c59-0242ac140007");
        user.setIsActive(true);
        List<Phone> phones = new ArrayList<>();
        Phone phone = new Phone("1234567", "1", "56");
        phones.add(phone);
        user.setPhones(phones);

        Mockito.when(jwtUtil.validateToken(anyString())).thenReturn(true);
        Mockito.when(validations.validateEmail(anyString())).thenReturn(true);
        Mockito.when(validations.validatePassword(anyString())).thenReturn(true);
        Mockito.when(userUseCase.updateUser(any(User.class))).thenReturn(user);

        var resultultado = controller.updateUser(userRequestDTO, "test");

        Assertions.assertNotNull(resultultado);
        Assertions.assertEquals(resultultado.getStatusCode(), HttpStatusCode.valueOf(200));
    }
}