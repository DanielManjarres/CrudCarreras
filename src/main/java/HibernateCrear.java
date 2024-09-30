import entidades.Carrera;
import jakarta.persistence.EntityManager;
import utilidades.JpaUtil;

import javax.swing.*;
import java.util.Scanner;

import entidades.Carrera;
import jakarta.persistence.EntityManager;
import utilidades.JpaUtil;

import javax.swing.*;

public class HibernateCrear {

    public static void main(String[] args) {
        Carrera ca = new Carrera();

        // Solicitamos los datos de la nueva carrera al usuario
        String nombre = JOptionPane.showInputDialog(null, "Ingrese nuevo nombre de carrera: ", ca.getNombre());
        String tipo = JOptionPane.showInputDialog(null, "Ingrese tipo de carrera (número): ", ca.getTipo());
        String idfacultad = JOptionPane.showInputDialog(null, "Ingrese ID de facultad (número): ", ca.getIdFacultad());

        // Validamos las entradas
        if (nombre == null || nombre.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre de la carrera no puede estar vacío.");
            return;
        }

        try {
            // Validación de tipo y idfacultad como números enteros
            int tipoInt = Integer.parseInt(tipo);
            int idFacultadInt = Integer.parseInt(idfacultad);

            // Asignamos los valores validados a la nueva instancia de Carrera
            ca.setNombre(nombre);
            ca.setTipo(tipoInt);
            ca.setIdFacultad(idFacultadInt);

            // Obtenemos el EntityManager
            EntityManager em = JpaUtil.getEntityManager();
            try {
                // Iniciamos una transacción
                em.getTransaction().begin();
                // Persistimos la entidad Carrera
                em.persist(ca);
                // Confirmamos la transacción
                em.getTransaction().commit();
                JOptionPane.showMessageDialog(null, "Carrera creada exitosamente!");
            } catch (Exception e) {
                em.getTransaction().rollback();
                JOptionPane.showMessageDialog(null, "Error al crear la carrera: " + e.getMessage());
            } finally {
                em.close();
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El tipo y el ID de facultad deben ser números válidos.");
        }
    }
}
