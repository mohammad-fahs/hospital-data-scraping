package com.capstone.hospitaldata.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Hospital {
    String address;
    String phone;
    String fax;
    String email;
    String director;
    String  region;
    String status;
    String registeredByMOPH;
    String ownership;
    String numberOfMedicineBeds;
    String numberOfSurgeryBeds;
    String numberOfBGYNBeds;
    String numberOfPediatricBeds;
    String numberOfICUBeds;
    String numberOfFullTimeDrs;
    String numberOfFullTimeNurses;
    String generalLab;
    String bloodBank;
    String controllingDr;
    String controllingDrPhoneNumber;
    String generalRadiology;
    String radioTherapy;
    String memberOfSoPH;
    String investmentAuthorizationNumber;
    String constructionAuthorizationNumber;

    @Override
    public String toString() {
        return "Hospital{" +
                "\naddress='" + address + '\'' +
                "\n phone='" + phone + '\'' +
                "\n fax='" + fax + '\'' +
                "\n email='" + email + '\'' +
                "\n director='" + director + '\'' +
                "\n region='" + region + '\'' +
                "\n status='" + status + '\'' +
                "\n registeredByMOPH='" + registeredByMOPH + '\'' +
                "\n ownership='" + ownership + '\'' +
                "\n numberOfMedicineBeds='" + numberOfMedicineBeds + '\'' +
                "\n numberOfSurgeryBeds='" + numberOfSurgeryBeds + '\'' +
                "\n numberOfBGYNBeds='" + numberOfBGYNBeds + '\'' +
                "\n numberOfPediatricBeds='" + numberOfPediatricBeds + '\'' +
                "\n numberOfICUBeds='" + numberOfICUBeds + '\'' +
                "\n numberOfFullTimeDrs='" + numberOfFullTimeDrs + '\'' +
                "\n numberOfFullTimeNurses='" + numberOfFullTimeNurses + '\'' +
                "\n generalLab='" + generalLab + '\'' +
                "\n bloodBank='" + bloodBank + '\'' +
                "\n controllingDr='" + controllingDr + '\'' +
                "\n controllingDrPhoneNumber='" + controllingDrPhoneNumber + '\'' +
                "\n generalRadiology='" + generalRadiology + '\'' +
                "\n radioTherapy='" + radioTherapy + '\'' +
                "\n memberOfSoPH='" + memberOfSoPH + '\'' +
                "\n investmentAuthorizationNumber='" + investmentAuthorizationNumber + '\'' +
                "\n constructionAuthorizationNumber='" + constructionAuthorizationNumber + '\'' +
                "\n}";
    }
}
