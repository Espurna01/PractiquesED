package CostComputacional;

import fase1.EstructuraDades.DLL;
import fase1.Excepcions.elementNoExisteix;
import fase2.EstructuraDades.TaulaHash;
import fase2.Excepcions.noInsercio;
import fase2.Excepcions.noTrobat;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class CostTest {
    public static final int MAXN = 50; /* N = [1000, MAXN*1000] */
    public static void main(String[] args) throws noInsercio, IOException {
        /* La meva funcio de hash per a enters retorna el mateix enter */
        /* Per fer les coses justes utilitzaré <String, Integer> on String serà el nombre aleatori en string */
        /* Per aconseguir que hi hagin colisions */
        /* A la especificació del metode inserir diu que s'hi hi han dos claus iguals es modificarà el valor guardat */
        /* Per tant pot passar que surtin 2 números aleatoris repetits i la taula de hash guardarà menys valors */
        DLL<Integer> dll;
        TaulaHash<String, Integer> th;
        Random rand = new Random();
        float[] mitjanaBusquedesDLL = new float[MAXN];
        float[] mitjanaBusquedesTH = new float[MAXN];
        for(int N = 1000; N <= MAXN * 1000; N += 1000) {
            th = new TaulaHash<>(N, true);
            dll = new DLL<>();
            for(int i = 1;i <= N/2; i++){
                int random = rand.nextInt(N/2) + 1; /* [1, N/2] */
                dll.inserir(random);
                try{
                    th.inserir(Integer.toString(random), random);
                } catch (noInsercio ni){
                    System.out.println(ni);
                }
            }
            float busquedesDLL = 0;
            float busquedesTH = 0;
            for(int i = 0;i < N; i++){
                int random = rand.nextInt(N/2) + 1;
                try {
                    busquedesDLL += dll.buscar(random);
                }catch (elementNoExisteix e){
                    busquedesDLL += e.getElements();
                }
                try {
                    busquedesTH += th.buscar(Integer.toString(random));
                } catch (noTrobat e) {
                    busquedesTH += e.getN();
                }
            }
            mitjanaBusquedesDLL[N/1000 - 1] = busquedesDLL/N;
            mitjanaBusquedesTH[N/1000 - 1] = busquedesTH/N;
            System.out.println("prova " + N + " acabada...");
        }
        System.out.println("S'ha acabat la prova del cost:\nMida\tBusquedes DLL\tBusquedes TH");
        for(int i = 0; i < MAXN; i++){
            System.out.println((i+1) * 1000 + "\t" + mitjanaBusquedesDLL[i] + "\t" + mitjanaBusquedesTH[i]);
        }
        toCSV(mitjanaBusquedesDLL, mitjanaBusquedesTH);
    }

    public static void toCSV(float[] mitjanaBusquedesDLL, float[] mitjanaBusquedesTH) throws IOException {
        FileWriter fw = new FileWriter("CostComputacional.csv", false);

        fw.write("MIDA;BUSQUEDES DLL;BUSQUEDES TH" + ";\n");
        for(int i = 0; i < MAXN; i++){
            fw.write((i+1) * 1000 + ";" + mitjanaBusquedesDLL[i] + ";" + mitjanaBusquedesTH[i] + ";\n");
        }
        fw.close();
    }
}
