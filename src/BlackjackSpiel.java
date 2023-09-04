import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BlackjackSpiel {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        List<String> karten = new ArrayList<>();
        for (int i = 2; i <= 11; i++) {
            for (int j = 0; j < 4; j++) {
                String karte = Integer.toString(i);
                karten.add(karte);
            }
        }
        for (int i = 0; i < 4; i++) {
            karten.add("Bube");
            karten.add("Dame");
            karten.add("König");
            karten.add("Ass");
        }

        int spielerPunkte = 0;
        int dealerPunkte = 0;

        for (int i = 0; i < 2; i++) {
            int index = random.nextInt(karten.size());
            String karte = karten.get(index);
            spielerPunkte += wertDerKarte(karte);
            karten.remove(index);
        }

        for (int i = 0; i < 2; i++) {
            int index = random.nextInt(karten.size());
            String karte = karten.get(index);
            dealerPunkte += wertDerKarte(karte);
            karten.remove(index);
        }

        System.out.println("Ihre Karten: " + spielerPunkte);
        System.out.println("Dealer zeigt: " + dealerPunkte);

        while (spielerPunkte < 21) {
            System.out.print("Möchten Sie eine weitere Karte ziehen? (Ja/Nein): ");
            String antwort = scanner.next();
            if (antwort.equalsIgnoreCase("Ja")) {
                int index = random.nextInt(karten.size());
                String karte = karten.get(index);
                spielerPunkte += wertDerKarte(karte);
                karten.remove(index);
                System.out.println("Ihre Karten: " + spielerPunkte);
            } else {
                break;
            }
        }

        while (dealerPunkte < 17) {
            int index = random.nextInt(karten.size());
            String karte = karten.get(index);
            dealerPunkte += wertDerKarte(karte);
            karten.remove(index);
        }

        System.out.println("Ihre Karten: " + spielerPunkte);
        System.out.println("Dealer Karten: " + dealerPunkte);

        if (spielerPunkte > 21 || (dealerPunkte <= 21 && dealerPunkte >= spielerPunkte)) {
            System.out.println("Dealer gewinnt!");
        } else {
            System.out.println("Sie gewinnen!");
        }
        scanner.close();
    }

    public static int wertDerKarte(String karte) {
        if (karte.equals("Bube") || karte.equals("Dame") || karte.equals("König")) {
            return 10;
        } else if (karte.equals("Ass")) {
            return 11;
        } else {
            return Integer.parseInt(karte);
        }
    }
}