package gui.Controller;

import mainpackage.Clothes;
import mainpackage.Client;
import mainpackage.ClientManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GenerateFromData
{
    DataManager dm;
    ClientManager clientManager;

    public GenerateFromData(ClientManager clientManager) throws SQLException
    {
        dm = new DataManager();
        this.clientManager = clientManager;
    }

    public Client generateClient(int id,String name,String type, String phoneNumber)
    {
        Client clt = new Client(name,type,phoneNumber);
        clientManager.addClient(clt);

        try
        {
            ResultSet wardrobe = dm.getData("clothes where owner_id=" + id + " and activity like '%active%'");
            ResultSet passiveWardrobe = dm.getData("clothes where owner_id=" + id + " and activity like '%passive%'");

            while(wardrobe.next())
            {
                clientManager.addClothesToClient(clientManager.getClientNum() - 1,new Clothes(wardrobe.getString("type"),wardrobe.getString("bodySize"),
                                                wardrobe.getString("status"),wardrobe.getString("colorType")));
            }
            while(passiveWardrobe.next())
            {
                clt.addToPassive(new Clothes(passiveWardrobe.getString("type"),passiveWardrobe.getString("bodySize"),
                        passiveWardrobe.getString("status"),passiveWardrobe.getString("colorType")));
            }
            dm.updateClientData("cost=" + clt.getCost() +" where name like '%" + clt.getName() + "%'");
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return clt;
    }

    public Clothes generateClothes(String type,String bodySize,String status,String colorType)
    {
        Clothes clothes = new Clothes(type,bodySize,status,colorType);
        return clothes;
    }
}
