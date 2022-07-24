package com.MikeBlackjack;

import java.util.Scanner;

public class Game {
    
    
    //Create variables used by the Game class
    private Deck deck, discarded;

    private Dealer dealer;
    private Player player;
    private int wins, losses, pushes;

    public Game() throws InterruptedException {
        // Create deck with full 52 cards
        deck = new Deck(true);
        // Create another empty deck, in which the played cards will go.
        discarded = new Deck();

        dealer = new Dealer();
        player = new Player(); 
        
        deck.shuffle();
        startRound();

        wins = 0; 
        losses = 0; 
        pushes = 0;


    }

    private void startRound() throws InterruptedException {
        // Player draws a card, then the dealer, then player, then the dealer draws the second card.
        // Check, whether the player or dealer has a blackjack.
        // If yes, round is over, if no, continue
        // Announce the score of the hand of the player, ask for decision
        // If player hits, give a card to player hand, evaluate if equal to or over 21
        // Repeat until player stands
        // Dealer reveals second card. If dealer shows over 17, dealer stands, else dealer takes card.
        // After Dealer stands, compare the hand of the dealer to the hand of player.
        // Evaluate winner, and announce the winner.
        // Round over.

        if(wins > 0 || losses > 0 || pushes > 0){
            System.out.println();
            System.out.println("Starting next round...");
            Thread.sleep(1000);
            System.out.println("Current statistics : ");
            System.out.println("Wins : " + wins);
            System.out.println("Losses : " + losses);
            System.out.println("Pushes : " + pushes);
            Thread.sleep(1000);
            player.getHand().discardHandToDeck(discarded);
            dealer.getHand().discardHandToDeck(discarded);
        }
        
        if(deck.cardsLeft() < 4){
            deck.reloadCardsFromDiscard(discarded);
        }
        player.getHand().takeCardFromDeck(deck);
        dealer.getHand().takeCardFromDeck(deck);
        player.getHand().takeCardFromDeck(deck);
        dealer.getHand().takeCardFromDeck(deck);

        player.printHand();
        dealer.printFirstHand();
        
        if(dealer.hasBlackjack()){
            dealer.printHand();
            if(player.hasBlackjack()){
                System.out.println("Push! Both parties have a Blackjack!");
                pushes++;
                promptNewRound();
            }
            else{
                System.out.println("Dealer has Blackjack. Player loses.");
                losses++;
                promptNewRound();
            }
        
        }
        if(player.hasBlackjack()){
            System.out.println("Player has a Blackjack!");
            wins++;
            promptNewRound();
        }
        player.makeDecision(deck, discarded);
        // Check if player doesn't have too many.
        if(player.getHand().calculateValue() > 21){
            System.out.println("Too many, you have gone over 21...");
            losses++;
            promptNewRound();
        }

        dealer.printHand();
        while(dealer.getHand().calculateValue() < 17){
            dealer.hit(deck,discarded); 
        }

        if(dealer.getHand().calculateValue() > 21 ){
            System.out.println("Dealer busts. Congratulations!");
            Thread.sleep(1000);
            wins++;
        }
        else if(dealer.getHand().calculateValue() > player.getHand().calculateValue()){
            System.out.println("Dealer wins. You lose.");
            Thread.sleep(1000);
            losses++;
        }
        else if(dealer.getHand().calculateValue() < player.getHand().calculateValue()){
            System.out.println("You win! Congratulations!");
            Thread.sleep(1000);
            wins++;
        }
        else{
            System.out.println("Push. Thanks for playing.");
            Thread.sleep(1000);
            pushes++;
        }
       promptNewRound();
    }

    private void promptNewRound(){
        Scanner scanner = new Scanner(System.in);
        boolean getNum = true;
        while(getNum) {
            try{
                System.out.println();
                System.out.println("Would you like to 1) play another round  or  2) quit?");
                int decision = scanner.nextInt();
                // additional if statement to get either 1 or 2
                if(decision == 1 || decision == 2){
                    getNum = false;
                    if(decision == 1){
                        startRound();
                    } else {
                        System.out.println("Thank you for playing Blackjack!");
                        break;
                    }
                }
                else {
                    System.out.println("Invalid numeric input, try again.");
                }
            }
            catch (Exception e) {
                System.out.println("Invalid input, try again.");
                scanner.next();
            }
        }
    }
}

