package opet.tds171a.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import opet.tds171a.vo.Categoria;

public class CategoriaDAO implements ICategoriaDAO {

	/**
	 * definindo as constantes para acesso ao banco
	 */
	public static final String DB_USUARIO = "system";
	public static final String DB_PASS = "oracle";
	public static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";

	/**
	 * Inclui categoria no banco
	 */
	@Override
	public boolean incluir(Categoria categoria) {
		Connection connection = null;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);

			PreparedStatement pstmt = connection.prepareStatement("INSERT INTO CATEGORIA (IDCATEGORIA, NOME) values ("
					+ "coalesce((select max(IDCATEGORIA)+1 from CATEGORIA),1), " + "?)");
			pstmt.setString(1, categoria.getNome());

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
	 * Edita categoria no banco
	 */
	@Override
	public boolean editar(int idCategoria, String nome) {
		Connection connection = null;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);

			PreparedStatement pstmt = connection
					.prepareStatement("UPDATE CATEGORIA SET NOME = ? WHERE IDCATEGORIA = ?");
			pstmt.setString(1, nome);
			pstmt.setInt(2, idCategoria);

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
	 * Excluir categoria
	 */
	@Override
	public boolean excluir(int idCategoria) {
		Connection connection = null;
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);

			PreparedStatement pstmt = connection.prepareStatement("DELETE FROM CATEGORIA WHERE IDCATEGORIA = ?");
			pstmt.setInt(1, idCategoria);

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
	 * Pegar categoria conforme o id
	 */
	@Override
	public Categoria getCategoria(int idCategoria) {
		Connection connection = null;

		Categoria retCategoria = null;
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);

			// traz os registros ordenados por nome
			PreparedStatement pstmt = connection
					.prepareStatement("SELECT IDCATEGORIA, NOME FROM CATEGORIA WHERE IDCATEGORIA = ?");

			pstmt.setInt(1, idCategoria);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				// cria objeto autor para colocar no ArrayList de retorno
				retCategoria = new Categoria(rs.getInt("IDCATEGORIA"), rs.getString("NOME"));

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
		return retCategoria;
	}

	/**
	 * Listrar todas as categorias ordenando por nome
	 */
	@Override
	public List<Categoria> listAll() {
		Connection connection = null;
		// Categoria = null;
		ArrayList<Categoria> listagem = null;
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);

			// traz os registros ordenados por nome
			PreparedStatement pstmt = connection
					.prepareStatement("SELECT IDCATEGORIA, NOME FROM CATEGORIA ORDER BY NOME");

			ResultSet rs = pstmt.executeQuery();
			listagem = new ArrayList<Categoria>();
			while (rs.next()) {
				// cria objeto categoria para colocar no ArrayList de retorno
				Categoria categoria = new Categoria(rs.getInt("IDCATEGORIA"), rs.getString("NOME"));
				listagem.add(categoria);
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

	public boolean salvar(Categoria categoria) {
		Connection connection = null;
		// Autor aut = null;
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);
			PreparedStatement pstmt;

			if (categoria.getCodigo() == -1) {
				pstmt = connection.prepareStatement("INSERT INTO CATEGORIA (IDCATEGORIA, NOME) values ("
						+ "coalesce((select max(IDCATEGORIA)+1 from CATEGORIA),1), " + "?)");
				pstmt.setString(1, categoria.getNome());

				/**
				 * Precisei trocar executeUpdate por execute pq estou utilizando
				 * o select max() para encontrar o novo id para o próximo autor
				 */
				// pstmt.executeUpdate();
				pstmt.execute(); // precisou colocar execute pq uso select para
									// achar novo id.
			} else {
				pstmt = connection.prepareStatement("UPDATE categoria SET NOME = ? WHERE idcategoria = ?");
				pstmt.setString(1, categoria.getNome());
				pstmt.setInt(2, categoria.getCodigo());

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
