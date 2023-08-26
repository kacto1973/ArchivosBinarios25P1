import java.io.*;

public class Principal {

    public static void main(String[] args) throws Exception {

        //instanciamos objetos a utilizar en este proyecto
        FileInputStream fileInputStreamA = new FileInputStream("a.mat");
        FileInputStream fileInputStreamB = new FileInputStream("b.mat");
        DataInputStream dataInputStreamA = new DataInputStream(fileInputStreamA);
        DataInputStream dataInputStreamB = new DataInputStream(fileInputStreamB);


        //creamos el archivo c.mat en el cual almacenaremos los resultados y hacemos lo necesario
        File file = new File("c.mat");
        file.createNewFile();
        FileOutputStream fileOutputStreamC = new FileOutputStream(file);
        DataOutputStream dataOutputStreamC = new DataOutputStream(fileOutputStreamC);


        //arreglos a multiplicar y el que llenaremos *el resultante*
        double[][] arregloA = new double[3][3];
        double[][] arregloB = new double[3][3];
        double[][] arregloC = new double[3][3];

        //saltamos los dos primeros Bytes
        dataInputStreamA.readByte();
        dataInputStreamA.readByte();
        System.out.println("Matriz A:");
        for (int i = 0; i < arregloA.length; i++) {
            for (int j = 0; j < arregloA.length; j++) {

                arregloA[i][j] = dataInputStreamA.readDouble();

                System.out.print(arregloA[i][j]+" - ");

            }//fin for j
            System.out.println();
        }//fin for i

        System.out.println();//separador

        //saltamos los dos primeros Bytes
        dataInputStreamB.readByte();
        dataInputStreamB.readByte();
        //rellenamos matriz B
        System.out.println("Matriz B:");
        for (int i = 0; i < arregloB.length; i++) {
            for (int j = 0; j < arregloB.length; j++) {

                arregloB[i][j] = dataInputStreamB.readDouble();

                System.out.print(arregloB[i][j]+" - ");

            }//fin for j
            System.out.println();
        }//fin for i

        System.out.println();


        for (int i = 0; i < arregloC.length; i++) {
            for (int j = 0; j < arregloC.length; j++) {
                for (int k = 0; k < 3; k++) {

                       arregloC[i][j] +=  arregloA[i][k]  *  arregloB[k][j];

                }//fin k
                System.out.print(arregloC[i][j]+" - ");
            }//fin for j
            System.out.println();
        }//fin for i

        dataOutputStreamC.writeByte(3);
        dataOutputStreamC.writeByte(3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                dataOutputStreamC.writeDouble(arregloC[i][j]);

            }
        }

        FileInputStream fileInputStreamC = new FileInputStream("c.mat");
        DataInputStream dataInputStreamC = new DataInputStream(fileInputStreamC);

        System.out.println();
        System.out.println("Comprobamos que se haya escrito bien c.mat");
        System.out.println("renglones:"+dataInputStreamC.readByte());
        System.out.println("columnas:"+dataInputStreamC.readByte());

        System.out.print("numeros: ");
        for (int i = 0; i < 9; i++) {
            System.out.print(+dataInputStreamC.readDouble()+" - ");
        }

    }//fin main
}//fin class
