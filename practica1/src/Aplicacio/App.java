package Aplicacio;

import EstructuraDades.DLL;
import Excepcions.elementNoExisteix;
import Excepcions.operacioImposible;

public class App {
    public static void main(String[] args) throws elementNoExisteix, operacioImposible {
        DLL<Integer> dll = new DLL<>(3);
        dll.inserir(4);
        dll.inserir(5);
        dll.inserir(1, 2);
        System.out.println(dll);
        dll.inserir(8);
        System.out.println(dll.longitud());
        dll.inserir(4,7);
        dll.inserir(4,6);
        System.out.println(dll);

        dll.esborrar(1);
        System.out.println(dll);
        dll.esborrar(dll.longitud());
        System.out.println(dll);
        dll.esborrar(dll.longitud()/2);
        System.out.println(dll);
    }
}
