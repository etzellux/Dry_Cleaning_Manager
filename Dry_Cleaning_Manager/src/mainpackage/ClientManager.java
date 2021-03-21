
package mainpackage;
import java.util.ArrayList;

/**
 *
 * @author etzellux
 */
public class ClientManager 
{
    private ArrayList<Client> Clients = new ArrayList<>();
    private int clientNum = 0;
    
    //private final String types[] = {"jean","pant","dress","sweat","hoodie","t-shirt","shirt","suit","mont","portmanto"};
    private int[] prices = {20,22,50,17,19,15,22,50,45,50};
    
    public ClientManager()
    {
        
    }
    
    public void addClient(Client clt)
    {
        Clients.add(clt);
        clientNum++;
    }
    
    public void removeClient(Client clt)
    {
        Clients.remove(clt);
    }
    
    public Client getClient(int index)
    {
        return Clients.get(index);
    }
    
    public int getClientNum()
    {
        return clientNum;
    }
    
    public void classify()
    {
        for(Client clt: Clients)
        {
            clt.setActivity(0);
            for(int i = 0 ;i < clt.getClothesNum();i++)
            {
                String status = clt.getFromWardrobe(i).getStatus();
                if(status.equals("dirty") || status.equals("cleaned") || status.equals("cleaned-ironed"))
                {
                    clt.setActivity(1);
                    break;
                }
            }
        }
    }
    
    public void addClothesToClient(int index,Clothes clt)
    {
        Clients.get(index).addToWardrobe(clt);
    }
    
    public void removeClothesFromClient(int indexOfC,Clothes clt)
    {
        Clients.get(indexOfC).removeFromWardrobe(clt);
        Clients.get(indexOfC).moveToPassive(clt);
    }
            
    /*public void computeCost()
    {
        for(Client clt: Clients)
        {
            clt.setCost(0);
            for(int i = 0;i < clt.getClothesNum();i++)
            {
                String status = clt.getFromWardrobe(i).getStatus();
                if(!status.equals("delivered"))
                {
                    String type = clt.getFromWardrobe(i).getType();
                    
                   for(int j = 0;j < types.length;j++)
                   {
                       if(type.equals(types[j]))
                       {
                           clt.setCost(clt.getCost() + prices[j]);
                       }
                   }
                }
            }
        }
    }*/
    
}

