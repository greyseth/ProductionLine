package model;

import java.util.Date;

public interface Item {
    final String MANUFACTURER = "OracleProductions";

    void setProductionNumber(int productionNumber);
    void setName(String name);
    String getName();
    Date getManufactureDate();
    int getSerialNumber();
}