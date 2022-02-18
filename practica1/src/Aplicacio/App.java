package Aplicacio;

import EstructuraDades.DLL;

public class App {
    public static void main(String[] args) {
        DLL<Integer> dll = new DLL<>(3);
        dll.inserir(4);
        dll.inserir(5);
        DLL<Integer> dll2 = new DLL<>();
        dll2.inserir(3);
        dll2.inserir(4);
        dll2.inserir(5);
    }
}
