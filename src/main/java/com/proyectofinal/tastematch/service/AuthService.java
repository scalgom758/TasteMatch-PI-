package com.proyectofinal.tastematch.service;

import com.proyectofinal.tastematch.dto.LoginRequest;
import com.proyectofinal.tastematch.dto.RegisterRequest;
import com.proyectofinal.tastematch.entities.Usuarios;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private UsuariosService usuariosService;

    @Autowired
    private SecurityService securityService;

    public Usuarios registrarUsuario(RegisterRequest request) {
        logger.info("🎯 === INICIO REGISTRO ===");
        logger.info("Usuario: {}, Email: {}", request.getUsername(), request.getEmail());

        // 1. Validar contraseñas
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            logger.error("❌ Contraseñas no coinciden");
            throw new RuntimeException("Las contraseñas no coinciden");
        }

        // 2. Validar longitud
        if (request.getPassword().length() < 6) {
            throw new RuntimeException("La contraseña debe tener al menos 6 caracteres");
        }

        // 3. Verificar si ya existe
        if (usuariosService.existePorNombreUsuario(request.getUsername())) {
            logger.error("❌ Usuario ya existe: {}", request.getUsername());
            throw new RuntimeException("El nombre de usuario ya está en uso");
        }
        if (usuariosService.existePorEmail(request.getEmail())) {
            logger.error("❌ Email ya registrado: {}", request.getEmail());
            throw new RuntimeException("El email ya está registrado");
        }

        // 4. Crear usuario
        Usuarios usuario = new Usuarios();
        usuario.setNombreUsuario(request.getUsername());
        usuario.setEmail(request.getEmail());

        // 5. Encriptar contraseña
        String encryptedPassword = securityService.encryptPassword(request.getPassword());
        logger.info("🔐 Contraseña encriptada");
        usuario.setContrasenia(encryptedPassword);

        // 6. Guardar
        Usuarios saved = usuariosService.crearUsuario(usuario);
        logger.info("✅ === REGISTRO EXITOSO ===");
        logger.info("ID: {}", saved.getIdUsuario());

        return saved;
    }

    public Usuarios loginUsuario(LoginRequest request) {
        logger.info("🎯 === INICIO LOGIN ===");
        logger.info("Usuario recibido: '{}'", request.getUsername());

        if (request.getUsername() == null || request.getUsername().trim().isEmpty()) {
            logger.error("❌ Usuario vacío");
            throw new RuntimeException("Nombre de usuario requerido");
        }

        // Buscar usuario
        Usuarios usuario = usuariosService.obtenerUsuarioPorNombre(request.getUsername())
                .orElseThrow(() -> {
                    logger.error("❌ Usuario no encontrado: {}", request.getUsername());
                    return new RuntimeException("Usuario no encontrado");
                });

        logger.info("✅ Usuario encontrado. ID: {}", usuario.getIdUsuario());

        // Validar contraseña
        boolean passwordMatch = securityService.verifyPassword(
                request.getPassword(),
                usuario.getContrasenia()
        );

        logger.info("🔐 ¿Contraseña válida?: {}", passwordMatch);

        if (!passwordMatch) {
            logger.error("❌ Contraseña incorrecta");
            throw new RuntimeException("Contraseña incorrecta");
        }

        logger.info("✅ === LOGIN EXITOSO ===");
        return usuario;
    }
}