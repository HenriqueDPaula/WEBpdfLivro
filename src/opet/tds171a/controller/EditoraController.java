package opet.tds171a.controller;

import java.util.ArrayList;

import opet.tds171a.model.EditoraModel;
import opet.tds171a.vo.Editora;

public class EditoraController {
	private EditoraModel editoraModel;

	/**
	 * Construtor instanciando a model da editora
	 */
	public EditoraController() {
		editoraModel = new EditoraModel();
	}

	public EditoraModel getEditoraModel() {
		return editoraModel;
	}

	public void setEditoraModel(EditoraModel editoraModel) {
		this.editoraModel = editoraModel;
	}

	/**
	 * Grava a editora no banco de dados
	 *
	 * @param editora
	 * @return boolean
	 */
	public boolean incluir(Editora editora) {

		return editoraModel.incluir(editora);
	}

	/**
	 * Lista todas as editoras cadastrados no banco
	 *
	 * @return ArrayList
	 */
	public ArrayList<Editora> listarAll() {
		return (ArrayList<Editora>) editoraModel.listAll();
	}

	/**
	 * Exclui a editora do banco de dados
	 *
	 * @param idEditora
	 * @return
	 */
	public boolean excluir(int idEditora) {
		return editoraModel.excluir(idEditora);
	}

	/**
	 * Edita a editora no banco de dados
	 *
	 * @param idEditora
	 * @param nome
	 * @return
	 */
	public boolean editar(int idEditora, String nome) {
		return editoraModel.editar(idEditora, nome);
	}
	
	/**
	 * Salvar editora 
	 * @param editora
	 * @return
	 */
	public boolean salvar(Editora editora) {
		return editoraModel.salvar(editora);
	}
	
	
	/**
	 *  Retorna o objeto Editora, filtrando pelo código passado como parâmetro
	 * @param codigoEditora
	 * @return
	 */
	public Editora getEditora(int codigoEditora) {
		return editoraModel.getEditora(codigoEditora);
	}

}
