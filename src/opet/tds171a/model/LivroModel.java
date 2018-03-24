package opet.tds171a.model;

import java.util.List;

import opet.tds171a.vo.Livro;

public class LivroModel implements ILivroDAO
{
    private LivroDAO livroDAO;

    /**
     * Construtor instanciando a classe DAO  do livro
     */
    public LivroModel() {
        livroDAO = new LivroDAO();
    }

    /**
     * Incluindo livro no banco
     */
    @Override
    public boolean incluir(Livro pLivro)
    {
        // TODO Auto-generated method stub
        return livroDAO.incluir(pLivro);
    }

    /**
     * Editar livro do banco
     */
    @Override
    public boolean editar(int pIdLivro, String pTitulo, String pResumo, int pIdAutor, String pAtivo, int pIdEditora, int pIdCategoria)
    {
        // TODO Auto-generated method stub
        return livroDAO.editar(pIdLivro, pTitulo, pResumo, pIdAutor, pAtivo, pIdEditora, pIdCategoria);
    }
    
/**
 * Editar livro do banco
 */
    @Override
    public boolean excluir(int pIdLivro)
    {
        // TODO Auto-generated method stub
        return livroDAO.excluir(pIdLivro);
    }

    /**
     * Salvar livro
     * @param livro
     * @return
     */
    public boolean salvar(Livro livro) { 
		return livroDAO.salvar(livro);
	}
    
    /**
     * Pegar livro do banco de acordo com seu id
     */
    @Override
    public Livro getLivro(int pIdLivro)
    {
        // TODO Auto-generated method stub
        return livroDAO.getLivro(pIdLivro);
    }

    /**
     * Listar todos os livros presentes no banco
     */
    @Override
    public List<Livro> listAll()
    {
        // TODO Auto-generated method stub
        return livroDAO.listAll();
    }

    public LivroDAO getLivroDAO()
    {
        return livroDAO;
    }

    public void setLivroDAO(LivroDAO pLivroDAO)
    {
        livroDAO = pLivroDAO;
    }



}
