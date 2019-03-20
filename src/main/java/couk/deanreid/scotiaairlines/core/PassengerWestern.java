/*
 Scotia Airlines - HND Computer Science
 Program Version: 2.9
 Code Version: 1.3
 @Author: Dean D. Reid
 */
package couk.deanreid.scotiaairlines.core;

public class PassengerWestern
        extends Passenger {
    //Attributes 
    private String islandOfResidence;

    /**
     * Get the Island of Residence
     *
     * @return
     */
    public String getIslandOfResidence () {
        return islandOfResidence;
    }

    /**
     * Set the Island of Residence
     *
     * @param islandOfResidenceIn
     */
    public void setIslandOfResidence (String islandOfResidenceIn) {
        islandOfResidence = islandOfResidenceIn;
    }

    /**
     * Constructor
     */
    public PassengerWestern () {
        super ();
        islandOfResidence = "";
    }

    /**
     * Constructor
     *
     * @param passengerNameIn
     * @param islandOfResidenceIn
     */
    public PassengerWestern (String passengerNameIn, String islandOfResidenceIn) {
        super (0.9f, passengerNameIn);
        islandOfResidence = islandOfResidenceIn;
    }

    /**
     * Set Discount Amount
     *
     * @param islandOfResidenceIn
     * @param discountAmountIn
     * @param passengerNameIn
     */
    public PassengerWestern (String islandOfResidenceIn, float discountAmountIn, String passengerNameIn) {
        super (discountAmountIn, passengerNameIn);
        islandOfResidence = islandOfResidenceIn;
    }

}
