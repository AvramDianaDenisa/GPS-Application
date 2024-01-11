package edu.tucn.scd.serverapp.position;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author Radu Miron
 * @version 1
 */
@RestController // creates an instance of the current class
@RequestMapping("/positions") // maps the requests starting with '/positions' to this controller
public class PositionController {

    @Autowired // creates the injection of PositionService instance
    private PositionService positionService;


    //create
    @PostMapping // maps the '/positions' POST requests to this method
    @Operation(summary = "Save a new position") // swagger description
    public PositionDTO create(@RequestBody PositionDTO positionDTO) {
        return positionService.create(positionDTO);
    }

    //update
    @Operation(summary = "Update position")
    @PutMapping("/{id}") // maps the '/positions' PUT requests to this method
    @SecurityRequirement(name = "Bearer Authentication") // swagger auth
    public PositionDTO update(Integer id, @RequestBody PositionDTO positionDTO){
        return positionService.update(id, positionDTO);
    }

    //delete
    @Operation(summary = "Delete a position")
    @DeleteMapping("/{id}/delete") // maps the '/positions' DELETE requests to this method
    @SecurityRequirement(name = "Bearer Authentication") // swagger auth
    public void delete(Integer id) {
        positionService.delete(id);
    }




    @GetMapping // maps the '/positions' GET requests to this method
    @Operation(summary = "Get all positions for a terminal between two dates") // swagger description
    @SecurityRequirement(name = "Bearer Authentication") // swagger auth
    public List<PositionDTO> findPositions(@RequestParam String terminalId,
                                           @RequestParam Date startDate,
                                           @RequestParam Date endDate) {
        return positionService.findByTerminalIdStartDateEndDate(terminalId, startDate, endDate);
    }
}
