package sample;

//Esta es una clase Intervalo que haria referencia a una fila de la tabla de chi cuadrado, tiene 6 atributos y sus metodos get and set
public class Intervalo {
    //cada atributo hace referencia a una columna de la tabla
    private double desde;
    private double hasta;
    private int frecObservada;
    private double frecEsperada;
    private double C;
    private double cAcumulado;


    //contructor
    public Intervalo(double desde, double hasta, int frecObservada, double frecEsperada, double C, double cAcumulado) {
        this.desde = desde;
        this.hasta = hasta;
        this.frecObservada = frecObservada;
        this.frecEsperada = frecEsperada;
        this.C = C;
        this.cAcumulado = cAcumulado;
    }

    //Este metodo lo que hace es incrementar en 1 la frecuencia observada. Es decir, cuando encuentre un n° aleatorio
    //que pertenezca a este interalo, voy a sumarle 1 a la frecuencia observada
    public void incrementarFrecuenciaObservada() {
        int frecuenciaAnterior = this.getFrecObservada();
        this.setFrecObservada(frecuenciaAnterior+1);

    }

    //Este metodo calcula c en base a la frecuencia observada y la esperada para el intervalo actual
    public double calcularC() {
        double c = Math.round((Math.pow((this.frecObservada - this.frecEsperada),2)/this.frecEsperada)*10000d)/10000d;

        //Setteamos el valor de c que calculamos
        this.setC(c);

        return c;


    }

    //metodos get y set

    //GET y SET del atributo desde
    public double getDesde() {
        return desde;
    }

    public void setDesde(double limInferior) {
        this.desde = limInferior;
    }

    //GET y SET del atributo hasta
    public double getHasta() {
        return hasta;
    }

    public void setHasta(double limSuperior) {
        this.hasta = limSuperior;
    }

    //GET y SET del atributo frecObservada
    public int getFrecObservada() {
        return frecObservada;
    }

    public void setFrecObservada(int frecObservada) {
        this.frecObservada = frecObservada;
    }


    //GET y SET del atributo frecEsperada
    public double getFrecEsperada() {
        return frecEsperada;
    }

    public void setFrecEsperada(double frecEsperada) {
        this.frecEsperada = frecEsperada;
    }

    //GET y SET del atributo C
    public double getC() {
        return C;
    }

    public void setC(double C) {
        this.C = C;
    }

    //GET y SET del atributo cAcumulado
    public double getCAcumulado() {
        return cAcumulado;
    }

    public void setCAcumulado(double cAcumulado) {
        this.cAcumulado = cAcumulado;
    }
}
