package com.mycompany.proyecto_academico.DAO;

import com.mycompany.proyecto_academico.modelo.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class EvaluadorTieneEspecialidadDAO {

    private EntityManagerFactory emf;

    public EvaluadorTieneEspecialidadDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void agregarRelacion(Evaluador evaluador, Especialidad especialidad) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        ClaveCompuesta clave = new ClaveCompuesta(evaluador.getId_evaluador(), especialidad.getId_especialidad());
        Evaluador_tiene_especialidad relacion = new Evaluador_tiene_especialidad(clave, evaluador, especialidad);

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

}

