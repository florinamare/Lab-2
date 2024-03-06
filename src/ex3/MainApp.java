package ex3;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
public class MainApp {
    public static void main(String[] args) {
        StringBuilder text = new StringBuilder();

        // Citirea șirului de caractere
        try (BufferedReader reader = new BufferedReader(new FileReader("src/ex3/input.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Afisarea textului original
        System.out.println("Textul original:");
        System.out.println(text);

        // Citirea șirului de inserat și a poziției de inserare
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Introduceti sirul de inserat:");
            String insertedString = reader.readLine();
            System.out.println("Introduceti pozitia de inserare:");
            int insertPosition = Integer.parseInt(reader.readLine());

            // Inserarea șirului în poziția specificată
            text.insert(insertPosition, insertedString);

            // Afisarea textului dupa inserare
            System.out.println("Textul dupa inserare:");
            System.out.println(text);
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        // Ștergerea unei porțiuni din șir
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Introduceti pozitia de start a sectiunii de sters:");
            int start = Integer.parseInt(reader.readLine());
            System.out.println("Introduceti lungimea sectiunii de sters:");
            int length = Integer.parseInt(reader.readLine());

            // Ștergerea porțiunii de la poziția start până la start+length
            text.delete(start, start + length);

            // Afisarea textului dupa stergere
            System.out.println("Textul dupa stergere:");
            System.out.println(text);
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        // Scrierea textului final în fișierul de ieșire
        try (FileWriter writer = new FileWriter("output.txt")) {
            writer.write(text.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
