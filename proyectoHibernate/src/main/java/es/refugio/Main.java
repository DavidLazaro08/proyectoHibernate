package es.refugio;

import es.refugio.dao.AnimalDAO;
import es.refugio.dao.AnimalDAOImpl;
import es.refugio.entities.Animal;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AnimalDAO dao = new AnimalDAOImpl();
        int opcion;

        do {
            System.out.println("\n--- Refugio de Animales ---");
            System.out.println("1. Registrar animal");
            System.out.println("2. Listar animales");
            System.out.println("3. Buscar por especie");
            System.out.println("4. Actualizar estado");
            System.out.println("5. Eliminar animal");
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
                    dao.create(nuevo);
                    System.out.println("Animal registrado correctamente.");
                }

                case 2 -> {
                    List<Animal> lista = dao.findAll();
                    for (Animal a : lista) {
                        System.out.println(a.getId() + " - " + a.getNombre() + " (" + a.getEspecie() + ")");
                    }
                }

                case 3 -> {
                    System.out.print("Especie a buscar: ");
                    String esp = sc.nextLine();
                    List<Animal> lista = dao.findByEspecie(esp);

                    for (Animal a : lista) {
                        System.out.println(a.getId() + " - " + a.getNombre() + " (" + a.getEspecie() + ")");
                    }
                }

                case 4 -> {
                    System.out.print("ID del animal: ");
                    Long id = Long.parseLong(sc.nextLine());

                    System.out.print("Nuevo estado: ");
                    String est = sc.nextLine();

                    Animal a = dao.findById(id);
                    if (a != null) {
                        a.setEstado(est);
                        dao.update(a);
                        System.out.println("Estado actualizado.");
                    } else {
                        System.out.println("No existe un animal con ese ID.");
                    }
                }

                case 5 -> {
                    System.out.print("ID a eliminar: ");
                    Long id = Long.parseLong(sc.nextLine());

                    boolean borrado = dao.deleteById(id);
                    if (borrado) {
                        System.out.println("Animal eliminado.");
                    } else {
                        System.out.println("No existe un animal con ese ID.");
                    }
                }

                case 0 -> System.out.println("Saliendo...");

                default -> System.out.println("Opción no válida.");
            }

        } while (opcion != 0);

        sc.close();
    }
}
