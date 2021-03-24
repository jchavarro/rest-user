package co.edu.utp.isc.gia.restuser.service;

import co.edu.utp.isc.gia.restuser.web.dto.UserDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class UserService {
    
    private List<UserDto> users = new ArrayList<>(); 

    
    public UserDto save(UserDto user) {
        if(users.isEmpty()){
            user.setId(1L);
        }else{
            user.setId(users.get(users.size()-1).getId() + 1L);
        }
        users.add(user);
        
        return user;
    }
    
    public List<UserDto> listAll() {
        return users;
    }
    
    public UserDto findOne(Long id) {
        return users.get(id.intValue() - 1);
    }
    
    public UserDto upDateOne(Long id, UserDto user) {
        user.setId(id);        
        users.set(id.intValue() - 1, user);
        return users.get(id.intValue()-1);
    }
    
    public UserDto removeOne(Long id) {
        return users.remove(id.intValue()-1);
    }
    
}
