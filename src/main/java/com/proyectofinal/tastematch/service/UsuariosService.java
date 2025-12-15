package com.proyectofinal.tastematch.service;

import com.proyectofinal.tastematch.entities.Usuarios;
import com.proyectofinal.tastematch.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UsuariosService {

    @Autowired
    private UsuariosRepository usuarioRepository;

    // Crear nuevo usuario
    public Usuarios crearUsuario(Usuarios usuario) {
        try {
            if (usuarioRepository.existsByNombreUsuario(usuario.getNombreUsuario())) {
                throw new RuntimeException("El nombre de usuario '" + usuario.getNombreUsuario() + "' ya está en uso");
            }

            if (usuarioRepository.existsByNombreUsuario(usuario.getNombreUsuario())) {
                throw new RuntimeException("El usuario '" + usuario.getNombreUsuario() + "' ya está registrado");
            }

            if (usuario.getFechaRegistro() == null) {
                usuario.setFechaRegistro(LocalDate.now());
            }

            if (usuario.getTipoUsuario() == null) {
                usuario.setTipoUsuario(Usuarios.TipoUsuario.REGISTERED);
            }

            if (usuario.getEstado() == null) {
                usuario.setEstado(Usuarios.EstadoUsuario.ACTIVE);
            }

            return usuarioRepository.save(usuario);

        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Error de integridad de datos: " + e.getMessage());
        }
    }

    // Obtener todos los usuarios
    public List<Usuarios> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    // Obtener usuario por ID
    public Optional<Usuarios> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    // Obtener usuario por nombre de usuario
    public Optional<Usuarios> obtenerUsuarioPorNombre(String nombreUsuario) {
        System.out.println("🔍 Buscando usuario por nombre: '" + nombreUsuario + "'");

        try {
            Optional<Usuarios> usuario = usuarioRepository.findByNombreUsuario(nombreUsuario);

            if (usuario.isPresent()) {
                System.out.println("✅ Usuario encontrado en BD: " + usuario.get().getNombreUsuario());
            } else {
                System.out.println("❌ Usuario NO encontrado en BD: " + nombreUsuario);

                // Listar todos los usuarios para debug
                List<Usuarios> todos = usuarioRepository.findAll();
                System.out.println("📋 Usuarios en BD (" + todos.size() + "):");
                todos.forEach(u -> System.out.println("   - " + u.getNombreUsuario()));
            }

            return usuario;
        } catch (Exception e) {
            System.out.println("💥 ERROR en obtenerUsuarioPorNombre: " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }

    // Obtener usuarios por estado
    public List<Usuarios> obtenerUsuariosPorEstado(Usuarios.EstadoUsuario estado) {
        return usuarioRepository.findByEstado(estado);
    }

    // Actualizar usuario
    public Usuarios actualizarUsuario(Long id, Usuarios usuarioActualizado) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    if (!usuario.getNombreUsuario().equals(usuarioActualizado.getNombreUsuario()) &&
                            usuarioRepository.existsByNombreUsuario(usuarioActualizado.getNombreUsuario())) {
                        throw new RuntimeException("El nombre de usuario '" + usuarioActualizado.getNombreUsuario() + "' ya está en uso");
                    }

                    usuario.setNombreUsuario(usuarioActualizado.getNombreUsuario());
                    usuario.setContrasenia(usuarioActualizado.getContrasenia());
                    usuario.setBiografia(usuarioActualizado.getBiografia());
                    usuario.setTipoUsuario(usuarioActualizado.getTipoUsuario());
                    usuario.setEstado(usuarioActualizado.getEstado());

                    return usuarioRepository.save(usuario);
                })
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }

    // Cambiar tipo de usuario
    public Usuarios cambiarTipoUsuario(Long id, Usuarios.TipoUsuario tipoUsuario) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuario.setTipoUsuario(tipoUsuario);
                    return usuarioRepository.save(usuario);
                })
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }

    // Cambiar estado de usuario
    public Usuarios cambiarEstadoUsuario(Long id, Usuarios.EstadoUsuario estado) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuario.setEstado(estado);
                    return usuarioRepository.save(usuario);
                })
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }

    // Eliminar usuario
    public void eliminarUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
        } else {
            throw new RuntimeException("Usuario no encontrado con id: " + id);
        }
    }

    // Verificar si existe usuario por ID
    public boolean existeUsuario(Long id) {
        return usuarioRepository.existsById(id);
    }

    // Verificar si existe usuario por nombre de usuario
    public boolean existePorNombreUsuario(String nombreUsuario) {
        return usuarioRepository.existsByNombreUsuario(nombreUsuario);
    }

    // Contar total de usuarios
    public long contarUsuarios() {
        return usuarioRepository.count();
    }

    // Contar usuarios por estado
    public long contarUsuariosPorEstado(Usuarios.EstadoUsuario estado) {
        return usuarioRepository.countByEstado(estado);
    }
}