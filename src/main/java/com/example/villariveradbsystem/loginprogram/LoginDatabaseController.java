package com.example.villariveradbsystem.loginprogram;

import com.example.villariveradbsystem.App;
import com.example.villariveradbsystem.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginDatabaseController extends App implements Initializable {

    @FXML
    private TableView<LoginDatabase> DATABASE;
    @FXML
    private TableColumn<LoginDatabase, String> USERNAME;
    @FXML
    private TableColumn<LoginDatabase, String> PASSWORD;
    @FXML
    private TableColumn<LoginDatabase, String> FIRSTNAME;
    @FXML
    private TableColumn<LoginDatabase, String> LASTNAME;
    @FXML
    private TableColumn<LoginDatabase, String> ADMIN;

    @FXML
    private TextField textfieldUsername, textfieldPassword, textfieldFirstName, textfieldLastName;
    @FXML
    private ChoiceBox<String> choiceboxAdmin;

    ObservableList<LoginDatabase> listM;
    LoginDatabase infoAccount;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        DefaultLoginDatabase();
    }

    public void buttonRegisterOnAction(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        try(Statement statement = connectDB.createStatement()){

            int admin;
            if(choiceboxAdmin.getValue() =="True"){
                admin = 1;
            }else{
                admin = 0;
            }

            String information = String.format("insert into accounts (username, password, firstname, lastname, admin) " +
                            "values('%s', '%s', '%s', '%s', '%s')",
                    textfieldUsername.getText(), textfieldPassword.getText(),
                    textfieldFirstName.getText(), textfieldLastName.getText(), Integer.toString(admin));

            statement.executeUpdate(information);
            DefaultLoginDatabase();
        }
        catch(Exception f){
            f.printStackTrace();
        }
    }

    public void buttonDeleteOnAction(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        infoAccount = DATABASE.getSelectionModel().getSelectedItem();

        try(Statement statement = connectDB.createStatement()){

            String information = String.format("DELETE from accounts where username = '%s' AND password = '%s' AND firstname = '%s' AND lastname = '%s';",
                    infoAccount.getUsername(), infoAccount.getPassword(), infoAccount.getFirstname(), infoAccount.getLastname());

            statement.executeUpdate(information);
            DefaultLoginDatabase();
        }
        catch(Exception f){
            f.printStackTrace();
        }
    }

    public void DefaultLoginDatabase(){
        USERNAME.setCellValueFactory(new PropertyValueFactory<LoginDatabase,String>("username"));
        PASSWORD.setCellValueFactory(new PropertyValueFactory<LoginDatabase,String>("password"));
        FIRSTNAME.setCellValueFactory(new PropertyValueFactory<LoginDatabase,String>("firstname"));
        LASTNAME.setCellValueFactory(new PropertyValueFactory<LoginDatabase,String>("lastname"));
        ADMIN.setCellValueFactory(new PropertyValueFactory<LoginDatabase,String>("admin"));

        listM = DatabaseConnection.getDatabaseAccounts();
        DATABASE.setItems(listM);

        ObservableList<String> admin = FXCollections.observableArrayList("True", "False");
        choiceboxAdmin.setItems(admin);

        clearValues();
    }
    public void clearValues(){
        textfieldPassword.setText("");
        textfieldUsername.setText("");
        textfieldFirstName.setText("");
        textfieldLastName.setText("");
    }
}
