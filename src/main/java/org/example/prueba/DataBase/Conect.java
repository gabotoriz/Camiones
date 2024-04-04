package org.example.prueba.DataBase;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Conect {
    static Settings settings = new Settings();
    static Connection connection;
    static Conect instancia;

    public Connection getConnection() {
        try {
            Class.forName(settings.getDriver());
            connection = DriverManager.getConnection(settings.getURL(), settings.getUser(), settings.getPassword());
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropConnection() {
        try {
            getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Conect getInstance() {
        if (instancia == null) {
            instancia = new Conect();
        }
        return instancia;
    }
}
