package ic;

import java.util.ArrayList;

public class Chainage_Avant {
    public static void func(ArrayList<String> bf, Base_de_regles bRegle, String f) {
        ArrayList<Regle> les_regles_de_chainage = new ArrayList<Regle>();
        try {
            while (!bf.contains(f) && bRegle.countRegleApplicable(bf) > 0) {
                // prend la regle applicable
                Regle regAppl = bRegle.getRegleApplicable(bf);
                // deactiver la regle
                regAppl.deactiver();
                System.out.println("la regle applicable est : \n" + regAppl.toString() + "\n");
                // add regApp to a stack
                les_regles_de_chainage.add(regAppl);
                // add the actions to BF
                bf.addAll(regAppl.action);
                System.out.println("BF pour cet iteration:  " + bf.toString() + "\n");
            }
            if (bf.contains(f)) {
                System.out.println("La regle: " + f + "  est etabli");
                System.out.println("Les regles utilisees pour demontrer {" + f + "} :");
                for (Regle r : les_regles_de_chainage) {
                    System.out.println(r);
                }

            } else {
                System.out.println("La regle: " + f + "  n'est pas etabli");

            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
