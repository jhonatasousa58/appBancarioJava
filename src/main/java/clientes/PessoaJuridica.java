package clientes;

public class PessoaJuridica extends Cliente {

    String cnpj;

    public PessoaJuridica(String nome, String cnpj, String tipoCliente) {
        super(nome, cnpj, tipoCliente);
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
