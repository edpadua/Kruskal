package lab_mst_final;
import java.util.Arrays;

public class Kruskal 
{
    public Aresta [] MST (Dados[] m, int size)
    {
        int n = m.length, k = 1000;
        Aresta [] T = new Aresta[size-1];
        Aresta [] S = new Aresta[n];
        //S<-E
        int t = 0;
        for(int i = 0; i < n; i++)
        {
              S[t++]= new Aresta(m[i].getCid1(), m[i].getCid2(), m[i].getDist());
//            for(int j = i+1; j < n; j++)
//            {
//                S[t++]= new Aresta(i,j,m[i][j]);
//            }
        }
        Arrays.sort(S);
        // criar Union Find para cada vertice
        No [] no = new No[n];
        for(int i = 0; i < n; i++){
            no[i] = new No(i);
        }
        t = 0;
        for(int i = 0; i < S.length; i++){
            Aresta e = S[i];
            if(e != null){
                No a = no[e.v1].find();
                No b = no[e.v2].find();
                if(a.id != b.id){
                    T[t++]=e;
                    a.uniao(b);
                }
            }
        }
        return T;
    }
}
