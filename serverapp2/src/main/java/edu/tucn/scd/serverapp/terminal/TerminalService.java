package edu.tucn.scd.serverapp.terminal;

import edu.tucn.scd.serverapp.position.Position;
import edu.tucn.scd.serverapp.position.PositionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service // creates one instance of this class
@Slf4j // adds the 'log(ger)' instance to this class

public class TerminalService {
    @Autowired // injects the TerminalRepository instance
    private TerminalRepository terminalRepository;

    @Autowired //  TerminalMapper instance
    private TerminalMapper terminalMapper;


    //create
    @Transactional(rollbackFor = Exception.class) // the method is executed in a transaction
    public TerminalDTO create(TerminalDTO terminalDTO) {
        TerminalDTO createdTerminalDTO =
                terminalMapper.terminalToDto(terminalRepository.save(terminalMapper.dtoToTerminal(terminalDTO)));

        log.info("Saved a new terminal with name " + createdTerminalDTO.getTerminalName());

        return createdTerminalDTO;
    }

    //delete


    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        log.info("Am ajuns in terminal service");terminalRepository.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public Optional<TerminalDTO> get(String id) {
        Optional<Terminal> terminalOptional = terminalRepository.findById(id);

        if (terminalOptional.isPresent()) {
            TerminalDTO getTerminalDTO = terminalMapper.terminalToDto(terminalOptional.get());
            return Optional.of(getTerminalDTO);
        } else {
            return Optional.empty();
        }
    }


    //update
    @Transactional(rollbackFor = Exception.class)
    public TerminalDTO update(String id, TerminalDTO terminalDTO) {
        Terminal terminal = terminalMapper.dtoToTerminal(terminalDTO);
        Optional<Terminal> terminal1 = terminalRepository.findById(id);
        if (terminal1.isPresent()) {
            Terminal existingTerminal = terminal1.get();
            if (terminal.getTerminalName() != null) {
                existingTerminal.setTerminalName(terminal.getTerminalName());
            }
            return terminalMapper.terminalToDto(terminalRepository.save((existingTerminal)));
        } else {
            throw new IllegalArgumentException("Terminalul cu id-ul specificat nu a fost găsit.");
        }
    }









}
