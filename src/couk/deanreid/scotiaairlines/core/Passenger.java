/*
 Scotia Airlines - HND Computer Science
 Program Version: 2.9
 Code Version: 1.3
 @Author: Dean D. Reid
 */
package couk.deanreid.scotiaairlines.core;

public abstract class Passenger {

    //Attributes
    private float discountAmount;
    private String passengerName;

    /**
     * Get Discount Amount for Seat
     * @return discountAmount
     */
    public float getDiscountAmount() {
        return discountAmount;
    }

    /**
     * Get Passengers Name
     * @return passengerName
     */
    public String getPassengerName() {
        return passengerName;
    }

    /**
     * Set the Discount Amount for specific passenger types
     * @param discountAmountIn
     */
    public void setDiscountAmount(float discountAmountIn) {
        discountAmount = discountAmountIn;
    }

    /**
     * Set Passengers Name
     * @param passengerNameIn
     */
    public void setPassengerName(String passengerNameIn) {
        passengerName = passengerNameIn;
    }

    /**
     * Passenger Constructor
     */
    public Passenger() {

        discountAmount = 0;
        passengerName = "";

    }

    /**
     * Passenger Constructor w/ Passenger Name
     * @param passengerNameIn
     */
    public Passenger(String passengerNameIn) {
        discountAmount = 0f;
        passengerName = passengerNameIn;
    }

    /**
      Passenger Constructor for Discount andPassenger Name
     * @param discountAmountIn
     * @param passengerNameIn
     */
    public Passenger(float discountAmountIn, String passengerNameIn) {

        discountAmount = discountAmountIn;
        passengerName = passengerNameIn;

    }
}
