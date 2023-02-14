package lab_mst_final;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DesenhaPontos 
{
    int[][][] figura;
    static final File dir =
            new File("C:\\Users\\Victor.Victor_PC-PC\\Desktop");

    public void setPreto(int i, int j) 
    {
        figura[i][j][0] = 0;
        figura[i][j][1] = 0;
        figura[i][j][2] = 0;
    }

    public void setBranco(int i, int j) 
    {
        figura[i][j][0] = 255;
        figura[i][j][1] = 255;
        figura[i][j][2] = 255;
    }

    public void imprimePontos() 
    {
        try {
            int xmax = Integer.MIN_VALUE;
            int ymax = Integer.MIN_VALUE;
            int xmin = Integer.MAX_VALUE;
            int ymin = Integer.MAX_VALUE;

            //primeira vez encontra os limites xmax, xmin, ymax, ymin
            //segunda vez imprime o arquivo ppm

            for (int vez = 0; vez < 2; vez++) 
            {
                int dx = 0;
                int dy = 0;
                BufferedWriter out = null;
                if (vez == 1) 
                {
                    dx = xmax - xmin + 1;
                    dy = ymax - ymin + 1;
                    figura = new int[dx][dy][3];
                    out = new BufferedWriter(new FileWriter(new File(dir, "aux.ppm")));
                    //cabecalho da figura
                    out.write("P3\n");
                    out.write(dy + " " + dx + "\n");
                    out.write("255\n");

                    //inicializa matriz quadriculada
                    for (int i = 0; i < dx; i++) 
                    {
                        for (int j = 0; j < dy; j++) 
                        {
                            if (i % 100 == 9 || j % 100 == 9) 
                            {
                                setPreto(i, j);
                            } 
                            else 
                            {
                                setBranco(i, j);
                            }
                        }
                    }
                }


                //coordenadas.
                BufferedReader b =
                        new BufferedReader(
                        new FileReader(
                        new File(dir, "coordenadas.txt")));
                //leitura
                String linha = b.readLine();
                while (linha != null && linha.length() > 0) {
                    String[] lista = linha.split(";");

                    double a1 = Double.parseDouble(lista[3]);
                    double a2 = Double.parseDouble(lista[4]);

                    //converte em inteiro com um fator de escala 30.
                    int x = (int) (a1 * 30);
                    int y = (int) (a2 * 30);

                    
                    if (vez == 0) {
                        //procura min  e max
                        if (x < xmin) {
                            xmin = x;
                        }
                        if (x > xmax) {
                            xmax = x;
                        }
                        if (y < ymin) {
                            ymin = y;
                        }
                        if (y > ymax) {
                            ymax = y;
                        }
                    } else {
                        //imprime latitude e longitude
                        setPreto(x - xmin, y - ymin);
                    }
                    assert (ymin <= ymax);

                    linha = b.readLine();
                }
                if (vez == 1) {
                    //imprime o arquivo.
                    for (int i = dx - 1; i >= 0; i--) {
                        for (int j = 0; j < dy; j++) {
                            out.write(figura[i][j][0] + " " +
                                    figura[i][j][1] + " " +
                                    figura[i][j][2] + " ");
                        }
                        out.write("\n");
                    }

                    out.write("\n");
                    out.close();
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(DesenhaPontos.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    //leitura de pontos. Exemplo.
    public void le(){
        File dir =
            new File("C:\\Users\\Paula\\Desktop");
        BufferedReader b = null;
        try{
            b = new BufferedReader(
                    new FileReader(
                    new File(dir, "coordenadas.txt")));
            String linha = b.readLine();
            while (linha != null && linha.length() > 0) {
                String[] lista = linha.split(";");
                for(int i=0;i<lista.length;i++){
                    System.out.println(lista[i]+" ");
                }
                double lati = Double.parseDouble(lista[3]);
                double longi = Double.parseDouble(lista[4]);

                linha = b.readLine();
            }
        }catch(Exception e){
            e.printStackTrace();
        }       
    }
}
