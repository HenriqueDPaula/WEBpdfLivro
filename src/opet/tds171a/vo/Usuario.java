package opet.tds171a.vo;

public class Usuario
{
    private int    codigo;
    private String login;
    private String pwd;
    private String nome;
    private String endereco;
    private int    numero;
    private String complemento;
    private int    idGrupo;
    private String   cpf;


    public Usuario()
    {

    }

    public Usuario(String nome, String login, String pwd)
    {
        this.nome = nome;
        this.login = login;
        this.pwd = pwd;
    }



    public Usuario(int codigo, String nome,
                    String login, String pwd,
                    String endereco, int numero,
                    String complemento,
                    int idGrupo,String cpf ) {

        this.codigo = codigo;
        this.nome = nome;
        this.login = login;
        this.pwd = pwd;
        this.endereco =endereco;
        this.numero = numero;
        this.complemento = complemento;
        this.idGrupo = idGrupo;
        this.cpf = cpf;

    }

    public Usuario(String nome, String login, String pwd,
                    String endereco, int numero,
                    String complemento, String cpf) {

        this.nome = nome;
        this.login = login;
        this.pwd = pwd;
        this.endereco =endereco;
        this.numero = numero;
        this.complemento = complemento;
        this.cpf = cpf;

}

    public Usuario (String nome,int codigo,
                    String login, String pwd,
                    String endereco, int numero,
                    String complemento,
                    int idGrupo,String cpf ) {


        this.nome = nome;
        this.login = login;
        this.pwd = pwd;
        this.endereco =endereco;
        this.numero = numero;
        this.complemento = complemento;
        this.idGrupo = idGrupo;
        this.cpf = cpf;

    }
    public Usuario(int codigo, String nome)
    {
        this.codigo = codigo;
        this.nome = nome;
    }



    public int getCodigo()
    {
        return codigo;
    }

    public void setCodigo(int codigo)
    {
        this.codigo = codigo;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String pLogin)
    {
        login = pLogin;
    }

    public String getPwd()
    {
        return pwd;
    }

    public void setPwd(String pPwd)
    {
        pwd = pPwd;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String pNome)
    {
        nome = pNome;
    }

    public String getEndereco()
    {
        return endereco;
    }

    public void setEndereco(String pEndereco)
    {
        endereco = pEndereco;
    }

    public int getNumero()
    {
        return numero;
    }

    public void setNumero(int pNumero)
    {
        numero = pNumero;
    }

    public String getComplemento()
    {
        return complemento;
    }

    public void setComplemento(String pComplemento)
    {
        complemento = pComplemento;
    }

    public int getIdGrupo()
    {
        return idGrupo;
    }

    public void setIdGrupo(int pIdGrupo)
    {
        idGrupo = pIdGrupo;
    }

    public String getCpf()
    {
        return cpf;
    }

    public void setCpf(String pCpf)
    {
        cpf = pCpf;
    }

}
