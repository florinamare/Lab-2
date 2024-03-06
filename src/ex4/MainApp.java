package ex4;
import java.time.LocalDate;
import java.util.Scanner;

class Persoana {
    private String nume;
    private String cnp;

    public Persoana(String nume, String cnp) {
        this.nume = nume;
        this.cnp = cnp;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public int getVarsta() {
        int anNastere = Integer.parseInt(cnp.substring(1, 3));
        int secol = (cnp.charAt(0) - '0') < 3 ? 19 : 20;
        LocalDate dataNasterii = LocalDate.of(secol * 100 + anNastere, Integer.parseInt(cnp.substring(3, 5)), Integer.parseInt(cnp.substring(5, 7)));
        LocalDate dataCurenta = LocalDate.now();
        int varsta = dataCurenta.getYear() - dataNasterii.getYear();
        if (dataCurenta.getMonthValue() < dataNasterii.getMonthValue() ||
                (dataCurenta.getMonthValue() == dataNasterii.getMonthValue() &&
                        dataCurenta.getDayOfMonth() < dataNasterii.getDayOfMonth())) {
            varsta--;
        }
        return varsta;
    }
}

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduceți numărul de persoane:");
        int n = scanner.nextInt();
        scanner.nextLine(); // Consumăm newline

        Persoana[] persoane = new Persoana[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Introduceți numele persoanei " + (i + 1) + ":");
            String nume = scanner.nextLine();

            String cnp;
            boolean cnpValid;
            do {
                System.out.println("Introduceți CNP-ul persoanei " + (i + 1) + ":");
                cnp = scanner.nextLine();
                cnpValid = cnp.length() == 13 && cnp.matches("[0-9]+") && "1256".contains(String.valueOf(cnp.charAt(0)));
                if (cnpValid) {
                    int cifraControl = Integer.parseInt(cnp.substring(12));
                    int sumaControl = 0;
                    int[] coeficienti = {2, 7, 9, 1, 4, 6, 3, 5, 8, 2, 7, 9};
                    for (int j = 0; j < 12; j++) {
                        sumaControl += (cnp.charAt(j) - '0') * coeficienti[j];
                    }
                    int restImpartire = sumaControl % 11;
                    int cifraCalculata = restImpartire == 10 ? 1 : restImpartire;
                    cnpValid = cifraCalculata == cifraControl;
                }
                if (!cnpValid) {
                    System.out.println("CNP-ul introdus nu este valid. Reîncercați.");
                }
            } while (!cnpValid);

            persoane[i] = new Persoana(nume, cnp);
        }

        System.out.println("Informațiile introduse sunt:");

        for (Persoana persoana : persoane) {
            int varsta = persoana.getVarsta();
            if (varsta != -1) {
                System.out.println("Nume: " + persoana.getNume() + ", CNP: " + persoana.getCnp() + ", Varsta: " + persoana.getVarsta());
            } else {
                System.out.println("Nume: " + persoana.getNume() + ", CNP: " + persoana.getCnp() + ", Varsta: CNP invalid");
            }
        }
    }
}
