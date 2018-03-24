package opet.tds171a.model;

import java.util.ArrayList;


import opet.tds171a.vo.Autor;

/**
 * Model representando o autor
 * @author Tiago Oliveira e Henrique de Paula
 *
 */
public class AutorModel implements IAutorDAO {
	
	private AutorDAO autorDAO;
	
	public AutorModel() {
		autorDAO = new AutorDAO();
	}

	/**
	 * Inclui autor no banco de dados
	 */
	@Override
	public boolean incluir(Autor autor) { 
		return autorDAO.incluir(autor);
	}

	/**
	 * Lista todos os autores cadastrados no banco 
	 */
	@Override
	public ArrayList<Autor> listAll() {
		return autorDAO.listAll();
		
	}

	public AutorDAO getAutorDAO() {
		return autorDAO;
	}

	public void setAutorDAO(AutorDAO autorDAO) {
		this.autorDAO = autorDAO;
	}

	/**
	 * edita o nome do autor no banco de dados
	 */
	@Override
	public boolean editar(int idAutor, String nome) {
		return autorDAO.editar(idAutor, nome);
	}

	/**
	 * Exclui autor do banco
	 */
	@Override
	public boolean excluir(int idAutor) {
		return autorDAO.excluir(idAutor);
	}
	
	public Autor getAutor(int idAutor) {
		return autorDAO.getAutor(idAutor);
	}
	
	
	public boolean salvar(Autor autor) { 
		return autorDAO.salvar(autor);
	}
	
	
	

}
