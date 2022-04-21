package fase2.Aplicacio;

import fase1.EstructuraDades.TADCiutada;
import fase2.EstructuraDades.Hasher;
import fase2.EstructuraDades.TaulaHash;
import fase2.Excepcions.noInsercio;
import fase1.EstructuraDades.CiutadaPeu;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws noInsercio {
        TaulaHash<String, TADCiutada> th = new TaulaHash<>();

        CiutadaPeu a = new CiutadaPeu("Matias","Larrosa Babio","a");
        CiutadaPeu b = new CiutadaPeu("Nancy","Babio Sanchez","b");
        CiutadaPeu c = new CiutadaPeu("Jordi","Cojuhar Cojuhar","c");
        CiutadaPeu d = new CiutadaPeu("Hugo","Acedo Coronado","d");
        CiutadaPeu e = new CiutadaPeu("Lucas","Larrosa Babio","e");
        CiutadaPeu f = new CiutadaPeu("Marcelo","Larrosa Bermudéz","f");

        CiutadaPeu[] lcp = new CiutadaPeu[]{a, b, c, d, e, f};

        for(CiutadaPeu cp : lcp){
            th.inserir(cp.getDni(), cp);
        }

        for(TADCiutada tc : th){
            System.out.println(tc);
        }

        System.out.println("\n" + th);

        /**
         * Espai per fer les comprovacions que cregui necessàries. Per fer el joc de proves he fet servir JUnit testing.
         */

    }
}
