import entidades.Carrera;
import jakarta.persistence.EntityManager;
import utilidades.JpaUtil;

import java.util.Scanner;

public class HibernateCrear {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitamos los datos de la nueva carrera al usuario
        System.out.println("Ingrese el nombre de la nueva carrera:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese el tipo de carrera (ingrese un número):");
        int tipo = Integer.parseInt(scanner.nextLine());

        System.out.println("Ingrese el ID de la facultad asociada:");
        int idFacultad = Integer.parseInt(scanner.nextLine());

        // Creamos una nueva instancia de Carrera
        Carrera nuevaCarrera = new Carrera();
        nuevaCarrera.setNombre(nombre);
        nuevaCarrera.setTipo(tipo);
        nuevaCarrera.setIdFacultad(idFacultad);

        // Obtenemos el EntityManager
        EntityManager em = JpaUtil.getEntityManager();
        try {
            // Iniciamos una transacción
            em.getTransaction().begin();
            // Persistimos la entidad Carrera
            em.persist(nuevaCarrera);
            // Confirmamos la transacción
            em.getTransaction().commit();
            System.out.println("Carrera creada exitosamente!");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Error al crear la carrera: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}