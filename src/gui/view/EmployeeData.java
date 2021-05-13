package gui.view;

import javafx.beans.property.SimpleStringProperty;

public class EmployeeData
{
    int id;
    SimpleStringProperty name;
    SimpleStringProperty position;
    SimpleStringProperty phoneNumber;
    int salary;
    SimpleStringProperty hiringDate;
    EmployeeData nextNode;

    public EmployeeData(int id,String name, String position, String phoneNumber, int salary, String hiringDate)
    {
        this.id = id;
        this.name = new SimpleStringProperty(name);
        this.position = new SimpleStringProperty(position);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.salary = salary;
        this.hiringDate = new SimpleStringProperty(hiringDate);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getPosition() {
        return position.get();
    }

    public SimpleStringProperty positionProperty() {
        return position;
    }

    public void setPosition(String position) {
        this.position.set(position);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public SimpleStringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getHiringDate() {
        return hiringDate.get();
    }

    public SimpleStringProperty hiringDateProperty() {
        return hiringDate;
    }

    public void setHiringDate(String hiringDate) {
        this.hiringDate.set(hiringDate);
    }
}
