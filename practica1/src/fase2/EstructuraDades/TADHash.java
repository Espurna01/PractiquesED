package fase2.EstructuraDades;

import fase1.EstructuraDades.DLL;
import fase1.Excepcions.elementNoExisteix;
import fase2.Excepcions.noInsercio;
import fase2.Excepcions.noObtenir;

public interface TADHash<K extends Comparable<K>, T extends Comparable<T>> {
    /**
     * Constructor per inicialitzar la taula.
     */
    void crear();

    /**
     * Mètode per tal d'inserir un element a la taula de Hash.
     * Si l'element ja existia s'actualitza el seu valor. Es
     * llença una excepció si no es pot inserir.
     * @param key Identificador
     * @param data Data
     * @throws noInsercio No s'ha pogut inserir.
     */
    void inserir(K key, T data) throws noInsercio;

    /**
     * Mètode que retorna l'element que té la clau K.
     * @param key Identificador
     * @return data
     * @throws noObtenir No s'ha trobat key
     */
    T obtenir(K key) throws noObtenir;

    /**
     * Mètode que comprova si un element està a la taula.
     * @param key Identificador
     * @return El coste de l'operació. Nombre d'elements que s'hagin accedit per tal de comprovar si l'element existeix o no.
     * @throws elementNoExisteix No s'ha trobat l'element.
     */
    int buscar(K key) throws elementNoExisteix;

    /**
     * Mètode per saber la mida de la taula.
     * @return Retorna el nombre d'elements que conté la taula en aquest moment.
     */
    int mida();

    /**
     * Mètode per tal d'esborrar un element de la taula.
     * @param key Identificador
     */
    void esborrar(K key) throws elementNoExisteix;

    /**
     * Mètode per obtenir tots els valors.
     * @return Retorna una llista amb tots els valors de la taula.
     */
    DLL<T> obtenirValors();

    /**
     * Mètode per obtenir totes les claus.
     * @return Retorna una llista amb totes les claus de la taula.
     */
    DLL<K> obtenirClaus();

    /**
     * Mètode per obtenir el factor de càrrega
     * @return Retorna el factor de càrrega.
     */
    Float obtenirFactorDeCarrega();

}
