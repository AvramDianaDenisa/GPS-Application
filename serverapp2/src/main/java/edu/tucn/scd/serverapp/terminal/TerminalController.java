package edu.tucn.scd.serverapp.terminal;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/terminal")
public class TerminalController {

    @Autowired
    private TerminalService terminalService;

    @PostMapping// maps the '/terminal' POST requests to this method
    @Operation(summary = " Create a new terminal") //swagger description
    @SecurityRequirement(name = "Bearer Authentication") // swagger auth
    public TerminalDTO create(@RequestBody TerminalDTO terminalDTO) {
        log.info("Creating a new terminal with ID: " + terminalDTO.getId());
        return terminalService.create(terminalDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a terminal")
    @SecurityRequirement(name = "Bearer Authentication")
    public void delete(@PathVariable String id) {
        log.info("Deleting terminal with ID: " + id);
        terminalService.delete(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a terminal")
    @SecurityRequirement(name = "Bearer Authentication")
    public Optional<TerminalDTO> get(@PathVariable String id) {
        log.info("Fetching terminal with ID: " + id);
        return terminalService.get(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a terminal")
    @SecurityRequirement(name = "Bearer Authentication")
    public TerminalDTO update(@PathVariable String id, @RequestBody TerminalDTO terminalDTO) {
        log.info("Updating terminal with ID: " + id);
        return terminalService.update(id, terminalDTO);
    }


}
