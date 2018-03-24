package opet.tds171a.vo;

/**
 * Representa o autor de livros
 * @author Tiago Oliveira e Henrique de Paula
 *
 */
public class Autor {

	private String nome;
	private int codigo;
	
	/**
	 * Construtor da classe. Espera um nome para um novo autor
	 * @param nome String
	 */
	public Autor(String nome) {
		this.codigo = -1;
		this.nome = nome;
	}
	
	
	public Autor(int codigo, String nome) {
		this.codigo= codigo;
		this.nome = nome;
	}
	
	/**
	 * Retorna o nome do autor
	 * @return
	 */
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * Seta o nome do autor
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Pega o código gerado para o autor
	 * @return
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * Seta código
	 * @param codigo
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
