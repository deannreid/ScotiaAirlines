/*
 Scotia Airlines - HND Computer Science
 Version 1.4
 @Author: Dean D. Reid
 */
package couk.deanreid.scotiaairlines;

public class PassengerWestern extends Passenger {

    //Attribute 
    private String islandOfResidence;

    //Getter
    public String getIslandOfResidence() {
        return islandOfResidence;
    }

    //Setter
    public void setIslandOfResidence(String islandOfResidenceIn) {
        islandOfResidence = islandOfResidenceIn;
    }

    //Constructors
    public PassengerWestern() {
        super();
        islandOfResidence = "";
    }

    public PassengerWestern(String passengerNameIn, String islandOfResidenceIn) {
        super(0.9f, passengerNameIn);
        islandOfResidence = islandOfResidenceIn;
    }

    /**
     * Set Discount Amount
     * @param islandOfResidenceIn
     * @param discountAmountIn
     * @param passengerNameIn
     */
    public PassengerWestern(String islandOfResidenceIn, float discountAmountIn, String passengerNameIn) {
        super(discountAmountIn, passengerNameIn);

        islandOfResidence = islandOfResidenceIn;

        discountAmountIn = 0.9f;

    }

}
