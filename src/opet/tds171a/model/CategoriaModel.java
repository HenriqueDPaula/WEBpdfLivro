package opet.tds171a.model;

import java.util.List;

import opet.tds171a.vo.Categoria;

/**
 * model representando a Categoria
 *
 * @author Tiago Oliveira e Henrique de Paula
 *
 */
public class CategoriaModel implements ICategoriaDAO
{
    private CategoriaDAO categoriaDAO;

    /**
     * Construtor da CategoriaModel instanciando um objeto da CategoriaDAO
     */
    public CategoriaModel()
    {
        categoriaDAO = new CategoriaDAO();
    }

    public CategoriaDAO getCategoriaDAO()
    {
        return categoriaDAO;
    }

    public void setCategoriaDAO(CategoriaDAO categoriaDAO)
    {
        this.categoriaDAO = categoriaDAO;
    }

    /**
     * Insere uma categoria no banco
     */
    @Override
    public boolean incluir(Categoria categoria)
    {

        return categoriaDAO.incluir(categoria);
    }

    /**
     * Edita uma categoria do banco
     */
    @Override
    public boolean editar(int idCategoria, String nome)
    {

        return categoriaDAO.editar(idCategoria, nome);
    }

    /**
     * Exclui uma categoria do banco
     */
    @Override
    public boolean excluir(int idCategoria)
    {

        return categoriaDAO.excluir(idCategoria);

    }

    /**
     * Pega uma categoria do banco conforme id
     */
    @Override
    public Categoria getCategoria(int idCategoria)
    {

        return categoriaDAO.getCategoria(idCategoria);
    }

    /**
     * Lista todas as categorias do banco
     */
    @Override
    public List<Categoria> listAll()
    {

        return categoriaDAO.listAll();
    }

    public boolean salvar(Categoria categoria)
    {
        return categoriaDAO.salvar(categoria);
    }
    
    

}
