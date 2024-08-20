import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("---Willkommen beim Textanalyse-Programm!---");

        while (true) {
            // Eingabe des Textes
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
            char[] encryptedArray = caesarEncrypt(text.toCharArray(), shift);

            // Verschlüsselten Text ausgeben
            System.out.println("Der verschlüsselte Text lautet: " + new String(encryptedArray)); // Array wird für Ausgabe in String umgewandelt

            while (true) {
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
                        scanner.close();
                        return; // Beendet das Programm
                    } else if (choice.equalsIgnoreCase("n")) {
                        // Springt zur nächsten Iteration der äußeren Schleife für neuen Text
                        break;
                    } else {
                        System.out.println("Unbekannte Auswahl. Das Programm wird beendet.");
                        scanner.close();
                        return; // Beendet das Programm
                    }
                } else if (choice.equalsIgnoreCase("b")) {
                    System.out.println("Das Programm wird beendet.");
                    scanner.close();
                    return; // Beendet das Programm
                } else {
                    System.out.println("Unbekannte Auswahl. Bitte wählen Sie 'e' zum Entschlüsseln oder 'b' zum Beenden.");
                }
            }
        }
    }

    // Methode zur Caesar-Verschlüsselung
    public static char[] caesarEncrypt(char[] textArray, int shift) {
        char[] resultArray = new char[textArray.length];
        for (int i = 0; i < textArray.length; i++) {
            char c = textArray[i];
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                resultArray[i] = (char) ((c - base + shift) % 26 + base);
            } else {
                resultArray[i] = c;
            }
        }
        return resultArray;
    }

    // Methode zur Caesar-Entschlüsselung
    public static char[] caesarDecrypt(char[] textArray, int shift) {
        return caesarEncrypt(textArray, -shift);
    }
}
