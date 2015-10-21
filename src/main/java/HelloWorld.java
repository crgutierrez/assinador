import br.jus.tjro.assinadortjro.AssinadorTjro;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.utils.IOUtils;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.Part;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static spark.Spark.*;

public class HelloWorld {

     static byte[] imagem;
    static {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(new File("/Users/cristianogutierrez/Pictures/dexter_001.gif"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finalFileInputStream = fileInputStream;
        try {
            imagem = IOUtils.toByteArray(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    final static FileInputStream finalFileInputStream ;

    public static void main(String[] args) {
        new TrayIconBuild().tray();
       // ipAddress("127.0.0.1");


        AssinadorTjro display = new AssinadorTjro();

        JFrame calc = new JFrame();


        display.init();
        display.setSize(240, 480);
        calc.setSize(240, 480);
        calc.add(display);
        calc.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        calc.pack();



        get("/config.gif", (req, res) -> {

            res.header("content-type", "image/gif");
            res.header("Access-Control-Allow-Origin", "*");
            res.header("Access-Control-Request-Headers","*");
            res.header("Access-Control-Allow-Methods","*");
           // res.header("Access-Control-Allow-Origin", "http://172.10.0.183:9000");


//                    calc.setVisible(true);
            res.status(200);
            calc.toFront();
            calc.toFront();
            calc.requestFocus();
            display.mostrarConfiguracao();
            display.requestFocus();
            calc.toFront();

            return imagem;

        });
        get("/add.gif", (req, res) -> {
            res.header("content-type", "image/gif");
            res.header("Access-Control-Allow-Origin", "*");
            res.header("Access-Control-Request-Headers", "*");
            res.header("Access-Control-Allow-Methods", "*");



            res.status(200);
           // display.setVisible(true);
            //calc.setAlwaysOnTop(true);

          //  calc.requestFocus();


         //   display.requestFocus();
         //   calc.toFront();Runnable task1 = new Runnable(){

                display.addArquivoLocal();



            return imagem;

        });
        get("/assinar.gif", (req, res) -> {
            res.header("content-type", "image/gif");
            res.header("Access-Control-Allow-Origin", "*");
            res.header("Access-Control-Request-Headers","*");
            res.header("Access-Control-Allow-Methods","*");
            res.status(200);

            display.assinarArquivos();

            return imagem;

        });
        get("/show.gif", (req, res) -> {
            res.header("content-type", "image/gif");
            res.header("Access-Control-Allow-Origin", "*");
            res.header("Access-Control-Request-Headers","*");
            res.header("Access-Control-Allow-Methods","*");
            res.status(200);
                display.setVisible(true);

            return imagem;

        });


        post("/upload", new Route(){
            @Override
            public Object handle(Request request, Response response) throws Exception {

                MultipartConfigElement multipartConfigElement = new MultipartConfigElement("/tmp");
                request.raw().setAttribute("org.eclipse.jetty.multipartConfig", multipartConfigElement);

                Part part = null;
                try {
                    part = request.raw().getPart("arquivo");
                    final Path path = Paths.get("/Users/cristianogutierrez/Documents/"+part.getSubmittedFileName());
                    try (final InputStream in = part.getInputStream()) {
                        Files.copy(in, path);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Erro:"+e.getMessage();
                } catch (ServletException e) {
                    e.printStackTrace();
                    return "Erro:"+e.getMessage();
                }

                return "OK";
            }
        });
        post("/upload2", new Route(){
            @Override
            public Object handle(Request request, Response response)  {

                MultipartConfigElement multipartConfigElement = new MultipartConfigElement("/tmp");
                request.raw().setAttribute("org.eclipse.jetty.multipartConfig", multipartConfigElement);

                Part part = null;
                try {
                    part = request.raw().getPart("arquivo");
                    final Path path = Paths.get("/Users/cristianogutierrez/Documents/"+part.getSubmittedFileName());
                    try (final InputStream in = part.getInputStream()) {
                        Files.copy(in, path);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Erro:"+e.getMessage();
                } catch (ServletException e) {
                    e.printStackTrace();
                    return "Erro:"+e.getMessage();
                }



                return "OK";
            }
        });
    }

}