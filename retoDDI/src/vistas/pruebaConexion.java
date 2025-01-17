package vistas;

import modeloDTO.empleadosDTO;

import java.sql.Date;

import conexion.conexion;
import modeloDAO.empleadosDAO;

public class pruebaConexion {

    public static void main(String[] args) {
        empleadosDAO empleadoDAO = new empleadosDAO();
        Date fechaActual = new Date(System.currentTimeMillis());

        empleadosDTO empleado = new empleadosDTO(1, "Carlos", "García López", "carlos.garcia@example.com", "678901234", "Desarrollador", fechaActual, "Activo", "1234");
        
       /*if (empleadoDAO.insertar(empleado)) {
            System.out.println("Se añadió el empleado");
        } else {
            System.out.println("Error");
        }*/
        
        conexion conexion = new conexion();
        System.out.println(conexion.getInstancia());
    }
}
