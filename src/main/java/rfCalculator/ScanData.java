package rfCalculator;

import java.util.List;
import java.util.Scanner;

import static rfCalculator.PolicyPlan.*;

public class ScanData {
    private Policy policy;
    private Scanner scanner;

    public ScanData() {
        scanner = new Scanner(System.in);
    }

    public Policy scanAndGetPolicy(boolean isUsa) {

        policy = new Policy();

        readPolicyScheme();
        readDeductible();
        readCopay();
        readPetType();
        readPetBreed();
        readPetAge();
        readZipOrPostalCode(isUsa);
        readState(isUsa);
        readIsWorkingDog();

        return policy;
    }

    private String checkValue(String[] array, String value) {
        if (value == null)
            return value;
        value = value.trim().toLowerCase();
        for (String sc : array)
            if (value.equals(sc.toLowerCase()))
                return value;
        return null;
    }

    private String checkZipOrPostalFormat(boolean isUsa, String zip) {
        if (zip == null)
            return zip;

        zip = zip.trim();

        if (isUsa) {
            if (zip.matches("[0-9]+") && (zip.length() == 5))
                return zip;
        } else {
            if (zip.matches("^((\\d{5}-\\d{4})|(\\d{5})|([A-Z]\\d[A-Z]\\s\\d[A-Z]\\d))$"))
                return zip;
        }

        return null;
    }

    private int checkPetAge(String age) {
        if (age == null)
            return -1;

        age = age.trim();
        int i;
        try {
            i = Integer.parseInt(age);
        } catch (NumberFormatException e) {
            return -1;
        }
        if (i < 0)
            return -1;
        return i;
    }

    private String readValue(String array[]) {
        String str = scanner.nextLine();
        String result;
        while (null == (result = checkValue(array, str))) {
            System.out.println("Please enter acceptable value.");
            str = scanner.nextLine();
        }
        return result;
    }

    private void readPolicyScheme() {
        System.out.print("Enter Policy Scheme: ");
        policy.setPolicyScheme(readValue(baseRates));
    }

    private void readDeductible() {
        System.out.print("Enter Deductible: ");
        policy.setDeductible(readValue(deductibles));
    }

    private void readCopay() {
        System.out.print("Enter Copay: ");
        policy.setCopay(readValue(copays));
    }

    private void readPetType() {
        System.out.print("Enter Pet Type (c/d): ");
        policy.setPetType(
                readValue(petTypes).equals("d") ? PetType.DOG : PetType.CAT);
    }

    private void readPetBreed() {
        System.out.print("Enter Pet Breed: ");
        switch (policy.getPetType()) {
            case DOG:
                policy.setBreed(readValue(dogBreed));
                break;
            case CAT:
                policy.setBreed(readValue(catBreed));
                break;
        }
    }

    private void readPetAge() {
        System.out.print("Enter Pet Age: ");
        String str = scanner.nextLine();
        int result;
        while (-1 == (result = checkPetAge(str))) {
            System.out.println("Please enter valid age.");
            str = scanner.nextLine();
        }
        policy.setAge(result);
    }

    private void readZipOrPostalCode(boolean isUsa) {
        System.out.print("Enter Zip/Postal Code: ");
        String str = scanner.nextLine();
        String result;
        while (null == (result = checkZipOrPostalFormat(isUsa, str))) {
            System.out.println("Please enter valid Zip/Postal code.");
            str = scanner.nextLine();
        }
        policy.setZipCode(result);
    }

    private void readState(boolean isUsa) {
        System.out.print("Enter State: ");
        policy.setState(readValue(isUsa ? states_usa : states_canada));
    }

    private void readIsWorkingDog() {
        if (policy.getPetType().equals(PetType.DOG)) {
            System.out.print("Is your dog working? (t/f): ");
            policy.setWorkingDog(readValue(new String[]{"t", "f"}).equals("t"));
        }
    }
}
