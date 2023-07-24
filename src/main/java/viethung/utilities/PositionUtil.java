package viethung.utilities;

import viethung.models.Position;
import viethung.models.Producer;
import viethung.repositories.PositionRepositoryImpl;
import viethung.repositories.ProducerRepositoryImpl;
import viethung.repositories.impl.PositionRepository;
import viethung.repositories.impl.ProducerRepository;

public class PositionUtil {
    public static String validateInsert(Position position) {
        PositionRepository positionRepo= new PositionRepositoryImpl();

        if (position.getCode() == null || position.getCode().trim().equals("")) {
            return "Fail! Code is empty";
        }
        if (position.getCode().length() > 20) {
            return "Fail! Code more than 20 characters";
        }
        if (position.getName() == null || position.getName().trim().equals("")) {
            return "Fail! Name is empty";
        }
        if (position.getName().length() > 30) {
            return "Fail! Name more than 30 characters";
        }
        if (positionRepo.getByCode(position.getCode()) != null) {
            return "Fail! Code is exist";
        }
        return null;
    }

    public static String validateUpdate(Position position) {
        if ( position.getCode() == null || position.getCode().trim().equals("")) {
            return "Fail! Code is empty";
        }
        if (position.getCode().length() > 20) {
            return "Fail! Code more than 20 characters";
        }
        if (position.getCode() == null || position.getCode().trim().equals("")) {
            return "Fail! Name is empty";
        }
        if (position.getName().length() > 30) {
            return "Fail! Name more than 30 characters";
        }
        return null;
    }
}
