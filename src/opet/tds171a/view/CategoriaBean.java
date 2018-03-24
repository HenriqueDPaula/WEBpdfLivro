package opet.tds171a.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
//import javax.annotation.ManagedBean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import opet.tds171a.controller.CategoriaController;
import opet.tds171a.vo.Categoria;

@ManagedBean(name = "categoriaBean")
@SessionScoped
// @SessionScoped

public class CategoriaBean implements Serializable {

	/**
	 * 
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1579794757848824683L;

	/**
	 * 
	 */

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;

	private CategoriaController categoriaController;

	private int idCategoria;

	private String nomeCategoria;

	private List<Categoria> listaCategorias;

	/**
	 * Setando valores aos atributos
	 */
	public CategoriaBean() {
		// TODO Auto-generated constructor stub
		setIdCategoria(-1);
		setNomeCategoria("");
		// setListaAutores(null);

		listaCategorias = null;
		categoriaController = new CategoriaController();

		// this.loginBean = (LoginBean)
		// FacesContext.getCurrentInstance().getExternalContext()
		// .getRequestMap().get("loginBean");

	}

	@PostConstruct
	public void init() {
		listaCategorias = categoriaController.listarAll();
	}

	/**
	 * Inclusao de categoria
	 * 
	 * @return
	 */
	public String incluir() {
		Categoria categoria = new Categoria(nomeCategoria);
		FacesContext contexto = FacesContext.getCurrentInstance();

		if (categoriaController.incluir(categoria)) {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Inclusão OK!", null));
			System.out.println("Categoria inserido com sucesso.");
		} else {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas na inclusão!", null));
			System.out.println("Problemas ao inserir categoria.");
		}

		// da refresh na listagem
		listaCategorias = categoriaController.listarAll();

		return "listagemCategorias";

	}

	
	/**
	 * Método para setar o IdLivro para -1, e assim inserir no banco, e não
	 * realizar o update. Após setar
	 * 
	 * @return Retorna a view de cadastro
	 */
	public String prepararInserir() {
		setIdCategoria(-1);
		setNomeCategoria("");

		return "cadastroCategoria";
	} 
	

	/**
	 * M�todo para listar categorias
	 * 
	 * @return
	 */

	public String listar() {
		listaCategorias = categoriaController.listarAll();

		return "categoria/listagemCategorias";
	}

	/**
	 * Exclusao e categoria
	 * 
	 * @param codigoCategoria
	 * @return
	 */
	public String excluir(int codigoCategoria) {
		FacesContext contexto = FacesContext.getCurrentInstance();
		if (categoriaController.excluir(codigoCategoria)) {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Categoria excluída com sucesso!", null));
			// refaz a listagem pra não constar mais o excluido
			listaCategorias = categoriaController.listarAll();
		} else {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas ao excluir categoria. Possivelmente está sendo utilizada em algum livro.", null));
		}
		

		return "listagemCategorias";
	}

	/**
	 * Editar categoria
	 * 
	 * @param codigoCategoria
	 * @return
	 */
	public String editar(int codigoCategoria) {
		setIdCategoria(codigoCategoria);

		Categoria categoria = categoriaController.getCategoria(codigoCategoria);
		setNomeCategoria(categoria.getNome());
		return "cadastroCategoria";
	}

	public String salvar() {

		
		Categoria categoria = new Categoria(idCategoria, nomeCategoria);
		FacesContext contexto = FacesContext.getCurrentInstance();

		
		if(categoriaController.salvar(categoria)) {
			// cria mensagem para usuário 
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Categoria salva com sucesso!", null));
			// recarrega a listagem 
			listaCategorias = categoriaController.listarAll();	
			// redireciona para listagem 
			return "listagemCategorias";
		} else {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Livro salvo com sucesso!", null));
			System.out.println("Problemas ao salvar registro.");
		}
		
		
		return "";

	}

	/**
	 * M�todos Getters & Setters
	 * 
	 * @return
	 */
	public CategoriaController getCategoriaController() {
		return categoriaController;
	}

	public void setCategoriaController(CategoriaController categoriaController) {
		this.categoriaController = categoriaController;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public List<Categoria> getListaCategorias() {
		return listaCategorias;
	}

	public void setListaCategorias(List<Categoria> listaCategorias) {
		this.listaCategorias = listaCategorias;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

}
