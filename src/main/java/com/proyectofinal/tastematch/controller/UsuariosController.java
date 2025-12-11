package com.proyectofinal.tastematch.controller;

import com.proyectofinal.tastematch.entities.Usuarios;
import com.proyectofinal.tastematch.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuariosController {
    
    @Autowired
    private UsuariosService usuarioService;
    
    // Crear nuevo usuario
    @PostMapping("/alta")
    public ResponseEntity<?> crearUsuario(@RequestBody Usuarios usuario) {
        try {
            Usuarios nuevoUsuario = usuarioService.crearUsuario(usuario);
            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    // Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<Usuarios>> obtenerTodosLosUsuarios() {
        List<Usuarios> usuarios = usuarioService.obtenerTodosLosUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
    
    // Obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerUsuarioPorId(@PathVariable Long id) {
        Optional<Usuarios> usuario = usuarioService.obtenerUsuarioPorId(id);
        if (usuario.isPresent()) {
            return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
        }
    }
    
    // Actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Long id, @RequestBody Usuarios usuario) {
        try {
            Usuarios usuarioActualizado = usuarioService.actualizarUsuario(id, usuario);
            return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    // Eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
        try {
            usuarioService.eliminarUsuario(id);
            return new ResponseEntity<>("Usuario eliminado correctamente", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}/biografia")
    public ResponseEntity<?> actualizarBiografia(@PathVariable Long id, @RequestBody String biografia) {
        try {
            Usuarios usuario = usuarioService.obtenerUsuarioPorId(id)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            usuario.setBiografia(biografia);
            Usuarios usuarioActualizado = usuarioService.actualizarUsuario(id, usuario);
            return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Cambiar estado de usuario
    @PatchMapping("/{id}/estado")
    public ResponseEntity<?> cambiarEstado(@PathVariable Long id, @RequestBody String estado) {
        try {
            Usuarios usuario = usuarioService.obtenerUsuarioPorId(id)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            usuario.setEstado(Usuarios.EstadoUsuario.valueOf(estado.toUpperCase()));
            Usuarios usuarioActualizado = usuarioService.actualizarUsuario(id, usuario);
            return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}