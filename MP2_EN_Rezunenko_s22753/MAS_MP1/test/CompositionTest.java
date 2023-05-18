import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class CompositionTest {
    @Test
    void create(){
        Patient pa1 = new Patient("Jay","Keigh", LocalDate.of(2000,10,22),"233090321");
        pa1.addMedicalRecord(1,40.00,2.00,134.00,LocalDate.of(2023,2,12) );
        Assert.assertEquals(1,pa1.getMedicalRecords().size());
        Assert.assertThrows(IllegalArgumentException.class,()->pa1.addMedicalRecord(null));
        MedicalRecord med1 = new MedicalRecord(2,45.00,2.00,135.00,LocalDate.of(2023,3,12),pa1);
        Assert.assertEquals(2,pa1.getMedicalRecords().size());
        Assert.assertEquals(pa1,med1.getPatient());
    }
    @Test
    void remove(){
        Patient pa1 = new Patient("Jay","Keigh", LocalDate.of(2000,10,22),"233090321");
        MedicalRecord med1 = new MedicalRecord(2,45.00,2.00,135.00,LocalDate.of(2023,3,12),pa1);
        pa1.removeMedicalRecord(med1);
        Assert.assertEquals(0,pa1.getMedicalRecords().size());

    }
}
