import br.jus.tjro.assinadortjro.AssinadorTjro;
import spark.utils.IOUtils;

import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static spark.Spark.*;

public class HelloWorld {
    public static void main(String[] args) {
        new TrayIconBuild().tray();
         FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(new File("/Users/cristianogutierrez/Pictures/dexter_001.gif"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        final FileInputStream finalFileInputStream = fileInputStream;

        AssinadorTjro a =new AssinadorTjro();
        a.mostrarConfiguracao();
        get("/hello.gif", (req, res) ->{
            res.header("content-type", "image/gif");
           res.header("Access-Control-Allow-Origin", "http://172.0.0.100:9000");
        //    res.header("Access-Control-Allow-Origin", "*");

            //JOptionPane.showInputDialog("teste");


            return IOUtils.toByteArray(finalFileInputStream);

        });
    }
}