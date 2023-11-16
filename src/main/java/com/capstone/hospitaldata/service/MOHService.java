package com.capstone.hospitaldata.service;

import com.capstone.hospitaldata.model.Hospital;
import com.capstone.hospitaldata.model.HospitalRecord;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

public class MOHService {
    private static final String BASE_LINK =  "https://www.moph.gov.lb/HealthFacilities/index/3/188/8/page:@@PAGE@@?&facility_type=8&district=&name=";
    private static final String MOH_LINK = "https://www.moph.gov.lb/";
    private static final String ADDRESS = "Address";
    private static final String PHONE = "Phone";
    private static final String FAX = "Fax ";
    private static final String EMAIL = "Email";
    private static final String DIRECTOR = "Director's name";
    private static final String REGION = "Caza";
    private static final String STATUS = "Status";
    private static final String REGISTERED = "Registered by MOPH";
    private static final String OWNERSHIP = "Ownership";
    private static final String MEDICINE_BEDS = "Nb of medicine beds";
    private static final String SURGERY_BEDS = "Nb of surgery beds";
    private static final String BGYN_BEDS = "Nb of beds BGYN";
    private static final String PEDIATRICS_BEDS = "Nb of beds Pediatrics";
    private static final String ICU_BEDS = "Nb of beds ICU CCU";
    private static final String DOCTORS = "Nb of fulltime doctors";
    private static final String NURSES = "Nb of fulltime nurses";
    private static final String GENERAL_LABS = "General Lab";
    private static final String BLOOD_BANK = "Blood Bank";
    private static final String CONTROLLING_DOCTOR = "Controlling Doctor";
    private static final String CONTROLLING_DOCTOR_PHONE_NUMBER = "Controlling Doctor Phone Number";
    private static final String GENERAL_RADIOLOGY = "General Radiology";
    private static final String RADIO_THERAPY = "Radiotherapy";
    private static final String MOSOPH = "Member of syndicate of private hospitals";
    private static final String INVESTMENT_AUTH_NB = "Investment Authorization Nb";
    private static final String CONSTRUCTION_AUTH_NB = "Construction Authorization Nb";
    public static List<HospitalRecord> getRecords(){
       int i=1;
        List<HospitalRecord> hospitalRecords = new ArrayList<>();
       while(true){
           String link = BASE_LINK.replace("@@PAGE@@",Integer.toString(i++));
           try{
               Connection connection = Jsoup.connect(link);
               Document doc = connection.get();
               String html = doc.html();
               Document document = Jsoup.parse(html);
               Elements rows = document.select("table.table tbody tr");

               for (Element row : rows) {
                   Element nameCell = row.select("td:eq(0)").first();
                   String name = nameCell.text();
                   String href = MOH_LINK+nameCell.select("a").attr("href").replace("/ar/", "/en/");;
                   hospitalRecords.add(new HospitalRecord(name,href));
                   System.out.println("Name: " + name);
                   System.out.println("Href: " + href);
                   System.out.println("------------------------------------------------");

               }
           }catch (Exception e){
               System.out.println("finished Scraping !!");
               break;
           }
       }
       return hospitalRecords;

    }

    public static Hospital getHospitalData(String link) throws IOException {
        Connection connection = Jsoup.connect(link);
        Document doc = connection.get();
        Hospital hospital = new Hospital();

        Element facilityContent = doc.selectFirst(".facilityContent");
        Elements facilityRowDetails = facilityContent.select(".facilityRowDetails");

        HashMap<String,String> information = new HashMap<>();
        for (Element row : facilityRowDetails) {
            String key = row.select(".infoTitle").text();
            String value = row.select(".infoContent").text();
            information.put(key,value);
        }


        // Extract information from HTML elements and set them in the Hospital object
        hospital.setAddress(information.getOrDefault(ADDRESS, "-"));
        hospital.setPhone(information.getOrDefault(PHONE, "-"));
        hospital.setFax(information.getOrDefault(FAX, "-"));
        hospital.setEmail(information.getOrDefault(EMAIL, "-"));
        hospital.setDirector(information.getOrDefault(DIRECTOR, "-"));
        hospital.setRegion(information.getOrDefault(REGION, "-"));
        hospital.setStatus(information.getOrDefault(STATUS, "-"));
        hospital.setRegisteredByMOPH(information.getOrDefault(REGISTERED, "-"));
        hospital.setOwnership(information.getOrDefault(OWNERSHIP, "-"));
        hospital.setNumberOfMedicineBeds(information.getOrDefault(MEDICINE_BEDS, "-"));
        hospital.setNumberOfSurgeryBeds(information.getOrDefault(SURGERY_BEDS, "-"));
        hospital.setNumberOfBGYNBeds(information.getOrDefault(BGYN_BEDS, "-"));
        hospital.setNumberOfPediatricBeds(information.getOrDefault(PEDIATRICS_BEDS, "-"));
        hospital.setNumberOfICUBeds(information.getOrDefault(ICU_BEDS, "-"));
        hospital.setNumberOfFullTimeDrs(information.getOrDefault(DOCTORS, "-"));
        hospital.setNumberOfFullTimeNurses(information.getOrDefault(NURSES, "-"));
        hospital.setGeneralLab(information.getOrDefault(GENERAL_LABS, "-"));
        hospital.setBloodBank(information.getOrDefault(BLOOD_BANK, "-"));
        hospital.setControllingDr(information.getOrDefault(CONTROLLING_DOCTOR, "-"));
        hospital.setControllingDrPhoneNumber(information.getOrDefault(CONTROLLING_DOCTOR_PHONE_NUMBER, "-"));
        hospital.setGeneralRadiology(information.getOrDefault(GENERAL_RADIOLOGY, "-"));
        hospital.setRadioTherapy(information.getOrDefault(RADIO_THERAPY, "-"));
        hospital.setMemberOfSoPH(information.getOrDefault(MOSOPH, "-"));
        hospital.setInvestmentAuthorizationNumber(information.getOrDefault(INVESTMENT_AUTH_NB, "-"));
        hospital.setConstructionAuthorizationNumber(information.getOrDefault(CONSTRUCTION_AUTH_NB, "-"));

        return hospital;
    }



    public static void main(String[] args) throws IOException {
        List<HospitalRecord> hospitalRecords = getRecords();
        for(HospitalRecord hospitalRecord : hospitalRecords){
            try{
                System.out.println(getHospitalData(hospitalRecord.detailsLink()));
            }catch (Exception e){
                System.out.println("\nError Fetching link : "+hospitalRecord.detailsLink()+" for hospital "+hospitalRecord.name()+"\n");
            }
        }

    }



}
