package ui;

import java.util.Scanner;

public class Guacamaya {

    public static Scanner reader;
    public static double[] precios;
    public static int[] unidades;

    public static void main(String[] args) {

        inicializarGlobales();
        menu();
    }

    /**
     * Descripcion: Este método se encarga de iniciar las variables globales
     * pre: El Scanner reader debe estar declarado
     * pos: l Scanner reader queda inicializado
    */
    public static void inicializarGlobales() {

        reader = new Scanner(System.in);

    }

    /**
     * Descripcion: Este método se encarga de desplegar el menu al usuario y mostrar los mensajes de resultado para cada funcionalidad
     * pre: El Scanner reader debe estar inicializado
     * pre: El arreglo precios debe estar inicializado
    */
    public static void menu() {

        System.out.println("Bienvenido a Guacamaya!");

        establecerCantidadVendida();

        boolean salir = false;

        do {

            System.out.println("\nMenu Principal:");
            System.out.println("1. Solicitar precios ($) y cantidades de cada referencia de producto vendida en el dia");
            System.out.println("2. Calcular la cantidad total de unidades vendidas en el dia");
            System.out.println("3. Calcular el precio promedio de las referencias de producto vendidas en el dia");
            System.out.println("4. Calcular las ventas totales (dinero recaudado) durante el dia");
            System.out.println("5. Consultar el numero de referencias de productos que en el dia han superado un limite minimo de ventas");
            System.out.println("6. Salir");
            System.out.println("\nDigite la opcion a ejecutar");
            int opcion = reader.nextInt();

            switch (opcion) {
                case 1:
                    solicitarDatos();
                    break;
                case 2:
                    System.out.println("\nLa cantidad total de unidades vendidas en el dia fue de: "+calcularTotalUnidadesVendidas());
                    break;
                case 3:
                    System.out.println("\nEl precio promedio de las referencias de producto vendidas en el dia fue de: " + (int) calcularPrecioPromedio());
                    break;
                case 4:
                    System.out.println("\nLas ventas totales (dinero recaudado) durante el dia fueron: "+ (int) calcularVentasTotales());
                    break;
                case 5:
                    System.out.println("\nDigite el limite minimo de ventas a analizar");
                    double limite = reader.nextDouble();
                    System.out.println("\nDe las "+precios.length+" referencias vendidas en el dia, "+consultarReferenciasSobreLimite(limite)+" superaron el limite minimo de ventas de "+(int)limite);
                    break;
                case 6:
                    System.out.println("\nGracias por usar nuestros servicios!");
                    salir = true;
                    reader.close();
                    break;

                default:
                    break;
            }

        } while (!salir);

    }

    /**
     * Descripcion: Este método se encarga de preguntar al usuario el número de referencias de producto diferentes 
     * vendidas en el día e inicializa con ese valor los arreglos encargados de almacenar precios y cantidades
     * pre: El Scanner reader debe estar inicializado
     * pre: Los arreglos precios y unidades deben estar declarados
     * pos: Los arreglos precios y unidades quedan inicializados
     */
    public static void establecerCantidadVendida() {

        System.out.println("\nDigite el numero de referencias de producto diferentes vendidas en el dia ");
        int referencias = reader.nextInt();

        precios = new double[referencias];
        unidades = new int[referencias];

    }

    /**
     * Descripcion: Este método solicita al usuario los precio y cantidades vendidas de cada referencia 
     * pre: El Scanner reader debe estar inicializado
     * pre: Los arreglos precios y unidades deben estar declarados
     * pos: Los arreglos precios y unidades quedan inicializados
     */
    public static void solicitarDatos(){
        for ( int i = 0; i < precios.length; i++ ) {
          System.out.println("\nDigite el precio del producto  " + (i + 1)); 
          precios[i] = reader.nextDouble();

          System.out.println("Digite la cantidad del producto  " + (i + 1));
          unidades[i] = reader.nextInt();
        }

     
    }

    /**
     * Descripcion: Este método calcula el total de las unidades vendidas durante el día 
     * pre: Los arreglos precios y unidasdes deben estar inicializados 
     * @return el total de las unidades vendidas
     */
    public static int calcularTotalUnidadesVendidas(){
        int totalUnidades = 0;
        for(int unidad : unidades){
            totalUnidades += unidad;

        }

        return totalUnidades;

    }

    /**
     * Descripcion: Este método calcula el promedio de las referencias vendidas durante el día 
     * pre: Los arreglos precios y unidades deben estar inicializados
     * @return El precio promedio de las referencias
     */
    public static double calcularPrecioPromedio(){
        double sumaPrecios = 0;
        for (double precio : precios) {
            sumaPrecios += precio;
        }

        return sumaPrecios / precios.length;

    }

    /**
     * Descripcion: Este método calcula el total de ventas (dinero recaudado) durante el día
     * pre: Los arreglos precios y unidades deben estar inicializados
     * @return El total del dinero recaudado
     */
    public static double calcularVentasTotales(){

        double totalVentas = 0;
        for (int i = 0; i < precios.length; i++) {
            totalVentas += precios[i] * unidades[i];
        }

        return totalVentas;

    }

    /**
     * Descripcion: Este método consultar el número de referencias que superan el limite minimo de ventas 
     * pre: Los arreglos precios y unidades deben estar inicializados
     * @param limite El limite minimo de ventas a consultar
     * @return Las referencias que superaron el limite 
     */
    public static int consultarReferenciasSobreLimite(double limite){

        int referenciasSobreLimite = 0;
        for (int i = 0; i < precios.length; i++) {
            double totalVentaProducto = precios[i] * unidades[i];
            if (totalVentaProducto > limite) {
                referenciasSobreLimite++;
            }

        }
        return referenciasSobreLimite;

    }

}
