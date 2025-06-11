package com.mycompany.proyecto_academico.DAO;

import com.mycompany.proyecto_academico.modelo.Especialidad;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class EspecialidadDAO {

    private EntityManagerFactory emf;

    public EspecialidadDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    // Obtener todas las especialidades
    public List<Especialidad> obtenerTodas() {
        EntityManager em = emf.createEntityManager();
        List<Especialidad> lista = em.createQuery("SELECT e FROM Especialidad e", Especialidad.class)
                                     .getResultList();
        em.close();
        return lista;
    }

    // Guardar una especialidad
    public void agregar(Especialidad esp) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(esp);
        em.getTransaction().commit();
        em.close();
    }

    // (Opcional) Buscar por id
    public Especialidad buscar(int id) {
        EntityManager em = emf.createEntityManager();
        Especialidad e = em.find(Especialidad.class, id);
        em.close();
        return e;
    }
}