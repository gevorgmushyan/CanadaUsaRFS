package policy;


import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.testng.Assert.assertTrue;

@Data
public class PolicyHolder implements Serializable {
    private String firstName;
    private String lastName;
    private String mailingAddress;
    private String city;
    private String zipCode;
    private String email;
    private String phoneNumber;
    private String cardNumber;
    private String exp;
    private String cvc;
    private String state;
    private String emailMe;
    private String password;
    private String IPID;
    private String acctType;
    private String serviceId;
    private List<Quote> quoteList;

    public static final String[][] zipCodeMap = {
            {"35243", "3100 Cahaba Village Plz", "AL", "Mountain Brk"},
            {"72703", "3425 N College Ave", "AR", "Fayetteville"},
            {"85016", "4701 N 20th St", "AZ", "Phoenix"},
            {"80012", "11831 E Kepner Dr", "CO", "Aurora"},
            {"06033", "55 Welles St", "CT", "Glastonbury"},
            {"96815", "2569 Cartwright Rd", "HI", "Colfax"},
            {"50054", "11000 Federal Ave", "IA", "Honolulu"},
            {"67205", "10555 W 21st St N Ste 600", "KS", "Wichita"},
            {"41339", "540 Jett Dr", "KY", "Jackson"},
            {"68123", "2500 Bellevue Medical Center Dr", "NE", "Bellevue"},
            {"87102", "504 Elm St NE", "NM", "Albuquerque"},
            {"28461", "924 N Howe St", "NC", "Southport"},
            {"39564", "601 Washington Ave", "MS", "Ocean Springs"},
            {"43025", "403 E Cumberland St", "OH", "Hebron"},
            {"37353", "281 Pleasant Grove Rd", "TN", "Mc Donald"},
            {"57350", "1865 Dakota Ave S", "SD", "Huron"},
            {"54455", "553 State Highway 153", "WI", "Mosinee"},


            {"80246", "715 S Forest St", "CO", "Denver"},
            {"19103", "1901 John F Kennedy Blvd", "PA", "Philadelphia"},
            {"67846", "905 E Kansas Plz", "KS", "Garden City"},
            {"04736", "116 Washburn St", "ME", "Caribou"},
            {"49684", "616 W Tenth St", "MI", "Traverse City"},
            {"58504", "926 E Bismarck Expy", "ND", "Bismarck"},
            {"11211", "247 Metropolitan Ave", "NY", "Brooklyn"},
            {"29033", "1035 Indigo Ave", "SC", "Cayce"},
            {"78204", "226 Dwyer Ave", "TX", "San Antonio"},
            {"98116", "2333 44th Ave SW", "WA", "Seattle"}
    };

    public static final String[][] postalCodeMap = {
            {"T8V 3Y4", "11633 100 St", "AB", "Grande Prairie"},
            {"V1C 3R7", "600 Cranbrook St N", "BC", "Cranbrook"},
            {"R0B 0E0", "Division No. 23", "MB", "Unorganized"},
            {"A1C 2H9", "222 Lemarchant Rd", "NL", "St. John's"},
            {"B3H 3G5", "1980 Robie St", "NS", "Halifax"},
            {"X1A 2N2", "4401 Franklin Ave", "NT", "Yellowknife"},
            {"X0C 0H0", "Ukkusiksalik National Park", "NU", "Unorganized"},
            {"L3T 5W6", "340 John St", "ON", "Thornhill" },
            {"C1A 8T5", "60 Riverside Dr", "PE", "Charlottetown"},
            {"S3N 2A7", "127 Gladstone Ave N", "SK", "Yorkton"},
            {"Y1A 4P9", "9021 Quartz Rd", "YT", "Whitehorse"},
    };

    /* public PolicyHolder(int policyCount) {
         this();
         assertTrue(policyCount > 0, "The policyCount should be > 0 .");
         for (int i = 0; i < policyCount - 1; i++) {
             Quote quote = new Quote();
             quote.setName("Pet" + (i + 1));
             setQuote(quote);
         }
     }*/
    private String[] getAddress(boolean isUsa, String state) {
        String[][] addresses = (isUsa) ? zipCodeMap : postalCodeMap;
        for (int i = 0; i < addresses.length; i++) {
            if (addresses[i][2].toLowerCase().trim().equals(state.toLowerCase().trim()))
                return addresses[i];
        }
        return null;
    }

    public List<Quote> getQuoteList() {
        if (quoteList == null)
            quoteList = new ArrayList<Quote>();
        return quoteList;
    }

    public int getActivePoliciesCount() {
        if (quoteList == null)
            return 0;
        int count = 0;
        for (int i = 0; i < quoteList.size(); i++) {
            if (quoteList.get(i).isQuoteActive())
                count++;
        }
        return count;
    }

    public int getInactivePoliciesCount() {
        if (quoteList == null)
            return 0;
        int count = 0;
        for (int i = 0; i < quoteList.size(); i++) {
            if (!quoteList.get(i).isQuoteActive())
                count++;
        }
        return count;
    }

    public void addQuoteList(List<Quote> quoteList1) {
        if (quoteList == null)
            quoteList = new ArrayList<>();
        quoteList.addAll(quoteList1);
    }


    public void addQuote(Quote quoteList1) {
        if (quoteList == null)
            quoteList = new ArrayList<>();
        quoteList.add(quoteList1);
    }

    public Quote getQuote(int petNum) {
        assertTrue(quoteList != null && petNum >= 0 && petNum < quoteList.size());
        return quoteList.get(petNum);
    }

    public void setQuote(Quote quote) {
        if (quoteList == null)
            quoteList = new ArrayList<>();
        quoteList.add(quote);
    }

    public String getZipOrPostalCode() {
        return zipCode;
    }

    private void setZipOrPostalMailingStateCity(String[][] codeMap) {
        int num = new Random().nextInt(codeMap.length);
        setZipCode(codeMap[num][0]);
        setMailingAddress(codeMap[num][1]);
        setState(codeMap[num][2]);
        setCity(codeMap[num][3]);
    }

    public void setZipOrPostalMailingAndState(int region) {
        assertTrue(region == 1 || region == 2);
        if (region == 1) {
            setZipOrPostalMailingStateCity(zipCodeMap);
        } else {
            setZipOrPostalMailingStateCity(postalCodeMap);
        }
    }

    public void setZipOrPostalMailingAndState(String []address) {
        assertTrue(address != null);
        setZipCode(address[0]);
        setMailingAddress(address[1]);
        setState(address[2]);
        setCity(address[3]);
    }

    public void setServiceId(int region) {
        assertTrue(region == 1 || region == 2);
        if (region == 1) {
            serviceId = "1";
        } else {
            serviceId = "2";
        }
    }

    public void setAcctType(int region) {
        assertTrue(region == 1 || region == 2);
        if (region == 1) {
            acctType = "1";
        } else {
            acctType = "0";
        }
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailMe() {
        return emailMe;
    }

    public void setEmailMe(String emailMe) {
        this.emailMe = emailMe;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
