/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package teste;


import com.drew.imaging.ImageProcessingException;
import controller.ImagemController;
import java.io.IOException;
import java.sql.SQLException;
import model.Imagem;

public class Main {
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException, ImageProcessingException{
        String path = "/home/rafaeleliaslx/workspace/TopicosBancoDeDados/TBD/src/teste/imgteste.dng";
        ImagemController ic = new ImagemController();
        Imagem img = new Imagem(path);
        ic.adicionar(img);
      //  ic.getImg(6);
    }
    
}
