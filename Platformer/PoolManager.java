/**
 * PoolManager has map of different pools, this mmanages creating the pools themselves and assigning callbacks so that pooled objects get sent back to their respective pool.
 * 
 * @author Prince Christian Basiga
 */

import java.util.*;


public class PoolManager  
{
    //I could change this so that pool is an object wrapped around a Queue then can just clone prefab.
    Map<String, Queue<Reusable>> pools = new HashMap<String, Queue<Reusable>>();
    
    
    public void addPool(String id, Reusable prefab, int poolSize){
       
        pools.put(id,new LinkedList<Reusable>());
        
        for (int i = 0; i < poolSize; ++i){
         
            //so I could clonse and just override clone
            //that way I could also just have same type item
            //but different instances. Or could get class name and then 
            //construct object based on that. Problem with that though is
            //no guarantee that no argument, which makes this Pooling system
            //very specific, cloning adds more flexibility, I'll do that.
           
            try{
                pools.get(id).add((Reusable)prefab.clone());
            }
            catch (CloneNotSupportedException e){
                
            }
        }
       
    }
    
    public Reusable getReusable(String id){
        
        Reusable obj = pools.get(id).peek();
        
        if (obj == null){
            return obj;
        }
        
        //Adding self back to pool
        obj.doneUsing.addEvent(() -> {pools.get(id).add(obj);});
       
            
        pools.get(id).remove();
        
        return obj;
        
    }
    
}
