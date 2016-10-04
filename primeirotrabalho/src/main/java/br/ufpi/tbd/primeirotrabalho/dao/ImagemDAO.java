package br.ufpi.tbd.primeirotrabalho.dao;

import com.drew.imaging.ImageProcessingException;
import br.ufpi.tbd.primeirotrabalho.config.Conexao;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import br.ufpi.tbd.primeirotrabalho.model.Imagem;

public class ImagemDAO {    
    
    private PreparedStatement ps;
    
    //Recebe o objeto imagem e faz a inserção dos seus dados no BD
    public void adicionar(Imagem img) throws SQLException, ClassNotFoundException, FileNotFoundException{
        Conexao.conectar();
        File imgfile = new File(img.getUrl());//Cria um arquivo com o path dado(no caso uma imagem)
        FileInputStream fin = new FileInputStream(imgfile);//Cria um objeto da classe FileInputStream com o arquivo criado
        
        System.out.println(img.getTamanho());
        
        ps = Conexao.conexao.prepareStatement("INSERT INTO img VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");//Prepara a query de inserção
        ps.setInt(1, 0);//Seta o valor do ID(no caso é desnecessário. Feito só pra nada mesmo)
        ps.setString(2, img.getNome());////Seta nome da imagem na segunda coluna da tabela 'img'
        ps.setString(3, img.getCriacao());
        ps.setString(4, img.getExtensao());//Seta extensao da imagem na quarta coluna da tabela 'img'
        ps.setLong(5, img.getTamanho());//Seta tamanho da imagem na sexta coluna da tabela 'img'
        ps.setString(6, img.getAltura());
        ps.setString(7, img.getLargura());
        ps.setString(8, img.getIso());
        ps.setString(9, img.getAbertura());
        ps.setString(10, img.getVelocidade());
        ps.setBinaryStream(11,(InputStream)fin,(int)imgfile.length());//Seta conteudo da imagem na quinta coluna da tabela 'img'
        ps.setString(12, img.getMetadados());//Seta string com metadados da imagem na sétima coluna da tabela 'img'
        ps.setString(13, img.getUrl());//Seta url da imagem na terceira coluna da tabela 'img'
               
        ps.execute();//Executa a query para inserção no BD
    }

    //Recebe o ID de uma imagem e retorna a imagem o objeto imagem que contem o ID dado
    public Imagem getImg(int id) throws SQLException, ClassNotFoundException, IOException, ImageProcessingException{
        Conexao.conectar();//Conecta ao BD
        String query = "SELECT * FROM img where id = " + id;//Criando query
        ps = Conexao.conexao.prepareStatement(query);//Prepara query
        ResultSet rs = ps.executeQuery(query);//Executa query e joga resultado na variavel 'rs'
        rs.next();//Vai para o primeiro(e único) registro
        
        Imagem img = new Imagem(rs.getString("url"));//Cria um objeto imagem
        img.setId(rs.getInt("id"));
        img.setNome(rs.getString("nome"));//Seta nome da imagem com valor retornado do banco
        img.setUrl(rs.getString("url"));
        img.setExtensao(rs.getString("extensao"));
        img.setConteudo(rs.getBlob("conteudo"));
        img.setTamanho(rs.getLong("tamanho"));
        img.setMetadados(rs.getString("propriedades"));////Seta metadados da imagem com valor retornado do banco
        img.setAltura(rs.getString("altura"));
        img.setLargura(rs.getString("largura"));
        img.setCriacao(rs.getString("criacao"));
        img.setIso(rs.getString("iso"));
        img.setAbertura(rs.getString("abertura"));
        img.setVelocidade(rs.getString("velocidade"));
        
        System.out.println(img.getMetadados());
        //JOptionPane.showMessageDialog(null, img.getMetadados());        
        return img;
    }
}
