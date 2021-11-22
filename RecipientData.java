public class RecipientData{
    String id,pass,userName,age,phone,assignedVC,firstVacDate,firstVacStatus,secondVacDate,secondVacStatus;
    ///////////////////getter///////////////////////////////
    public String getId() {
        return id;
    }
    public String getPass() {
        return pass;
    }
    public String getUserName() {
        return userName;
    }
    public String getAge() {
        return age;
    }
    public String getPhone() {
        return phone;
    }
    public String getAssignedVC() {
        return assignedVC;
    }
    public String getFirstVacDate() {
        return firstVacDate;
    }
    public String getFirstVacStatus() {
        return firstVacStatus;
    }
    public String getSecondVacDate() {
        return secondVacDate;
    }
    public String getSecondVacStatus() {
        return secondVacStatus;
    }
    RecipientData(String id,String pass,String userName,String age,String phone,String assignedVC,String firstVacDate,String firstVacStatus,String secondVacDate,String secondVacStatus){
        this.id = id;
        this.pass = pass;
        this.userName = userName;
        this.age = age;
        this.phone = phone;
        this.assignedVC = assignedVC;
        this.firstVacDate = firstVacDate;
        this.firstVacStatus = firstVacStatus;
        this.secondVacDate = secondVacDate;
        this.secondVacStatus = secondVacStatus;
    }
}