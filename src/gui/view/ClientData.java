package gui.view;

import javafx.beans.property.SimpleStringProperty;
public class ClientData {
    int cost;
    int id;
    SimpleStringProperty name;
    SimpleStringProperty activity;

    ClientData(String name,String activity)
    {
        this.name = new SimpleStringProperty(name);
        this.activity = new SimpleStringProperty(activity);

    }

    ClientData(String name,String activity,int cost)
    {
        this.name = new SimpleStringProperty(name);
        this.activity = new SimpleStringProperty(activity);
        this.cost = cost;
    }
    public int getCost()
    {
        return cost;
    }
    public void setCost(int cost)
    {
        this.cost = cost;
    }
    public String getName(){
        return name.get();
    }
    public void setName(String name){
        this.name.set(name);
    }
    public String getActivity()
    {
        return activity.get();
    }
    public void setActivity(String activity)
    {
        this.activity.set(activity);
    }
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
}