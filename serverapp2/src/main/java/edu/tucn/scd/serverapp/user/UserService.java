package edu.tucn.scd.serverapp.user;


import com.fasterxml.jackson.databind.JavaType;
import edu.tucn.scd.serverapp.exceptions.UserAlreadyExisting;
import lombok.extern.slf4j.Slf4j;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service // creates one instance of this class
@Slf4j // adds the 'log(ger)' instance to this class
public class UserService {

    @Autowired // injects the PositionRepository instance
    private UserRepository userRepository;
    @Autowired // injects the PositionMapper instance
    private UserMapper userMapper;



    @Transactional(rollbackFor = Exception.class) // the method is executed in a transaction
    public UserDTO create(UserDTO userDTO) {
        User existingUser = userRepository.findByUsername(userDTO.getUsername());
        if (existingUser != null) {
            throw new UserAlreadyExisting("Username already used!");
        }
        else{
            // Generarea unui salt aleator
            String plainPassword = userDTO.getPassword();
            String salt = BCrypt.gensalt();
            // Criptarea parolei

            String hashedPassword = BCrypt.hashpw(plainPassword,
                    salt);

            userDTO.setPassword(hashedPassword);
            UserDTO createdUserDTO =
                    userMapper.userToDto(userRepository.save(userMapper.dtoToUser(userDTO)));

            log.info("Saved a new user with username " + createdUserDTO.getUsername());

            return createdUserDTO;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public UserDTO get(String username) {
        User user = userRepository.findByUsername(username);
        UserDTO getUserDTO = new UserDTO();

        if (user != null) {
            getUserDTO.setId(user.getId());
            getUserDTO.setUsername(user.getUsername());
            getUserDTO.setPassword(user.getPassword());
            getUserDTO.setFirstName(user.getFirstName());
            getUserDTO.setLastName(user.getLastName());
        }
        return getUserDTO;
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public UserDTO update(Integer id, UserDTO userDTO) {
        User user = userMapper.dtoToUser(userDTO);
        Optional<User> user1 = userRepository.findById(id);
        if (userDTO.getUsername() == user1.get().getUsername())
            throw new UserAlreadyExisting("Username already used!");
        else {
            if (user1.isPresent()) {
                User existingUser = user1.get();
                User existingUsername = userRepository.findByUsername(userDTO.getUsername());
                if (existingUsername != null) {
                    throw new UserAlreadyExisting("Username already used!");
                } else {

                    if (user.getUsername() != null) {
                        existingUser.setUsername(user.getUsername());
                    }
                    if (user.getPassword() != null) {
                        existingUser.setPassword(user.getPassword());
                    }
                    if (user.getFirstName() != null) {
                        existingUser.setFirstName(user.getFirstName());
                    }
                    if (user.getLastName() != null) {
                        existingUser.setLastName(user.getLastName());
                    }
                    return userMapper.userToDto(userRepository.save((existingUser)));
                }
            } else {
                throw new IllegalArgumentException("User-ul cu id-ul specificat nu a fost găsit.");
            }
        }

    }

}
