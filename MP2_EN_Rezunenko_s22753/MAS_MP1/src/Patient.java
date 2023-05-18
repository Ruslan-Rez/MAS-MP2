import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Patient extends ObjectPlus{
    private String firstname;
    private String lastname;
    private LocalDate birthDate;
    private String phoneNumber;
    private Set<Visit> visits = new HashSet<>();
    private Set<MedicalRecord> medicalRecords = new HashSet<>();

    public Patient(String firstname, String lastname, LocalDate birthDate, String phoneNumber) {
       setFirstname(firstname);
        setLastname(lastname);
        setBirthDate(birthDate);
        setPhoneNumber(phoneNumber);
    }
    public Set<Visit> getVisits() {
        return Collections.unmodifiableSet(visits);
    }
    public void addVisit(Visit v){
        if (v == null){
            throw  new IllegalArgumentException("visit cannot be null");
        }
        if (this.visits.contains(v)){
            return;
        }
        if (v.getPatient()!= this){
            throw new IllegalArgumentException("visit is not related to this patient");
        }
        this.visits.add(v);
    }
    public void removeVisit(Visit v){
        if (v == null){
            throw  new IllegalArgumentException("visit cannot be null");
        }
        if (!this.visits.contains(v)){
            return;
        }
        this.visits.remove(v);
        v.remove();

    }
public Set<MedicalRecord> getMedicalRecords(){
        return Collections.unmodifiableSet(medicalRecords);
}
public void addMedicalRecord(long recordNumber, Double weight, Double height, Double bloodSugar, LocalDate recordDate){
        MedicalRecord medicalRecord = new MedicalRecord(recordNumber,weight,height,bloodSugar,recordDate,this);
        this.medicalRecords.add(medicalRecord);

}
public void addMedicalRecord(MedicalRecord m){
    if (m == null){
        throw  new IllegalArgumentException("medical record cannot be null");
    }
    if (this.medicalRecords.contains(m)){
        return;
    }
    this.medicalRecords.add(m);

}
public List<MedicalRecord> getMedicalRecordList(){
        return new ArrayList<>(medicalRecords);
    }
public void removeMedicalRecord(MedicalRecord medicalRecord){
        this.medicalRecords.remove(medicalRecord);
}

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        if (birthDate == null){
            throw new IllegalArgumentException("birth date cannot be null");
        }
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public static boolean isValidPhoneNumber(String str)
    {
        Pattern ptrn = Pattern.compile("[0-9]{9}");
        Matcher match = ptrn.matcher(str);
        return (match.find() && match.group().equals(str));
    }

    public void setPhoneNumber(String phoneNumber) {
        if(phoneNumber == null || "".equals(phoneNumber.trim())){
            throw new IllegalArgumentException("Name cannot be null");
        }
        if (!isValidPhoneNumber(phoneNumber)){
            throw new IllegalArgumentException("Not a valid phone number");
        }
        this.phoneNumber = phoneNumber;
    }


}
