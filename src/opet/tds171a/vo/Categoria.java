package opet.tds171a.vo;
/**
 * Categoria dos livros
 * @author Tiago Oliveira e Henrique de Paula
 *
 */
public class Categoria {

	private int codigo;
	private String nome;
	
	/**
	 * Construtor da classe Categoria, somente com o nome
	 * @param nome
	 */
	public Categoria(String nome){
		this.nome = nome;
	}
	
	/**
	 * Construtor da classe Categoria com nome e codigo
	 * @param nome
	 * @param codigo
	 */
	public Categoria(int codigo, String nome){
		this.codigo = codigo;
		this.nome = nome;
	}

	/**
	 * Retorna o codigo da Categoria
	 * @return
	 */
	public int getCodigo() {
		return codigo;
	}
/**
 * Seta o codigo da Categoria
 * @param codigo
 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * Retorna o nome da catedoria
	 * @return
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Seta o nome da Categoria
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
	
	
	
	
	
}
