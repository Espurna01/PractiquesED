package fase1.EstructuraDades;

import fase1.Excepcions.elementNoExisteix;
import fase1.Excepcions.operacioImpossible;

public interface TADLlista<T> {

    /**
     * Constructor per inicialitzar la llista.
     */
    void crear();

    /**
     * Funció per tal d'inserir un element al final de la llista.
      * @param data element a inserir.
     */
    void inserir(T data);

    /**
     * Funció per tal d'inserir un element a la llista en la posició indicada.
     * @param posicio posició a inserir element
     * @param data element a inserir.
     * @throws operacioImpossible posició fora de la llista
     */
    void inserir(int posicio, T data) throws operacioImpossible;

    /**
     * Funció que retorna l'element que hi ha en una determinada posició
     * @param posicio posició de l'element
     * @throws operacioImpossible posició fora de la llista
     * @return element T a la posició indicada.
     */
    T obtenir(int posicio) throws operacioImpossible;

    /**
     * Retorna el nombre d'elements que conté la llista en aquest moment.
     * @return longitud de la llista.
     */
    int longitud();

    /**
     * Funció per tal d'esborrar un element de la llista en una posició determinada.
     * @param posicio posició de l'element a esborrar
     * @throws operacioImpossible posició fora de la llista.
     */
    void esborrar(int posicio) throws operacioImpossible;

    /**
     * Funció que comprova si un element està en la llista
     * @param data element a buscar.
     * @throws elementNoExisteix l'element no existeix a la llista
     * @return nombre d'elements que s'hagin accedit per tal de comprovar si l'element existeix o no.
     */
    int buscar(T data) throws elementNoExisteix;

}
