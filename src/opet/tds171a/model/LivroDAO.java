package opet.tds171a.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import opet.tds171a.vo.Livro;

public class LivroDAO implements ILivroDAO
{
    public static final String DB_USUARIO = "system";
    public static final String DB_PASS    = "oracle";
    public static final String DB_URL     = "jdbc:oracle:thin:@localhost:1521:XE";

    /**
     * Inclui Livro no banco
     */
    @Override
    public boolean incluir(Livro livro)
    {
        Connection connection = null;

        try
        {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);
            
            PreparedStatement pstmt = connection
                            .prepareStatement("INSERT INTO LIVRO (IDLIVRO, TITULO, RESUMO, IDAUTOR, ATIVO, IDEDITORA, IDCATEGORIA) values "

                                            +"("+"coalesce((select max(IDLIVRO)+1 from LIVRO),1), " + "?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, livro.getTitulo());
            pstmt.setString(2, livro.getResumo());
            pstmt.setInt(3, livro.getIdAutor());
            pstmt.setString(4, livro.getAtivo());
            pstmt.setInt(5, livro.getIdEditora());
            pstmt.setInt(6, livro.getIdCategoria());

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

     * Salva as alteracoes do livro no banco

     * @param livro
     * @return
     */
    public boolean salvar(Livro livro) {
		Connection connection = null;
		// Autor aut = null;
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);

			PreparedStatement pstmt;
			/* se for -1 então é uam isnerção */
			if(livro.getIdLivro() == -1) {
				pstmt = connection
                        .prepareStatement("INSERT INTO LIVRO (IDLIVRO, TITULO, RESUMO, IDAUTOR, ATIVO, IDEDITORA, IDCATEGORIA) values "

                                        +"("+"coalesce((select max(IDLIVRO)+1 from LIVRO),1), " + "?, ?, ?, ?, ?, ?)");
				pstmt.setString(1, livro.getTitulo());
				pstmt.setString(2, livro.getResumo());
				pstmt.setInt(3, livro.getIdAutor());
				pstmt.setString(4, livro.getAtivo());
				pstmt.setInt(5, livro.getIdEditora());
				pstmt.setInt(6, livro.getIdCategoria());

				// pstmt.executeUpdate();
				pstmt.execute(); // precisou colocar execute pq uso select para
									// achar novo id.
				
			} else {
				pstmt = connection.prepareStatement("UPDATE livro SET TITULO = ? , RESUMO = ?, IDAUTOR = ?, IDEDITORA = ?, IDCATEGORIA = ? WHERE idlivro = ?");
				pstmt.setString(1, livro.getTitulo());
				pstmt.setString(2, livro.getResumo());
				pstmt.setInt(3, livro.getIdAutor());
				pstmt.setInt(4, livro.getIdEditora());
				pstmt.setInt(5, livro.getIdCategoria());
				pstmt.setInt(6, livro.getIdLivro());
				pstmt.executeUpdate();
			}
				
			if (pstmt != null)
				pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return true;
	}

    /**
     * Edita livro no banco
     */
    @Override
    public boolean editar(int idLivro, String titulo, String resumo, int idAutor, String ativo, int idEditora, int idCategoria)
    {
        Connection connection = null;
        // Autor aut = null;
        try
        {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);
            
            PreparedStatement pstmt = connection
                            .prepareStatement(
                                            "UPDATE LIVRO SET TITULO, RESUMO, IDAUTOR, ATIVO, IDEDITORA, IDCATEGORIA = ?, ?, ?, ?, ? WHERE IDLIVRO = ?");
            pstmt.setString(1, titulo);
            pstmt.setString(2, resumo);
            pstmt.setInt(3, idAutor);
            pstmt.setString(4, ativo);
            pstmt.setInt(5, idEditora);
            pstmt.setInt(6, idCategoria);
            pstmt.setInt(7, idLivro);

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
     * Exclui livro do banco
     */
    @Override
    public boolean excluir(int idLivro)
    {
        Connection connection = null;

        try
        {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.print("Conectando ao banco...");
            connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);
            System.out.println("Conectado!");

            PreparedStatement pstmt = connection
                            .prepareStatement("DELETE FROM LIVRO WHERE IDLIVRO = ?");
            pstmt.setInt(1, idLivro);

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
     * Pega um livro do banco conforme seu id
     */
    @Override
    public Livro getLivro(int idLivro)
    {
        Connection connection = null;

        Livro retLivro = null;
        try
        {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.print("Conectando ao banco...");
            connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);
            System.out.println("Conectado!");

            // traz os registros ordenados por nome
            PreparedStatement pstmt = connection
                            .prepareStatement("SELECT IDLIVRO, TITULO, RESUMO, IDAUTOR, ATIVO, IDEDITORA, IDCATEGORIA FROM LIVRO WHERE IDLIVRO = ?");

            pstmt.setInt(1, idLivro);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                // cria objeto autor para colocar no ArrayList de retorno
                retLivro = new Livro(rs.getInt("IDLIVRO"), rs.getString("TITULO"), rs.getString("RESUMO"),
                                rs.getInt("IDAUTOR"), rs.getString("ATIVO"), rs.getInt("IDEDITORA"),
                                rs.getInt("IDEDITORA"));

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
        return retLivro;
    }

    /**
     * Listar todos os livros do banco
     */
    @Override
    public List<Livro> listAll()
    {
        Connection connection = null;

        ArrayList<Livro> listagem = null;
        try
        {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);

            // traz os registros ordenados por nome
            PreparedStatement pstmt = connection
                            .prepareStatement("SELECT IDLIVRO, TITULO, RESUMO, IDAUTOR, ATIVO, IDEDITORA, IDCATEGORIA FROM LIVRO ORDER BY TITULO");

            ResultSet rs = pstmt.executeQuery();
            listagem = new ArrayList<Livro>();
            while (rs.next())
            {
                // cria objeto autor para colocar no ArrayList de retorno
                Livro livro = new Livro(rs.getInt("IDLIVRO"), rs.getString("TITULO"),
                                rs.getString("RESUMO"), rs.getInt("IDAUTOR"),
                                rs.getString("ATIVO"), rs.getInt("IDEDITORA"),
                                rs.getInt("IDCATEGORIA"));

                listagem.add(livro);
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
}
