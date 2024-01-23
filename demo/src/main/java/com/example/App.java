package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        try {
            System.out.println("Server in avvio!");
            ServerSocket server = new ServerSocket(8080);
            
            while (true) {
                Socket s = server.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                DataOutputStream out = new DataOutputStream(s.getOutputStream());

                String richiesta = in.readLine();
                String riga[] = richiesta.split(" ");
                String path = riga[1];
                //path = path.substring(1);
                System.out.println("----" + path + "----");
                /*
                do {
                    String line = in.readLine();
                    System.out.println(line);
                    if (line == null || line.isEmpty())
                        break;
                } while (true);

                String it[] = path.split("\\.");

                String file = it[3];

                if(file.equals("html")){
                    sendHTML(out);
                }

                if(file.equals("jpeg")){
                    sendJPEG(out);
                }

                if(file.equals("css")){
                    sendCSS(out);
                }
                */

                if (path.equals("/classe.json")) {

                    //File myObject = new File("/classe.json");

                    Studente s1 = new Studente("01/02/2003", "Alessio", "Bruni");
                    Studente s2 = new Studente("10/03/2005", "Marco", "Ascanio");
                    Studente s3 = new Studente("01/01/2005", "Jim", "Carrey");
                    Studente s4 = new Studente("05/04/2006", "Matteo", "Falli");
                    Studente s5 = new Studente("09/07/2005", "Eros", "Ramazzotti");
            
                    ArrayList<Studente> listastudenti = new ArrayList();

                    listastudenti.add(s1);
                    listastudenti.add(s2);
                    listastudenti.add(s3);
                    listastudenti.add(s4);
                    listastudenti.add(s5);

                    Aula classe = new Aula("IA", "5", "03-TT", listastudenti);


                    ObjectMapper objectMapper = new ObjectMapper();
                    String st = objectMapper.writeValueAsString(classe);
                    System.out.println(st);

                    out.writeBytes("HTTP/1.1 200 OK \n");
                    out.writeBytes("Content-Lenght: " + st.length()+ "\n");
                    out.writeBytes("Server: Java HTTP Server from Pingu: 1.0 \n");
                    out.writeBytes("Date: " + new Date()+ "\n");
                    out.writeBytes("Content-Type: application/json; charset=utf-8 \n" );
                    out.writeBytes("\n");
                    out.writeBytes(st + "\n");
                }
                
                s.close();
            }
            // server.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("errore durante l'instanza del server");
            System.exit(1);
        }
    }


    public static void sendHTML(PrintWriter out){

        try{
            File myObject = new File("test.html");
            Scanner reader = new Scanner(myObject);
            
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Lenght: " + myObject.length());
            out.println("Server: Java HTTP Server from Pingu: 1.0");
            out.println("Date: " + new Date());
            out.println("Content-Type: text/html; charset=utf-8");
            
            out.println();

            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                out.println(data);
            }
            reader.close();
        } catch(FileNotFoundException e){
            out.println("HTTP/1.1 404 NON OK");
        }
    }


     public static void sendJPEG(PrintWriter out){

        try{
            File myObject = new File(".jpeg");
            Scanner reader = new Scanner(myObject);
            
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Lenght: " + myObject.length());
            out.println("Server: Java HTTP Server from Pingu: 1.0");
            out.println("Date: " + new Date());
            out.println("Content-Type: image/jpeg; charset=utf-8");
            
            out.println();

            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                out.println(data);
            }
            reader.close();
        } catch(FileNotFoundException e){
            out.println("HTTP/1.1 404 NON OK");
        }
    }

     public static void sendCSS(PrintWriter out){

        try{
            File myObject = new File("style.css");
            Scanner reader = new Scanner(myObject);
            
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Lenght: " + myObject.length());
            out.println("Server: Java HTTP Server from Pingu: 1.0");
            out.println("Date: " + new Date());
            out.println("Content-Type: text/css; charset=utf-8");
            
            out.println();

            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                out.println(data);
            }
            reader.close();
        } catch(FileNotFoundException e){
            out.println("HTTP/1.1 404 NON OK");
        }
    }

    public static void sendJson(PrintWriter out){

        try{
            File myObject = new File("file.json");
            Scanner reader = new Scanner(myObject);
            
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Lenght: " + myObject.length());
            out.println("Server: Java HTTP Server from Pingu: 1.0");
            out.println("Date: " + new Date());
            out.println("Content-Type: text/css; charset=utf-8");
            
            out.println();

            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                out.println(data);
            }
            reader.close();
        } catch(FileNotFoundException e){
            out.println("HTTP/1.1 404 NON OK");
        }
    }
}
