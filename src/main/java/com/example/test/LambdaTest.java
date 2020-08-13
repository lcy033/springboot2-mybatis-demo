package com.example.test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LambdaTest {

    static String[] atp = {"Rafael Nadal", "Novak Djokovic",
            "Stanislas Wawrinka",
            "David Ferrer","Roger Federer",
            "Andy Murray","Tomas Berdych",
            "Juan Martin Del Potro"};
    static List<String> players =  Arrays.asList(atp);

    public static void main(String[] args) {

        players.forEach(player-> System.out.println(player + ";"));



    }
}
