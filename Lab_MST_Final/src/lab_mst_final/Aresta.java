package lab_mst_final;

public class Aresta implements Comparable 
{
    int v1, v2;
    double custo;

    public Aresta(int v1, int v2, double custo) 
    {
        this.v1 = v1;
        this.v2 = v2;
        this.custo = custo;
    }
    
    @Override
    public int compareTo(Object t) 
    {
        Aresta outra = (Aresta) t;
        if(this != null && t != null)
        {
            if (this.custo == outra.custo) 
            {
                return 0;
            } 
            else if (this.custo < outra.custo) 
            {
                return -1;
            } 
            else 
            {
                return 1;
            }
        }
        else
            return -1;
    }
}
