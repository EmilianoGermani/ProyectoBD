/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_academico.DAO;

import com.mycompany.proyecto_academico.modelo.Evaluador;
import javax.persistence.*;
import java.util.List;

public class EvaluadorDAO {
private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");;


    public Evaluador agregar(Evaluador evaluador) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(evaluador); // Hibernate asigna el ID aquí
            em.getTransaction().commit();
            return evaluador; // El evaluador ahora tiene su ID asignado
        } finally {
            em.close();
        }
    }


    public void modificar(Evaluador evaluador) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(evaluador);
        em.getTransaction().commit();
        em.close();
    }

    public void eliminar(int idEvaluador) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Evaluador e = em.find(Evaluador.class, idEvaluador);
        if (e != null) {
            em.remove(e);
        }
        em.getTransaction().commit();
        em.close();
    }

    public Evaluador buscar(int idEvaluador) {
        EntityManager em = emf.createEntityManager();
        Evaluador e = em.find(Evaluador.class, idEvaluador);
        em.close();
        return e;
    }

    public List<Evaluador> listarTodos() {
        EntityManager em = emf.createEntityManager();
        List<Evaluador> lista = em.createQuery("SELECT e FROM Evaluador e", Evaluador.class).getResultList();
        em.close();
        return lista;
    }
}

