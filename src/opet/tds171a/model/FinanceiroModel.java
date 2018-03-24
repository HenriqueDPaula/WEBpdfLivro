package opet.tds171a.model;

import java.util.List;

import opet.tds171a.vo.Financeiro;

public class FinanceiroModel implements IFinanceiroDAO
{

    private FinanceiroDAO financeiroDAO;

    /**
     * Instanciando a classe do FinanceiroDAO
     */
    public FinanceiroModel()
    {
        financeiroDAO = new FinanceiroDAO();
    }

    /**
     * Incluir boleto do banco
     */
    @Override
    public boolean incluir(Financeiro financeiro)
    {
        // TODO Auto-generated method stub
        return financeiroDAO.incluir(financeiro);
    }

    /**
     * Editar boleto do banco
     */
    @Override
    public boolean editar(int idBoleto, String pago
)
    {
        // TODO Auto-generated method stub
        return financeiroDAO.editar(idBoleto, pago);
    }

    /**
     * Excluir boleto do banco
     */
    @Override
    public boolean excluir(int idBoleto)
    {
        // TODO Auto-generated method stub
        return financeiroDAO.excluir(idBoleto);
    }

    public boolean salvar(Financeiro financeiro)
    {
        return financeiroDAO.salvar(financeiro);
    }
    /**
     * Pegar boleto no banco
     */
    @Override
    public Financeiro getFinanceiro(int idBoleto)
    {
        // TODO Auto-generated method stub
        return financeiroDAO.getFinanceiro(idBoleto);
    }

    /**
     * Listar boletos do banco
     */
    @Override
    public List<Financeiro> listAll()
    {
        // TODO Auto-generated method stub
        return financeiroDAO.listAll();
    }

    public FinanceiroDAO getFinanceiroDAO()
    {
        return financeiroDAO;
    }

    public void setFinanceiroDAO(FinanceiroDAO financeiroDAO)
    {
        this.financeiroDAO = financeiroDAO;
    }

}
