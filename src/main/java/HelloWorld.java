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


    static {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(new File("/Users/cristianogutierrez/Pictures/dexter_001.gif"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finalFileInputStream = fileInputStream;
    }
    final static FileInputStream finalFileInputStream ;
    public static void main(String[] args) {
        new TrayIconBuild().tray();
        ipAddress("127.0.0.1");


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

            return IOUtils.toByteArray(finalFileInputStream);

        });
        get("/add.gif", (req, res) -> {
            res.header("content-type", "image/gif");
            res.header("Access-Control-Allow-Origin", "*");
            res.header("Access-Control-Request-Headers", "*");
            res.header("Access-Control-Allow-Methods", "*");



            res.status(200);
           // display.setVisible(true);
            //calc.setAlwaysOnTop(true);

            calc.requestFocus();


            display.requestFocus();
            calc.toFront();
            display.addArquivoLocal();
            return IOUtils.toByteArray(finalFileInputStream);

        });
        get("/assinar.gif", (req, res) -> {
            res.header("content-type", "image/gif");
            res.header("Access-Control-Allow-Origin", "*");
            res.header("Access-Control-Request-Headers","*");
            res.header("Access-Control-Allow-Methods","*");
            res.status(200);

            display.assinarArquivos();
            return IOUtils.toByteArray(finalFileInputStream);

        });


        post("/upload", new Route(){
            @Override
            public Object handle(Request request, Response response) throws Exception {

                MultipartConfigElement multipartConfigElement = new MultipartConfigElement("/tmp");
                request.raw().setAttribute("org.eclipse.jetty.multipartConfig", multipartConfigElement);

                Part part = request.raw().getPart("arquivo");

                final Path path = Paths.get("/Users/cristianogutierrez/Documents/"+part.getSubmittedFileName());
                try (final InputStream in = part.getInputStream()) {
                    Files.copy(in, path);
                }

                return "OK";
            }
        });
        post("/upload2", new Route(){
            @Override
            public Object handle(Request request, Response response) throws Exception {

                MultipartConfigElement multipartConfigElement = new MultipartConfigElement("/tmp");
                request.raw().setAttribute("org.eclipse.jetty.multipartConfig", multipartConfigElement);

                Part part = request.raw().getPart("arquivo");

                final Path path = Paths.get("/Users/cristianogutierrez/Documents/"+part.getSubmittedFileName());
                try (final InputStream in = part.getInputStream()) {
                    Files.copy(in, path);
                }

                return "OK";
            }
        });
    }

}