package sample;

//Esta es una clase muy basica, con 2 atributos y sus metodos get and set
public class Numero {
    //el primero es la iteracion que se ve en la tabla con el numero aleatorio
    private int iteracion;
    private double numero;

    //este es el contructor, seria la funcion "new"
    public Numero(int iteracion, double numero) {
        this.iteracion = iteracion;
        this.numero = numero;
    }

    //meotodos o funciones get y set
    public int getIteracion() {
        return iteracion;
    }

    public void setIteracion(int iteracion) {
        this.iteracion = iteracion;
    }

    public double getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
