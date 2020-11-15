package com.epam.university.java.core.task057;

import java.util.ArrayList;
import java.util.List;

public class Task057Impl implements Task057 {
    static int NUMBEROFFLATS;
    static int NUMBEROFWINDOWS;

    /**
     * Every house has levels, and entrances.
     * This method returns correct window for delivery.
     *
     * @param level        - How many levels are there in the house?
     * @param entrances    - How many entrances are there in the house?
     * @param numberOfFlat - number of flat from task of delivery.
     */
    @Override
    public Window getWindowForDelivery(int level, int entrances, int numberOfFlat) {
        NUMBEROFFLATS = entrances * level * 8;
        NUMBEROFWINDOWS = 4 * entrances;
        List<Integer> numberOfWindow = new ArrayList<>();
        List<SideType> side = new ArrayList<>();
        List<Integer> levelNumber = new ArrayList<>();

        int k = 0;
        int q = NUMBEROFWINDOWS + 1;
        for (int i = 0; i < entrances; i++) {
            for (int j = 0; j < level; j++) {
                numberOfWindow.add(k + 3);
                numberOfWindow.add(k + 4);
                numberOfWindow.add(q - 4);
                numberOfWindow.add(q - 3);
                numberOfWindow.add(q - 2);
                numberOfWindow.add(q - 1);
                numberOfWindow.add(k + 1);
                numberOfWindow.add(k + 2);
            }
            k += 4;
            q -= 4;
        }

        for (int i = 0; i < NUMBEROFFLATS; i += 8) {
            side.add(SideType.FRONT_SIDE);
            side.add(SideType.FRONT_SIDE);
            side.add(SideType.BACK_SIDE);
            side.add(SideType.BACK_SIDE);
            side.add(SideType.BACK_SIDE);
            side.add(SideType.BACK_SIDE);
            side.add(SideType.FRONT_SIDE);
            side.add(SideType.FRONT_SIDE);
        }


        for (int i = 0; i < entrances; i++) {
            for (int j = 1; j <= level; j++) {
                levelNumber.add(j);
                levelNumber.add(j);
                levelNumber.add(j);
                levelNumber.add(j);
                levelNumber.add(j);
                levelNumber.add(j);
                levelNumber.add(j);
                levelNumber.add(j);
            }
        }

        try {
            return new WindowImpl(
                    levelNumber.get(numberOfFlat - 1),
                    numberOfWindow.get(numberOfFlat - 1) * 2,
                    side.get(numberOfFlat - 1)
            );
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException();
        }
    }
}
