package com.MikeBlackjack;

import java.util.ArrayList;

public class Deck {
    
    private ArrayList<Card> deck;

    //Constructor Method to create deck
    public Deck(){
        deck = new ArrayList<Card>();
    }
    
    public void addCard(Card card) { 
        deck.add(card);
    }

    //Method to create a deck of 52 cards. 
    //Loops through each suit and rank, to get 52 unique cards.
    public Deck(boolean makeDeck){
        deck = new ArrayList<Card>();
        for(Suit suit : Suit.values()){
            for(Rank rank : Rank.values()){
                deck.add(new Card(suit,rank));
            }
        }
    }

    public void shuffle(){
        ArrayList<Card> shuffled = new ArrayList<Card>();
        //To iterate through every card in the deck, use a while loop until the original deck size hits 0
        while(deck.size() > 0){
            int cardToPull = (int) (Math.random() * (deck.size() - 1));
            shuffled.add(deck.get(cardToPull));
            deck.remove(cardToPull);
        }
        deck = shuffled;
    }

    public boolean hasCards(){
        if(deck.size() > 0){
            return true;
        }
        else{
            return false;
        }
    }

    public void emptyDeck(){
        deck.clear();
    }

    public void addCards(ArrayList<Card> cards){
        deck.addAll(cards);
    }

    public ArrayList<Card> getCards(){
        return deck;
    }

    public int cardsLeft(){
        return deck.size();
    }
    
    public void reloadCardsFromDiscard(Deck discard){
        this.addCards(discard.getCards());
        this.shuffle();
        discard.emptyDeck();
        System.out.println("Ran out of cards, creating new deck from discard pile & shuffling..");
    }

    public Card takeCard() {
        Card cardToTake = new Card(deck.get(0));
        deck.remove(0);
        return cardToTake;
    }

    public String toString(){

        //Create a string which contains all added cards.
        String output = ""; 

        for (Card card : deck) {
            //add the card and a new-line character
            output += card;
            output += "\n";       
        }
        return output;
    }
}
