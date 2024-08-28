package org.esfe.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cancha")
public class Cancha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre es requerido")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "El tipo es requerido")
    @Column(nullable = false)
    private String tipo;

    @NotBlank(message = "La ubicación es requerida")
    @Column(nullable = false)
    private String ubicacion;

    @NotNull(message = "La tarifa por hora es requerida")
    @Column(nullable = false)
    private Double tarifaPorHora;

    private String descripcion;

    private String imagen1;
    private String imagen2;
    private String imagen3;

    @NotNull(message = "El estado es requerido")
    @Column(nullable = false)
    private Boolean estado;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Double getTarifaPorHora() {
        return tarifaPorHora;
    }

    public void setTarifaPorHora(Double tarifaPorHora) {
        this.tarifaPorHora = tarifaPorHora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen1() {
        return imagen1;
    }

    public void setImagen1(String imagen1) {
        this.imagen1 = imagen1;
    }

    public String getImagen2() {
        return imagen2;
    }

    public void setImagen2(String imagen2) {
        this.imagen2 = imagen2;
    }

    public String getImagen3() {
        return imagen3;
    }

    public void setImagen3(String imagen3) {
        this.imagen3 = imagen3;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Cancha{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", tarifaPorHora=" + tarifaPorHora +
                ", descripcion='" + descripcion + '\'' +
                ", imagen1='" + imagen1 + '\'' +
                ", imagen2='" + imagen2 + '\'' +
                ", imagen3='" + imagen3 + '\'' +
                ", estado=" + estado +
                '}';
    }
}