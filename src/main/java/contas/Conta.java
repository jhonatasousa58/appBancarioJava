package contas;

import clientes.Cliente;

public class Conta {

    //private String agenciaConta;
    private Integer numeroConta;
    private String tipoConta;
    private Double saldo;

    private Cliente cliente;

    public Integer getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(Integer numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Conta abrirConta() {
        Conta conta = new Conta();
        return conta;
    }

    public boolean sacar(Double valor) {
        //sacar PF
        if(this.saldo >= valor) {
            this.saldo -= valor;
            return true;
        } else {
            return false;
        }
    }

    public boolean sacar(Double valor, String tipoCliente) {
        //sacar PJ
        valor = (valor + (valor*0.005));
        if(this.saldo >= valor) {
            this.saldo -= valor;
            return true;
        } else {
            return false;
        }
    }

    public void depositar(Double valor) {
        //depositar
        this.saldo += valor;
    }

    public boolean transferir(Conta contaOrigem, Conta conta, Double valor) {
        //transferir
        if(contaOrigem.sacar(valor)) {
            conta.depositar(valor);
            return true;
        } else {
            return false;
        }
    }

    public boolean transferir(Conta contaOrigem, Conta conta, Double valor, String tipoCliente) {
        //transferir
        Double val = (valor + (valor*0.005));
        if(contaOrigem.sacar(val)) {
            conta.depositar(valor);
            return true;
        } else {
            return false;
        }
    }

    public void investir(Double valor, String tipoCliente) {
        //investimento
        if (tipoCliente.toLowerCase().equals("pj")) {
            this.saldo += (valor + 1.03);
        } else {
            this.saldo += (valor + 1.01);
        }
    }

    @Override
    public String toString() {
        return "Conta{" +
                "numeroConta=" + numeroConta +
                "\ntipoConta='" + tipoConta + '\'' +
                "\nsaldo=" + saldo +
                '}';
    }
}
