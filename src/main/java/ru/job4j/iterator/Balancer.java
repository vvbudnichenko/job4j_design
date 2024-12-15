package ru.job4j.iterator;

import java.util.*;

public class Balancer {

    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        while (source.hasNext()) {
            for (ArrayList<Integer> eachInnerArray : nodes) {
                if (source.hasNext()) {
                    eachInnerArray.add(source.next());
                }
            }
        }
    }
}

