/*
 Scotia Airlines - HND Computer Science
 Program Version: 2.9
 Code Version: 1.3
 @Author: Dean D. Reid
 */
package couk.deanreid.scotiaairlines.core;

public class PassengerOrdinary
        extends Passenger {
    //Attributes
    private char currentPromotion;

    /**
     * Get the Current Promotion
     *
     * @return currentPromotion
     */
    public char getCurrentPromotion () {
        return currentPromotion;
    }

    /**
     * Set the Current Promotion
     *
     * @param currentPromotionIn
     */
    public void setCurrentPromotion (char currentPromotionIn) {
        currentPromotion = currentPromotionIn;
    }

    /**
     * Constructor
     */
    public PassengerOrdinary () {
        super ();
        currentPromotion = ' ';
    }

    /**
     * Set Discount Amount
     *
     * @param passengerNameIn
     * @param currentPromotionIn
     */
    public PassengerOrdinary (String passengerNameIn, char currentPromotionIn) {
        super (passengerNameIn);
        currentPromotion = currentPromotionIn;
        //takes in whether a promotion or not and sets discount accordingly
        if (currentPromotion
                == 'y') {
            this.setDiscountAmount (0.95f);
        } else {
            this.setDiscountAmount (1.0f);
        }
    }

}
