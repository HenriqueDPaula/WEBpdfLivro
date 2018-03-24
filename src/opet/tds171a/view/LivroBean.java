package opet.tds171a.view;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import opet.tds171a.controller.LivroController;
import opet.tds171a.vo.Autor;
import opet.tds171a.vo.Categoria;
import opet.tds171a.vo.Editora;
import opet.tds171a.vo.Livro;

@ManagedBean(name = "livroBean")
@SessionScoped
// @SessionScoped

public class LivroBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private LivroController livroController;

	private int idlivro;

	private String titulo;

	private String resumo;

	private Autor autor;

	private Boolean ativo;

	private Editora editora;

	private int editoraId;

	private int autorId;

	private int categoriaId;

	private Categoria categoria;

	private List<Livro> listaLivros;

	private Part arquivo;

	private String fileContent;

	/**
	 * Construtor
	 */
	public LivroBean() {
		// TODO Auto-generated constructor stub
		setIdlivro(-1);
		setTitulo("");

		// setListaAutores(null);

		listaLivros = null;
		livroController = new LivroController();

	}

	@PostConstruct
	public void init() {
		listaLivros = livroController.listAll();
	}

	/**
	 * Método para setar o IdLivro para -1, e assim inserir no banco, e não
	 * realizar o update. Após setar
	 * 
	 * @return Retorna a view de cadastro
	 */
	public String inserirLivro() {
		setIdlivro(-1);

		setTitulo("");
		setResumo("");
		setEditoraId(-1);
		setAutorId(-1);
		setCategoriaId(-1);
		return "cadastroLivro";
	}

	/**
	 * Método para inserir um novo livro no banco. Foi substituída pelo método
	 * salvar, que já verifica se é uma inserção ou edição
	 * 
	 * @deprecated
	 * @return
	 */
	public String incluir() {

		Livro livro = new Livro();

		livro.setTitulo(titulo);
		livro.setResumo(resumo);
		livro.setIdEditora(editoraId);
		livro.setIdAutor(autorId);
		livro.setIdCategoria(categoriaId);

		FacesContext contexto = FacesContext.getCurrentInstance();

		if (livroController.incluir(livro)) {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Inclusão OK!", null));
			return "admin/livro/listagemLivros";

		} else {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas na inclusão!", null));
		}

		// da refresh na listagem
		listaLivros = livroController.listAll();

		return "admin/livro/listagemLivros";

	}

	/**
	 * Método para importar o arquivo PDF
	 * 
	 * @throws IOException
	 */
	public void importa() throws IOException {
		FacesContext contexto = FacesContext.getCurrentInstance();
		try {
			// pega o caminho da aplicação
			String caminho_aplicacao = new File(".").getCanonicalPath();
			// cria um nome de diretório sobre o caminho da aplicação
			String pasta_upload_livros = caminho_aplicacao + "\\upload_livros";
			// se pasta de upload não existe, entao cria
			File pastaUploads = new File(pasta_upload_livros);
			if (!pastaUploads.exists())
				pastaUploads.mkdir();

			InputStream conteudo = arquivo.getInputStream();

			File arquivoDestino = new File(pasta_upload_livros, idlivro+".PDF");
			// se arquivo já existe, então exclui antes de copiar
			if (arquivoDestino.exists())
				arquivoDestino.delete();
			Files.copy(conteudo, new File(pasta_upload_livros, idlivro+".PDF").toPath());
			conteudo.close();

			System.out.println(arquivoDestino.toPath());
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Arquivo PDF salvo com sucesso!", null));
			
		} catch (Exception e) {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas ao realizar o upload do arquivo PDF!", null));
		}

		//return "livro/listagemLivros";
	}

	/**
	 * Listar livro
	 * 
	 * @return
	 */
	public String listar() {
		listaLivros = livroController.listAll();

		return "livro/listagemLivros";
	}

	/**
	 * Excluir Livro
	 * 
	 * @param codigoCategoria
	 * @return
	 */
	public String excluir(int codigoCategoria) {
		FacesContext contexto = FacesContext.getCurrentInstance();
		if (livroController.excluir(codigoCategoria)) {
			contexto.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Livro excluído com sucesso!", null));
			// refaz a listagem pra não constar mais o excluido
			listaLivros = livroController.listAll();
		} else {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Problemas ao excluir livro. Contate o suporte técnico.", null));
		}

		return "listagemCategorias";
	}

	/**
	 * Editar livro
	 * 
	 * @param codigoLivro
	 * @return
	 */
	public String editar(int codigoLivro) {
		setIdlivro(codigoLivro);

		Livro livro = livroController.getLivro(codigoLivro);
		setAutorId(livro.getIdAutor());
		setTitulo(livro.getTitulo());
		setCategoriaId(livro.getIdCategoria());
		setResumo(livro.getResumo());
		setEditoraId(livro.getIdEditora());
		// setNomeCategoria(categoria.getNome());

		return "cadastroLivro";
	}

	/**
	 * Retorna o formulario já com as informações do livro, para escolha do
	 * arquivo para upload
	 * 
	 * @param codigoLivro
	 * @return
	 */
	public String adicionarPDF(int codigoLivro) {
		setIdlivro(codigoLivro);

		Livro livro = livroController.getLivro(codigoLivro);
		setAutorId(livro.getIdAutor());
		setTitulo(livro.getTitulo());
		setCategoriaId(livro.getIdCategoria());
		setResumo(livro.getResumo());
		setEditoraId(livro.getIdEditora());
		// setNomeCategoria(categoria.getNome());
		return "cadastroLivroPdf";
	}

	public String salvar() {

		Livro livro = new Livro();

		livro.setIdLivro(idlivro);
		livro.setTitulo(titulo);
		livro.setResumo(resumo);
		livro.setIdEditora(editoraId);
		livro.setIdAutor(autorId);
		livro.setIdCategoria(categoriaId);

		FacesContext contexto = FacesContext.getCurrentInstance();

		if (livroController.salvar(livro)) {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Livro salvo com sucesso!", null));

			// da refresh na listagem
			listaLivros = livroController.listAll();

			return "listagemLivros";

		} else {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas na inclusão!", null));
			System.out.println("Problemas ao salvar registro.");
		}

		return "";
	}

	public void download(int idLivro) throws IOException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

		// pega o caminho da aplicação
		String caminho_aplicacao = new File(".").getCanonicalPath();
		// cria um nome de diretório sobre o caminho da aplicação
		String pasta_upload_livros = caminho_aplicacao + "\\upload_livros";
		// pega o arquivo PDF
		File f = new File(pasta_upload_livros + "\\"+idLivro+".PDF");

		response.reset(); // Algum filtro pode ter configurado alguns cabeçalhos
							// no buffer de antemão. Queremos livrar-se deles,
							// senão ele pode colidir.
		response.setHeader("Content-Type", "application/pdf"); // Define apenas
																// o tipo de
																// conteúdo,
																// Utilize se
																// necessário
																// ServletContext#getMimeType()
																// para detecção
																// automática
																// com base em
																// nome de
																// arquivo.
		response.setHeader("Content-disposition", "attachment; filename=" + f.getName());
		FileInputStream input = new FileInputStream(f);
		InputStream in = new FileInputStream(f);
		BufferedInputStream bin = new BufferedInputStream(in);
		DataInputStream din = new DataInputStream(input);
		while (input.available() > 0) {
			response.getOutputStream().write(input.read());
		}
		in.close();
		bin.close();
		din.close();
		response.getOutputStream().flush();
		// OutputStream responseOutputStream = response.getOutputStream();

		// Lê o conteúdo do PDF
		// URL url = new URL(pasta_upload_livros+"\\1.PDF");
		/*
		 * URL url = new URL("upload_livros/1.PDF"); InputStream pdfInputStream
		 * = url.openStream();
		 * 
		 * // Lê o conteúdo do PDF e grava para saída byte[] bytesBuffer = new
		 * byte[2048]; int bytesRead; while ((bytesRead =
		 * pdfInputStream.read(bytesBuffer)) > 0) {
		 * responseOutputStream.write(bytesBuffer, 0, bytesRead); }
		 * responseOutputStream.flush();
		 * 
		 * // Fecha os streams pdfInputStream.close();
		 * responseOutputStream.close(); facesContext.responseComplete();
		 */
	}

	public LivroController getLivroController() {
		return livroController;
	}

	public void setLivroController(LivroController livroController) {
		this.livroController = livroController;
	}

	public int getIdlivro() {
		return idlivro;
	}

	public void setIdlivro(int idlivro) {
		this.idlivro = idlivro;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Livro> getListaLivros() {
		return listaLivros;
	}

	public void setListaLivros(List<Livro> listaLivros) {
		this.listaLivros = listaLivros;
	}

	public String getFileContent() {
		return fileContent;
	}

	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}

	public Part getArquivo() {
		return arquivo;
	}

	public void setArquivo(Part arquivo) {
		this.arquivo = arquivo;
	}

	public int getEditoraId() {
		return editoraId;
	}

	public void setEditoraId(int editoraID) {
		this.editoraId = editoraID;
	}

	public int getAutorId() {
		return autorId;
	}

	public void setAutorId(int autorId) {
		this.autorId = autorId;
	}

	public int getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(int categoriaId) {
		this.categoriaId = categoriaId;
	}

}
