import java.lang.Math;

public class ProblemaMochila{
	//recebe um vetor de valoreso, um vetor de pesos e o peso maximo da mochila
	//retorna um vetor mostrando quais itens foram selecionados.
	public static double[] fractionalKnapsack(int[] valor, int[] peso, double pesoMaximo){
		//ordenando os vetores valor e peso pelo seu preço por quilo
		//optamos pelo bubbleSort
		for(int i = 0; i<valor.length; i++){
			for(int j = 0; j<valor.length - 1; j++){
				if(((double)valor[j]/(double)peso[j]) > ((double)valor[j+1]/(double)peso[j+1])){
					int aux = valor[j];
                    valor[j] = valor[j + 1];
                    valor[j + 1] = aux;
                    aux = peso[j];
                    peso[j] = peso[j + 1];
                    peso[j + 1] = aux;
				}
			}
		}

		int i = 1;
		double[] mochila = new double[10];

		while (peso[i] <= pesoMaximo){
			mochila[i] = 1;
			pesoMaximo = pesoMaximo - peso[i];
			i = i + 1;
		}

		if(pesoMaximo > 0){
			mochila[i] = pesoMaximo/peso[i];
		}

		return mochila;
	}

	//recebe um vetor de valores, um vetor de pesos e o peso maximo da mochila
	//retorna uma matriz mostando o caminho percorrido até chegar no resultado
	public static double[][] bynaryKnapsack(int[] valor, int[] peso, int pesoMaximo){
		int n = valor.length;
		double[][] mochila = new double[n+1][pesoMaximo+1];

		for(int i=0; i<n; i++){
			mochila[i][0] = 0;
		}
		for(int j=1;j<pesoMaximo;j++){
			mochila[0][j] = 0;
		}

		for(int i=1; i<n; i++){
			for(int j=1; j<pesoMaximo;j++){
				if(j<peso[i]){
					mochila[i][j] = mochila[i-1][j];
				}
				else{
					mochila[i][j] = Math.max(mochila[i-1][j], mochila[i-1][j-peso[i]]+valor[i]);
				}
			}
		}

		/*
		for(double[] row : mochila) {
            printRow(row);
        }
        */

		return mochila;
	}

	//auxilia na impressão da matriz da saída do algoritmo  da mochila binária
	public static void printRow(double[] row) {
        for (double i : row) {
            System.out.print(i);
            System.out.print("\t");
        }
        System.out.println();
    }
}  