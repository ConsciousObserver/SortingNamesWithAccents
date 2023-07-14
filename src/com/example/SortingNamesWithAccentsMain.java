package com.example;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SortingNamesWithAccentsMain {
    public static void main(String[] args) {
        System.out.println("Started");

        var names = new ArrayList<String>();
        names.add("axioms");
        names.add("�ber");
        names.add("�pple");
        names.add("under");

        var sortedWithoutNormalization = names.stream().sorted().collect(Collectors.toList());

        //Prints [axioms, under, �pple, �ber]
        System.out.println(sortedWithoutNormalization);

        record Tuple(String normalized, String plain) {}
        
        List<String> sortedWithNormalization = names.stream()
                .map(a -> new Tuple(normalize(a), a))
                .sorted( (a, b) -> a.normalized.compareTo(b.normalized))
                .map(Tuple::plain)
                .collect(Collectors.toList());

        //Prints [�pple, axioms, �ber, under]
        System.out.println(sortedWithNormalization);
    }

    static String normalize(String a) {
        return Normalizer.normalize(a, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
}
