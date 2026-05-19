package model;

import java.util.Date;

abstract public class Product implements Item, Comparable<Product> {

    public static int currentProductionNumber = 0;

    int serialNumber;
    String manufacturer;
    Date manufacturedOn;
    String name;
    String productType;
    String classification;

    public Product(String name) {
        this.name = name;
        this.serialNumber = currentProductionNumber;
        this.manufacturedOn = new Date();

        Product.currentProductionNumber++;

        classification = toString();
    }

    @Override
    public void setProductionNumber(int productionNumber) {
        this.serialNumber = productionNumber;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Date getManufactureDate() {
        return null;
    }

    @Override
    public int getSerialNumber() {
        return serialNumber;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getClassification() {
        return classification;
    }

    @Override
    public String toString() {
        return "Manufacturer : " + Item.MANUFACTURER + "\nSerial Number : " + serialNumber + "\nDate : " + manufacturedOn.toString() + "\nName : " + name;
    }

    @Override
    public int compareTo(Product other) {
        return name.compareTo(other.name);
    }
}
