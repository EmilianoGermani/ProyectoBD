/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.proyecto_academico.vistaEvaluador.vistaGeneral;

import ReportePDFJava.ReportePDF;
import com.mycompany.proyecto_academico.DAO.EvaluadorDAO;
import com.mycompany.proyecto_academico.DAO.EvaluadorTieneEspecialidadDAO;
import com.mycompany.proyecto_academico.modelo.Evaluador;
import com.mycompany.proyecto_academico.modelo.Evaluador_tiene_especialidad;
import com.mycompany.proyecto_academico.vistaEvaluador.EvaluadorBaja;
import com.mycompany.proyecto_academico.vistaEvaluador.EvaluadorForm;
import com.mycompany.proyecto_academico.vistaEvaluador.EvaluadorInfo;
import com.mycompany.proyecto_academico.vistaEvaluador.EvaluadorModificacion;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author emi_g
 */
public class EvaluadorListadoForm extends javax.swing.JFrame {

    /**
     * Creates new form EvaluadorListadoForm
     */
    private EntityManagerFactory emf;
    private EvaluadorDAO evaluadorDAO;
    private EvaluadorTieneEspecialidadDAO relacionDAO;
    public EvaluadorListadoForm() {
        initComponents();
        emf = Persistence.createEntityManagerFactory("Persistencia");
        evaluadorDAO = new EvaluadorDAO();
        relacionDAO = new EvaluadorTieneEspecialidadDAO(emf);
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setRowCount(0); // Borra todas las filas

        List<Evaluador> listaEvaluadores = evaluadorDAO.listarTodos();

        for (Evaluador eval : listaEvaluadores) {
            int id = eval.getId_evaluador();
            String nombre = eval.getNombre();
            String apellido = eval.getApellido();

            // Buscar especialidad
            Evaluador_tiene_especialidad ete = relacionDAO.buscarEspecialidadEvaluador(id);
            String nombreEspecialidad = ete.getEspecialidad().getNombre();

            // Agregar fila a la tabla
            modelo.addRow(new Object[]{id, nombre, apellido, nombreEspecialidad});
        }
    }

    public void exportarDumpBaseDatos(String backupPath) {
        String pgDumpPath = "\"C:\\Program Files\\PostgreSQL\\17\\bin\\pg_dump.exe\""; // Ruta completa a pg_dump
        String host = "localhost";
        String port = "5432";
        String user = "postgres"; // Reemplazá si tu usuario es otro
        String dbName = "proyecto_academico"; // Reemplazá con el nombre de tu base

        String command = pgDumpPath + " -h " + host + " -p " + port + " -U " + user + " -F p -f \"" + backupPath + "\" " + dbName;

        try {
            // Variable de entorno para la contraseña
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", command);
            pb.environment().put("PGPASSWORD", "Wakser77"); // Reemplazá con tu contraseña real

            // Ejecutar el proceso
            Process process = pb.start();

            // Capturar errores
            InputStream errorStream = process.getErrorStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(errorStream));
            String line;
            while ((line = reader.readLine()) != null) {
                System.err.println(line); // Podés mostrarlo en un JTextArea si querés
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                JOptionPane.showMessageDialog(null, "Base de datos exportada exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Error al exportar la base de datos. Código: " + exitCode, "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al ejecutar el comando: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void importarBD() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar archivo de respaldo");
        int seleccion = fileChooser.showOpenDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            String ruta = archivo.getAbsolutePath();

            try {
                String comando = "pg_restore -U postgres -d proyecto_academico -f \"" + ruta + "\"";
                ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", comando);
                pb.environment().put("PGPASSWORD", "Wakser77");

                Process p = pb.start();
                p.waitFor();

                JOptionPane.showMessageDialog(null, "Base de datos importada correctamente.");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al importar la base de datos: " + e.getMessage());
            }
        }
    }


    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        BotonActualizar = new javax.swing.JButton();
        BotonCerrar = new javax.swing.JButton();
        BotonAgregar = new javax.swing.JButton();
        BotonModificacion = new javax.swing.JButton();
        BotonEliminar = new javax.swing.JButton();
        BotonBuscar = new javax.swing.JButton();
        BotonExportarPDF = new javax.swing.JButton();
        BotonExportarBD = new javax.swing.JButton();
        BotonImportarBD = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Apellido", "Especialidad"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        BotonActualizar.setText("Actualizar");
        BotonActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotonActualizarMouseClicked(evt);
            }
        });
        BotonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonActualizarActionPerformed(evt);
            }
        });

        BotonCerrar.setText("Cerrar");
        BotonCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotonCerrarMouseClicked(evt);
            }
        });
        BotonCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonCerrarActionPerformed(evt);
            }
        });

        BotonAgregar.setText("Agregar");
        BotonAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotonAgregarMouseClicked(evt);
            }
        });

        BotonModificacion.setText("Modificar");
        BotonModificacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotonModificacionMouseClicked(evt);
            }
        });

        BotonEliminar.setText("Eliminar");
        BotonEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotonEliminarMouseClicked(evt);
            }
        });

        BotonBuscar.setText("Buscar");
        BotonBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotonBuscarMouseClicked(evt);
            }
        });

        BotonExportarPDF.setText("Reporte PDF");
        BotonExportarPDF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotonExportarPDFMouseClicked(evt);
            }
        });

        BotonExportarBD.setText("Exportar BD");
        BotonExportarBD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotonExportarBDMouseClicked(evt);
            }
        });
        BotonExportarBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonExportarBDActionPerformed(evt);
            }
        });

        BotonImportarBD.setText("Importar BD");
        BotonImportarBD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotonImportarBDMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(BotonAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94)
                        .addComponent(BotonModificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
                        .addComponent(BotonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77)
                        .addComponent(BotonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGap(313, 313, 313)
                .addComponent(BotonCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BotonActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(BotonExportarPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addComponent(BotonExportarBD, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BotonImportarBD, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotonActualizar)
                    .addComponent(BotonExportarPDF)
                    .addComponent(BotonExportarBD)
                    .addComponent(BotonImportarBD))
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotonBuscar)
                    .addComponent(BotonEliminar)
                    .addComponent(BotonModificacion)
                    .addComponent(BotonAgregar))
                .addGap(18, 18, 18)
                .addComponent(BotonCerrar)
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonActualizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotonActualizarActionPerformed

    private void BotonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonCerrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotonCerrarActionPerformed

    private void BotonActualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonActualizarMouseClicked
        // TODO add your handling code here:
        // Limpiar tabla antes de actualizar
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setRowCount(0); // Borra todas las filas

        List<Evaluador> listaEvaluadores = evaluadorDAO.listarTodos();

        for (Evaluador eval : listaEvaluadores) {
            int id = eval.getId_evaluador();
            String nombre = eval.getNombre();
            String apellido = eval.getApellido();

            // Buscar especialidad
            Evaluador_tiene_especialidad ete = relacionDAO.buscarEspecialidadEvaluador(id);
            String nombreEspecialidad = ete.getEspecialidad().getNombre();

            // Agregar fila a la tabla
            modelo.addRow(new Object[]{id, nombre, apellido, nombreEspecialidad});
        }
    }//GEN-LAST:event_BotonActualizarMouseClicked

    private void BotonCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonCerrarMouseClicked
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_BotonCerrarMouseClicked

    private void BotonAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonAgregarMouseClicked
        // TODO add your handling code here:
        EvaluadorForm altaForm = new EvaluadorForm();
        altaForm.setVisible(true);
    }//GEN-LAST:event_BotonAgregarMouseClicked

    private void BotonModificacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonModificacionMouseClicked
        // TODO add your handling code here:
        EvaluadorModificacion modificacionForm = new EvaluadorModificacion();
        modificacionForm.setVisible(true);
    }//GEN-LAST:event_BotonModificacionMouseClicked

    private void BotonEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonEliminarMouseClicked
        // TODO add your handling code here:
        EvaluadorBaja eliminarForm = new EvaluadorBaja();
        eliminarForm.setVisible(true);
    }//GEN-LAST:event_BotonEliminarMouseClicked

    private void BotonBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonBuscarMouseClicked
        // TODO add your handling code here:
        EvaluadorInfo buscarForm = new EvaluadorInfo();
        buscarForm.setVisible(true);
    }//GEN-LAST:event_BotonBuscarMouseClicked

    private void BotonExportarPDFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonExportarPDFMouseClicked
        // TODO add your handling code here:
        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("Guardar reporte como...");

        int seleccion = selector.showSaveDialog(this);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            String ruta = selector.getSelectedFile().getAbsolutePath();
            if (!ruta.endsWith(".pdf")) {
                ruta += ".pdf";
            }

            ReportePDF reporte = new ReportePDF();
            List<Evaluador> evaluadores = evaluadorDAO.listarTodos();
            reporte.generarReporteEvaluadores(evaluadores, ruta);

            JOptionPane.showMessageDialog(this, "Reporte exportado exitosamente a:\n" + ruta);
        }
    }//GEN-LAST:event_BotonExportarPDFMouseClicked

    private void BotonExportarBDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonExportarBDMouseClicked
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar ubicación del backup");
        fileChooser.setSelectedFile(new File("backup.sql")); // nombre por defecto

        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            exportarDumpBaseDatos(fileToSave.getAbsolutePath());
        }
        
    }//GEN-LAST:event_BotonExportarBDMouseClicked

    private void BotonExportarBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonExportarBDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotonExportarBDActionPerformed

    private void BotonImportarBDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonImportarBDMouseClicked
        // TODO add your handling code here:
        importarBD();
    }//GEN-LAST:event_BotonImportarBDMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EvaluadorListadoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EvaluadorListadoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EvaluadorListadoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EvaluadorListadoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EvaluadorListadoForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonActualizar;
    private javax.swing.JButton BotonAgregar;
    private javax.swing.JButton BotonBuscar;
    private javax.swing.JButton BotonCerrar;
    private javax.swing.JButton BotonEliminar;
    private javax.swing.JButton BotonExportarBD;
    private javax.swing.JButton BotonExportarPDF;
    private javax.swing.JButton BotonImportarBD;
    private javax.swing.JButton BotonModificacion;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
