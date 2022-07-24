package com.MikeBlackjack;

import java.util.Scanner;

public class Player extends Person {
    
    Scanner input = new Scanner(System.in);

    //Create a new player
    public Player(){
        super.setName("Player");
    }

    public void makeDecision(Deck deck, Deck discarded) throws InterruptedException {
        int decision = 0;
        boolean getNum = true;

        while (getNum) { 
            // try/catch loop to invalidate non-numeric inputs
            try{
                System.out.println("Would you like to 1) Hit  or  2) Stand?");
                decision = input.nextInt();
                // additional if statement to get either 1 or 2
                if(decision == 1 || decision == 2){
                    getNum = false;
                }
                else { 
                    System.out.println("Invalid numeric input, try again.");
                }
            }
            catch (Exception e) {
                System.out.println("Invalid input, try again.");
                input.next();
            }
        }
        if(decision == 1){
            // Deal a card to hand if decision to hit
            this.hit(deck, discarded);
            // If it's 21 or over, end method
            if(this.getHand().calculateValue() > 20){
                return;
            }
            // otherwise, pose question again
            else{
                this.makeDecision(deck, discarded);
            }
        }
        else {
            System.out.println("You stand.");
        }
    }
}
