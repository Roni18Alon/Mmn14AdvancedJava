/*
Author: Roni Alon
Mmn 14 - Ser class implements functionality of sets as required in mmn 14 q1
 */
import java.util.ArrayList;
import java.util.Iterator;

public class Set <E>{

    private final ArrayList<E> setList ;

    //empty constructor -create empty set -empty array list
    public Set(){
        setList = new ArrayList<E>();
    }

    // Requirement 2 - constructor that receives an array and insert the items to the set
    public Set(E[] inputArray){
        setList = new ArrayList<E>();
        for (E item:inputArray) {
            this.insert(item);
        }
    }

    // Requirement 3 - union between this set and given set - will be added to this set
    public void union(Set<E> otherSet){
        Iterator<E> it = otherSet.iterator();
            while (it.hasNext())  {
            E item = it.next();
            this.insert(item); //for each item in other set check if exists in this set, if don't - insert
        }
    }

    // Requirement 4 - intersect between this set and given set - will be shown in  this set
    public void intersect(Set<E> otherSet){
        for(int i = 0; i < this.setList.size(); i++){
            E element = this.setList.get(i);
            if(!otherSet.checkIfExists(element)){ //if this element doesn't exist in other set - delete this item from this set
                this.delete(element);
                i--;
            }
        }
    }

   // Requirement 5 - return true if other set is a subset of this set
    public boolean isSubset(Set<E> otherSet){
        Iterator<E> it = otherSet.iterator();
        while (it.hasNext())  {
            E item = it.next();
            if (!this.checkIfExists(item)){ //if there is an item of other set that doesn't appear in this set - return false
                return false;
            }
        }
        return true;
    }

   // Requirement 6 - check if an item is a member of a set
    public boolean isMember(E item){
        return this.checkIfExists(item);
    }

    //Requirement 7 - Add the new element to the set if it doesn't exist already
    public void insert(E newElement) {
        if (!this.checkIfExists(newElement) ){
            this.setList.add(newElement);
        }
    }

    //Requirement 8 - delete an item from set, if exists
    public void delete(E item) {
       if(this.checkIfExists(item)){ //if exists - delete
           this.setList.remove(item);
       }
    }

    //Requirement 9 - delete an item from set, if exists
    public Iterator<E> iterator(){
        return setList.iterator();
    }

    //Requirement 10 - override toString method, prints the set elements separate by spaces
    public String toString() {
        String retVal = "Elements:\n";
        for (E item : this.setList ) {
            retVal += item.toString()+" ";
        }
        return retVal;
    }

    //Supporting method, checks if an input element is already in the set
    private boolean checkIfExists (E item) {
        for (E element : this.setList) {
            // using equal to compare object like in the instructions
            if (element.equals(item)) {
                return true;
            }
        }
        return false;
    }
}
