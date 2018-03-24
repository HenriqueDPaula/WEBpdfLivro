package opet.tds171a.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import opet.tds171a.controller.FinanceiroController;
import opet.tds171a.vo.Financeiro;
import opet.tds171a.vo.Usuario;

@ManagedBean(name = "financeiroBean")
@SessionScoped
public class FinanceiroBean implements Serializable
{

    /**
     * Atributos do financeiro
     */
    private static final long    serialVersionUID = 1L;
    private FinanceiroController financeiroController;
    private int                  idFinanceiro;
    private Date                 dtEmissao;
    private Date                 dtVencimento;
    private double               valor;
    private double               jurosPagos;
    private String               pago;
    private int                  idUsuario;
    private Usuario              usuario;
    private List<Financeiro>     listaBoletos;

    /**
     * Setando valores
     */
    public FinanceiroBean()
    {
        setIdFinanceiro(-1);
        setDtEmissao(null);
        setDtVencimento(null);
        setValor(0);
        setJurosPagos(0);
        setPago("");
        setIdUsuario(0);
        listaBoletos = null;
        financeiroController = new FinanceiroController();
    }

    public void init()
    {
        listaBoletos = financeiroController.listAll();
    }

    /**
     * M�todo para incluir no banco chamando a controller
     *
     * @return
     */
    public String incluir()
    {
        Financeiro financeiro = new Financeiro(dtEmissao, dtVencimento, valor, jurosPagos, pago, idUsuario);
        FacesContext contexto = FacesContext.getCurrentInstance();

        if (financeiroController.incluir(financeiro))
        {
            contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Inclusão OK!", null));
            listaBoletos = financeiroController.listAll();
            return "/admin/financeiro/listagemFinanceiro";
        }
        else
        {
            contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nao incluido", null));
        }

        listaBoletos = financeiroController.listAll();
        return "/admin/financeiro/listagemFinanceiro";
    }

    /**
     * M�todo para listar os boletos
     *
     * @return
     */
    public String listar()
    {
        listaBoletos = financeiroController.listAll();
        return "/admin/financeiro/listagemFinanceiro";
    }

    /**
     * Excluindo chamando a controller
     *
     * @param idFinanceiro
     * @return
     */
    public String excluir(int idFinanceiro)
    {
        if (financeiroController.excluir(idFinanceiro))
        {
            listaBoletos = financeiroController.listAll();
        }
        return "listagemFinanceiro";
    }

    public String caminhoEditar(int idFinanceiro)
    {
        setIdFinanceiro(idFinanceiro);
        Financeiro financeiro = financeiroController.getFinanceiro(idFinanceiro);
        setPago(financeiro.getPago());
        financeiroController.editar(idFinanceiro, financeiro.getPago());
        listaBoletos = financeiroController.listAll();

        return "editarFinanceiro";
    }


    public String salvar()
    {

        // setIdAutor(codigoAutor);
        Financeiro financeiro = new Financeiro(idFinanceiro, pago);
        FacesContext contexto = FacesContext.getCurrentInstance();

        financeiroController.salvar(financeiro);
        listaBoletos = financeiroController.listAll();

        return "listagemFinanceiro";
    }

    /**
     * M�todos Getters & setters dos atributos
     *
     * @return
     */
    public FinanceiroController getFinanceiroController()
    {
        return financeiroController;
    }

    public void setFinanceiroController(FinanceiroController pFinanceiroController)
    {
        financeiroController = pFinanceiroController;
    }

    public int getIdFinanceiro()
    {
        return idFinanceiro;
    }

    public void setIdFinanceiro(int pIdFinanceiro)
    {
        idFinanceiro = pIdFinanceiro;
    }

    public Date getDtEmissao()
    {
        return dtEmissao;
    }

    public void setDtEmissao(Date pDtEmissao)
    {
        dtEmissao = pDtEmissao;
    }

    public Date getDtVencimento()
    {
        return dtVencimento;
    }

    public void setDtVencimento(Date pDtVencimento)
    {
        dtVencimento = pDtVencimento;
    }

    public double getValor()
    {
        return valor;
    }

    public void setValor(double pValor)
    {
        valor = pValor;
    }

    public double getJurosPagos()
    {
        return jurosPagos;
    }

    public void setJurosPagos(double pJurosPagos)
    {
        jurosPagos = pJurosPagos;
    }

    public String getPago()
    {
        return pago;
    }

    public void setPago(String pPago)
    {
        pago = pPago;
    }

    public int getIdUsuario()
    {
        return idUsuario;
    }

    public void setIdUsuario(int pIdUsuario)
    {
        idUsuario = pIdUsuario;
    }

    public Usuario getUsuario()
    {
        return usuario;
    }

    public void setUsuario(Usuario pUsuario)
    {
        usuario = pUsuario;
    }

    public List<Financeiro> getBoletos()
    {
        return listaBoletos;
    }

    public void setBoletos(List<Financeiro> listaBoletos)
    {
        this.listaBoletos = listaBoletos;
    }

    public List<Financeiro> getListaBoletos()
    {
        return listaBoletos;
    }

    public void setListaBoletos(List<Financeiro> listaBoletos)
    {
        this.listaBoletos = listaBoletos;
    }

    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }
}
