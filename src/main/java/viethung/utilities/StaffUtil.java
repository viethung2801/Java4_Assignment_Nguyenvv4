package viethung.utilities;


import viethung.models.Staff;
import viethung.repositories.StaffRepositoryImpl;
import viethung.repositories.impl.StaffRepository;

public class StaffUtil {
    public static String validateInsert(Staff staff) {
        StaffRepository staffRepo = new StaffRepositoryImpl();
        if ( staff.getCode() == null || staff.getCode().trim().equals("")) {
            return "Fail! Code is empty";
        }
        if ( staff.getFirstName() == null || staff.getFirstName().trim().equals("")) {
            return "Fail! First Name is empty";
        }
        if ( staff.getGender() == null || staff.getGender().trim().equals("")) {
            return "Fail! Choose gender";
        }
        if ( staff.getPassword() == null || staff.getPassword().trim().equals("")) {
            return "Fail! Password is empty";
        }
        if (staffRepo.getByCode(staff.getCode()) != null) {
            return "Fail! Code is exist";
        }
        return null;
    }

    public static String validateUpdate(Staff staff) {
        if ( staff.getCode() == null || staff.getCode().trim().equals("")) {
            return "Fail! Code is empty";
        }
        if ( staff.getFirstName() == null || staff.getFirstName().trim().equals("")) {
            return "Fail! First Name is empty";
        }
        if ( staff.getGender() == null || staff.getGender().trim().equals("")) {
            return "Fail! Choose gender";
        }
        if ( staff.getPassword() == null || staff.getPassword().trim().equals("")) {
            return "Fail! Password is empty";
        }
        return null;
    }
}
