import java.util.*;

public class __gauss{

    public static double[][] calc (double[][] arr,int m){
		
		//Imprimimos en pantalla la matriz
		for(int k=0;k<m+2;k++){
			for(int j=0;j<m+3;j++){
				System.out.print(arr[j][k] + " ");
			}
			System.out.println("");
		} System.out.println("");
		
		/*
		Recordemos que para resolver un sistema de ecuaciones por Gauss Jordan
		lo que se debe hacer es "crear" triangulos de ceros de forma que la matriz
		termine unicamente con una hilera en diagonal de puros unos y en la columna
		de resultados el valor de cada variable.
		Es decir, de la siguiente matriz:
		
		A  B  C  Res
		1  1  1  1
		4  2  1  3
		9  3  1  6
		
		convertirla en:
		
		A  B  C  Res
        1  0  0  0.5
        0  1  0  0.5
        0  0  1  0		
		
		Asi A = 0.5
		Asi B = 0.5
		Asi C = 0
		
		Y nos quedaria la formula como: (0.5)(x^2) + (0.5)(x) + (0) = resultado
		*/
		
		/*
		Criterios de equivalencia de sistemas de ecuaciones:
		
		-> Si a ambos miembros de una ecuación de un sistema se les suma o se les resta una misma expresión, el sistema resultante es equivalente.
		
		-> Si multiplicamos o dividimos ambos miembros de las ecuaciones de un sistema por un número distinto de cero, el sistema resultante es equivalente.
		
		-> Si sumamos o restamos a una ecuación de un sistema otra ecuación del mismo sistema, el sistema resultante es equivalente al dado.
		
		-> Si en un sistema se sustituye una ecuación por otra que resulte de sumar las dos ecuaciones del sistema previamente multiplicadas o divididas por números no nulos, resulta otro sistema equivalente al primero.
		
		-> Si en un sistema se cambia el orden de las ecuaciones o el orden de las incógnitas, resulta otro sistema equivalente.
		*/
		
		//Variable que recorrera las filas del pivote
        int fila = 0;
		//Variable que recorrera las columnas del pivote
		int columna = 0;
		//Bucle que pasara por cada ecuación o fila
		for(int h=0;h<m+2;h++){
			//Bucle que pasara por cada ecuación o fila
			for(int i=0;i<m+2;i++){
				//Mientras la fila en la que estemos sea distinta a la del pivote
				if(i != fila){
					//Variable que obtendra el valor del pivote
					double primernum = arr[columna][fila];
					//Si las celdas son distintas de 0
					if(arr[columna][i] != 0){
						//Variable que obtendra el valor de la primera celda de las demas filas
						double ultimonum = arr[columna][i];
						//A lo largo de cada fila
						for(int columnas=0;columnas<m+3;columnas++){
							
							double multi1;
							double multi2;
							
							//Si primernum es divisible entre ultimonum
							if(primernum%ultimonum == 0){
								//Dejamos toda la fila del pivote como esta
								//Multiplicaremos toda la fila del ultimonum por la división del primernum/ultimonum
								multi1 = arr[columnas][i]*(primernum/ultimonum);
								multi2 = arr[columnas][fila];
							}else{
								if(ultimonum%primernum == 0){
									//Dejamos toda la fila del ultimonum como esta
									//Multiplicaremos toda la fila del pivote por la división del ultimonum/primernum
									multi1 = arr[columnas][i];
									multi2 = arr[columnas][fila]*(ultimonum/primernum);
								}else{
									//Multiplicaremos toda la fila del pivote por el ultimonum
									//Multiplicaremos toda la fila del ultimonum por el pivote
									multi1 = arr[columnas][i]*primernum;
									multi2 = arr[columnas][fila]*ultimonum;
								}
							}
							//Los restamos entre si para crear nuestros 0 en la matriz
							if(multi1-multi2 == 0){
								arr[columnas][i] = 0;
							}else{
								arr[columnas][i] = multi1-multi2;
							}
						}
					}
				}
				//Maximo comun divisor de toda la fila
				double num = arr[0][i];
				for(int columnas=0;columnas<m+2;columnas++){
					num = maximo_comun_divisor(num,arr[columnas+1][i]);
				}
				//Si es distinto de 1, dividimos toda la fila entre ese numero
				if(num!=1){
					for(int columnas=0;columnas<m+3;columnas++){
						arr[columnas][i] /= num;
					}
				}
			}
			fila++;
			columna++;
			
			//Imprimimos en pantalla la matriz
			for(int k=0;k<m+2;k++){
				for(int j=0;j<m+3;j++){
					System.out.print(arr[j][k] + " ");
				}
				System.out.println("");
			} System.out.println("");
		}
		//Una vez se ha resuelta la matriz, falta crear la hilera de unos
		fila = 0;
		columna = 0;
		//Dividiremos toda la fila entre el valor del pivote
		for(int i=0;i<m+2;i++){
			double reducir_a_uno = arr[columna][fila];
			if(arr[m+2][i]!=0){
				arr[m+2][i] /= reducir_a_uno;
			}
			arr[columna][fila] = 1;
			for(int j=0;j<m+3;j++){
				System.out.print(arr[j][i] + " ");
			}
			System.out.println("");
			fila++;
			columna++;
		}
		
        return arr;
    }

    public static void calcres (int m, int n){
		//Calcular suma de gauss de forma rustica, sumando numero por numero
		double res = 1;
		System.out.print("1");
		for(int i=2;i<n+1;i++){
			double mult = Math.pow(i,m);
			System.out.print(" + " + mult);
			res += mult;
		}
		System.out.println(" = " + res);
    }
	
	public static double maximo_comun_divisor(double a,double b){
		//Variable del residuo
	    double r;
		//Variable del resultado
		double res;
		
		/*
		Por propiedades del mcd:
		-> mcd(a,0) = |a|
		-> mcd(0,b) = |b|
		-> mcd(a,1) v mcd(1,b) = 1
		-> mcd(a,b) = mcd(b,r)
		*/
		
		if(a == 0){
			res = Math.abs(b);
		}else{
			if(b == 0){
			res = Math.abs(a);
			}else{
				if(b == 1 || a == 1){
					res = 1;
				}else{
					r = a%b;
					res = maximo_comun_divisor(b,r);
				}
			}
		}
		//Devolvemos el resultado
		return res;
	}
}