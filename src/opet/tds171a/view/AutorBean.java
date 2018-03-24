package opet.tds171a.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import opet.tds171a.controller.AutorController;
import opet.tds171a.vo.Autor;

@Named("autorBean")
@SessionScoped
public class AutorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AutorController autorController;

	private int idAutor;

	private String nomeAutor;

	private List<Autor> listaAutores;

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;

	/**
	 * Setando valores aos atributos
	 */
	public AutorBean() {
		// TODO Auto-generated constructor stub
		setIdAutor(1);
		setNomeAutor("");
		// setListaAutores(null);

		listaAutores = null;
		autorController = new AutorController();
		// loginUsuarioBean = loginBean;

	}

	/**
	 * Alimenta a lista após instanciar o objeto, para que seja possível
	 * utilizar a listagem nos select dos formularios
	 */
	@PostConstruct
	public void init() {
		listaAutores = autorController.listarAll();
	}

	/**

	 * Método para setar o IdLivro para -1, e assim inserir no banco, e não
	 * realizar o update. Após setar
	 * 
	 * @return Retorna a view de cadastro
	 */
	public String prepararInserir() {
		setIdAutor(-1);

		setNomeAutor("");

		return "cadastroAutor";
	}


	 /* Inclusao de autor chamando o autorController
	 * @return
	 */

	public String incluir() {
		Autor autor = new Autor(nomeAutor);
		FacesContext contexto = FacesContext.getCurrentInstance();

		if (autorController.incluir(autor)) {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Inclusão OK!", null));
			System.out.println("Autor inserido com sucesso.");
		} else {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas na inclusão!", null));
			System.out.println("Problemas ao inserir autor.");
		}

		// da refresh na listagem
		listaAutores = autorController.listarAll();

		return "listagemAutores";

	}

	/**
	 * Listagem de autores
	 * @return
	 */
	public String listar() {
		listaAutores = autorController.listarAll();

		return "/admin/autor/listagemAutores";
	}

	/**
	 * M�todos Getters & Setters
	 * @return
	 */
	public int getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(int idAutor) {
		this.idAutor = idAutor;
	}

	public String getNomeAutor() {
		return nomeAutor;
	}

	public void setNomeAutor(String nomeAutor) {
		this.nomeAutor = nomeAutor;
	}

	public List<Autor> getListaAutores() {
		return listaAutores;
	}

	public void setListaAutores(List<Autor> listaAutores) {
		this.listaAutores = listaAutores;
	}

	/**
	 * Exclusao de autor pelo id
	 * @param codigoAutor
	 * @return
	 */
	public String excluir(int codigoAutor) {
		FacesContext contexto = FacesContext.getCurrentInstance();
		if (autorController.excluir(codigoAutor)) {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Autor excluido com sucesso!", null));
			// refaz a listagem pra não constar mais o excluido
			listaAutores = autorController.listarAll();
		} else {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas ao excluir autor. Possivelmente já está sendo utilizado em algum livro.", null));
		}

		return "listagemAutores";
	}

	public String editar(int codigoAutor) {
		setIdAutor(codigoAutor);

		Autor autor = autorController.getAutor(codigoAutor);
		setNomeAutor(autor.getNome());
		return "cadastroAutor";
	}

	/**
	 * Salva o registro
	 * @return
	 */
	public String salvar() {

		Autor autor = new Autor(idAutor, nomeAutor);
		FacesContext contexto = FacesContext.getCurrentInstance();

		if (autorController.salvar(autor)) {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Autor salvo com sucesso!", null));

			// da refresh na listagem
			listaAutores = autorController.listarAll();

			return "listagemAutores";
		} else {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas ao incluir registro!", null));
		}

		return "";
	}

}
