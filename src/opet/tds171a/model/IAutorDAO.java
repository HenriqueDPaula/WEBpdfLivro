package opet.tds171a.model;


import java.util.List;

import opet.tds171a.vo.Autor;

/**
 * Implementa interface de autor
 * @author Tiago Oliveira e Henrique de Paula
 *
 */
public interface IAutorDAO {

	/**
	 * Insere um novo autor
	 * @param autor
	 * @return
	 */
	public boolean incluir(Autor autor);
	
	/**
	 * edita um autor existente
	 * @param idAutor
	 * @param nome
	 * @return
	 */
	public boolean editar(int idAutor, String nome);
	/**
	 * exclui um autor
	 * @param idAutor
	 * @return
	 */
	public boolean excluir(int idAutor);
	/**
	 * pega um autor no banco de dados
	 * @param idAutor
	 * @return
	 */
	public Autor getAutor(int idAutor);
	
	/**
	 * Retorna a listagem de autores do banco 
	 * @return
	 */
	public List<Autor> listAll();
	
	
}
