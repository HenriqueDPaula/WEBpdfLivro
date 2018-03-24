package opet.tds171a.model;

import java.util.List;

import opet.tds171a.vo.Categoria;

public interface ICategoriaDAO
{

    /**
     * Insere uma categoria
     *
     * @param categoria
     * @return
     */
    public boolean incluir(Categoria categoria);

    /**
     * Edita uma categoria
     *
     * @param idCategoria
     * @param nome
     * @return
     */
    public boolean editar(int idCategoria, String nome);

    /**
     * Exclui uma categoria existente
     *
     * @param idCategoria
     * @return
     */
    public boolean excluir(int idCategoria);

    /**
     * Pega uma categoria no banco de dados
     *
     * @param idCategoria
     * @return
     */
    public Categoria getCategoria(int idCategoria);

    /**
     * Retorna a listagem de categoria no banco
     *
     * @return
     */
    public List<Categoria> listAll();
}
