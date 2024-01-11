package edu.tucn.scd.serverapp.user;
import edu.tucn.scd.serverapp.position.PositionDTO;
import edu.tucn.scd.serverapp.terminal.TerminalDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController // creates an instance of the current class
@RequestMapping("/users") // maps the requests starting with '/users' to this controller
public class UserController {
    @Autowired // creates the injection of UserService instance
    private UserService userService;

    @PostMapping // maps the '/users' POST requests to this method
    @Operation(summary = " new user") // swagger description
    public UserDTO create(@RequestBody UserDTO userDTO) {
        return userService.create(userDTO);
    }

    @Operation(summary = "Get a user")
    @SecurityRequirement(name = "Bearer Authentication") // swagger auth
    @GetMapping("/{id}/get") // maps the '/users' GET requests to this method
    public UserDTO get(String username) {

        return userService.get(username);
    }

    @Operation(summary = "Delete a user")
    @DeleteMapping("/{id}/delete") // maps the '/users' DELETE requests to this method
    @SecurityRequirement(name = "Bearer Authentication") // swagger auth
    public void delete(Integer id) {
        userService.delete(id);
    }

    @Operation(summary = "Update a User")
    @PutMapping("/{id}") // maps the '/positions' PUT requests to this method
    @SecurityRequirement(name = "Bearer Authentication") // swagger auth
    public UserDTO update(Integer id, @RequestBody UserDTO userDTO) {
        return userService.update(id, userDTO);
    }








}
