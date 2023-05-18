import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
 class BasicAssociationTest {
  @Test
  void create(){
   Doctor d1 = new Doctor("John Johnson","Anesthesiology",3000.00);
   Assert.assertEquals(0,d1.getProcedures().size());
   Procedure p1 = new Procedure(1,"Gland Removal",300.0,"Paracetamol",true, Duration.ofHours(3),Duration.ofDays(7),d1);
   Procedure p2  = new Procedure(2,"Arthroscopy",400.0,"Penicillin", false, Duration.ofMinutes(5), null,d1);
   Assert.assertEquals(2,d1.getProcedures().size());
   Assert.assertThrows(IllegalArgumentException.class,()-> p1.addDoctor(null));
   Doctor d2 = new Doctor("Samantha Jones","Dermatology",4000.00);
   d2.addProcedure(p2);
   Assert.assertEquals(2,p2.getDoctors().size());
   Doctor d3 = new Doctor("d3","Dermatology",4000.00);
   Doctor d4 = new Doctor("d4", "Internal medicine", 5000.00);
   Doctor d5 = new Doctor("d5", "Internal medicine", 5000.00);
   Doctor d6 = new Doctor("d6", "Internal medicine", 5000.00);
   p2.addDoctor(d3);
   p2.addDoctor(d4);
   p2.addDoctor(d5);
   Assert.assertThrows(IllegalArgumentException.class,()-> p2.addDoctor(d6));
  }
  @Test
  void remove(){
   Doctor d1 = new Doctor("John Johnson","Anesthesiology",3000.00);
   Procedure p1 = new Procedure(1,"Gland Removal",300.0,"Paracetamol",true, Duration.ofHours(3),Duration.ofDays(7),d1);
   Assert.assertThrows(IllegalArgumentException.class,()-> p1.removeDoctor(d1));
   Doctor d2 = new Doctor("d2","Dermatology",4000.00);
   p1.addDoctor(d2);
   Assert.assertEquals(2,p1.getDoctors().size());
   p1.removeDoctor(d2);
   Assert.assertEquals(1,p1.getDoctors().size());
   d2.addProcedure(p1);
   Assert.assertEquals(1,d2.getProcedures().size());
   d2.removeProcedure(p1);
   Assert.assertEquals(0,d2.getProcedures().size());
  }
}
