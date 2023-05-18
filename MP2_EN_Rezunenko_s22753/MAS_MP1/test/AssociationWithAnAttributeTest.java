import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;

public class AssociationWithAnAttributeTest {
    @Test
    void create(){
        Doctor d1 = new Doctor("John Johnson","Anesthesiology",3000.00);
        Patient pa1 = new Patient("Jay","Keigh", LocalDate.of(2000,10,22),"233090321");
        Procedure p1 = new Procedure(1,"Gland Removal",300.0,"Paracetamol",true, Duration.ofHours(3),Duration.ofDays(7),d1);
        Visit v1 = new Visit(1,p1,pa1,0.5, LocalDate.of(2023,10,12));
        Assert.assertEquals(1,p1.getVisits().size());
        Assert.assertEquals(1,pa1.getVisits().size());
        Assert.assertEquals(p1,v1.getProcedure());
        Assert.assertThrows(IllegalArgumentException.class,()->p1.addVisit(null));
    }
    @Test
    void remove(){
        Doctor d1 = new Doctor("John Johnson","Anesthesiology",3000.00);
        Patient pa1 = new Patient("Jay","Keigh", LocalDate.of(2000,10,22),"233090321");
        Procedure p1 = new Procedure(1,"Gland Removal",300.0,"Paracetamol",true, Duration.ofHours(3),Duration.ofDays(7),d1);
        Visit v1 = new Visit(1,p1,pa1,0.5, LocalDate.of(2023,10,12));
        Visit v44 = new Visit(4,p1,pa1,0.5, LocalDate.of(2023,10,12));
        Assert.assertEquals(2,p1.getVisits().size());
        Assert.assertEquals(2,pa1.getVisits().size());
        p1.removeVisit(v44);
        Assert.assertEquals(1,p1.getVisits().size());
        Assert.assertEquals(1,pa1.getVisits().size());
    }
}
