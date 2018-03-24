package opet.tds171a.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import opet.tds171a.vo.Financeiro;

public class FinanceiroDAO implements IFinanceiroDAO
{

    public static final String DB_USUARIO = "system";
    public static final String DB_PASS    = "oracle";
    public static final String DB_URL     = "jdbc:oracle:thin:@localhost:1521:XE";

    /**
     * Inclui um boleto
     */
    @Override
    public boolean incluir(Financeiro pFinanceiro)
    {
        Connection connection = null;

        try
        {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);

            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO FINANCEIRO_BOLETOS" +
                            " (IDBOLETO, DTEMISSAO, DTVENCIMENTO, VALOR, JUROS_PAGOS, PAGO, IDUSUARIO) " +
                            " VALUES (coalesce((select max(idboleto)+1 from financeiro_boletos),1),  " + "?, ?, ?, ?, ?, ?)");

            pstmt.setDate(1, new java.sql.Date(pFinanceiro.getDtEmissao().getTime()));
            pstmt.setDate(2, new java.sql.Date(pFinanceiro.getDtVencimento().getTime()));
            pstmt.setDouble(3, pFinanceiro.getValor());
            pstmt.setDouble(4, pFinanceiro.getJurosPago());
            pstmt.setString(5, pFinanceiro.getPago());
            pstmt.setInt(6, pFinanceiro.getIdUsuario());

            /**
             * Precisei trocar executeUpdate por execute pq estou utilizando o
             * select max() para encontrar o novo id para o próximo autor
             */
            // pstmt.executeUpdate();
            pstmt.execute(); // precisou colocar execute pq uso select para
                             // achar novo id.

            if (pstmt != null)
                pstmt.close();

        }
        catch (Exception e)
        {
            System.out.println(e.toString());
            return false;
        }
        finally
        {
            try
            {
                // fecha a conexão
                if (connection != null)
                    connection.close();
            }
            catch (SQLException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return true;
    }

    /**
     * Edita um boleto
     */
    @Override
    public boolean editar(int idBoleto, String pago)
    {

        Connection connection = null;

        try
        {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);
            

            PreparedStatement pstmt = connection
                            .prepareStatement(
                                            "UPDATE FINANCEIRO_BOLETOS SET PAGO = ? WHERE IDBOLETO = ?");
            pstmt.setString(1, pago);
            pstmt.setInt(2, idBoleto);
            pstmt.executeUpdate();

            if (pstmt != null)
                pstmt.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        finally
        {
            try
            {
                // fecha a conexão
                if (connection != null)
                    connection.close();
            }
            catch (SQLException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return true;

    }

    /**
     * Exclui um boleto
     */
    @Override
    public boolean excluir(int pIdBoleto)
    {
        Connection connection = null;

        try
        {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);

            PreparedStatement pstmt = connection
                            .prepareStatement("DELETE FROM FINANCEIRO_BOLETOS WHERE IDBOLETO = ?");
            pstmt.setInt(1, pIdBoleto);

            pstmt.executeUpdate(); // precisou colocar execute pq uso select
                                   // para achar novo id.

            if (pstmt != null)
                pstmt.close();

        }
        catch (Exception e)
        {
            System.out.println(e.toString());
            return false;
        }
        finally
        {
            try
            {
                // fecha a conexão
                if (connection != null)
                    connection.close();
            }
            catch (SQLException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return true;
    }

    /**
     * pega um boleto no banco
     */
    @Override
    public Financeiro getFinanceiro(int pIdBoleto)
    {
        Connection connection = null;

        Financeiro retFinanceiro = null;
        try
        {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);

            // traz os registros ordenados por nome
            PreparedStatement pstmt = connection
                            .prepareStatement(
                                            "SELECT IDBOLETO, DTEMISSAO, DTVENCIMENTO, VALOR, JUROS_PAGOS, PAGO, IDUSUARIO FROM FINANCEIRO_BOLETOS WHERE IDBOLETO = ?");

            pstmt.setInt(1, pIdBoleto);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                // cria objeto autor para colocar no ArrayList de retorno
                retFinanceiro = new Financeiro(rs.getInt("IDBOLETO"), rs.getDate("DTEMISSAO"),
                                rs.getDate("DTVENCIMENTO"), rs.getDouble("VALOR"), rs.getDouble("JUROS_PAGOS"),
                                rs.getString("PAGO"), rs.getInt("IDUSUARIO"));

            }
            // fecha o prepareStatement
            if (pstmt != null)
                pstmt.close();

        }
        catch (Exception e)
        {
            System.out.println(e.toString());

        }
        finally
        {
            try
            {
                // fecha a conexão
                if (connection != null)
                    connection.close();
            }
            catch (SQLException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return retFinanceiro;
    }

    /**
     * Listar boletos na tabela Financeiro
     */
    @Override
    public List<Financeiro> listAll()
    {
        Connection connection = null;

        ArrayList<Financeiro> listagem = null;
        try
        {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);

            // traz os registros ordenados por nome
            PreparedStatement pstmt = connection
                            .prepareStatement(
                                            "SELECT IDBOLETO, DTEMISSAO, DTVENCIMENTO, VALOR, JUROS_PAGOS, PAGO, IDUSUARIO FROM FINANCEIRO_BOLETOS");

            ResultSet rs = pstmt.executeQuery();
            listagem = new ArrayList<Financeiro>();
            while (rs.next())
            {
                // cria objeto autor para colocar no ArrayList de retorno
                Financeiro financeiro = new Financeiro(rs.getInt("IDBOLETO"), rs.getDate("DTEMISSAO"),
                                rs.getDate("DTVENCIMENTO"), rs.getDouble("VALOR"), rs.getDouble("JUROS_PAGOS"),
                                rs.getString("PAGO"), rs.getInt("IDUSUARIO"));

                listagem.add(financeiro);
            }
            // fecha o prepareStatement
            if (pstmt != null)
                pstmt.close();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());

        }
        finally
        {
            try
            {
                if (connection != null)
                    connection.close();
            }
            catch (SQLException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return listagem;
    }

    public boolean salvar(Financeiro financeiro)
    {
        Connection connection = null;

        try
        {

            Class.forName("oracle.jdbc.driver.OracleDriver");

            connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);

            PreparedStatement pstmt = connection
                            .prepareStatement("UPDATE FINANCEIRO_BOLETOS SET PAGO = ? WHERE IDBOLETO = ?");
            pstmt.setString(1, financeiro.getPago());
            pstmt.setInt(2, financeiro.getIdBoleto());

            pstmt.executeUpdate();

            if (pstmt != null)
                pstmt.close();

        }
        catch (Exception e)
        {
            System.out.println(e.toString());
            return false;
        }
        finally
        {
            try
            {
                if (connection != null)
                    connection.close();
            }
            catch (SQLException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return true;
    }

}
