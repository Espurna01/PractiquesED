package fase1.JUnit;

import fase1.EstructuraDades.CiutadaPeu;
import fase1.EstructuraDades.DLL;
import fase1.EstructuraDades.TADCiutada;
import fase1.Excepcions.elementNoExisteix;
import fase1.Excepcions.operacioImpossible;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class DLLTest {
    DLL<TADCiutada> dllc;

    CiutadaPeu a = new CiutadaPeu("Matias","Larrosa Babio","a");
    CiutadaPeu b = new CiutadaPeu("Nancy","Babio Sanchez","b");
    CiutadaPeu c = new CiutadaPeu("Jordi","Cojuhar Cojuhar","c");
    CiutadaPeu d = new CiutadaPeu("Hugo","Acedo Coronado","d");
    CiutadaPeu e = new CiutadaPeu("Lucas","Larrosa Babio","e");
    CiutadaPeu f = new CiutadaPeu("Marcelo","Larrosa Bermudéz","f");

    CiutadaPeu[] lcp = new CiutadaPeu[]{a, b, c, d, e, f};

    /**
     * Es comprovara si la dllc creada te els atributs correctes.
     */
    @Test
    void crear() {
        dllc = new DLL<>();
        dllc.crear();
        assertNotNull(dllc, "La DLL es null");
        assertNull(dllc.getData(), "La data inicial creada es null");
    }

    /**
     * Es comprovara si s'han afegit els elements en l'ordre correcte.
     */
    @Test
    void inserir() {
        dllc = new DLL<>();
        dllc.inserir(a);
        dllc.inserir(b);
        dllc.inserir(c);
        dllc.inserir(d);
        dllc.inserir(e);
        dllc.inserir(f);

        DLL<TADCiutada> poi = dllc;
        for(int i = 0;i < 6;i++,poi = poi.getFwd()){
            assertEquals(0, poi.getData().compareTo(lcp[i]), poi + " != " + lcp[i]);
        }
    }

    /**
     * Es comprovara si es poden afegir elements a posicions critiques de la llista.
     * La primera i una del mig(4), per afegir elements al final de la llista s'ha
     * d'utilitzar el metode DLL.inserir(T). I, que llençi l'excepció operacioImpossible
     * quan la poscició introduida estigui fora del rang ([1, longitud()]).
     */
    @Test
    void testInserir() {
        reiniciarDLL();
        CiutadaPeu cp1 = new CiutadaPeu("1","1", "1");
        CiutadaPeu cp2 = new CiutadaPeu("2","2", "2");
        CiutadaPeu cp3 = new CiutadaPeu("3","3", "3");

        assertDoesNotThrow(() -> dllc.inserir(1, cp1), "El métode inserir(int, T) ha llençat l'excepció operacioImpossible");
        /* Inserim a la primera posicio de la llista [1, a, b, c, ..., f ] */
        assertDoesNotThrow(() -> dllc.inserir(4, cp2), "El métode inserir(int, T) ha llençat l'excepció operacioImpossible");
        assertThrows(operacioImpossible.class, () -> dllc.inserir(-1, cp3), "El métode inserir(int, T) NO ha llençat l'excepcio");
        assertThrows(operacioImpossible.class, () -> dllc.inserir(100, cp3), "El métode inserir(int, T) NO ha llençat l'excepcio");

        assertEquals(0, dllc.getData().compareTo(cp1), "El métode inserir(int, T) en la primera posició no guarda l'element en la primera posició");
        assertEquals(0, dllc.getFwd().getFwd().getFwd().getData().compareTo(cp2), "El métode inserir(int, T) en la cuarta posició no guarda l'element en la primera posició");
    }

    /**
     * Es comprovara que cada posició de el DLL utilitzant el metode obtenir(int)
     * retorni els objectes correctes. I, que llençi l'excepció operacioImpossible
     * quan la poscició introduida estigui fora del rang ([1, longitud()]).
     */
    @Test
    void obtenir() {
        reiniciarDLL();
        assertDoesNotThrow(()->{
            for(int i = 0; i < 6;i++){
                TADCiutada tmp = dllc.obtenir(i + 1);
                assertNotNull(tmp, "L'element obtingut a la posició + " + i + 1 + " es null");
                assertEquals(0, tmp.compareTo(lcp[i]), "L'element obtingut a la posició " + i + " no es l'esperat");
            }
        });

        assertThrows(operacioImpossible.class, () -> dllc.obtenir(100), "El métode inserir(int, T) NO ha llençat l'excepcio");
        assertThrows(operacioImpossible.class, () -> dllc.obtenir(-1 ), "El métode inserir(int, T) NO ha llençat l'excepcio");
    }

    /**
     * Es comprovara que s'eliminin correctament els elements de cada posició critica
     * la primera, cualsevol del mig i l'ultima. I, que llençi l'excepció operacioImpossible
     * quan la poscició introduida estigui fora del rang ([1, longitud()]).
     *
     */
    @Test
    void esborrar() {
        reiniciarDLL();

        assertDoesNotThrow(()->{
            for(int i = 0; i < 6;i++){
                reiniciarDLL();
                dllc.esborrar(i+1);
                TADCiutada tmp = lcp[i];
                assertThrows(elementNoExisteix.class, ()->dllc.buscar(tmp), "L'element " + tmp + " no s'ha eliminat correctament.");
            }
        });

        /* Esborrar elements de dll particulars */
        dllc = new DLL<>();
        dllc.inserir(a);
        assertDoesNotThrow(()->dllc.esborrar(1));
        assertNotNull(dllc);
        /* Posició fora del rang */
        reiniciarDLL();
        assertThrows(operacioImpossible.class, () -> dllc.obtenir(100), "El métode inserir(int, T) NO ha llençat l'excepcio");
        assertThrows(operacioImpossible.class, () -> dllc.obtenir(-1 ), "El métode inserir(int, T) NO ha llençat l'excepcio");
    }

    /**
     * Es comprovarà que la longitud comenci en 0 i, augmenti
     * i disminueixi a mesura que s'insereixen i s'esborren elements.
     */
    @Test
    void longitud() {
        dllc = new DLL<>();
        assertEquals(0, dllc.longitud(), "La longitud de un DLL nou ha de ser 0." + dllc);
        dllc.inserir(a);
        assertEquals(1, dllc.longitud(), "La longitud de un DLL amb 1 element ha de ser 1. " + dllc );
        dllc.inserir(b);
        assertEquals(2, dllc.longitud(), "La longitud de un DLL amb 2 element ha de ser 2. " + dllc);
        dllc.inserir(c);
        assertEquals(3, dllc.longitud(), "La longitud de un DLL amb 3 element ha de ser 3. " + dllc);
        dllc.inserir(d);
        assertEquals(4, dllc.longitud(), "La longitud de un DLL amb 4 element ha de ser 4. " + dllc);
        dllc.inserir(e);
        assertEquals(5, dllc.longitud(), "La longitud de un DLL amb 5 element ha de ser 5. " + dllc);
        dllc.inserir(f);
        assertEquals(6, dllc.longitud(), "La longitud de un DLL amb 6 element ha de ser 6. " + dllc);

        assertDoesNotThrow(()->{
            dllc.esborrar(1);
            assertEquals(5, dllc.longitud(), "La longitud de un DLL amb 5 element ha de ser 5. " + dllc);
            dllc.esborrar(1);
            assertEquals(4, dllc.longitud(), "La longitud de un DLL amb 4 element ha de ser 4. " + dllc);
            dllc.esborrar(1);
            assertEquals(3, dllc.longitud(), "La longitud de un DLL amb 3 element ha de ser 3. " + dllc);
            dllc.esborrar(1);
            assertEquals(2, dllc.longitud(), "La longitud de un DLL amb 2 element ha de ser 2. " + dllc);
            dllc.esborrar(1);
            assertEquals(1, dllc.longitud(), "La longitud de un DLL amb 1 element ha de ser 1. " + dllc);
            dllc.esborrar(1);
            assertEquals(0, dllc.longitud(), "La longitud de un DLL amb 0 element ha de ser 0. " + dllc);
        }, "El mètode esborrar ha llençat l'excepció operacioImpossible");

    }

    /**
     * Es comprovara que per a cada entrada de buscar(T) el resultat sera
     * igual a la mateixa posició d'aquell objecte. I, que llençi l'excepció
     * elementNoExisteix si l'element no existeix al DLL.
     */
    @Test
    void buscar() {
        reiniciarDLL();
        assertDoesNotThrow(()->{
            for (int i = 0; i < dllc.longitud();i++)
                assertEquals(dllc.buscar(lcp[i]), i + 1,
                        "Per trobar l'element " + lcp[i] + " no s'han recorregut el nombre correcte de elements");
        }, "El métode DLL.buscar(T) ha llençat l'excepció elementNoExisteix.");
        assertThrows(elementNoExisteix.class, ()->dllc.buscar(new CiutadaPeu("element","No","Existeix")), "El métode DLL.buscar NO ha llençat l'excepció noExisteixElement. ");
    }

    /**
     * Es comprovara que la llista iterada retorna els elements ordenadament.
     */
    @Test
    void iterator() {
        reiniciarDLL();
        int i = 0;
        for(TADCiutada c : dllc){
            assertEquals(0, c.compareTo(lcp[i]), "L'element retornat per l'iterator no es el correcte. " + c + " != " + lcp[i]);
            i++;
        }
    }

    private void reiniciarDLL(){
        dllc = new DLL<>();
        dllc.inserir(a);
        dllc.inserir(b);
        dllc.inserir(c);
        dllc.inserir(d);
        dllc.inserir(e);
        dllc.inserir(f);
    }
}