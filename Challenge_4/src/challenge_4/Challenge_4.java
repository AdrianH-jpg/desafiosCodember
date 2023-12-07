/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package challenge_4;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Adrian H
 */
public class Challenge_4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*** Hackers dañan sistema de archivos **
En un mundo donde la información es poder, un hacker conocido como Savipo Yatar descubre una serie de archivos en un servidor altamente protegido.

Estos archivos contienen secretos que podrían cambiar el curso de la historia. Pero hay un problema: algunos de los archivos son falsos, diseñados para engañar a los intrusos. Savipo Yatar debe determinar rápidamente cuáles archivos son reales y cuáles son falsos.

Cada archivo tiene un nombre con dos partes, separadas por un guion (-). La primera parte es una cadena alfanumérica y la segunda es unchecksum, que es una cadena formada por los caracteres que sólo aparecen una vez en la primera parte y en el orden en que aparecen.

Escribe un programa que determine si un archivo es real o falso basado en estas reglas.

Ejemplos:

Nombre del archivo: xyzz33-xy
Resultado: ✅ Real (El checksum es válido)

Nombre del archivo: abcca1-ab1
Resultado: ❌ Falso (El checksum debería ser b1, es incorrecto)

Nombre del archivo: abbc11-ca
Resultado: ❌ Falso (El checksum debería ser ac, el orden es incorrecto)
Cada línea indica el nombre del archivo y su correspondiente checksum, separados por un guion (-).

** Cómo resolverlo **
1. Analiza la lista de nombres de archivos y sus checksums que encontrarás en este archivo: https://codember.dev/data/files_quarantine.txt

2. Busca el archivo real número 33 (de todos los archivos reales, el 33º en orden de apareción) y envía su checksum con submit. Por ejemplo si el archivo es xyzz33-xy, harías:
submit xy*/
        
        try {
            // Obtener la lista de políticas y claves de cifrado desde el archivo en línea
            List<String> lines = obtenerListaDesdeURL("https://codember.dev/data/files_quarantine.txt");
            boolean valida = true;
//            // Iterar sobre la lista e imprimir cada elemento
//            for (String linea : lines) {
//                System.out.println(linea);
//            }

            // Contador de claves inválidas
            int clavesValidas = 0;
            // Iterar sobre cada línea de la lista
            for (String line : lines) {
                // Dividir la línea en política y clave
                String[] partes = line.split("-");
                String codigo = partes[0].trim();
                String confirmacion = partes[1].trim();
                // Dividir la confirmacion en valores numéricos y carácteres
                String[] confiPartes = confirmacion.split("");
               
                for (int i = 0; i < confiPartes.length; i++) {
                    char caracter = confiPartes[i].charAt(0);
                    // Contar la cantidad de veces que aparece el carácter en la clave
                    long cantidad = codigo.chars().filter(ch -> ch == caracter).count();
                    // Verificar si la clave cumple con la validacion
                    if (cantidad > 1) {
                        valida = false;
                    }   
                    
                }
//                System.out.println("");
//                System.out.println(codigo);
//                System.out.println("palabra" + c);
                if (valida) {
                        clavesValidas++;

                        // Si es la 33ª clave inválida, imprimir y salir del bucle
                        if (clavesValidas == 33) {
                            System.out.println("La 33ª clave válida es: " + codigo);
                            break;
                        }
                    }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener la lista de políticas y claves desde una URL
    private static List<String> obtenerListaDesdeURL(String urlString) throws IOException {
        URL url = new URL(urlString);
        try (Scanner scanner = new Scanner(url.openStream())) {
            return List.of(scanner.useDelimiter("\\A").next().split("\n"));
        }
    }
    
    
}
