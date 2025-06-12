/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ReportePDFJava;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.mycompany.proyecto_academico.DAO.EvaluadorTieneEspecialidadDAO;
import com.mycompany.proyecto_academico.modelo.Evaluador;
import com.mycompany.proyecto_academico.modelo.Evaluador_tiene_especialidad;
import java.io.FileOutputStream;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ReportePDF {
    private EvaluadorTieneEspecialidadDAO relacionDAO;
    private EntityManagerFactory emf;
    public void generarReporteEvaluadores(List<Evaluador> evaluadores, String rutaDestino) {
        Document documento = new Document();
        emf = Persistence.createEntityManagerFactory("Persistencia"); // Usa el nombre exacto del persistence.xml
        relacionDAO = new EvaluadorTieneEspecialidadDAO(emf);

        try {
            PdfWriter.getInstance(documento, new FileOutputStream(rutaDestino));
            documento.open();

            documento.add(new Paragraph("Reporte de Evaluadores"));
            documento.add(new Paragraph(" ")); // espacio

            PdfPTable tabla = new PdfPTable(6); // columnas
            tabla.setWidthPercentage(100); // usa el 100% del ancho disponible
            // Ajuste de anchos personalizados
            float[] anchos = {1f, 2.5f, 2.5f, 3.5f, 2.5f, 4f};
            tabla.setWidths(anchos);

            tabla.addCell("ID");
            tabla.addCell("Nombre");
            tabla.addCell("Apellido");
            tabla.addCell("Email");
            tabla.addCell("Teléfono");
            tabla.addCell("Especialidad");

            for (Evaluador e : evaluadores) {
                tabla.addCell(String.valueOf(e.getId_evaluador()));
                tabla.addCell(e.getNombre());
                tabla.addCell(e.getApellido());
                tabla.addCell(e.getEmail());
                tabla.addCell(e.getTelefono());
                Evaluador_tiene_especialidad relacion = relacionDAO.buscarEspecialidadEvaluador(e.getId_evaluador());
                String esp = relacion.getEspecialidad().getNombre();
                tabla.addCell(esp);
            }

            documento.add(tabla);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            documento.close();
        }
    }
}

