package ex2;
import java.io.*;
import java.util.*;

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
            List<Vers> versuri = new ArrayList<>();
            while (scanner.hasNextLine()) {
                versuri.add(new Vers(scanner.nextLine()));
            }
            return versuri.toArray(new Vers[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
