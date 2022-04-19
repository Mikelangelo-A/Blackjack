package com.MikeBlackjack;

import java.util.ArrayList;

public class Hand {

    private ArrayList<Card> hand;
    
    public Hand(){
        hand = new ArrayList<Card>();
    }

    public void takeCardFromDeck(Deck deck){
        hand.add(deck.takeCard());
    }

    public int cardCount(){
        return hand.size();
    }

    public int calculateValue(){
        int value = 0;
        int aceCount = 0;  

        for(Card card : hand){
            value += card.getValue();
            if (card.getValue() == 11){
                aceCount++;
            }
        }
        if(value > 21 && aceCount > 0){
            while(aceCount > 0 && value > 21){
                value -= 10;
                aceCount--;
            }
        }
        return value;
    }

    public Card getCard(int index){
        return hand.get(index);
    }

    public void discardHandToDeck(Deck discardedDeck){
        // Copy cards from hand to discard deck
        discardedDeck.addCards(hand);
        // Remove cards from hand
        hand.clear();
    }
    public String toString() {
        
        String output = "";
        for(Card card: hand){
            output += card + " - ";
        }
        return output;
    }
}
