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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addGrooup(ActionEvent event) {
    }

    @FXML
    private void saveContact(ActionEvent event) {
       
       Contact show = new Contact(nameField.getText(),
               numberField.getText(),
               emailField.getText(),
               addressField.getText(),
               groupAddField.getText());
       System.out.print(show);
        try {
            RandomAccessFile contactfile = new RandomAccessFile ("contact.txt", "rw");
            contactfile.seek(contactfile.length());
            contactfile.writeBytes(show.toString()+ "\n");
        } catch (FileNotFoundException ex) {
            System.out.println("There is no file");
        } catch (IOException ex) {
            System.out.println("There is a problem to write file");
        }
    }
    private void resetForm(){
        nameField.setText("");
        numberField.setText("");
        emailField.setText("");
        addressField.setText("");
        groupAddField.setText("");
    }
    @FXML
    private void clearContact(ActionEvent event) {
        resetForm();
    }

    private void displaycontact(Contact loadcontactconsole){
        nameField.setText(loadcontactconsole.getName());
    }
    
    @FXML
    private void loadContact(ActionEvent event) {
        try {
            RandomAccessFile loadcontact = new RandomAccessFile("contact.txt", "r");
            String name = loadcontact.readLine();
            String number = loadcontact.readLine();
            String email = loadcontact.readLine();
            String address = loadcontact.readLine();
            String group = loadcontact.readLine();
            Contact loadcontactconsole = new Contact(name,number,email,address,group);
            System.out.println(loadcontactconsole);
            displaycontact(loadcontactconsole);
        } catch (FileNotFoundException ex) {
            System.out.println("There is no file");
        } catch (IOException ex){
            
        }
    }
    
}
