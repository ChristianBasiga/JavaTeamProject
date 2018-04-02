/**
 * Write a description of class PoolManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;


public class PoolManager  
{
    static Map<String, Queue<Reusable>> pools = new HashMap<String, Queue<Reusable>>();
    
    
    public static void addPool(String id, Reusable prefab, int poolSize){
       
        pools.put(id,new LinkedList<Reusable>());
        
        for (int i = 0; i < poolSize; ++i){
         
            try{
            pools.get(id).add((Reusable)prefab.clone());
            }
            catch (CloneNotSupportedException e){
            }
        }
       
    }
    
    public static Reusable getReusable(String id){
        
        Reusable obj = pools.get(id).peek();
        
        if (obj == null){
            return obj;
        }
        
       
            
        pools.get(id).remove();
        
        return obj;
        
    }
    
}
