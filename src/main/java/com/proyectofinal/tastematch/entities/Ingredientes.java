package com.proyectofinal.tastematch.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ingredientes")
public class Ingredientes {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ingrediente")
    private Long idIngrediente;
    
    @ManyToOne
    @JoinColumn(name = "id_receta", nullable = false)
    private Recetas receta;
    
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    
    @Column(name = "apartado", nullable = false, length = 50)
    private String apartado;
    
    @Column(name = "cantidad", nullable = false, precision = 10, scale = 2)
    private BigDecimal cantidad;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "medida")
    private Medida medida;
    
    // Enum para medidas
    public enum Medida {
        MILILITROS("mililitro/s"),
        LITROS("litro/s"),
        GRAMOS("gramo/s"),
        KILOS("kilo/s"),
        CUCHARADAS("cucharada/s"),
        CUCHARADITAS("cucharadita/s"),
        TAZAS("taza/s"),
        UNIDADES("unidad/es"),
        PIZCAS("pizca/s"),
        PUÑADOS("puñado/s");
        
        private final String descripcion;
        
        Medida(String descripcion) {
            this.descripcion = descripcion;
        }
        
        public String getDescripcion() {
            return descripcion;
        }
    }
    
    // Constructores
    public Ingredientes() {
    }
    
    public Ingredientes(Recetas receta, String nombre, String apartado,
                        BigDecimal cantidad, Medida medida) {
        this.receta = receta;
        this.nombre = nombre;
        this.apartado = apartado;
        this.cantidad = cantidad;
        this.medida = medida;
    }
    
    // Getters y Setters
    public Long getIdIngrediente() {
        return idIngrediente;
    }
    
    public void setIdIngrediente(Long idIngrediente) {
        this.idIngrediente = idIngrediente;
    }
    
    public Recetas getReceta() {
        return receta;
    }
    
    public void setReceta(Recetas receta) {
        this.receta = receta;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getApartado() {
        return apartado;
    }
    
    public void setApartado(String apartado) {
        this.apartado = apartado;
    }
    
    public BigDecimal getCantidad() {
        return cantidad;
    }
    
    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }
    
    public Medida getMedida() {
        return medida;
    }
    
    public void setMedida(Medida medida) {
        this.medida = medida;
    }
    
    @Override
    public String toString() {
        return "Ingrediente{" +
                "idIngrediente=" + idIngrediente +
                ", nombre='" + nombre + '\'' +
                ", apartado='" + apartado + '\'' +
                ", cantidad=" + cantidad +
                ", medida=" + medida +
                '}';
    }
}