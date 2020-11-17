package com.epam.university.java.core.task065;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task065Impl implements Task065 {
    @Override
    public WayTable getWayTable(String filepath) {
        if (filepath == null || filepath.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Document doc = null;
        try {
            doc = Jsoup.parse(new File(getClass().getResource(filepath).toURI()), "UTF-8");
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        Element table = doc.select("table").get(4);
        Elements rows = table.select("tr");

        Map<String, List<String>> map = new HashMap<>();
        for (int i = 2; i < rows.size() - 1; i++) {
            Element row = rows.get(i);
            Elements cols = row.select("td");

            List<String> list = new ArrayList<>();
            for (Element col : cols) {
                list.add(col.text());
            }
            map.put(list.get(0), list);
        }

        return new WayTableImpl(map);
    }
}
