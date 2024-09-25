import entidades.Carrera;
import jakarta.persistence.EntityManager;
import utilidades.JpaUtil;

import javax.swing.*;

public class HibernateEditar {
    public static void main(String[] args) {

        EntityManager em= JpaUtil.getEntityManager();

        try {
            // Solicitar un Id
            Long id=Long.valueOf((JOptionPane.showInputDialog("Ingrese un código de carrera a modificar: ")));
            // Se busca en la base de datos
            Carrera ca =em.find(Carrera.class,id);

            // Se solicitan los nuevos datos
            String nombre=JOptionPane.showInputDialog("Ingrese nuevo nombre de facultad: ",ca.getNombre());
            String tipo=JOptionPane.showInputDialog("Ingrese tipo de carrera: ",ca.getTipo());
            String idfacultad=JOptionPane.showInputDialog("Ingrese id facultad: ",ca.getIdFacultad());
            // Se inicia transacción
            em .getTransaction().begin();
            //Pasamos los nuevos valores
            ca.setNombre(nombre);
            ca.setTipo(Integer.parseInt(tipo));
            ca.setIdFacultad(Integer.parseInt(idfacultad));
            // TODO

            // Hasta el momento los datos han sido cambiados en las entidades / objetos pero no se han sincronizado con la BD
            em.getTransaction().commit(); //Se sincronizan los datos a la BD
            // Se publica el registro modificado
            System.out.println(ca);

        }catch (Exception e){
            em.getTransaction().rollback();
            System.out.println("Se ha generado el error: " + e.getMessage());

        }finally {
            em.close();
        }
    }
}