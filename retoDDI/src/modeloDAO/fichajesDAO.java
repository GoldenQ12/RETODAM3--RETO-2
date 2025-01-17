package modeloDAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;

import conexion.conexion;
import modeloDTO.fichajesDTO;

public class fichajesDAO implements Patron_DAO<fichajesDTO> {

    private static final String SQL_INSERT = "INSERT INTO fichajes (id_empleado, fecha, hora_entrada, hora_salida, modo) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_DELETE = "DELETE FROM fichajes WHERE id_fichaje = ?";
    private static final String SQL_UPDATE = "UPDATE fichajes SET id_empleado = ?, fecha = ?, hora_entrada = ?, hora_salida = ?, modo = ? WHERE id_fichaje = ?";
    private static final String SQL_SELECT = "SELECT * FROM fichajes";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM fichajes WHERE id_fichaje = ?";
    private static final String SQL_SELECT_BY_CARGO = "SELECT * FROM fichajes WHERE id_empleado IN (SELECT id_empleado FROM empleados WHERE email = ?) ORDER BY DATETIME DESC";
    private static final String SQL_SELECT_BY_IDEMP = "SELECT * FROM fichajes WHERE id_empleado = ?";
    
    private conexion con = conexion.getInstancia();

    @Override
    public boolean insertar(fichajesDTO fichaje) {
        try (PreparedStatement ps = con.getCon().prepareStatement(SQL_INSERT)) {
            ps.setInt(1, fichaje.getIdEmpleado());
            ps.setDate(2, fichaje.getFecha());
            ps.setTime(3, fichaje.getHoraEntrada());
            ps.setTime(4, fichaje.getHoraSalida());
            ps.setString(5, fichaje.getModo());
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
    public boolean actualizar(fichajesDTO fichaje) {
        try (PreparedStatement ps = con.getCon().prepareStatement(SQL_UPDATE)) {
            ps.setInt(1, fichaje.getIdEmpleado());
            ps.setDate(2, fichaje.getFecha());
            ps.setTime(3, fichaje.getHoraEntrada());
            ps.setTime(4, fichaje.getHoraSalida());
            ps.setString(5, fichaje.getModo());
            ps.setInt(6, fichaje.getIdFichaje());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public fichajesDTO buscar(Object pk) {
        try (PreparedStatement ps = con.getCon().prepareStatement(SQL_SELECT_BY_ID)) {
            ps.setInt(1, (int) pk);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new fichajesDTO(
                            rs.getInt("id_fichaje"),
                            rs.getInt("id_empleado"),
                            rs.getDate("fecha"),
                            rs.getTime("hora_entrada"),
                            rs.getTime("hora_salida"),
                            rs.getString("modo")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<fichajesDTO> listarTodos() {
        ArrayList<fichajesDTO> lista = new ArrayList<>();
        try (PreparedStatement ps = con.getCon().prepareStatement(SQL_SELECT);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new fichajesDTO(
                        rs.getInt("id_fichaje"),
                        rs.getInt("id_empleado"),
                        rs.getDate("fecha"),
                        rs.getTime("hora_entrada"),
                        rs.getTime("hora_salida"),
                        rs.getString("modo")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    public ArrayList<fichajesDTO> buscarPorCargo(String cargo) {
        ArrayList<fichajesDTO> lista = new ArrayList<>();

        try (PreparedStatement ps = con.getCon().prepareStatement(SQL_SELECT_BY_CARGO)) {
            ps.setString(1, cargo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new fichajesDTO(
                            rs.getInt("id_fichaje"),
                            rs.getInt("id_empleado"),
                            rs.getDate("fecha"),
                            rs.getTime("hora_entrada"),
                            rs.getTime("hora_salida"),
                            rs.getString("modo")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    public fichajesDTO buscarPorIdEmpleado(int idEmpleado) {
        try (PreparedStatement ps = con.getCon().prepareStatement(SQL_SELECT_BY_IDEMP)) {
        	ps.setInt(1, idEmpleado);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new fichajesDTO(
                            rs.getInt("id_fichaje"),
                            rs.getInt("id_empleado"),
                            rs.getDate("fecha"),
                            rs.getTime("hora_entrada"),
                            rs.getTime("hora_salida"),
                            rs.getString("modo")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean finalizarFichaje(int idEmpleado, Time horaSalida) {
        String SQL_UPDATE_HORA_SALIDA = "UPDATE fichajes SET hora_salida = ? WHERE id_empleado = ? AND hora_salida IS NULL";
        try (PreparedStatement ps = con.getCon().prepareStatement(SQL_UPDATE_HORA_SALIDA)) {
            ps.setTime(1, horaSalida);
            ps.setInt(2, idEmpleado);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public ArrayList<Object[]> listarFichajesConNombreEmpleado() {
        String SQL_SELECT_FICHAJES_CON_NOMBRES = 
            "SELECT  e.nombre, f.fecha, f.hora_entrada, f.hora_salida, f.modo " +
            "FROM fichajes f " +
            "JOIN empleados e ON f.id_empleado = e.id_empleado";

        ArrayList<Object[]> lista = new ArrayList<>();
        try (PreparedStatement ps = con.getCon().prepareStatement(SQL_SELECT_FICHAJES_CON_NOMBRES);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Object[] {
                	rs.getString("nombre"),  // Nombre del empleado
                    rs.getDate("fecha"),
                    rs.getTime("hora_entrada"),
                    rs.getTime("hora_salida"),
                    rs.getString("modo")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    public ArrayList<Object[]> listarFichajesOrdenados(String campo, String orden) {
        String SQL_SELECT_ORDENADO = 
            "SELECT e.nombre, f.fecha, f.hora_entrada, f.hora_salida, f.modo " +
            "FROM fichajes f " +
            "JOIN empleados e ON f.id_empleado = e.id_empleado " +
            "ORDER BY " + campo + " " + orden;

        ArrayList<Object[]> lista = new ArrayList<>();
        try (PreparedStatement ps = con.getCon().prepareStatement(SQL_SELECT_ORDENADO);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Object[] {
                    rs.getString("nombre"),
                    rs.getDate("fecha"),
                    rs.getTime("hora_entrada"),
                    rs.getTime("hora_salida"),
                    rs.getString("modo")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    public ArrayList<Object[]> buscarFichajesPorCriterio(String criterio, String valor) {
        String SQL_BUSCAR_CRITERIO = 
            "SELECT e.nombre, f.fecha, f.hora_entrada, f.hora_salida, f.modo " +
            "FROM fichajes f " +
            "JOIN empleados e ON f.id_empleado = e.id_empleado " +
            "WHERE " + criterio + " LIKE ?";

        ArrayList<Object[]> lista = new ArrayList<>();
        try (PreparedStatement ps = con.getCon().prepareStatement(SQL_BUSCAR_CRITERIO)) {
            ps.setString(1, "%" + valor + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Object[] {
                        rs.getString("nombre"),
                        rs.getDate("fecha"),
                        rs.getTime("hora_entrada"),
                        rs.getTime("hora_salida"),
                        rs.getString("modo")
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

}
