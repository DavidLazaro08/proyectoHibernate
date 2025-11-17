package es.refugio.dao;

import es.refugio.entities.Persona;
import java.util.List;

public interface PersonaDAO {

    List<Persona> findAll();

    Persona findById(Long id);

    Persona findByDni(String dni);

    Persona create(Persona persona);

    Persona update(Persona persona);

    boolean deleteById(Long id);
}
