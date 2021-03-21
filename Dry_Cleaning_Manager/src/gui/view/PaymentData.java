package gui.view;

import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

public class PaymentData
{
    private SimpleStringProperty accountName;
    private SimpleStringProperty employeeName;
    private int cost;
    private SimpleStringProperty date;

    public PaymentData(String employeeName,String accountName,int cost,String date)
    {
        this.accountName = new SimpleStringProperty(accountName);
        this.employeeName = new SimpleStringProperty(employeeName);
        this.cost = cost;
        this.date = new SimpleStringProperty(date);
    }

    public String getAccountName() {
        return accountName.get();
    }

    public SimpleStringProperty accountNameProperty() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName.set(accountName);
    }

    public String getEmployeeName() {
        return employeeName.get();
    }

    public SimpleStringProperty employeeNameProperty() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName.set(employeeName);
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }
}
