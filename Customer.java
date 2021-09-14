
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Customer {
    private String custName;
    private String custPhone;
    private String password;
    private String custStatus; // Saves the customer's name, phone number, password, status into the
                               // "customers.csv" file

    public void saveCustomertoFile() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your name:  ");
        this.custName = input.nextLine();

        System.out.println("Enter your phone number: ");
        this.custPhone = input.nextLine();

        System.out.println("Enter a password: ");
        this.password = input.nextLine();

        this.custStatus = "Normal"; // sets custStatus to "Normal"

        // writes to the file "customer.csv"
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("customers.csv", true));
            writer.append('\n' + custName + ',' + custPhone + ',' + password + ',' + custStatus);
            writer.close();
        } catch (IOException ex) {
            System.out.println("No file found.");
        }
    }

    // Reads the "customers.csv" file and returns the values customer.

    public static List<List<String>> ReadCustomerFromFile() {
        File file = new File("customers.csv");
        BufferedReader readCSV = null;
        List<List<String>> customer = new ArrayList<>(); // Creates list of list of strings to store inside an array

        // reads from the "customers.csv" file
        try {
            FileReader fr = new FileReader(file);
            readCSV = new BufferedReader(fr);

            String line;

            while ((line = readCSV.readLine()) != null) { // Stores the value of customers inside an array
                String[] values = line.trim().split(",");
                customer.add(Arrays.asList(values));
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File:" + file.toString() + " is not found!");
        } catch (IOException ex) {
            System.out.println("Cannot read to file: " + file.toString());
        } catch (NullPointerException ex) {
        } // File was never opened
        return customer;
    }

    // Returns the signInValue to true when the user checks in. Also checks if the
    // user has signed in the system.

    public boolean CustomerSignIn() {
        boolean signInValue = false;

        Scanner input = new Scanner(System.in);
        do {
            System.out.println("Sign In Menu\n");
            System.out.print("Enter your name: ");
            this.custName = input.nextLine();

            System.out.println("Enter your phone number: ");
            this.custPhone = input.nextLine();

            System.out.println("Enter your password: ");
            this.password = input.nextLine();

            for (int i = 1; i < ReadCustomerFromFile().size(); i++) {

                // checks if the typed name, password, phone number is in the file or not
                // changes signInValue to true when found
                signInValue = (ReadCustomerFromFile().get(i).get(0).equals(custName))
                        & (ReadCustomerFromFile().get(i).get(1).equals(custPhone))
                        & (ReadCustomerFromFile().get(i).get(2).equals(password));

                if (signInValue == true) {
                    break;
                }
            }

            int fileLine = 0;

            for (int i = 0; i < ReadCustomerFromFile().size(); i++) {
                if (ReadCustomerFromFile().get(i).get(0).equals(custPhone)) {
                    fileLine = i;
                    break;
                }
            }

            this.custStatus = ReadCustomerFromFile().get(fileLine).get(3);

            if (signInValue == true) {
                System.out.println("You have succesfully signed in!");
            } else {
                System.out.println("Invalid input. Try signing in again. \n");
            }

        } while (signInValue != true);

        // if customer exit return this
        return signInValue;
    }

    public void ViewCustomerStatus() {

        int customerLine = 0;

        // reads from the file while checking if custName is the same in the
        // "customers.csv" file
        for (int i = 0; i < ReadCustomerFromFile().size(); i++) {
            if (ReadCustomerFromFile().get(i).get(0).equals(custName)) {
                customerLine = i;
                break;
            }
        }

        // sets the custName and custStatus after finding specific line from
        // "customers.csv"file
        custName = ReadCustomerFromFile().get(customerLine).get(0);
        custStatus = ReadCustomerFromFile().get(customerLine).get(3);
        System.out.println("Status\n" + custName + ": " + custStatus);
    }
}
