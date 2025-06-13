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
import com.mycompany.proyecto_academico.vistaEvaluador.vistaGeneral.LoginFrame;
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
        LoginFrame login = new LoginFrame();
        login.setVisible(true);

        // Esperamos que el login se cierre antes de continuar
        while (login.isDisplayable()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (login.isLoginExitoso()) {
            // Aquí abris tu ventana principal porque el login fue correcto
            EvaluadorListadoForm listado = new EvaluadorListadoForm();
            listado.setVisible(true);
        } else {
            // Opcional: salir o mostrar mensaje
            System.out.println("No se pudo iniciar sesión.");
            System.exit(0);
        }
    }
}
