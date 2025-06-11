/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_academico.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author emi_g
 */
@Embeddable
public class ClaveCompuesta implements Serializable{
    @Column(name = "id_evaluador")
    private int id_evaluador;
    
    @Column(name = "id_especialidad")
    private int id_especialidad;

    public ClaveCompuesta() {
    }

    public ClaveCompuesta(int id_evaluador, int id_especialidad) {
        this.id_evaluador = id_evaluador;
        this.id_especialidad = id_especialidad;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.id_evaluador;
        hash = 79 * hash + this.id_especialidad;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ClaveCompuesta other = (ClaveCompuesta) obj;
        if (this.id_evaluador != other.id_evaluador) {
            return false;
        }
        return this.id_especialidad == other.id_especialidad;
    }
    
    
}
