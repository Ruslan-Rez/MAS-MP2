import java.io.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final String FILENAME = "data.dat";
    public static void main(String[] args) throws IOException {
        List<String> medication = new ArrayList<>();
        medication.add("Paracetamol");
        medication.add("Antibiotic");
        medication.add("Penicillin");

        String hospital = "LENOX HILL HOSPITAL";
        Visit.setHospital(hospital);
        Doctor d1 = new Doctor("John Johnson","Anesthesiology",3000.00);
        Doctor d2 = new Doctor("Samantha Jones","Dermatology",4000.00);
        Doctor d3 = new Doctor("George Smith", "Internal medicine", 5000.00);
        Doctor d4 = new Doctor("d4", "Internal medicine", 5000.00);
        Doctor d5 = new Doctor("d5", "Internal medicine", 5000.00);
        Doctor d6 = new Doctor("d6", "Internal medicine", 5000.00);

        Patient pa1 = new Patient("Jay","Keigh",LocalDate.of(2000,10,22),"233090321");
        Patient pa2 = new Patient("Elle","Emayo",LocalDate.of(2010,10,12),"233090321");
        Patient pa3 = new Patient("Axe","Dee",LocalDate.of(1978,12,26),"233698321");
        Procedure p1 = new Procedure(1,"Gland Removal",300.0,medication.get(0),true, Duration.ofHours(3),Duration.ofDays(7),d1);
        Procedure p2  = new Procedure(2,"Arthroscopy",400.0,medication.get(2), false, Duration.ofMinutes(5), null,d1);
        Procedure p3 = new Procedure(3,"Arthrocentesis",500.0,medication.get(2), true,Duration.ofHours(6), Duration.ofHours(6),d2);
        d3.addProcedure(p3);
        Visit v1 = new Visit(1,p1,pa1,0.5, LocalDate.of(2023,10,12));
        Visit v2 = new Visit(2,p2,pa2,LocalDate.of(2023,11,04));
        Visit v3  = new Visit(3, p3,pa3,0.4,LocalDate.of(2023,8,03));

        Ward w1 = new Ward(1,3);
        Ward w2 = new Ward(2,4);
        Visit v44 = new Visit(4,p1,pa1,0.5, LocalDate.of(2023,10,12));
        w1.addVisit(v2);
        w1.addVisit(v1);

        System.out.println(v1.getWard());
        System.out.println(pa1.getVisits());
        p1.removeVisit(v44);
        System.out.println(pa1.getVisits());
        //System.out.println(v44);
        saveAllExtents();
        loadAllExtents();
        p3.addDoctor(d1);

        pa1.addMedicalRecord(1,40.00,2.00,134.00,LocalDate.of(2023,2,12) );
        MedicalRecord med1 = new MedicalRecord(2,45.00,2.00,135.00,LocalDate.of(2023,3,12),pa1);
        pa1.removeMedicalRecord(pa1.getMedicalRecordList().get(1));
       // pa1.removeMedicalRecord(med1);
        System.out.println(pa1.getMedicalRecords());
        //System.out.println(p3.getDoctors());
        //System.out.println(d3);
        System.out.println(d1);
        System.out.println(p3.getDoctors());
        System.out.println(v1.getTotal().toString());
        System.out.println(v2.getTotal().toString());
        System.out.println(Visit.getHospital());
        //System.out.println("Visits where medication used is " + medication.get(2)+ "= " + Visit.findByMedication(medication.get(2)).toString());

        System.out.println(Visit.getExtent(Visit.class));
        System.out.println(Procedure.getExtent(Procedure.class));


    }
    public static void saveAllExtents () throws IOException {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME));) {
            Procedure.saveExtent(oos);
            Visit.saveExtent(oos);
        }
    }

    public static void loadAllExtents () throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME));) {
            Procedure.loadExtent(ois);
            Visit.loadExtent(ois);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
