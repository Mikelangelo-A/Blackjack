package com.MikeBlackjack;

public class Card {
    
    //Provide fields for the cards' status
    private Suit suit;
    private Rank rank;

    //Constructor / Create a card, given the suit and rank of it.
    public Card (Suit suit, Rank rank){
        this.suit = suit;
        this.rank = rank;
    }

    //Another constructor with different input parameters
    public Card(Card card){
        this.suit = card.getSuit();
        this.rank = card.getRank();
    }
    //Create methods to get the cards rank,suit and value
    public int getValue() { 
        return rank.rankValue;
    }

    public Suit getSuit(){
        return suit;
    }

    public Rank getRank(){
        return rank;
    }

    public String toString() {
        return ("["+rank+" of "+ suit+"] ("+this.getValue()+")");
    }
}
