/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.proyecto_academico;

import com.mycompany.proyecto_academico.DAO.EvaluadorDAO;
import com.mycompany.proyecto_academico.modelo.ClaveCompuesta;
import com.mycompany.proyecto_academico.modelo.Especialidad;
import com.mycompany.proyecto_academico.modelo.Evaluador;
import com.mycompany.proyecto_academico.modelo.Evaluador_tiene_especialidad;
import com.mycompany.proyecto_academico.vistaEvaluador.vistaGeneral.EvaluadorListadoForm;
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
        javax.swing.SwingUtilities.invokeLater(() -> {
            EvaluadorListadoForm ventana = new EvaluadorListadoForm();
            ventana.setVisible(true);
        });
    }
}
