/**
 @author Andre Luis, Nicoli (21689), Talita (21261) e Maria Alice (21249).
 @since 2022.
 */

package bd.dbos;

public class Cinema implements Cloneable
{
    private int    codigo;
    private String shopping;
    private String nome;
    private String  sala;
    private String  cep;
    private String  instagram;
    private String numero;
    private String complemento;

    public void setCodigo (int codigo) throws Exception
    {
        if (codigo <= 0)
            throw new Exception ("Codigo invalido");

        this.codigo = codigo;
    }

    public void setNome (String nome) throws Exception
    {
        if (nome==null || nome.equals(""))
            throw new Exception ("Nome nao fornecido");

        this.nome = nome;
    }

    public void setShopping (String shopping) throws Exception
    {
        if (shopping.length() < 0 || shopping.length() > 50)
            throw new Exception ("Nome de shopping invalido");

        this.shopping = shopping;
    }

    public void setSala (String sala) throws Exception
    {
        if (sala.length() < 0 || sala.length() > 5)
            throw new Exception ("Sala do cinema invalida");

        this.sala = sala;
    }

    public void setCep (String cep) throws Exception
    {
        if(cep.length() != 8)
            throw new Exception ("CEP invalido");

        this.cep = cep;
    }

    public void setInstagram (String instagram) throws Exception
    {
        if(instagram.length() < 0 || instagram.length() > 30)
            throw new Exception ("Instagram do cinema invalido");

        this.instagram = instagram;
    }

    public void setNumero (String numero) throws Exception
    {
        if(numero.length() < 0 || numero.length() > 8)
            throw new Exception ("Numero do cinema invalido");

        this.numero = numero;
    }

    public void setComplemento (String complemento) throws Exception
    {
        if(complemento.length() < 0 || complemento.length() > 50)
            throw new Exception ("Numero do cinema invalido");

        this.complemento = complemento;
    }

    public int getCodigo ()
    {
        return this.codigo;
    }

    public String getNome ()
    {
        return this.nome;
    }

    public String getShopping ()
    {
        return this.shopping;
    }

    public String getSala () { return this.sala; }

    public String getCep () { return this.cep; }

    public String getInstagram () { return this.instagram; }

    public String getNumero () { return this.numero; }

    public String getComplemento () { return this.complemento; }
    public Cinema(int codigo, String nome, String shopping, String sala, String cep, String instagram, String numero, String complemento) throws Exception
    {
        this.setCodigo (codigo);
        this.setNome   (nome);
        this.setShopping  (shopping);
        this.setSala     (sala);
        this.setCep      (cep);
        this.setInstagram(instagram);
        this.setNumero(numero);
        this.setComplemento(complemento);
    }

    public String toString ()
    {
        String ret="";

        ret+="\n"+"Codigo: "+this.codigo+"\n";
        ret+="Nome..: "+this.nome  +"\n";
        ret+="Shopping.: "+this.shopping +"\n";
        ret+="Sala.: "+ this.sala +"\n";
        ret+="Instagram.: "+ this.instagram +"\n";
        ret+="Numero.: "+ this.numero  +"\n";
        ret+="Complemento.: " + this.complemento + "\n";
        ret+="Cep.: "+ this.cep;

        return ret;
    }

    public boolean equals (Object obj)
    {
        if (this==obj)
            return true;

        if (obj==null)
            return false;

        if (!(obj instanceof Cinema))
            return false;

        Cinema cin = (Cinema)obj;

        if (this.codigo != cin.codigo)
            return false;

        if (this.nome.equals(cin.nome))
            return false;

        if (this.shopping.equals(cin.shopping))
            return false;

        if (this.sala.equals(cin.sala))
            return false;

        if (this.cep.equals(cin.cep))
            return false;

        if (this.instagram.equals(cin.instagram))
            return false;

        if (this.numero.equals(cin.numero))
            return false;

        if (this.complemento.equals(cin.complemento))
            return false;

        return true;
    }

    public int hashCode ()
    {
        int ret=666;

        ret = 7*ret + Integer.valueOf(this.codigo).hashCode();      // new Integer(this.codigo)
        ret = 7*ret + this.nome.hashCode();
        ret = 7*ret + this.shopping.hashCode();
        ret = 7*ret + this.sala.hashCode();
        ret = 7*ret + this.cep.hashCode();
        ret = 7*ret + this.instagram.hashCode();
        ret = 7*ret + this.numero.hashCode();
        ret = 7*ret + this.complemento.hashCode();

        return ret;
    }

    public Cinema(Cinema modelo) throws Exception
    {
        this.codigo = modelo.codigo; // nao clono, pq nao eh objeto
        this.nome   = modelo.nome;   // nao clono, pq nao eh clonavel
        this.shopping  = modelo.shopping;  // nao clono, pq nao eh objeto
        this.sala = modelo.sala;
        this.cep = modelo.cep;
        this.instagram = modelo.instagram;
        this.numero = modelo.numero;
        this.complemento = modelo.complemento;
    }

    public Object clone ()
    {
        Cinema ret=null;

        try
        {
            ret = new Cinema(this);
        }
        catch (Exception erro)
        {} // nao trato, pq this nunca � null e construtor de
           // copia da excecao qdo seu parametro for null

        return ret;
    }
}