package dominio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dominio.Usuario;

public class daoUsuario {

    public Usuario obtenerUsuarioPorNombreUsuario(String nombreUsuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario = null;

        try {
            conn = Conexion.getConexion().getSQLConexion();
            String query = "SELECT * FROM Usuario WHERE nombreUsuario = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, nombreUsuario);
            rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setNombreUsuario(rs.getString("nombreUsuario"));
                usuario.setPass(rs.getString("pass"));
                usuario.setTipoUsuario(rs.getInt("tipoUsuario"));
                usuario.setDni(rs.getString("dni"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return usuario;
    }
    
    public boolean insertarUsuario(Usuario usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean exito = false;

        try {
            conn = Conexion.getConexion().getSQLConexion();
            String query = "INSERT INTO Usuario (nombreUsuario, pass, tipoUsuario, dni, nombre, apellido) VALUES (?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, usuario.getNombreUsuario());
            stmt.setString(2, usuario.getPass());
            stmt.setInt(3, usuario.getTipoUsuario());
            stmt.setString(4, usuario.getDni());
            stmt.setString(5, usuario.getNombre());
            stmt.setString(6, usuario.getApellido());

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                exito = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return exito;
    }
    
}
