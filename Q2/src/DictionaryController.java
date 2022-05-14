/*
Author: Roni Alon
Mmn 14 -Q2 This class is the controller of the dictionary
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javax.swing.*;
import java.io.*;


public class DictionaryController {

    @FXML
    private TextArea textArea;

    private Dictionary d;
    private String key;
    private String value;

    public void initialize() {
        JOptionPane.showMessageDialog(null, "Welcome to my dictionary application");
        d = new Dictionary();
    }


    @FXML
    void addItem(ActionEvent event) {
        key = getKey();
        if (key != null) {
            if (!d.checkIfKeyExists(key)) { //if this key doesn't exists - get value and add to dict
                value = getValue();
                if (value != null) {
                    d.addToDict(key, value);
                    JOptionPane.showMessageDialog(null, "The key: " + key + " was successfully added with value: " + value, "SUCCESS ADD", JOptionPane.INFORMATION_MESSAGE);
                    showNewContent();
                }
            } else { //this key already exists - check if edit the value
                checkIfEdit();
            }
        }
    }


    @FXML
    void deleteItem(ActionEvent event) {
        key = getKey();
        if (key != null) {
            if (!d.checkIfKeyExists(key)) { // if this key doesn't exists - can't edit
                JOptionPane.showMessageDialog(null, "The key " + key + " doesn't exists ,Please try again", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                d.deleteFromDict(key); //delete item from tree
            }
            showNewContent(); //show the current content
        }


    }

    @FXML
    void editItem(ActionEvent event) {
        key = getKey();
        if (key != null) {
            if (!d.checkIfKeyExists(key)) { // if this key doesn't exists - can't edit
                JOptionPane.showMessageDialog(null, "The key " + key + " doesn't exists ,Please try again", "Error", JOptionPane.ERROR_MESSAGE);
            } else { //if it does exist
                editDict();
            }
            showNewContent();
        }
    }

    @FXML
    void loadFile(ActionEvent event) {
        loadDictFromFile();
        showNewContent(); // Displays the dictionary's keys and value, after the action occurred.
    }

    @FXML
    void saveFile(ActionEvent event) {
        saveDictToFile();
    }

    @FXML
    void searchItem(ActionEvent event) { //search item by key in dict
        key = getKey();
        if (key != null) {
            if (!d.checkIfKeyExists(key)) {
                JOptionPane.showMessageDialog(null, "The key " + key + " doesn't exists ,Please try again", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "The key: " + key + " has the value: " + d.getValue(key), "Search", JOptionPane.INFORMATION_MESSAGE);
            }
            showNewContent();
        }

    }

    // Updates the content of the dictionary with each dictionary action.
    private void showNewContent() {
        textArea.clear();
        textArea.setText(d.toString());
    }

    //Get from the user the key
    private String getKey() {
        while (true) {
            key = JOptionPane.showInputDialog("Enter the key");
            if (key == null)
                return null;
            if (!key.isEmpty()) {
                return key;
            }
            JOptionPane.showMessageDialog(null, "Bad input - key can't be empty string", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Get from the user the value of key
    private String getValue() {
        while (true) {
            value = JOptionPane.showInputDialog("Enter value of '" + key + "' key");
            if (value == null) return null;
            if (!value.isEmpty()) {
                return value;
            }
            JOptionPane.showMessageDialog(null, "Bad input - value can't be empty string", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //check if the user want to change the value of existed key in dict
    private void checkIfEdit() {
        String[] options = {"Yes", "No"};
        int x = JOptionPane.showOptionDialog(null, "The key: '" + key + "' ,is already added with value : '" + d.getValue(key) + "' ,would you like to edit it instead?",
                "The key is located inside the dictionary - please select an option",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if (x == 0) {
            editDict();
        }
    }

    //edit the value of a given key
    private void editDict() {
        value = getValue();
        if (value != null) {
            d.addToDict(key, value);
            JOptionPane.showMessageDialog(null, "The key: " + key + " was successfully edited with value: " + value, "SUCCESS EDIT", JOptionPane.INFORMATION_MESSAGE);
            showNewContent();
        }

    }

    //returns a chosen file of dict to open
    private File getFile() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Select a file");
        fc.setInitialDirectory(new File("."));
        File file = fc.showOpenDialog(null);
        return file;
    }

    //save the file in chosen dir
    private File saveFile() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Select a file");
        fc.setInitialDirectory(new File("."));
        File file = fc.showSaveDialog(null);
        return file;
    }

    // Load from file functionality void method.
    private void loadDictFromFile() {
        File file = getFile();
        if (file != null) {
            try {
                FileInputStream fi = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fi);
                d.loadDictFromFileStream(ois);
                ois.close();
                fi.close();
            } catch (FileNotFoundException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("The file is not found");
                alert.show();
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("The file hasn't been handled correctly");
                alert.show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    // Save to file functionality void method.
    private void saveDictToFile() {
        File file = saveFile();
        if (file != null) {
            try {
                FileOutputStream fo = new FileOutputStream(file);
                ObjectOutputStream out = new ObjectOutputStream(fo);
                out.writeObject(d.getDictionary());
                out.close();
                fo.close();
            } catch (FileNotFoundException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("The file is not found");
                alert.show();
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("The file hasn't been handled correctly");
                alert.show();
            }
        }
    }


}
