package controller;

import java.text.DecimalFormat;
import java.util.concurrent.Semaphore;

public class ThreadBanco extends Thread {
	private int codConta;
	private int tipoTransicao;
	private double valorTransicao;
	private double saldoConta;
	private double saldoAtual;
	private Semaphore saque;
	private Semaphore deposito;
	private String formato = "0.00";
    private DecimalFormat d = new DecimalFormat(formato);

	public ThreadBanco(int codConta, double saldoConta, double valorTransicao, int tipoTransicao, Semaphore saque, Semaphore deposito) {
		this.codConta = codConta;
		this.saldoConta = saldoConta;
		this.valorTransicao = valorTransicao;
		this.tipoTransicao = tipoTransicao;
		this.saque = saque;
		this.deposito = deposito;
	}

	@Override
	public void run() {
		if (tipoTransicao == 1) {
			try {
				saque.acquire();
				Saque();
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				saque.release();
			}
		} else {
			try {
				deposito.acquire();
				Deposito();
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				deposito.release();
			}
		}
	}
	
	public void Saque() {
		if (valorTransicao > saldoConta) {
			System.out.println("Conta numero: "+codConta+"# Seu saldo atual: R$" + d.format(saldoConta) + "\n Valor de saque requirido: R$" + d.format(valorTransicao));
			System.out.println("Valor requirido não existe na sua conta, operação negada");
		} else {
			System.out.println("Conta numero: "+codConta+"# Seu saldo atual: R$" + d.format(saldoConta) + "\n Valor de saque requirido: R$" + d.format(valorTransicao));
			saldoAtual = saldoConta - valorTransicao;
			System.out.println("valor permitido, saque: R$" + d.format(valorTransicao) + "\n Saldo atual: R$" + d.format(saldoAtual));
		}
	}

	public void Deposito() {
		System.out.println("Conta numero: "+codConta+"# Seu saldo atual: R$" + d.format(saldoConta) + "\n Valor de deposito: R$" + d.format(valorTransicao));
		saldoConta = saldoConta + valorTransicao;
		System.out.println("Saldo atual: R$" + d.format(saldoConta));
	}

}
