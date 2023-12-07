/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package challenge_3;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Adrian H
 */
public class Challenge_3 {

    /**
     * @param args the command line arguments
     */
     public static void main(String[] args) {
         /*
** El Desafío del Cifrado Espía **
Un grupo de espías ha descubierto que su sistema de cifrado de mensajes está comprometido.

Han encontrado algunas contraseñas que no cumplen con laPolítica de Seguridad de Cifrado que tenían establecida cuando fueron creadas.

Para solucionar el problema, han creado una lista (tu entrada al desafío) de contraseñas (según la base de datos corrupta) y la política de seguridad cuando esa clave fue establecida.

Ejemplo de la lista:

2-4 f: fgff
4-6 z: zzzsg
1-6 h: hhhhhh
Cada línea indica, separado por :, la política de la clave y la clave misma.

La política de la clave especifica el número mínimo y máximo de veces que un carácter dado debe aparecer para que la clave sea válida. Por ejemplo, 2-4 f significa que la clave debe contener f al menos 2 veces y como máximo 4 veces.

Sabiendo esto, en el ejemplo anterior, hay 2 claves válidas:

La segunda clave, zzzsg, no lo es; contiene 3 veces la letra z, pero necesita al menos 4. Las primeras y terceras claves son válidas: contienen la cantidad adecuada de f y h, respectivamente, según sus políticas.

** Tu desafío: **
Determina cuántas claves de cifrado son válidas según sus políticas.

** Cómo resolverlo **
1. Analiza la lista de políticas y claves de cifrado que encontrarás en este archivo: https://codember.dev/data/encryption_policies.txt

2. Crea un programa que devuelva la clave inválida número 42 (de todas las claves inválidas, la 42ª en orden de aparición). Por ejemplo:
submit bqamidgewtbuz
         */
        try {
            // Obtener la lista de políticas y claves de cifrado desde el archivo en línea
            List<String> lines = obtenerListaDesdeURL("https://codember.dev/data/encryption_policies.txt");
            
            // Iterar sobre la lista e imprimir cada elemento
            for (String linea : lines) {
                System.out.println(linea);
            }

            // Contador de claves inválidas
            int clavesInvalidas = 0;

            // Iterar sobre cada línea de la lista
            for (String line : lines) {
                // Dividir la línea en política y clave
                String[] partes = line.split(":");
                String politica = partes[0].trim();
                String clave = partes[1].trim();

                // Dividir la política en valores numéricos y carácter
                String[] politicaPartes = politica.split(" ");
                String[] rango = politicaPartes[0].split("-");
                int min = Integer.parseInt(rango[0]);
                int max = Integer.parseInt(rango[1]);
                char caracter = politicaPartes[1].charAt(0);

                // Contar la cantidad de veces que aparece el carácter en la clave
                long cantidad = clave.chars().filter(ch -> ch == caracter).count();

                // Verificar si la clave cumple con la política
                if (cantidad < min || cantidad > max) {
                    clavesInvalidas++;

                    // Si es la 42ª clave inválida, imprimir y salir del bucle
                    if (clavesInvalidas == 42) {
                        System.out.println("La 42ª clave inválida es: " + clave);
                        break;
                    }
                    if (clavesInvalidas == 13) {
                        System.out.println("La 13ª clave inválida es: " + clave);
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
