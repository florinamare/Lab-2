package ex2;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

class Vers {
    private String text;

    public Vers(String text) {
        this.text = text;
    }

    public int numarCuvinte() {
        return text.split("\\s+").length;
    }

    public int numarVocale() {
        int numarVocale = 0;
        for (char c : text.toLowerCase().toCharArray()) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                numarVocale++;
            }
        }
        return numarVocale;
    }

    public String getText() {
        return text;
    }

    public boolean trebuieScrisCuMajuscule() {
        double random = Math.random();
        return random < 0.1;
    }

    public boolean seIncheieCuGrupareAleasa(String grupare) {
        return text.endsWith(grupare);
    }
}

public class MainApp {
    public static void main(String[] args) {
        Vers[] versuri = citesteVersuri("src/ex2/cantec_in.txt");

        try {
            FileWriter writer = new FileWriter("cantec_out.txt");
            for (Vers vers : versuri) {
                int numarCuvinte = vers.numarCuvinte();
                int numarVocale = vers.numarVocale();
                String text = vers.getText();

                if (vers.trebuieScrisCuMajuscule()) {
                    text = text.toUpperCase();
                }

                writer.write(text + " - " + numarCuvinte + " cuvinte, " + numarVocale + " vocale");
                if (vers.seIncheieCuGrupareAleasa("ii")) {
                    writer.write("*");
                }
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Vers[] citesteVersuri(String numeFisier) {
        try {
            File file = new File(numeFisier);
            Scanner scanner = new Scanner(file);
            int numarVersuri = 0;
            while (scanner.hasNextLine()) {
                numarVersuri++;
                scanner.nextLine();
            }
            Vers[] versuri = new Vers[numarVersuri];
            scanner = new Scanner(file);
            for (int i = 0; i < numarVersuri; i++) {
                versuri[i] = new Vers(scanner.nextLine());
            }
            return versuri;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
