package ic;

import java.util.ArrayList;

public class Chainage_Arriere {

    public static Boolean func(ArrayList<String> bf, Base_de_regles bregle, ArrayList<String> f) {
        Boolean res;
        if (f.size() == 0) {
            res = true;
        } else {
            System.out.println("Fait a verifier :" + f.get(f.size() - 1));
            String firstFait = f.get(f.size() - 1);
            if (demBut(bf, bregle, firstFait)) {
                System.out.println(firstFait + "\s  est verifie");
                if (!bf.contains(firstFait)) {
                    bf.add(firstFait);
                } // pour ne pas insirer a chaque fois un element deja existe
                f.remove(firstFait);
                res = func(bf, bregle, f);
            } else {
                res = false;
            }
        }
        return res;
    }

    public static Boolean demBut(ArrayList<String> bf, Base_de_regles bregle, String but) {
        if (bf.contains(but)) {
            return true;
        } else {
            boolean res = false;
            while (bregle.countRegleChainageArriere(but) > 0 && !res) {
                try {
                    Regle r = bregle.getRegleChainageArriere(but);
                    r.deactiver();
                    System.out.println("Premises a verifier : " + r.premiss);
                    res = func(bf, bregle, r.premiss);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return res;
        }
    }

}
