package co.edu.utp.isc.gia.restuser.service;

import co.edu.utp.isc.gia.restuser.data.entity.User;
import co.edu.utp.isc.gia.restuser.data.repository.UserRepository;
import co.edu.utp.isc.gia.restuser.exceptions.BadRequestException;
import co.edu.utp.isc.gia.restuser.exceptions.UserNotFoundException;
import co.edu.utp.isc.gia.restuser.web.dto.UserDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper = new ModelMapper();

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    
    public UserDto save(UserDto user) {
        if (validateUser(user)){
            User myUser = modelMapper.map(user, User.class);            
            UserDto userDto =  modelMapper.map(userRepository.save(myUser), UserDto.class);
            return userDto;
        }else {
            throw new BadRequestException("Usted es que es bobo metiendo usuarios nulos?");
        }
    }

    public List<UserDto> listAll() {
        List<UserDto> usersDto = null;
        List<User> users = (List<User>) userRepository.findAll();
        if (users != null && !users.isEmpty() ) {
            usersDto = new ArrayList<>();
            for (User user : users) {
                usersDto.add(modelMapper.map(user, UserDto.class));
            }
        }else{
            throw new UserNotFoundException("Users not Found" );
        }  
        return usersDto;
    }

    public UserDto findOne(Long id) {
        Optional<User> user = userRepository.findById(id);
        UserDto userDto = new UserDto();
        if (user.isPresent()) {
            
            return modelMapper.map(user.get(), UserDto.class);
        }else{
            throw new UserNotFoundException("User id : "+id+ " not Found" );
        }            

        
    }

    public UserDto updateOne(Long id, UserDto user) {
        user.setId(id);
        userRepository.save(modelMapper.map(user, User.class));
        return findOne(id);
    }

    public UserDto removeOne(Long id) {
        Optional<User> user = userRepository.findById(id);
        UserDto userDto = new UserDto();
        if (user.isPresent()) {
            userDto = modelMapper.map(user.get(), UserDto.class);
            userRepository.deleteById(id);
            return userDto;
            
        }else{
            throw new UserNotFoundException("User id : "+id+ " not Found" );
        }
    }
    
    private boolean validateUser(UserDto user){
        return user != null && user.getUsername() != null  && !user.getUsername().isEmpty() 
                && user.getPassword() != null  && !user.getPassword().isEmpty()
                && user.getName() != null  && !user.getName().isEmpty()
                && user.getEmail() != null  && !user.getEmail().isEmpty();
    }


}
