/*
 Scotia Airlines - HND Computer Science
 Version 1.4
 @Author: Dean D. Reid
 */
package couk.deanreid.scotiaairlines;

public class PassengerBusiness extends Passenger {

    //Attributes
    private String companyName;

    //Getter and Setter
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyNameIn) {
        companyName = companyNameIn;
    }

    //Constructors
    public PassengerBusiness() {
        super();
    }

    public PassengerBusiness(String companyNameIn, float discountAmountIn, String passengerNameIn) {
        super(discountAmountIn, passengerNameIn);

        companyName = companyNameIn;
        discountAmountIn = 0.75f;

    }

    /**
     * Set Discount Amount
     * @param passengerNameIn
     * @param companyNameIn
     */
    public PassengerBusiness(String passengerNameIn, String companyNameIn) {
        super(0.75f, passengerNameIn);

        companyName = companyNameIn;

    }

}
