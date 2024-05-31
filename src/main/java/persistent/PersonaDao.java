package persistent;

import model.IPersona;
import model.Persona;
import model.PersonaProxy;
import model.Telefono;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class PersonaDao {
    private static final String URL = "jdbc:mysql://localhost:3306/tp_proxy";
    private static final String SELECT_NOMBRE_PERSONA_ID = "SELECT nombre FROM personas WHERE personas.id = ?";
    private static final String SELECT_TELEFONOS_PERSONA_ID = "SELECT personas.nombre, telefonos.numero " +
                                                                "FROM personas " +
                                                                "JOIN telefonos ON personas.id = telefonos.idPersona " +
                                                                "WHERE personas.id = ?";
    private Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, "root", "");
    }
    public IPersona personaPorId(int id) {
        try (Connection conn = obtenerConexion();
             PreparedStatement statement =
                     conn.prepareStatement(SELECT_NOMBRE_PERSONA_ID);) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            String nombrePersona = null;
            while (result.next()) {
                nombrePersona = result.getString(1);
            }
            return new PersonaProxy(id, new Persona(id,nombrePersona,new HashSet<>()),this);
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Persona telefonosPorIdPersona(int id){
        try (Connection conn = obtenerConexion();
             PreparedStatement statement =
                     conn.prepareStatement(SELECT_TELEFONOS_PERSONA_ID);) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            String nombrePersona = null;
            Set<Telefono> telefonos = new HashSet<Telefono>();
            while (result.next()) {
                nombrePersona = result.getString(1);
                telefonos.add(new Telefono(result.getString(2)));
            }
            return new Persona(id, nombrePersona, telefonos);
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
