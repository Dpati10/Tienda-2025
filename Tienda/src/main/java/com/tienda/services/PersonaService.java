/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.services;

import java.util.List;
import java.util.Optional;
import com.tienda.entities.Persona;
import com.tienda.repositories.PersonaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author diego
 */
@Service
public class PersonaService {
    
    @Autowired
    private PersonaRepository personaRepository;
    
    public List<Persona> findAll(){
        return (List<Persona>) this.personaRepository.findAll();
    }
    
    public Persona save (Persona persona){
        return personaRepository.save(persona);
    }
    
     public Optional<Persona> getById (Long id){
        return personaRepository.findById(id);
    }
    
     public Persona delete (Long id){
        personaRepository.deleteById(id);
        return null;
        }
}
