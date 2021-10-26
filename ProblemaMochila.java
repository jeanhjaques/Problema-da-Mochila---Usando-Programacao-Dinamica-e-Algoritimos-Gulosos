public class ProblemaMochila{
	public static double[] fractionalKnapsack(int[] valor, int[] peso, int pesoMaximo){
		//ordenando os vetores valor e peso pelo seu preço por quilo
		//optamos pelo bubbleSort
		for(int i = 0; i<valor.length; i++){
			for(int j = 0; j<valor.length - 1; j++){
				System.out.println(j);
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

		int i = 0;
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
}