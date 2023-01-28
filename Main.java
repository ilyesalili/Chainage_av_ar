package ic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    // base de faits pour Chainage avant
    public static ArrayList<String> bf = new ArrayList<String>();
    // base de faits pour Chainage arriere
    public static ArrayList<String> bf2 = new ArrayList<String>();
    public static ArrayList<String> faits = new ArrayList<String>();
    public static String File = "ic/ex.txt";

    public static void main(String[] args) throws Exception {
        List<String> fVal = new ArrayList<String>();
        System.out.println(
                "**************************************\n* \s \s \s \s \s \sChainage Avant\s \s \s \s \s *\n**************************************");

        // base de regles
        Base_de_regles bRegle = new Base_de_regles();

        // lire les donnees depuis le fichier
        readFromFile(bf);
        // lire BF et Fait depuis le fichier
        String s = readFileContents(File);
        fVal = splitToLines(s);
        String fait = fVal.get(0);
        System.out.println("le fait est :\s" + fait);
        System.out.println("Base de faits :\s" + bf.toString());

        // insrer le fait dans un tableu pour le traitement de chainage arriere
        faits.add(fait);

        Regle r;
        for (int i = 2; i < fVal.size(); i++) {
            String item = fVal.get(i).replace(" ", "");
            r = new Regle(item.substring(0, item.length() - 1), item.charAt(item.length() - 1) + "");
            bRegle.addRegle(r);
        }
        bRegle.toString();
        Chainage_Avant.func(bf, bRegle, fait);

        System.out.println(
                "**************************************\n*\s\s \s \s \s \sChainage Arriere\s \s \s \s   *\n**************************************");

        // Chainage arriere

        // lire BF pour chainage arriere
        readFromFile(bf2);
        System.out.println("le fait est :\s" + fait);
        System.out.println("Base de faits :\s" + bf2.toString() + "\n");

        // reactivater tout les regles
        bRegle.init();

        Chainage_Arriere.func(bf2, bRegle, faits);

        System.out.println(Chainage_Arriere.func(bf2, bRegle, faits) ? "La regle " + fait + " est etabli"
                : "La regle " + fait + " n'est pas etabli");

        System.out.println("BF apres l'application du chainage arriere:\n" + bf2.toString() + "\n");
    }

    public static List<String> splitToLines(String input) {

        Stream<String> lines = input.lines();
        return lines.toList();
    }

    public static String readFileContents(String filename) throws Exception {
        Path file = Paths.get(filename);
        return Files.readString(file);
    }

    public static void readFromFile(ArrayList<String> bf) {
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(File));
            // lire la premiere ligne
            String line = reader.readLine();

            // // deuxieme ligne
            line = reader.readLine();
            bf.addAll(Arrays.asList(line.split(",")));
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

}