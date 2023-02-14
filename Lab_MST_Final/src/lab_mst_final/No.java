package lab_mst_final;


public class No 
{
    int id;
    int size;
    No pai;
    public No(int id)
    {
        this.id = id;
        this.size = 1;
        pai = null;//opcional
    }
    
    public No find()
    {
        if(pai == null)
        {
            return this;
        }
        else
        {
            return pai.find();
        }
    }
    
    public void uniao(No aux)
    {
        //buscar pais
        No a = this.find();
        No b = aux.find();
        if(a.id!=b.id)
        {
            //ligar o menor no maior
            if(a.size < b.size)
            {
                a.pai = b;
                b.size += a.size;
                a.size = 0;//opcional
            }
            else
            {
                b.pai = a;
                a.size += b.size;
                b.size = 0;//opcional
            }
        }
    }
}
