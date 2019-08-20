package rfCalculator;

import policy.PolicyHolder;
import policy.Quote;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static rfCalculator.PolicyPlan.*;

public class CRF {
    public Policy policy;
    public MyExcel excel;
    public final boolean isUsa;
    //public String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\Blueprint (Rates) 20190423 0525 pm.xlsx";
    //public String filePath_usa = System.getProperty("user.dir") + "\\src\\main\\resources\\Blueprint - US (Rates) 20190703 0850 am.xlsx";
    //public String filePath_usa = System.getProperty("user.dir") + "\\src\\main\\resources\\Blueprint - US (Rates) 20190808 1215 pm.xlsx";
    public String filePath_usa = System.getProperty("user.dir") + "\\src\\main\\resources\\Blueprint - US (Rates) 20190809 1053 am.xlsx";
    public String filePath_usa_2018 = System.getProperty("user.dir") + "\\src\\main\\resources\\Blueprint (Rates) 20190312 0340 pm.xlsx";
    //public String filePath_canada = System.getProperty("user.dir") + "\\src\\main\\resources\\Blueprint - Canada (Rates) 20190703 1145 am.xlsx";
    public String filePath_canada = System.getProperty("user.dir") + "\\src\\main\\resources\\Blueprint - Canada (Rates) 20190719 0215 pm.xlsx";
    public String filePath_canada_2018 = System.getProperty("user.dir") + "\\src\\main\\resources\\Blueprint - Canada (2018 Rates)_20181106 0905 am.xlsx";

    public CRF() {
        ScanData scan = new ScanData();
        this.isUsa = scan.readRegion();
        String rfYear = scan.readRFYear();

        Thread thread = new Thread("New Thread") {
            public void run() {
                excel = new MyExcel(isUsa ? getFilePathUsa(rfYear) : getFilePathCanada(rfYear));
            }
        };
        thread.start();
        policy = scan.scanAndGetPolicy(isUsa, rfYear);
    }

    public CRF(boolean isUsa, String rfYear, PolicyHolder policyHolder) {
        this.isUsa = isUsa;
        if (!rfYear.equals("2018") && !rfYear.equals("2019"))
            throw new IllegalArgumentException("Illegal rfYear argument");

        Thread thread = new Thread("New Thread1") {
            @Override
            public void run() {
                excel = new MyExcel(isUsa ? getFilePathUsa(rfYear) : getFilePathCanada(rfYear));
            }
        };
        thread.start();

        policy = new Policy(rfYear);

        Quote quote = policyHolder.getQuote(0);

        policy.setAge(Integer.parseInt(quote.getAge()));
        policy.setBreed(quote.getBreed());
        policy.setPetType(quote.getType().toLowerCase().equals("dog") ? PetType.DOG : PetType.CAT);
        policy.setWorkingDog(quote.isWorkingDog());
        policy.setPolicyScheme(formatSchemeValue(quote.getAnnualCoverage()));
        policy.setCopay(formatSchemeValue(quote.getReimbursement()));
        policy.setDeductible(formatSchemeValue(quote.getAnnualDeductible()));
        policy.setState(formatSchemeValue(policyHolder.getState()));
        policy.setZipCode(policyHolder.getZipOrPostalCode());
    }

    private String getFilePathUsa(String year) {
        switch (year) {
            case "2018":
                return filePath_usa_2018;
            case "2019":
                return filePath_usa;
            default:
                throw new IllegalArgumentException("Illegal argument year: " + year);
        }
    }

    private String getFilePathCanada(String year) {
        switch (year) {
            case "2019":
                return filePath_canada;
            case "2018":
                return filePath_canada_2018;
            default:
                throw new IllegalArgumentException("Illegal argument year: " + year);
        }
    }

    public String formatSchemeValue(String value) {
        return value.trim().toLowerCase().replaceAll("[$%,.]", "");
    }

    private int getBaseRateColumn() {
        String scheme = policy.getPolicyScheme();
        for (int i = 0; i < baseRates.length; i++) {
            if (baseRates[i].toLowerCase().equals(scheme))
                if (policy.getPetType().equals(PetType.CAT))
                    return i + 2;
                else
                    return i + baseRates.length + 2;
        }
        return -1;
    }

    private int getBaseRateRow() {
        excel.openWorkSheet("Base Rate");
        //return excel.finedInColumn(0, policy.getState(), 4, 54);
        String states[] = isUsa ? states_usa : states_canada;
        for (int i = 0; i < states_usa.length; i++) {
            if (states[i].toLowerCase().equals(policy.getState()))
                return i + 3;
        }
        return -1;
    }

    public double getBaseRate() {
        excel.openWorkSheet("Base Rate");
        int i = getBaseRateRow();
        if (i == -1) {
            System.out.println("Cannot fined state in sheet.");
            return -1;
        }
        int j = getBaseRateColumn();
        if (j == -1) {
            System.out.println("Cannot fined scheme for pet in sheet.");
            return -1;
        }

        return Double.parseDouble(excel.readCell(i, j));
    }

    public double getBreedAgeFactor() {
        excel.openWorkSheet("Breed to Breed Grp Mapping 2019");
        String breedVal = this.policy.getPetType().equals(PetType.DOG) ? "ppdog001" : "ppcat001";
        int i = excel.finedInColumn(1, policy.getBreed(), 2, breedVal, 1, 626);
        if (i == -1) {
            System.out.println("Cannot fined breed in sheet.");
            return -1;
        }
        String groupId = excel.readCell(i, 7);
        System.out.println("Pet group Id is: " + groupId);

        excel.openWorkSheet("Combined Breed Grp-Age Factors ");

        int baseLine = excel.finedInColumn(0, policy.getPetType().toString(), 1, groupId, isUsa ? 10 : 8, 1067);
        if (baseLine == -1) {
            System.out.println("Cannot fined group Id for pet in sheet.");
            return -1;
        }

        return Double.parseDouble(excel.readCell(baseLine + policy.getAge(), 3));
    }

    public double getBreedFactor2018() {
        String sheetName = "Breed";
        switch (policy.getState().toUpperCase().trim()) {
            case "FL":
                sheetName = "FL Breed";
                break;
            case "NJ":
                sheetName = "NJ Breed";
                break;
        }
        excel.openWorkSheet(sheetName);
        String breedVal = this.policy.getPetType().equals(PetType.DOG) ? "ppdog001" : "ppcat001";
        int i = excel.finedInColumn(1, policy.getBreed(), 2, breedVal, 1, 628);
        if (i == -1) {
            System.out.println("Cannot fined breed in sheet.");
            return -1;
        }

        String breedFactor = excel.readCell(i + 1, 4);

        return Double.parseDouble(breedFactor);
    }

    public double getAgeFactor2018() {
        excel.openWorkSheet("Age");
        int baseLine = excel.finedInColumn(0, policy.getState(), 1, policy.getPetType().toString(), 3, 2656);
        if (baseLine == -1) {
            System.out.println("Cannot fined group Id for pet in sheet.");
            return -1;
        }

        return Double.parseDouble(excel.readCell(baseLine + policy.getAge() + 1, 4));
    }

    public String getBreedAgeFactor(String year) {
        switch (year) {
            case "2018":
                return getBreedFactor2018() + "\nAge Factor: " + getAgeFactor2018();
            case "2019":
                return "" + getBreedAgeFactor();
            default:
                return "-1";
        }
    }

    public double getAreaLookupUsa() {
        excel.openWorkSheet("Zip Code Look Up");
        int i = excel.finedInColumn(0, policy.getZipCode(), 3, 38250);
        if (i == -1) {
            System.out.println("Cannot fined zip in sheet.");
            return -1;
        }
        String areaLookup = excel.readCell(isUsa ? i + 1 : i, 4);
        System.out.println("Pet area lookup is: " + areaLookup);

        excel.openWorkSheet("Area 2019");
        int num;
        try {
            num = (int) Double.parseDouble(areaLookup.trim());
        } catch (NumberFormatException e) {
            System.out.println("Bad format for area lookup.");
            return -1;
        }
        int deep = policy.getPetType().equals(PetType.CAT) ? 0 : 7;
        return Double.parseDouble(excel.readCell(2 + num + deep, 3));
    }

    public double getAreaLookupUsa2018() {
        excel.openWorkSheet("Zip Code Look Up");
        int i = excel.finedInColumn(0, policy.getZipCode(), 3, 38250);
        if (i == -1) {
            System.out.println("Cannot fined zip in sheet.");
            return -1;
        }
        String areaLookup = excel.readCell(isUsa ? i + 1 : i, 4);
        System.out.println("Pet area lookup is: " + areaLookup);

        excel.openWorkSheet("Area");
        int num;
        try {
            num = (int) Double.parseDouble(areaLookup.trim());
        } catch (NumberFormatException e) {
            System.out.println("Bad format for area lookup.");
            return -1;
        }
        int exception = excel.finedInColumn(0, policy.getState(),
                1, policy.getPetType().toString(),
                2, policy.getAge()+"",
                7, 1000);
        if (exception == -1) {
            int deep = policy.getPetType().equals(PetType.CAT) ? 0 : 6;
            return Double.parseDouble(excel.readCell(2 + num + deep, 3));
        }
        return Double.parseDouble(excel.readCell(exception, 3));
    }

    public double getAreaLookupCanada() {
        Map<String, Integer> areaLookup = new HashMap();
        areaLookup.put("NL", 3);
        areaLookup.put("NS", 2);
        areaLookup.put("PE", 1);
        areaLookup.put("ON", 3);
        areaLookup.put("MB", 2);
        areaLookup.put("SK", 2);
        areaLookup.put("AB", 4);
        areaLookup.put("BC", 3);
        areaLookup.put("NU", 1);
        areaLookup.put("NT", 1);
        areaLookup.put("YT", 1);


        if (policy.getState().trim().toUpperCase().equals("ON")
                && policy.getZipCode().trim().toUpperCase().startsWith("M")) {
            areaLookup.put("ON", 4);
        }

        if (policy.getState().trim().toUpperCase().equals("AB")
                && policy.getZipCode().trim().toUpperCase().startsWith("T0")) {
            areaLookup.put("AB", 3);
        }

        if (policy.getState().trim().toUpperCase().equals("BC")
                && policy.getZipCode().trim().toUpperCase().startsWith("V0")) {
            areaLookup.put("BC", 2);
        }

        int num = areaLookup.get(policy.getState().trim().toUpperCase());
        System.out.println("Pet area lookup is: " + num);

        excel.openWorkSheet("Area");
        int deep = policy.getPetType().equals(PetType.CAT) ? 0 : 4;

        return Double.parseDouble(excel.readCell(2 + num + deep, 2));
    }

    public double getAreaLookup(String year) {
        switch (year) {
            case "2018":
                return getAreaLookupUsa2018();
            case "2019":
                return getAreaLookup();
            default:
                return -1;
        }
    }

    public double getAreaLookup() {
        return isUsa ? getAreaLookupUsa() : getAreaLookupCanada();
    }

    private double getDeductibleFactorUsa() {
        excel.openWorkSheet("Deductible");
        int i = excel.finedInColumn(0, policy.getState(), 3, 56);
        if (i == -1) {
            System.out.println("Cannot fined state in sheet.");
            return -1;
        }
        int k = -1;
        for (int j = 0; j < deductibles.length; j++) {
            if (deductibles[j].toLowerCase().equals(policy.getDeductible())) {
                k = j;
                break;
            }
        }
        if (k == -1) {
            System.out.println("Bad deductible.");
            return -1;
        }
        int j = (policy.getPetType().equals(PetType.DOG) ? deductibles.length : 0) + k + 1;
        String deductible = excel.readCell(i, j);
        return Double.parseDouble(deductible);
    }

    public double getDeductibleFactorCanada() {
        excel.openWorkSheet("Deductible");
        int i = excel.finedInColumn(0, policy.getState().toUpperCase(), 2, 25);
        if (i == -1) {
            System.out.println("Cannot fined state in sheet.");
            return -1;
        }
        int k = -1;
        for (int j = 0; j < deductibles.length; j++) {
            if (deductibles[j].toLowerCase().equals(policy.getDeductible().trim().toLowerCase())) {
                k = j;
                break;
            }
        }
        if (k == -1) {
            System.out.println("Bad deductible.");
            return -1;
        }
        int j = (policy.getPetType().equals(PetType.DOG) ? deductibles.length : 0) + k + 1;
        return Double.parseDouble(excel.readCell(i + 1, j));
    }

    public double getDeductibleFactor() {
        return isUsa ? getDeductibleFactorUsa() : getDeductibleFactorCanada();
    }

    public double getCopayFactor() {
        excel.openWorkSheet("Copay");
        int i = excel.finedInColumn(0, policy.getState(), isUsa ? 3 : 2, isUsa ? 56 : 25);
        if (i == -1) {
            System.out.println("Cannot fined state in sheet.");
            return -1;
        }
        int k = -1;
        for (int j = 0; j < copays.length; j++) {
            if (copays[j].toLowerCase().equals(policy.getCopay())) {
                k = j;
                break;
            }
        }
        if (k == -1) {
            System.out.println("Bad copay.");
            return -1;
        }
        int j = (policy.getPetType().equals(PetType.DOG) ? copays.length : 0) + k + 1;
        return Double.parseDouble(excel.readCell(isUsa ? i : i + 1, j));
    }

    public double getAnnualDeductible() {
        excel.openWorkSheet("Annual Deductible");
        int i = excel.finedInColumn(0, policy.getState(), 1, policy.getPetType().toString(), isUsa ? 3 : 2, isUsa ? 108 : 40);
        if (i == -1) {
            System.out.println("Cannot fined state or pet in sheet.");
            return -1;
        }
        return Double.parseDouble(excel.readCell(isUsa ? i : i + 1, 2));
    }

    private double getAgeAtInceptionFactorUsa() {
        excel.openWorkSheet("Age-At-Inception");
        int i = excel.finedInColumn(0, policy.getState(), 1, policy.getPetType().toString(), 3, 2655);
        if (i == -1) {
            System.out.println("Cannot fined state or pet in sheet.");
            return -1;
        }
        String inception = excel.readCell((isUsa ? i + 1 : i) + policy.getAge(), 4);
        return Double.parseDouble(inception);
    }

    private double getSurchargeFactorUsaAndCanada() {
        excel.openWorkSheet("Surcharges");
        int i = excel.finedInColumn(0, policy.getState(), 2, 55);
        if (i == -1) {
            System.out.println("Cannot fined state or pet in sheet.");
            return -1;
        }
        i = isUsa ? i : i + 1;
        String surchargeFactor = excel.readCell(i, 1);
        return Double.parseDouble(surchargeFactor);
    }

    private double getAgeAtInceptionFactorCanada() {
        excel.openWorkSheet("Age-At-Inception");
        if (policy.getAge() > 20)
            throw new IllegalStateException("The selected pet Age should be less then 21 years.");

        int i = excel.finedInColumn(0, policy.getState(), 2, 700);
        if (i == -1) {
            System.out.println("Cannot fined state or pet in sheet.");
            return -1;
        }
        int petTypeIndex = (policy.getPetType().equals(PetType.DOG) ? 2 : 1);
        return Double.parseDouble(excel.readCell(i + 2 * policy.getAge() + petTypeIndex, 4));
    }

    public double getAgeAtInceptionFactor() {
        return isUsa ? getAgeAtInceptionFactorUsa() : getAgeAtInceptionFactorCanada();
    }

    public double getSurchargeFactor(boolean isWorkingDog) {
        if (isWorkingDog)
            return getSurchargeFactorUsaAndCanada();
        return 1.0;
    }

    public static void disableWarning() {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            Unsafe u = (Unsafe) theUnsafe.get(null);

            Class cls = Class.forName("jdk.internal.module.IllegalAccessLogger");
            Field logger = cls.getDeclaredField("logger");
            u.putObjectVolatile(cls, u.staticFieldOffset(logger), null);
        } catch (Exception e) {
            // ignore
        }
    }

    public static void main(String[] args) {
        disableWarning();
        CRF calculator = new CRF();
        System.out.println();
        System.out.println("Base Rate: " + calculator.getBaseRate());
        System.out.println("Breed Factor: " + calculator.getBreedAgeFactor(calculator.policy.getYear()));
        System.out.println("Area Lookup: " + calculator.getAreaLookup(calculator.policy.getYear()));
        System.out.println("Deductible Factor: " + calculator.getDeductibleFactor());
        System.out.println("Copay Factor: " + calculator.getCopayFactor());
        System.out.println("Surcharge Factor " + calculator.getSurchargeFactor(calculator.policy.isWorkingDog()));
        System.out.println("AnnualDeductible: " + calculator.getAnnualDeductible());
        System.out.println("Age-At-Inception Factor: " + calculator.getAgeAtInceptionFactor());
    }
}
//mvn clean assembly:single