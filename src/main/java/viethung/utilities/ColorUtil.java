package viethung.utilities;

import viethung.models.Color;
import viethung.repositories.ColorRepositoryImpl;
import viethung.repositories.impl.ColorRepository;

public class ColorUtil {

    public static String validateInsert(Color color) {
        ColorRepository colorRepo = new ColorRepositoryImpl();

        if (color.getCode().trim().equals("") || color.getCode() == null) {
            return "Fail! Code is empty";
        }
        if (color.getCode().length() > 20) {
            return "Fail! Code more than 20 characters";
        }
        if (color.getName().trim().equals("") || color.getName() == null) {
            return "Fail! Name is empty";
        }
        if (color.getName().length() > 30) {
            return "Fail! Name more than 30 characters";
        }
        if (colorRepo.getByCode(color.getCode()) != null) {
            return "Fail! Code is exist";
        }
        return null;
    }

    public static String validateUpdate(Color color) {
        if (color.getCode().trim().equals("") || color.getCode() == null) {
            return "Fail! Code is empty";
        }
        if (color.getCode().length() > 20) {
            return "Fail! Code more than 20 characters";
        }
        if (color.getCode().trim().equals("") || color.getCode() == null) {
            return "Fail! Name is empty";
        }
        if (color.getName().length() > 30) {
            return "Fail! Name more than 30 characters";
        }
        return null;
    }
}
