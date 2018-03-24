package opet.tds171a.vo;

import java.util.Date;

/**
 * Classe do financeiro dos boletos
 *
 * @author 1201700302
 *
 */
public class Financeiro
{
    private int    idBoleto;
    private Date   dtEmissao;
    private Date   dtVencimento;
    private double valor;
    private double jurosPago;
    private String pago;
    private int    idUsuario;

    public Financeiro(Date dtEmissao, Date dtVencimento, double valor, double jurosPagos,
                    String pago, int idUsuario)
    {
        this.dtEmissao = dtEmissao;
        this.dtVencimento = dtVencimento;
        this.valor = valor;
        this.jurosPago = jurosPagos;
        this.pago = pago;
        this.idUsuario = idUsuario;
    }

    public Financeiro(int idBoleto, Date dtEmissao, Date dtVencimento, double valor, double jurosPagos,
                    String pago, int idUsuario)
    {
        this.idBoleto = idBoleto;
        this.dtEmissao = dtEmissao;
        this.dtVencimento = dtVencimento;
        this.valor = valor;
        this.jurosPago = jurosPagos;
        this.pago = pago;
        this.idUsuario = idUsuario;
    }
    public Financeiro(int idBoleto, String pago) {
        this.pago = pago;
        this.idBoleto = idBoleto;
    }



    public int getIdBoleto()
    {
        return idBoleto;
    }

    public void setIdBoleto(int pIdBoleto)
    {
        idBoleto = pIdBoleto;
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

    public double getJurosPago()
    {
        return jurosPago;
    }

    public void setJurosPago(double pJurosPago)
    {
        jurosPago = pJurosPago;
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

}
