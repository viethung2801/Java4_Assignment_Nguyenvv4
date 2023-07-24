package viethung.repositories.impl;


import viethung.models.Position;

import java.util.List;

public interface PositionRepository {
    Position insert(Position position);
    Position update(Position position, String positionId);
    Position delete(String positionId);
    List<Position> getAll();
    Position getById(String positionId);
    Position getByCode(String positionCode);
}
