import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Visit extends ObjectPlus{
    private long id; //mandatory
    //with an attr
    private Procedure procedure;
    private Patient patient;

    //qualified
    private Ward ward;

    private Double discount; //optional
    private LocalDate dateOfVisit;
    private static String hospital; //class attribute
    public Visit(long id, Procedure procedure, Patient patient, double discount, LocalDate dateOfVisit){
        setId(id);
        setProcedure(procedure);
        setPatient(patient);
        setDiscount(discount);
        setDateOfVisit(dateOfVisit);
        addToExtent();

    }
    //method overloading
    public Visit(long id, Procedure procedure, Patient patient, LocalDate dateOfVisit){
        setId(id);
        setProcedure(procedure);
        setPatient(patient);
        setDateOfVisit(dateOfVisit);
        addToExtent();

    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("id should be positive");
        }
        this.id = id;
    }

    public Ward getWard() {
        return ward;
    }

    public void setWard(Ward ward) {

        if (ward == this.ward){
            return;
        }
        if (this.ward == null && ward!= null){
            this.ward = ward;
            ward.addVisit(this);
        }else  if(this.ward!= null && ward==null){
            Ward tmp = this.ward;
            this.ward = null;
            tmp.removeVisit(this);
        } else if(this.ward!= null && ward!=null){
            Ward tmp = this.ward;
            this.ward = null;
            tmp.removeVisit(this);
            this.ward = ward;
            ward.addVisit(this);
        }
    }

    public Procedure getProcedure() {
        return procedure;
    }
    public Patient getPatient() { return  patient;}
    private void setProcedure(Procedure procedure){
        if (procedure == null){
            throw new IllegalArgumentException("procedure cannot be null");
        }
        this.procedure = procedure;
        procedure.addVisit(this);
    }
    private  void  setPatient(Patient patient){
        if (patient == null){
            throw new IllegalArgumentException("patient cannot be null");
        }
        this.patient = patient;
        patient.addVisit(this);
    }
    public void remove(){
        if (this.patient !=null){
            Patient tmp = this.patient;
            this.patient = null;
            tmp.removeVisit(this);

        }
        if (this.procedure != null){
            Procedure tmp = this.procedure;
            this.procedure = null;
            tmp.removeVisit(this);
        }else if(this.ward!= null && ward!=null){
            Ward tmp = this.ward;
            this.ward = null;
            tmp.removeVisit(this);
            this.ward = ward;
            ward.addVisit(this);
        }
        removeObjectFromExtent(this);

    }


    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        if (discount!=null && discount<0){
            throw new IllegalArgumentException("discount cannot be negative");
        } else if(discount!=null && discount >100){
            throw new IllegalArgumentException("discount cannot be more than 100");
        }
        this.discount = discount;
    }
    public Double getTotal(){ //derived attribute
        if(discount == null){
            return procedure.getPrice();
        } else return procedure.getPrice()-(procedure.getPrice()*discount);
    }

    public LocalDate getDateOfVisit() {
        return dateOfVisit;
    }

    public void setDateOfVisit(LocalDate dateOfVisit) {
        if(dateOfVisit==null){
            throw new IllegalArgumentException("date cannot be null");
        }
        this.dateOfVisit = dateOfVisit;
    }
    public static List<Visit> findByMedication(String med){  //class method
        List<Visit> extent = getExtent(Visit.class);
        return extent.stream()
                .filter(m-> m.getProcedure().getMedication().contains(med))
                .collect(Collectors.toList());
    }

    public static String getHospital() {
        return hospital;
    }

    public static void setHospital(String hospital) {
        if(hospital == null || "".equals(hospital.trim())){
            throw new IllegalArgumentException("Hospital cannot be null");
        }
        Visit.hospital = hospital;
    }

    //method overriding
    @Override
    public String toString() {
        return "Visit{" +
                "id=" + id +
                ", discount=" + discount +
                ", dateOfVisit=" + dateOfVisit +
                ", Total=" + getTotal() +
                ", Hospital = " + getHospital() +
                '}';
    }
}
