package fase2.JUnit;

import fase1.EstructuraDades.CiutadaPeu;
import fase1.EstructuraDades.DLL;
import fase1.EstructuraDades.TADCiutada;
import fase1.Excepcions.elementNoExisteix;
import fase2.EstructuraDades.TaulaHash;
import fase2.Excepcions.noInsercio;
import fase2.Excepcions.noObtenir;
import fase2.Excepcions.noTrobat;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaulaHashJUnit {

    TaulaHash<String, TADCiutada> th;

    CiutadaPeu a = new CiutadaPeu("Matias","Larrosa Babio","a");
    CiutadaPeu b = new CiutadaPeu("Nancy","Babio Sanchez","b");
    CiutadaPeu c = new CiutadaPeu("Jordi","Cojuhar Cojuhar","c");
    CiutadaPeu d = new CiutadaPeu("Hugo","Acedo Coronado","d");
    CiutadaPeu e = new CiutadaPeu("Lucas","Larrosa Babio","e");
    CiutadaPeu f = new CiutadaPeu("Marcelo","Larrosa Bermudéz","f");

    CiutadaPeu[] lcp = new CiutadaPeu[]{a, b, c, d, e, f};

    /**
     * Es comprovara si la taula de hash creada te els atributs correctes
     */
    @Test
    void crear(){
        th = new TaulaHash<>();
        /* TaulaHash() crida a crear() */
        assertNotNull(th, "La TH es null");
        assertEquals(0, th.mida(), "Hi han valors inicials en la TH que s'acaba de crear.");
    }

    /**
     * Es comprovara que s'afegeixen elements correctament.
     */
    @Test
    void inserir() throws noInsercio {
        th = new TaulaHash<>();

        assertDoesNotThrow(() -> th.inserir(a.getDni(), a), "El metode inserir() ha llençat l'excepcio noInsercio");
        assertEquals(1, th.mida(), "S'ha afegit 1 element i la mida no es correspon.");
        assertDoesNotThrow(() -> th.inserir(b.getDni(), b), "El metode inserir() ha llençat l'excepcio noInsercio");
        assertEquals(2, th.mida(), "S'ha afegit 1 element i la mida no es correspon.");
        assertDoesNotThrow(() -> th.inserir(c.getDni(), c), "El metode inserir() ha llençat l'excepcio noInsercio");
        assertEquals(3, th.mida(), "S'ha afegit 1 element i la mida no es correspon.");
        assertDoesNotThrow(() -> th.inserir(d.getDni(), d), "El metode inserir() ha llençat l'excepcio noInsercio");
        assertEquals(4, th.mida(), "S'ha afegit 1 element i la mida no es correspon.");
        assertDoesNotThrow(() -> th.inserir(e.getDni(), e), "El metode inserir() ha llençat l'excepcio noInsercio");
        assertEquals(5, th.mida(), "S'ha afegit 1 element i la mida no es correspon.");
        assertDoesNotThrow(() -> th.inserir(f.getDni(), f), "El metode inserir() ha llençat l'excepcio noInsercio");
        assertEquals(6, th.mida(), "S'ha afegit 1 element i la mida no es correspon.");

        boolean[] blcp = {false, false, false, false, false, false};
        for(TADCiutada cp : th.obtenirValors()){
            for(int i = 0; i < 6; i++){
                if(!blcp[i] && lcp[i].compareTo(cp) == 0){
                    blcp[i] = true;
                }
            }
        }
        boolean and = blcp[0];
        for(int i = 1; i < 6; i++) {
            and = and && blcp[i];
        }

        assertTrue(and, "No s'han trobat tots els elements.");

        /* Canviar informació d'un element que ja existeix */

        CiutadaPeu ncp = new CiutadaPeu("Informacio", "Canviada", "a");
        assertDoesNotThrow(() -> th.inserir(ncp.getDni(), ncp), "Inserir ha llençat una excepció al afegir un element amb una clau repetida.");
        assertDoesNotThrow(() -> assertNotEquals(0, th.obtenir("a").getNom().compareTo(a.getNom()), "L'element no te les seves dades canviades."),
                "Inserir un element ha llençat la excepció noInsercio.");

        /* Comprovar que la taula es redimensiona si supera el limit (0.75) */

        reiniciarTH();

        int icapacity = th.getCapacity();
        int imida = th.mida();
        int i = 0;
        while(icapacity == th.getCapacity()){
            /* (char)(i+103) = 'g' */
            CiutadaPeu nncp = new CiutadaPeu(Integer.toString(i), Integer.toString(i), (char)(i+103) + "");
            assertDoesNotThrow(() -> th.inserir(nncp.getDni(), nncp), "No s'ha pogut inserir un ciutada.");
            i++;
        }

        assertEquals(i+imida, th.mida(), "La mida inicial mes els elements afegits no és igual a la mida actual.");
        assertEquals(icapacity*1.5, th.getCapacity(), "No s'ha redimensionat la taula hash correctament.");
        assertTrue(th.obtenirFactorDeCarrega() <= 0.75, "El factor de carrega es superior al limit, s'hauria de redimensionar la taula.");

    }

    /**
     * Obtenir comprova si es troben tots els elements i s'hi s'obtenen correctament.
     * @throws noInsercio
     */

    @Test
    void obtenir() throws noInsercio {
        reiniciarTH(); /* {a, b, c, d, e, f} */

        assertDoesNotThrow(()->{
            for(int i = 0; i < 6;i++){
                TADCiutada tmp = th.obtenir(lcp[i].getDni());
                assertNotNull(tmp, "L'element obtingut es null");
                assertEquals(0, tmp.compareTo(lcp[i]), "L'element obtingut a la posició " + i + " no es l'esperat");
            }
        }, "No s'ha obtés un ciutada");

        /* Comprovar que salta una excepció si es busca un valor que no existeix */

        assertThrows(noObtenir.class, () -> th.obtenir("Clau Inexistent"), "Buscar una clau que no hi és a la llista no llença una excepció.");
    }

    /**
     * Comprova que es puguin buscar tots els elemets i, s'hi l'element que s'està buscant forma part d'una colisió
     * també és troba.
     * @throws noInsercio
     */
    @Test
    void buscar() throws noInsercio {
        reiniciarTH();
        /* Per com funciona el Hasher (XOR) si el codi es repeteix un nombre imparell de vegades el codi sera el mateix */
        /* hash("a") == hash("aaa") */
        /* Actualment la th te 6 ciutadans tots en posicions diferents */
        /* a->7, b->8, c->9, d->0, e->1, f->2 */
        /* Per tant, al ser "aaa" el mateix hash code que "a" s'afegira darrere */
        assertDoesNotThrow(() -> {
            for(int i = 0; i < 6; i++){
                assertEquals(1, th.buscar(lcp[i].getDni()), "La disposicio dels elements no es la correcta.");
            }
        }, "No s'ha obtés un element que hi és a la taula.");

        th.inserir("aaa", new CiutadaPeu("Nou", "Ciutada", "aaa"));

        assertDoesNotThrow(() -> assertEquals(2, th.buscar("aaa"), "El nombre d'elements buscat no correspon amb l'esperat.")
                , "No s'ha trobat un element colisionat");

        assertThrows(noTrobat.class, () -> th.buscar("Clau Inexistent"), "No s'ha llençat una excepció per a un element inexistent.");

    }

    /**
     * Mida comprova que la mida augmenta i disminueix correctament.
     * @throws noInsercio
     */
    @Test
    void mida() throws noInsercio {
        reiniciarTH();

        assertEquals(6, th.mida(), "La mida quan es reiniciar la taula de hash ha de ser 6.");
        th.inserir("aaa", new CiutadaPeu("Nou", "Ciutada", "aaa"));

        assertEquals(7, th.mida(), "S'ha afegit 1 element i la mida no es correspon.");

        th.crear();

        assertEquals(0, th.mida(), "S'ha reiniciat la Taula i la mida no es correspon.");

    }

    /**
     * Es comprova que s'eliminin elements corectament en tots els casos possibles.
     * @throws noInsercio
     */
    @Test
    void esborrar() throws noInsercio {
        reiniciarTH(); /* {a, b, c, d, e, f} */

        assertDoesNotThrow(()->{
            for(int i = 0; i < 6;i++){
                reiniciarTH();
                th.esborrar(lcp[i].getDni());
                TADCiutada tmp = lcp[i];
                assertThrows(noTrobat.class, ()->th.buscar(tmp.getDni()), "L'element " + tmp + " no s'ha eliminat correctament.");
            }
        }, "Esborrar ha llençat una excepció inesperada.");

        reiniciarTH();
        assertThrows(elementNoExisteix.class, ()->th.esborrar("Clau Inexistent"), "No s'ha llençat una excepció al buscar un element inexistent.");

        /* Eliminar un element que colisiona amb un altre */

        CiutadaPeu ncp = new CiutadaPeu("Nou", "Ciutada", "aaa");

        th.inserir(ncp.getDni(), ncp);

        /* Eliminar el "head" de la colisio */

        assertDoesNotThrow(() -> th.esborrar("a"), "No s'ha trobat l'element buscat.");
        assertThrows(noObtenir.class, () -> th.obtenir("a"), "L'element no s'ha eliminat correctament.");
        assertDoesNotThrow(() -> assertEquals(1, th.buscar("aaa"), "No s'ha trobat l'element"),
                "No s'ha trobat el següent element de la colisió.");

        /* Eliminar el "tail" de la colisio */

        reiniciarTH();
        th.inserir(ncp.getDni(), ncp);
        assertDoesNotThrow(() -> th.esborrar("aaa"), "No s'ha trobat l'element buscat.");
        assertThrows(noObtenir.class, () -> th.obtenir("aaa"), "L'element no s'ha eliminat correctament.");
        assertDoesNotThrow(() -> assertEquals(1, th.buscar("a"), "No s'ha trobat l'element"),
                "No s'ha trobat el primer element de la colisió.");

        /* Eliminar un element en el mig d'una colisio */

        CiutadaPeu ncp2 = new CiutadaPeu("Nounou", "Ciutadaciutada", "aaaaa");
        reiniciarTH();
        th.inserir(ncp.getDni(), ncp);
        th.inserir(ncp2.getDni(), ncp2);
        /* Element del mig "aaa" */
        assertDoesNotThrow(() -> th.esborrar("aaa"), "No s'ha trobat l'element buscat.");
        assertThrows(noObtenir.class, () -> th.obtenir("aaa"), "L'element no s'ha eliminat correctament.");
        assertDoesNotThrow(() -> assertEquals(1, th.buscar("a"), "No s'ha trobat l'element"),
                "No s'ha trobat el primer element de la colisió.");
        assertDoesNotThrow(() -> assertEquals(2, th.buscar("aaaaa"), "No s'ha trobat l'element"),
                "No s'ha trobat el següent element de la colisió.");

    }

    /**
     * Es comprova que esl valors obtinguts siguin tots els que hi ha present a la llista.
     * @throws noInsercio
     */
    @Test
    void obtenirValors() throws noInsercio {
        reiniciarTH(); /* {a, b, c, e, f} */
        DLL<TADCiutada> dllcp = th.obtenirValors();

        boolean[] blcp = {false, false, false, false, false, false};
        for(TADCiutada cp : th.obtenirValors()){
            for(int i = 0; i < 6; i++){
                if(!blcp[i] && lcp[i].compareTo(cp) == 0){
                    blcp[i] = true;
                }
            }
        }
        boolean and = blcp[0];
        for(int i = 1; i < 6; i++) {
            and = and && blcp[i];
        }

        assertTrue(and, "No s'han trobat tots els elements.");

        /* Obtenir valors en colisió */

        CiutadaPeu ncp = new CiutadaPeu("Nou", "Ciutada", "aaa");
        CiutadaPeu ncp2 = new CiutadaPeu("Nounou", "Ciutadanou", "aaaaa");
        CiutadaPeu[] lcp2 = {a, b, c, d, e, f, ncp, ncp2};

        th.inserir(ncp.getDni(), ncp);
        th.inserir(ncp2.getDni(), ncp2);

        boolean[] blcp2 = {false, false, false, false, false, false, false, false};
        for(TADCiutada cp : th.obtenirValors()){
            for(int i = 0; i < 8; i++){
                if(!blcp2[i] && lcp2[i].compareTo(cp) == 0){
                    blcp2[i] = true;
                }
            }
        }
        and = blcp2[0];
        for(int i = 1; i < 8; i++) {
            and = and && blcp2[i];
        }

        assertTrue(and, "No s'han trobat tots els elements.");
    }

    /**
     * Es comprova que totes les claus obtingudes es corresponguin amb totes les claus que hi haurien d'haber.
     * @throws noInsercio
     */
    @Test
    void obtenirClaus() throws noInsercio {
        reiniciarTH(); /* {a, b, c, d, e, f} */

        DLL<String> dllccp = th.obtenirClaus();
        for(String s : dllccp){
            assertDoesNotThrow(() -> th.buscar(s), "Les claus retornades no son correctes");
        }

        /* Obtenir claus en colisió */

        CiutadaPeu ncp = new CiutadaPeu("Nou", "Ciutada", "aaa");
        CiutadaPeu ncp2 = new CiutadaPeu("Nounou", "Ciutadanou", "aaaaa");

        th.inserir(ncp.getDni(), ncp);
        th.inserir(ncp.getDni(), ncp2);

        dllccp = th.obtenirClaus();
        for(String s : dllccp){
            assertDoesNotThrow(() -> th.buscar(s), "Les claus retornades no son correctes");
        }

    }

    /**
     * Reinicia la taula de hash per tal de no repetir codi.
     * @throws noInsercio
     */
    private void reiniciarTH() throws noInsercio {
        th = new TaulaHash<>();
        for(CiutadaPeu cp : lcp){
            th.inserir(cp.getDni(), cp);
        }
    }
}
