package opet.tds171a.controller;

import java.util.ArrayList;

import opet.tds171a.model.CategoriaModel;
import opet.tds171a.vo.Categoria;

/**
 * Controller representando a Categoria
 *
 * @author Tiago Oliveira e Henrique de paula
 *
 */
public class CategoriaController
{

    private CategoriaModel categoriaModel;

    /**
     * Construtor instanciando a model da categoria
     */
    public CategoriaController()
    {
        categoriaModel = new CategoriaModel();
    }

    public CategoriaModel getCategoriaModel()
    {
        return categoriaModel;
    }

    public void setCategoriaModel(CategoriaModel categoriaModel)
    {
        this.categoriaModel = categoriaModel;
    }

    /**
     * Grava a categoria no banco de dados
     *
     * @param categoria
     * @return boolean
     */
    public boolean incluir(Categoria categoria)
    {

        return categoriaModel.incluir(categoria);
    }

    /**
     * Lista todas as categorias cadastrados no banco
     *
     * @return ArrayList
     */
    public ArrayList<Categoria> listarAll()
    {
        return (ArrayList<Categoria>) categoriaModel.listAll();
    }

    /**
     * Exclui a categoria do banco de dados
     *
     * @param idCategoria
     * @return
     */
    public boolean excluir(int idCategoria)
    {
        return categoriaModel.excluir(idCategoria);
    }

    /**
     * Edita a categoria no banco de dados
     *
     * @param idCategoria
     * @param nome
     * @return
     */
    public boolean editar(int idCategoria, String nome)
    {
        return categoriaModel.editar(idCategoria, nome);
    }

    /**
     * Pega categoria no banco pelo id
     * @param codigoCategoria
     * @return
     */
    public Categoria getCategoria(int codigoCategoria)
    {
        // TODO Auto-generated method stub
        return categoriaModel.getCategoria(codigoCategoria);
    }

    /**
     * salva categoria no banco
     * @param categoria
     * @return
     */
    public boolean salvar(Categoria categoria)
    {
        return categoriaModel.salvar(categoria);
    }

}
