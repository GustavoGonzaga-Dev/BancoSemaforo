package controller;


public class ThreadBanco extends Thread{
	private int codConta;
	private int tipoTransicao;
	private double valorTransicao;
	private double saldoConta;
	private double saldoAtual;
	
	public ThreadBanco(int codConta, double saldoConta, double valorTransicao, int tipoTransicao) {
		this.codConta = codConta;
		this.saldoConta = saldoConta;
		this.valorTransicao = valorTransicao;
		this.tipoTransicao = tipoTransicao;
	}
	
	@Override
	public void run() {
		try {
			if(tipoTransicao == 2) {
				Deposito();
			}else {
				Saque();
			}
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void Saque() {
		if(valorTransicao > saldoConta) {
			System.out.println("Seu saldo atual: R$" + saldoConta + "\n Valor de saque requirido: R$" + valorTransicao);
			System.out.println("Valor requirido não existe na sua conta, operação negada");
		}else {
			System.out.println("Seu saldo atual: R$" + saldoConta + "\n Valor de saque requirido: R$" + valorTransicao);
			saldoAtual = saldoConta - valorTransicao;
			System.out.println("valor permitido, saque: R$" + valorTransicao + "\n Saldo atual: R$" + saldoAtual);
		}
	}
	
	public void Deposito() {
		System.out.println("Seu saldo atual: R$" + saldoConta + "\n Valor de deposito: R$" + valorTransicao);
		saldoConta = saldoConta + valorTransicao;
		System.out.println("Saldo atual: R$" + saldoConta);
	}

}
