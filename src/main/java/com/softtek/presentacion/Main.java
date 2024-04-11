package com.softtek.presentacion;

import com.softtek.modelo.Empleado;
import com.softtek.persistencia.AccesoEmpleado;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println("Que quieres hacer en la BBDD");
        System.out.println("1. Mostrar uno, 2.Mostrar todos, 3.Añadir, 4.Eliminar, 5.Modificar");
        Scanner sc = new Scanner(System.in);
        AccesoEmpleado ae = new AccesoEmpleado();
        int opt = sc.nextInt();
        switch (opt) {
            case 1:
                System.out.println("Has seleccionado mostrar un empleado por su ID.");
                System.out.println("Introduce el ID del empleado:");
                int idEmpleadoMostrar = sc.nextInt();
                try {
                    Empleado empleadoMostrado = ae.obtenerEmpleadoPorId(idEmpleadoMostrar);
                    if (empleadoMostrado != null) {
                        System.out.println("Empleado encontrado:");
                        System.out.println(empleadoMostrado);
                    } else {
                        System.out.println("No se encontró ningún empleado con el ID proporcionado.");
                    }
                } catch (SQLException e) {
                    System.out.println("Error al obtener el empleado: " + e.getMessage());
                }
                break;
            case 2:
                System.out.println("Has seleccionado mostrar todos los empleados:");
                try {
                    List<Empleado> listaEmpleados = ae.obtenerTodos();
                    if (!listaEmpleados.isEmpty()) {
                        System.out.println("Lista de empleados:");
                        for (Empleado emp : listaEmpleados) {
                            System.out.println(emp);
                        }
                    } else {
                        System.out.println("No hay empleados registrados en la base de datos.");
                    }
                } catch (SQLException e) {
                    System.out.println("Error al obtener la lista de empleados: " + e.getMessage());
                }
                break;
            case 3:
                System.out.println("Has seleccionado añadir un nuevo empleado");
                Empleado e1 = new Empleado();
                System.out.println("Introduzca el id del empleado");
                e1.setIdEmpleado(sc.nextInt());
                System.out.println("Introduzca el nombre del empleado");
                e1.setIdEmpleado(sc.nextInt());
                System.out.println("Introduzca el apellido del empleado");
                e1.setIdEmpleado(sc.nextInt());
                System.out.println("Introduzca el id del jefe del empleado");
                e1.setIdEmpleado(sc.nextInt());
                System.out.println(ae.añadir(e1));
                break;
            case 4:
                System.out.println("Has seleccionado eliminar un empleado");
                System.out.println("Introduce el ID del empleado que deseas eliminar:");
                int idEmpleadoEliminar = sc.nextInt();
                try {
                    if (ae.borrarEmpleado(idEmpleadoEliminar)) {
                        System.out.println("Empleado eliminado exitosamente.");
                    } else {
                        System.out.println("No se pudo eliminar el empleado.");
                    }
                } catch (SQLException e) {
                    System.out.println("Error al eliminar el empleado: " + e.getMessage());
                }
                break;
            case 5:
                System.out.println("Has seleccionado modificar un empleado existente");
                System.out.println("Introduce el ID del empleado que deseas modificar:");
                int idEmpleadoModificar = sc.nextInt();
                System.out.println("Introduce el nuevo nombre del empleado:");
                String nuevoNombre = sc.next();
                System.out.println("Introduce el nuevo apellido del empleado:");
                String nuevoApellido = sc.next();
                System.out.println("Introduce el nuevo ID del jefe del empleado:");
                int nuevoIdJefe = sc.nextInt();
                try {
                    if (ae.modificarEmpleado(idEmpleadoModificar, new Empleado(idEmpleadoModificar, nuevoNombre, nuevoApellido, nuevoIdJefe))) {
                        System.out.println("Empleado modificado exitosamente.");
                    } else {
                        System.out.println("No se pudo modificar el empleado.");
                    }
                } catch (SQLException e) {
                    System.out.println("Error al modificar el empleado: " + e.getMessage());
                }
                break;
            default:
                System.out.println("Opcion no valida");
        }
    }
}
