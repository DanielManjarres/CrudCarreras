import entidades.Carrera;
import jakarta.persistence.EntityManager;
import utilidades.JpaUtil;

import java.util.List;

public class HibernateList {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        List<Carrera> carreras = em.createQuery("from Carrera ", Carrera.class).getResultList();
        carreras.forEach(System.out::println);
    }
}
