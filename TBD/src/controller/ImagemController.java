package controller;

import com.drew.imaging.ImageProcessingException;
import dao.ImagemDAO;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import model.Imagem;

public class ImagemController {
    
    private final ImagemDAO idao = new ImagemDAO();

    public void adicionar(Imagem img) throws SQLException, ClassNotFoundException, FileNotFoundException{
        idao.adicionar(img);
    }
    
    public Imagem getImg(int id) throws SQLException, ClassNotFoundException, IOException, ImageProcessingException{
        return idao.getImg(id);
    }
}
