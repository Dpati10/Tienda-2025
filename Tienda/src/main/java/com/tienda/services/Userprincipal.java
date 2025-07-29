/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.services;

/**
 *
 * @author diego
 */
public class Userprincipal implements UserDetails {

    private Persona persona;

    public Userprincipal(Persona persona) {
        this.persona = persona;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        this.persona.getPermissionList().forEch(p 
            → {
        GrantedAuthority authority = new SimpleGrantedAuthority(p);
            authorities.add(authority);
        }
        );
        
        //roles
        
        this.persona.getRoleList().forEch(r 
            → {
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + r);
            authorities.add(authority);
        }
        );
        return authorities;
        
    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    
    @Override
    public String getPassword(){
        return this.persona.getPassword();
    }
    
     @Override
    public String getUsername(){
        return this.persona.getNombre();
    }
    
    @Override
    public boolean isAccountNonExpired(){
        return true;
    }
    
    @Override
    public boolean isAccountNoLocked(){
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }
    
    @Override
    public boolean isAEnabled(){
        return this.persona.isEnabled();
    }
    
    

} // fin de la clase 

