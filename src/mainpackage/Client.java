
package mainpackage;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author etzellux
 */

public class Client 
{
    private int id;
    private String name;
    private String type;
    private Date signingDate;
    private int cost;
    private String activity;
    private String phoneNumber;

    private ArrayList<Clothes> wardrobe = new ArrayList<>();
    private ArrayList<Clothes> passiveWardrobe = new ArrayList<>();
    private int clothesNum;

    private final String[] types = {"individual","corporate","individual+","corporate+"};
    private final String[] activityTypes = {"passive","active"};
    
    private final String clothesTypes[] = {"jean","pant","dress","sweat","hoodie","t-shirt","shirt","suit","mont","portmanto"};
    private int[] prices = {20,22,50,17,19,15,22,50,45,50};
    
    public Client(String name,String type,String phoneNumber)
    {

        setName(name);
        setType(type);
        setPhoneNumber(phoneNumber);
        setSigningDate();
        clothesNum = 0;
        cost = 0;
    }
    public Client(String name,String type,String phoneNumber,int[] prices)
    {

        setName(name);
        setType(type);
        setPhoneNumber(phoneNumber);
        setSigningDate();
        clothesNum = 0;
        cost = 0;
        this.prices = prices;
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
    
    public String getType()
    {
        return type;
    }
    
    public void setType(String type)
    {
        this.type = type;
    }
    
    public Date getSigningDate()
    {
        return signingDate;
    }
    
    public void setSigningDate()
    {
        signingDate = new Date();
    }
    
    public void setSigningDate(Date dt)
    {
        signingDate = dt;
    }
    
    public int getCost()
    {
        return cost;
    }
    
    public void setCost(int cost)
    {
        this.cost = cost;
    }
    
    public void addToWardrobe(Clothes clt)
    {
        wardrobe.add(clt);
        
        for(int j = 0;j < clothesTypes.length;j++)
        {
            if(clt.getType().equals(clothesTypes[j]))
            {
                cost += prices[j];
            }
        }
    }
    
    public void removeFromWardrobe(Clothes clt)
    {
        wardrobe.remove(clt);
    }
    
    public void removeFromWardrobe(int index)
    {
        wardrobe.remove(index);
    }
    
    public Clothes getFromWardrobe(int index)
    {
        return wardrobe.get(index);
    }
    
    public int getClothesNum()
    {
        clothesNum = wardrobe.size();
        return clothesNum;
    }
    
    public String getActivity()
    {
        return activity;
    }
    
    public void setActivity(int aNum)
    {
        if(aNum >= 0 && aNum < activityTypes.length)
        {
            activity = activityTypes[aNum];
        }
        else
        {
            System.out.println("Invalid entry: 0-" + (activityTypes.length - 1));
        }
    }
    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
    public void addToPassive(Clothes clt)
    {
        passiveWardrobe.add(clt);
    }
    public void moveToPassive(Clothes clt)
    {
        passiveWardrobe.add(clt);
        wardrobe.remove(clt);
    }
    
    public void moveToPassive(int index)
    {
        Clothes clt = wardrobe.get(index);
        passiveWardrobe.add(clt);
        wardrobe.remove(index);
    }
    
}
