/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.services;

import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author diego
 */
public class UserService implements UserDetailsService {
    
    @Autowired
    public IPersonaService personaService;
    
    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException
            Persona persona = rhis.personaService.findByNombre(username);
            Userprincipal userPrincipal = new Userprincipak(persona);
            return userPrincipal;
            
    
}
