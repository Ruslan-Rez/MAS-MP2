import java.io.*;
import java.util.*;

public class ObjectPlus implements Serializable {
    private static Map<Class, List> extent = new HashMap<>();

    public ObjectPlus(){

    }
    protected void addToExtent() {
        List list = extent.get(this.getClass());
        if(list==null) {
            list = new ArrayList();
            extent.put(this.getClass(), list);
        }
        list.add(this);
    }
    protected void removeFromExtent(Class clazz) {
        List list = extent.get(clazz);
        if (list!=null){
            list.remove(clazz);
        }
    }
    protected void removeObjectFromExtent(Object o) {
        List list = extent.get(o.getClass());
        if (list!=null){
            list.remove(o);
        }
    }
    public static  <E> List<E> getExtent(Class<E> clazz){
        List list = extent.get(clazz);
        if(list == null){
            return new ArrayList<>();
        }
        return Collections.unmodifiableList(list);
    }
    public void setExtent(ObjectOutputStream oos) throws IOException{
        oos.writeObject(extent);
    }
    public static void saveExtent(ObjectOutputStream oos) throws IOException {
        oos.writeObject(extent);
    }
    public static void loadExtent(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        extent = (Map<Class, List>) ois.readObject();
    }
}
