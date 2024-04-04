package org.example.prueba.Models;

import javafx.scene.control.Alert;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.prueba.DataBase.Conect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class Viaje {
    int IdViaje;
    String Nombre;
    String Direccion;
    String CiudadOrigen;
    String CiudadDestino;
    static Viaje instance;

    public void insertToViajes(String Nombre, String Direccion, String CiudadOrigen, String CiudadDestino) {
        Conect conect = Conect.getInstance();
        Connection con = conect.getConnection();
        PreparedStatement preparedStatement;
        String sql = "INSERT INTO VIAJES (Nombre,Direccion, CiudadOrigen, CiudadDestino) VALUES (?,?,?,?)";
        try {
            if (con != null) {
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, Nombre);
                preparedStatement.setString(2, Direccion);
                preparedStatement.setString(3, CiudadOrigen);
                preparedStatement.setString(4, CiudadDestino);
                preparedStatement.execute();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Registro exitoso");
                alert.setContentText("Su viaje fue agendado con exito");
                alert.show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void selectToViajes(int IdViaje) {
        Conect conect = Conect.getInstance();
        Connection con = conect.getConnection();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String sql = "SELECT *from viajes where idViaje=?";
        try {
            if (con != null) {
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setInt(1, IdViaje);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int Id = resultSet.getInt(1);
                    String Name = resultSet.getString(2);
                    String Direction = resultSet.getString(3);
                    String Origin = resultSet.getString(4);
                    String Destiny = resultSet.getString(5);
                    Viaje viaje = new Viaje(Id, Name, Direction, Origin, Destiny);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Usuario Encontrado");
                    alert.setContentText("Viaje: " + viaje.getIdViaje() + "\nNombre: " + viaje.getNombre() + "\nDireccion: " + viaje.getDireccion() + "\nCiudad Origen: " + viaje.getCiudadOrigen() + "\nCiudad Destino: " + viaje.getCiudadDestino());
                    alert.show();

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static Viaje getInstance() {
        if (instance == null) {
            instance = new Viaje();
        }
        return instance;
    }


}