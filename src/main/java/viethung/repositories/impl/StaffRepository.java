package viethung.repositories.impl;


import viethung.models.Staff;

import java.util.List;

public interface StaffRepository {
    Staff insert(Staff staff);
    Staff update(Staff staff,String staffId);
    Staff delete(String staffId);
    Staff getById(String staffId);
    List<Staff> getAll();
    Staff getByCode(String staffCode);
}
