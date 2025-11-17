package es.refugio.dao;

import es.refugio.entities.Animal;
import java.util.List;

public interface AnimalDAO {

    List<Animal> findAll();

    Animal findById(Long id);

    List<Animal> findByEspecie(String especie);

    Animal create(Animal animal);

    Animal update(Animal animal);

    boolean deleteById(Long id);
}
