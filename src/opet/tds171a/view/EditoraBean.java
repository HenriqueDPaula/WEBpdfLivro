package opet.tds171a.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import opet.tds171a.controller.EditoraController;
import opet.tds171a.vo.Editora;
import opet.tds171a.vo.Usuario;

@ManagedBean(name = "editoraBean")
@SessionScoped
// @SessionScoped

public class EditoraBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8590723287020996535L;

	/**
	 * 
	 */

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;

	private EditoraController editoraController;

	private int idEditora;

	private String nomeEditora;

	private List<Editora> listaEditoras;

	public EditoraBean() {
		// TODO Auto-generated constructor stub
		setIdEditora(-1);
		setNomeEditora("");

		listaEditoras = null;
		editoraController = new EditoraController();

	}

	/**
	 * Alimenta a lista após instanciar o objeto, para que seja possível
	 * utilizar a listagem nos select dos formularios
	 */
	@PostConstruct
	public void init() {
		listaEditoras = editoraController.listarAll();
	}
	
/**
 * Incluir editora
 * @return
 */
	public String incluir() {
		Editora editora = new Editora(nomeEditora);
		FacesContext contexto = FacesContext.getCurrentInstance();

		if (editoraController.incluir(editora)) {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Inclusão OK!", null));
			System.out.println("Editora inserida com sucesso.");
		} else {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas na inclusão!", null));
			System.out.println("Problemas ao inserir editora.");
		}

		// da refresh na listagem
		listaEditoras = editoraController.listarAll();

		return "listagemEditoras";

	}

	/**
	 * Método para setar o IdLivro para -1, e assim inserir no banco, e não
	 * realizar o update. Após setar
	 * 
	 * @return Retorna a view de cadastro
	 */
	public String prepararInserir() {
		setIdEditora(-1);
		setNomeEditora("");

		return "cadastroEditora";
	}

	 /*
	 * Listar editora
	 * @return
	 */
	public String listar() {

		listaEditoras = editoraController.listarAll();

		return "editora/listagemEditoras";
	}

	/**
	 * Excluir editora
	 * @param codigoEditora
	 * @return
	 */
	public String excluir(int codigoEditora) {
		FacesContext contexto = FacesContext.getCurrentInstance();
		if (editoraController.excluir(codigoEditora)) {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Editora excluída com sucesso!", null));
			// refaz a listagem pra não constar mais o excluido
			listaEditoras = editoraController.listarAll();
		} else {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas ao excluir editora. Possivelmente já está sendo utilizada em algum livro.", null));
		}

		return "listagemEditoras";
	}

	/**
	 * Editar editora
	 * @param codigoEditora
	 * @return
	 */
	public String editar(int codigoEditora) {
		setIdEditora(codigoEditora);

		Editora editora = editoraController.getEditora(codigoEditora);
		setNomeEditora(editora.getNome());
		return "cadastroEditora";
	}

	public String salvar() {

		
		Editora editora = new Editora(idEditora, nomeEditora);
		FacesContext contexto = FacesContext.getCurrentInstance();

		if (editoraController.salvar(editora)) {
			// cria mensagem para usuário
			contexto.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Editora salva com sucesso!", null));
			// recarrega a listagem
			listaEditoras = editoraController.listarAll();
			// redireciona para listagem
			return "listagemEditoras";
		} else {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas ao salvar editora!", null));
		}

		return "";

	}

	
	
	
	/**
	 * M�todos Getters & Setters
	 * @return
	 */
	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public EditoraController getEditoraController() {
		return editoraController;
	}

	public void setEditoraController(EditoraController editoraController) {
		this.editoraController = editoraController;
	}

	public int getIdEditora() {
		return idEditora;
	}

	public void setIdEditora(int idEditora) {
		this.idEditora = idEditora;
	}

	public String getNomeEditora() {
		return nomeEditora;
	}

	public void setNomeEditora(String nomeEditora) {
		this.nomeEditora = nomeEditora;
	}

	public List<Editora> getListaEditoras() {
		return listaEditoras;
	}

	public void setListaEditoras(List<Editora> listaEditoras) {
		this.listaEditoras = listaEditoras;
	}

}
