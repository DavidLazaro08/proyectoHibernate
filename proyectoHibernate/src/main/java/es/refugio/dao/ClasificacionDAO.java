package es.refugio.dao;

import es.refugio.entities.Clasificacion;
import java.util.List;

public interface ClasificacionDAO {

    List<Clasificacion> findAll();

    Clasificacion findById(Long id);

    Clasificacion findByCodigo(String codigo);

    Clasificacion create(Clasificacion clasificacion);

    Clasificacion update(Clasificacion clasificacion);

    boolean deleteById(Long id);
}
