package opet.tds171a.model;

import java.util.List;

import opet.tds171a.vo.Financeiro;

public interface IFinanceiroDAO
{
    /**
     * Inclui um boleto
     *
     * @param financeiro
     * @return
     */
    public boolean incluir(Financeiro financeiro);

    /**
     * Edita um boleto
     *
     * @param idBoleto
     * @param dtEmissao
     * @param dtVencimento
     * @param valor
     * @param jurosPagos
     * @param pago
     * @param idUsuario
     * @return
     */

    public boolean editar(int idBoleto, String pago);

    /**
     * Exclui um Boleto
     *
     * @param idBoleto
     * @return
     */
    public boolean excluir(int idBoleto);

    /**
     * Pega um boleto no banco de dados
     *
     * @param idBoleto
     * @return
     */
    public Financeiro getFinanceiro(int idBoleto);

    /**
     * Retorna a listagem de Livros no banco
     *
     * @return
     */
    public List<Financeiro> listAll();



}
