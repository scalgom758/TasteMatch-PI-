package com.proyectofinal.tastematch.dto;

import com.proyectofinal.tastematch.entities.Usuarios;

public class AuthResponse {
    private boolean success;
    private String message;
    private Usuarios usuario;

    // Constructores
    public AuthResponse() {}

    public AuthResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public AuthResponse(boolean success, String message, Usuarios usuario) {
        this.success = success;
        this.message = message;
        this.usuario = usuario;
    }

    // Getters y Setters
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Usuarios getUsuario() { return usuario; }
    public void setUsuario(Usuarios usuario) { this.usuario = usuario; }
}