package Aplicacio;

import EstructuraDades.CiutadaCotxe;
import EstructuraDades.CiutadaPeu;
import EstructuraDades.DLL;
import EstructuraDades.TADCiutada;
import Excepcions.elementNoExisteix;
import Excepcions.operacioImposible;

public class App {
    public static void main(String[] args) throws elementNoExisteix, operacioImposible {
        DLL<TADCiutada> dll = new DLL<>();
        TADCiutada a = new CiutadaCotxe("Matias Ariel","Larrosa Babio","39458904N");
        TADCiutada b = new CiutadaPeu("Nancy Elvira","Babio Sanchez","39458903B");
        TADCiutada c = new CiutadaCotxe("Jordi","Cojuhar Cojuhar","123456789Z");
        TADCiutada d = new CiutadaPeu("Hugo","Acedo Coronado","987654321A");
        TADCiutada e = new CiutadaCotxe("Lucas","Larrosa Babio","789654123T");
        TADCiutada f = new CiutadaPeu("Marcelo Eduardo","Larrosa Bermudéz","7531594862M");

        dll.inserir(a);
        dll.inserir(b);
        dll.inserir(c);
        dll.inserir(d);
        dll.inserir(e);
        dll.inserir(f);

        System.out.println("----------------------------------------------------------------------------");
        System.out.println("Elements a la llista:\n" + dll);



    }
}
