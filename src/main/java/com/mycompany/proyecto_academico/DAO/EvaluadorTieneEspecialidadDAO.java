package com.mycompany.proyecto_academico.DAO;

import com.mycompany.proyecto_academico.modelo.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import javax.persistence.NoResultException;

public class EvaluadorTieneEspecialidadDAO {

    private EntityManagerFactory emf;

    public EvaluadorTieneEspecialidadDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void agregarRelacion(Evaluador evaluador, Especialidad especialidad) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
         // Reasociar las entidades al contexto actual
        Evaluador evaluadorGestionado = em.find(Evaluador.class, evaluador.getId_evaluador());
        Especialidad especialidadGestionada = em.find(Especialidad.class, especialidad.getId_especialidad());

        
        ClaveCompuesta clave = new ClaveCompuesta(evaluadorGestionado.getId_evaluador(), especialidadGestionada.getId_especialidad());
        Evaluador_tiene_especialidad relacion = new Evaluador_tiene_especialidad(clave, evaluadorGestionado, especialidadGestionada);

        em.persist(relacion);

        em.getTransaction().commit();
        em.close();
    }
    
    public void eliminarRelacion(int idEvaluador, int idEspecialidad) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        ClaveCompuesta clave = new ClaveCompuesta(idEvaluador, idEspecialidad);
        Evaluador_tiene_especialidad relacion = em.find(Evaluador_tiene_especialidad.class, clave);

        if (relacion != null) {
            em.remove(relacion);
        }

        em.getTransaction().commit();
        em.close();
    }

    public List<Evaluador_tiene_especialidad> listarEspecialidadesDeEvaluador(int idEvaluador) {
        EntityManager em = emf.createEntityManager();

        List<Evaluador_tiene_especialidad> lista = em.createQuery(
            "SELECT ete FROM Evaluador_tiene_especialidad ete WHERE ete.evaluador.id_evaluador = :id", 
            Evaluador_tiene_especialidad.class
        ).setParameter("id", idEvaluador).getResultList();

        em.close();
        return lista;
    }
    
    public void eliminarPorEvaluador(int idEvaluador) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Evaluador_tiene_especialidad e WHERE e.evaluador.id_evaluador = :id")
              .setParameter("id", idEvaluador)
              .executeUpdate();
            em.getTransaction().commit();
        } finally {
            if (em.isOpen()) em.close();
        }
    }
    
    public Evaluador_tiene_especialidad buscarEspecialidadEvaluador(int idEvaluador) {
        EntityManager em = emf.createEntityManager();
        Evaluador_tiene_especialidad resultado = null;

        try {
            resultado = em.createQuery(
                "SELECT e FROM com.mycompany.proyecto_academico.modelo.Evaluador_tiene_especialidad e WHERE e.id.id_evaluador = :idEvaluador", 
                Evaluador_tiene_especialidad.class)
                .setParameter("idEvaluador", idEvaluador)
                .setMaxResults(1) // por si hay más de una especialidad
                .getSingleResult();
        } catch (NoResultException ex) {
            // No se encontró ninguna especialidad asociada al evaluador
            resultado = null;
        } finally {
            em.close();
        }

        return resultado;
    }
    
    public Especialidad buscarEspecialidad(int idEspecialidad) {
        EntityManager em = emf.createEntityManager();
        Especialidad e = em.find(Especialidad.class, idEspecialidad);
        em.close();
        return e;
    }

}

