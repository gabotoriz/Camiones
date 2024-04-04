package org.example.prueba.Controller;

import io.github.palexdev.materialfx.controls.MFXComboBox;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lombok.Getter;
import org.example.prueba.Models.Ciudades;
import org.example.prueba.Models.Viaje;

import java.net.URL;
import java.util.ResourceBundle;

@Getter
public class MainController implements Initializable {
    public TextField JTF_Buscar;
    @FXML
    Button BTN_Buscar;
    @FXML
    MFXComboBox<Ciudades> JCB_CiudadDestino;
    @FXML
    MFXComboBox<Ciudades> JCB_CiudadOrigen;
    @FXML
    TextField JTF_Nombre;
    @FXML
    TextField JTF_Direccion;
    @FXML
    Button BTN_Registrar;

    public void insert() {
        String Nombre = getJTF_Nombre().getText();
        String Direccion = getJTF_Direccion().getText();
        String Origen = getJCB_CiudadOrigen().getSelectedItem().getNombre();
        String Destino = getJCB_CiudadDestino().getSelectedItem().getNombre();
        Viaje viaje = Viaje.getInstance();
        if (Origen != null && Destino != null) {
            viaje.insertToViajes(Nombre, Direccion, Origen, Destino);
        }
        limpiarRegistro();
    }

    public void limpiarRegistro() {
        getJTF_Nombre().setText("");
        getJTF_Direccion().setText("");
        getJCB_CiudadOrigen().setText("");
        getJCB_CiudadDestino().setText("");
    }

    public void limpiarBusqueda() {
        getJTF_Buscar().setText("");
    }

    public void buscar() {
        int IdViaje = Integer.parseInt(getJTF_Buscar().getText());
        Viaje viaje = Viaje.getInstance();
        viaje.selectToViajes(IdViaje);
        limpiarBusqueda();
    }

    public void initCombos() {
        Ciudades ciudades = Ciudades.getInstance();
        ObservableList<Ciudades> list = ciudades.getCiudades();
        this.getJCB_CiudadOrigen().setItems(list);
        this.getJCB_CiudadDestino().setItems(list);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCombos();
    }
}