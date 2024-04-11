package com.softtek.persistencia;

import com.softtek.modelo.Empleado;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccesoEmpleado extends Conexion{
    public boolean modificarEmpleado(int id, Empleado e) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE employees SET nombre = ?, apellidos = ?, jefe = ? WHERE idEmpleado = ?";
        abrirConexion();
        PreparedStatement ps = miConexion.prepareStatement(sql);

        ps.setString(1, e.getNombre());
        ps.setString(2, e.getApellidos());
        ps.setInt(3, e.getJefe());
        ps.setInt(4, id);


        int filasAfectadas = ps.executeUpdate();


        if (filasAfectadas > 0) {
            System.out.println("Empleado modificado con éxito.");
        } else {
            System.out.println("No se pudo modificar el empleado.");
        }
        return false;
    }
    public boolean borrarEmpleado(int id) throws  ClassNotFoundException, SQLException {
        String sql = "DELETE FROM employees WHERE employee_id = ?";
        abrirConexion();
        PreparedStatement sentencia = miConexion.prepareStatement(sql);
        sentencia.setInt(1, id);
        return sentencia.executeUpdate() > 0;
    }
    public boolean añadir(Empleado e) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO employees (employee_id, first_name, last_name, reports_to)\n" +
                "\t\t\t\tVALUES (?, ?, ?, ?)";
        abrirConexion();
        PreparedStatement ps = miConexion.prepareStatement(sql);
        ps.setInt(1,e.getIdEmpleado());
        ps.setString(2,e.getNombre());
        ps.setString(3,e.getApellidos());
        ps.setInt(4,e.getIdEmpleado());
        return ps.executeUpdate() > 0;
    }
    public List<Empleado> obtenerTodos() throws SQLException, ClassNotFoundException {
        PreparedStatement sentencia;
        ResultSet resultado;
        String sql = """
    SELECT employee_id, last_name, first_name, reports_to FROM employees;
    """;
        List<Empleado> empleados = new ArrayList<>();
        abrirConexion();
        sentencia = miConexion.prepareStatement(sql);
        resultado = sentencia.executeQuery();
        while (resultado.next()){
            empleados.add(
                    new Empleado(
                            resultado.getInt("employee_id"),
                            resultado.getString("last_name"),
                            resultado.getString("first_name"),
                            resultado.getInt("reports_to")
                    )
            );
        }
        return empleados;
    }

    public Empleado obtenerEmpleadoPorId(int id) throws SQLException, ClassNotFoundException {
        PreparedStatement sentencia;
        ResultSet resultado;
        String sql = "SELECT employee_id, last_name, first_name, reports_to FROM employees WHERE employee_id = ?";
        Empleado empleado = null;
        abrirConexion();
        sentencia = miConexion.prepareStatement(sql);
        sentencia.setInt(1, id);
        resultado = sentencia.executeQuery();
        if (resultado.next()) {
            empleado = new Empleado(
                    resultado.getInt("employee_id"),
                    resultado.getString("last_name"),
                    resultado.getString("first_name"),
                    resultado.getInt("reports_to")
            );
        }
        return empleado;
    }
}
