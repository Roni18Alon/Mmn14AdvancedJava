/*
Author: Roni Alon
Mmn 14 - Person class implements the ability to compare between two persons by their id num
 */

public class Person implements Comparable<Person>{

    private final String id;
    private final String firstName;
    private final String lastName;
    private final int birthYear;


    public Person(String id,String firstName ,String lastName, int birthYear){
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.birthYear=birthYear;
    }

    //Override compareTo method to compare persons according to the alphabetic order of their id number
    public int compareTo(Person otherPerson) {
        if (this.id.compareTo(otherPerson.id)>0) { //this is bigger than other
            return 1;
        } else if (this.id.compareTo(otherPerson.id)==0) { //this is equals than other
            return 0;
        }
        return -1; //this is smaller than other
    }

    public String toString(){
        return "Name:" + this.firstName + " " + this.lastName + " ,ID:" + this.id + " ,YEAR OF BIRTH: " + this.birthYear +"\n";
    }



}
