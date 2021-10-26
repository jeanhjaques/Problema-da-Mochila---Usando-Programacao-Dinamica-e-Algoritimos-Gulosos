import java.util.Arrays;

public class Main{
	public static void main(String[] args){
		int [] valor = {100, 30, 20, 50, 20, 20, 50};
		int [] peso = {15, 10, 10, 10, 30, 10, 10};

		for(int i = 0; i<valor.length; i++){
			double precoPorKg = (double)valor[i]/(double)peso[i];
			System.out.println(i+"- "+valor[i]+" "+peso[i]+" = "+precoPorKg);
		}

		double[] mochila = ProblemaMochila.fractionalKnapsack(valor, peso, 49);

		System.out.println (Arrays.toString(mochila));

	}
}