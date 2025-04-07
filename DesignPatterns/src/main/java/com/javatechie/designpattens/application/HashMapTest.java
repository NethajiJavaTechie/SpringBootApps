package com.javatechie.designpattens.application;

import java.util.HashMap;
import java.util.Hashtable;

public class HashMapTest {

    public static void main(String[] args) {
        HashMap<Integer, String> testMap = new HashMap<>();
        testMap.put(1, "Nethaji");
        System.out.println("Map Entry "+testMap.get(1));
        testMap.put(null, "Manikandan");
        System.out.println("Map Entry "+testMap.get(null));

        Hashtable<Integer, String> testTable = new Hashtable<>();
        testTable.put(1, "Nethaji");
        System.out.println("Hashtable Entry "+testTable.get(1));
        testTable.put(null, "Manikandan");
        System.out.println("Hashtable Entry "+testTable.get(null));
    }
}
