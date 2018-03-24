package opet.tds171a.vo;
/**
 * Classe da Editora dos livros
 * @author Tiago Oliveira e Henrique de Paula
 *
 */
public class Editora {
	
	private String nome;
	private int codigo;
	
	
	/**
	 * Construtor da classe Editora, somente com o nome
	 * @param nome
	 */
	public Editora(String nome){
		this.nome = nome;
	}
	/**
	 * Construtor da classe Editora com nome e codigo
	 * @param codigo
	 * @param nome
	 */
	public Editora(int codigo, String nome){
		this.codigo = codigo;
		this.nome = nome;
	}

	/**
	 * Retorna o nome da editora
	 * @return
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Seta o nome da editora
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Retorna o codigo da editora
	 * @return
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * Seta o codigo da editora
	 * @param nome
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * Necessária para utilizar o <h:select sem precisar do conversor
	 */
	@Override
    public String toString() {
		Integer cod = this.codigo;
        return cod.toString();
    }
	
	
	
	
	
}
