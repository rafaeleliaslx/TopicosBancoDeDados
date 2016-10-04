package br.ufpi.tbd.primeirotrabalho.teste;


import com.drew.imaging.ImageProcessingException;
import br.ufpi.tbd.primeirotrabalho.controller.ImagemController;

import java.awt.FileDialog;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFileChooser;
import javax.swing.plaf.FileChooserUI;

import br.ufpi.tbd.primeirotrabalho.model.Imagem;

public class Main {
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException, ImageProcessingException{
        JFileChooser fc = new JFileChooser();
        int ret = fc.showOpenDialog(null);
        if(ret == fc.APPROVE_OPTION){
        	File f = fc.getSelectedFile();
        	String path = f.getPath();
        	System.out.println(path);
        	ImagemController ic = new ImagemController();
            Imagem img = new Imagem(path);
            ic.adicionar(img);
        }
        
    	
    	//String path = "/home/rafaeleliaslx/workspace/TopicosBancoDeDados/TBD/src/teste/imgteste.jpg";
        
        //ic.getImg(7);
    }
    
}
