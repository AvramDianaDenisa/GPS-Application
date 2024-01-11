package edu.tucn.scd.serverapp.terminal;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.tucn.scd.serverapp.position.Position;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class TerminalDTO {
    //@JsonProperty(access = JsonProperty.Access.READ_ONLY) // hides field 'id' for create in swagger
    private String id;
    private String terminalName;

}
