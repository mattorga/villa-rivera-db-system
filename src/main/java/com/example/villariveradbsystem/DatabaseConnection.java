package com.example.villariveradbsystem;

import com.example.villariveradbsystem.databasegui.DatabaseInquiries;
import com.example.villariveradbsystem.loginprogram.LoginDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DatabaseConnection {
    public static Connection databaseLink;

    public static Connection getConnection(){
        String databaseName = "villariveradb";
        String databaseUser = "root";
        String databasePassword = "Zeus_3245";

        String url = "jdbc:mysql://localhost/" + databaseName;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
            return databaseLink;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static ObservableList<DatabaseInquiries> getDataInquiries(){
        Connection connect = getConnection();
        ObservableList<DatabaseInquiries> list = FXCollections.observableArrayList();

        try{
            PreparedStatement statement = connect.prepareStatement("select * from villariveradb.inquiries");
            ResultSet result = statement.executeQuery();

            while (result.next()){
                list.add(new DatabaseInquiries(result.getString("firstName"),result.getString("lastName"),Integer.parseInt(result.getString("guestCount")),
                        result.getString("contactNumber"), result.getString("date"),result.getString("time"),Integer.parseInt(result.getString("francis")),
                        Integer.parseInt(result.getString("simon")),Integer.parseInt(result.getString("fatima")),Integer.parseInt(result.getString("miguel")),
                        Integer.parseInt(result.getString("gabriel")),Integer.parseInt(result.getString("2pax")),Integer.parseInt(result.getString("4pax")),
                        Integer.parseInt(result.getString("10pax"))));
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public static ObservableList<DatabaseInquiries> getSortedDataInquiries(String id){
        Connection connect = getConnection();
        ObservableList<DatabaseInquiries> list = FXCollections.observableArrayList();

        String format = String.format("SELECT * FROM villariveradb.inquiries ORDER BY %s;", id);

        try{
            PreparedStatement statement = connect.prepareStatement(format);
            ResultSet result = statement.executeQuery();

            while (result.next()){
                list.add(new DatabaseInquiries(result.getString("firstName"),result.getString("lastName"),Integer.parseInt(result.getString("guestCount")),
                        result.getString("contactNumber"), result.getString("date"),result.getString("time"),Integer.parseInt(result.getString("francis")),
                        Integer.parseInt(result.getString("simon")),Integer.parseInt(result.getString("fatima")),Integer.parseInt(result.getString("miguel")),
                        Integer.parseInt(result.getString("gabriel")),Integer.parseInt(result.getString("2pax")),Integer.parseInt(result.getString("4pax")),
                        Integer.parseInt(result.getString("10pax"))));
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public static ObservableList<DatabaseInquiries> getSearchDataInquiries(String firstname, String lastname){
        Connection connect = getConnection();
        ObservableList<DatabaseInquiries> list = FXCollections.observableArrayList();

        String format = String.format("SELECT * FROM villariveradb.inquiries where firstname = '%s' AND lastname = '%s';", firstname, lastname);

        try{
            PreparedStatement statement = connect.prepareStatement(format);
            ResultSet result = statement.executeQuery();

            while (result.next()){
                list.add(new DatabaseInquiries(result.getString("firstName"),result.getString("lastName"),Integer.parseInt(result.getString("guestCount")),
                        result.getString("contactNumber"), result.getString("date"),result.getString("time"),Integer.parseInt(result.getString("francis")),
                        Integer.parseInt(result.getString("simon")),Integer.parseInt(result.getString("fatima")),Integer.parseInt(result.getString("miguel")),
                        Integer.parseInt(result.getString("gabriel")),Integer.parseInt(result.getString("2pax")),Integer.parseInt(result.getString("4pax")),
                        Integer.parseInt(result.getString("10pax"))));
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public static ObservableList<DatabaseInquiries> getDeleteDataInquiries(String firstname, String lastname, String date)  {
        Connection connect = getConnection();
        ObservableList<DatabaseInquiries> list = FXCollections.observableArrayList();

        String format = String.format("DELETE from villariveradb.inquiries where firstname = '%s' AND lastname = '%s' AND date = '%s';",
                firstname, lastname, date);

        try{
            Statement deleteStatement = connect.createStatement();
            deleteStatement.executeUpdate(format);

            PreparedStatement statement = connect.prepareStatement("select * from villariveradb.inquiries");
            ResultSet result = statement.executeQuery();

            while (result.next()){
                list.add(new DatabaseInquiries(result.getString("firstName"),result.getString("lastName"),Integer.parseInt(result.getString("guestCount")),
                        result.getString("contactNumber"), result.getString("date"),result.getString("time"),Integer.parseInt(result.getString("francis")),
                        Integer.parseInt(result.getString("simon")),Integer.parseInt(result.getString("fatima")),Integer.parseInt(result.getString("miguel")),
                        Integer.parseInt(result.getString("gabriel")),Integer.parseInt(result.getString("2pax")),Integer.parseInt(result.getString("4pax")),
                        Integer.parseInt(result.getString("10pax"))));
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public static ObservableList<LoginDatabase> getDatabaseAccounts(){
        Connection connect = getConnection();

        ObservableList<LoginDatabase> list = FXCollections.observableArrayList();

        try{
            PreparedStatement statement = connect.prepareStatement("select * from accounts");
            ResultSet result = statement.executeQuery();

            while (result.next()){
                list.add(new LoginDatabase(result.getString("username"),result.getString("password"),
                        result.getString("firstname"), result.getString("lastname"),result.getString("admin")));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}

