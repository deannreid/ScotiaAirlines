/*
 Scotia Airlines - HND Computer Science
 Program Version: 2.9
 Code Version: 1.3
 @Author: Dean D. Reid
 */
package couk.deanreid.scotiaairlines.core;

public class PassengerBusiness extends Passenger {

    //Attributes
    private String companyName;

    /**
     * Get the Company Name
     * @return companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Set the Company Name
     * @param companyNameIn
     */
    public void setCompanyName(String companyNameIn) {
        companyName = companyNameIn;
    }
    
    /**
     * Constructor
     */
    public PassengerBusiness() {
        super();
    }

    /**
     * Constructor
     * @param companyNameIn
     * @param discountAmountIn
     * @param passengerNameIn
     */

    public PassengerBusiness(String companyNameIn, float discountAmountIn, String passengerNameIn) {
        super(discountAmountIn, passengerNameIn);

        companyName = companyNameIn;
        //discountAmountIn = 0.75f;

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
