package opet.tds171a.controller;

import java.util.List;

import opet.tds171a.model.FinanceiroModel;
import opet.tds171a.model.IFinanceiroDAO;
import opet.tds171a.vo.Financeiro;

public class FinanceiroController implements IFinanceiroDAO
{
    private FinanceiroModel financeiroModel;

    /**
     * Instanciando a classe FinanceiroModel
     */
    public FinanceiroController()
    {
        financeiroModel = new FinanceiroModel();
    }

    /**
     * Incluir boleto no banco
     */
    @Override
    public boolean incluir(Financeiro financeiro)
    {
        // TODO Auto-generated method stub
        return financeiroModel.incluir(financeiro);
    }

    /**
     * Editar boleto do banco
     */
    @Override
    public boolean editar(int idBoleto, String pago)
    {
        // TODO Auto-generated method stub
        return financeiroModel.editar(idBoleto, pago);
    }

    /**
     * Excluir boleto do banco
     */
    @Override
    public boolean excluir(int idBoleto)
    {
        // TODO Auto-generated method stub
        return financeiroModel.excluir(idBoleto);
    }

    /**
     * Pegar boleto do banco
     */
    @Override
    public Financeiro getFinanceiro(int idBoleto)
    {
        // TODO Auto-generated method stub
        return financeiroModel.getFinanceiro(idBoleto);
    }

    /**
     * Listar boleto do banco
     */

    @Override
    public List<Financeiro> listAll()
    {
        // TODO Auto-generated method stub
        return financeiroModel.listAll();
    }

    public FinanceiroModel getFinanceiroModel()
    {
        return financeiroModel;
    }

    public void setFinanceiroModel(FinanceiroModel financeiroModel)
    {
        this.financeiroModel = financeiroModel;
    }

    public boolean salvar(Financeiro financeiro) {
        return financeiroModel.salvar(financeiro);
    }


}
