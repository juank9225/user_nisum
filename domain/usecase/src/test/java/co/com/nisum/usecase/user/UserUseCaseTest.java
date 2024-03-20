package co.com.nisum.usecase.user;

import co.com.nisum.model.user.Phone;
import co.com.nisum.model.user.User;
import co.com.nisum.model.user.gateways.UserGatewayRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@SpringBootTest(classes = UserUseCase.class)
class UserUseCaseTest {

    @MockBean
    private UserGatewayRepository userGatewayRepository;

    @SpyBean
    private UserUseCase userUseCase;

    @Test
    @DisplayName("Test Save User")
    public void testCreateUser(){
        User user = new User();
        user.setId("fm880ea0-5636-44ee-8c59-0242ac140007");
        user.setName("Juan Rodriguez");
        user.setEmail("juan@rodriguez.org");
        user.setPassword("Hunter256");
        user.setCreated(new Date());
        user.setModified(new Date());
        user.setLastLogin(new Date());
        user.setToken("prueba-5636-44ee-8c59-0242ac140007");
        user.setIsActive(true);
        List<Phone> phones = new ArrayList<>();
        Phone phone = new Phone("1234567","1","56");
        phones.add(phone);
        user.setPhones(phones);

        Mockito.when(userGatewayRepository.saveUser(user)).thenReturn(user);
        var response = userUseCase.saveUser(user);

        Assertions.assertEquals(response.getName(),"Juan Rodriguez");
        Assertions.assertEquals(response.getEmail(),"juan@rodriguez.org");
    }

    @Test
    @DisplayName("Test Get User")
    public void testGetUser(){
        User user = new User();
        user.setId("fm880ea0-5636-44ee-8c59-0242ac140007");
        user.setName("Juan Rodriguez");
        user.setEmail("juan@rodriguez.org");
        user.setPassword("Hunter256");
        user.setCreated(new Date());
        user.setModified(new Date());
        user.setLastLogin(new Date());
        user.setToken("prueba-5636-44ee-8c59-0242ac140007");
        user.setIsActive(true);
        List<Phone> phones = new ArrayList<>();
        Phone phone = new Phone("1234567","1","56");
        phones.add(phone);
        user.setPhones(phones);

        Mockito.when(userGatewayRepository.getUserEmail("juan@rodriguez.org")).thenReturn(user);
        var response = userUseCase.getUser("juan@rodriguez.org");

        Assertions.assertEquals(response.getName(),"Juan Rodriguez");
        Assertions.assertEquals(response.getId(), "fm880ea0-5636-44ee-8c59-0242ac140007");
    }

    @Test
    @DisplayName("Tes Get Users")
    public void tesGetUsers(){
        User user1 = new User();
        user1.setId("fm880ea01-5636-44ee-8c59-0242ac140008");
        user1.setName("Juan Rodriguez");
        user1.setEmail("juan@rodriguez.org");
        user1.setPassword("Hunter256");
        user1.setCreated(new Date());
        user1.setModified(new Date());
        user1.setLastLogin(new Date());
        user1.setIsActive(true);
        user1.setToken("prueba1-5636-44ee-8c59-0242ac140008");

        User user2 = new User();
        user2.setId("fm880ea02-5636-44ee-8c59-0242ac140007");
        user2.setName("Juan Salcedo");
        user2.setEmail("juan@salcedo.co");
        user2.setPassword("Hunter136");
        user2.setCreated(new Date());
        user2.setModified(new Date());
        user2.setLastLogin(new Date());
        user2.setIsActive(true);
        user2.setToken("prueba2-5636-44ee-8c59-0242ac140007");

        List<Phone> phones = new ArrayList<>();
        Phone phone = new Phone("1234567","1","56");
        phones.add(phone);
        user1.setPhones(phones);
        user2.setPhones(phones);

        List<User> usuarios = new ArrayList<>();
        usuarios.add(user1);
        usuarios.add(user2);

        Mockito.when(userGatewayRepository.getUsers()).thenReturn(usuarios);
        var response = userUseCase.getUsers();

        Assertions.assertEquals(response.size(), 2);
        Assertions.assertEquals(response.get(0).getName(),"Juan Rodriguez");
        Assertions.assertEquals(response.get(1).getName(),"Juan Salcedo");

    }

    @Test
    @DisplayName("Test Update User")
    public void testUpdateUser(){
        User userExist = new User();
        userExist.setId("fm880ea0-5636-44ee-8c59-0242ac140007");
        userExist.setName("Juan Rodriguez");
        userExist.setEmail("juan@rodriguez.org");
        userExist.setPassword("Hunter256");
        userExist.setCreated(new Date());
        userExist.setModified(new Date());
        userExist.setLastLogin(new Date());
        userExist.setIsActive(true);
        userExist.setToken("prueba-5636-44ee-8c59-0242ac140007");
        List<Phone> phones = new ArrayList<>();
        Phone phone = new Phone("1234567","1","56");
        phones.add(phone);
        userExist.setPhones(phones);

        User user = new User();
        user.setId("fm880ea0-5636-44ee-8c59-updateUser");
        user.setName("Juan Salcedo");
        user.setEmail("juan@rodriguez.org");
        user.setPassword("Hunter256");
        user.setCreated(new Date());
        user.setModified(new Date());
        user.setLastLogin(new Date());
        user.setIsActive(true);
        user.setToken("prueba-5636-44ee-8c59-0242ac140007");
        List<Phone> phones2 = new ArrayList<>();
        Phone phone2 = new Phone("1234567","1","56");
        phones.add(phone);
        user.setPhones(phones);

        Mockito.when(userGatewayRepository.getUserEmail("juan@rodriguez.org")).thenReturn(user);
        Mockito.when(userGatewayRepository.updateUser(user,user)).thenReturn(user);
        var response = userUseCase.updateUser(user);

        Assertions.assertEquals(response.getName(),"Juan Salcedo");
        Assertions.assertEquals(response.getId(),"fm880ea0-5636-44ee-8c59-updateUser");
    }

    @Test
    @DisplayName("Test Disable User")
    public void testDisableUser(){
        User user = new User();
        user.setId("fm880ea0-5636-44ee-8c59-updateUser");
        user.setName("Juan Salcedo");
        user.setEmail("juan@rodriguez.org");
        user.setPassword("Hunter256");
        user.setCreated(new Date());
        user.setModified(new Date());
        user.setLastLogin(new Date());
        user.setIsActive(true);
        user.setToken("prueba-5636-44ee-8c59-0242ac140007");
        List<Phone> phones = new ArrayList<>();
        Phone phone = new Phone("1234567","1","56");
        phones.add(phone);
        user.setPhones(phones);

        Mockito.when(userGatewayRepository.getUserEmail("juan@rodriguez.org")).thenReturn(user);
        Mockito.when(userGatewayRepository.disableUser(user)).thenReturn(user.toBuilder().isActive(false).build());

        var response = userUseCase.disableUser("juan@rodriguez.org");

        Assertions.assertEquals(response.getIsActive(),false);
    }

}