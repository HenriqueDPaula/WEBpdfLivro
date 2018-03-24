package opet.tds171a.view;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import opet.tds171a.controller.UsuarioController;
import opet.tds171a.vo.Usuario;

@ManagedBean(name = "adesaoBean")
@SessionScoped
public class AdesaoBean implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = 1L;

	private UsuarioController usuarioController;

	private int idUsuario;

	private String nomeUsuario;

	private String loginUsuario;

	private String senhaUsuario;

	private String senhaUsuario2;

	private String enderecoUsuario;

	private int enderecoNumero;

	private String complementoEndereco;

	private String cpfUsuario;

	private Usuario usuario;

	private List<Usuario> listaUsuarios;

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;

	/**
	 * Setando valores aos atributos
	 */
	public AdesaoBean() {
		// TODO Auto-generated constructor stub
		setIdUsuario(0);
		setLoginUsuario("");
		setSenhaUsuario("");
		setNomeUsuario("");
		setSenhaUsuario2("");
		setEnderecoUsuario("");
		setEnderecoNumero(0);
		setComplementoEndereco("");
		setCpfUsuario("");

		listaUsuarios = null;
		usuarioController = new UsuarioController();

	}

	public void init() {
		listaUsuarios = usuarioController.listAll();
	}

	/**
	 * Inclusao de usuario chamando a usuarioController
	 * 
	 * @return
	 */
	public String incluir() {
		Usuario usuario = new Usuario(nomeUsuario, loginUsuario, senhaUsuario, enderecoUsuario, enderecoNumero,
				complementoEndereco, cpfUsuario);
		FacesContext contexto = FacesContext.getCurrentInstance();

		// Senha2 deve ser igual a primeira senha
		if (senhaUsuario.equals(senhaUsuario2)) {
			
			//Chama o método para conferir se o login já existe
			usuarioController.loginExist(loginUsuario);
			
			//se for verdadeiro possegue
			if (usuarioController.loginExist(loginUsuario) == true) {
				if(enderecoNumero != 0){
				if (usuarioController.usuarioCadastro(usuario)) {
					contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Cadastrado com sucesso, por favor faça o login para continuar!", null));
					usuarioController.getUsuario(idUsuario);
					return "/index";
					
				} else { //Se não incluir
					contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Problemas na inclusÃ£o! Tente novamente", null));
					return "/usuario/adesaoUsuario";
				}
				}else{
					contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Número do endereço não pode ser '0'", null));
					return "/usuario/adesaoUsuario";
				}
			} else { // Se Login já existir
				contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login já existe", null));
				return "/usuario/adesaoUsuario";
			}
		} else { // Se a confirmação de senha não conferir
			contexto.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha informada nÃ£o confere", null));
			return "/usuario/adesaoUsuario";
		}
		

	}

	/**
	 * Métodos Getters & Setters
	 * 
	 * @return
	 */
	public UsuarioController getUsuarioController() {
		return usuarioController;
	}

	public void setUsuarioController(UsuarioController pUsuarioController) {
		usuarioController = pUsuarioController;
	}

	public String getLoginUsuario() {
		return loginUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String pNomeUsuario) {
		nomeUsuario = pNomeUsuario;
	}

	public void setLoginUsuario(String pLoginUsuario) {
		loginUsuario = pLoginUsuario;
	}

	public String getSenhaUsuario() {
		return senhaUsuario;
	}

	public void setSenhaUsuario(String pSenhaUsuario) {
		senhaUsuario = pSenhaUsuario;
	}

	public String getEnderecoUsuario() {
		return enderecoUsuario;
	}

	public void setEnderecoUsuario(String pEnderecoUsuario) {
		enderecoUsuario = pEnderecoUsuario;
	}

	public int getEnderecoNumero() {
		return enderecoNumero;
	}

	public void setEnderecoNumero(int pEnderecoNumero) {
		enderecoNumero = pEnderecoNumero;
	}

	public String getComplementoEndereco() {
		return complementoEndereco;
	}

	public void setComplementoEndereco(String pComplementoEndereco) {
		complementoEndereco = pComplementoEndereco;
	}

	public String getCpfUsuario() {
		return cpfUsuario;
	}

	public void setCpfUsuario(String pCpfUsuario) {
		cpfUsuario = pCpfUsuario;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean pLoginBean) {
		loginBean = pLoginBean;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getSenhaUsuario2() {
		return senhaUsuario2;
	}

	public void setSenhaUsuario2(String pSenhaUsuario2) {
		senhaUsuario2 = pSenhaUsuario2;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

}
