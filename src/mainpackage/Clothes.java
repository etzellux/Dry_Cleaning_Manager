
package mainpackage;
import java.util.Date;

/**
 *
 * @author etzellux
 */

public class Clothes 
{
    private int id;
    private String type;
    private String bodySize;
    private String status;
    private String colorType;
    private Date arrDate;
    private String activity;
    
    private final String types[] = {"jean","pant","dress","sweat","hoodie","t-shirt","shirt","suit","mont","portmanto"};
    private final String statuses[] = {"dirty","cleaned","cleaned-ironed","delivered"};
    private final String bodySizes[] = {"XS","S","M","L","XL","XXL","XXXL"};
    private final String colorTypes[] = {"white","black","colorful"};
    
    public Clothes()
    {
        
    }
    public Clothes(String type,String bodySize,String status,String colorType)
    {
        setType(type);
        setStatus(status);
        setColorType(colorType);
        setArrDate();
        setBodySize(bodySize);
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
    public String getType()
    {
        return type;
    }
    
    public void setType(int typeNum)
    {
        if(typeNum >= 0 && typeNum < types.length)
        {
            type = types[typeNum];
        }
        else
        {
            System.out.println("Invalid entry: 0-" + (types.length - 1));
        }
    }
    public void setType(String type)
    {
        this.type = type;
    }
    
    public String getBodySize()
    {
        return bodySize;
    }
    
    public void setBodySize(int bodySizeNum)
    {
        if(bodySizeNum >= 0 && bodySizeNum < bodySizes.length)
        {
            bodySize = bodySizes[bodySizeNum];
        }
        else
        {
            System.out.println("Invalid entry: 0-" + (bodySizes.length - 1));
        }
    }
    public void setBodySize(String bodySize)
    {
        this.bodySize = bodySize;
    }
    
    public String getStatus()
    {
        return status;
    }
    
    public void setStatus(int statusNum)
    {
        if(statusNum >= 0 && statusNum < statuses.length)
        {
            status = statuses[statusNum];
        }
        else
        {
            System.out.println("Invalid entry: 0-" + (statuses.length - 1));
        }
        if(statusNum == 4)
        {
            setActivity("passive");
        }
        else
        {
            setActivity("active");
        }
    }
    public void setStatus(String status)
    {
        this.status = status;
        if(status.equals("delivered"))
        {
            this.setActivity("passive");
        }
        else
        {
            this.setActivity("active");
        }
    }
    public String getColorType()
    {
        return colorType; 
    }
    
    public void setColorType(int colorTypeNum)
    {
        if(colorTypeNum >= 0 && colorTypeNum < colorTypes.length)
        {
            colorType = colorTypes[colorTypeNum];
        }
        else
        {
            System.out.println("Invalid entry: 0-" + (colorTypes.length - 1));
        }
    }
    public void setColorType(String colorType)
    {
        this.colorType = colorType;
    }
    
    public Date getArrDate()
    {
        return arrDate;
    }
    
    public void setArrDate()
    {
        arrDate = new Date();
    }
    
    public void setArrDate(Date dt)
    {
        arrDate = dt;
    }
    
    public String toString()
    {
        String msg = "id:" + id + "\ntype:" + type + ":" + colorType + "\nstatus" + status;
        return msg;
    }

    public String getActivity()
    {
        return activity;
    }

    public void setActivity(String activity)
    {
        this.activity = activity;
    }
}
