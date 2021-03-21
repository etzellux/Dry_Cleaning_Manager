
package mainpackage;
import java.util.ArrayList;
/**
 *
 * @author etzellux
 */

public class MachineManager 
{
    private ArrayList<CleaningMachine> CMs = new ArrayList<>();
    private ArrayList<IroningMachine> IMs = new ArrayList<>();
    
    private int Mnum;
    
    public MachineManager()
    {
        
    }
    
    public void addToCMs(CleaningMachine cm)
    {
        CMs.add(cm);
    }
    
    public void removeFromCMs(CleaningMachine cm)
    {
        CMs.remove(cm);
    }
    
    public void removeFromCMs(int index)
    {
        CMs.remove(index);
    }
    
    public void addToIMs(IroningMachine im)
    {
        IMs.add(im);
    }
    
    public void removeFromIMs(IroningMachine im)
    {
        IMs.remove(im);
    }
    
    public void removeFromIMs(int index)
    {
        IMs.remove(index);
    }
    
    public void setCTypeForCM()
    {
        int wNum = 0;
        int bNum = 0;
        int cNum = 0;
        
        for(CleaningMachine cm: CMs)
        {
            if(cm.getwlNum() == 0)
            {
                cm.setCType("empty");
                continue;
            }
            
            for(int i = 0;i < cm.getwlNum();i++)
            {
                wNum = 0;
                bNum = 0;
                cNum = 0;
                Clothes clt = cm.getFromWorkload(i);
                if(clt.getColorType().equals("white"))
                {
                    wNum++;
                }
                else if(clt.getColorType().equals("black"))
                {
                    bNum++;
                }
                else if(clt.getColorType().equals("colorful"))
                {
                    cNum++;
                }
            }
            if(wNum > bNum && wNum > cNum)
            {
                cm.setCType("white");
            }
            else if(bNum > wNum && bNum > cNum)
            {
                 cm.setCType("black");
            }
            else if(cNum > wNum && cNum > bNum)
            {
                 cm.setCType("colorful");
            }
            else
            {
                cm.setCType("empty");
            }
        }
    }
    public void setCTypeForOne(int index)
    {
        int wNum = 0;
        int bNum = 0;
        int cNum = 0;
        
        CleaningMachine cm = CMs.get(index);
        
        if(cm.getwlNum() == 0)
        {
            cm.setCType("empty");
        }
        else
        {
        
            for(int i = 0;i < cm.getwlNum();i++)
            {
                wNum = 0;
                bNum = 0;
                cNum = 0;
            
                Clothes clt = cm.getFromWorkload(i);
                if(clt.getColorType().equals("white"))
                {
                    wNum++;
                }
                else if(clt.getColorType().equals("black"))
                {
                    bNum++;
                }
                else if(clt.getColorType().equals("colorful"))
                {
                    cNum++;
                }
            }
            if(wNum > bNum && wNum > cNum)
            {
                cm.setCType("white");
            }
            else if(bNum > wNum && bNum > cNum)
            {
                cm.setCType("black");
            }
            else if(cNum > wNum && cNum > bNum)
            {
                cm.setCType("colorful");
            }
            else
            {
                cm.setCType("empty");
            }
        }
        
        
    }
    
    public CleaningMachine availableCM()
    {
        int indexOf = 0;
        int minWL = CMs.get(0).wlNum;
        for(int i = 0;i < CMs.size();i++)
        {
            if(CMs.get(i).wlNum < minWL )
            {
                indexOf = i;
            }
        }
        return CMs.get(indexOf);
    }
    
    public CleaningMachine availableCM(String type)
    {
        boolean found = true;
        int indexOfEmpty = 0;
        int indexOf = -1;
        int minWL = 10000;
        
        for(int i = 0;i < CMs.size();i++)
        {
            this.setCTypeForOne(i);
            CleaningMachine cm = CMs.get(i);
            
            if(cm.getCType().equals(type))
            {
                if(cm.getwlNum() < minWL)
                {
                    minWL = cm.getwlNum();
                    indexOf = i;
                }
            }
            else if(cm.getCType().equals("empty"))
            {
                indexOfEmpty = i;
            }
        }
        if(indexOf == -1)
        {
            return CMs.get(indexOfEmpty);
        }
        else
        {
            return CMs.get(indexOf);
        }
        
    }
    
    public IroningMachine availableIM()
    {
        int indexOf = 0;
        int minWL = IMs.get(0).wlNum;
        for(int i = 0;i < IMs.size();i++)
        {
            if(IMs.get(i).wlNum < minWL )
            {
                indexOf = i;
            }
        }
        return IMs.get(indexOf);
    }
    
    public void loadToCM(Clothes clt)
    {
        CleaningMachine cm = availableCM(clt.getColorType());
        
        cm.addToWorkload(clt);
    }
    
    public void removeLoad(int mtypeNum,int index, Clothes clt)
    {
        if(mtypeNum == 0)
        {
            CMs.get(index).removeFromWorkload(clt);
        }
        else if(mtypeNum == 1)
        {
            IMs.get(index).removeFromWorkload(clt);
        }
        else
        {
            System.out.println("Invalid entry: 0-1");
        }
    }
    
    public void loadToIM(Clothes clt)
    {
        IroningMachine im = availableIM();
        
        im.addToWorkload(clt);
    }
    
    public int SumOfWL()
    {
        int sum = 0;
        for(CleaningMachine cm: CMs)
        {
            sum += cm.getwlNum();
        }
        for(IroningMachine im: IMs)
        {
            sum += im.getwlNum();
        }
        return sum;
    }
}
