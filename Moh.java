import java.util.ArrayList;

public class Moh {
    private ArrayList<Customer> Recipients = new ArrayList<>();
    // private ArrayList<VC> VCs = new VC<>();

    Moh() {
        System.out.println("Welcome but sorry it seems like you dont have access to this point"); 
    }

    Moh(User admin) {
        System.out.println("Welcome " + admin); 
        Recipients.add(new Customer());
        System.out.println("Recipient in check: " + Recipients.size()); 
    }

    public void addData(Customer data) {
        Recipients.add(data);
    }

    public Customer getRecipient() {                  //fx overload
        return getRecipient(0);
    }
    
    public Customer getRecipient(int index) {
        return Recipients.get(index);
    }

    public Customer searchData(String name) {  // Unique Primary Key
        if (name=="found") {
            System.out.println("Supposed to loop this function till found and return the customer");
            throw new NullPointerException("Customer Class no accessor");
        }
        else {
            System.out.println ("name not found");
            throw new NullPointerException("Receipient Not Found");  
        }
    }

    public void viewData() {}
    public void viewStatistic() {}
    public void setReceipientVC(int VCid, int id) {}

}

