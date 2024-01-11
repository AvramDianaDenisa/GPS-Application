package edu.tucn.scd.serverapp.security;

import edu.tucn.scd.serverapp.exceptions.UnauthorizedException;
import edu.tucn.scd.serverapp.user.User;
import edu.tucn.scd.serverapp.user.UserDTO;
import edu.tucn.scd.serverapp.user.UserRepository;
import edu.tucn.scd.serverapp.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j // adds the 'log(ger)' instance to this class

/**
 * @author Radu Miron
 * @version 1
 */
@RestController // creates an instance of the current class
@RequestMapping("/login") // maps the requests starting with '/login' to this controller
public class LoginController {

    private final UserRepository userRepository;
    private final UserService userService;

    public LoginController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping
    public JwtTokenDTO login(@RequestBody CredentialsDTO credentialsDTO) {
        UserDTO user = userService.get(credentialsDTO.getUsername());

        if (user != null && BCrypt.checkpw(credentialsDTO.getPassword(), user.getPassword())) {
            JwtTokenDTO jwtTokenDTO = new JwtTokenDTO();
            jwtTokenDTO.setToken(JwtUtil.generateToken(credentialsDTO.getUsername()));
            return jwtTokenDTO;
        } else {
            throw new UnauthorizedException("Invalid credentials");
}}

}
