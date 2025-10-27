// File: SpellChecker.java
package src.spellchecker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * SpellChecker application that uses GTUHashSet to check spelling and suggest
 * corrections.
 */
public class SpellChecker {

    public static void main(String[] args) throws IOException {
        GTUHashSet<String> dictionary = new GTUHashSet<>(120000); // Estimate dictionary size for initial capacity

        // Load dictionary from file
        System.out.println("Loading dictionary...");
        long loadStartTime = System.nanoTime();
        BufferedReader reader = new BufferedReader(new FileReader("dictionary.txt"), 65536); // 64KB buffer
        String word;
        int count = 0;
        while ((word = reader.readLine()) != null) {
            dictionary.add(word.trim()); // Store words in lowercase
            count++;
        }
        reader.close();
        long loadEndTime = System.nanoTime();
        System.out.printf("Dictionary loaded with %d words in %.2f ms.\n", count, (loadEndTime - loadStartTime) / 1e6);

        Scanner scanner = new Scanner(System.in); // Scanner is allowed

        // Main loop for user input
        while (true) {
            System.out.print("\nEnter a word (or type 'exit' to quit): ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            if (input.isEmpty()) {
                continue;
            }

            long startTime = System.nanoTime();

            // Check if the word is in the dictionary
            if (dictionary.contains(input)) {
                System.out.println("'" + input + "' is spelled correctly.");
            } else {
                System.out.println("'" + input + "' is misspelled.");
                System.out.print("Suggestions: ");

                GTUArrayList<String> suggestions = new GTUArrayList<>();
                GTUHashSet<String> uniqueSuggestions = new GTUHashSet<>(); // To ensure suggestions are unique

                // Generate and check edit distance 1
                GTUArrayList<String> edits1 = EditDistanceHelper.getEdits1List(input);
                for (int i = 0; i < edits1.size(); ++i) {
                    String variant1 = edits1.get(i);
                    if (dictionary.contains(variant1)) {
                        if (uniqueSuggestions.add(variant1)) { // add returns true if new
                            suggestions.add(variant1);
                        }
                    }
                }

                // Generate and check edit distance 2
                // For each variant from edits1, generate its edits1 list (these are edits2 from
                // original)
                for (int i = 0; i < edits1.size(); ++i) {
                    String variant1 = edits1.get(i);
                    // If variant1 itself is a valid word, we already added it.
                    // Now, generate edits from variant1 to find edit distance 2 words from original
                    // input.
                    GTUArrayList<String> edits2_from_variant1 = EditDistanceHelper.getEdits1List(variant1);
                    for (int j = 0; j < edits2_from_variant1.size(); ++j) {
                        String variant2 = edits2_from_variant1.get(j);
                        if (dictionary.contains(variant2)) {
                            if (uniqueSuggestions.add(variant2)) {
                                suggestions.add(variant2);
                            }
                        }
                    }
                }

                if (suggestions.isEmpty()) {
                    System.out.println("No suggestions found.");
                } else {
                    System.out.println(suggestions.toString());
                }
            }

            long endTime = System.nanoTime();
            System.out.printf("Lookup and suggestion generation took %.2f ms.\n", (endTime - startTime) / 1e6);
        }

        // Close resources
        scanner.close();
        System.out.println("Spell checker terminated.");
    }
}