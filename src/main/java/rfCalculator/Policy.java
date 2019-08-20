package rfCalculator;

import lombok.Data;

@Data
public class Policy {

    private String year;
    private String policyScheme;
    private String deductible;
    private String copay;
    private PetType petType;
    private String breed;
    private int age;
    private String zipCode;
    private String state;
    private boolean isWorkingDog;

    public Policy(String year) {
        this.year = year;
    }

    public Policy() {

    }
}
