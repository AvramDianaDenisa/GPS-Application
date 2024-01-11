package edu.tucn.scd.serverapp.user;

import edu.tucn.scd.serverapp.terminal.Terminal;
import edu.tucn.scd.serverapp.terminal.TerminalDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component // creates one instance of this class
@Slf4j // adds the 'log(ger)' instance to this class
public class UserMapper {
    public UserDTO userToDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        return userDTO;
    }
    public User dtoToUser(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        return user;
    }
}
