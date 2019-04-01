package sample;
//la clase de arriba package sample significa que esta guerdado en el paquete sample
//las de abajo son importaciones, para que funcionen los botones

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

//cotroller es tambien una clase(class) y publica(que todas las clases las pueden ver)
public class Controller {


    //este es el unico atributo privado de la clase, el gestor con el que se comunica
    private Gestor gestor;
    private int opcion;
    //estos son los nombres de todos los botones, oraciones, campos, y la tabla
    //label son las oraciones o caracteres, textos fijos. esos son los de a, m y la primera oracion
    //los label de xo, c, k, g no les puse nombre ya que como nunca hacen nada siempre se quedan estaticos
    //no los necesitan, a, m y el mensaja de info se hacen visibles o cambian de texto.

    //Botones del menu de la izquierda:
    public Hyperlink btnVolverA;
    public Hyperlink btnVolverMenu;
    public Button btnClear;
    public Button btnGenerar;


    //Botones del menu del centro:
    public Button btnLineal;
    public Button btnMultiplicativo;
    public Button btnA;
    public Button btnB;
    public Button btnC;
    public Button btnVolver;
    public Button btnNext;

    //Botones del menu de la derecha:
    public Button btnVerHistograma;
    public Button btnVerTablaChi;


    //Labels del menu de la izquierda:
    public Label lblX0;
    public Label lblk;
    public Label lblg;
    public Label lblc;
    public Label lblM;
    public Label lblInt;


    //Labels del menu del centro:
    public Label lbla;
    public Label lblm;

    //Labels del menu de la derecha:
    public Label lblCac;
    public Label lbl2;
    public Label lblConfianza;
    public Label lblGradosDeLib;
    public Label lblChi;


    //Estos son los campos donde el usuario pone xo,k,g,c
    //TextField del menu de la izquierda:
    public TextField txtX0;
    public TextField txtK;
    public TextField txtG;
    public TextField txtC;
    public TextField txtM;
    public TextField txtIntervalos;

    //TextField del menu del centro:
    public TextField txtm;
    public TextField txta;
    public TextField txtInfo;


    //TextField del menu de la derecha:
    public TextField txtCac;
    public TextField txtChi;
    public TextField txtSeRechaza;


    //Estas son las 2 tablas (de n° aleatorios y de chi cuadrado) donde se muestran los datos,
    // tienen un new por que es lo unico que necesito construir por que es mas comlplejo

    public TableView<Numero> tablaNums = new TableView();
    public TableView<Intervalo> tablaChiCuadrado = new TableView();


    public BarChart grafico;
    public CategoryAxis ejeX;
    public NumberAxis ejeY;

    public AnchorPane pantallaMenuA;
    public AnchorPane pantallaMenu;
    public AnchorPane pantallaIzquierda;
    public AnchorPane pantallaNrosAleatorios;
    public AnchorPane pantallaChiCuadrado;


    //Metodos para controlar las acciones de los botones:


    //Cuando se elige el boton del punto A se hacen visibles los botones para elegir método mutiplicativo o lineal, y
    //se vuelven invisibles los botones de A, B y C.
    public void puntoA() {
        pantallaMenuA.setVisible(true);
        pantallaMenu.setVisible(false);
        btnVolverMenu.setVisible(true);
        btnVolverA.setVisible(false);
        lblX0.setVisible(true);
        lblk.setVisible(true);
        lblg.setVisible(true);
        lblc.setVisible(true);
        txtX0.setVisible(true);
        txtK.setVisible(true);
        txtG.setVisible(true);
        txtC.setVisible(true);
        lblM.setVisible(false);
        lblInt.setVisible(false);
        btnNext.setVisible(false);
    }

    public void metodoLineal() {
        //Hago que la opcion sea 11
        //Las opciones posibles son:
        //Opcion 11: Punto A - método libeal
        //Opcion 12: Punto A - método lineal
        //Opcion 2: Punto B (chi cuadrado con generador de java)
        //Opcion 3: Punto C (chi cuadrado con nuestro generador)

        opcion = 11;
        btnVolverA.setVisible(true);
        btnVolverMenu.setVisible(false);
        pantallaMenuA.setVisible(false);


        pantallaIzquierda.setVisible(true);
        txtM.setVisible(false);
        txtIntervalos.setVisible(false);


    }

    public void metodoMultiplicativo() {
        opcion = 12;
        btnVolverA.setVisible(true);
        btnVolverMenu.setVisible(false);
        pantallaMenuA.setVisible(false);

        pantallaIzquierda.setVisible(true);
        txtM.setVisible(false);
        txtIntervalos.setVisible(false);
    }

    public void puntoB() {
        opcion = 2;
        pantallaMenu.setVisible(false);
        pantallaIzquierda.setVisible(true);
        lblX0.setVisible(false);
        lblk.setVisible(false);
        lblg.setVisible(false);
        lblc.setVisible(false);
        txtX0.setVisible(false);
        txtK.setVisible(false);
        txtG.setVisible(false);
        txtC.setVisible(false);
        txtM.setVisible(true);
        txtIntervalos.setVisible(true);
        lblInt.setVisible(true);
        lblM.setVisible(true);
        btnVolverMenu.setVisible(true);
    }

    public void puntoC() {
        opcion = 3;
        pantallaMenu.setVisible(false);
        pantallaIzquierda.setVisible(true);
        lblX0.setVisible(true);
        lblk.setVisible(true);
        lblg.setVisible(true);
        lblc.setVisible(true);
        txtX0.setVisible(true);
        txtK.setVisible(true);
        txtG.setVisible(true);
        txtC.setVisible(true);
        txtIntervalos.setVisible(true);
        txtM.setVisible(true);
        lblM.setVisible(true);
        lblInt.setVisible(true);
        btnVolverMenu.setVisible(true);
        btnNext.setVisible(false);
    }


    public void volverA() {
        pantallaIzquierda.setVisible(false);
        pantallaMenuA.setVisible(true);
        btnVolverMenu.setVisible(true);
        btnVolverA.setVisible(false);
        this.clear();
    }

    public void volverMenu() {
        btnVolverMenu.setVisible(false);
        pantallaMenu.setVisible(false);
        pantallaIzquierda.setVisible(false);
        pantallaMenu.setVisible(true);
        pantallaMenuA.setVisible(false);
        clear();
    }


    public void generar() {
        //con esta condicion if verifico que los campos no esten vacios
        // !(la interrogacion) en java niega, con la funcion getText() obtengo el contenido de un campo-
        //con isEmpty pregunto si esta vacio, los && son el AND en java.
        //osea con el campo txtXo diria si(if) el contenido(getText) de txtX0 no(!) esta vacio(isEmpty)

        this.gestor = new Gestor();




        if (validarCampos(opcion)) { //Si los campos son correctos entonces seguimos, sino no
            txtInfo.setText(null);
            txtInfo.setVisible(false);

            int x0 = Integer.parseInt(txtX0.getText());
            int k = Integer.parseInt(txtK.getText());
            int g = Integer.parseInt(txtG.getText());
            int c = Integer.parseInt(txtC.getText());



            if (opcion == 11 || opcion == 12) { //Si la opcion es del punto A (11 o 12) entonces habilito la tabla y genero los nros
                habilitarTabla(opcion);
                this.gestor.generarNumeros(this, x0, k, g, c, opcion, 20);
            } else if (opcion == 2) { //Si la opcion es 2 usamos el generador de java, pasandole M y la cantidad e intervalos
                int M = Integer.parseInt(txtM.getText());
                int cantIntervalos = Integer.parseInt(txtIntervalos.getText());
                //generarNumerosJava();
            } else if (opcion == 3) { //Si la opcion es 3 (punto C):
                int M = Integer.parseInt(txtM.getText());
                int cantIntervalos = Integer.parseInt(txtIntervalos.getText());
                pantallaChiCuadrado.setVisible(true); //Habilitamos la pantalla para ver la tabla y todo lo de chi cuadrado
                habilitarTabla(opcion);
                Intervalo[] intervalos = gestor.armarIntervalosChiCuadrado(cantIntervalos, M);
                this.gestor.generarNumeros(this, x0, k, g, c, opcion, M);
                this.gestor.calcularFrecuenciaObservada(intervalos);
                double cAcum = this.gestor.calcularC(intervalos);
                txtCac.setText("" + cAcum);
                int gradosDeLIb = cantIntervalos - 1;
                double chiTabulado = this.gestor.chiCuadradoTabulado(gradosDeLIb);
                txtChi.setText("" + chiTabulado);
                lblGradosDeLib.setText("gl = " + gradosDeLIb);
                lblConfianza.setText("α = 0.95");
                if (cAcum < chiTabulado) {
                    txtSeRechaza.setText("No se puede rechazar");
                } else {
                    txtSeRechaza.setText("Se rechaza");
                }
                this.mostrarNumerosChiCuadrado(intervalos);
                this.histograma();
            }


        }
    }

    //Esta funcion lo que hace es comprobar que los campos necesarios esten completos y que c sea impar

    public boolean validarCampos(int opcion) {

        if (opcion == 11 || opcion == 12 || opcion == 3) {
            //NO FUNCIONA! deberia comprobar que los campos no esten vacios, pero tira excepcion porque no puede obtener el texto
            if (txtC.getText().isEmpty() && txtX0.getText().isEmpty() && txtK.getText().isEmpty() && txtG.getText().isEmpty()) {
                txtInfo.setVisible(true);
                txtInfo.setText("Error: Campos incompletos.");
                return false;
            } else {
                int c = Integer.parseInt(txtC.getText());
                if (c % 2 == 0) {
                    txtInfo.setVisible(true);
                    txtInfo.setText("Error: Recuerde que C debe ser impar.");
                    return false;
                }
            }
        }

        if (opcion == 2 || opcion == 3) {
            if (txtM.getText().isEmpty() || txtIntervalos.getText().isEmpty()) {
                txtInfo.setVisible(true);
                txtInfo.setText("Error: Campos incompletos.");
                return false;
            }
        }
        return true;
    }



    //Hace visible la tabla, los botones next y clear, y hace no visible el boton generar y el lbl de informacion
    private void habilitarTabla(int opcion) {
        pantallaNrosAleatorios.setVisible(true);

        if (opcion == 2) {
            lbla.setVisible(false);
            lblm.setVisible(false);
            txtm.setVisible(false);
            txta.setVisible(false);
        }

        txtInfo.setVisible(true);
        btnClear.setVisible(true);
        btnGenerar.setVisible(false);

    }

    //Construye las columnas de la tabla e invoca el metodo que muestras las filas
    public void mostrarNumeros(double[] v, int a, int m) {
        //con este if controlo que no existan columnas en la tabla,de no ponerlo, va agregando columnas iguales
        //cada vez que se toca generar o next
        if (tablaNums.getColumns().isEmpty()) {
            //Aca creo las dos columnas, se los explico por parte con la primera
            //como es una variable tengo que pones el tipo, en este caso TableColumn
            //lo que esta entre <> significa que la columna recibe un objeto de la clase numero-
            //y lo transforma en cadena de caracteres
            //colIt y colNum son el nombre de las variables
            //despues invoco al constructor new y por ultimo dentro del parentesis escribo el titulo de la columna
            TableColumn<Numero, String> colIt = new TableColumn<>("Iteracion");
            TableColumn<Numero, String> colNum = new TableColumn<>("Numero");
            //con esto agrefo las columnas creadas a la tabla
            tablaNums.getColumns().addAll(colIt, colNum);
            //lo siguiente es mas complicado, en la clase numero que cree, tiene 2 atributos iteracion y numero
            //en en la siguiente oracion diria en la columna colIt establecer como valor(setCellValueFactory)
            //la propiedad con nombre iteracion(PropertyValue...) de la clase Numero, aunque ahora no mencione-
            // la clase numero, ya habia dicho que la recibia en la construccion
            colIt.setCellValueFactory(new PropertyValueFactory<>("iteracion"));
            colNum.setCellValueFactory(new PropertyValueFactory<>("numero"));
        }
        //esta las funciones que llenas las filas
        llenarColumnas(v);
        //aca solo agrego los valores de m y a
        txta.setText("" + a);
        txtm.setText("" + m);
    }

    public void mostrarNumerosChiCuadrado(Intervalo[] intervalos) {
        if (tablaChiCuadrado.getColumns().isEmpty()) {
            TableColumn<Intervalo, String> colDesde = new TableColumn<>("Desde");
            TableColumn<Intervalo, String> colHasta = new TableColumn<>("Hasta");
            TableColumn<Intervalo, String> colFo = new TableColumn<>("Fo");
            TableColumn<Intervalo, String> colFe = new TableColumn<>("Fe");
            TableColumn<Intervalo, String> colC = new TableColumn<>("C");
            TableColumn<Intervalo, String> colCac = new TableColumn<>("C(ac)");

            tablaChiCuadrado.getColumns().addAll(colDesde, colHasta, colFo, colFe, colC, colCac);


            colDesde.setCellValueFactory(new PropertyValueFactory<>("desde"));
            colHasta.setCellValueFactory(new PropertyValueFactory<>("hasta"));
            colFo.setCellValueFactory(new PropertyValueFactory<>("frecObservada"));
            colFe.setCellValueFactory(new PropertyValueFactory<>("frecEsperada"));
            colC.setCellValueFactory(new PropertyValueFactory<>("C"));
            colCac.setCellValueFactory(new PropertyValueFactory<>("cAcumulado"));

            colDesde.prefWidthProperty().bind(tablaChiCuadrado.widthProperty().divide(7));
            colHasta.prefWidthProperty().bind(tablaChiCuadrado.widthProperty().divide(7));
            colFo.prefWidthProperty().bind(tablaChiCuadrado.widthProperty().divide(9));
            colFe.prefWidthProperty().bind(tablaChiCuadrado.widthProperty().divide(6));
            colC.prefWidthProperty().bind(tablaChiCuadrado.widthProperty().divide(6));
            colCac.prefWidthProperty().bind(tablaChiCuadrado.widthProperty().divide(6));
        }
        //esta las funciones que llenas las filas
        llenarColumnasChiCuadrado(intervalos);
    }


    //Muestra los numeros obtenidos, ubicandolo en la columna correspondiente
    public void llenarColumnas(double[] v) {
        //ciclo for que recoore el valor de los numeros
        for (int i = 0; i < v.length; i++) {
            //cre un nuevo objeto num para poder agregarlo a la tabla con add
            Numero num = new Numero(i + 1, v[i]);
            tablaNums.getItems().add(num);
        }
    }

    public void llenarColumnasChiCuadrado(Intervalo[] intervalos) {
        //ciclo for que recorre los intervalos
        for (int i = 0; i < intervalos.length; i++) {
            tablaChiCuadrado.getItems().add(intervalos[i]);
        }
    }


    //Funcion para el boton Next que muestra el siguiente numero en la tabla
    public void siguiente(ActionEvent actionEvent) {
        borrar();
        this.gestor.next();
    }

    //Borra todas las filas de la tabla
    public void borrar() {
        double[] v = this.gestor.getNum();
        for (int i = 0; i < v.length; i++)
            //remove va sacando el primer elemento de la tabla, como esta en un ciclo for lo hace hasta que este vacia
            tablaNums.getItems().remove(0);
    }

    public void borrarChiCuadrado() {
        Intervalo[] intervalos = this.gestor.getIntervalos();
        for (int i = 0; i < intervalos.length; i++)
            //remove va sacando el primer elemento de la tabla, como esta en un ciclo for lo hace hasta que este vacia
            tablaChiCuadrado.getItems().remove(0);
    }

    //Funcion para el boton clear que borra la tabla
    public void clear() {
        pantallaChiCuadrado.setVisible(false);
        pantallaNrosAleatorios.setVisible(false);
        btnClear.setVisible(false);
        this.borrar();
        this.borrarChiCuadrado();
        this.deshabilitarTabla();
    }

    //Inhabilita la tabla
    private void deshabilitarTabla() {
        //aca hago no visible a la tabla volviendo como era al comienzo
        pantallaNrosAleatorios.setVisible(false);

        //a set info le cambio el texto por que si se equivoco y puso una c impar se cambia el texto
        txtInfo.setVisible(true);
        txtInfo.setText("Inserte en los campos valores enteros, recordando que c debe ser impar.");
        btnGenerar.setVisible(true);
        //Esto borra los numeros de los campos de texto
        txtX0.setText(null);
        txtK.setText(null);
        txtG.setText(null);
        txtC.setText(null);
        txtm.setText(null);
        txta.setText(null);
        txtM.setText(null);
        txtCac.setText(null);
        txtChi.setText(null);
        txtSeRechaza.setText(null);
        txtIntervalos.setText(null);
        grafico.setVisible(false);
        grafico.setData(null);

    }


    public void histograma() {

        if (!txtIntervalos.getText().isEmpty()) {

            grafico.setVisible(true);
            grafico.setData(null);
            XYChart.Series<String, Number> barras = new XYChart.Series<>();
            int per = Integer.parseInt(txtIntervalos.getText());
            double[] intervalos = gestor.armarIntervalos(per);
            int[] valores = gestor.llenarIntervalos(intervalos);
            grafico.setCategoryGap(0);
            grafico.setBarGap(0);
            ejeX.setLabel("Intervalos");
            ejeY.setLabel("Valores");
            String limInf;
            for (int i = 0; i < per; i++) {
                if (i == 0) {
                    limInf = "0";
                } else {
                    limInf = String.valueOf(intervalos[i - 1]);
                }
                String limSup = String.valueOf(intervalos[i]);
                String cad = limInf + " - " + limSup;
                barras.getData().add(new XYChart.Data<String, Number>(cad, valores[i]));
            }
            ObservableList<XYChart.Series<String, Number>> data = FXCollections.observableArrayList();
            data.add(barras);
            grafico.setData(data);
        }
    }

    //Esta es una funcion para calcular el MCD (maximo comun divisor) entre c y m
    //La idea es comprobar que el MCD entre ellos sea 1, porque en ese caso seran relativamente primos como pide el metodo
    //No la utilizamos en el codigo porque comprobamos que mientras c sea impar, siempre seran relativamente primos, pero
    //esta bueno tenerla por las dudas para entenderlo mejor
    public int MCD() {
        int m = Integer.parseInt(txtm.getText());
        int c = Integer.parseInt(txtC.getText());

        int mayor, menor;
        if (m > c) {
            mayor = m;
            menor = c;
        } else {
            mayor = c;
            menor = m;
        }

        int resto = mayor % menor;
        int restoActual;

        while (resto != 1) {
            restoActual = menor % resto;
            menor = resto;
            resto = restoActual;
        }

        int MCD = resto;

        return MCD;

    }




    /*
    En los numeros 1 a 6 etan los importaciones para que anden la tabla, los botones, los campos y cualquier cosa que usemos.
     Lo siguiente significa que tiene una estuctura VBOX(como si fuera una caja vertical, miren la foto que les mande),
    en la vbox v cada cosa debajo de la anterior. como sample es una ventana emergente o pantalla, con fx:controller
    le decimos como se llama o la direccion de la clase que controlla la pantalla en este caso controller, xmlns no se
    que significa se carga solo y aligment es la alineacion como la alineacion en los documentos de word.
    en esta pagina las oraciones o comandos se encuentrar entre <>
    <VBox fx:controller="sample.Controller" xmlns:fx="http://javafx.com/fxml" alignment="center" >
    Para que quede mas bonito dentro de la Vbox puse una Gridpane, esto lo unico que hace es hacer una estructura de fondo
    como si fueran las celdas de excel, fx:id es la identificacion o nombre (en este caso grid1), la alinacion central
    hgap es la altura y vgap el ancho.
    <GridPane fx:id="grid1" alignment="CENTER" hgap="10" vgap="10">
    los label, textField, botones tienen en comun que se empieza escribiendo que son, luego pongo la fila en la que debe
    estar ubicado de la grilla(GridPane.rowIndex) y luego la columna(GridPane.columnIndex) y el texto que va a contener
    con text, (el unico que no tiene texto son los textfield)
    <Label GridPane.rowIndex="1" GridPane.columnIndex="0" text="X0: " />
    La unica funcion diferente es que en los textfield el pongo una anchura maxima en este caso 50(maxWidth)
     <TextField fx:id="txtX0" GridPane.rowIndex="1" GridPane.columnIndex="1" maxWidth="50"/>
     con el comando onAction le digo al boton que funcion se debe realizar cuando alguien lo toca(se le agrega numeral
     a la funcion)
     <Button fx:id="btnGenerar" GridPane.rowIndex="1" GridPane.columnIndex="8" onAction="#generar" text="Generar"/>
      La tabla ademas de contener lo mencionado tiene eso de "columnResizePolicy" las tres lineas son un solo comando
      para decir que las columnas deben tener el ancho total de la tabla
      <TableView fx:id="tabla" maxWidth="500" GridPane.columnIndex="1" GridPane.rowIndex="2" visible="false">
            <columnResizePolicy>
                <TableView fx:constant="CONSTRAINED_RESIZE_POLICY" />
            </columnResizePolicy></TableView>

     Los siguientes comandos son simplemente para cerrrar las estucturas de fondo(grilla o caja)
     </GridPane>
    </VBox>
    */
}
