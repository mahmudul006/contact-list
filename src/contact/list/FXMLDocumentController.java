/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contact.list;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 *
 * @author mahmudulhasan
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField nameField;
    @FXML
    private TextField numberField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField groupAddField;
    ArrayList<Contact> contactList;
    ObservableList<Contact> listOfContacts;
    @FXML
    private TableView<Contact> tableViewField;
    @FXML
    private TableColumn<Contact, String> nameTableField;
    @FXML
    private TableColumn<Contact, String> phoneTableField;
    @FXML
    private TableColumn<Contact, String> emailTableField;
    @FXML
    private TableColumn<Contact, String> addressTableField;
    @FXML
    private TableColumn<Contact, String> groupTableField;
    RandomAccessFile file;
    @FXML
    private Label listCount;
    @FXML
    private Label resultLabel;
    int count, load = 0;

    public void Read(String textFile, ObservableList<Contact> temp, ArrayList<Contact> temps) {
        count = 0;
        try {
            file = new RandomAccessFile(textFile, "r");
            String name = null, phoneNumber = null, email = null, address = null, group = null, line1 = null;
            name = file.readLine();
            for (int i = 1; name != null; i++) {

                phoneNumber = file.readLine();
                email = file.readLine();
                address = file.readLine();
                group = file.readLine();

                Contact acontact = new Contact(name, phoneNumber, email, address, group);
                temp.add(acontact);
                temps.add(acontact);

                name = file.readLine();
                count++;
            }
            listCount.setText("Total number of Contacts : " + count);
            for (int i = 1; i <= count; i++) {
                nameTableField.setCellValueFactory(val -> new SimpleStringProperty(val.getValue().getName()));
                phoneTableField.setCellValueFactory(val -> new SimpleStringProperty(val.getValue().getPhoneNumber()));
                emailTableField.setCellValueFactory(val -> new SimpleStringProperty(val.getValue().getEmail()));
                addressTableField.setCellValueFactory(val -> new SimpleStringProperty(val.getValue().getAddress()));
                groupTableField.setCellValueFactory(val -> new SimpleStringProperty(val.getValue().getGroup()));
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Write(ArrayList<Contact> contactList, String textFile) {

        try {
            file = new RandomAccessFile(textFile, "rw");
            file.setLength(0);
            for (Contact c : contactList) {
                file.seek(file.length());
                file.writeBytes(c.toString());
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        contactList = new ArrayList<>();
        listOfContacts = FXCollections.observableArrayList();
        tableViewField.setItems(listOfContacts);
        Read("contact.txt", listOfContacts, contactList);
    }

    @FXML
    private void saveContact(ActionEvent event) {
       if(nameField.getText().equals("") || nameField.getText().equals("") || emailField.getText().equals("") ||addressField.getText().equals("")|| groupAddField.getText().equals(""))
        {
            resultLabel.setText("All fields are required");
        }
        else {
            Contact show = new Contact(nameField.getText(),
                    numberField.getText(),
                    emailField.getText(),
                    addressField.getText(),
                    groupAddField.getText());

            try {
                RandomAccessFile contactfile = new RandomAccessFile("contact.txt", "rw");
                contactfile.seek(contactfile.length());
                contactfile.writeBytes(show.toString());
            } catch (FileNotFoundException ex) {
                System.out.println("There is no file");
            } catch (IOException ex) {
                System.out.println("There is a problem to write file");
            }

            resultLabel.setText("Save Successfully");
            resetForm();

            initialize(null, null);
        }

    }

    private void resetForm() {
        nameField.setText("");
        numberField.setText("");
        emailField.setText("");
        addressField.setText("");
        groupAddField.setText("");
    }

    @FXML
    private void clearContact(ActionEvent event) {
        resultLabel.setText("");
        resetForm();
        Contact a = tableViewField.getSelectionModel().getSelectedItem();
        int temp = 0;
        for (Contact c : contactList) {
            if (c.equals(a)) {
                contactList.remove(c);
                listOfContacts.remove(c);
                Write(contactList, "contact.txt");
                break;
            }
        }
        initialize(null, null);
    }

    private void displaycontact(Contact loadcontactconsole) {
        nameField.setText(loadcontactconsole.getName());
    }

    @FXML
    private void loadContact(ActionEvent event) {
        resultLabel.setText("");
        Contact a = tableViewField.getSelectionModel().getSelectedItem();
        for (Contact c : contactList) {
            if (c == a) {
                nameField.setText(a.getName());
                numberField.setText(a.getPhoneNumber());
                emailField.setText(a.getEmail());
                addressField.setText(a.getAddress());
                groupAddField.setText(a.getGroup());
            }
        }

    }

}
