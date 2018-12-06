/*
 Scotia Airlines - HND Computer Science
 Version 1.4
 @Author: Dean D. Reid
 */
package couk.deanreid.scotiaairlines;

public abstract class Passenger {

    //Attributes
    private float discountAmount;
    private String passengerName;

    //Getters
    public float getDiscountAmount() {
        return discountAmount;
    }

    public String getPassengerName() {
        return passengerName;
    }

    //Setters
    public void setDiscountAmount(float discountAmountIn) {
        discountAmount = discountAmountIn;
    }

    public void setPassengerName(String passengerNameIn) {
        passengerName = passengerNameIn;
    }

    //Constructors
    public Passenger() {

        discountAmount = 0;
        passengerName = "";

    }

    public Passenger(String passengerNameIn) {
        discountAmount = 0f;
        passengerName = passengerNameIn;
    }

    public Passenger(float discountAmountIn, String passengerNameIn) {

        discountAmount = discountAmountIn;
        passengerName = passengerNameIn;

    }

}
