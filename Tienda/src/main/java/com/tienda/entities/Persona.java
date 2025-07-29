/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;
import static org.hibernate.internal.util.PropertiesHelper.map;

/**
 *
 * @author diego
 */
@Entity
@Table(
        name = "Persona",
        uniqueConstraints = @UniqueConstraint(name = "uk_persona_email", columnNames = "email")
)
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 100, nullable = false)
    private String nombre;
    @NotBlank
    @Column(length = 100, nullable = false)
    private String apellido;

    @NotBlank
    @Email
    @Column(length = 191, nullable = false, unique = true)
    private String email;

    @Column(length = 30)
    private String telefono;

    @Column(length = 255)
    private String direccion;

    @NotBlank
    @Column(length = 100, nullable = false)
    private String password;

// activo / habilitado
    @Column(nullable = false)
    private int active = 1;

    // roles separados por coma : admin, user, vendedor
    @Column(length = 255)
    private String roles = "";

    //Permisos
    @Column(length = 500)
    private String permissions = "";
    
    //METODOS GET Y SET

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    // HELPERS
    
    @Transient
    public boolean isEnabled() {
        return this.active == 1;
    }
    
     @Transient
    public List<String> getRoleList() {
        if(roles ==null || roles.isBlank()) return new ArrayList <> ();
        return Arrays.stream(roles.split(","))
                    .map(String ::trim)
                    .filter(s → !s.isEmpty())
                    .map(String::toUpperCase)
                    .collect(Collectors,toList());
    }
    
    @Transient
    public List<String> getPermissionList() {
        if(permissions ==null || permissions.isBlank()) return new ArrayList <> ();
        return Arrays.stream(permissions.split(","))
                    .map(String ::trim)
                    .filter(s → !s.isEmpty())
                    .collect(Collectors,toList());
    }
    
    /* Métodos de conveniencia para agregar roles/permissions */

public void addRole(String role) {
    if (role == null || role.isBlank()) return;
    List<String> list = getRoleList();
    String r = role.trim().toUpperCase();
    if (!list.contains(r)) {
        list.add(r);
        this.roles = String.join(",", list);
    }
}

public void addPermission(String permission) {
    if (permission == null || permission.isBlank()) return;
    List<String> list = getPermissionList();
    String p = permission.trim();
    if (!list.contains(p)) {
        list.add(p);
        this.permissions = String.join(",", list);
    }
    
}




}// Fin clase