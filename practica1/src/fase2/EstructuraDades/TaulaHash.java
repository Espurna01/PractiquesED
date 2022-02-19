package fase2.EstructuraDades;

import fase1.EstructuraDades.DLL;
import fase1.Excepcions.elementNoExisteix;
import fase2.Excepcions.noInsercio;
import fase2.Excepcions.noObtenir;

public class TaulaHash<K extends Comparable<K>, T extends Comparable<T>> implements TADHash<K, T>{
    @Override
    public void crear() {

    }

    @Override
    public void inserir(K key, T data) throws noInsercio {

    }

    @Override
    public T obtenir(K key) throws noObtenir {
        return null;
    }

    @Override
    public int buscar(K key) throws elementNoExisteix {
        return 0;
    }

    @Override
    public int mida() {
        return 0;
    }

    @Override
    public void esborrar(K key) throws elementNoExisteix {

    }

    @Override
    public DLL<T> obtenirValors() {
        return null;
    }

    @Override
    public DLL<K> obtenirClaus() {
        return null;
    }

    @Override
    public Float obtenirFactorDeCarrega() {
        return null;
    }
}
