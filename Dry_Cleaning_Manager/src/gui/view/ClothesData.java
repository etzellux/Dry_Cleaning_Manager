package gui.view;

import javafx.beans.property.SimpleStringProperty;

public class ClothesData
{
    int id;
    SimpleStringProperty type;
    SimpleStringProperty bodySize;
    SimpleStringProperty status;
    SimpleStringProperty colorType;
    SimpleStringProperty arrDate;
    SimpleStringProperty activity;

    ClothesData(int id,String type,String bodySize,String status,String colorType,String arrDate,String activity)
    {
        this.id = id;
        this.type = new SimpleStringProperty(type);
        this.bodySize = new SimpleStringProperty(bodySize);
        this.status = new SimpleStringProperty(status);
        this.colorType = new SimpleStringProperty(colorType);
        this.arrDate= new SimpleStringProperty(arrDate);
        this.activity = new SimpleStringProperty(activity);
    }

    public String getType()
    {
        return type.get();
    }

    public SimpleStringProperty typeProperty()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type.set(type);
    }

    public String getBodySize()
    {
        return bodySize.get();
    }

    public SimpleStringProperty bodySizeProperty()
    {
        return bodySize;
    }

    public void setBodySize(String bodySize)
    {
        this.bodySize.set(bodySize);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status.set(status);
    }

    public String getColorType()
    {
        return colorType.get();
    }

    public SimpleStringProperty colorTypeProperty()
    {
        return colorType;
    }

    public void setColorType(String colorType)
    {
        this.colorType.set(colorType);
    }

    public String getArrDate() {
        return arrDate.get();
    }

    public SimpleStringProperty arrDateProperty()
    {
        return arrDate;
    }

    public void setArrDate(String arrDate)
    {
        this.arrDate.set(arrDate);
    }

    public String getActivity()
    {
        return activity.get();
    }

    public SimpleStringProperty activityProperty()
    {
        return activity;
    }

    public void setActivity(String activity)
    {
        this.activity.set(activity);
    }

    public void setId(int id)
    {
        this.id = id;
    }
    public int getId()
    {
        return id;
    }
}
