package view;

import java.util.Random;
import java.util.concurrent.Semaphore;

import controller.ThreadBanco;

public class Principal {
	public static void main (String[] args) {
		int permissoes = 1, CodConta, TipoTransicao;
		double SaldoConta, ValorTransicao;
		Semaphore semaforo = new Semaphore(permissoes);
		Random random = new Random();
		
		for(int i =0; i<20; i++) {
			CodConta = random.nextInt(100);
			SaldoConta = random.nextDouble()*5000 +1000;
			ValorTransicao = random.nextDouble()*5000 +1000;
			TipoTransicao = random.nextInt(2)+1;
			Thread Banco = new ThreadBanco(CodConta, SaldoConta, ValorTransicao, TipoTransicao);
			Banco.start();
		}
	}

}
