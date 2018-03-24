package opet.tds171a.model;

import java.util.List;

import opet.tds171a.vo.Editora;

public interface IEditoraDAO {
	
/**
 * Insere uma nova editora ao banco
 * @param editora
 * @return
 */
	public boolean incluir(Editora editora);
	
	
	/**
	 * Edita uma editora
	 * @param idEditora
	 * @param nome
	 * @return
	 */
	public boolean editar(int idEditora, String nome);
	
	/**
	 * Exclui uma editora existente
	 * @param idEditora
	 * @return
	 */
	public boolean excluir(int idEditora);
	
	/**
	 * Pega uma editora no banco de dados
	 * @param idEditora
	 * @return
	 */
	public Editora getEditora(int idEditora);
	
	/**
	 * Retorna a listagem de editoras no banco
	 * @return
	 */
	public List<Editora> listAll();
}
