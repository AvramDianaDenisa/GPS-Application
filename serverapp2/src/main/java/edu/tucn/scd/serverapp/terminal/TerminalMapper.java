package edu.tucn.scd.serverapp.terminal;

import edu.tucn.scd.serverapp.position.Position;
import edu.tucn.scd.serverapp.position.PositionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import edu.tucn.scd.serverapp.position.PositionRepository;


@Component // creates one instance of this class
@Slf4j // adds the 'log(ger)' instance to this class
public class TerminalMapper {
    @Autowired // creates the injection of the PositionRepository instance
    private PositionRepository positionRepository;

    public TerminalDTO terminalToDto(Terminal terminal) {
        TerminalDTO terminalDTO = new TerminalDTO();
        terminalDTO.setId(terminal.getId());
        terminalDTO.setTerminalName(terminal.getTerminalName());
        return terminalDTO;
    }

    public Terminal dtoToTerminal(TerminalDTO terminalDTO){
        Terminal terminal = new Terminal();
        terminal.setId(terminalDTO.getId());
        terminal.setTerminalName(terminalDTO.getTerminalName());
        return terminal;
    }
}
