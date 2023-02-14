package lab_mst_final;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class ExercicioKruskal 
{
    static int size = 27; //28142
    static String nome[] = new String[size];
    static double[] latitude = new double[size];
    static double[] longitude = new double[size];

    public static void leArquivo()
    {
        File dir = new File("C:\\Users\\Stephanie\\Desktop\\Lab_MST_Final\\src\\lab_mst_final");
        BufferedReader b = null;
        int j = 0;
        try{
            b = new BufferedReader(new FileReader(new File(dir, "Capitais.txt")));
            String linha = b.readLine();
            while (linha != null && linha.length() > 0) 
            {
                String[] lista = linha.split(";");
//                for(int i = 0; i < lista.length; i++){
//                    System.out.println(lista[i]+" ");
//                }
                nome[j] = lista[0];
                latitude[j] = Double.parseDouble(lista[3]);
                longitude[j] = Double.parseDouble(lista[4]);
                j++;
                linha = b.readLine();
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /** calculo da distancia seguindo a formula:

    Multiplique D por 111km para transformar a distÃ¢ncia angular em distÃ¢ncia linear.
     * @param la1 - latitude 1
     * @param la2 - latitude 2
     * @param lo1 - longitude 1
     * @param lo2 - longitude 2
     * @return
     */
    public double distancia(double lat1, double lat2, double lon1, double lon2) 
    {
        double R = 6371.0; // raio da terrakm
        double dLat = (lat2 - lat1) * Math.PI / 180.0;
        double dLon = (lon2 - lon1) * Math.PI / 180;
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180)
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c;

        return d;
    }


    public static void main(String[] args) 
    {
        leArquivo();
        ExercicioKruskal x = new ExercicioKruskal();
        int p = 0;// n = 1000;
        double dist;
        Dados[] vet = new Dados[(size*size-size)/2];
        //double[][] m = new double[n][n];

        for (int i = 0; i < size-1; i++) 
        {
            for (int j = i+1; j < size; j++) 
            {
                if(i != j)
                {
                    dist = x.distancia(x.latitude[i],x.latitude[j],x.longitude[i], x.longitude[j]);
                    //if(dist < 150){
                        vet[p] = new Dados();
                        vet[p].setDist(dist);
                        vet[p].setCid1(i);
                        vet[p].setCid2(j);
                        p++;
                    //}
                }
            }
        }
        //System.out.println("p = "+p);
        long ti = System.currentTimeMillis();
        Kruskal k = new Kruskal();
        Aresta [] T = k.MST(vet,size);
        long tf = System.currentTimeMillis() - ti;
        double custo =0;
        int cont = 0;
        for(int i = 0; i < T.length; i++)
        {
            Aresta e = T[i];
            cont++;
            System.out.println(x.nome[e.v1]+"  -  "+x.nome[e.v2]+" => "+e.custo+" km");
            custo+=e.custo;
            //System.out.println("Arestas = "+cont);
        }
        System.out.println("Numero total de arestas = "+cont);
        System.out.println("Custo Total: "+custo);
        System.out.println("Tempo de execucao: "+tf+"ms.");        
    }
}


