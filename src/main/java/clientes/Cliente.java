package clientes;

public class Cliente {

    String nome;
    String documento;
//    String endereco;
//    String telefone;
    String tipoCliente;


    public Cliente(String nome, String documento, String tipoCliente) {
        this.nome = nome;
        this.documento = documento;
        this.tipoCliente = tipoCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", Documento='" + documento + '\'' +
                ", tipoCliente='" + tipoCliente + '\'' +
                '}';
    }
}
