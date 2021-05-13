
package mainpackage;

/**
 *
 * @author etzellux
 */

public class MainManager 
{
    private MachineManager machine_manager;
    private ClientManager client_manager;
    
    public MainManager()
    {
        client_manager = new ClientManager();
        machine_manager = new MachineManager();
    }
    
    public void displayMachine(Machine m)
    {
        System.out.println(m.toString());
    }
    
    public MachineManager MachineManager()
    {
        return machine_manager;
    }
    
     public ClientManager ClientManager()
    {
        return client_manager;
    }
}
