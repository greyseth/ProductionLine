package model;

import java.util.Date;

abstract public class Product implements Item, Comparable<Product> {
    public static int currentProductionNumber = 0;

    int serialNumber;
    String manufacturer;
    Date manufacturedOn;
    String name;

    public Product(String name) {
        this.name = name;
        this.serialNumber = currentProductionNumber;
        this.manufacturedOn = new Date();

        currentProductionNumber++;
    }

    @Override
    public void setProductionNumber(int productionNumber) {

    }

    @Override
    public void setName(String name) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Date getManufactureDate() {
        return null;
    }

    @Override
    public int getSerialNumber() {
        return 0;
    }

    @Override
    public String toString() {
        return "Manufacturer : "+Item.MANUFACTURER+"\nSerial Number : "+serialNumber+"\nDate : "+manufacturedOn.toString()+"\nName : "+name;
    }

    @Override
    public int compareTo(Product other) {
        return name.compareTo(other.name);
    }
}
