/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.proyecto_academico;

import com.mycompany.proyecto_academico.DAO.EvaluadorDAO;
import com.mycompany.proyecto_academico.modelo.ClaveCompuesta;
import com.mycompany.proyecto_academico.modelo.Especialidad;
import com.mycompany.proyecto_academico.modelo.Evaluador;
import com.mycompany.proyecto_academico.modelo.Evaluador_tiene_especialidad;
import java.lang.module.Configuration;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author emi_g
 */
public class Proyecto_academico {

    public static void main(String[] args) {
        /*EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");
        EntityManager man = emf.createEntityManager();
        
        Especialidad especialidad = new Especialidad(2, "Bases de Datos", "Diseño y gestión de bases de datos.");
        Evaluador evaluador = new Evaluador("Lucía", "Martínez", "lucia.martinez@uni.edu", "1122334455");
        ClaveCompuesta compuesta = new ClaveCompuesta(1, 2);
        Evaluador_tiene_especialidad evaluador_especialidad = new Evaluador_tiene_especialidad(compuesta, evaluador, especialidad);
        
        
        man.getTransaction().begin();
        man.merge(evaluador_especialidad);
        man.getTransaction().commit();
        man.close();*/
        Evaluador prueba = new Evaluador("pepe1", "pepe1", "pepe1", "pepe1");
        Evaluador prueba2 = new Evaluador("pepe2","pepe2","pepe2","pepe2");
        Evaluador prueba3 = new Evaluador("pepe3","pepe3","pepe3","pepe3");
        System.out.println(prueba.getId_evaluador());
        System.out.println(prueba2.getId_evaluador());
        System.out.println(prueba3.getId_evaluador());
        /*EvaluadorDAO pruebaDAO = new EvaluadorDAO();
        pruebaDAO.agregar(prueba);
        pruebaDAO.agregar(prueba2);
        pruebaDAO.agregar(prueba3);*/
    }
}
