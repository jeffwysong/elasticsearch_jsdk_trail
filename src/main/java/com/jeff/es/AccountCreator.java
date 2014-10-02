/*
 * Copyright (c) 2014, Stormpath, Inc.
 * All rights reserved.
 */
package com.jeff.es;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.jeff.es.data.Base62TimeUuidCreator.createBase62TimeUuid;

/**
 * @author Jeff Wysong
 * @since 10/1/14 3:57 PM
 */
public class AccountCreator {

    private static final ObjectMapper mapper = new ObjectMapper();

    private static Random ran = new Random();

    private static String[] last_names = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor", "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin", "Thompson", "Garcia", "Martinez", "Robinson", "Clark", "Rodriguez", "Lewis", "Lee", "Walker", "Hall", "Allen", "Young", "Hernandez", "King", "Wright", "Lopez", "Hill", "Scott", "Green", "Adams", "Baker", "Gonzalez", "Nelson", "Carter", "Mitchell", "Perez", "Roberts", "Turner", "Phillips", "Campbell", "Parker", "Evans", "Edwards", "Collins", "Stewart", "Sanchez", "Morris", "Rogers", "Reed", "Cook", "Morgan", "Bell", "Murphy", "Bailey", "Rivera", "Cooper", "Richardson", "Cox", "Howard", "Ward", "Torres", "Peterson", "Gray", "Ramirez", "James", "Watson", "Brooks", "Kelly", "Sanders", "Price", "Bennett", "Wood", "Barnes", "Ross", "Henderson", "Coleman", "Jenkins", "Perry", "Powell", "Long", "Patterson", "Hughes", "Flores", "Washington", "Butler", "Simmons", "Foster", "Gonzales", "Bryant", "Alexander", "Russell", "Griffin", "Diaz", "Hayes", "Myers", "Ford", "Hamilton", "Graham", "Sullivan", "Wallace", "Woods", "Cole", "West", "Jordan", "Owens", "Reynolds", "Fisher", "Ellis", "Harrison", "Gibson"};
    private static String[] first_names = {"Mary", "Patricia", "Linda", "Barbara", "Elizabeth", "Jennifer", "Maria", "Susan", "Margaret", "Dorothy", "Lisa", "Nancy", "Karen", "Betty", "Helen", "Sandra", "Donna", "Carol", "Ruth", "Sharon", "Michelle", "Laura", "Sarah", "Kimberly", "Deborah", "Jessica", "Shirley", "Cynthia", "Angela", "Melissa", "Brenda", "Amy", "Anna", "Rebecca", "Virginia", "Kathleen", "Pamela", "Martha", "Debra", "Amanda", "Stephanie", "Carolyn", "Christine", "Marie", "Janet", "Catherine", "Frances", "Ann", "Joyce", "Diane", "Alice", "Julie", "Heather", "Teresa", "Doris", "Gloria", "Evelyn", "Jean", "Cheryl", "Mildred", "Katherine", "Joan", "Ashley", "Judith", "Rose", "Janice", "Kelly", "Nicole", "Judy", "Christina", "Kathy", "Theresa", "Beverly", "Denise", "Tammy", "Irene", "Jane", "Lori", "Rachel", "Marilyn"};
    private static final String DIGITS_AND_LETTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String CAP_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String[] COUNTRIES = {"AC","AD","AE","AF","AG","AI","AL","AM","AN","AO","AQ","AR","AS","AT","AU","AW","AX","AZ","BA","BB","BD","BE","BF","BG","BH","BI","BJ","BM","BN","BO","BR","BS","BT","BV","BW","BY","BZ","CA","CC","CD","CF","CG","CH","CI","CK","CL","CM","CN","CO","CR","CS","CU","CV","CX","CY","CZ","DE","DJ","DK","DM","DO","DZ","EC","EE","EG","EH","ER","ES","ET","EU","FI","FJ","FK","FM","FO","FR","FX","GA","GB","GD","GE","GF","GG","GH","GI","GL","GM","GN","GP","GQ","GR","GS","GT","GU","GW","GY","HK","HM","HN","HR","HT","HU","ID","IE","IL","IM","IN","IO","IQ","IR","IS","IT","JE","JM","JO","JP","KE","KG","KH","KI","KM","KN","KP","KR","KW","KY","KZ","LA","LB","LC","LI","LK","LR","LS","LT","LU","LV","LY","MA","MC","MD","ME","MF","MG","MH","MK","ML","MM","MN","MO","MP","MQ","MR","MS","MT","MU","MV","MW","MX","MY","MZ","NA","NC","NE","NF","NG","NI","NL","NO","NP","NR","NT","NU","NZ","OM","PA","PE","PF","PG","PH","PK","PL","PM","PN","PR","PS","PT","PW","PY","QA","RE","RS","RO","RU","RW","SA","SB","SC","SD","SE","SG","SH","SI","SJ","SK","SL","SM","SN","SO","SR","SS","ST","SU","SV","SY","SZ","TC","TD","TF","TG","TH","TJ","TK","TM","TN","TO","TP","TR","TT","TV","TW","TZ","UA","UG","UK","UM","US","UY","UZ","VA","VC","VE","VG","VI","VN","VU","WF","WS","XK","YE","YT","YU","ZA","ZM","ZW"};
    private static final String[] STATES = {"AL","MT","AK","NE","AZ","NV","AR","NH","CA","NJ","CO","NM","CT","NY","DE","NC","FL","ND","GA","OH","HI","OK","ID","OR","IL","PA","IN","RI","IA","SC","KS","SD","KY","TN","LA","TX","ME","UT","MD","VT","MA","VA","MI","WA","MN","WV","MS","WI","MO","WY"};
    private static final String[] CITIES = {"Montgomery","Helena","Juneau","Lincoln","Phoenix","Carson City","Little Rock","Concord","Sacramento","Trenton","Denver","Santa Fe","Hartford","Albany","Dover","Raleigh","Tallahassee","Bismarck","Atlanta","Columbus","Honolulu","Oklahoma City","Boise","Salem","Springfield","Harrisburg","Indianapolis","Providence","Des Moines","Columbia","Topeka","Pierre","Frankfort","Nashville","Baton Rouge","Austin","Augusta","Salt Lake City","Annapolis","Montpelier","Boston","Richmond","Lansing","Olympia","St. Paul","Charleston","Jackson","Madison","Jefferson City","Cheyenne"};
    private static final String[] ADDRESS_SUFFIX= {"Drive", "Street", "Ave"};
    public static final String[] LABELS = {"home", "oldHome", "work", "parents", "lovers", "neighbor"};
    public static final String[] CUSTOM_DATA_ATTRIBS = {"favorite_color", "age", "glasses", "random_long"};
    public static final String[] COLORS = {"red", "orange", "yellow", "green", "blue", "indigo", "violet"};

    private static final String[] TENANT_UID = {"6kreTfmfJAvYDHr7WZO8k2","6ks4PzXlQdko4QNvkMD93W","6ksHOCkOOxTPcokC3VTupK","6ksYgMb2u13Gu5KsL6pz5a","6kspyZlmJew6oB1Poy3Jxu","6ktBaqEh5DI9gn85fn4UYo","6ktSt6jVPRTyDhuULu963C","6ktkBIFCMnDJoO36EdQncU","6ku1TTktK8wfP4Bi7MiVBm","6kuERgxWISfGxSXyQVzGxa"};
    private static final Integer[] TENANT_ID = {1,2,3,4,5,6,7,8,9,10};

    private static Map<Integer, Integer[]> tenantDirIdMap;
    private static Map<String, String[]> tenantDirUidMap;


    static {
        tenantDirIdMap = new HashMap<>();
        int counter = 1;
        for (Integer id : TENANT_ID) {
            Integer[] intArray = new Integer[10];
            for (int i = 0; i < 10; i++) {
                intArray[i] = counter++;
            }
            tenantDirIdMap.put(id, intArray);
        }

        tenantDirUidMap = new HashMap<>();
        for (String uid : TENANT_UID) {
            String[] uidArray = new String[10];
            for (int i = 0; i < 10; i++) {
                uidArray[i] = createBase62TimeUuid();
            }
            tenantDirUidMap.put(uid, uidArray);
        }
    }

    public static String getRandomAccountJsonString() {
        try {
            String firstName = getRandomGivenName();
            String lastName = getRandomSurname();
            Map<String, Object> accountMap = new LinkedHashMap<>();
            accountMap.put("email", getEmailAddress(firstName, lastName));
            accountMap.put("password", "Storm:path1");
            accountMap.put("givenName", firstName);
            accountMap.put("surname", lastName);
            Map<String, Object> customDataMap = new LinkedHashMap<>();
            customDataMap.put("postalAddresses", populateAddresses());
            createOtherRandomCustomData(customDataMap);
            accountMap.put("customData", customDataMap);
            int tenantIndex = ran.nextInt(10);
            int tenantId = tenantIndex + 1;
            String tenantUid = TENANT_UID[tenantIndex];
            int dirIndex = ran.nextInt(10);
            accountMap.put("dirId", tenantDirIdMap.get(tenantId)[dirIndex]);
            accountMap.put("dirUId", tenantDirUidMap.get(tenantUid)[dirIndex]);
            accountMap.put("tenantId", tenantId);
            accountMap.put("tenantUid", tenantUid);
            return mapper.writeValueAsString(accountMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void createOtherRandomCustomData(Map<String, Object> customDataMap) {
        int moreCustomAttributes = ran.nextInt(4);
        switch (moreCustomAttributes) {
            case 3:
                customDataMap.put(CUSTOM_DATA_ATTRIBS[2], ran.nextBoolean());
            case 2:
                customDataMap.put(CUSTOM_DATA_ATTRIBS[1], ran.nextInt(100));
            case 1:
                customDataMap.put(CUSTOM_DATA_ATTRIBS[0], COLORS[ran.nextInt(COLORS.length)]);
            default:
                //do nothing
                break;
        }
        if (ran.nextBoolean()) {
            Map<String, Object> anotherMap = new LinkedHashMap<>();
            createOtherRandomCustomData(anotherMap);
            customDataMap.put("masCustomData", anotherMap);
        }
    }


    public static void main(String[] args) throws JsonProcessingException {
//        AccountCreator accountCreator = new AccountCreator();
//        Map<String, Object> addressMap = new HashMap<>();
//        for (int i = 0; i < ran.nextInt(4); i++) {
//            System.out.println(i);
//            accountCreator.printMap(accountCreator.createAddress());
//            System.out.println("***************");
//        }
//        addressMap.put("postalAddresses", accountCreator.populateAddresses());
//        System.out.println(accountCreator.mapper.writeValueAsString(addressMap));

        for (int i = 0; i < 40; i++) {
            System.out.println(getRandomAccountJsonString());
//            System.out.println(ran.nextInt(4));
        }
    }


    private static String getRandomGivenName() {
        return first_names[ran.nextInt(first_names.length)];
    }
    private static String getRandomSurname() {
        return last_names[ran.nextInt(last_names.length)];
    }

    private static String getEmailAddress(String first, String last) {
        StringBuilder sb = new StringBuilder();
        sb.append(first);
        sb.append(".");
        sb.append(last);
        sb.append("@mailinator.com");
        return sb.toString();
    }

    private static List<Map<String, Object>> populateAddresses() {
        List<Map<String, Object>> addresses = new ArrayList<>();
        for (int i = 0; i < ran.nextInt(4); i++) {
            addresses.add(createAddress());
        }
        return addresses;
    }

    private static Map<String, Object> createAddress() {
        Map<String, Object> addressMap = new LinkedHashMap<>();
        addressMap.put("label", getRandomLabel());
        addressMap.put("street1", getRandomStreet());
        addressMap.put("street2", getRandomStreet2());
        addressMap.put("city", getRandomCity());
        addressMap.put("state", getRandomState());
        addressMap.put("zip", getRandomZip());
        addressMap.put("country", getRandomCountry());
        return addressMap;
    }

    private static String getRandomLabel() {
        return LABELS[ran.nextInt(LABELS.length)];
    }
    private static String getRandomStreet() {
        StringBuilder sb = new StringBuilder();
        sb.append(ran.nextInt(9999));
        sb.append(" ");
        sb.append(first_names[ran.nextInt(first_names.length)]);
        sb.append(" ");
        sb.append(ADDRESS_SUFFIX[ran.nextInt(3)]);
        return sb.toString();
    }
    private static String getRandomStreet2() {
        StringBuilder sb = new StringBuilder();
        if (ran.nextBoolean()) {
            sb.append("Suite ");
            sb.append(ran.nextInt(999));
        }
        return sb.toString();
    }
    private static String getRandomCity() {
        return CITIES[ran.nextInt(CITIES.length)];
    }
    private static String getRandomState() {
        return STATES[ran.nextInt(STATES.length)];
    }
    private static int getRandomZip() {
        StringBuilder sb = new StringBuilder();
        sb.append(ran.nextInt(9)+1);
        sb.append(ran.nextInt(10));
        sb.append(ran.nextInt(10));
        sb.append(ran.nextInt(10));
        sb.append(ran.nextInt(10));
        return Integer.valueOf(sb.toString());
    }
    private static String getRandomCountry() {
        if (ran.nextBoolean()) {
            return COUNTRIES[ran.nextInt(COUNTRIES.length)];
        } else {
            return "US";
        }
    }

    private String createStringOfCapitalLetter(int size) {
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            sb.append(CAP_LETTERS.charAt(ran.nextInt(CAP_LETTERS.length())));
        }
        return sb.toString();
    }

    private void printMap(Map<String, Object> map) {
        for (Map.Entry<String, Object> stringObjectEntry : map.entrySet()) {
            System.out.println(stringObjectEntry.getKey() + ":" + stringObjectEntry.getValue());
        }
    }
}
