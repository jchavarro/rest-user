package co.edu.utp.isc.gia.restuser.service;

import co.edu.utp.isc.gia.restuser.web.dto.UserDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

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
        return users.get(searchById(id, users));
    }
    
    public UserDto updateOne(Long id, UserDto user) {
        UserDto userUpdate = null;
        for (int i=0; i<users.size(); i++) {
            if (id.equals(users.get(i).getId())) {
                user.setId(id);        
                users.set(i, user);
                userUpdate = users.get(i);
            }               
        }
        return userUpdate;
        
    }
    
    public UserDto removeOne(Long id) {
        UserDto user = null;
        for (int i=0; i<users.size(); i++) {
            if (id.equals(users.get(i).getId())) user = users.remove(i);               
        }
        return user;
    }
    
    private int searchById (Long id, List<UserDto> users) {
        for (int i=0; i<users.size(); i++) {
            if (id.equals(users.get(i).getId())) return i;               
        }
        return -1;
    }
    
}
