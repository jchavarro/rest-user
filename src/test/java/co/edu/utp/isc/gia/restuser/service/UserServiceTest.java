
package co.edu.utp.isc.gia.restuser.service;

import co.edu.utp.isc.gia.restuser.data.entity.User;
import co.edu.utp.isc.gia.restuser.data.repository.UserRepository;
import co.edu.utp.isc.gia.restuser.exceptions.BadRequestException;
import co.edu.utp.isc.gia.restuser.exceptions.UserNotFoundException;
import co.edu.utp.isc.gia.restuser.web.dto.UserDto;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {
     private UserRepository userRepositoryTest;
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        userRepositoryTest = Mockito.mock(UserRepository.class);
        modelMapper = new ModelMapper();

    }

    @Test
    public void testSaveAllDataOk_ResultOk() {
        User resulted = new User(1L, "Juan", "12345", "Juan", "j.chavarro@utp.edu.co");
        when(userRepositoryTest.save(any(User.class))).thenReturn(resulted);

        //input
        UserDto user = new UserDto(null, "Juan", "12345", "Juan", "j.chavarro@utp.edu.co");

        //Target
        UserService instance = new UserService(userRepositoryTest, modelMapper);

        //Expected
        UserDto expResult = new UserDto(1L, "Juan", "12345", "Juan", "j.chavarro@utp.edu.co");

        //Test
        UserDto result = instance.save(user);

        //Validaciones
        assertEquals(expResult.getId(), result.getId());
        assertEquals(expResult.getUsername(), result.getUsername());
        assertEquals(expResult.getPassword(), result.getPassword());
        assertEquals(expResult.getName(), result.getName());
        assertEquals(expResult.getEmail(), result.getEmail());
    }
//

    @Test
    public void testSaveParamNull_ResultException() {
        //input
        UserDto user = null;

        //Target
        UserService instance = new UserService(userRepositoryTest, modelMapper);

        //Test 
        //Validaciones
        Assertions.assertThrows(BadRequestException.class, () -> {
           UserDto result = instance.save(user);
        });
    }
    
    @Test 
    public void testListAll_ResultNullException() {
        //input
        //no data in db
        
        //target 
        UserService instance = new UserService(userRepositoryTest, modelMapper);
        
        //Test
        //validations
        Assertions.assertThrows(UserNotFoundException.class, () -> {
           List<UserDto> result = instance.listAll();
        });

    }
    
       @Test
    public void testFindOneAllDataOk_ResultOk() {
        User resulted = new User(1L, "Juan", "12345", "Juan", "j.chavarro@utp.edu.co");
        when(userRepositoryTest.save(any(User.class))).thenReturn(resulted);

        //input
        Long id = 1L;

        //Target
        UserService instance = new UserService(userRepositoryTest, modelMapper);

        //Expected
        UserDto expResult = new UserDto(1L, "Juan", "12345", "Juan", "j.chavarro@utp.edu.co");

        //Test
        UserDto result = instance.findOne(id);

        //Validaciones
        assertEquals(expResult.getId(), result.getId());
        assertEquals(expResult.getUsername(), result.getUsername());
        assertEquals(expResult.getPassword(), result.getPassword());
        assertEquals(expResult.getName(), result.getName());
        assertEquals(expResult.getEmail(), result.getEmail());
    }
//

    @Test
    public void testFindOneParamNull_ResultException() {
        //input
        Long id = null;

        //Target
        UserService instance = new UserService(userRepositoryTest, modelMapper);

        //Test 
        //Validaciones
        Assertions.assertThrows(UserNotFoundException.class, () -> {
           UserDto result = instance.findOne(id);
        });
    }
    
    @Test
    public void testRemoveAllDataOk_ResultOk() {
        User resulted = new User(1L, "Juan", "12345", "Juan", "j.chavarro@utp.edu.co");
        when(userRepositoryTest.save(any(User.class))).thenReturn(resulted);

        //input
        Long id = 1L;

        //Target
        UserService instance = new UserService(userRepositoryTest, modelMapper);

        //Expected
        UserDto expResult = new UserDto(1L, "Juan", "12345", "Juan", "j.chavarro@utp.edu.co");

        //Test
        UserDto result = instance.removeOne(id);

        //Validaciones
        assertEquals(expResult.getId(), result.getId());
        assertEquals(expResult.getUsername(), result.getUsername());
        assertEquals(expResult.getPassword(), result.getPassword());
        assertEquals(expResult.getName(), result.getName());
        assertEquals(expResult.getEmail(), result.getEmail());
    }
//

    @Test
    public void testRemoveParamNull_ResultException() {
        //input
        Long id = null;

        //Target
        UserService instance = new UserService(userRepositoryTest, modelMapper);

        //Test 
        //Validaciones
        Assertions.assertThrows(UserNotFoundException.class, () -> {
           UserDto result = instance.removeOne(id);
        });
    }
    
    @Test
    public void testUpdateDataOk_ResultOk() {
        User resulted = new User(1L, "Juan", "12345", "Juan", "j.chavarro@utp.edu.co");
        when(userRepositoryTest.save(any(User.class))).thenReturn(resulted);

        //input
        Long id = 1L;
        UserDto user = new UserDto(null, "Juan", "12345", "Juan", "j.chavarro@utp.edu.co");
        
        //Target
        UserService instance = new UserService(userRepositoryTest, modelMapper);

        //Expected
        UserDto expResult = new UserDto(1L, "Juan", "12345", "Juan", "j.chavarro@utp.edu.co");

        //Test
        UserDto result = instance.removeOne(id);

        //Validaciones
        assertEquals(expResult.getId(), result.getId());
        assertEquals(expResult.getUsername(), result.getUsername());
        assertEquals(expResult.getPassword(), result.getPassword());
        assertEquals(expResult.getName(), result.getName());
        assertEquals(expResult.getEmail(), result.getEmail());
    }
//

    @Test
    public void testUpdateParamNull_ResultException() {
        //input
        Long id = null;
        UserDto user = null;

        //Target
        UserService instance = new UserService(userRepositoryTest, modelMapper);

        //Test 
        //Validaciones
        Assertions.assertThrows(UserNotFoundException.class, () -> {
           UserDto result = instance.updateOne(id,user);
        });
    }

}
