package opet.tds171a.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import opet.tds171a.vo.Editora;

public class EditoraDAO implements IEditoraDAO {

	/**
	 * definindo as constantes para acesso ao banco
	 */
	public static final String DB_USUARIO = "system";
	public static final String DB_PASS = "oracle";
	public static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";

	/**
	 * Insere uma editora no banco
	 */
	@Override
	public boolean incluir(Editora editora) {
		Connection connection = null;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);

			PreparedStatement pstmt = connection.prepareStatement("INSERT INTO EDITORA (IDEDITORA, NOME) values ("
					+ "coalesce((select max(IDEDITORA)+1 from EDITORA),1), " + "?)");
			pstmt.setString(1, editora.getNome());

			/**
			 * Precisei trocar executeUpdate por execute pq estou utilizando o
			 * select max() para encontrar o novo id para o próximo autor
			 */
			// pstmt.executeUpdate();
			pstmt.execute(); // precisou colocar execute pq uso select para
								// achar novo id.

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
	
	
/**
 * Edita uma editora no banco
 */
	@Override
	public boolean editar(int idEditora, String nome) {

		Connection connection = null;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);

			PreparedStatement pstmt = connection.prepareStatement("UPDATE EDITORA SET NOME = ? WHERE IDEDITORA = ?");
			pstmt.setString(1, nome);
			pstmt.setInt(2, idEditora);

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

	/**
	 * Exclui editora do banco
	 */
	@Override
	public boolean excluir(int idEditora) {
		Connection connection = null;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);

			PreparedStatement pstmt = connection.prepareStatement("DELETE FROM EDITORA WHERE IDEDITORA = ?");
			pstmt.setInt(1, idEditora);

			pstmt.executeUpdate(); // precisou colocar execute pq uso select
									// para achar novo id.

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

	/**
	 * Pega uma editora do banco conforme o id
	 */
	@Override
	public Editora getEditora(int idEditora) {
		Connection connection = null;

		Editora retEditora = null;
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);

			// traz os registros ordenados por nome
			PreparedStatement pstmt = connection
					.prepareStatement("SELECT IDEDITORA, NOME FROM EDITORA WHERE IDEDITORA = ?");

			pstmt.setInt(1, idEditora);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				// cria objeto autor para colocar no ArrayList de retorno
				retEditora = new Editora(rs.getInt("IDEDITORA"), rs.getString("NOME"));

			}
			// fecha o prepareStatement
			if (pstmt != null)
				pstmt.close();
			// fecha a conexão
			if (connection != null)
				connection.close();
		} catch (Exception e) {
			System.out.println(e.toString());

		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return retEditora;
	}

	/**
	 * Lista todas as editoras do banco
	 */
	@Override
	public List<Editora> listAll() {
		Connection connection = null;
		// Editora = null;
		ArrayList<Editora> listagem = null;
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);

			// traz os registros ordenados por nome
			PreparedStatement pstmt = connection.prepareStatement("SELECT IDEDITORA, NOME FROM EDITORA ORDER BY NOME");

			ResultSet rs = pstmt.executeQuery();
			listagem = new ArrayList<Editora>();
			while (rs.next()) {
				// cria objeto Editora para colocar no ArrayList de retorno
				Editora editora = new Editora(rs.getInt("IDEDITORA"), rs.getString("NOME"));
				listagem.add(editora);
			}
			// fecha o prepareStatement
			if (pstmt != null)
				pstmt.close();
			// fecha a conexão
			if (connection != null)
				connection.close();
		} catch (Exception e) {
			System.out.println(e.toString());

		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listagem;

	}

	public boolean salvar(Editora editora) {
		Connection connection = null;
		// Autor aut = null;
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);
			PreparedStatement pstmt;

			if (editora.getCodigo() == -1) {
				pstmt = connection.prepareStatement("INSERT INTO EDITORA (IDEDITORA, NOME) values ("
						+ "coalesce((select max(IDEDITORA)+1 from EDITORA),1), " + "?)");
				pstmt.setString(1, editora.getNome());

				/**
				 * Precisei trocar executeUpdate por execute pq estou utilizando
				 * o select max() para encontrar o novo id para o próximo autor
				 */
				// pstmt.executeUpdate();
				pstmt.execute(); // precisou colocar execute pq uso select para
									// achar novo id.

			} else {
				pstmt = connection.prepareStatement("UPDATE editora SET NOME = ? WHERE ideditora = ?");
				pstmt.setString(1, editora.getNome());
				pstmt.setInt(2, editora.getCodigo());

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
