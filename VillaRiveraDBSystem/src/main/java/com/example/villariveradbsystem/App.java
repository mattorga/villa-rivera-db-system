package com.example.villariveradbsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class App extends Application {
    private final Stage stageLogin = new Stage();
    private final Stage stageManageAccount = new Stage();
    private final Stage stageAddInquiry = new Stage();
    private final Stage stageDatabase = new Stage();
    private final Stage stageEditInquiry = new Stage();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        try{
            firstRunCheck();
            initLogin();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void initLogin(){
        try{
            URL url = new File("src/main/resources/com/example/villariveradbsystem/loginprogram/loginDB.fxml").toURI().toURL();

            Parent sceneLogin = FXMLLoader.load(url);

            stageLogin.setScene(new Scene(sceneLogin));
            stageLogin.initStyle(StageStyle.UNDECORATED);
            stageLogin.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void initManageAccounts(){
        try{
            URL url = new File("src/main/resources/com/example/villariveradbsystem/loginprogram/manageAccounts.fxml").toURI().toURL();

            Parent sceneManageAccount = FXMLLoader.load(url);

            stageManageAccount.setScene(new Scene(sceneManageAccount));
            stageManageAccount.setTitle("Accounts");
            stageManageAccount.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void initInquiry() {
        try {
            URL url = new File("src/main/resources/com/example/villariveradbsystem/inquiryprogram/main-page.fxml").toURI().toURL();

            Parent sceneAddInquiry = FXMLLoader.load(url);

            stageAddInquiry.setScene(new Scene(sceneAddInquiry));
            stageAddInquiry.initStyle(StageStyle.UNDECORATED);
            stageAddInquiry.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initDatabase(){
        try {
            URL url = new File("src/main/resources/com/example/villariveradbsystem/databaseprogram/DatabaseGUI.fxml").toURI().toURL();

            Parent sceneDatabase = FXMLLoader.load(url);

            stageDatabase.setScene(new Scene(sceneDatabase));
            stageDatabase.setResizable(false);
            stageDatabase.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initEditInquiry(Scene scene){
        try {
            stageEditInquiry.setScene(scene);
            stageEditInquiry.initStyle(StageStyle.UNDECORATED);
            stageEditInquiry.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void firstRunCheck(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyInquiryTable = "CREATE TABLE IF NOT EXISTS villariveradb.Inquiries (\n" +
                "\tfirstName varchar(255),\n" +
                "    lastName varchar(255),\n" +
                "    guestCount int,\n" +
                "    contactNumber varchar(255),\n" +
                "    date varchar(255),\n" +
                "    time varchar(255),\n" +
                "    francis int,\n" +
                "    simon int,\n" +
                "    fatima int,\n" +
                "    miguel int,\n" +
                "    gabriel int,\n" +
                "    2pax int,\n" +
                "    4pax int,\n" +
                "    10pax int\n" +
                "    );";
        String verifyAccountsTable = "CREATE TABLE IF NOT EXISTS villariveradb.accounts (\n" +
                "\tusername varchar(255),\n" +
                "    password varchar(255),\n" +
                "    firstname varchar(255),\n" +
                "    lastname varchar(255),\n" +
                "    admin tinyint\n" +
                "    );";
        String verifyMasterAccount ="SELECT Count(1) from villariveradb.accounts where username='Manager' AND password = 'villariveradbManager' AND admin = '1';";

        String createMasterAccount = "INSERT into villariveradb.accounts (username, password, admin) VALUES ('Manager', 'villariveradbManager', '1');";

        try(Statement statement = connectDB.createStatement()){
            statement.executeUpdate(verifyInquiryTable);
            statement.executeUpdate(verifyAccountsTable);

            ResultSet queryResult = statement.executeQuery(verifyMasterAccount);
            queryResult.next();
            if (queryResult.getInt(1) == 0){
                    statement.executeUpdate(createMasterAccount);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
