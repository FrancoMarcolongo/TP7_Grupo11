package dominio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dominio.Seguro;

public class DaoSeguro {

	public DaoSeguro()
	{
		
	}
	
    public Seguro obtenerSeguroPorId(int idSeguro) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Seguro seguro = null;
        DaoTipoSeguro daoTipoSeguro = new DaoTipoSeguro();
        
        try {
            conn = Conexion.getConexion().getSQLConexion();
            String query = "SELECT * FROM Seguro WHERE idSeguro = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, idSeguro);
            rs = stmt.executeQuery();

            if (rs.next()) {
                seguro = new Seguro();
                seguro.setIdSeguro(rs.getInt("idSeguro"));
                seguro.setDescripcion(rs.getString("descripcion"));
                seguro.setTipo(daoTipoSeguro.obtenerSeguroPorId(rs.getInt("idTipo")));
                seguro.setCostoContratacion(rs.getDouble("costoContratacion"));
                seguro.setCostoAsegurado(rs.getDouble("costoAsegurado"));
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

        return seguro;
    }
    
    public List<Seguro> listarSeguros() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Seguro> listaSeguros = new ArrayList<>();
        DaoTipoSeguro daoTipoSeguro = new DaoTipoSeguro();

        try
        {
        	Class.forName("com.mysql.jdbc.Driver");
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
        
        try {
            conn = Conexion.getConexion().getSQLConexion();
            String query = "SELECT * FROM Seguro";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Seguro seguro = new Seguro();
                seguro.setIdSeguro(rs.getInt("idSeguro"));
                seguro.setDescripcion(rs.getString("descripcion"));
                seguro.setTipo(daoTipoSeguro.obtenerSeguroPorId(rs.getInt("idTipo")));
                seguro.setCostoContratacion(rs.getDouble("costoContratacion"));
                seguro.setCostoAsegurado(rs.getDouble("costoAsegurado"));
                listaSeguros.add(seguro);
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

        return listaSeguros;
    }
    public int obtenerProximoId() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int proximoId = 0;

        try
        {
        	Class.forName("com.mysql.jdbc.Driver");
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
        
        try {
            conn = Conexion.getConexion().getSQLConexion();
            String query = "SHOW TABLE STATUS LIKE 'seguros';";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            if (rs.next()) {
                proximoId = rs.getInt("Auto_increment");
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

        return proximoId;
    }
    
    public boolean insert(Seguro seguro)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		
        try
        {
        	Class.forName("com.mysql.jdbc.Driver");
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
        
		try
		{
			statement = conexion.prepareStatement("INSERT INTO seguros (descripcion,idTipo,costoContratacion,costoAsegurado) VALUES (?,?,?,?);");
			statement.setString(1, seguro.getDescripcion());
			statement.setInt(2, seguro.getTipo().getId());
			statement.setDouble(3, seguro.getCostoContratacion());
			statement.setDouble(4, seguro.getCostoAsegurado());
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isInsertExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return isInsertExitoso;
	}
}
