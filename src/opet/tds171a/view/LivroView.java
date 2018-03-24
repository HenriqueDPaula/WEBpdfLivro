package opet.tds171a.view;

import java.util.ArrayList;

import opet.tds171a.controller.LivroController;
import opet.tds171a.util.Leitor;
import opet.tds171a.vo.Livro;

public class LivroView
{
 // constanstes representando opçoes do menu (boas práticas!)
    public static final int OPCAO_INSERIR   = 1;
    public static final int OPCAO_ALTERAR   = 2;
    public static final int OPCAO_EXCLUIR   = 3;
    public static final int OPCAO_LISTAR    = 4;
    public static final int OPCAO_SAIR      = 9;

    private LivroController livroController;

    public LivroView() {
        livroController = new LivroController();
    }

    public void menu() {

        int opcao_menu = -1;
        while(opcao_menu != 9) {
            System.out.println(" --- MENU ---");
            System.out.println(OPCAO_INSERIR+" - Inserir livro");
            System.out.println(OPCAO_ALTERAR+" - Alterar livro");
            System.out.println(OPCAO_EXCLUIR+" - Excluir livro");
            System.out.println(OPCAO_LISTAR+" - Listar livroes cadastrados");
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


    public void incluir() {
        String titulo = Leitor.readString("Entre com o titulo: ");
        String resumo = Leitor.readString("Entre com o resumo: ");
        int idAutor = Leitor.readInt("id do autor: ");
        String ativo = Leitor.readString("esta ativo(s/n): ");
        int idEditora = Leitor.readInt("id da editora: ");
        int idCategoria = Leitor.readInt("id da categoria: ");
        Livro novoLivro = new Livro(titulo, resumo, idAutor, ativo, idEditora, idCategoria);


        if (livroController.incluir(novoLivro)) {
            System.out.println("");
            System.out.println("Autor inserido com sucesso!");
            System.out.println("");
        } else {
            System.out.println("");
            System.out.println("Autor nao inserido no banco de dados.");
            System.out.println("");
        }
    }

    public void listar() {
        System.out.println("");
        System.out.println("");
        System.out.println("LISTAGEM DE Livro");
        System.out.println("-------- -- -------");
        ArrayList<Livro> listaLivros = (ArrayList<Livro>) livroController.listAll();
        for (Livro livro : listaLivros) {
            System.out.println(livro.getIdLivro()+" - "+livro.getTitulo()+" - "+livro.getResumo()+" - "+
                            livro.getAtivo()+" - "+livro.getIdAutor()+" - "+
                            livro.getIdCategoria()+" - "+livro.getIdEditora());
        }

        System.out.println("");
        System.out.println("");
    }


    public void excluir() {
        int idLivro = Leitor.readInt("Informe o código do livro: ");


        if (livroController.excluir(idLivro)) {
            System.out.println("");
            System.out.println("Livro excluído com sucesso!");
            System.out.println("");
        } else {
            System.out.println("");
            System.out.println("Não foi possível excluir o livro.");
            System.out.println("");
        }
    }

    public void editar() {
        int idLivro = Leitor.readInt("Informe o código do livro a ser editado: ");
        String titulo = Leitor.readString("Informe o novo titulo: ");
        String resumo = Leitor.readString("Entre com novo resumo: ");
        int idAutor = Leitor.readInt("id do autor: ");
        String ativo = Leitor.readString("esta ativo(s/n): ");
        int idEditora = Leitor.readInt("id da editora: ");
        int idCategoria = Leitor.readInt("id da categoria: ");

        if (livroController.editar(idAutor, titulo, resumo, idAutor, ativo, idEditora, idCategoria)) {
            System.out.println("Livro editado com sucesso!");
        } else {
            System.out.println("Não foi possível editar o livro.");
        }
    }





}
