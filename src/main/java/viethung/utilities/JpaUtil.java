package viethung.utilities;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    public static EntityManagerFactory emf;

    public static EntityManager getConnection() {
        if (emf == null || !emf.isOpen()) {
            emf = Persistence.createEntityManagerFactory("Assignment_Final");
        }
        return emf.createEntityManager();
    }

    public static void destroy() {
        emf.close();
    }

//    public static void main(String[] args) {
//        System.out.println(JpaUtil.getConnection());
//        JpaUtil.destroy();
//    }

}
