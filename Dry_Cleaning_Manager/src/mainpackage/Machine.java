
package mainpackage;
import java.util.ArrayList;

/**
 *
 * @author etzellux
 */

public class Machine 
{
    protected int id;
    protected String name;
    
    protected ArrayList<Clothes> Workload = new ArrayList<>();
    protected int wlNum;
    
    public Machine(int id,String name)
    {
        this.id = id;
        this.name = name;
        wlNum = 0;
    }
    
    public int getId()
    {
        return id;
    }
    
    public void setId(int idNum)
    {
        if(idNum >= 0 && idNum < 1000000)
        {
            id = idNum;
        }
        else
        {
            System.out.println("Invalid entry: 0-" + (999999));
        }
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void addToWorkload(Clothes clt)
    {
        Workload.add(clt);
    }
    
    public void removeFromWorkload(Clothes clt)
    {
        Workload.remove(clt);
    }
    
    public void removeFromWorkload(int index)
    {
        Workload.remove(index);
    }
    public Clothes getFromWorkload(int index)
    {
        return Workload.get(index);
    }
    public int getwlNum()
    {
        wlNum = Workload.size();
        return wlNum;
    } 
    
    public String toString()
    {
        String msg = "id:" + id + "\nname:" + name;
        return msg;
    }
}
