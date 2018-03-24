package opet.tds171a.model;

import java.util.List;

import opet.tds171a.vo.Usuario;

public class UsuarioModel implements IUsuarioDAO {

	private UsuarioDAO usuarioDAO;

	/**
	 * Construtor instanciando a classe DAO do usuario
	 */
	public UsuarioModel() {

		usuarioDAO = new UsuarioDAO();
	}

	/**
	 * Cadastro usuario
	 * 
	 * @param usuario
	 * @return
	 */
	public boolean usuarioCadastro(Usuario usuario) {
		return usuarioDAO.usuarioCadastro(usuario);
	}

	/**
	 * Incluir usuario
	 */
	@Override
	public boolean incluir(Usuario usuario) {
		// TODO Auto-generated method stub
		return usuarioDAO.incluir(usuario);
	}

	/**
	 * Editar usuario
	 */
	@Override
	public boolean editar(int idUsuario, String nome, String login, String pwd, String endereco, int numero,
			String complemento, int idGrupo, String cpf) {
		// TODO Auto-generated method stub
		return usuarioDAO.editar(idUsuario, nome, login, pwd, endereco, numero, complemento, idGrupo, cpf);
	}

	/**
	 * Excluir usuario
	 */
	@Override
	public boolean excluir(int pIdUsuario) {
		// TODO Auto-generated method stub
		return usuarioDAO.excluir(pIdUsuario);
	}

	/**
	 * Pegar usuario conforme id
	 */
	@Override
	public Usuario getUsuario(int pIdUsuario) {
		// TODO Auto-generated method stub
		return usuarioDAO.getUsuario(pIdUsuario);
	}

	/**
	 * Listar usuarios
	 */
	@Override
	public List<Usuario> listAll() {
		// TODO Auto-generated method stub
		return usuarioDAO.listAll();
	}

	@Override
	public Usuario getUsuarioByLoginSenha(String login, String senha) {
		// TODO Auto-generated method stub
		return usuarioDAO.getUsuarioByLoginSenha(login, senha);
	}

	public boolean salvar(Usuario usuario) {
		return usuarioDAO.salvar(usuario);
	}
	public boolean loginExist(String login){
		return usuarioDAO.loginExist(login);
	}

}
