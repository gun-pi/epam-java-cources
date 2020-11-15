package com.epam.university.java.core.task057;

public class WindowImpl implements Window {
    int levelNumber;
    int numberOfWindow;
    SideType side;

    /**
     * Constructor.
     *
     * @param levelNumber level number
     * @param numberOfWindow number of window
     * @param side side
     */
    public WindowImpl(int levelNumber, int numberOfWindow, SideType side) {
        this.levelNumber = levelNumber;
        this.numberOfWindow = numberOfWindow;
        this.side = side;
    }

    @Override
    public int getLevelNumber() {
        return levelNumber;
    }

    @Override
    public int getNumberOfWindow() {
        return numberOfWindow;
    }

    @Override
    public SideType getSide() {
        return side;
    }

    @Override
    public String toString() {
        return "WindowImpl{" + "levelNumber=" + levelNumber
                + ", numberOfWindow=" + numberOfWindow
                + ", side=" + side + '}';
    }
}
