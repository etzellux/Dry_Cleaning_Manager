
package mainpackage;

/**
 *
 * @author etzellux
 */
public class CleaningMachine extends Machine
{
    private static int cmNum = 0;
    private String cTypeOfWL = "empty";
    
    public CleaningMachine(int id,String name)
    {
        super(id,name);
        cmNum++;
    } 
    
    public String getCType()
    {
        return cTypeOfWL;
    }
    
    public void setCType(String type)
    {
        cTypeOfWL = type;
    }
    public int getCMNum()
    {
        return cmNum;
    }
    
    public String toString()
    {
        String msg = "id:" + id + "\nname:" + name + "\ntype: Cleaning Machine" + "\nnumber of loads:" + wlNum;
        return msg;
    }
}
