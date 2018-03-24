package opet.tds171a.view;

import java.util.Date;
import java.util.List;

import opet.tds171a.controller.FinanceiroController;
import opet.tds171a.util.Leitor;
import opet.tds171a.vo.Financeiro;


public class FinanceiroView {

	public static final int OPCAO_INSERIR 	= 1;
	public static final int OPCAO_ALTERAR 	= 2;
	public static final int OPCAO_EXCLUIR 	= 3;
	public static final int OPCAO_LISTAR 	= 4;
	public static final int OPCAO_SAIR 		= 9;

	private FinanceiroController financeiroController;


	public FinanceiroView() {
		financeiroController = new FinanceiroController();
	}

	/**
	 * Exibe o menu de interação com usuário
	 */
	public void menu() {

		int opcao_menu = -1;
		while(opcao_menu != 9) {
			System.out.println(" --- MENU ---");
			System.out.println(OPCAO_INSERIR+" - Inserir boleto");
			System.out.println(OPCAO_ALTERAR+" - Alterar boleto");
			System.out.println(OPCAO_EXCLUIR+" - Excluir boleto");
			System.out.println(OPCAO_LISTAR+" - Listar boletos cadastrados");
			System.out.println(OPCAO_SAIR+" - Sair do sistema");

			opcao_menu = Leitor.readInt("Informe a opção desejada: ");

			switch (opcao_menu) {
			case OPCAO_INSERIR:
				incluir();
				break;
			case OPCAO_EXCLUIR:
				excluir();
				break;
			case OPCAO_LISTAR:
				listar();
				break;
			case OPCAO_ALTERAR:
				editar();
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
	public void incluir() {
		Date dtEmissao = Leitor.readDate("Entre com a data de emissao: ");
		Date dtVencimento = Leitor.readDate("Entre com a data de vencimento: ");
		double valor = Leitor.readDouble("Entre com o valor do boleto: ");
		double jurosPago = Leitor.readDouble("Entre com os juros a serem pagos: ");
		String pago = Leitor.readString("Boleto j� foi pago(S/N)? ");
		int idUsuario = Leitor.readInt("id do usuario");
		Financeiro novoBoleto = new Financeiro(dtEmissao, dtVencimento, valor, jurosPago, pago, idUsuario);


		if (financeiroController.incluir(novoBoleto)) {
			System.out.println("");
			System.out.println("Boleto inserido com sucesso!");
			System.out.println("");
		} else {
			System.out.println("");
			System.out.println("Boleto nao inserido no banco de dados.");
			System.out.println("");
		}
	}

	/**
	 * Lista os autores cadastrados no banco de dados
	 */
	public void listar() {
		System.out.println("");
		System.out.println("");
		System.out.println("LISTAGEM DE BOLETOS");
		System.out.println("-------- -- -------");
		List<Financeiro> listaBoletos = financeiroController.listAll();
		for (Financeiro financeiro : listaBoletos) {
			System.out.println(financeiro.getIdBoleto()+" - "+ financeiro.getDtEmissao()+" - "+financeiro.getDtVencimento()+" - "+financeiro.getValor()
			+" - "+financeiro.getJurosPago()+" - "+financeiro.getPago()+" - "+financeiro.getIdUsuario());
		}

		System.out.println("");
		System.out.println("");
	}

	public void excluir() {
		int idBoleto = Leitor.readInt("Informe o código do boleto: ");


		if (financeiroController.excluir(idBoleto)) {
			System.out.println("");
			System.out.println("Boleto excluído com sucesso!");
			System.out.println("");
		} else {
			System.out.println("");
			System.out.println("Não foi possível excluir o boleto.");
			System.out.println("");
		}
	}

	public void editar() {
		int idBoleto = Leitor.readInt("id do boleto a ser editado: ");
		String pago = Leitor.readString("Boleto j� foi pago(S/N)? ");



		if (financeiroController.editar(idBoleto, pago)) {
			System.out.println("Boleto editado com sucesso!");
		} else {
			System.out.println("Não foi possível editar o Boleto.");
		}
	}


}
