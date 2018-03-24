package opet.tds171a.vo;

public class Livro
{
    private int    idLivro;
    private String titulo;
    private String resumo;
    private int    idAutor;
    private String ativo;
    private int    idEditora;
    private int    idCategoria;

    
    public Livro()
	{
	
	}
    
    public Livro(int idLivro, String titulo, String resumo,
                    int idAutor, String ativo, int idEditora, int idCategoria)
    {

        this.idLivro = idLivro;
        this.titulo = titulo;
        this.resumo = resumo;
        this.idAutor = idAutor;
        this.ativo = ativo;
        this.idEditora = idEditora;
        this.idCategoria = idCategoria;
    }

    public Livro(String titulo, String resumo,
                    int idAutor, String ativo, int idEditora, int idCategoria)
    {


        this.titulo = titulo;
        this.resumo = resumo;
        this.idAutor = idAutor;
        this.ativo = ativo;
        this.idEditora = idEditora;
        this.idCategoria = idCategoria;
    }



    public Livro(int idLivro)
    {
        this.idLivro = idLivro;
    }

    public int getIdLivro()
    {
        return idLivro;
    }

    public void setIdLivro(int pIdLivro)
    {
        idLivro = pIdLivro;
    }

    public String getTitulo()
    {
        return titulo;
    }

    public void setTitulo(String pTitulo)
    {
        titulo = pTitulo;
    }

    public String getResumo()
    {
        return resumo;
    }

    public void setResumo(String pResumo)
    {
        resumo = pResumo;
    }

    public int getIdAutor()
    {
        return idAutor;
    }

    public void setIdAutor(int pIdAutor)
    {
        idAutor = pIdAutor;
    }

    public String getAtivo()
    {
        return ativo;
    }

    public void setAtivo(String pAtivo)
    {
        ativo = pAtivo;
    }

    public int getIdEditora()
    {
        return idEditora;
    }

    public void setIdEditora(int pIdEditora)
    {
        idEditora = pIdEditora;
    }

    public int getIdCategoria()
    {
        return idCategoria;
    }

    public void setIdCategoria(int pIdCategoria)
    {
        idCategoria = pIdCategoria;
    }

}
