package com.example.estoquejava.gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class TelaPrincipalController implements Initializable {

    @FXML
    private BorderPane border;

    @FXML
    private TreeTableColumn<String, String> coluna;

    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem telaPrincipal;

    @FXML
    private TreeTableView<String> treeTableView;

    @FXML
    private Button voltar;


    TreeItem<String> root = new TreeItem<>("Produtos");
    TreeItem<String> item1_1 = new TreeItem<>("Peito de Frango 1kg");
    TreeItem<String> item1_2 = new TreeItem<>("Sobrecoxas de Frango 1kg");
    TreeItem<String> parent_1 = new TreeItem<>("Carnes");

    TreeItem<String> item2_1 = new TreeItem<>("Peito de Frango 1kg");
    TreeItem<String> item2_2 = new TreeItem<>("Sobrecoxas de Frango 1kg");
    TreeItem<String> parent_2 = new TreeItem<>("Frios");





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        parent_1.getChildren().setAll(item1_1, item1_2);
        parent_2.getChildren().setAll(item2_1, item2_2);

        root.getChildren().setAll(parent_1, parent_2);

        coluna.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<String, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<String, String> param) {
                return new SimpleStringProperty(param.getValue().getValue());
            }
        });

        treeTableView.setRoot(root);
    }
}
