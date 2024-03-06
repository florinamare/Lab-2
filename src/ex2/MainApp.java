package ex2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Vers {
    private String vers;

    public Vers(String vers) {
        this.vers = vers;
    }

    public int numarCuvinte() {
        return vers.split("\\s+").length;
    }

    public int numarVocale() {
        int count = 0;
        String vocale = "aeiouAEIOU";
        for (char c : vers.toCharArray()) {
            if (vocale.indexOf(c) != -1) {
                count++;
            }
        }
        return count;
    }

    public String afisareVers() {
        return vers + " (" + numarCuvinte() + " cuvinte, " + numarVocale() + " vocale)";
    }
}

public class MainApp {
    public static String generareVersModificat(String vers) {
        if (Math.random() < 0.1) {
            return vers.toUpperCase();
        }
        return vers;
    }

    public static String adaugaSteluta(String vers, String grupareAleasa) {
        if (vers.endsWith(grupareAleasa)) {
            return vers + " *";
        }
        return vers;
    }

    public static String citesteGrupareAleasa() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introdu gruparea de litere aleasă: ");
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        List<Vers> versuri = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/ex2/cantec_in.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                versuri.add(new Vers(line.trim()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String grupareAleasa = citesteGrupareAleasa();

        try (FileWriter writer = new FileWriter("cantec_out.txt")) {
            for (Vers vers : versuri) {
                String versModificat = generareVersModificat(vers.afisareVers());
                versModificat = adaugaSteluta(versModificat, grupareAleasa);
                writer.write(versModificat + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
