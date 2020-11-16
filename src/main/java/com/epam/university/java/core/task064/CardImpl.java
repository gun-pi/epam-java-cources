package com.epam.university.java.core.task064;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CardImpl implements Card {
    static private Map<Integer, Rank> rankIntMap = new HashMap<>();

    static {
        rankIntMap.put(2, Rank.TWO);
        rankIntMap.put(3, Rank.THREE);
        rankIntMap.put(4, Rank.FOUR);
        rankIntMap.put(5, Rank.FIVE);
        rankIntMap.put(6, Rank.SIX);
        rankIntMap.put(7, Rank.SEVEN);
        rankIntMap.put(8, Rank.EIGHT);
        rankIntMap.put(9, Rank.NINE);
        rankIntMap.put(10, Rank.TEN);
        rankIntMap.put(11, Rank.JACK);
        rankIntMap.put(12, Rank.QUEEN);
        rankIntMap.put(13, Rank.KING);
        rankIntMap.put(14, Rank.ACE);
    }

    private Rank rank;
    private Suit suit;

    public CardImpl(Rank rank, Suit suit){
        this.rank = rank;
        this.suit = suit;
    }

    /**
     * Get the rank of the card.
     *
     * @return card rank.
     */
    @Override
    public int getCardRank() {
        return rankIntMap.entrySet().stream()
                .filter(x -> x.getValue().equals(rank))
                .map(Map.Entry::getKey)
                .findAny()
                .get();
    }

    /**
     * Set the rank of the card.
     *
     * @param rank is the rank of the card.
     */
    @Override
    public void setCardRank(int rank) {
        this.rank = rankIntMap.get(rank);
    }

    /**
     * Get suit of the card.
     *
     * @return card suit.
     */
    @Override
    public Suit getSuit() {
        return suit;
    }

    /**
     * Set the rank of the card.
     *
     * @param suit is the suit of the card.
     */
    @Override
    public void setCardSuit(Suit suit) {
        this.suit = suit;
    }

    public Rank getCardRankInRank() {
        return rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CardImpl)) return false;
        CardImpl card = (CardImpl) o;
        return rank == card.rank &&
                getSuit() == card.getSuit();
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, getSuit());
    }
}
