package com.example.villariveradbsystem.loginprogram;

import com.example.villariveradbsystem.App;
import com.example.villariveradbsystem.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController extends App {
    @FXML
    private Label loginErrorLabel;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;


    /**
     * Closes the program upon clicking "Cancel Button"
     */
    public void cancelButtonOnAction(ActionEvent e){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Displays an error
     */
    public void loginButtonOnAction(ActionEvent e) throws Exception{
        if (!usernameTextField.getText().isBlank() && !passwordPasswordField.getText().isBlank()){
            validateLogin();
        }
        else{
            loginErrorLabel.setText("Please enter credentials!");
        }
    }

    public void validateLogin(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM accounts WHERE username = '" + usernameTextField.getText() + "'" +
                " AND password = '" + passwordPasswordField.getText() + "'";

        try(Statement statement = connectDB.createStatement()){
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()){
                if (queryResult.getInt(1) == 1){

                    Stage stage = (Stage)cancelButton.getScene().getWindow();
                    stage.close();

                    initInquiry();
                }else {
                    loginErrorLabel.setText("Invalid Login. Please try again.");
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void buttonManageAccountsOnAction(ActionEvent e){
        validateAdmin();
    }

    public void validateAdmin(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = String.format("SELECT count(1) FROM accounts WHERE username = '%s' AND password ='%s' AND admin = '1';",
                usernameTextField.getText(), passwordPasswordField.getText());

        try(Statement statement = connectDB.createStatement()){
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()){
                if (queryResult.getInt(1) == 1){
                    initManageAccounts();
                }else {
                    loginErrorLabel.setText("Invalid Login. Please try again.");
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}