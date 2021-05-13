
package mainpackage;

/**
 *
 * @author etzellux
 */

public class IroningMachine extends Machine
{
    private static int imNum = 0;
    
    public IroningMachine(int id, String name)
    {
        super(id,name);
        imNum++;
    }
    
    public int getIMNum()
    {
        return imNum;
    }
    
    public String toString()
    {
        String msg = "id:" + id + "\nname:" + name + "\ntype: Ironing Machine" + "\nnumber of loads:" + wlNum;
        return msg;
    }
}
