import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;

public class QualifiedAssociationTest {
    @Test
    void create(){
        Ward w1 = new Ward(1,3);
        Ward w2 = new Ward(2,4);
        Doctor d1 = new Doctor("John Johnson","Anesthesiology",3000.00);
        Patient pa1 = new Patient("Jay","Keigh", LocalDate.of(2000,10,22),"233090321");
        Procedure p1 = new Procedure(1,"Gland Removal",300.0,"Paracetamol",true, Duration.ofHours(3),Duration.ofDays(7),d1);
        Visit v1 = new Visit(1,p1,pa1,0.5, LocalDate.of(2023,10,12));
        Visit v44 = new Visit(4,p1,pa1,0.5, LocalDate.of(2023,10,12));
        Assert.assertEquals(null,v1.getWard());
        v1.setWard(w1);
        Assert.assertEquals(w1,v1.getWard());
        Assert.assertEquals(1,w1.getVisitList().size());
        w1.addVisit(v44);
        Assert.assertEquals(2,w1.getVisitList().size());
        v1.setWard(w2);
        Assert.assertEquals(w2,v1.getWard());
        Assert.assertEquals(1,w1.getVisitList().size());
    }

    @Test
    void remove(){
        Ward w1 = new Ward(1,3);
        Ward w2 = new Ward(2,4);
        Doctor d1 = new Doctor("John Johnson","Anesthesiology",3000.00);
        Patient pa1 = new Patient("Jay","Keigh", LocalDate.of(2000,10,22),"233090321");
        Procedure p1 = new Procedure(1,"Gland Removal",300.0,"Paracetamol",true, Duration.ofHours(3),Duration.ofDays(7),d1);
        Visit v1 = new Visit(1,p1,pa1,0.5, LocalDate.of(2023,10,12));
        Visit v44 = new Visit(4,p1,pa1,0.5, LocalDate.of(2023,10,12));
        v1.setWard(w1);
        w1.removeVisit(v1);
        Assert.assertEquals(0,w1.getVisitList().size());
        Assert.assertEquals(null,v1.getWard());
    }
}
