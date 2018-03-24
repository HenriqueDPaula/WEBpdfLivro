package opet.tds171a.controller;

import opet.tds171a.model.UsuarioModel;
import opet.tds171a.vo.Usuario;

public class LoginController {

	/**
	 * Get usuario e senha do usuario
	 * @param usuarioLogin
	 * @param senhaLogin
	 * @return
	 */
	public Usuario autenticar(String usuarioLogin, String senhaLogin) {
		
		Usuario usuario = new UsuarioModel().getUsuarioByLoginSenha(usuarioLogin, senhaLogin);
		
		return usuario;

	}
	
}
