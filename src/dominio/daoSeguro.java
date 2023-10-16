package dominio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.Seguro;

public class daoSeguro {

    public Seguro obtenerSeguroPorId(int idSeguro) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Seguro seguro = null;

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
                seguro.setIdTipo(rs.getInt("idTipo"));
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

        try {
            conn = Conexion.getConexion().getSQLConexion();
            String query = "SELECT * FROM Seguro";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Seguro seguro = new Seguro();
                seguro.setIdSeguro(rs.getInt("idSeguro"));
                seguro.setDescripcion(rs.getString("descripcion"));
                seguro.setIdTipo(rs.getInt("idTipo"));
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
}
