package opet.tds171a.controller;

import java.util.List;

import opet.tds171a.model.IUsuarioDAO;
import opet.tds171a.model.UsuarioModel;
import opet.tds171a.vo.Usuario;

public class UsuarioController implements IUsuarioDAO
{
    private UsuarioModel usuarioModel;

    /**
     * Construtor instanciando a Model do Usuario
     */
    public UsuarioController()
    {
        usuarioModel = new UsuarioModel();
    }

    public boolean usuarioCadastro(Usuario usuario) {
        return usuarioModel.usuarioCadastro(usuario);
    }

    /**
     * Incluir usuario no banco
     */
    @Override
    public boolean incluir(Usuario Usuario)
    {
        // TODO Auto-generated method stub
        return usuarioModel.incluir(Usuario);
    }

    /**
     * Editar usuario do banco
     */
    @Override
    public boolean editar(int idUsuario, String nome, String login, String pwd, String endereco, int numero, String complemento, int idGrupo,
                    String cpf)
    {
        // TODO Auto-generated method stub
        return usuarioModel.editar(idUsuario, nome, login, pwd, endereco, numero, complemento, idGrupo, cpf);
    }

    /**
     * Excluir usuario do banco
     */
    @Override
    public boolean excluir(int pIdUsuario)
    {
        // TODO Auto-generated method stub
        return usuarioModel.excluir(pIdUsuario);
    }

    /**
     * Pegar usuario do banco
     */
    @Override
    public Usuario getUsuario(int pIdUsuario)
    {
        // TODO Auto-generated method stub
        return usuarioModel.getUsuario(pIdUsuario);
    }

    /**
     * Listar usuarios
     */
    @Override
    public List<Usuario> listAll()
    {
        // TODO Auto-generated method stub
        return usuarioModel.listAll();
    }

	@Override
	public Usuario getUsuarioByLoginSenha(String login, String senha) {
		// TODO Auto-generated method stub
		return usuarioModel.getUsuarioByLoginSenha(login, senha);
	}
	
	public boolean salvar(Usuario usuario){
		return usuarioModel.salvar(usuario);
	}

	public boolean loginExist(String login){
		return usuarioModel.loginExist(login);
	}
}
