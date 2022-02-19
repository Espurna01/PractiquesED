package Aplicacio;

import EstructuraDades.CiutadaPeu;
import EstructuraDades.DLL;
import EstructuraDades.TADCiutada;
import Excepcions.elementNoExisteix;
import Excepcions.operacioImposible;

public class App {
    public static void main(String[] args) throws elementNoExisteix, operacioImposible {
        DLL<TADCiutada> dll = new DLL<>();
        CiutadaPeu a = new CiutadaPeu("Matias Ariel","Larrosa Babio","39458904N");
        CiutadaPeu b = new CiutadaPeu("Nancy Elvira","Babio Sanchez","39458903B");
        CiutadaPeu c = new CiutadaPeu("Jordi","Cojuhar Cojuhar","123456789Z");
        CiutadaPeu d = new CiutadaPeu("Hugo","Acedo Coronado","987654321A");
        CiutadaPeu e = new CiutadaPeu("Lucas","Larrosa Babio","789654123T");
        CiutadaPeu f = new CiutadaPeu("Marcelo Eduardo","Larrosa Bermudéz","7531594862M");

        CiutadaPeu[] lcp = new CiutadaPeu[6];
        lcp[0] = a;
        lcp[1] = b;
        lcp[2] = c;
        lcp[3] = d;
        lcp[4] = e;
        lcp[5] = f;

        dll.inserir(a);
        dll.inserir(2, b);
        dll.inserir(c);
        dll.inserir(d);
        dll.inserir(e);
        dll.inserir(f);
        dll.buscar(a);
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("Elements a la llista:\n" + dll);



    }
}
