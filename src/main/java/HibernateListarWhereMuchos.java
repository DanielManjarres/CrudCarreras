import entidades.Carrera;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import utilidades.JpaUtil;

import java.util.List;
import java.util.Scanner;

public class HibernateListarWhereMuchos {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        // Obtenemos el EntityManager
        EntityManager em = JpaUtil.getEntityManager();

        // Preparamos la consulta para la tabla Carrera, filtrando por idfacultad
        Query consulta = em.createQuery("SELECT c FROM Carrera c WHERE c.idCarrera = ?1", Carrera.class);

        // Solicitamos el código de facultad al usuario
        System.out.print("Ingrese un código de carrera: ");
        int idcarrera = s.nextInt();

        // Asignamos el parámetro de la consulta
        consulta.setParameter(1, idcarrera);

        // Ejecutamos la consulta y obtenemos el resultado
        List<Carrera> carreras = consulta.getResultList();

        // Mostramos el resultado
        if (carreras.isEmpty()) {
            System.out.println("No se encontraron carreras para la facultad con ID: " + idcarrera);
        } else {
            carreras.forEach(System.out::println);
        }

        // Cerramos el EntityManager
        em.close();
    }
}