package opet.tds171a.vo;

public class UsuarioAdmin extends Usuario
{
   


    public UsuarioAdmin(String nome, String login, String pwd)
    {
        super();
    	setNome(nome);
        setLogin(login);
        setPwd(pwd);
        
    }


    public UsuarioAdmin(int codigo, String nome,
                    String login, String pwd,
                    String endereco, int numero,
                    String complemento,
                    int idGrupo,String cpf ) {

    	super();
        setCodigo(codigo);
        setNome(nome);
        setLogin(login);
        setPwd(pwd);
        setEndereco(endereco);
        setNumero(numero);
        setComplemento(complemento);
        setIdGrupo(idGrupo);
        setCpf(cpf);

    }


    public UsuarioAdmin (String nome,int codigo,
                    String login, String pwd,
                    String endereco, int numero,
                    String complemento,
                    int idGrupo,String cpf ) {

    	setCodigo(codigo);
        setNome(nome);
        setLogin(login);
        setPwd(pwd);
        setEndereco(endereco);
        setNumero(numero);
        setComplemento(complemento);
        setIdGrupo(idGrupo);
        setCpf(cpf);


    }
    public UsuarioAdmin(int codigo, String nome)
    {
    	setCodigo(codigo);
        setNome(nome);
    }


}
