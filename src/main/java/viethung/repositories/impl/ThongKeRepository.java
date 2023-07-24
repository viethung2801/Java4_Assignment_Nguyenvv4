package viethung.repositories.impl;

import java.util.Date;
import java.util.List;

public interface ThongKeRepository {
    List<Object[]> getDoanhThuByDate(String fromDate,String toDate);
}
