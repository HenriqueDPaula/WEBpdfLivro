package opet.tds171a.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import opet.tds171a.vo.Usuario;

public class UsuarioDAO implements IUsuarioDAO

{

	public static final String DB_USUARIO = "system";
	public static final String DB_PASS = "oracle";
	public static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";

	/**
	 * cadastro do usuario, ja setando o grupo ao qual pertence(2 =
	 * usuarioComum)
	 * 
	 * @param usuario
	 * @return
	 */
	public boolean usuarioCadastro(Usuario usuario) {
		Connection connection = null;
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);

			PreparedStatement pstmt = connection.prepareStatement(
					"INSERT INTO USUARIO (IDUSUARIO, NOME, LOGIN, PWD, ENDERECO, NUMERO, COMPLEMENTO, IDGRUPO, CPF) values "
							+ "(" + "coalesce((select max(idusuario)+1 from usuario),1), " + "?, ?, ?, ?, ?, ?, 2, ?)");
			pstmt.setString(1, usuario.getNome());
			pstmt.setString(2, usuario.getLogin());
			pstmt.setString(3, usuario.getPwd());
			pstmt.setString(4, usuario.getEndereco());
			pstmt.setInt(5, usuario.getNumero());
			pstmt.setString(6, usuario.getComplemento());
			pstmt.setString(7, usuario.getCpf());

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
				// fecha a conexão
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
	 * Inclusao usuario
	 */
	@Override
	public boolean incluir(Usuario usuario) {
		Connection connection = null;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);

			PreparedStatement pstmt = connection.prepareStatement(
					"INSERT INTO USUARIO (IDUSUARIO, NOME, LOGIN, PWD, ENDERECO, NUMERO, COMPLEMENTO, IDGRUPO, CPF) values "
							+ "(" + "coalesce((select max(idusuario)+1 from usuario),1), " + "?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, usuario.getNome());
			pstmt.setString(2, usuario.getLogin());
			pstmt.setString(3, usuario.getPwd());
			pstmt.setString(4, usuario.getEndereco());
			pstmt.setInt(5, usuario.getNumero());
			pstmt.setString(6, usuario.getComplemento());
			pstmt.setInt(7, usuario.getIdGrupo());
			pstmt.setString(8, usuario.getCpf());

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
				// fecha a conexão
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
	 * Editar usuario
	 */
	@Override
	public boolean editar(int idUsuario, String nome, String login, String pwd, String endereco, int numero,
			String complemento, int idGrupo, String cpf) {
		Connection connection = null;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);

			PreparedStatement pstmt = connection.prepareStatement(
					"UPDATE USUARIO SET NOME, LOGIN, PWD, ENDERECO, NUMERO, COMPLEMENTO, IDGRUPO, CPF = ?, ?, ?, ?, ?, ?, 2, ? WHERE IDUSUARIO = ?");
			pstmt.setString(1, nome);
			pstmt.setString(2, login);
			pstmt.setString(3, pwd);
			pstmt.setString(4, endereco);
			pstmt.setInt(5, numero);
			pstmt.setString(6, complemento);
			pstmt.setInt(7, 2);
			pstmt.setString(8, cpf);
			pstmt.setInt(7, idUsuario);
			pstmt.executeUpdate();

			if (pstmt != null)
				pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
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

		return true;
	}

	/**
	 * Excluir usuario
	 */
	@Override
	public boolean excluir(int idUsuario) {
		Connection connection = null;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);

			PreparedStatement pstmt = connection.prepareStatement("DELETE FROM USUARIO WHERE IDUSUARIO = ?");
			pstmt.setInt(1, idUsuario);

			pstmt.executeUpdate(); // precisou colocar execute pq uso select
									// para achar novo id.

			if (pstmt != null)
				pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
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

		return true;
	}

	/**
	 * Pegar usuario do banco conforme id
	 */
	@Override
	public Usuario getUsuario(int pIdUsuario) {
		Connection connection = null;
		Usuario retUsuario = null;
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);

			// traz os registros ordenados por nome
			PreparedStatement pstmt = connection.prepareStatement(
					"SELECT IDUSUARIO, NOME, LOGIN, PWD, ENDERECO, NUMERO, COMPLEMENTO, IDGRUPO, CPF FROM USUARIO WHERE IDUSUARIO = ?");

			pstmt.setInt(1, pIdUsuario);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				// cria objeto autor para colocar no ArrayList de retorno
				retUsuario = new Usuario(rs.getInt("IDUSUARIO"), rs.getString("NOME"), rs.getString("LOGIN"),
						rs.getString("PWD"), rs.getString("ENDERECO"), rs.getInt("NUMERO"), rs.getString("COMPLEMENTO"),
						rs.getInt("IDGRUPO"), rs.getString("CPF"));
				
				System.out.println(rs.getString("NOME"));

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
		return retUsuario;
	}

	/**
	 * Listar usuarios
	 */
	@Override
	public List<Usuario> listAll() {
		Connection connection = null;

		ArrayList<Usuario> listagem = null;
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);

			// traz os registros ordenados por nome
			PreparedStatement pstmt = connection.prepareStatement(
					"SELECT IDUSUARIO,NOME, LOGIN, PWD, ENDERECO, NUMERO, COMPLEMENTO, IDGRUPO, CPF FROM USUARIO ORDER BY NOME");

			ResultSet rs = pstmt.executeQuery();
			listagem = new ArrayList<Usuario>();
			while (rs.next()) {
				// cria objeto autor para colocar no ArrayList de retorno
				Usuario usuario = new Usuario(rs.getInt("IDUSUARIO"), rs.getString("NOME"));
				usuario.setLogin(rs.getString("LOGIN"));
				usuario.setPwd(rs.getString("PWD"));
				usuario.setEndereco(rs.getString("ENDERECO"));
				usuario.setNumero(rs.getInt("NUMERO"));
				usuario.setComplemento(rs.getString("COMPLEMENTO"));
				usuario.setIdGrupo(rs.getInt("IDGRUPO"));
				usuario.setCpf(rs.getString("CPF"));
				listagem.add(usuario);
			}
			// fecha o prepareStatement
			if (pstmt != null)
				pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());

		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listagem;
	}

	/**
	 * Retorna o usuário informando login e senha
	 *
	 * @param login
	 * @param senha
	 * @return Retorna nulo se não encontrar usuário
	 */
	@Override
	public Usuario getUsuarioByLoginSenha(String login, String senha) {

		Connection connection = null;

		Usuario retUsuario = null;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);

			// traz os registros ordenados por nome
			PreparedStatement pstmt = connection.prepareStatement(
					"SELECT IDUSUARIO, NOME, LOGIN, PWD, ENDERECO, NUMERO, COMPLEMENTO, IDGRUPO, CPF FROM USUARIO WHERE LOGIN = ? AND PWD=?");

			pstmt.setString(1, login);
			pstmt.setString(2, senha);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				// cria objeto usuario para retornar
				retUsuario = new Usuario(rs.getInt("IDUSUARIO"), rs.getString("NOME"), rs.getString("LOGIN"),
						rs.getString("PWD"), rs.getString("ENDERECO"), rs.getInt("NUMERO"), rs.getString("COMPLEMENTO"),
						rs.getInt("IDGRUPO"), rs.getString("CPF"));

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
		return retUsuario;

	}

	public boolean salvar(Usuario usuario) {
		Connection connection = null;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);

			PreparedStatement pstmt = connection.prepareStatement(
					"UPDATE USUARIO SET NOME = ?, LOGIN = ?, PWD = ?, ENDERECO = ?, NUMERO = ?, COMPLEMENTO = ?, IDGRUPO = ?, CPF = ? WHERE IDUSUARIO = ?");
			pstmt.setString(1, usuario.getNome());
			pstmt.setString(2, usuario.getLogin());
			pstmt.setString(3, usuario.getPwd());
			pstmt.setString(4, usuario.getEndereco());
			pstmt.setInt(5, usuario.getNumero());
			pstmt.setString(6, usuario.getComplemento());
			pstmt.setInt(7, 2);
			pstmt.setString(8, usuario.getCpf());
			pstmt.setInt(9, usuario.getCodigo());
			pstmt.executeUpdate();

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

	public boolean loginExist(String login) {
		Connection connection = null;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);

			PreparedStatement pstmt = connection.prepareStatement("SELECT LOGIN FROM USUARIO WHERE LOGIN = ?");
			pstmt.setString(1, login);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return false;
			} else {
				return true;
			}

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

	}

}
