package com.epam.university.java.core.task064;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Task064Impl implements Task064 {
    private Player firstPlayer = new PlayerImpl(1);
    private Player secondPlayer = new PlayerImpl(2);
    private Set<Card> pullOfCards = new HashSet<>();

    /**
     * This method should determine the winner after
     * the showdown in Texas Hold'em.
     * Hands of players are represented by a string of two letters.
     * The first letter is the rank of the card, the second is its suit.
     *
     * <p>E.g.
     * "Js,Qh" represents the first player's hand - Jack of spades and Queen of hearts.
     * "2c,7d" represents the second player's hand - two of clubs and seven of diamonds.
     * "2d,2s,7h,Jc,Qs" represents the board with 5 card (showdown time).
     * result of the method should return the second player (with id == 2),
     * because he has the Full house, while the first player has only two pairs.
     *
     * @param firstHand  is the first player's hand (First player is the player with id equals 1)
     * @param secondHand is the second player's hand (Second player is the player with id equals 2)
     * @param board      represents cards on the board (common for both players)
     * @return winner of the game. If the pot was splitted, then null should be returned.
     */
    @Override
    public Player determineWinner(String firstHand, String secondHand, String board) {
        if (firstHand == null || secondHand == null
                || board == null) {
            throw new IllegalArgumentException();
        }

        determineHand(firstHand, firstPlayer);
        determineHand(secondHand, secondPlayer);
        Collection<Card> boardCards = determineBoard(board);

        Combination combinationOfFirstPlayer = determineCombination(boardCards, firstPlayer);
        Combination combinationOfSecondPlayer = determineCombination(boardCards, secondPlayer);

        if (combinationOfFirstPlayer.ordinal() > combinationOfSecondPlayer.ordinal()) {
            return firstPlayer;
        } else if (combinationOfFirstPlayer.ordinal() < combinationOfSecondPlayer.ordinal()) {
            return secondPlayer;
        } else if (combinationOfFirstPlayer.equals(Combination.HIGHCARD)
                && combinationOfSecondPlayer.equals(Combination.HIGHCARD)) {
            int maxCardOfFirstPlayer = firstPlayer.getPlayerHand().stream()
                    .mapToInt(Card::getCardRank)
                    .max().getAsInt();
            int maxCardOfSecondPlayer = secondPlayer.getPlayerHand().stream()
                    .mapToInt(Card::getCardRank)
                    .max().getAsInt();
            int maxCardOfBoard = boardCards.stream()
                    .mapToInt(Card::getCardRank)
                    .max().getAsInt();
            if (maxCardOfBoard > maxCardOfFirstPlayer && maxCardOfBoard > maxCardOfSecondPlayer) {
                return null;
            }
            if (maxCardOfFirstPlayer > maxCardOfSecondPlayer) {
                return firstPlayer;
            } else if (maxCardOfFirstPlayer < maxCardOfSecondPlayer) {
                return secondPlayer;
            } else {
                return null;
            }
        }

        return null;
    }



    private Combination determineCombination(Collection<Card> board, Player player) {
        Collection<Card> cards = new ArrayList<>(board);
        cards.addAll(player.getPlayerHand());

        Collection<Card> clubCards = filterCardsBySuit(cards, Suit.CLUB);
        Collection<Card> diamondCards = filterCardsBySuit(cards, Suit.DIAMOND);
        Collection<Card> spadeCards = filterCardsBySuit(cards, Suit.SPADE);
        Collection<Card> heartCards = filterCardsBySuit(cards, Suit.HEART);

        if (checkIfThereIsRoyalFlush(clubCards)
                || checkIfThereIsRoyalFlush(diamondCards)
                || checkIfThereIsRoyalFlush(spadeCards)
                || checkIfThereIsRoyalFlush(heartCards)) {
            return Combination.ROYAL_FLUSH;
        }

        if ((checkIfThereIsStraightFlush(clubCards)
                || checkIfThereIsStraightFlush(diamondCards)
                || checkIfThereIsStraightFlush(spadeCards)
                || checkIfThereIsStraightFlush(heartCards))
                && (!checkIfThereIsStraightFlush(board)
                || isTheSameSuitOnTheHand(new ArrayList<>(player.getPlayerHand())))) {
            return Combination.STRAIGHT_FLUSH;
        }

        if (checkIfThereIsFourOfKind(cards)) {
            return Combination.FOUR_OF_KIND;
        }

        if (checkIfThereIsFullHouse(cards)) {
            return Combination.FULL_HOUSE;
        }

        if (checkIfThereIsFlush(cards)) {
            return Combination.FLUSH;
        }

        if (checkIfThereIsStraight(cards)) {
            return Combination.STRAIGHT;
        }

        if (checkIfThereIsThreeOfKind(cards)) {
            return Combination.THREE_OF_KIND;
        }

        if (checkIfThereIsTwoPairs(cards)) {
            return Combination.TWO_PAIRS;
        }

        return Combination.HIGHCARD;
    }

    private boolean isTheSameSuitOnTheHand(List<Card> cards) {
        return cards.get(0).getSuit().equals(cards.get(1).getSuit());
    }

    private boolean checkIfThereIsTwoPairs(Collection<Card> cards) {
        Map<Integer, Long> map = cards.stream()
                .map(Card::getCardRank)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .keySet().stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        try {
            return map.get(2) == 2;
        } catch (NullPointerException e) {
            return false;
        }
    }

    private boolean checkIfThereIsThreeOfKind(Collection<Card> cards) {
        Map<Integer, Long> map = cards.stream()
                .map(Card::getCardRank)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return map.containsValue(3);
    }

    private boolean checkIfThereIsStraight(Collection<Card> cards) {
        List<Integer> list = cards.stream()
                .map(Card::getCardRank)
                .sorted()
                .collect(Collectors.toList());
        if (list.size() >= 5) {
            for (int i = 4; i < list.size(); i++) {
                if (list.get(i - 4)  < list.get(i - 3)
                        && list.get(i - 3) < list.get(i - 2)
                        && list.get(i - 2) < list.get(i - 1)
                        && list.get(i - 1) < list.get(i)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean checkIfThereIsFlush(Collection<Card> cards) {
        Map<Suit, Long> map = cards.stream()
                .map(Card::getSuit)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        for (Map.Entry<Suit, Long> each : map.entrySet()) {
            if (each.getValue() == 5) {
                return true;
            }
        }
        return false;
    }

    private boolean checkIfThereIsFullHouse(Collection<Card> cards) {
        Map<Integer, Long> map = cards.stream()
                .map(Card::getCardRank)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return map.containsValue(3) && map.containsValue(2);
    }

    private boolean checkIfThereIsFourOfKind(Collection<Card> cards) {
        Map<Integer, Long> map = cards.stream()
                .map(Card::getCardRank)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return map.containsValue(4);
    }

    private boolean checkIfThereIsStraightFlush(Collection<Card> cards) {
        List<Integer> list = cards.stream()
                .map(Card::getCardRank)
                .sorted()
                .collect(Collectors.toList());
        if (list.size() >= 5) {
            for (int i = 4; i < list.size(); i++) {
                if (list.get(i - 4)  < list.get(i - 3)
                        && list.get(i - 3) < list.get(i - 2)
                        && list.get(i - 2) < list.get(i - 1)
                        && list.get(i - 1) < list.get(i)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean checkIfThereIsRoyalFlush(Collection<Card> cards) {
        List<Integer> list = cards.stream()
                .map(Card::getCardRank)
                .collect(Collectors.toList());
        return list.contains(10) && list.contains(11)
                && list.contains(12) && list.contains(13)
                && list.contains(14);
    }

    private Collection<Card> filterCardsBySuit(Collection<Card> cards, Suit suit) {
        return cards.stream()
                .filter(x -> x.getSuit().equals(suit))
                .collect(Collectors.toList());
    }

    private Collection<Card> determineBoard(String board) {
        if (!board.contains(",")
                || board.split(",").length != 5) {
            throw new IllegalArgumentException();
        }

        Collection<Card> boardCards = new ArrayList<>();
        for (String s : board.split(",")) {
            Card card = new CardImpl(
                    determineRank(s),
                    determineSuit(s)
            );

            if (pullOfCards.contains(card)) {
                throw new IllegalArgumentException();
            } else {
                pullOfCards.add(card);
                boardCards.add(card);
            }
        }
        return boardCards;
    }

    private void determineHand(String hand, Player player) {
        if (!hand.contains(",")
                || hand.split(",").length != 2) {
            throw new IllegalArgumentException();
        }

        Collection<Card> playerHand = new ArrayList<>();
        for (String s : hand.split(",")) {
            CardImpl card = new CardImpl(
                    determineRank(s),
                    determineSuit(s)
            );

            if (pullOfCards.contains(card)) {
                throw new IllegalArgumentException();
            } else {
                pullOfCards.add(card);
                playerHand.add(card);
            }
        }
        player.setPlayerHand(playerHand);
    }

    private Rank determineRank(String card) {
        String rank = card.length() == 2
                ? card.substring(0, 1) : card.substring(0, 2);

        switch (rank) {
            case "2":
                return Rank.TWO;
            case "3":
                return Rank.THREE;
            case "4":
                return Rank.FOUR;
            case "5":
                return Rank.FIVE;
            case "6":
                return Rank.SIX;
            case "7":
                return Rank.SEVEN;
            case "8":
                return Rank.EIGHT;
            case "9":
                return Rank.NINE;
            case "10":
                return Rank.TEN;
            case "J":
                return Rank.JACK;
            case "Q":
                return Rank.QUEEN;
            case "K":
                return Rank.KING;
            case "A":
                return Rank.ACE;
            default:
                throw new IllegalArgumentException();
        }
    }

    private Suit determineSuit(String card) {
        String suit = card.length() == 2
                ? card.substring(1, 2) : card.substring(2, 3);

        switch (suit) {
            case "c":
                return Suit.CLUB;
            case "d":
                return Suit.DIAMOND;
            case "h":
                return Suit.HEART;
            case "s":
                return Suit.SPADE;
            default:
                throw new IllegalArgumentException();
        }
    }
}
