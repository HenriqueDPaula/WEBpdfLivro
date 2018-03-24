package opet.tds171a.model;

import java.util.List;

import opet.tds171a.vo.Editora;

/**
 * Model representando a Editora
 * @author Tiago Oliveira e Henrique
 *
 */
public class EditoraModel implements IEditoraDAO {

	private EditoraDAO EditoraDAO;

	/**
	 * Construtor da da model da editora, instanciando um novo objeto EditoraDAO
	 */
	public EditoraModel() {
		EditoraDAO = new EditoraDAO();
	}

	public EditoraDAO getEditoraDAO() {
		return EditoraDAO;
	}

	public void setEditoraDAO(EditoraDAO editoraDAO) {
		EditoraDAO = editoraDAO;
	}

	/**
	 * Insere uma nova editora ao banco
	 */
	@Override
	public boolean incluir(Editora editora) {
		return EditoraDAO.incluir(editora);
	}

	/**
	 * Edita uma editora exixtente no banco
	 */
	@Override
	public boolean editar(int idEditora, String nome) {
		return EditoraDAO.editar(idEditora, nome);
	}

	/**
	 * Exclui uma editora do banco
	 */
	@Override
	public boolean excluir(int idEditora) {
		return EditoraDAO.excluir(idEditora);
	}

	public boolean salvar(Editora editora) { 
		return EditoraDAO.salvar(editora);
	}
	
	/**
	 * Pegando uma editora do banco
	 */
	@Override
	public Editora getEditora(int idEditora) {
		// TODO Auto-generated method stub
		return EditoraDAO.getEditora(idEditora);
	}

	/**
	 * Lista todas as editoras cadastradas no banco
	 */
	@Override
	public List<Editora> listAll() {
		return EditoraDAO.listAll();
	}

}
