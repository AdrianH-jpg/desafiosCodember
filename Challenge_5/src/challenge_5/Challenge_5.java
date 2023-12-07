/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package challenge_5;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Adrian H
 */
public class Challenge_5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
        ** El problema final **
Finalmente los hackers han conseguido acceder a la base de datos y la han dejado corrupta. Pero parece que han dejado un mensaje oculto en la base de datos. ¿Podrás encontrarlo?

Nuestra base de datos está en formato .csv. Las columnas son id, username, email, age, location.

Un usuario sólo es válido si:

- id: existe y es alfanumérica
- username: existe y es alfanumérico
- email: existe y es válido (sigue el patrón user@dominio.com)
- age: es opcional pero si aparece es un número
- location: es opcional pero si aparece es una cadena de texto
Ejemplos:

Entrada: 1a421fa,alex,alex9@gmail.com,18,Barcelona
Resultado: ✅ Válido

Entrada: 9412p_m,maria,mb@hotmail.com,22,CDMX
Resultado: ❌ Inválido (id no es alfanumérica, sobra el _)

Entrada: 494ee0,madeval,mdv@twitch.tv,,
Resultado: ✅ Válido (age y location son opcionales)
Entrada: 494ee0,madeval,twitch.tv,22,Montevideo
Resultado: ❌ Inválido (email no es válido)
** Cómo resolverlo **
1. Analiza la lista de entradas de la baes de datos y detecta los inválidos: https://codember.dev/data/database_attacked.txt

2. Encuentra el primer caracter (número o letra) del username de cada usuario inválido. Júntalos por orden de aparición y descubre el mensaje oculto. Luego envíalo con submit. Por ejemplo:
submit att4ck
        */
        
//        try {
//            // Obtener la lista de políticas y claves de cifrado desde el archivo en línea
//            List<String> lines = obtenerListaDesdeURL("https://codember.dev/data/database_attacked.txt");
//            int c = 0;
//            // Iterar sobre la lista e imprimir cada elemento
////            for (String linea : lines) {
////                System.out.println(linea);
////                c++;
////            }
////            System.out.println(c);
//
//            // Contador de claves inválidas
//            String usuariosValidos = "";
//
////            // Iterar sobre cada línea de la lista
//            for (String line : lines) {
//                // Dividir la línea en política y clave
//                String[] partes = line.split(",");
//                String id = "";
//                    String user = "";
//                    String email = "";
//                    String age = "";
//                    String location = "";
//
//                 if (partes.length == 5) {
//                     
//                     id = partes[0];
//                     user = partes[1];
//                     email = partes[2];
//                     age = partes[3];
//                     location = partes[4];
//                 }
//                 else{
//                      id = partes[0];
//                      user = partes[1];
//                      email = partes[2];
//                      
//                 }
//                
//
//                
//                // Verificar si la clave cumple con la política
//                if (id.isEmpty() || !isAlphaNumeric(id)) {
//                    System.out.println("1");
//                    if (user.isEmpty() || !isAlphaNumeric(user)) {
//                        System.out.println("2");
//                        if (email.isEmpty() || !esEmailValido(email)) {
//                            System.out.println("3");
//                            if (age.isEmpty() || !esSoloNumeros(age)) {
//                                System.out.println("4");
//                                if (location.isEmpty() || !esSoloTexto(location)) {
//                                    System.out.println("5");
//                                    usuariosValidos.concat(String.valueOf(user.charAt(0)));
//                                    System.out.print(user.charAt(0));
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//            System.out.println( usuariosValidos);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Método para obtener la lista de políticas y claves desde una URL
//    private static List<String> obtenerListaDesdeURL(String urlString) throws IOException {
//        URL url = new URL(urlString);
//        try (Scanner scanner = new Scanner(url.openStream())) {
//            return List.of(scanner.useDelimiter("\\A").next().split("\n"));
//        }
//    }
//    
//    //Metodo para comprobar que es alfanumerico
//    private static boolean isAlphaNumeric(String cadena) {
//        return cadena != null && cadena.matches("^[a-zA-Z0-9]*$");
//    }
//    //Metodo para comprobar que solo sean numeros
//    private static boolean esSoloNumeros(String cadena) {
//        // Utilizar una expresión regular para verificar si la cadena contiene solo números
//        return cadena.matches("\\d+");
//    }
//    //Metodo para comprobar que es solo texto
//    public static boolean esSoloTexto(String cadena) {
//        // Utilizar una expresión regular para verificar si la cadena contiene solo letras
//        return cadena.matches("[a-zA-Z]+");
//    }
//    //Metodo para comprobar que el email es valido
//    public static boolean esEmailValido(String email) {
//        // Utilizar una expresión regular para verificar si la cadena sigue el formato de correo electrónico
//        String patronEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
//        Pattern pattern = Pattern.compile(patronEmail);
//        Matcher matcher = pattern.matcher(email);
//
//        return matcher.matches();
//    }


    try {
            // Obtener la lista de usuarios desde el archivo en línea
            List<String> lines = obtenerListaDesdeURL("https://codember.dev/data/database_attacked.txt");

            // Lista para almacenar el primer caracter del username de cada usuario inválido
            List<Character> caracteresInvalidos = new ArrayList<>();

            // Iterar sobre cada línea de la lista
            for (String line : lines) {
                // Dividir la línea en partes
                String[] partes = line.split(",");
                
                // Verificar si el usuario es válido
                if (esUsuarioValido(partes)) {
                    // Usuario válido
                    System.out.println("✅ Válido: " + line);
                } else {
                    // Usuario inválido
                    System.out.println("❌ Inválido: " + line);
                    // Obtener el primer caracter del username y agregarlo a la lista
                    if (partes.length >= 2 && partes[1].length() > 0) {
                        caracteresInvalidos.add(partes[1].charAt(0));
                    }
                }
            }

            // Imprimir el mensaje oculto
            System.out.print("Mensaje oculto: ");
            for (Character c : caracteresInvalidos) {
                System.out.print(c);
            }
            System.out.println();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para verificar si un usuario es válido según las reglas
    private static boolean esUsuarioValido(String[] partes) {
        if (partes.length >= 3) {
            String id = partes[0];
            String username = partes[1];
            String email = partes[2];

            // Verificar condiciones para usuario válido
            return isAlphaNumeric(id) && isAlphaNumeric(username) && esEmailValido(email);
        }

        return false;
    }

    // Método para obtener la lista de usuarios desde una URL
    private static List<String> obtenerListaDesdeURL(String urlString) throws IOException {
        URL url = new URL(urlString);
        try (Scanner scanner = new Scanner(url.openStream())) {
            return List.of(scanner.useDelimiter("\\A").next().split("\n"));
        }
    }

    // Método para comprobar que es alfanumérico
    private static boolean isAlphaNumeric(String cadena) {
        return cadena != null && cadena.matches("^[a-zA-Z0-9]*$");
    }

    // Método para comprobar que el email es válido
    private static boolean esEmailValido(String email) {
        String patronEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(patronEmail);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
}