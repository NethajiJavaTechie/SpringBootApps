package com.javatechie.designpattens.application;

import java.util.HashMap;
import java.util.Objects;

public class HashCollisionExample {

    static class CustomKey {
        private String value;

        public CustomKey(String value) {
            this.value = value;
        }

        // Force same hash code for all keys
        @Override
        public int hashCode() {
            return 42; // All keys will go into the same bucket
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CustomKey customKey = (CustomKey) o;
            return Objects.equals(value, customKey.value);
        }

        @Override
        public String toString() {
            return "Key[" + value + "]";
        }
    }

    public static void main(String[] args) {
        HashMap<CustomKey, String> map = new HashMap<>();

        CustomKey key1 = new CustomKey("apple");
        CustomKey key2 = new CustomKey("banana"); // Different object, same hash code

        map.put(key1, "Red");
        map.put(key2, "Yellow");

        System.out.println("Key1: " + map.get(key1)); // Output: Red
        System.out.println("Key2: " + map.get(key2)); // Output: Yellow

        System.out.println("Map size: " + map.size()); // 2

        System.out.println("Internal map entries:");
        map.forEach((k, v) -> System.out.println(k + " -> " + v));
    }
}
