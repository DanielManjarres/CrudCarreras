
import entidades.Carrera;
import jakarta.persistence.EntityManager;
import utilidades.JpaUtil;
import java.util.Scanner;
import jakarta.persistence.Query;

public class HibernateListarWhere {
    public static void main(String[] args) {

// Se usa Scanner para solicitar un valor por teclado al usuario
        Scanner s =new Scanner(System.in);

// Importamos el em de Entitymanager
        EntityManager em= JpaUtil.getEntityManager();

// Se crea la consulta, al final se le aclara que se va a tener un objeto de tipo facultad, antes no se declaro
// Consulta preparada pero con JPA; ?=1 significa que es el parámetro 1 e inicia en 1
        //TODO
        Query consulta= em.createQuery("SELECT c from Carrera c where c.id=?1", Carrera.class);
        System.out.print("Ingrese un código de carreras: ");
        String id = s.next();
// Observar que el Id es un Long, pero la variable id es de tipo string
        consulta.setParameter(1,id);
        // El query retorna un objeto, asi que se debe convertir
        //TODO
        Carrera ca = (Carrera)consulta.getSingleResult();
        // Se imprime el resultado
        System.out.println(ca);
        System.out.println(ca.getNombre());
        // Se cierra el entityManager
        em.close();
// AL USAR getSingleResult ESTE SOLO RETORNA UN VALOR, intente cambiar en la consulta el = por >


    }
}
