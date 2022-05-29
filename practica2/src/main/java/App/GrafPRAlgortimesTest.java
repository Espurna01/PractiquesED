package main.java.App;

import main.java.DataStructure.ComplementaryStructures.NodeEstrella;
import main.java.DataStructure.ComplementaryStructures.NodeGraf;
import main.java.DataStructure.MainStructures.GrafPR;
import main.java.DataStructure.MainStructures.ZonaRecarrega;
import main.java.Excepcions.CamiImpossible;

public class GrafPRAlgortimesTest {
    public static void main(String[] args) throws CamiImpossible {
        /**
         * Aquest test tractara sobre els dos algoritmes implementats (camiOptim i distanciaMaxNoGarantida)
         */

        GrafPR grafPR = new GrafPR();

        System.out.println("Zones de recarrega no accesibles amb una autonomia de 30 desde el node: " + grafPR.getNode(61743) + ":");
        for(NodeGraf<ZonaRecarrega, Double> node : grafPR.zonesDistMaxNoGarantida (61743, 30)){
            System.out.println("\t" + node);
        }

        System.out.println("\nCami optim entre " + grafPR.getNode(61743) + " i " + grafPR.getNode(14849689) + ":");
        for(NodeEstrella node : grafPR.camiOptim(61743, 14849689, 30)){
            System.out.println("\t" + node);
        }

        /**
         * Cami Impossible autonomia molt baixa.
         */
        try {
            System.out.println("\nCami entre " + grafPR.getNode(61743) + " i " + grafPR.getNode(14849689) + " amb una autonomia de 3: ");
            for(NodeEstrella node : grafPR.camiOptim(61743, 14849689, 3)){
                System.out.println("\t" + node);
            }
        }catch (CamiImpossible e){
            System.out.println(e);
        }

        /**
         * Cami Impossible nodes aillats.
         */
        try{
            System.out.println("\nCami entre " + grafPR.getNode(61743) + " i " + grafPR.getNode(33852299) + " amb una autonomia de 40: ");
            for(NodeEstrella node : grafPR.camiOptim(61743, 33852299, 40)){
                System.out.println("\t" + node);
            }
        }catch (CamiImpossible e){
            System.out.println(e);
        }
    }
}
