package opet.tds171a.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import opet.tds171a.vo.Autor;

/**
 * Classe implementando a manipulação de dados no oracle
 * 
 * @author Tiago Oliveira e Henrique de Paula
 *
 */
public class AutorDAO implements IAutorDAO {

	public static final String DB_USUARIO = "system";
	public static final String DB_PASS = "oracle";
	public static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";

	/**
	 * Grava o autor no banco de dados
	 */
	@Override
	public boolean incluir(Autor autor) {

		Connection connection = null;
		// Autor aut = null;
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);

			PreparedStatement pstmt = connection.prepareStatement("INSERT INTO autor (IDAUTOR, NOME) values ("
					+ "coalesce((select max(idautor)+1 from autor),1), " + "?)");
			pstmt.setString(1, autor.getNome());

			/**
			 * Precisei trocar executeUpdate por execute pq estou utilizando o
			 * select max() para encontrar o novo id para o próximo autor
			 */
			// pstmt.executeUpdate();
			pstmt.execute(); // precisou colocar execute pq uso select para
								// achar novo id.

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
	 * Lista os autores cadastrados
	 */
	@Override
	public ArrayList<Autor> listAll() {
		Connection connection = null;
		ArrayList<Autor> listagem = null;
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);

			// traz os registros ordenados por nome
			PreparedStatement pstmt = connection.prepareStatement("SELECT IDAUTOR, NOME FROM AUTOR ORDER BY NOME");

			ResultSet rs = pstmt.executeQuery();
			listagem = new ArrayList<Autor>();
			while (rs.next()) {
				// cria objeto autor para colocar no ArrayList de retorno
				Autor autor = new Autor(rs.getInt("IDAUTOR"), rs.getString("NOME"));
				listagem.add(autor);
			}
			// fecha o prepareStatement
			if (pstmt != null)
				pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());

		} finally {
			try {
				// fecha a conexão
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listagem;

	}

	public void setNome(String nome) {

	}

	/**
	 * Exclui um autor do banco de dados.
	 * 
	 * @param idAutor
	 *            - código do autor no banco de dados
	 * @return boolean
	 */
	public boolean excluir(int idAutor) {

		Connection connection = null;
		// Autor aut = null;
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);

			PreparedStatement pstmt = connection.prepareStatement("DELETE FROM AUTOR WHERE IDAUTOR = ?");
			pstmt.setInt(1, idAutor);

			pstmt.executeUpdate(); // precisou colocar execute pq uso select
									// para achar novo id.

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
	 * Retorna um objeto autor, tendo como base seu ID no banco de dados
	 */
	public Autor getAutor(int idAutor) {

		Connection connection = null;
		// Autor aut = null;
		Autor retAutor = null;
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);

			// traz os registros ordenados por nome
			PreparedStatement pstmt = connection.prepareStatement("SELECT IDAUTOR, NOME FROM AUTOR WHERE IDAUTOR = ?");

			pstmt.setInt(1, idAutor);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				// cria objeto autor para colocar no ArrayList de retorno
				retAutor = new Autor(rs.getInt("IDAUTOR"), rs.getString("NOME"));

			}
			// fecha o prepareStatement
			if (pstmt != null)
				pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());

		} finally {
			try {
				// fecha a conexão
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return retAutor;

	}

	/**
	 * Edita o nome do autor no banco de dados
	 */
	@Override
	public boolean editar(int idAutor, String nome) {
		Connection connection = null;
		// Autor aut = null;
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);

			PreparedStatement pstmt = connection.prepareStatement("UPDATE AUTOR SET NOME = ? WHERE IDAUTOR = ?");
			pstmt.setString(1, nome);
			pstmt.setInt(2, idAutor);

			pstmt.executeUpdate();

			if (pstmt != null)
				pstmt.close();

			if (connection != null)
				connection.close();
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return true;
	}

	public boolean salvar(Autor autor) {
		Connection connection = null;
		// Autor aut = null;
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);
			PreparedStatement pstmt;

			if (autor.getCodigo() == -1) {
				pstmt = connection.prepareStatement("INSERT INTO autor (IDAUTOR, NOME) values ("
						+ "coalesce((select max(idautor)+1 from autor),1), " + "?)");
				pstmt.setString(1, autor.getNome());

				/**
				 * Precisei trocar executeUpdate por execute pq estou utilizando
				 * o select max() para encontrar o novo id para o próximo autor
				 */
				// pstmt.executeUpdate();
				pstmt.execute(); // precisou colocar execute pq uso select para
									// achar novo id.
			} else {
				pstmt = connection.prepareStatement("UPDATE AUTOR SET NOME = ? WHERE IDAUTOR = ?");
				pstmt.setString(1, autor.getNome());
				pstmt.setInt(2, autor.getCodigo());

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
}
