package co.edu.utp.isc.gia.restuser.web.controller;

import co.edu.utp.isc.gia.restuser.exceptios.UserNotFoundException;
import co.edu.utp.isc.gia.restuser.service.UserService;
import co.edu.utp.isc.gia.restuser.web.dto.UserDto;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping()
    public UserDto save(@RequestBody UserDto user){
        return userService.save(user); 
    }
    
    @GetMapping
    public List<UserDto> listAll() {
        return userService.listAll();
    }
    
    @GetMapping("/{id}")
    public UserDto findOne(@PathVariable("id") Long id) {
        return userService.findOne(id);
    }
    
    @PutMapping("/{id}")
    public UserDto updateOne(@PathVariable("id") Long id,@RequestBody UserDto user) {
        return userService.updateOne(id, user);
    }
    
    @DeleteMapping("/{id}")
    public UserDto removeOne(@PathVariable("id") Long id) {
        return userService.removeOne(id);
    }
}
