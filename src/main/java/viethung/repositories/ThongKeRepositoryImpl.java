package viethung.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import viethung.repositories.impl.ThongKeRepository;
import viethung.utilities.JpaUtil;

import java.util.List;

public class ThongKeRepositoryImpl implements ThongKeRepository {
    @Override
    public List<Object[]> getDoanhThuByDate(String fromDate, String toDate) {
        EntityManager em = JpaUtil.getConnection();
        List<Object[]> doanhThus = null;
        try {
            String sql = "Select hd.NgayThanhToan,SUM(hdct.SoLuong*hdct.DonGia) from HoaDon hd\n" +
                    "inner join HoaDonChiTiet hdct on hd.id = hdct.IdHoaDon\n" +
                    "where hd.TinhTrang = 1 and (hd.NgayThanhToan between :fromDate and :toDate)\n" +
                    "group by hd.NgayThanhToan";
            Query query = em.createNativeQuery(sql);
            query.setParameter("fromDate",fromDate);
            query.setParameter("toDate",toDate);
            doanhThus = query.getResultList();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return doanhThus;
    }

//    public static void main(String[] args) {
//        List<Object[]> list= new ThongKeRepositoryImpl().getDoanhThuByDate("2023-07-19","2023-07-21");
//        list.forEach(objects -> System.out.println(objects[0] + " : " +objects[1]));
//    }
}
