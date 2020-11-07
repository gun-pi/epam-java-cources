package com.epam.university.java.core.task056;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Task056Impl implements Task056 {
    static List<String> unsortedIntervals;
    static Comparator<String> comparatorForIntervals = Comparator.comparingInt(
        o -> Integer.parseInt(o.split("-")[0])
    );

    /**
     * Determination of the required packs of pills.
     *
     * @param prescriptionFile prescription from the doctor
     * @return collection with packs numbers. Numbering started from 0
     * @throws IllegalArgumentException if input parameter is null
     */
    @Override
    public Collection<Integer> necessaryMedication(String prescriptionFile) {
        if (prescriptionFile == null) {
            throw new IllegalArgumentException();
        }

        String[] prescriptionArray = readFile(prescriptionFile).split("\\s");
        List<Integer> daysOfUnsortedIntervals = new ArrayList<>();
        unsortedIntervals = new ArrayList<>();
        for (int i = 0; i < prescriptionArray.length; i++) {
            if (i % 2 == 0) {
                daysOfUnsortedIntervals.add(Integer.parseInt(prescriptionArray[i]));
            } else {
                unsortedIntervals.add(prescriptionArray[i]);
            }
        }

        List<String> sortedIntervals = unsortedIntervals.stream()
                .sorted(comparatorForIntervals)
                .collect(Collectors.toList());

        TreeMap<Integer, List<Integer>> sumOfDaysAndIndicesMap = new TreeMap<>();

        for (int i = 0; i < sortedIntervals.size() - 1; i++) {
            int sumOfDaysForSelectedIntervals = daysOfUnsortedIntervals.get(
                    unsortedIntervals.indexOf(sortedIntervals.get(i))
            );

            List<Integer> indicesOfSelectedIntervals = new ArrayList<>();
            indicesOfSelectedIntervals.add(
                    unsortedIntervals.indexOf(sortedIntervals.get(i))
            );

            for (int j = i, k = i; j < sortedIntervals.size() - 1; j++) {
                int endOfFirstPeriod = Integer.parseInt(
                        sortedIntervals.get(k).split("-")[1]
                );
                int startOfSecondPeriod = Integer.parseInt(
                        sortedIntervals.get(j + 1).split("-")[0]
                );

                if (endOfFirstPeriod < startOfSecondPeriod) {
                    int index = j + 1;

                    //Check if there is bigger interval with the same start
                    for (int q = j + 2; q < sortedIntervals.size(); q++) {
                        int startOfBiggerInterval = Integer.parseInt(
                                sortedIntervals.get(q).split("-")[0]
                        );
                        int endOfBiggerInterval = Integer.parseInt(
                                sortedIntervals.get(q).split("-")[1]
                        );
                        int endOfSecondPeriod = Integer.parseInt(
                                sortedIntervals.get(j + 1).split("-")[1]
                        );

                        if (startOfSecondPeriod == startOfBiggerInterval
                                && endOfBiggerInterval > endOfSecondPeriod) {
                            index = q;
                        }
                    }

                    int indexOfIntervalFromUnsortedIntervals
                            = unsortedIntervals.indexOf(sortedIntervals.get(index));
                    sumOfDaysForSelectedIntervals
                            += daysOfUnsortedIntervals.get(indexOfIntervalFromUnsortedIntervals);
                    indicesOfSelectedIntervals.add(
                            indexOfIntervalFromUnsortedIntervals
                    );
                    k = j + 1;
                }
            }
            sumOfDaysAndIndicesMap.put(sumOfDaysForSelectedIntervals, indicesOfSelectedIntervals);
        }

        List<Integer> list = sumOfDaysAndIndicesMap.pollLastEntry().getValue();
        Collections.sort(list);

        return list;
    }

    /**
     * Determination of intervals between medication. Each interval is a string in the following
     * format:
     * "from-to" (both inclusive)
     *
     * @param necessaryMedication treatment course
     * @return collection of intervals or empty collection. Numbering started from 1
     * @throws IllegalArgumentException if input parameter is null
     */
    @Override
    public Collection<String> intervalBetweenMedication(Collection<Integer> necessaryMedication) {
        if (necessaryMedication == null) {
            throw new IllegalArgumentException();
        }

        List<String> necessaryIntervals = unsortedIntervals.stream()
                .filter(x -> necessaryMedication.contains(
                        unsortedIntervals.indexOf(x))
                )
                .sorted(comparatorForIntervals)
                .collect(Collectors.toList());

        List<String> result = new ArrayList<>();
        for (int i = 0; i < necessaryIntervals.size() - 1; i++) {
            int endOfFirstPeriod = Integer.parseInt(
                    necessaryIntervals.get(i).split("-")[1]
            );
            int startOfSecondPeriod = Integer.parseInt(
                    necessaryIntervals.get(i + 1).split("-")[0]
            );
            if (startOfSecondPeriod - endOfFirstPeriod > 1) {
                result.add((endOfFirstPeriod + 1) + "-" + (startOfSecondPeriod - 1));
            }
        }

        return result;
    }

    private String readFile(String input) {
        StringBuilder content = new StringBuilder();
        String readerString = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(input)))  {
            while ((readerString = reader.readLine()) != null) {
                content.append(readerString).append(" ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return content.toString().trim();
    }
}
