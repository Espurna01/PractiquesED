package Aplicacio;

import EstructuraDades.CiutadaPeu;
import EstructuraDades.DLL;
import EstructuraDades.TADCiutada;
import Excepcions.elementNoExisteix;
import Excepcions.operacioImpossible;

public class App {
    public static <T extends Comparable<T>> void reiniciarDll(DLL<T> dll, T[] array){
        dll.crear();
        for(T data : array) dll.inserir(data);
    }
    public static void main(String[] args) throws elementNoExisteix, operacioImpossible {
        DLL<TADCiutada> dll = new DLL<>();
        CiutadaPeu a = new CiutadaPeu("Matias","Larrosa Babio","1");
        CiutadaPeu b = new CiutadaPeu("Nancy","Babio Sanchez","2");
        CiutadaPeu c = new CiutadaPeu("Jordi","Cojuhar Cojuhar","3");
        CiutadaPeu d = new CiutadaPeu("Hugo","Acedo Coronado","4");
        CiutadaPeu e = new CiutadaPeu("Lucas","Larrosa Babio","5");
        CiutadaPeu f = new CiutadaPeu("Marcelo","Larrosa Bermudéz","6");

        CiutadaPeu[] lcp = new CiutadaPeu[]{a, b, c, d, e, f};

        reiniciarDll(dll, lcp);
        System.out.println("Elements a la DLL: " + dll);

    }
}
