/*
Author: Roni Alon
Mmn 14 - Main class includes programs required in clause B and C in Mmn 14
 */

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static final int NUMBER_OF_ELEMENTS = 10;
    public static final int MIN_NUM = 0;
    public static final int MAX_NUM = 100;


    public static void main(String[] args) {
        System.out.println("\n\n*************** STARTING CLAUSE B ***************\n\n");
        clausB();
        System.out.println("\n\n*************** STARTING CLAUSE C ***************\n\n");
        clausC();

    }


    // Function implements the requirements for clause B
    public static void clausB() {
        //Creating the 3 sets
        Set<Integer> setOne = createSet();
        Set<Integer> setTwo = createSet();
        Set<Integer> setThree = createSet();

        //Presenting the content of the sets (using set's toString)
        System.out.println("SetOne:\n" + setOne);
        System.out.println("SetTwo:\n" + setTwo);
        System.out.println("SetThree:\n" + setThree);

        //Union set one and set two, and presenting the result
        setOne.union(setTwo);
        System.out.println("The following set is the union result of Set One and Set Two:\n" + setOne);

        //Intersecting the above result and set Three, and presenting the result
        setOne.intersect(setThree);
        System.out.println("setOne intersect with setThree is:\n" + setOne);

        //Get two numbers from the user, create a set with the inputs numbers, and check if it is a subset
        Integer[] userNumbers;
        String answer;
        userNumbers = getUserNumbers();
        Set<Integer> setFour = new Set<>(userNumbers);
        answer = (setOne.isSubset(setFour)) ? "Yes!" : "No";
        System.out.println("Is set Four subset of Set One?\n" + answer);
        answer = (setTwo.isSubset(setFour)) ? "Yes!" : "No";
        System.out.println("Is set Four subset of Set Two?\n" + answer);
        answer = (setThree.isSubset(setFour)) ? "Yes!" : "No";
        System.out.println("Is set Four subset of Set Three?\n" + answer);

        //Get number from the user and make some actions accordingly
        System.out.println("Please enter an additional number:");
        Integer userNumber = getUserNumber();
        //Check if in set One and present the result
        answer = (setOne.isMember(userNumber)) ? "Yes!" : "No";
        System.out.println("Is the number part of Set One?\n" + answer);
        //Add it to set Two and present result
        setTwo.insert(userNumber);
        System.out.println("SetTwo:\n" + setTwo);
        //Delete it from the set Three
        setThree.delete(userNumber);
        System.out.println("SetThree:\n" + setThree);
    }


    //    // Function implements the requirements for clause C
    public static void clausC() {
        Person[] personArray = new Person[5];
        //Create the persons
        personArray[0] = new Person("0567891", "Roni", "Alon", 1995);
        personArray[1] = new Person("1234560", "Daniel", "Alon", 1991);
        personArray[2] = new Person("00234567", "Zeev", "Alon", 1956);
        personArray[3] = new Person("345678", "Sara", "Paz Alon", 1964);
        personArray[4] = new Person("456798", "Guy", "Ben Haim", 1993);
        //Convert the persons into a set
        Set<Person> personSet = new Set<>(personArray);
        //Prints the set
        System.out.println(personSet);
        //Run the generic minimum function for test
        MinSetItem<Person> minObj = new MinSetItem<>();
        System.out.println("\nThe 'Smallest' person (smallest ID by Lexicographic order) in the set is:\n" + minObj.minInSet(personSet));
    }


    // Function that allocates random values within an input array
    public static void fillArray(ArrayList<Integer> arr) {
        Random r = new Random();
        int counter=0;
        while (counter<NUMBER_OF_ELEMENTS){
            int randNum = r.nextInt(MAX_NUM+1); //random integers from 0 to 100,next int is in range[0,x) when x is param
            if (!arr.contains(randNum)){
                arr.add(randNum);
                counter ++;
            }
        }
    }

    //Supporting function to create a new set
    public static Set<Integer> createSet() {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        fillArray(arr);
        return new Set<>(arr.toArray(new Integer[NUMBER_OF_ELEMENTS]));
    }

    //Supporting function to validate and read the 2 numbers from the user
    public static Integer[] getUserNumbers() {
        Integer[] intArray = new Integer[2];
        System.out.println("Please enter the first number:");
        intArray[0] = getUserNumber();
        System.out.println("Please enter the second number:");
        intArray[1] = getUserNumber();
        return intArray;
    }

    //Supporting function to get and validate an input number of user
    public static Integer getUserNumber() {
        Scanner scan = new Scanner(System.in);
        Integer num = null;
        boolean isValid = true;
        while (isValid) {
            try {
                num = scan.nextInt();
            } catch (InputMismatchException err) {
                System.out.println("please enter valid integer");
                scan.nextLine(); //empty scanner object
                continue;
            }
            if (num > MAX_NUM || num < MIN_NUM) {
                System.out.println("please enter valid integer between 0 to 100");
                continue;
            }

            isValid = false;
        }
        return num;
    }
}

