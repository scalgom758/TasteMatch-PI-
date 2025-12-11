package com.proyectofinal.tastematch.repository;

import com.proyectofinal.tastematch.entities.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {

    // Métodos existentes
    Optional<Usuarios> findByNombreUsuario(String nombreUsuario);
    Optional<Usuarios> findByEmail(String email);
    boolean existsByNombreUsuario(String nombreUsuario);
    boolean existsByEmail(String email);

    // Nuevos métodos para los campos adicionales
    List<Usuarios> findByTipoUsuario(Usuarios.TipoUsuario tipoUsuario);
    List<Usuarios> findByEstado(Usuarios.EstadoUsuario estado);
    long countByTipoUsuario(Usuarios.TipoUsuario tipoUsuario);
    long countByEstado(Usuarios.EstadoUsuario estado);
}