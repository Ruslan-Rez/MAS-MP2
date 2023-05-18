import java.time.LocalDate;

public class MedicalRecord extends ObjectPlus{
private long recordNumber;
    private Double weight;
    private Double height;
    private Double bloodSugar;
    private LocalDate recordDate;
    private Patient patient;

    public MedicalRecord(long recordNumber , Double weight, Double height, Double bloodSugar, LocalDate recordDate, Patient patient) {
       setWeight(weight);
        setHeight(height);
        setBloodSugar(bloodSugar);
      setRecordDate(recordDate);
       setPatient(patient);
       addToExtent();
    }

    public long getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(long recordNumber) {
        if (recordNumber < 0) {
            throw new IllegalArgumentException("record number should be positive");
        }
        this.recordNumber = recordNumber;
    }

    public Double getWeight() {

        return weight;
    }

    public void setWeight(Double weight) {
        if (weight < 2) {
            throw new IllegalArgumentException("weight too low");
        }
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        if (height < 0.63) {
            throw new IllegalArgumentException("height too low");
        }
        this.height = height;
    }

    public Double getBloodSugar() {
        return bloodSugar;
    }

    public void setBloodSugar(Double bloodSugar) {
        if (bloodSugar < 0) {
            throw new IllegalArgumentException("bloodSugar should be positive");
        }
        this.bloodSugar = bloodSugar;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDate recordDate) {
        if (recordDate == null){
            throw  new IllegalArgumentException("date cannot be null");
        }
        this.recordDate = recordDate;
    }

    public Patient getPatient() {
        return patient;
    }

    private void setPatient(Patient patient) {
        if (patient == null){
            throw new IllegalArgumentException("patient cannot be null");
        }
        this.patient = patient;
        patient.addMedicalRecord(this);
    }
        public void remove(){
            if (this.patient !=null){
                Patient tmp = this.patient;
                this.patient = null;
                tmp.removeMedicalRecord(this);
            }
            removeObjectFromExtent(this);

    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "weight=" + weight +
                ", height=" + height +
                ", bloodSugar=" + bloodSugar +
                ", recordDate=" + recordDate +
                ", patient=" + patient.getFirstname()+" "+patient.getLastname() +
                '}';
    }
}
