import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("---Willkommen beim Textanalyse-Programm!---");

        while (true) {
            String text;
            while (true) {
                System.out.print("Bitte geben Sie Ihren Text ein, der gespeichert werden soll: ");
                text = scanner.nextLine();
                if (text.isEmpty()) {
                    System.out.println("Fehler: Die Eingabe darf nicht leer sein.");
                } else {
                    System.out.println("Die Eingabe wurde gespeichert.");
                    break;
                }
            }

            // Umwandeln des Textes in ein char-Array
            char[] textArray = text.toCharArray();

            // Eingabe des Verschiebungswerts für die Caesar-Verschlüsselung
            int shift;
            while (true) {
                System.out.print("Bitte geben Sie den Verschiebungswert für die Caesar-Verschlüsselung ein: ");
                if (scanner.hasNextInt()) {
                    shift = scanner.nextInt();
                    scanner.nextLine();  // Scanner-Problem bei nextInt() beheben
                    break;
                } else {
                    System.out.println("Fehler: Bitte geben Sie eine gültige Zahl ein.");
                    scanner.next();
                }
            }

            // Caesar-Verschlüsselung anwenden
            char[] encryptedArray = caesarEncrypt(textArray, shift);

            // Verschlüsselten Text ausgeben
            System.out.println("Der verschlüsselte Text lautet: " + new String(encryptedArray)); // Array wird für Ausgabe in String umgewandelt

            // Option zum Entschlüsseln oder Beenden
            System.out.println("Möchten Sie den Text entschlüsseln (e) oder das Programm beenden (b)?");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("e")) {
                // Entschlüsseln
                char[] decryptedArray = caesarDecrypt(encryptedArray, shift);
                System.out.println("Der entschlüsselte Text lautet: " + new String(decryptedArray));

                // Abfrage, ob ein neuer Text eingegeben werden soll
                System.out.println("Möchten Sie einen neuen Text eingeben (n) oder das Programm beenden (b)?");
                choice = scanner.nextLine();

                if (choice.equalsIgnoreCase("b")) {
                    System.out.println("Das Programm wird beendet.");
                    break;
                } else if (!choice.equalsIgnoreCase("n")) {
                    System.out.println("Unbekannte Auswahl. Das Programm wird beendet.");
                    break;
                }
            } else if (choice.equalsIgnoreCase("b")) {
                System.out.println("Das Programm wird beendet.");
                break;
            } else {
                System.out.println("Unbekannte Auswahl. Bitte wählen Sie 'e' zum Entschlüsseln oder 'b' zum Beenden.");
            }
        }

        scanner.close();
    }

// Methode zur Caesar-Verschlüsselung mit Arrays
    public static char[] caesarEncrypt(char[] textArray, int shift) {
        // Erstellen eines Arrays für den verschlüsselten Text, gleiche Länge wie das Eingabe-Array
        char[] resultArray = new char[textArray.length];

        // Schleife über jedes Zeichen im Eingabe-Array
        for (int i = 0; i < textArray.length; i++) {
            // Extrahieren des aktuellen Zeichens aus dem Eingabe-Array
            char c = textArray[i];

            // Überprüfen, ob das Zeichen ein Buchstabe ist
            if (Character.isLetter(c)) {
                // Bestimmen der Basis für die Verschiebung ('a' für Kleinbuchstaben, 'A' für Großbuchstaben)
                char base = Character.isLowerCase(c) ? 'a' : 'A';

                // Verschieben des Buchstabens und Berechnen des neuen Zeichens
                // (c - base ..): Berechnet die Position des Buchstabens innerhalb des Alphabets
                // (.. + shift): Verschiebt diese Position um den Wert von shift
                // % 26: Modulo 26 sorgt dafür, dass die Verschiebung zirkulär bleibt
                // + base: Setzt den verschobenen Wert zurück in den ASCII-Bereich für Buchstaben
                // (char): Wandelt das Ergebnis von int zurück in char um
                resultArray[i] = (char) ((c - base + shift) % 26 + base);
            } else {
                // Hier bleiben Zahlen, Sonderzeichen und Leerzeichen unverändert
                resultArray[i] = c;
            }
        }

        // Rückgabe des Arrays mit den verschlüsselten Zeichen
        return resultArray;
    }


    // Methode zur Caesar-Entschlüsselung mit Arrays
    public static char[] caesarDecrypt(char[] textArray, int shift) {
        return caesarEncrypt(textArray, -shift);
    }
}
