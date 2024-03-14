import java.util.Arrays;
import java.util.Scanner;

/**
 * @Description Aplicación para una máquina expendedora de golosinas. Tema:Array
 * @author Irina Medina
 * @version 1.0 14/03/2024 *
 * */

public class Maquina {
    public static void main(String[] args) {
        menu();
    }

    /**
     * Muestra el menu de la aplicación
     */
    public static void menu(){
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
        mostrar(Arrays.deepToString(stock));
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
                case 1->{
                    //Pedir las golosinas
                     mostrar("Posicion");
                     pos = sn.nextInt();
                    if (pos>=0 && pos<=33){
                        columna=pos%10;
                        fila=(pos%100)/10;
                        mostrar("fila"+ fila);
                        mostrar("columna"+columna);

                    }else {
                        mostrar("Código no válido");
                    }
                }
                case 2-> {
                    //mostrar todos los códigos, los nombres de las golosinas y su precio
                    mostrarGolosinas(golosinas,precios);
                }
                case 3->{
                    //solicitara la contraseña del técnico y luego llamar la funcion de rellenar
                }
                case 4->{
                    //mostrar lo recolectado en las ventas de la máquina (cierre de caja)
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
