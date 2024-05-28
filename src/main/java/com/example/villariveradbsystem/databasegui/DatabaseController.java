package com.example.villariveradbsystem.databasegui;

import com.example.villariveradbsystem.App;
import com.example.villariveradbsystem.DatabaseConnection;
import com.example.villariveradbsystem.inquiryprogram.InquiryEditController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class DatabaseController extends App implements Initializable{

    int ActiveFunction = 0;

    @FXML
    private TableView<DatabaseInquiries> DATABASE;
    @FXML
    private TableColumn<DatabaseInquiries, String> CONTACT_NUMBER;
    @FXML
    private TableColumn<DatabaseInquiries, Integer> COUNT;
    @FXML
    private TableColumn<DatabaseInquiries, String> DATE;
    @FXML
    private TableColumn<DatabaseInquiries, Integer> FATIMA;
    @FXML
    private TableColumn<DatabaseInquiries, Integer> FRANCIS;
    @FXML
    private TableColumn<DatabaseInquiries, Integer> GABRIEL;
    @FXML
    private TableColumn<DatabaseInquiries, Integer> MIGUEL;
    @FXML
    private TableColumn<DatabaseInquiries, String> FIRSTNAME;
    @FXML
    private TableColumn<DatabaseInquiries, String> LASTNAME;
    @FXML
    private TableColumn<DatabaseInquiries, Integer> PAX10;
    @FXML
    private TableColumn<DatabaseInquiries, Integer> PAX2;
    @FXML
    private TableColumn<DatabaseInquiries, Integer> PAX4;
    @FXML
    private TableColumn<DatabaseInquiries, Integer> SIMON;
    @FXML
    private TableColumn<DatabaseInquiries, String> TIME;

    @FXML
    private Button buttonSignout, buttonSort, buttonFilter, buttonSearch, buttonEdit, buttonDelete;
    @FXML
    private ChoiceBox<String> choiceSort, choiceFilter1, choiceFilter2;
    @FXML
    private TextField textfieldSearch;
    @FXML
    private Line lineSearch;

    ObservableList<DatabaseInquiries> listM, listSorted, listSearch, listDelete;
    DatabaseInquiries infoPerson;

    @FXML
    private TextField inputCustomerName;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        DefaultDatabase();
    }

    public void buttonSignoutOnAction(ActionEvent e){
        Stage stage = (Stage) buttonSignout.getScene().getWindow();
        stage.close();
    }

    public void buttonEditOnAction(ActionEvent e) {
        buttonEdit.setStyle("-fx-text-fill: #eaeae9");

        infoPerson = DATABASE.getSelectionModel().getSelectedItem();
        buttonDelete.setStyle("*database.css;");

        ActiveFunction = 1;
    }

    public void buttonDeleteOnAction(ActionEvent e) {
        buttonDelete.setStyle("-fx-text-fill: #eaeae9");

        infoPerson = DATABASE.getSelectionModel().getSelectedItem();
        buttonEdit.setStyle("*database.css;");

        ActiveFunction = 2;
    }

    public void buttonSearchOnAction(ActionEvent e){
        clearInputFields();

        textfieldSearch.setVisible(true);
        lineSearch.setVisible(true);
        buttonSearch.setStyle("-fx-text-fill: #eaeae9");

        ActiveFunction = 3;
    }

    public void buttonFilterOnAction(ActionEvent e){
        clearInputFields();

        choiceFilter1.setVisible(true);
        choiceFilter2.setVisible(true);
        buttonFilter.setStyle("-fx-text-fill: #eaeae9");

        ActiveFunction = 4;
    }

    public void buttonSortOnAction(ActionEvent e){
        clearInputFields();

        choiceSort.setVisible(true);
        buttonSort.setStyle("-fx-text-fill: #eaeae9");

        ActiveFunction = 5;
    }

    public void buttonApplyOnAction(ActionEvent e){
        switch(ActiveFunction){
            case 1:
                try {
                    URL url = new File("src/main/resources/com/example/villariveradbsystem/inquiryprogram/edit-page.fxml").toURI().toURL();
                    FXMLLoader loader = new FXMLLoader(url);
                    Parent root = loader.load();

                    String name = infoPerson.getFirstname() + " " + infoPerson.getLastname();
                    String count = Integer.toString(infoPerson.getGuessCount());
                    String number = infoPerson.getContactNumber();

                    String date = infoPerson.getDate();
                    String time = infoPerson.getTime();

                    String francis = Integer.toString(infoPerson.getFrancis());
                    String simon = Integer.toString(infoPerson.getSimon());
                    String fatima = Integer.toString(infoPerson.getFatima());
                    String miguel = Integer.toString(infoPerson.getMiguel());
                    String gabriel = Integer.toString(infoPerson.getGabriel());

                    String PAX2 = Integer.toString(infoPerson.getPax2());
                    String PAX4 = Integer.toString(infoPerson.getPax4());
                    String PAX10 = Integer.toString(infoPerson.getPax18());


                    InquiryEditController inquiryEditController = loader.getController();
                    inquiryEditController.setEditParameters(name, count, number, date, time,
                            francis, simon, fatima, miguel, gabriel, PAX2, PAX4, PAX10);

                    Scene scene = new Scene(root);
                    initEditInquiry(scene);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                break;
            case 2: //remove
                listDelete = DatabaseConnection.getDeleteDataInquiries(infoPerson.getFirstname(), infoPerson.getLastname(), infoPerson.getDate());
                DATABASE.setItems(listDelete);
                clearInputFields();
                break;
            case 3:
                String[] name = textfieldSearch.getText().split(" ");
                String firstname, lastname;

                firstname = name[0];
                lastname = name[1];

                listSearch = DatabaseConnection.getSearchDataInquiries(firstname, lastname);
                DATABASE.setItems(listSearch);
                break;
            case 4:
                break;
            case 5: //Sort
                String id = choiceSort.getValue();
                listSorted = DatabaseConnection.getSortedDataInquiries(id);
                DATABASE.setItems(listSorted);
                break;
            default:
                break;
        }
    }

    public void buttonRefreshOnAction(ActionEvent e){
        DefaultDatabase();
    }

    public void DefaultDatabase(){
        FRANCIS.setCellValueFactory(new PropertyValueFactory<DatabaseInquiries,Integer>("francis"));
        SIMON.setCellValueFactory(new PropertyValueFactory<DatabaseInquiries,Integer>("simon"));
        FATIMA.setCellValueFactory(new PropertyValueFactory<DatabaseInquiries,Integer>("fatima"));
        MIGUEL.setCellValueFactory(new PropertyValueFactory<DatabaseInquiries,Integer>("miguel"));
        GABRIEL.setCellValueFactory(new PropertyValueFactory<DatabaseInquiries,Integer>("gabriel"));
        PAX2.setCellValueFactory(new PropertyValueFactory<DatabaseInquiries,Integer>("pax2"));
        PAX4.setCellValueFactory(new PropertyValueFactory<DatabaseInquiries,Integer>("pax4"));
        PAX10.setCellValueFactory(new PropertyValueFactory<DatabaseInquiries,Integer>("pax18"));
        COUNT.setCellValueFactory(new PropertyValueFactory<DatabaseInquiries,Integer>("guessCount"));
        FIRSTNAME.setCellValueFactory(new PropertyValueFactory<DatabaseInquiries,String>("firstname"));
        LASTNAME.setCellValueFactory(new PropertyValueFactory<DatabaseInquiries,String>("lastname"));
        CONTACT_NUMBER.setCellValueFactory(new PropertyValueFactory<DatabaseInquiries,String>("contactNumber"));
        DATE.setCellValueFactory(new PropertyValueFactory<DatabaseInquiries,String>("date"));
        TIME.setCellValueFactory(new PropertyValueFactory<DatabaseInquiries,String>("time"));

        listM = DatabaseConnection.getDataInquiries();
        DATABASE.setItems(listM);

        ObservableList<String> sort = FXCollections.observableArrayList("Firstname", "Lastname", "Guesscount", "Date", "Time");
        choiceSort.setItems(sort);

        clearInputFields();
    }

    public void clearInputFields(){
        buttonDelete.setStyle("*database.css;");

        choiceSort.setVisible(false);
        buttonSort.setStyle("*database.css;");

        choiceFilter1.setVisible(false);
        choiceFilter2.setVisible(false);
        buttonFilter.setStyle("*database.css;");

        textfieldSearch.setVisible(false);
        lineSearch.setVisible(false);
        buttonSearch.setStyle("*database.css;");

        ActiveFunction = 0;
    }
}