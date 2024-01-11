package edu.tucn.scd.serverapp.position;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Radu Miron
 * @version 1
 */
@Service // creates one instance of this class
@Slf4j // adds the 'log(ger)' instance to this class
public class PositionService {

    @Autowired // injects the PositionRepository instance
    private PositionRepository positionRepository;
    @Autowired // injects the PositionMapper instance
    private PositionMapper positionMapper;


    //create
    @Transactional(rollbackFor = Exception.class) // the method is executed in a transaction
    public PositionDTO create(PositionDTO positionDTO) {
        PositionDTO createdPositionDTO =
                positionMapper.positionToDto(positionRepository.save(positionMapper.dtoToPosition(positionDTO)));

        log.info("Saved a new position for terminal with id " + createdPositionDTO.getTerminalId());

        return createdPositionDTO;
    }

    //update
    @Transactional(rollbackFor = Exception.class)
    public PositionDTO update(Integer id, PositionDTO positionDTO) {
        Position position = positionMapper.dtoToPosition(positionDTO);
        Optional<Position> position1 = positionRepository.findById(id);

        if (position1.isPresent()) {
            Position existingPosition = position1.get();
            if (position.getLatitude() != null) {
                existingPosition.setLatitude(position.getLatitude());
            }
            if (position.getLongitude() != null) {
                existingPosition.setLongitude(position.getLongitude());
            }
            if (position.getTerminal() != null) {
                existingPosition.setTerminal(position.getTerminal());
            }

            return positionMapper.positionToDto(positionRepository.save(existingPosition));
        }else {
            throw new IllegalArgumentException("Pozitia nu a fost gasita");

        }
    }


    //delete
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        positionRepository.deleteById(id);
    }



    @Transactional(rollbackFor = Exception.class, readOnly = true) // the method is executed in a read-only transaction
    public List<PositionDTO> findByTerminalIdStartDateEndDate(String terminalId, Date startDate, Date endDate) {
        List<PositionDTO> positions = positionRepository.findPositions(terminalId, startDate, endDate)
                .stream()
                .map(p -> positionMapper.positionToDto(p))
                .collect(Collectors.toList());

        log.info(String.format("Found %d positions for: terminalId=%s, startDate=%s, endDate=%s",
                positions.size(), terminalId, startDate, endDate));

        return positions;
    }
}
