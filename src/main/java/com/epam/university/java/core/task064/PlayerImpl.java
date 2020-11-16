package com.epam.university.java.core.task064;

import java.util.Collection;

public class PlayerImpl implements Player {
    private int id;
    private Collection<Card> hand;

    public PlayerImpl(){}

    public PlayerImpl(int id) {
        this.id = id;
    }

    /**
     * Get player's id.
     *
     * @return player's id
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * Set player's id.
     *
     * @param id is the player's id.
     */
    @Override
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get player's hand.
     *
     * @return player's hand
     */
    @Override
    public Collection<Card> getPlayerHand() {
        return hand;
    }

    /**
     * Set player's hand.
     *
     * @param hand is the player's hand.
     */
    @Override
    public void setPlayerHand(Collection<Card> hand) {
        this.hand = hand;
    }
}
