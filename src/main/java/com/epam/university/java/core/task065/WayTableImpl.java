package com.epam.university.java.core.task065;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class WayTableImpl implements WayTable {
    private Map<String, List<String>> table;

    public WayTableImpl(Map<String, List<String>> table) {
        this.table = table;
    }


    @Override
    public int getDistOfDate(LocalDate localDate) {
        double result = 0;
        for (Map.Entry<String, List<String>> each : table.entrySet()) {
            if (each.getValue().get(2).equals(
                    localDate.format(DateTimeFormatter.ofPattern("dd.MM.yy"))
            )) {
                if (each.getValue().get(7).isEmpty()) {
                    result += Double.parseDouble(each.getValue().get(5));
                } else if (each.getValue().get(7).split(" ").length == 4) {
                    result += Double.parseDouble(each.getValue().get(7).split(" ")[2]);
                } else {
                    result += Double.parseDouble(each.getValue().get(7).split(" ")[0]);
                }
            }
        }

        return (int)Math.round(result);
    }

    @Override
    public int getAllDistance() {
        double result = 0;
        for (Map.Entry<String, List<String>> each : table.entrySet()) {
            if (each.getValue().get(7).isEmpty()) {
                result += Double.parseDouble(each.getValue().get(5));
            } else if (each.getValue().get(7).split(" ").length == 4) {
                result += Double.parseDouble(each.getValue().get(7).split(" ")[2]);
            } else {
                result += Double.parseDouble(each.getValue().get(7).split(" ")[0]);
            }
        }

        return (int)Math.round(result);
    }

    @Override
    public int getCountWays() {
        return table.entrySet().size();
    }

    public Map<String, List<String>> getTable() {
        return table;
    }

    public void setTable(Map<String, List<String>> table) {
        this.table = table;
    }
}
