import acm.graphics.GLabel;
import acm.program.GraphicsProgram;
import com.prog2.blackjack.Card;
import svu.csc213.Dialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Blackjack extends GraphicsProgram {

    // data about the game
    private int wager = 0;
    private int balance = 10000;
    private int bank = 20000;

    //labels to display info to player
    private GLabel bankLabel;
    private GLabel wagerLabel;
    private GLabel balanceLabel;
    private GLabel blackJack;

    //buttons for controls
    private JButton wagerButton;
    private JButton playButton;
    private JButton hitButton;
    private JButton stayButton;
    private JButton quitButton;

    // objects we are playing with
    private Deck deck;
    private GHand player;
    private GHand dealer;

    @Override
    public void init() {

        this.setBackground(Color.DARK_GRAY);

        deck = new Deck();

        //set up all buttons
        wagerButton = new JButton("Wager");
        playButton = new JButton("Play");
        hitButton = new JButton("Hit");
        stayButton = new JButton("Stay");
        quitButton = new JButton("Quit");

        // add buttons to the screen
        add(wagerButton, SOUTH);
        add(playButton, SOUTH);
        add(hitButton, SOUTH);
        add(stayButton, SOUTH);
        add(quitButton, SOUTH);

        wagerButton.setVisible(true);
        quitButton.setVisible(true);
        playButton.setVisible(true);
        hitButton.setVisible(false);
        stayButton.setVisible(false);

        addActionListeners();

        //initialize GLabels
        bankLabel = new GLabel("Bank: " + bank);
        wagerLabel = new GLabel("Wager: " + wager);
        balanceLabel = new GLabel("Balance: " +balance);
        bankLabel.setColor(Color.lightGray);
        wagerLabel.setColor(Color.lightGray);
        balanceLabel.setColor(Color.lightGray);

        //add GLabels
        add(bankLabel, 10, bankLabel.getHeight() + 10);
        add(wagerLabel, 10, wagerLabel.getHeight()*2 + 10*2);
        add(balanceLabel, 10, balanceLabel.getHeight()*3 + 10*3);

        //initialze hands
        player = new GHand(new Hand(deck, false));
        dealer = new GHand(new Hand(deck, true));

        //
        add(dealer, 100, 100);
        add(player, 100, 250);
    }

    @Override
    public void actionPerformed(ActionEvent aE) {
        switch (aE.getActionCommand()){
            case "Wager":
                wager();
                break;

            case "Play":
                play();
                break;

            case "Hit":
                hit();
                break;

            case "Stay":
                stay();
                break;

            case "Quit":
                System.exit(0);
                break;
        }
    }

    private void reset (){
        wager = 0;
        wagerButton.setVisible(true);
        quitButton.setVisible(true);
        playButton.setVisible(true);
        hitButton.setVisible(false);
        stayButton.setVisible(false);

        if (bank <= 0){
            Dialog.showMessage("You beat the game");
            System.exit(0);
        } else if (balance <= 0){
            Dialog.showMessage("You lost the game");
            System.exit(0);
        }
    }

    private void wager (){
        wager = (Dialog.getInteger("How much would you like to wager?"));
        if (wager > balance){
            Dialog.showMessage("That is too big of a wager");
            wager = (Dialog.getInteger("How much would you like to wager?"));
        } else if(wager <= 0){
            Dialog.showMessage("That is too small of a wager");
            wager = (Dialog.getInteger("How much would you like to wager?"));
        }

        wagerLabel.setLabel("Wager: " +wager);
        balance -= wager;
        balanceLabel.setLabel("Balance:" + balance);
    }

    private void play(){
        if (wager == 0){
            Dialog.showMessage("You must make a wager first");
        }
        //set button visibilities
        wagerButton.setVisible(false);
        playButton.setVisible(false);
        hitButton.setVisible(true);
        stayButton.setVisible(true);
    }

    private void hit(){
        player.hit();
        //check to see if they break
        if (player.getTotal() > 21){
            Dialog.showMessage("You broke");
            bank += wager;
            bankLabel.setLabel("Bank: " + bank);
            reset();
        }
    }

    private void stay(){
        //dealer flips and hits
        dealer.flipCard(0);
        while (dealer.getTotal() <17){
            dealer.hit();
            if (dealer.getTotal()> 21){
                Dialog.showMessage("You won");
                bank -= wager;
                bankLabel.setLabel("Bank: " +bank);
                balance += wager*2;
                balanceLabel.setLabel("Balance: "+ balance);
                reset();
            }
        }
        //evaluate hands
        if(player.getTotal() > dealer.getTotal()){
            Dialog.showMessage("You won");
            bank -= wager;
            bankLabel.setLabel("Bank: " +bank);
            balance += wager*2;
            balanceLabel.setLabel("Balance: "+ balance);
            reset();
        }else if (dealer.getTotal() > player.getTotal()){
            Dialog.showMessage("You lost");
            bank += wager;
            bankLabel.setLabel("Bank: " + bank);
            reset();
        }else {
            Dialog.showMessage("You tied");
            balance += wager;
            balanceLabel.setLabel("Balance" + balance);
            reset();
        }

    }

    public static void main(String[] args) {
        new Blackjack().start();
    }



    }
