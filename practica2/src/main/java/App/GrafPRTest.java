package main.java.App;

import main.java.DataStructure.ComplementaryStructures.ArestaPruebaGraf;
import main.java.DataStructure.ComplementaryStructures.NodeGraf;
import main.java.DataStructure.MainStructures.GrafPR;
import main.java.DataStructure.MainStructures.ZonaRecarrega;

public class GrafPRTest {
    public static void main(String[] args) {

        /**
         * Test de les funcions basiques del graf.
         * Utilitzaré un graf basic per les operacions simples i el graf de la practica per les complexes
         */

        GrafPrueba grafPrueba = new GrafPrueba();

        /**
         * S'utilitzara d'exemple el grafic de la pagina 36 del fitxer 'tema5.pdf' de teoria
         */

        System.out.println("Graf buit: \n" + grafPrueba);

        grafPrueba.afegirNode("A");
        grafPrueba.afegirNode("B");
        grafPrueba.afegirNode("C");
        grafPrueba.afegirNode("D");

        System.out.println("Graf amb 4 nodes: \n" + grafPrueba);

        grafPrueba.afegirAresta(grafPrueba.getNode("A"), grafPrueba.getNode("B"), new ArestaPruebaGraf("AB"));
        grafPrueba.afegirAresta(grafPrueba.getNode("A"), grafPrueba.getNode("C"), new ArestaPruebaGraf("AC"));
        grafPrueba.afegirAresta(grafPrueba.getNode("A"), grafPrueba.getNode("D"), new ArestaPruebaGraf("AD"));
        grafPrueba.afegirAresta(grafPrueba.getNode("B"), grafPrueba.getNode("C"), new ArestaPruebaGraf("BC"));
        grafPrueba.afegirAresta(grafPrueba.getNode("B"), grafPrueba.getNode("D"), new ArestaPruebaGraf("BD"));
        grafPrueba.afegirAresta(grafPrueba.getNode("C"), grafPrueba.getNode("D"), new ArestaPruebaGraf("CD"));

        System.out.println("Graf amb 4 nodes connectats: \n" + grafPrueba);
        /**
         * X(Y) -> X correspon al valor que hi ha en la columna, Y correspon al valor de la aresta.
         */
        boolean existeixenTotes = true;
        for(char i = 'A'; i < 'E' && existeixenTotes;i++){
            for(char j = 'A'; j < 'E' && existeixenTotes; j++){
                if(i == j)
                    continue;
                existeixenTotes = grafPrueba.existeixAresta(grafPrueba.getNode(i + ""), grafPrueba.getNode(j + ""));
            }
        }

        System.out.println("Existeixen totes les arestes: " + existeixenTotes);

        System.out.println("Valor aresta entre A-B, D-A i C-A:");

        System.out.println("A-B: " + grafPrueba.valorAresta(grafPrueba.getNode("B"), grafPrueba.getNode("A")));
        System.out.println("D-A: " + grafPrueba.valorAresta(grafPrueba.getNode("D"), grafPrueba.getNode("A")));
        System.out.println("C-A: " + grafPrueba.valorAresta(grafPrueba.getNode("C"), grafPrueba.getNode("A")));

        System.out.println("Adjacents de tots els nodes:");

        System.out.println("A -> " + grafPrueba.adjacents(grafPrueba.getNode("A")));
        System.out.println("B -> " + grafPrueba.adjacents(grafPrueba.getNode("B")));
        System.out.println("C -> " + grafPrueba.adjacents(grafPrueba.getNode("C")));
        System.out.println("D -> " + grafPrueba.adjacents(grafPrueba.getNode("D")));

        System.out.println("\n//////////////////\n");

        /**
         * Mateixos tests pero amb la informacio del icaen.json
         * el valor aresta de cada node graf correspon a la distancia entre cada zona de recarrega
         * i, a cada node guardo: el nom, la latitud i longitud, la id de la estació i una llista
         * de endolls sabent quin es el maxim.
         */

        GrafPR grafPR = new GrafPR();

        /**
         * Aresta entre dos nodes de distancia major a 40 km
         */

        System.out.println("Valor aresta: " + grafPR.valorAresta(grafPR.getNode(61735), grafPR.getNode(15515362)));
        System.out.println("Distancia entre els dos nodes: " + grafPR.distancia(grafPR.getNode(61735), grafPR.getNode(15515362)));

        /**
         * Comprovar que totes les adjacencies a un node tenen una distancia menor a 40 km o que només tenen un node enllaçat
         */

        boolean allAdjacentNodes = true;
        for(NodeGraf<ZonaRecarrega, Double> node : grafPR.adjacents(grafPR.getNode(61735))){
            allAdjacentNodes = grafPR.valorAresta(node, grafPR.getNode(61735)).getInfo() < 40;
            if(!allAdjacentNodes)
                break;
        }

        System.out.println("Tots els nodes adjacents tenen una distancia menor a 40km (< 40)\n\t" + allAdjacentNodes);

        System.out.println("L'unic node que te un node adjacent amb una distancia major a 40 es (>=40)");

        System.out.println("Nodes adjacents a (" + grafPR.getNode(33852300) + "): " + grafPR.adjacents(grafPR.getNode(33852300)));
        System.out.println("Nodes adjacents a (" + grafPR.getNode(33852299) + "): " + grafPR.adjacents(grafPR.getNode(33852299)));

    }
}
