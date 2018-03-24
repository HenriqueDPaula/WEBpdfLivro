package opet.tds171a.vo;

/**
 * Classe do grupo de usuarios do nosso sistema
 * @author Tiago Oliveira e Henrique de Paula
 *
 */
public class GrupoUsuarios {

	private int codigo;
	private String descricao;
	
	/**
	 * Construtor da classe GrupoUsuarios somente com descrição
	 * @param descricao
	 */
	public GrupoUsuarios(String descricao){
		this.descricao = descricao;
	}
	
	/**
	 * Construtor da classe GrupoUsuarios com codigo e descrição
	 * @param codigo
	 * @param nome
	 */
	public GrupoUsuarios(int codigo, String descricao){
		this.codigo = codigo;
		this.descricao = descricao;
	}
/**
 * Retorna o codigo do grupo de usuarios
 * @return
 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * Seta o codigo para o grupo de usuarios
	 * @param codigo
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * Retorna a descriçao
	 * @return
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Seta a descricao
	 * @param descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
