package dominio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoTipoSeguro {
	public List<TipoSeguro> obtenerTipoSeguros() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<TipoSeguro> tipoSeguros = new ArrayList<TipoSeguro>();

        
        try {
            conn = Conexion.getConexion().getSQLConexion();
            String query = "SELECT * FROM tiposeguros;";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
            	TipoSeguro tipoSeguro = new TipoSeguro();
                tipoSeguro.setId(rs.getInt("idTipo"));
                tipoSeguro.setDescripcion(rs.getString("descripcion"));
                tipoSeguros.add(tipoSeguro);
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

        return tipoSeguros;
    }
	public TipoSeguro obtenerSeguroPorId(int idSeguro) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        TipoSeguro tipoSeguro = null;
        
        try
        {
        	Class.forName("com.mysql.jdbc.Driver");
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
        
        try {
            conn = Conexion.getConexion().getSQLConexion();
            String query = "SELECT * FROM tiposeguros WHERE idTipo = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, idSeguro);
            rs = stmt.executeQuery();

            if (rs.next()) {
                tipoSeguro = new TipoSeguro();
                tipoSeguro.setId(rs.getInt("idTipo"));
                tipoSeguro.setDescripcion(rs.getString("descripcion"));
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

        return tipoSeguro;
    }
}
