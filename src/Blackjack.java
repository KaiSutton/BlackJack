import acm.graphics.GLabel;
import acm.program.GraphicsProgram;
import com.prog2.blackjack.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Blackjack extends GraphicsProgram {

    // data about the game
    private int wager = 0;
    private int balance = 10000;
    private int bank = 10000;

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
        blackJack = new GLabel("Blackjack");

        //add GLabels
        add(bankLabel, bankLabel.getWidth() + 10, bankLabel.getHeight() + 10);

        //initialze hands
        GHand player = new GHand(new Hand(deck, false));
        GHand dealer = new GHand(new Hand(deck, true));

        //
        add(dealer, 100, 100);
        add(player, 100, 250);
    }

    @Override
    public void actionPerformed(ActionEvent aE) {
        switch (aE.getActionCommand()){
            case "Wager":
                // TODO
                
                break;
            case "Play":
                // TODO
                break;
            case "Hit":
                // TODO
                break;
            case "Stay":
                // TODO
                break;
            case "Quit":
                System.exit(0);
                break;
        }
    }


    public static void main(String[] args) {
        new Blackjack().start();
    }



    }
