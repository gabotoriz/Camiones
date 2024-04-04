package org.example.prueba.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.prueba.DataBase.Conect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Ciudades {
    int IdCiudad;
    String Nombre;
    static Ciudades instancia;

    public ObservableList<Ciudades> getCiudades() {
        Conect conect = Conect.getInstance();
        Connection con = conect.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        ObservableList<Ciudades> ciudades = FXCollections.observableArrayList();
        String sql = "SELECT * FROM CIUDADES";
        try {
            if (con != null) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int idCiudad = rs.getInt(1);
                    String nombre = rs.getString(2);
                    Ciudades ciudad = new Ciudades(idCiudad, nombre);
                    ciudades.add(ciudad);
                }
                con.close();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ciudades;
    }

    public static Ciudades getInstance() {
        if (instancia == null) {
            instancia = new Ciudades();
        }
        return instancia;
    }

    @Override
    public String toString() {
        return getNombre();
    }
}
