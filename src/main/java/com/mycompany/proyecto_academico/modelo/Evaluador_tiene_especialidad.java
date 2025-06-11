/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_academico.modelo;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 *
 * @author emi_g
 */
@Entity
@Table(name="evaluador_tiene_especialidad")
public class Evaluador_tiene_especialidad {
    
    @EmbeddedId
    private ClaveCompuesta id;
    
    @ManyToOne()
    @MapsId("id_evaluador")
    @JoinColumn(name="id_evaluador")
    private Evaluador evaluador;
    
    @ManyToOne()
    @MapsId("id_especialidad")
    @JoinColumn(name="id_especialidad")
    private Especialidad especialidad;

    public Evaluador_tiene_especialidad() {
    }

    public Evaluador_tiene_especialidad(ClaveCompuesta id, Evaluador evaluador, Especialidad especialidad) {
        this.id = id;
        this.evaluador = evaluador;
        this.especialidad = especialidad;
    }

    public ClaveCompuesta getId() {
        return id;
    }

    public void setId(ClaveCompuesta id) {
        this.id = id;
    }

    public Evaluador getEvaluador() {
        return evaluador;
    }

    public void setEvaluador(Evaluador evaluador) {
        this.evaluador = evaluador;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return "Evaluador_tiene_especialidad{" + "id=" + id + ", evaluador=" + evaluador + ", especialidad=" + especialidad + '}';
    }
    
    
}
