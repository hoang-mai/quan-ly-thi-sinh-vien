package com.example.project.gui51;

import java.util.ArrayList;
import java.util.List;

public class savedata {
    private static List<String> labelValues = new ArrayList<>();

    public static List<String> getLabelValues() {
        return labelValues;
    }

    public static void addLabelValue(String value) {
        labelValues.add(value);
    }

}
