package clientes;

public class PessoaFisica extends Cliente {

    String cpf;

    public PessoaFisica(String nome, String cpf, String tipoCliente) {
        super(nome, cpf, tipoCliente);
    }

    @Override
    public String getDocumento() {
        return cpf;
    }

    @Override
    public void setDocumento(String documento) {
        this.cpf = documento;
    }
}
