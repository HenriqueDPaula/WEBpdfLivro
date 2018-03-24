package opet.tds171a.model;

import java.util.List;

import opet.tds171a.vo.Livro;

public interface ILivroDAO
{
    /**
     * Insere um livro
     *
     * @param livro
     * @return
     */
    public boolean incluir(Livro livro);

    /**
     * Edita um livro
     *
     * @param idLivro
     * @param titulo
     * @param resumo
     * @param idAutor
     * @param ativo
     * @param idEditora
     * @param idCategoria
     * @return
     */
    public boolean editar(int idLivro, String titulo, String resumo, int idAutor, String ativo, int idEditora, int idCategoria);

    /**
     * Exclui um livro existente
     *
     * @param idLivro
     * @return
     */
    public boolean excluir(int idLivro);

    /**
     * Pega um livro no banco de dados
     *
     * @param idLivro
     * @return
     */
    public Livro getLivro(int idLivro);

    /**
     * Retorna a listagem de Livros no banco
     *
     * @return
     */
    public List<Livro> listAll();

}
