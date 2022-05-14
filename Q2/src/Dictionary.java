/*
Author: Roni Alon
Mmn 14 -Q2 This class is a dictionary that implements with TreeMap- to keep the lexicographic order of keys in dict
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public class Dictionary implements Serializable {

    private TreeMap<String, String> dictMap;

    //constructor - create empty dict- empty tree map
    public Dictionary() {
        dictMap = new TreeMap<String, String>();
    }

    public TreeMap<String, String> getDictionary() {
        return dictMap;
    }

    public String getValue(String key) {
        return dictMap.getOrDefault(key, "this key doesn't exists");
    }

    //add to dict - key&value
    public void addToDict(String key, String value) {
        dictMap.put(key, value); //put method of tree map will change the value if the key already exists
    }

    //if this key exists - return true ,else - false.I assume that for example the keys  roni and Roni aren't the same
    public boolean checkIfKeyExists(String key) {
        return dictMap.containsKey(key);
    }

    //delete a key-value from dict in given key
    public void deleteFromDict(String key) {
        if (dictMap.size() != 0) {
            dictMap.remove(key);
        }
    }

    public String toString() {
        String dictStr = "";
        if (dictMap.size() == 0) {
            dictStr = "This dictionary is EMPTY";
        } else {
            for (Map.Entry<String, String> entry : dictMap.entrySet()) {
                dictStr += entry.getKey() + " -> " + entry.getValue() + "\n";
            }
        }
        return dictStr;
    }

    public void loadDictFromFileStream(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        if (ois != null) {
            dictMap = (TreeMap<String, String>) ois.readObject();
        }
    }


}
