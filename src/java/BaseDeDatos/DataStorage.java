package BaseDeDatos;

import Objetos.Sala;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Familia
 */
public class DataStorage {
    
    private static DataStorage instance = null;
    
    private static Map<Integer,Sala> salas;
    
    protected DataStorage(){
        salas = new HashMap<>();
    }
    
    public static DataStorage getInstance(){
        if(instance==null){
            instance = new DataStorage();
        }
        return instance;
    }
    
    public static void insertarSala(Sala s){
        salas.put(s.getId(), s);
    }
    
    public static Sala getSala(int id){
        return salas.get(id);
    }
    
}
