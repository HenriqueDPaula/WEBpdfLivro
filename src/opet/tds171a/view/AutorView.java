package opet.tds171a.view;

import java.util.List;

import opet.tds171a.controller.AutorController;
import opet.tds171a.controller.UsuarioController;
import opet.tds171a.util.Leitor;
import opet.tds171a.vo.Autor;
import opet.tds171a.vo.Usuario;

/**
 * View geral para visualização de autores
 * 
 * @author Tiago Oliveira e Henrique de Paula
 *
 */

public class AutorView {

	// constanstes representando opçoes do menu (boas práticas!)
	public static final int OPCAO_INSERIR = 1;
	public static final int OPCAO_ALTERAR = 2;
	public static final int OPCAO_EXCLUIR = 3;
	public static final int OPCAO_LISTAR = 4;
	public static final int OPCAO_SAIR = 9;

	private UsuarioController usuarioController;

	public AutorView() {
		usuarioController = new UsuarioController();
	}

	/**
	 * Exibe o menu de interação com usuário
	 */
	public void menu() {

		int opcao_menu = -1;
		while (opcao_menu != 9) {
			System.out.println(" --- MENU ---");
			System.out.println(OPCAO_ALTERAR + " - Alterar autor");
			System.out.println(OPCAO_LISTAR + " - Listar autores cadastrados");

			opcao_menu = Leitor.readInt("Informe a opção desejada: ");

			switch (opcao_menu) {
			case OPCAO_LISTAR:
				listar();
				break;
			case OPCAO_ALTERAR:
				salvar();
				break;
			case 9:
				System.out.println("");
				System.out.println("Finalizando o sistema. Até mais!");
				System.out.println("");
				break;
			default:
				System.out.println("");
				System.out.println("Opção inválida. Tente novamente");
				opcao_menu = -1;
			}
		}
	}

	/**
	 * Grava um novo autor no banco de dados
	 */

	/**
	 * Lista os autores cadastrados no banco de dados
	 */
	public void listar() {
		System.out.println("");
		System.out.println("");
		System.out.println("LISTAGEM DE AUTORES");
		System.out.println("-------- -- -------");
		List<Usuario> listaUsuarios = usuarioController.listAll();
		for (Usuario usuario : listaUsuarios) {
			System.out.println(usuario.getNome() + " - " + usuario.getLogin() + " - " + usuario.getPwd());
		}

		System.out.println("");
		System.out.println("");
	}


	public void salvar() {
		int idUsuario = Leitor.readInt("Informe o código do usuario a ser editado: ");
		String nome = Leitor.readString("Informe o novo nome");
		String login = Leitor.readString("Informe o novo login");
		String pwd = Leitor.readString("Informe o novo senha");
		String endereco = Leitor.readString("Informe o novoendereco");
		int numero = Leitor.readInt("Informe o numero: ");
		String complemento = Leitor.readString("Informe o novo complemento");
		int idgrupo = Leitor.readInt("Informe o grupo(enter): ");
		String cpf = Leitor.readString("Informe o cpf: ");
		Usuario usuario = new Usuario(idUsuario, nome, login, pwd, endereco, numero, complemento, idgrupo, cpf);

		if (usuarioController.salvar(usuario)) {
			System.out.println("usuario editado com sucesso!");
		} else {
			System.out.println("Não foi possível editar o autor.");
		}
	}

}
