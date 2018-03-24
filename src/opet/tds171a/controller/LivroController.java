package opet.tds171a.controller;

import java.util.List;

import opet.tds171a.model.ILivroDAO;
import opet.tds171a.model.LivroModel;
import opet.tds171a.vo.Livro;

public class LivroController implements ILivroDAO
{
    private LivroModel livroModel;

    /**
     * Construtor instanciando a Model do livro
     */
    public LivroController()
    {
        livroModel = new LivroModel();
    }

    /**
     * Incluir livro no banco
     */
    @Override
    public boolean incluir(Livro pLivro)
    {
        // TODO Auto-generated method stub
        return livroModel.incluir(pLivro);
    }

    /**
     * Editar livro no banco
     */
    @Override
    public boolean editar(int pIdLivro, String pTitulo, String pResumo, int pIdAutor, String pAtivo, int pIdEditora, int pIdCategoria)
    {
        // TODO Auto-generated method stub
        return livroModel.editar(pIdLivro, pTitulo, pResumo, pIdAutor, pAtivo, pIdEditora, pIdCategoria);
    }

    /**
     * Excluir livro do banco
     */
    @Override
    public boolean excluir(int pIdLivro)
    {
        // TODO Auto-generated method stub
        return livroModel.excluir(pIdLivro);
    }
    
    /**
     * Salvar livro
     * @param livro
     * @return
     */
    public boolean salvar(Livro livro) {
		return livroModel.salvar(livro);
	}

    /**
     * Métodos Getters & Setters dos atributos da classe Livro
     */
    
    @Override
    public Livro getLivro(int pIdLivro)
    {
        // TODO Auto-generated method stub
        return livroModel.getLivro(pIdLivro);
    }

    @Override
    public List<Livro> listAll()
    {
        // TODO Auto-generated method stub
        return livroModel.listAll();
    }

    public LivroModel getLivroModel()
    {
        return livroModel;
    }

    public void setLivroModel(LivroModel pLivroModel)
    {
        livroModel = pLivroModel;
    }

}
