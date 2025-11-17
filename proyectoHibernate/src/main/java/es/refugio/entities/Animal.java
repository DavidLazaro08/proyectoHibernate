package es.refugio.entities;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "animal")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String especie;
    private int edad;

    @Column(length = 500)
    private String descripcion;

    private String estado;

    // ⭐ MANY TO ONE → Un animal tiene un dueño
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_id")
    private Persona duenio;

    // ⭐ MANY TO MANY → Un animal tiene varias clasificaciones
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "animal_clasificacion",
            joinColumns = @JoinColumn(name = "animal_id"),
            inverseJoinColumns = @JoinColumn(name = "clasificacion_id")
    )
    private Set<Clasificacion> clasificaciones = new HashSet<>();

    public Animal() {}

    public Animal(String nombre, String especie, int edad, String descripcion, String estado) {
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public Long getId() { return id; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEspecie() { return especie; }

    public void setEspecie(String especie) { this.especie = especie; }

    public int getEdad() { return edad; }

    public void setEdad(int edad) { this.edad = edad; }

    public String getDescripcion() { return descripcion; }

    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getEstado() { return estado; }

    public void setEstado(String estado) { this.estado = estado; }

    public Persona getDuenio() { return duenio; }

    public void setDuenio(Persona duenio) { this.duenio = duenio; }

    public Set<Clasificacion> getClasificaciones() { return clasificaciones; }

    public void setClasificaciones(Set<Clasificacion> clasificaciones) { this.clasificaciones = clasificaciones; }
}
