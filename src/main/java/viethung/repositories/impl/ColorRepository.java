package viethung.repositories.impl;


import viethung.models.Color;

import java.util.List;

public interface ColorRepository {
    Color insert(Color color);
    Color update(Color color, String colorId);
    Color delete(String colorId);
    List<Color> getAll();
    Color getById(String colorId);
    Color getByCode(String colorCode);
}
