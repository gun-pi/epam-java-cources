package com.epam.university.java.core.task064;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CardImpl implements Card {
    private static Map<Integer, Rank> rankMap = initializeRankMap();

    private Rank rank;
    private Suit suit;

    public CardImpl(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    private static Map<Integer, Rank> initializeRankMap() {
        Map<Integer, Rank> rankMap = new HashMap<>();

        rankMap.put(2, Rank.TWO);
        rankMap.put(3, Rank.THREE);
        rankMap.put(4, Rank.FOUR);
        rankMap.put(5, Rank.FIVE);
        rankMap.put(6, Rank.SIX);
        rankMap.put(7, Rank.SEVEN);
        rankMap.put(8, Rank.EIGHT);
        rankMap.put(9, Rank.NINE);
        rankMap.put(10, Rank.TEN);
        rankMap.put(11, Rank.JACK);
        rankMap.put(12, Rank.QUEEN);
        rankMap.put(13, Rank.KING);
        rankMap.put(14, Rank.ACE);

        return rankMap;
    }

    /**
     * Get the rank of the card.
     *
     * @return card rank.
     */
    @Override
    public int getCardRank() {
        return rankMap.entrySet().stream()
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
        this.rank = rankMap.get(rank);
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
        if (this == o) {
            return true;
        }
        if (!(o instanceof CardImpl)) {
            return false;
        }
        CardImpl card = (CardImpl) o;
        return rank == card.rank && getSuit() == card.getSuit();
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, getSuit());
    }
}
