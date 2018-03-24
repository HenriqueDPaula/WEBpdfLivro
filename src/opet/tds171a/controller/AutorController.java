package opet.tds171a.controller;

import java.util.ArrayList;


import opet.tds171a.model.AutorModel;
import opet.tds171a.vo.Autor;

/**
 * Controller para resolver interações entre a view e a model de Autor
 * @author Tiago Oliveira e Henrique de Paula
 *
 */
public class AutorController {
	
	private AutorModel autorModel;
	
	/**
	 * Construtor da controller
	 */
	public AutorController() {
		// instancia a model de Autor
		autorModel = new AutorModel();
	}

	/**
	 * Grava o autor no banco de dados
	 * @param autor
	 * @return boolean
	 */
	public boolean incluir(Autor autor) {
		
		return autorModel.incluir(autor);
	}
	
	/**
	 * Lista todos os autores cadastrados no banco
	 * @return ArrayList
	 */
	public ArrayList<Autor> listarAll() {
		return autorModel.listAll();
	}
	
	/**
	 * Exclui o autor do banco de dados
	 * @param idAutor
	 * @return
	 */
	public boolean excluir(int idAutor) {
		return autorModel.excluir(idAutor);
	}
	
	/**
	 * Edita o autor no banco de dados
	 * @param idAutor
	 * @param nome
	 * @return
	 */
	public boolean editar(int idAutor, String nome) {
		return autorModel.editar(idAutor, nome);
	}
	
	/**
	 * salva autor
	 * @param autor
	 * @return
	 */
	public boolean salvar(Autor autor) {
		return autorModel.salvar(autor);
	}
	
	public Autor getAutor(int idAutor) {
		return autorModel.getAutor(idAutor);
	}

}
