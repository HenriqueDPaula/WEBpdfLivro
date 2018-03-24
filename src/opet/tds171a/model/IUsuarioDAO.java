package opet.tds171a.model;

import java.util.List;

import opet.tds171a.vo.Usuario;

public interface IUsuarioDAO
{

    /**
     * Insere um novo usuario
     *
     * @param usuario
     * @return
     */
    public boolean incluir(Usuario usuario);

    /**
     * edita um usuario existente
     */
    public boolean editar(int idUsuario, String nome,
                    String login, String pwd,
                    String endereco, int numero,
                    String complemento,
                    int idGrupo, String cpf);

    /**
     * exclui um usuario
     *
     * @param idusuario
     * @return
     */
    public boolean excluir(int idUsuario);

    /**
     * pega um usuario no banco de dados
     *
     * @param idusuario
     * @return
     */
    public Usuario getUsuario(int idUsuario);

    /**
     * Retorna a listagem de usuarios do banco
     *
     * @return
     */
    public List<Usuario> listAll();

	Usuario getUsuarioByLoginSenha(String login, String senha);

}
