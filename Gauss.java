import java.util.*;

class Gauss{
	
	public static void main (String [] main){
		
		//Clase que resolvera el sistema de ecuaciones por Gauss Jordan
		__gauss _gauss = new __gauss();
		
		//Clase para obtener inputs
		Scanner leer = new Scanner(System.in);
		
		System.out.print("Dame x: ");
		//Número hasta el cual sumar
		int x = leer.nextInt();
		//Exponente de la suma de Gauss
		int m = 0;
		//El exponente debe ser mayor a 0
		do{
			System.out.print("Dame m: ");
			m = leer.nextInt();
		} while(m < 0);
		
		System.out.println();
		
		/*
		Según lo que descubrió Gauss
		La formula para cualquier suma de Gauss sera un exponente más alto al de la suma, es decir:
		  1+2+3+4+5+...+99+100 = 5050 
		Esta suma es de grado 1 por lo que la formula sera de grado 2, es decir:
		  Ax^2 + Bx + C = 5050   Donde x es el valor al cual llega la suma, osea 100
		Para este ejemplo: x=100 y m=1
		
		Si queremos una suma de gauss de grado 2:
		  1+4+9+16+...+10000 = 338350
		La formula seria:
		  Ax^3 + Bx^2 + Cx + D = 338350   Donde x es el valor al cual llega la suma, osea 100
		Para este ejemplo: x=100 y m=2
		 
		Las letras son constantes que se calcularan con este programa resolviendo el sistema de ecuaciones
		*/
		
		/*
		Dado que la formula sera de grado m+1, significa que existiran m+2 constantes a calcular
		Osea, se tendra que resolver un sistema de ecuaciones de m+2 x m+2
		
		Tendremos que crear una matriz de dichas dimensiones pero a las columnas deveremos agregar un espacio
		esta ultima columna es del resultado, es decir:
		
		(Ax^m+1) + (Bx^m) + (Cx^m-1) + ... + (Zx^0) = resultado
		\-------------------m+2--------------------/  \-- 1 --/
		*/
		
		double[/*Columnas*/][/*Filas*/] arr = new double[m+3][m+2];
		
		/*
		Para llenar esta matriz pondre un ejempo.
		
		Como ejemplo: 1+2+3+...+100 = 5050
		Su formula es: A(100^2) + B(100) + C = 5050
		Tenemos tres variables, para encontrarlas necesitamos tres formulas:
		                            A cada una le asignaremos un valor a x distinto
		Ax^2 + Bx + C = resultado    x=1
		Ax^2 + Bx + C = resultado    x=2
		Ax^2 + Bx + C = resultado    x=3
		
		Al final nos quedarian:
		
		  x=1    A + B + C = 1    
		  x=2    4A + 2B + C = 3
		  x=3    9A + 3B + C = 6
		*/
		
		//Cada fila representa una ecuación
		//Aqui nosotros definimos los valores que habra en cada fila (i)
		for(int i=0;i<m+2;i++){
			//Aqui nosotros definimos los valores de x en cada columna (j) de la fila (i)
			for(int j=0;j<m+2;j++){
				arr[j][i] = Math.pow((i+1),(m+1-j));
			}
			//Aqui nosotros definimos el resultado que ira en la ultima columna
			double suma = 0;
			//Hacemos la suma desde 1 hasta x con la potencia m
			for(int j=0;j<i+1;j++){
				suma += Math.pow((j+1),m);
			}
			arr[m+2][i] = suma;
		}
		
		//Este arreglo guardara unicamente los valores de cada variable
		double arr2[] = new double[m+2];
		
		//La funcion calc() resolvera el sistema de ecuaciones y nos devolvera la matris resuelta
		arr = _gauss.calc(arr,m);
		
		/*De la matriz devuelta, el valor de cada variable se encuentra en la columna de resultado
		//Por lo que:
		  El valor de A estara en la primera fila ultima columna
		  El valor de B estara en la segunda fila ultima columna
		  ...
		*/
		int fila = 0;
		for(int i=0;i<m+2;i++){
			arr2[fila]=arr[m+2][i];
			fila++;
		}
		
		//Ahora imprimiremos dichos valores que acompañan a cada x de la formula
		System.out.println("");
		for(int i=0;i<m+2;i++){
			System.out.println("La constante que acompaña a la x a la potencia " + (m+1-i) + " tiene un valor de " + arr2[i]);
		}
		System.out.println();
		
		//Imprimimos el resultado de la suma de gauss sumando cada número
		_gauss.calcres(m,x);

		System.out.println();
		
		//Obtenemos el resultado de la suma de Gauss usando los valores de cada variable
		double resultado = 0;
		for(int i=0;i<m+2;i++){
			double exp = Math.pow(x, m+1-i);
			resultado += arr2[i]*exp;
		}
		//Imprimimos el resultado de la suma de gauss usando la formula calculada
		System.out.println("El resultado de la sucesion usando la formula es: " + resultado);
	}
}	