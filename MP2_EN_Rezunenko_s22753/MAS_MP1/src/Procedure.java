import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class Procedure extends ObjectPlus { //basic association
    private static List<Procedure> extent = new ArrayList<>();
    private long id;
    private String name;
    private Double price;
    private List<String> medication = new ArrayList<>(); //A multi-value attribute
    private ProcedureDetails procedureDetails; // complex attribute


    private Set<Doctor> doctors = new HashSet<>(); //basic
    private Set<Visit> visits = new HashSet<>(); //attribute

    public Procedure(long id,String name,Double price, String medication , boolean requiresAnesthesia, Duration procedureLength, Duration recoveryLength, Doctor doctor){
        setId(id);
        setName(name);
        setPrice(price);
        addMedication(medication);
        setProcedureDetails(requiresAnesthesia,procedureLength,recoveryLength);
        addDoctor(doctor);
        addToExtent();
    }
    public String getName() {
        return name;
    }
    public long getId() {
        return id;
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
        if (v.getProcedure()!= this){
            throw new IllegalArgumentException("visit is not related to this procedure");
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
    public void setId(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("id should be positive");
        }
        this.id = id;
    }
    public void setName(String name) {
        if(name == null || "".equals(name.trim())){
            throw new IllegalArgumentException("Name cannot be null");
        }
        this.name = name;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        if (price < 0) {
            throw new IllegalArgumentException("price should be positive");
        }
        this.price = price;
    }
    public List<String> getMedication (){
        return Collections.unmodifiableList(medication);
    }
    public void addMedication(String med){
        if(med == null || "".equals(med.trim())){
            throw new IllegalArgumentException("List cannot be empty");
        }
        this.medication.add(med);
    }
    public void removeMedication(String med){
        if(this.medication.size()<2){
            throw new IllegalArgumentException();
        }
        this.medication.remove(med);
    }

    public ProcedureDetails getProcedureDetails() {
        return procedureDetails;
    }

    public void setProcedureDetails(boolean requiresAnesthesia, Duration procedureLength, Duration recoveryLength) {
        this.procedureDetails = new ProcedureDetails(requiresAnesthesia,procedureLength,recoveryLength);
    }
    public Set<Doctor> getDoctors(){
        return  Collections.unmodifiableSet(doctors);
    }
    public void addDoctor(Doctor d){
        if (d == null){
            throw new IllegalArgumentException("doctor cannot be null");
        }
        if(this.doctors.contains(d)){
            return;
        }
        if (this.doctors.size()>4){
            throw new IllegalArgumentException("cannot add more than 5 doctors");
        }
        this.doctors.add(d);
        d.addProcedure(this);
    }
    public void removeDoctor(Doctor d){
        if (d == null){
            throw new IllegalArgumentException("doctor cannot be null");
        }
        if(!this.doctors.contains(d)){
            return;
        } else if(doctors.size()<2){
            throw new IllegalArgumentException("cannot remove doctor");
        }
        this.doctors.remove(d);
        d.removeProcedure(this);
    }

    @Override
    public String toString() {
        return "Procedure{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", medication=" + medication +
                ", procedureDetails=" + procedureDetails +
                ", visits=" + visits +
                '}';
    }
}
