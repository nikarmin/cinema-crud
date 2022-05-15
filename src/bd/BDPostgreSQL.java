/**
 @author Andre Luis.
 @since 2022.
 */

package bd;

import bd.core.*;
import bd.daos.*;
public class BDPostgreSQL
{
    public static final MeuPreparedStatement COMANDO;

    static
    {
    	MeuPreparedStatement comando = null;

    	try
        {
            // caso postar no github, retirar credenciais
            comando =
            new MeuPreparedStatement (
            "org.postgresql.Driver",
            "jdbc:postgresql://ec2-18-210-64-223.compute-1.amazonaws.com/d7fbcjl694dt0q",
            "ahwefcsbjmjsls", "820f1671fe1db3a07e41330d93fc7a7c0546eb515edae3753a16e6d88932da9f");
        }
        catch (Exception erro)
        {
            System.err.println ("Problemas de conexao com o BD");
            System.exit(0); // aborta o programa
        }
        
        COMANDO = comando;
    }
}
