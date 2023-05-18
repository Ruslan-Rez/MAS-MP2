import java.util.*;

public class Ward extends ObjectPlus{
    private int wardNumber;
    private int capacity;
    private Map<Long, Visit> visits = new HashMap<>();


    public Ward(int wardNumber, int capacity ) {
       setWardNumber(wardNumber);
        setCapacity(capacity);

    }

    public Map<Long, Visit> getVisits() {
        return Collections.unmodifiableMap(visits);
    }
    public List<Visit> getVisitList(){
        return new ArrayList<>(visits.values());
    }
    public List<Long> getVisitId(){
        return new ArrayList<>(visits.keySet());
    }
    public Visit getVisitById(int visitId){
        return visits.get(visitId);
    }
    public void addVisit(Visit v){
        if (v == null){
            throw new IllegalArgumentException("visit cannot be null");
        }

        if (this.visits.containsKey(v.getId())){
            return;
        }
        this.visits.put(v.getId(),v);
        v.setWard(this);
    }
    public void removeVisit(Visit v){
        if (v == null){
            throw new IllegalArgumentException("visit cannot be null");
        }
        if (this.visits.containsKey(v.getId())){
           this.visits.remove(v.getId());
           v.setWard(null);
        }
    }
    public int getWardNumber() {
        return wardNumber;
    }

    public void setWardNumber(int wardNumber) {
        if (wardNumber<0){
            throw new IllegalArgumentException("ward number cannot be less than 0");
        }
        this.wardNumber = wardNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        if (capacity<1){
            throw new IllegalArgumentException("capacity cannot be less than 1");
        }
        this.capacity = capacity;
    }


    @Override
    public String toString() {
        return "Ward{" +
                "wardNumber=" + wardNumber +
                ", capacity=" + capacity +
                ", visits=" + getVisitId() +
                '}';
    }
}
