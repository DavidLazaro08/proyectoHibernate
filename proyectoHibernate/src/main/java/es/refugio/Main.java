package es.refugio;

import es.refugio.dao.*;
import es.refugio.entities.*;

import java.util.*;

/* Desarrollé el proyecto siguiendo la estructura y los ejemplos trabajados en
 * clase, adaptándolos a lo que nos pedía: un sistema completo con CRUD, relaciones
 * entre entidades y un menú interactivo por consola. */


public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        AnimalDAO animalDAO = new AnimalDAOImpl();
        PersonaDAO personaDAO = new PersonaDAOImpl();
        ClasificacionDAO clasificacionDAO = new ClasificacionDAOImpl();

        int opcion;

        do {
            System.out.println("\n--- Refugio de Animales ---");
            System.out.println("1. Registrar animal");
            System.out.println("2. Listar animales");
            System.out.println("3. Buscar animales por especie");
            System.out.println("4. Actualizar estado de un animal");
            System.out.println("5. Eliminar animal");
            System.out.println("6. Registrar persona (dueño)");
            System.out.println("7. Listar personas");
            System.out.println("8. Registrar clasificación");
            System.out.println("9. Asignar dueño a animal");
            System.out.println("10. Asignar clasificación a animal");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {

                case 1 -> {
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Especie: ");
                    String especie = sc.nextLine();

                    System.out.print("Edad: ");
                    int edad = Integer.parseInt(sc.nextLine());

                    System.out.print("Descripción: ");
                    String descripcion = sc.nextLine();

                    System.out.print("Estado: ");
                    String estado = sc.nextLine();

                    Animal nuevo = new Animal(nombre, especie, edad, descripcion, estado);
                    animalDAO.create(nuevo);

                    System.out.println("Animal registrado correctamente.");
                }

                case 2 -> {
                    List<Animal> lista = animalDAO.findAll();
                    System.out.println("\n--- Lista de animales ---");
                    for (Animal a : lista) {
                        System.out.println(a.getId() + " - " + a.getNombre() +
                                " (" + a.getEspecie() + ") - Estado: " + a.getEstado());
                    }
                }

                case 3 -> {
                    System.out.print("Especie a buscar: ");
                    String esp = sc.nextLine();

                    List<Animal> lista = animalDAO.findByEspecie(esp);

                    System.out.println("\n--- Resultados ---");
                    for (Animal a : lista) {
                        System.out.println(a.getId() + " - " + a.getNombre());
                    }
                }

                case 4 -> {
                    System.out.print("ID del animal: ");
                    Long id = Long.parseLong(sc.nextLine());

                    Animal a = animalDAO.findById(id);

                    if (a == null) {
                        System.out.println("No existe un animal con ese ID.");
                        break;
                    }

                    System.out.print("Nuevo estado: ");
                    String est = sc.nextLine();

                    a.setEstado(est);
                    animalDAO.update(a);

                    System.out.println("Estado actualizado.");
                }

                case 5 -> {
                    System.out.print("ID a eliminar: ");
                    Long id = Long.parseLong(sc.nextLine());

                    boolean borrado = animalDAO.deleteById(id);

                    if (borrado)
                        System.out.println("Animal eliminado.");
                    else
                        System.out.println("No existe un animal con ese ID.");
                }

                case 6 -> {
                    System.out.print("DNI: ");
                    String dni = sc.nextLine();

                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    Persona p = new Persona(dni, nombre, email);
                    personaDAO.create(p);

                    System.out.println("Persona registrada correctamente.");
                }

                case 7 -> {
                    System.out.println("\n--- Lista de personas ---");
                    for (Persona p : personaDAO.findAll()) {
                        System.out.println(p.getId() + " - " + p.getNombre() + " (" + p.getDni() + ")");
                    }
                }

                case 8 -> {
                    System.out.print("Código (ej: MAM): ");
                    String codigo = sc.nextLine();

                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();

                    Clasificacion c = new Clasificacion(codigo, nombre);
                    clasificacionDAO.create(c);

                    System.out.println("Clasificación registrada.");
                }

                case 9 -> {
                    System.out.print("ID del animal: ");
                    Long idAnimal = Long.parseLong(sc.nextLine());

                    System.out.print("ID del dueño: ");
                    Long idDuenio = Long.parseLong(sc.nextLine());

                    Animal animal = animalDAO.findById(idAnimal);
                    Persona persona = personaDAO.findById(idDuenio);

                    if (animal == null || persona == null) {
                        System.out.println("Animal o persona no encontrados.");
                        break;
                    }

                    animal.setDuenio(persona);
                    persona.getAnimales().add(animal);

                    animalDAO.update(animal);
                    personaDAO.update(persona);

                    System.out.println("Dueño asignado correctamente.");
                }

                case 10 -> {
                    System.out.print("ID del animal: ");
                    Long idAnimal = Long.parseLong(sc.nextLine());

                    System.out.print("ID de la clasificación: ");
                    Long idClas = Long.parseLong(sc.nextLine());

                    Animal animal = animalDAO.findById(idAnimal);
                    Clasificacion clas = clasificacionDAO.findById(idClas);

                    if (animal == null || clas == null) {
                        System.out.println("Animal o clasificación no encontrados.");
                        break;
                    }

                    animal.getClasificaciones().add(clas);
                    clas.getAnimales().add(animal);

                    animalDAO.update(animal);
                    clasificacionDAO.update(clas);

                    System.out.println("Clasificación asignada correctamente.");
                }

                case 0 -> System.out.println("Saliendo...");

                default -> System.out.println("Opción no válida.");
            }

        } while (opcion != 0);

        sc.close();
    }
}
