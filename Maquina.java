import java.util.Arrays;
import java.util.Scanner;

/**
 * @Description Aplicación para una máquina expendedora de golosinas. Tema:Array
 * @author Irina Medina
 * @version 1.0 14/03/2024 *
 * */

public class Maquina {
    public static void main(String[] args)  throws InterruptedException{
        menu();
    }

    /**
     * Muestra el menu de la aplicación
     */
    public static void menu() throws InterruptedException {
        //Array de las Golosinas
        String [][] golosinas={
                {
                    "KitKat","Chicles de Fresa","Lacasitos","Palotes"
                },
                {
                  "Kinder Bueno", "Bolsa Haribo", "Chettos", "Twix"
                },
                {
                    "Galletas OREO", "M&M`s", "Patatas Fritas","Chicles de Menta"
                },
                {
                    "Milkybar", "KitKat Blanco", "Galleta Oreo Fresa", "Huesitos"
                }

        };
        //Array de Precios
        double[][] precios={
                {
                    1.5,1.0,1.65,1.10
                },
                {
                    2.0,2.20,1.0,1.05
                },
                {
                    1.25,2.0,2.50,1
                },
                {
                    3.0,1.5,1.25,1.50
                }

        };
        //Array Stock
        int stock[][]=new int[4][4];
        //llamamos la funcion de rellenar la maquina
        stock=rellenarMaquina(stock,5);
    //    mostrar(Arrays.deepToString(stock));
        boolean salir=false; // para validar el bucle del menu
        double totalVentaEuros=0;
        int opcion, fila,columna,cantidadStrock,pos;

        // desarrollamos el menu
        while (!salir){//!variable booleada indica que es falso-> mientras sea salir falso se repide while
            //opciones
            mostrar("****\tM A Q U I N A  D E  G O L O S I N A S\t****");
            mostrar("1. Pedir Golosinas");
            mostrar("2. Mostrar Golosinas");
            mostrar("3. Rellenar Stock (Admin");
            mostrar("4. Apagar Máquina");
            Scanner sn=new Scanner(System.in);
            mostrarSinLn("Indique su opcion->");
            opcion=sn.nextInt();
            // hacemos un switch case de opciones
            switch (opcion){
                case 1-> {
                    //Pedir las golosinas
                    mostrarSinLn("Indique la posición de la Golosina a comprar->");
                    pos= sn.nextInt();
                    //tenemos que validar que la posicion sea >=0 y <=33
                    if (pos>=0 &&pos<=3 || pos>10 &&pos<=13 ||pos>=20&&pos<=23 || pos>=30&&pos<=33){
                        //calculamos el valor de la fila
                        fila=(pos%100)/10;
                        columna=pos%10;
                        //llamar un metodo que controle el stock y me diga true o false si hay cantidad suficiente a vender
                         if (controlStock(stock,fila,columna)){
                             mostrar("Ingresa el Dinero " + precios[fila][columna] + "€");
                             double aux=sn.nextDouble();
                             if (aux>precios[fila][columna]){
                                 mostrar("Tu cambio -> " + (aux-precios[fila][columna])+"€");
                             }
                             mostrar("Disfuta de tu ......"+ golosinas[fila][columna]);
                             stock[fila][columna]--;
                             totalVentaEuros+=precios[fila][columna];
                         }else{
                             mostrar("No hay " + golosinas[fila][columna] + " en la máquina");
                         }
                    }else{
                        mostrar("Opción no admitida");
                    }

                }
                case 2-> {
                    //mostrar todos los códigos, los nombres de las golosinas y su precio
                    mostrarGolosinas(golosinas,precios);
                }
                case 3->{
                    //solicitara la contraseña del técnico y luego llamar la funcion de rellenar
                    mostrar("Ingrese la Contraseña autorizada->");
                    String pass=sn.next();sn.nextLine();
                    //validamos que el tecnico intruduce la contraeña correcta
                    if (pass.equals("chuch3s")){
                        mostrar("..........Reponiendo la Maquina........");
                        for (int i = 0; i < 4; i++) {
                            for (int j = 0; j < 4; j++) {
                                mostrarSinLn("\uD83C\uDF6C");
                                Thread.sleep(500);
                            }
                            mostrar("");
                        }
                        rellenarMaquina(stock,5);
                    }else{
                        mostrar("Usuario no autorizado");
                    }
                }
                case 4->{
                    //mostrar lo recolectado en las ventas de la máquina (cierre de caja)
                    mostrar("La maquina ha vendido ->" + totalVentaEuros + "€");
                    salir=true;
                }
                default->mostrar("Opcion no válida");
            }
        }
    }

    /**
     * Métodos para mostrar las posiciones, los nombres de las golosinas y su precio
     * @param golosinas
     * @param precios
     */
    public static void mostrarGolosinas(String golosinas[][],double precios[][]){
        //necesitamos recorrer el array de nombres y con posición obtenemos el precio
        for (int i = 0; i < golosinas.length; i++) {
            for (int j = 0; j < golosinas[i].length; j++) {
                mostrar(i+""+j+ " " + golosinas[i][j]+ " " + precios[i][j]+"€");
            }
        }
    }

    /**
     * Método que controla el stock mediante booleanos
     * @param stock
     * @param fila
     * @param columna
     * @return
     */

public static boolean controlStock(int stock[][],int fila,int columna) {
    if (stock[fila][columna] == 0) {
        return false;
    } else {
        return true;
    }
}

    /**
     * Método que se utiliza para rellenar el stock de la máquina al inicio y cuando va el técnico
     * a reponer stock
     * @param stock
     * @param valor
     * @return stock
     */
    public static int[][] rellenarMaquina(int stock[][],int valor){
        //inicializamos el array de stock de cada golosina en 5
        for (int i = 0; i < stock.length; i++) {
            for (int j = 0; j < stock[i].length; j++) {
                stock[i][j]=valor; //valor que se envia como parámetro
            }
        }
        return stock;
    }

    /**
     * Método que se utiliza para mostrar en consola los textos con salto de línea
     * @param texto
     */

    public static void mostrar(String texto) {
            System.out.println("\t" + texto);
    }

    /**
     * Método que se utiliza para mostrar en consola los textos SIN salto de línea
     * @param texto
     */

    public static void mostrarSinLn(String texto) {
            System.out.print("\t" + texto);
    }
}
