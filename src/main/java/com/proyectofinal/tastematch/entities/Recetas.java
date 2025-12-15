package com.proyectofinal.tastematch.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "recetas")
public class Recetas {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_receta")
    private Long idReceta;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuarios usuario;
    
    @Column(name = "titulo", nullable = false, length = 50)
    private String titulo;
    
    @Column(name = "descripcion", nullable = false, columnDefinition = "TEXT")
    private String descripcion;
    
    @Column(name = "comensales", nullable = false)
    private Integer comensales;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "categorias")
    private Categoria categoria;
    
    @Column(name = "tiempo", nullable = false)
    private LocalTime tiempo;
    
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion = LocalDate.now();
    
    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoReceta estado = EstadoReceta.ACTIVE;
    
    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ingredientes> ingredientes;
    
    // Enums
    public enum Categoria {
        LACTOSE, GLUTEN, SUGAR, VEGAN, VEGETARIAN, KETO
    }
    
    public enum EstadoReceta {
        ACTIVE, SUSPENDED
    }
    
    // Constructores
    public Recetas() {
    }
    
    public Recetas(Usuarios usuario, String titulo, String descripcion, Integer comensales,
                   Categoria categoria, LocalTime tiempo) {
        this.usuario = usuario;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.comensales = comensales;
        this.categoria = categoria;
        this.tiempo = tiempo;
    }
    
    // Getters y Setters
    public Long getIdReceta() {
        return idReceta;
    }
    
    public void setIdReceta(Long idReceta) {
        this.idReceta = idReceta;
    }
    
    public Usuarios getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public Integer getComensales() {
        return comensales;
    }
    
    public void setComensales(Integer comensales) {
        this.comensales = comensales;
    }
    
    public Categoria getCategoria() {
        return categoria;
    }
    
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    public LocalTime getTiempo() {
        return tiempo;
    }
    
    public void setTiempo(LocalTime tiempo) {
        this.tiempo = tiempo;
    }
    
    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }
    
    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
    public EstadoReceta getEstado() {
        return estado;
    }
    
    public void setEstado(EstadoReceta estado) {
        this.estado = estado;
    }
    
    public List<Ingredientes> getIngredientes() {
        return ingredientes;
    }
    
    public void setIngredientes(List<Ingredientes> ingredientes) {
        this.ingredientes = ingredientes;
    }
    
    @Override
    public String toString() {
        return "Receta{" +
                "idReceta=" + idReceta +
                ", titulo='" + titulo + '\'' +
                ", usuario=" + (usuario != null ? usuario.getNombreUsuario() : "null") +
                ", comensales=" + comensales +
                ", categoria=" + categoria +
                ", estado=" + estado +
                '}';
    }
}