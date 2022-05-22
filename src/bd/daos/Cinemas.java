/**
 @author Andre Luis, Nicoli (21689), Talita (21261) e Maria Alice (21249).
 @since 2022.
 */

package bd.daos;

import bd.BDPostgreSQL;
import bd.core.MeuResultSet;
import bd.dbos.Cinema;

import java.sql.SQLException;
import java.util.ArrayList;

public class Cinemas
{
    public static boolean cadastrado (int codigo) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM CINEMAS " +
                  "WHERE ID = ?";

            BDPostgreSQL.COMANDO.prepareStatement (sql);

            BDPostgreSQL.COMANDO.setInt (1, codigo);

            MeuResultSet resultado = (MeuResultSet)BDPostgreSQL.COMANDO.executeQuery ();

            retorno = resultado.first(); // pode-se usar resultado.last() ou resultado.next() ou resultado.previous() ou resultado.absotule(numeroDaLinha)

            /* // ou, se preferirmos,

            String sql;

            sql = "SELECT COUNT(*) AS QUANTOS " +
                  "FROM LIVROS " +
                  "WHERE CODIGO = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, codigo);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            resultado.first();

            retorno = resultado.getInt("QUANTOS") != 0;

            */
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar cinema");
        }

        return retorno;
    }

    public static void incluir (Cinema cinema) throws Exception
    {
        if (cinema==null)
            throw new Exception ("Cinema nao fornecido");

        // doideira po
        if (cadastrado (cinema.getCodigo()))
            throw new Exception ("Cinema ja cadastrado");

        try
        {
            String sql;

            sql = "INSERT INTO CINEMAS " +
                  "(ID,NOME,SHOPPING,SALA,CEP,INSTAGRAM,NUMERO,COMPLEMENTO) " +
                  "VALUES " +
                  "(?,?,?,?,?,?,?,?)";

            BDPostgreSQL.COMANDO.prepareStatement (sql);

            BDPostgreSQL.COMANDO.setInt    (1, cinema.getCodigo ());
            BDPostgreSQL.COMANDO.setString (2, cinema.getNome ());
            BDPostgreSQL.COMANDO.setString  (3, cinema.getShopping ());
            BDPostgreSQL.COMANDO.setString  (4, cinema.getSala ());
            BDPostgreSQL.COMANDO.setString  (5, cinema.getCep ());
            BDPostgreSQL.COMANDO.setString  (6, cinema.getInstagram ());
            BDPostgreSQL.COMANDO.setString  (7, cinema.getNumero ());
            BDPostgreSQL.COMANDO.setString  (8, cinema.getComplemento ());

            BDPostgreSQL.COMANDO.executeUpdate ();
            BDPostgreSQL.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            BDPostgreSQL.COMANDO.rollback();
            throw new Exception ("Erro ao inserir cinema");
        }
    }

    public static void excluir (int codigo) throws Exception
    {
        if (!cadastrado (codigo))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "DELETE FROM CINEMAS " +
                  "WHERE ID=?";

            BDPostgreSQL.COMANDO.prepareStatement (sql);

            BDPostgreSQL.COMANDO.setInt (1, codigo);

            BDPostgreSQL.COMANDO.executeUpdate ();
            BDPostgreSQL.COMANDO.commit        ();        }
        catch (SQLException erro)
        {
            BDPostgreSQL.COMANDO.rollback();
            throw new Exception ("Erro ao excluir cinema");
        }
    }

    public static void alterar (Cinema cinema) throws Exception
    {
        if (cinema==null)
            throw new Exception ("Cinema nao fornecido");

        // retirada do método já que não fazia sentido 20/05/22

        /*if (cadastrado (cinema.getCodigo()))
            throw new Exception ("Ja existe um cinema neste local!");*/

        try
        {
            String sql;

            sql = "UPDATE CINEMAS " +
                  "SET NOME=?, " +
                  "SHOPPING=?, " +
                  "SALA=?, " +
                  "CEP=?, " +
                  "INSTAGRAM=?, " +
                  "NUMERO=?, " +
                  "COMPLEMENTO=? " +
                  "WHERE ID = ?";

            BDPostgreSQL.COMANDO.prepareStatement (sql);

            BDPostgreSQL.COMANDO.setString (1, cinema.getNome ());
            BDPostgreSQL.COMANDO.setString  (2, cinema.getShopping ());
            BDPostgreSQL.COMANDO.setString  (3, cinema.getSala ());
            BDPostgreSQL.COMANDO.setString    (4, cinema.getCep ());
            BDPostgreSQL.COMANDO.setString    (5, cinema.getInstagram ());
            BDPostgreSQL.COMANDO.setString  (6, cinema.getNumero ());
            BDPostgreSQL.COMANDO.setString  (7, cinema.getComplemento ());
            BDPostgreSQL.COMANDO.setInt    (8, cinema.getCodigo ());

            BDPostgreSQL.COMANDO.executeUpdate ();
            BDPostgreSQL.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            BDPostgreSQL.COMANDO.rollback();
            throw new Exception ("Erro ao atualizar dados de cinema");
        }
    }

    public static Cinema getCinema (String cep) throws Exception
    {
        Cinema cinema = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                    "FROM CINEMAS " +
                    "WHERE CEP = ?";

            BDPostgreSQL.COMANDO.prepareStatement (sql);

            BDPostgreSQL.COMANDO.setString (1, cep);

            MeuResultSet resultado = (MeuResultSet)BDPostgreSQL.COMANDO.executeQuery ();

            if (!resultado.first())
                throw new Exception ("Nao cadastrado");

            cinema = new Cinema (resultado.getInt   ("ID"),
                    resultado.getString("NOME"),
                    resultado.getString ("SHOPPING"),
                    resultado.getString ("SALA"),
                    resultado.getString("CEP"),
                    resultado.getString("INSTAGRAM"),
                    resultado.getString("NUMERO"),
                    resultado.getString("COMPLEMENTO"));
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar cinema");
        }

        return cinema;
    }

    public static Cinema getCinema (int codigo) throws Exception
    {
        Cinema cinema = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM CINEMAS " +
                  "WHERE ID = ?";

            BDPostgreSQL.COMANDO.prepareStatement (sql);

            BDPostgreSQL.COMANDO.setInt (1, codigo);

            MeuResultSet resultado = (MeuResultSet)BDPostgreSQL.COMANDO.executeQuery ();

            if (!resultado.first())
                throw new Exception ("Nao cadastrado");

            cinema = new Cinema (resultado.getInt   ("ID"),
                               resultado.getString("NOME"),
                               resultado.getString ("SHOPPING"),
                               resultado.getString ("SALA"),
                               resultado.getString("CEP"),
                               resultado.getString("INSTAGRAM"),
                               resultado.getString("NUMERO"),
                               resultado.getString("COMPLEMENTO"));
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar cinema");
        }

        return cinema;
    }

    public static ArrayList<Cinema> getCin () throws Exception
    {
        ArrayList<Cinema> c = new ArrayList<>();

        try
        {
            String sql;

            sql = "SELECT * " +
                    "FROM CINEMAS ";

            BDPostgreSQL.COMANDO.prepareStatement (sql);

            //BDPostgreSQL.COMANDO.setInt (1, codigo);

            MeuResultSet resultado = (MeuResultSet)BDPostgreSQL.COMANDO.executeQuery ();


            while(resultado.next())
            {
                Cinema cinema = new Cinema (resultado.getInt   ("ID"),
                        resultado.getString("NOME"),
                        resultado.getString ("SHOPPING"),
                        resultado.getString ("SALA"),
                        resultado.getString("CEP"),
                        resultado.getString("INSTAGRAM"),
                        resultado.getString("NUMERO"),
                        resultado.getString("COMPLEMENTO"));

                c.add(cinema);
            }

        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar cinema");
        }

        return c;
    }

    public static MeuResultSet getCinema () throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM CINEMAS";

            BDPostgreSQL.COMANDO.prepareStatement (sql);

            resultado = (MeuResultSet)BDPostgreSQL.COMANDO.executeQuery ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao recuperar cinemas");
        }

        return resultado;
    }
}
