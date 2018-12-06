/*
 Scotia Airlines - HND Computer Science
 Version 1.4
 @Author: Dean D. Reid
 */
package couk.deanreid.scotiaairlines;

public class PassengerOrdinary extends Passenger {

    //Attributes
    private char currentPromotion;

    //Getter
    public char getCurrentPromotion() {
        return currentPromotion;
    }

    //Setter
    public void setCurrentPromotion(char currentPromotionIn) {
        currentPromotion = currentPromotionIn;
    }

    //Constructors
    public PassengerOrdinary() {
        super();
        currentPromotion = ' ';
    }

    /**
     * Set Discount Amount
     * @param passengerNameIn
     * @param currentPromotionIn
     */
    public PassengerOrdinary(String passengerNameIn, char currentPromotionIn) {
        super(passengerNameIn);

        currentPromotion = currentPromotionIn;

        //takes in whether a promotion or not and sets discount accordingly
        if (currentPromotion == 'y') {
            this.setDiscountAmount(0.95f);
        } else {
            this.setDiscountAmount(1.0f);
        }
    }
}
