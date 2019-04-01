package sample;

import javafx.scene.control.TextField;
import org.omg.CORBA.Object;

//al igual que en main se define la clase como public o private, luego se pone class y su nombre
// aca podes ver, lo tipico de diseño y analis, la clase con sus atributos que estan abajo y depues las -
//funciones que comienzan con public
public class Gestor {
	// Estos son los atributos, todos comienzan con private, luego se pone su tipo y nombre. Son variables en realidad
	//el unico que es raro es l primero el controller que es el controlador de la pantalla, esto se hace-
	//para que esten relacionadas
	private Controller pantalla;
	//double significa que es decimal, los corchetes al lado es que un vector, osea que la variable num -
	// es un vector de decimales, es el vector de los numeros aleatorios
	private double[] num = new double[0];
	private Intervalo[] intervalos = new Intervalo[0];
	//estas son las variables que nos sirven para hacer los calculos, int significa que son enteros
	private int a, m, c, M, xSig;

	//en esta funcion la palabra void significa que no retorna nada, al finalizar solo llama a otra funcion
	//tambien le podes ver los parametros, el primero es el controlador de la pantalla, y los siguientes-
	//son los numeros necesarios para las operaciones
	public void generarNumeros(Controller pantalla, int x0, int k, int g, int c, int opcion, int iteraciones) {
		//como esta funcion en realidad la llama el controlador de la pantalla, le pasa el gestor su direccion-
		// para que lo guarde y la llame cuando la necesite, es decir que el renglon de abajo, solo vincula-
		//el gestor y el conrolador
		this.pantalla = pantalla;
		//calculo de a y m
		if(opcion == 11 || opcion == 3){
			a = 1+ 4*k;
		} else{
			a = 3+ 8*k;
		}

		//MAth.pow es potencia, osea 2^g
		m = (int) Math.pow(2,g);
		//esto guarda el parametro c que venia en la funcion en el parametro o variable de la clase

		this.c = c;
		//esto es un llamado a la funcion que calcula el primer xi
		calcularXi(x0,a,m,c);

		//hacemos un ciclo for para ir calculando los siguientes xi
		//la cantidad de iteraciones se pasa por parametro (si estamos en el punto A generamos 20, y sino las que ingrese el usuario)
		for(int i = 1; i < iteraciones; i++) {
			calcularXi(xSig, a, m, c);
		}
		//num contiene todos los numeros aleatorios que se envian a la pantalla, ademas de a y m para que se muestre
		this.pantalla.mostrarNumeros(num, a, m);
	}



	//Funcion que calcula el proximo numero aleatorio y llama a la pantalla para que lo muestre
	public void next() {
		calcularXi(xSig,a,m,c);
		this.pantalla.llenarColumnas(num);
	}

	//Funcion para agregar un numero al vector (recibe como parametro un numero decimal)
	public void agregarNumero(double rnd) {
		//aca creo un vector de decimal(double) auxiliar, por que en java tenemos el problema que los
		//vectores tienen cierto tamaño, para agregar otro numero a un vector lleno, tengo que crear otro vector-
		//mas grande(lo hice 1 mas grande)
		double[] aux = new double[num.length+1];
		//copio todos los valores del viejo vector al nuevo con un ciclo for
		for(int i = 0; i < num.length; i++)
			aux[i] = num[i];
		//y aca pongo el nuevo numero al final de todo
		aux[num.length]= rnd;
		// depues a este vector auxliar nuevo lo pongo en la direccion del orignal, como si nunca hubiera -
		//existido ese vector
		num = aux;
	}

	//Funcion para calcular ell proximo xi (xi+1)
	public void calcularXi(int xi, int a, int m, int c){
		//esta inicializacion la hago por que en java todas las variables le tenes que poner que va contener
		double rnd = 0;
		//estas son las formulas que nos dio el profe
		int n = a * xi + c;
		// % (porcentaje) signifia resto
		int xSig = n % m;

		rnd = (double) xSig / (m);

		//aca agrego al numero aleatorio en el vector
		//round es para redondear, 1000d significa que lo redondea a 3 decimales
		rnd= (double)((int)(rnd*10000)/10000.0);

		agregarNumero(rnd);
		//aca guardo el ultimo x+1 para usarlo recursivamente
		this.xSig = xSig;
	}

	public double[] armarIntervalos(int per) {
		double p = (double)1/per;
		double[] limSup = new double[per];
		double n = 0;
		for(int i=0; i < limSup.length; i++){
			n = Math.round((n+p)*1000d)/1000d;
			limSup[i] = n;
		}
		return limSup;

	}

	public int[] llenarIntervalos(double[] intervalos) {
		int[] numPer = new int[intervalos.length];
		for (int i = 0; i < num.length; i++) {
			for (int j = 0; j < intervalos.length; j++) {
				if (num[i] < intervalos[j]) {
					numPer[j] += 1;
					break;
				}
			}
		}

		return numPer;
	}

	public Intervalo[] armarIntervalosChiCuadrado(int per, int M) {
		//La variable p es la misma que uso Gastón en el armarIntervalos() del punto A, nos dice cada cuánto hay un intervalo
		//por ejemplo si son 10 intervalos, p = 1/10 osea 0,1, quiere decir que los intervalos van a ser
		// 0-0.1, 0.1-0.2, 0.2,0.3 ... y asi sucesivamente
		double p = (double)1/per;

		//La frecuencia esperada es la misma para todos los intervalos asi que la calculo solamente acá
		//es el n° de muestras dividido la cantidad de intervalos
		double frecEsperada = (float)M/per;

		//El limite inferior del 1er intervalo va a ser siempre 0, y el superior es igual al valor de p que calculamos
		//ej: si p es 0,1 el primer intervalo va a ser entre 0 y 0.1, para los demas intervalos irán cambiando esos valores
		double limInf = 0;
		double limSup = p;

		//Creo un vector (llamado intervalos) que va a contener objetos de la clase Intervalo. La cantidad a obtener
		//sera igual al n° de intervalos que tenga, osea si tengo 20 intervalos creo un vector de tamaño 20 (lenght = 20)
		Intervalo intervalos[]=new Intervalo[per];

		//Creo un objeto de la clase Intervalo, para eso debo pasarle los atributos necesarios que estan entre parentesis
		//El objeto que creo corresponde al primero intervalo osea que el limite inferior sera siempre 0, y p el superior
		//Tambien le paso la frecuencia esperada. Los demas valores los pongo en 0 porque aún no los conozco
		Intervalo primerIntervalo = new Intervalo(limInf, limSup, 0, frecEsperada, 0, 0);

		//Agrego el obtejo Intervalo creado al vector que contendrá todos los intervalos. Lo agrego en la posicion 0, ya que es el 1ro
		intervalos[0] = primerIntervalo;

		//Hago un ciclo for de tantas iteraciones como intervalos necesite (lenght del vector de intervalos que creamos antes)
		//En este ciclo for voy a ir calculando el limite superior e inferior de cada intervalo

		for(int i=1; i < intervalos.length; i++){
			//El limite inferior del proximo intervalo sera igual al limite inferior del intervalo anterior (osea si el intervalo anterior
			//era (0.2;0.3) entonces el limite inferior del intervalo que siga sera 0.3 y el superior 0.4
			limInf = limSup;
			limSup = Math.round((limInf+p)*1000d)/1000d;

			//Creo un nuevo objeto de la clase Intervalo, pasandole los limites inferior y superior calculados
			Intervalo intervalo = new Intervalo(limInf, limSup, 0, frecEsperada, 0, 0);

			//Agrego el objeto Intervalo que acabamos de crear al vector que contiene todos los intervalos
			//Lo agrego en la posicion i (osea si voy por la iteracion 5 agrego el Intervalo en la posicion 5 del vector)
			intervalos[i] = intervalo;

		}

		this.intervalos = intervalos;

		//retorno el vector que contiene todos los intervalos
		return intervalos;

	}

	//Este metodo toma todos los n° aleatorios (que estan en el vector num) y para cada uno de ellos comprueba a qué
	//intervalo pertenece (de los intervalos que se tienen en el vector "intervalos")
	public void calcularFrecuenciaObservada(Intervalo[] intervalos) {

		//Hago un 1er ciclo for que recorre todos los n° aleatorios
		for (int i = 0; i < num.length; i++) {

			//Dentro del ciclo anterior, recorro todos los intervalos existentes hasta encontrar a cuál pertenece el n°
			//aleatorio actual (osea num[i] )
			for (int j = 0; j < intervalos.length; j++) {
				if (num[i] < intervalos[j].getHasta()) {
					//Si el n° aleatorio es menor que el limite superior del Intervalo entonces aumento la frecuencia
					//observada de ese Intervalo
					intervalos[j].incrementarFrecuenciaObservada();
					//Si ya encontré el intervalo al cual pertenece ese n° hago un break (corte) ya que no tiene
					//sentido seguir recorriendo los demas intervalos.
					break;
				}
			}
		}

	}

	public double chiCuadradoTabulado(int gradosDeLib){
		double chiCuadrado = 0;
		switch (gradosDeLib) {
			case 1:  chiCuadrado = 3.8;
				break;
			case 2:  chiCuadrado = 6;
				break;
			case 3:  chiCuadrado = 7.8;
				break;
			case 4:  chiCuadrado = 9.5;
				break;
			case 5:  chiCuadrado = 11.1;
				break;
			case 6:  chiCuadrado = 12.6;
				break;
			case 7:  chiCuadrado = 14.1;
				break;
			case 8:  chiCuadrado = 15.5;
				break;
			case 9:  chiCuadrado = 16.9;
				break;
			case 10: chiCuadrado = 18.3;
				break;
			case 11: chiCuadrado = 19.7;
				break;
			case 12: chiCuadrado = 21;
				break;
			case 13: chiCuadrado = 22.4;
				break;
			case 14: chiCuadrado = 23.7;
				break;
			case 15: chiCuadrado = 25;
				break;
			case 16: chiCuadrado = 26.3;
				break;
			case 17: chiCuadrado = 27.6;
				break;
			case 18: chiCuadrado = 28.9;
				break;
			case 19: chiCuadrado = 30.1;
				break;
			case 20: chiCuadrado = 31.4;
				break;
			case 21: chiCuadrado = 32.7;
				break;
			case 22: chiCuadrado = 33.9;
				break;
			case 23: chiCuadrado = 35.2;
				break;
			case 24: chiCuadrado = 36.4;
				break;
			case 25: chiCuadrado = 37.7;
				break;
			case 26: chiCuadrado = 38.9;
				break;
			case 27: chiCuadrado = 40.1;
				break;
			case 28: chiCuadrado = 41.3;
				break;
			case 29: chiCuadrado = 42.6;
				break;
			case 30: chiCuadrado = 43.8;
				break;
		}

		return chiCuadrado;
	}

	public double calcularC(Intervalo[] intervalos) {

		double cAcumulado = 0;
		for (int i = 0; i < intervalos.length; i++) {
			double c = intervalos[i].calcularC();
			cAcumulado = Math.round((cAcumulado+c)*10000d)/10000d;
			intervalos[i].setCAcumulado(cAcumulado);

		}
		return cAcumulado;
	}

	//estos son los metodos gets y sets, es decir ver y modificar, los atributos
	public Controller getPantalla() {return pantalla; }

	public void setPantalla(Controller pantalla) { this.pantalla = pantalla; }

	public double[] getNum() { return num; }

	public Intervalo[] getIntervalos() { return intervalos; }

	public void setNum(double[] num) { this.num = num; }

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

	public int getxSig() {
		return xSig;
	}

	public void setxSig(int xSig) {
		this.xSig = xSig;
	}
}
