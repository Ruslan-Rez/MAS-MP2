import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Doctor extends ObjectPlus{
    private String name;
    private String specialization;
    private Double salary;
    private Set<Procedure> procedures = new HashSet<>(); //basic
    public Doctor(String name, String specialization, Double salary) {
        setName(name);
        setSpecialization(specialization);
        setSalary(salary);
    }

    public Set<Procedure> getProcedures(){
        return Collections.unmodifiableSet(procedures);
    }
    public void addProcedure(Procedure p){
        if (p == null){
            throw new IllegalArgumentException("procedure cannot be null");
        }
        if (this.procedures.contains(p)){
            return;
        }
        this.procedures.add(p);
        p.addDoctor(this);

    }
    public void removeProcedure(Procedure p){
        if (p == null){
            throw new IllegalArgumentException("procedure cannot be null");
        }
        if (!this.procedures.contains(p)){
            return;
        }
        procedures.remove(p);
        p.removeDoctor(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null || "".equals(name.trim())){
            throw new IllegalArgumentException("Name cannot be null");
        }
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        if(specialization == null || "".equals(specialization.trim())){
            throw new IllegalArgumentException("Name cannot be null");
        }
        this.specialization = specialization;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        if (salary < 1000) {
            throw new IllegalArgumentException("salary should be higher than 1000");
        }
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "name='" + name + '\'' +
                ", specialization='" + specialization + '\'' +
                ", salary=" + salary +
                ", performes procedures=" + procedures +
                '}';
    }
}
