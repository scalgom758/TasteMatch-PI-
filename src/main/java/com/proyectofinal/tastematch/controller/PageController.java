package com.proyectofinal.tastematch.controller;

import com.proyectofinal.tastematch.dto.LoginRequest;
import com.proyectofinal.tastematch.dto.RegisterRequest;
import com.proyectofinal.tastematch.entities.Usuarios;
import com.proyectofinal.tastematch.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PageController {

    private static final Logger logger = LoggerFactory.getLogger(PageController.class);

    @Autowired
    private AuthService authService;

    // Página principal
    @GetMapping("/")
    public String index() {
        logger.info("📄 GET / - Página principal");
        return "index";
    }

    // ========== LOGIN ==========

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        logger.info("📄 GET /login - Mostrando formulario");
        model.addAttribute("loginRequest", new LoginRequest());
        return "auth/login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute LoginRequest request,
                            Model model) {
        logger.info("🔐 POST /login - Usuario: {}", request.getUsername());

        try {
            Usuarios usuario = authService.loginUsuario(request);
            logger.info("✅ Login exitoso para: {}", usuario.getNombreUsuario());
            model.addAttribute("success", "¡Bienvenido " + usuario.getNombreUsuario() + "!");
            return "index";
        } catch (RuntimeException e) {
            logger.error("❌ Error en login: {}", e.getMessage());
            model.addAttribute("error", e.getMessage());
            model.addAttribute("username", request.getUsername());
            return "auth/login";
        }
    }

    // ========== REGISTRO ==========

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        logger.info("📄 GET /register - Mostrando formulario");
        model.addAttribute("registerRequest", new RegisterRequest());
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute RegisterRequest request,
                               Model model) {
        logger.info("📝 POST /register - Usuario: {}, Email: {}",
                request.getUsername(), request.getEmail());

        try {
            Usuarios usuario = authService.registrarUsuario(request);
            logger.info("✅ Registro exitoso. ID: {}", usuario.getIdUsuario());
            model.addAttribute("success", "¡Registro exitoso! Ahora puedes iniciar sesión.");
            return "auth/login";
        } catch (RuntimeException e) {
            logger.error("❌ Error en registro: {}", e.getMessage());
            model.addAttribute("error", e.getMessage());
            model.addAttribute("username", request.getUsername());
            model.addAttribute("email", request.getEmail());
            return "auth/register";
        }
    }

    // ========== ENDPOINT DEBUG ==========

    @GetMapping("/debug")
    @ResponseBody
    public String debug() {
        return "<h1>Debug - TasteMatch</h1>" +
                "<p>Aplicación funcionando</p>" +
                "<ul>" +
                "<li><a href='/'>Home</a></li>" +
                "<li><a href='/login'>Login</a></li>" +
                "<li><a href='/register'>Register</a></li>" +
                "</ul>";
    }
}