package ex1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
public class MainApp {
    public static void main(String[] args) {
        String[] judete = citesteFisier("src/ex1/judete_in.txt");
        Arrays.sort(judete);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceți județul căutat: ");
        String judetCautat = scanner.nextLine();
        int pozitie = Arrays.binarySearch(judete, judetCautat);
        if (pozitie >= 0) {
            System.out.println("Județul " + judetCautat + " se află pe poziția " + (pozitie + 1) + " în lista ordonată de județe.");
        } else {
            System.out.println("Județul " + judetCautat + " nu a fost găsit în lista ordonată de județe.");
        }
    }

    public static String[] citesteFisier(String numeFisier) {
        try {
            File fisier = new File(numeFisier);
            Scanner scanner = new Scanner(fisier);
            int numarJudete = 0;
            while (scanner.hasNextLine()) {
                numarJudete++;
                scanner.nextLine();
            }
            String[] judete = new String[numarJudete];
            scanner = new Scanner(fisier);
            for (int i = 0; i < numarJudete; i++) {
                judete[i] = scanner.nextLine();
            }
            return judete;
        } catch (FileNotFoundException e) {
            System.out.println("Fișierul nu a fost găsit: " + e.getMessage());
            return new String[0];
        }
    }
}
