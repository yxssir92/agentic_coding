import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

// Autor: Yassir (GitHub: yxssir92)
public class Wordle {

    public static void main(String[] args) {
        // Ein Array speichert mehrere Wörter. Alle Wörter haben fünf Buchstaben.
        String[] words = {"APFEL", "BLUME", "LAMPE", "TISCH", "WOLKE",
                "KATZE", "HUNDE", "SONNE", "REGEN", "RADIO"};

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String playAgain = "j";

        System.out.println("Willkommen bei Wordle!");
        System.out.println("Errate ein Wort mit 5 Buchstaben in 6 Versuchen.");
        System.out.println("G = richtige Stelle, Y = andere Stelle, X = nicht vorhanden.");
        System.out.println("Beispiel: A[G] P[Y] F[X] E[X] L[X]");

        // Solange der Spieler j eingibt, wird eine neue Runde gestartet.
        while (playAgain.equals("j")) {
            int wordIndex = random.nextInt(words.length);
            String secretWord = words[wordIndex];

            playRound(scanner, secretWord);

            System.out.println("Noch einmal spielen? (j/n)");
            // So beendet sich das Programm auch, wenn die Eingabe geschlossen wird.
            if (!scanner.hasNextLine()) {
                break;
            }
            playAgain = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
        }

        System.out.println("Danke fürs Spielen!");
        scanner.close();
    }

    // Diese Methode führt genau eine Runde durch.
    public static void playRound(Scanner scanner, String secretWord) {
        int attempt = 1;

        while (attempt <= 6) {
            System.out.println();
            System.out.print("Versuch " + attempt + "/6 - Dein Wort: ");

            if (!scanner.hasNextLine()) {
                return;
            }

            // Leerzeichen außen entfernen und Kleinbuchstaben vereinheitlichen.
            String guess = scanner.nextLine().trim().toUpperCase(Locale.ROOT);

            if (!isValidGuess(guess)) {
                System.out.println("Bitte genau 5 Buchstaben von A bis Z eingeben.");
                // continue springt zurück zum Anfang der Schleife.
                // Eine ungültige Eingabe verbraucht keinen Versuch.
                continue;
            }

            showFeedback(guess, secretWord);

            if (guess.equals(secretWord)) {
                System.out.println("Gewonnen! Du hast " + attempt + " Versuch(e) gebraucht.");
                return;
            }

            attempt++;
        }

        System.out.println("Leider verloren. Das Wort war: " + secretWord);
    }

    // Es werden fünf Buchstaben geprüft, aber kein Wörterbuch für Ratewörter.
    public static boolean isValidGuess(String guess) {
        if (guess.length() != 5) {
            return false;
        }

        for (int i = 0; i < guess.length(); i++) {
            char letter = guess.charAt(i);
            if (letter < 'A' || letter > 'Z') {
                return false;
            }
        }

        return true;
    }

    public static void showFeedback(String guess, String secretWord) {
        // Jeder Platz bekommt einen Hinweis. Am Anfang ist alles X.
        char[] hints = {'X', 'X', 'X', 'X', 'X'};

        // Merkt, welche Buchstaben des gesuchten Wortes schon verwendet wurden.
        boolean[] used = new boolean[5];

        // Zuerst richtige Positionen markieren. Diese haben Vorrang.
        for (int i = 0; i < 5; i++) {
            if (guess.charAt(i) == secretWord.charAt(i)) {
                hints[i] = 'G';
                used[i] = true;
            }
        }

        // Danach nach gleichen Buchstaben an anderen Positionen suchen.
        for (int i = 0; i < 5; i++) {
            if (hints[i] != 'G') {
                for (int j = 0; j < 5; j++) {
                    if (!used[j] && guess.charAt(i) == secretWord.charAt(j)) {
                        hints[i] = 'Y';
                        used[j] = true;
                        // Ein Buchstabe darf nur einmal zugeordnet werden.
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            System.out.print(guess.charAt(i) + "[" + hints[i] + "] ");
        }
        System.out.println();
    }
}
