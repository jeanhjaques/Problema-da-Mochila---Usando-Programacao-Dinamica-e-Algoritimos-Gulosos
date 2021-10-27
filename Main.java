import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main{
	public static void main(String[] args) throws IOException{

		File arquivo = new File( "./resultado.csv");

		arquivo.createNewFile();

		if(arquivo.exists()){
			arquivo.delete();
		}

		FileWriter fw = new	FileWriter(arquivo, true);
		BufferedWriter bw = new	BufferedWriter(fw);

		//prepara cabeçalho para a planilha
		String cabecalho = "W, n, bin, tf, tb";

		bw.write(cabecalho);

		Scanner ler = new Scanner(System.in);
		System.out.println("Entre com o tamanho máximo da mochila: ");
		int max = ler.nextInt();



		System.out.println("   W       |    n   |     bin    |      tf     |     tb    |");
		for(int W=10;W<=max; W = W + 5){

			//determina o tamanho do vetor que armazena os n
			int tamanhoVetorN = 0;
			for(int i=W/2;i<=W*2; i= i+5){
				tamanhoVetorN++;
			}
			
			//constrói vetor de n
			int[] n = new int[tamanhoVetorN];
			int iterador = 0;
			for(int i=W/2;i<=W*2; i= i+5){
				n[iterador] = i;
				iterador++;
			}


			for(int i=0; i<n.length; i++){
				int[] v = new int[n[i]]; //vetor de valores
				int[] w = new int[n[i]]; //vetor de pesos

				//preenche vetor de valores(v) e de pesos(w)
				Random random = new Random();
				for(int j=0; j<v.length; j++){
					v[j] = random.nextInt(200);
					w[j] = random.nextInt(W-1)+1;
				}

				//Testa usando mochila binaria
	            long tempoInicial = System.nanoTime();
	            double[][] mochilaBinaria = ProblemaMochila.bynaryKnapsack(v, w, W);
	            long tempoFinal = System.nanoTime();

	            long tempoMochilaBinaria = tempoFinal - tempoInicial;
	            tempoInicial = 0;
	            tempoFinal = 0;

	            String tempoMochilaBinariaFormatado = String.format("%.6f",(double)tempoMochilaBinaria/1000000000);
	            double valorMochilaBinaria = mochilaBinaria[v.length-1][W-1];

	            //Testa usando mochila fracionaria
	           	tempoInicial = System.nanoTime();
	            double[] mochilaFracionaria = ProblemaMochila.fractionalKnapsack(v, w, W);
	            tempoFinal = System.nanoTime();

	            long tempoMochilaFracionaria = tempoFinal - tempoInicial;
	            tempoInicial = 0;
	            tempoFinal = 0;

	           	String tempoMochilaFracionariaFormatado = String.format("%.6f",(double)tempoMochilaFracionaria/1000000000);


	            //imprime resultados
				System.out.println("  "+W+"            "+n[i]+"        "+valorMochilaBinaria+"     "+tempoMochilaFracionariaFormatado+"      "+tempoMochilaBinariaFormatado);

				String bin = String.valueOf(valorMochilaBinaria).replace("." , ",");

				bw.newLine();
				bw.write(W+","+n[i]+",\""+bin+"\",\""+tempoMochilaFracionariaFormatado+"\",\""+tempoMochilaBinariaFormatado+"\"");

				
			}


		}

		bw.close();
		fw.close();
		
		System.out.println("Um arquivo resultado.csv com o resultado desta execução agora pode ser encontrado na raiz do programa.");
	}
}