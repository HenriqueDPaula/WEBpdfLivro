package opet.tds171a.view;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
//import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import opet.tds171a.controller.LoginController;
import opet.tds171a.controller.UsuarioController;
import opet.tds171a.vo.Usuario;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -4016462403953004046L;

	private int idgrupo;

	private String nomeUsuario;

	private String senhaLogin2;

	private String senhaConfirm;

	private String enderecoUsuario;

	private int enderecoNumero;

	private String complementoEndereco;

	private String cpfUsuario;

	private int codigoUsuario;

	private String usuarioLogin;

	private String senhaLogin;

	private Usuario usuarioLogado;

	private LoginController loginController;

	private UsuarioController usuarioController;

	// private Usuario usuarioLogado = null

	public LoginBean() {
		// TODO Auto-generated constructor stub
		loginController = new LoginController();
		usuarioController = new UsuarioController();

	}

	public String autenticarUsuario() {
		Usuario usuario = loginController.autenticar(usuarioLogin, senhaLogin);

		// se usuário for diferente de nulo
		if (usuario != null) {
			setUsuarioLogado(usuario);
			// se usuário for do grupo Admin, redireciona para o grupo certo

			if (usuario.getIdGrupo() == 1) {
				return "admin/inicial";
			} else {
				return "usuario/inicial";
			}

		} else {
			// usuário ou senha inválidos , cria mensagem de erro
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Usuário ou senha informados são inválidos.", null));

			// limpa a senha do usuário, e continua exibindo o login informado
			senhaLogin = "";
			return "/";
		}

	}

	/**
	 * M�todo para pegar o id do usuario logado e direcionar para a pagina de
	 * perfil
	 * 
	 * @return
	 */
	public String detalhes() {
		Usuario usuario = usuarioController.getUsuario(usuarioLogado.getCodigo());
		setCodigoUsuario(usuario.getCodigo());
		setNomeUsuario(usuario.getNome());
		setEnderecoUsuario(usuario.getEndereco());
		setEnderecoNumero(usuario.getNumero());
		setCpfUsuario(usuario.getCpf());
		setEnderecoUsuario(usuario.getEndereco());
		setEnderecoNumero(usuario.getNumero());
		//loginBean.editar(loginBean.usuarioLogado.codigo)
		//return "/usuario/perfilUsuario";
		return "/usuario/editarUsuario";

	}

	/**
	 * 
	 * @param codigoUsuario
	 * @return
	 */
	public String editar(int codigoUsuario) {
		setCodigoUsuario(codigoUsuario);
		Usuario usuario = usuarioController.getUsuario(codigoUsuario);

		return "/usuario/editarUsuario";

	}

	/**
	 * Salva os dados no banco
	 * 
	 * @return
	 */
	public String salvar() {

		Usuario usuario = new Usuario(codigoUsuario, nomeUsuario, usuarioLogin, senhaLogin, enderecoUsuario,
				enderecoNumero, complementoEndereco, idgrupo, cpfUsuario);

		
		FacesContext contexto = FacesContext.getCurrentInstance();
		
		if (usuarioController.loginExist(usuarioLogin) == true) {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login j� existe", null));
			return "editarUsuario";
		}
		
		if(usuarioController.salvar(usuario) ) {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Dados atualizados com sucesso.", null));
			// atualiza o nome do usuário logado, para aparecer corretamente no cabeçalho da página
			usuarioLogado.setNome(nomeUsuario);
		} else {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Problemas ao atualizar dados.", null));
		}
		
		
		return "";
	}


	/**
	 * Retira o usuário da bean, e retorna a página inicial de login
	 *
	 * @return
	 */
	public String logoff() {
		//System.out.println("Executando logoff");
		// setUsuarioLogado(null);
		return "/index";
	}

	public String getUsuarioLogin() {
		return usuarioLogin;
	}

	public void setUsuarioLogin(String usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
	}

	public String getSenhaLogin() {
		return senhaLogin;
	}

	public void setSenhaLogin(String senhaLogin) {
		this.senhaLogin = senhaLogin;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public int getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(int codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenhaLogin2() {
		return senhaLogin2;
	}

	public void setSenhaLogin2(String senhaLogin2) {
		this.senhaLogin2 = senhaLogin2;
	}

	public String getEnderecoUsuario() {
		return enderecoUsuario;
	}

	public void setEnderecoUsuario(String enderecoUsuario) {
		this.enderecoUsuario = enderecoUsuario;
	}

	public int getEnderecoNumero() {
		return enderecoNumero;
	}

	public void setEnderecoNumero(int enderecoNumero) {
		this.enderecoNumero = enderecoNumero;
	}

	public String getComplementoEndereco() {
		return complementoEndereco;
	}

	public void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
	}

	public String getCpfUsuario() {
		return cpfUsuario;
	}

	public void setCpfUsuario(String cpfUsuario) {
		this.cpfUsuario = cpfUsuario;
	}

	public int getIdgrupo() {
		return idgrupo;
	}

	public void setIdgrupo(int idgrupo) {
		this.idgrupo = idgrupo;
	}

	public String getSenhaConfirm() {
		return senhaConfirm;
	}

	public void setSenhaConfirm(String senhaConfirm) {
		this.senhaConfirm = senhaConfirm;
	}

}
