import java.io.Serializable;
import java.time.*;

public class ProcedureDetails implements Serializable {
    public ProcedureDetails(boolean requiresAnesthesia, Duration procedureLength, Duration recoveryLength) {
        setRequiresAnesthesia(requiresAnesthesia);
        setProcedureLength(procedureLength);
        setRecoveryLength(recoveryLength);
    }

    private boolean requiresAnesthesia;
    private Duration procedureLength;
    private Duration recoveryLength;

    public boolean isRequiresAnesthesia() {
        return requiresAnesthesia;
    }

    public void setRequiresAnesthesia(boolean requiresAnesthesia) {
        this.requiresAnesthesia = requiresAnesthesia;
    }

    public Duration getProcedureLength() {
        return procedureLength;
    }

    public void setProcedureLength(Duration procedureLength) {
        if (procedureLength == null){
            throw new IllegalArgumentException("procedure length cannot be null");
        } else if(procedureLength.compareTo(Duration.ofMinutes(1)) < 0 ){
            throw new IllegalArgumentException("procedure length cannot last less than a minute");
        }
        this.procedureLength = procedureLength;
    }

    public Duration getRecoveryLength() {
        return recoveryLength;
    }

    public void setRecoveryLength(Duration recoveryLength) {
        if (recoveryLength == null){
            recoveryLength = Duration.ofNanos(0);
        }
        this.recoveryLength = recoveryLength;
    }

    @Override
    public String toString() {
        return "ProcedureDetails{" +
                "requiresAnesthesia=" + requiresAnesthesia +
                ", procedureLength=" + procedureLength.toMinutes() + "m "+
                ", recoveryLength=" + recoveryLength.toHours() + "h" +
                '}';
    }
}
