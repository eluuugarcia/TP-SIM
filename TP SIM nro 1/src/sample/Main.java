package sample;
//importaciones que permiten mostrar la venta o interfaz grafica
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//Este el metodo principial seria como una funcion test en phyton pero como es una interfaz grafica no se usa
//la palabra public significa ela clase "Main" puede ser usada par cualquiera
//la palabra class significa que es una clase si dice otra clase es una funcion, y todas las funciones -
//tienen que estar en una clase, las funciones son como los metodos.
//que extienda (extends) de Application es que va a permitir abrir la ventana
public class Main extends Application {
    //override significa qus sobreescribe una funcion que ya venia con java
    @Override
    //start es una funcion va abrir la ventana
    //como todas las funciones enpiezan por una palabra public o private, public que puede ser usada por otras -
    //clases private es que es una funcion que solo puede ser usado por la misma clase en este caso main
    //lo que esta entre parentesis son los parametros de una funcion, por lo general son enteros o cadenas -
    // de palabras pero en esta funcion no se lo que es, cuando cree el proyecto venia asi
    // throws Exception significa que puede lanzar un error
    public void start(Stage primaryStage) throws Exception{
        // lo siguiente dice donde esta la pantalla a abrir, como se llama el archivo a abrir
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        // Este el titulo de la ventana
        primaryStage.setTitle("Generador de numeros");
        // y estos son los tamaños
        primaryStage.setScene(new Scene(root, 1500, 700));
        // esta funcion le dice a java que mueste  ventana con el titulo y el tamaño elegido
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
