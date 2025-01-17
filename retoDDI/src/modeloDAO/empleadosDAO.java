package modeloDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import conexion.conexion;
import modeloDTO.empleadosDTO;

public class empleadosDAO implements Patron_DAO<empleadosDTO> {

    private static final String SQL_INSERT = "INSERT INTO empleados (nombre, apellidos, email, telefono, cargo, fecha_alta, estado, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE = "DELETE FROM empleados WHERE id_empleado = ?";
    private static final String SQL_UPDATE = "UPDATE empleados SET nombre = ?, apellidos = ?, email = ?, telefono = ?, cargo = ?, estado = ? WHERE id_empleado = ?";
    private static final String SQL_SELECT = "SELECT * FROM empleados";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM empleados WHERE id_empleado = ?";
    private static final String SQL_SELECT_BY_EMP = "SELECT cargo FROM empleados WHERE email = ? AND password = ?";
    private static final String SQL_SELECT_BY_EMAIL = "SELECT id_empleado FROM empleados WHERE email = ?";

    private conexion con = conexion.getInstancia();
    @Override
    public boolean insertar(empleadosDTO empleado) {
        try (PreparedStatement ps = con.getCon().prepareStatement(SQL_INSERT)) {
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getApellidos());
            ps.setString(3, empleado.getEmail());
            ps.setString(4, empleado.getTelefono());
            ps.setString(5, empleado.getCargo());
            ps.setDate(6, empleado.getFechaAlta());
            ps.setString(7, empleado.getEstado());
            ps.setString(8, empleado.getPassword());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean borrar(Object pk) {
        try (PreparedStatement ps = con.getCon().prepareStatement(SQL_DELETE)) {
            ps.setInt(1, (int) pk);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean actualizar(empleadosDTO empleado) {
        try (PreparedStatement ps = con.getCon().prepareStatement(SQL_UPDATE)) {
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getApellidos());
            ps.setString(3, empleado.getEmail());
            ps.setString(4, empleado.getTelefono());
            ps.setString(5, empleado.getCargo());
            ps.setString(6, empleado.getEstado());
            ps.setInt(7, empleado.getIdEmpleado());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public empleadosDTO buscar(Object pk) {
        try (PreparedStatement ps = con.getCon().prepareStatement(SQL_SELECT_BY_ID)) {
            ps.setInt(1, (int) pk);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new empleadosDTO(
                            rs.getInt("id_empleado"),
                            rs.getString("nombre"),
                            rs.getString("apellidos"),
                            rs.getString("email"),
                            rs.getString("telefono"),
                            rs.getString("cargo"),
                            rs.getDate("fecha_alta"),
                            rs.getString("estado"),
                            rs.getString("password")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<empleadosDTO> listarTodos() {
        ArrayList<empleadosDTO> lista = new ArrayList<>();
        try (PreparedStatement ps = con.getCon().prepareStatement(SQL_SELECT);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new empleadosDTO(
                        rs.getInt("id_empleado"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        rs.getString("cargo"),
                        rs.getDate("fecha_alta"),
                        rs.getString("estado"),
                        rs.getString("password")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    public String obtenerCargoPorEmailYPassword(String email, String password) {
        try (PreparedStatement ps = con.getCon().prepareStatement(SQL_SELECT_BY_EMP)) {
            ps.setString(1, email);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("cargo");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<empleadosDTO> buscarPorCriterio(String campo, String valor) {
    	ArrayList<empleadosDTO> lista = new ArrayList<>();
        String sql = "SELECT nombre, apellidos, email, telefono, cargo, fecha_alta, estado, password FROM empleados WHERE " + campo + " LIKE ?";
        
        try (PreparedStatement ps = con.getCon().prepareStatement(sql)) {
            ps.setString(1, "%" + valor + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new empleadosDTO(
                    0,
                    rs.getString("nombre"),
                    rs.getString("apellidos"),
                    rs.getString("email"),
                    rs.getString("telefono"),
                    rs.getString("cargo"),
                    rs.getDate("fecha_alta"),
                    rs.getString("estado"),
                    rs.getString("password")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public ArrayList<empleadosDTO> listarOrdenados(String campo, String orden) {
    	ArrayList<empleadosDTO> lista = new ArrayList<>();
        String sql = "SELECT nombre, apellidos, email, telefono, cargo, fecha_alta, estado, password FROM empleados ORDER BY " + campo + " " + orden;

        try (PreparedStatement ps = con.getCon().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new empleadosDTO(
                    0,
                    rs.getString("nombre"),
                    rs.getString("apellidos"),
                    rs.getString("email"),
                    rs.getString("telefono"),
                    rs.getString("cargo"),
                    rs.getDate("fecha_alta"),
                    rs.getString("estado"),
                    rs.getString("password")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    public empleadosDTO buscarPorEmail(String email) {
        try (PreparedStatement ps = con.getCon().prepareStatement(SQL_SELECT_BY_EMAIL)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int idEmpleado = rs.getInt("id_empleado");
                    return buscar(idEmpleado);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
