package com.MikeBlackjack;

public class Dealer extends Person {
    
    public Dealer(){
        super.setName("Dealer");
    }

    public void printFirstHand(){
        System.out.println("Dealer's first card looks like this: ");
        System.out.println(super.getHand().getCard(1));
        System.out.println("The second card is hidden.");
    }
}
