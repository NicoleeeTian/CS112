/*
 * TerrierShipping.java
 * CS 112, Boston University
 *
 * Completed by: your name and email
 * 
 * Calculates the total shipping charge for a collection of items.
 */

import java.util.*;

public class TerrierShipping {     
    /*
     * getShippingType - gets the type of shipping as an integer
     */
    public static int getShippingType(Scanner console){
        System.out.println("Available shipping types:");
        System.out.println("  1) one-day");
        System.out.println("  2) two-day");
        System.out.println("  3) standard");
        System.out.println();
        
        System.out.print("What type of shipping? (enter the number) ");
        int type = console.nextInt();
        return type;
    }

    /*
     * getItemType - gets the type of item as a single-character string
     */
    public static String getItemType(Scanner console){
        System.out.println();
        System.out.println("Item type:");
        System.out.println("  B) book");
        System.out.println("  C) clothing");
        System.out.println("  E) electronics");
        System.out.println("  T) toy");
        System.out.println();
        
        System.out.print("What type of item? (enter the letter or Q to quit) ");
        String itemType = console.next();
        return itemType;
    }
    
    /* 
     * PUT YOUR ADDITIONAL HELPER METHODS HERE.
     * Remember that you must have at least three additional methods that:
     *   - take one or more parameters
     *   - return a value 
     */
    public static int getOnedayCharge(int weight, String itemType) {
        int price;
        if(itemType.equals("B")||itemType.equals("C")){
            if (weight<2){
                price = 499;
            }else{
                price = 399 + weight * 60;
            }
        }else if (itemType.equals("T")){
            price = 499 + 199 * weight;
        }else{
            price = 599 + 199 * weight;
        }
        return price;
    }

    public static int getTwodayCharge(int weight, String itemType) {
        int price;
        if(itemType.equals("B")||itemType.equals("C")){
            if (weight<2){
                price = 299;
            }else{
                price = 199 + weight*75;
            }
        }else if (itemType.equals("T")){
            price = 299 + 99 * weight;
        }else{
            price = 399 + 89 * weight;
        }
        return price;
    }

    public static int getStandardCharge(int weight, String itemType) {
        int price;
        if(itemType.equals("B")||itemType.equals("C")){
            if (weight<2){
                price = 199;
            }else{
                price = 99 + weight*70;
            }
        }else{
            price = 199 + 80 * weight;
        }
        return price;
    }
    
    public static void main(String[] args){
        Scanner console = new Scanner(System.in);    // for user input
        
        System.out.println("Welcome to Terrier Shipping!");
        System.out.println();
        int shipType = getShippingType(console);
        
        int totalCents = 0;
        boolean hasMoreItems = true;
        
        /*
         * Process one item at a time until the user enters Q. 
         * We use a do-while loop, because we always need 
         * at least one repetition of the loop.
         */
        
        do {
            String itemType = getItemType(console);       
            if (itemType.equals("Q")) {
                hasMoreItems = false;
            } else {            
                /*
                 * TO DO: update the right-hand side of the assignment 
                 * statement below to get an integer from the user. 
                 * You MUST use the Scanner object created above 
                 * at the start of main. You may NOT construct an 
                 * additional Scanner object.
                 */
                System.out.print("Weight of item (rounded to nearest pound)? ");
                int weight = console.nextInt();
        
                int itemCharge = 0;
            
                /*
                 * TO DO: Add code here that uses conditional execution to 
                 * call one of your static methods to determine the charge
                 * for the current item and assign it to itemCharge.
                 */
                if (shipType == 1){
                    itemCharge += getOnedayCharge(weight, itemType);
                }else if (shipType == 2){
                    itemCharge += getTwodayCharge(weight, itemType);
                }else{
                    itemCharge += getStandardCharge(weight, itemType);
                }
           
                totalCents += itemCharge;
            }
        } while (hasMoreItems == true);
            
        System.out.println();
        
        /*
         * TO DO: add the appropriate expression to the right-hand side 
         * of this assignment statement to convert totalCents to dollars.
         */
        double totalDollars = 0.0;      
        totalDollars = totalCents/100.0;

        // We use printf to ensure that the final result always has
        // two digits after the decimal. 
        System.out.printf("The total charge is: $%.2f\n", totalDollars);   
    }
}