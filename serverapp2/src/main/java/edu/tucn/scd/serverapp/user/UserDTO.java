package edu.tucn.scd.serverapp.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) // Ascunde câmpul 'id' în documentația Swagger
    private Integer id;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
}
