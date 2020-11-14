package com.epam.university.java.core.task055;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "passports_houses")
public class HouseDefinitionImpl implements HouseDefinition {
    // добавить потом продолжение адреса
    @XmlElement(name = "addr_street")
    private String address;
    @XmlElement(name = "data_buildingdate")
    private String year;
    @XmlElement(name = "data_buildingarea")
    private String area;
    @XmlElement(name = "addr_district")
    private String district;
    @XmlElement(name = "comm_room_num")
    private String comm;

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public int getYear() {
        if (year.matches("\\d+")) {
            return Integer.parseInt(year);
        } else if (year.matches("\\D{3}\\d+")) {
            return Integer.parseInt(year.substring(3));
        } else {
            return 0;
        }
    }

    @Override
    public double getArea() {
        try {
            return Double.parseDouble(area);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void setYear(int year) {
        this.year = String.valueOf(year);
    }

    @Override
    public void setArea(double area) {
        this.area = String.valueOf(area);
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getComm() {
        return comm;
    }

    public void setComm(String comm) {
        this.comm = comm;
    }
}
