// File: EditDistanceHelper.java
package src.spellchecker;

/**
 * Helper class to generate word variations based on edit distance.
 */
public class EditDistanceHelper {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz"; // Assuming lowercase English alphabet

    /**
     * Generates all unique string variations that are an edit distance of 1 from
     * the input word.
     * Edits include deletions, substitutions, and insertions.
     * 
     * @param word          The original word.
     * @param variationsSet The GTUHashSet to store the generated unique variations.
     */
    public static void generateEdits1(String word, GTUHashSet<String> variationsSet) {
        // Deletions
        for (int i = 0; i < word.length(); i++) {
            variationsSet.add(word.substring(0, i) + word.substring(i + 1));
        }

        // Substitutions
        for (int i = 0; i < word.length(); i++) {
            char[] chars = word.toCharArray();
            for (char c : ALPHABET.toCharArray()) {
                if (chars[i] != c) { // Avoid substituting a char with itself
                    char originalChar = chars[i];
                    chars[i] = c;
                    variationsSet.add(new String(chars));
                    chars[i] = originalChar; // backtrack
                }
            }
        }

        // Insertions
        for (int i = 0; i <= word.length(); i++) {
            for (char c : ALPHABET.toCharArray()) {
                variationsSet.add(word.substring(0, i) + c + word.substring(i));
            }
        }
    }

    /**
     * Generates suggestions for a misspelled word from a dictionary.
     * Suggestions are words from the dictionary with an edit distance of 1 or 2.
     * 
     * @param word       The misspelled word.
     * @param dictionary The dictionary of correct words.
     * @return A GTUArrayList of suggested words.
     */
    public static GTUArrayList<String> generateSuggestions(String word, GTUHashSet<String> dictionary) {
        GTUArrayList<String> suggestions = new GTUArrayList<>();
        GTUHashSet<String> candidates = new GTUHashSet<>(); // To store all potential candidates (edit distance 1 & 2)

        // Generate edit distance 1 candidates
        GTUHashSet<String> edits1 = new GTUHashSet<>();
        generateEdits1(word, edits1);

        // Check edit distance 1 candidates against dictionary
        for (String edit1Word : edits1) { // GTUHashSet now implements Iterable
            if (dictionary.contains(edit1Word) && !suggestions.contains(edit1Word)) {
                suggestions.add(edit1Word);
            }
            candidates.add(edit1Word); // Add to candidates for generating edits2
        }

        // Generate edit distance 2 candidates
        GTUHashSet<String> edits2 = new GTUHashSet<>();
        for (String s1 : candidates) { // GTUHashSet now implements Iterable
            generateEdits1(s1, edits2);
        }

        // Check edit distance 2 candidates against dictionary
        for (String edit2Word : edits2) { // GTUHashSet now implements Iterable
            if (dictionary.contains(edit2Word) && !suggestions.contains(edit2Word)) {
                suggestions.add(edit2Word);
            }
        }
        return suggestions;
    }

    /**
     * Generates all unique string variations that are an edit distance of 1 from
     * the input word.
     * Returns them as a GTUArrayList for easier iteration.
     * 
     * @param word The original word.
     * @return GTUArrayList of unique variations.
     */
    public static GTUArrayList<String> getEdits1List(String word) {
        GTUHashSet<String> variationsSet = new GTUHashSet<>();
        // Deletions
        for (int i = 0; i < word.length(); i++) {
            variationsSet.add(word.substring(0, i) + word.substring(i + 1));
        }
        // Substitutions
        for (int i = 0; i < word.length(); i++) {
            char[] chars = word.toCharArray();
            for (char c : ALPHABET.toCharArray()) {
                char originalChar = chars[i];
                chars[i] = c;
                variationsSet.add(new String(chars));
                chars[i] = originalChar; // backtrack
            }
        }
        // Insertions
        for (int i = 0; i <= word.length(); i++) {
            for (char c : ALPHABET.toCharArray()) {
                variationsSet.add(word.substring(0, i) + c + word.substring(i));
            }
        }
        GTUArrayList<String> variationsList = new GTUArrayList<>();
        for (String variation : variationsSet) {
            variationsList.add(variation);
        }
        return variationsList; // Convert to GTUArrayList
    }
}