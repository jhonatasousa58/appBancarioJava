import clientes.Cliente;
import clientes.PessoaFisica;
import clientes.PessoaJuridica;
import contas.Conta;
import contas.ContaCorrente;
import contas.ContaInvestimento;
import contas.ContaPoupanca;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Aplicacao {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Integer op;
//        List<Cliente> clientes = new ArrayList<>();
        Cliente cliente = null;
        Conta conta;
        List<Conta> contasCliente = new ArrayList<>();
        String tipoConta = "";

        System.out.println("Bem vindo ao seu Banco!");
        do {
            System.out.println("Digite uma das opções abaixo: ");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Abrir Conta");
            System.out.println("3 - Depositar");
            System.out.println("4 - Sacar");
            System.out.println("5 - Transferência");
            System.out.println("6 - Investir");
            System.out.println("7 - Consultar Saldo");
            System.out.println("0 - Sair");

            // escolher opcao
            op = scanner.nextInt();
            scanner.nextLine();

            switch (op) {
                case 1:
                    if (cliente == null) {
                        cliente = cadastrarCLiente();
                    } else {
                        System.out.println("Cliente já cadastrado!");
                    }
                    freeze();
                    break;
                case 2:
                    if (cliente != null) {
                        conta = criarConta(cliente);
                        contasCliente.add(conta);
                    } else {
                        System.out.println("Cliente não cadastrado!");
                    }
                    freeze();
                    break;
                case 3:
                    System.out.println("Qual o tipo de conta em que deseja depositar: \nCC - Conta Corrente \nCP - Conta Poupança \nCI - Conta Investimento");
                    tipoConta = scanner.nextLine();
                    conta = selecionaConta(contasCliente, tipoConta);
                    if (conta != null) {
                        System.out.println("Qual o valor que deseja depositar: ");
                        Double valor = scanner.nextDouble();
                        scanner.nextLine();
                        conta.depositar(valor);
                    } else {
                        System.out.println("Conta não encontrada!");
                    }
                    freeze();
                    break;
                case 4:
                    System.out.println("Qual o tipo de conta em que deseja sacar: \nCC - Conta Corrente \nCP - Conta Poupança \nCI - Conta Investimento");
                    tipoConta = scanner.nextLine();
                    conta = selecionaConta(contasCliente, tipoConta);
                    boolean f;
                    if (conta != null) {
                        System.out.println("Qual o valor que deseja sacar: ");
                        Double valor = scanner.nextDouble();
                        scanner.nextLine();
                        if (cliente.getTipoCliente().toLowerCase().equals("pj")) {
                            f = conta.sacar(valor, cliente.getTipoCliente());
                        } else {
                            f = conta.sacar(valor);
                        }

                        if (f) {
                            System.out.println("Saldo apos o saque: " + conta.getSaldo());
                        } else {
                            System.out.println("Saldo Insuficiente");
                        }
                    } else {
                        System.out.println("Conta não encontrada!");
                    }
                    freeze();
                    break;
                case 5:
                    System.out.println("Qual a conta de onde deseja transferir: \nCC - Conta Corrente \nCP - Conta Poupança \nCI - Conta Investimento");
                    tipoConta = scanner.nextLine();
                    Conta contaOrigem = selecionaConta(contasCliente, tipoConta);
                    System.out.println("Digite o numero da conta para qual deseja transferir: ");
                    Integer nc = scanner.nextInt();
                    scanner.nextLine();
                    conta = buscaContaNumero(contasCliente, nc);
                    boolean fl;
                    if (conta != null) {
                        System.out.println("Qual o valor que deseja transferir: ");
                        Double valor = scanner.nextDouble();
                        scanner.nextLine();

                        if (cliente.getTipoCliente().toLowerCase().equals("pj")) {
                            fl = conta.transferir(contaOrigem, conta, valor, cliente.getTipoCliente());
                        } else {
                            fl = conta.transferir(contaOrigem, conta, valor);
                        }

                        if (fl) {
                            System.out.println("Saldo apos a transferencia: " + conta.getSaldo());
                        } else {
                            System.out.println("Saldo Insuficiente");
                        }
                    } else {
                        System.out.println("Conta não encontrada!");
                    }
                    freeze();
                    break;
                case 6:
                    conta = selecionaConta(contasCliente, "ci");
                    if (conta != null) {
                        System.out.println("Qual o valor que deseja investir: ");
                        Double valor = scanner.nextDouble();
                        scanner.nextLine();
                        conta.investir(valor, conta.getTipoConta());
                        System.out.println("Saldo apos o investimento: " + conta.getSaldo());
                    } else {
                        System.out.println("Conta não encontrada!");
                    }
                    freeze();
                    break;
                case 7:
                    System.out.println("Qual o tipo de conta em que deseja consultar o saldo: \nCC - Conta Corrente \nCP - Conta Poupança \nCI - Conta Investimento");
                    tipoConta = scanner.nextLine();
                    conta = selecionaConta(contasCliente, tipoConta);
                    System.out.println("Seu saldo na conta é de: " + conta.getSaldo());
                    freeze();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }

        } while (op != 0);
    }

    static public Cliente cadastrarCLiente() {
        Cliente cliente;
        String nome = "", tipoCliente = "", cpfCnpj = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite seu nome: ");
        nome = scanner.nextLine();
        System.out.println("Digite o tipo de cliente (PF ou PJ): ");
        tipoCliente = scanner.nextLine();
        System.out.println("Digite o CPF/CNPJ: ");
        cpfCnpj = scanner.nextLine();

        if (tipoCliente.toLowerCase().equals("pf")) {
            cliente = new PessoaFisica(nome, cpfCnpj, tipoCliente);
        } else {
            cliente = new PessoaJuridica(nome, cpfCnpj, tipoCliente);
        }

        return cliente;
    }

    static public Conta criarConta(Cliente cliente) {
        Conta conta = null;
        Integer random = new Random().nextInt(10000000);
        Scanner scanner = new Scanner(System.in);

        if (cliente.getTipoCliente().toLowerCase().equals("pf")) {
            System.out.println("Qual o tipo de conta: \nCC - Conta Corrente \nCP - Conta Poupança \nCI - Conta Investimento");
        } else {
            System.out.println("Qual o tipo de conta: \nCC - Conta Corrente \nCI - Conta Investimento");
        }

        String op2 = scanner.nextLine();
        switch (op2.toLowerCase()) {
            case "cc":
                conta = new ContaCorrente();
                conta.setTipoConta("cc");
                break;
            case "cp":
                conta = new ContaPoupanca();
                conta.setTipoConta("cp");
                break;
            case "ci":
                conta = new ContaInvestimento();
                conta.setTipoConta("ci");
                break;
            default:
                System.out.println("Tipo nao disponivel!");
                break;
        }

        conta.setNumeroConta(random);
        conta.setCliente(cliente);
        conta.setSaldo(0.0);

        System.out.println(conta.toString());

        return conta;
    }

    static public Conta selecionaConta(List<Conta> contas, String tipoConta) {
        for (Conta conta : contas) {
            if (conta.getTipoConta().equals(tipoConta.toLowerCase())) {
                return conta;
            }
        }
        return null;
    }

    static public Conta buscaContaNumero(List<Conta> contas, Integer numeroConta) {
        for (Conta conta : contas) {
            if (conta.getNumeroConta().equals(numeroConta)) {
                return conta;
            }
        }
        return null;
    }

    static public void freeze() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Tecle enter para voltar ao menu");
        scanner.nextLine();
    }

}
