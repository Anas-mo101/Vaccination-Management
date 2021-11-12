import java.util.Random;


public class Vaccine {
    private final int NUM_INDEX = 0;
    Csvreader csv = new Csvreader("vac.csv");
    private String vacBatchNo;
    private String recipinetName;
    private String recipinetAge;
    Random rand = new Random();

    Vaccine(){
        vacBatchNo = addVacBatchNO();
        System.out.println(vacBatchNo);
    }
    
    public String addVacBatchNO(){
        int Num;
        int min = 10000000;
        int max = 99999999;
        while(true){
            Num = rand.nextInt((max - min) + 1) + min;
            if(csv.checkData(Integer.toString(Num), NUM_INDEX)){
                break;
            }
        }
        csv.addVac(Integer.toString(Num));
        return Integer.toString(Num);
    }

    public void setrecipinetName(String recipinetName){
        this.recipinetName = recipinetName;
    }

    public void setrecipinetAge(String recipinetAge){
        this.recipinetAge = recipinetAge;
    }

    public String getVacBatchNo(){
        return vacBatchNo;
    }
}
