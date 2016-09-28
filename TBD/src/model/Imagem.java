package model;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.sql.Blob;

public class Imagem {
    private int id;
    private String url;
    private String extensao;
    private Blob conteudo;
    private long tamanho;
    private String nome;
    private String metadados;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMetadados() {
        return metadados;
    }

    public void setMetadados(String metadados) {
        this.metadados = metadados;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    //Recebe o caminho absoluto da imagem e joga numa string todas os metadados da imagem
    public void salvarMetadados(String url) throws ImageProcessingException, IOException{
        File imagem = new File(url);
        Metadata metadata = ImageMetadataReader.readMetadata(imagem);
        String dados = "";
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                if(!tag.getTagName().equals("XML Data")){
                    dados += tag.getTagName() + ": " + tag.getDescription() + ";\n";
                }
            }
            if (directory.hasErrors()) {
                for (String error : directory.getErrors()) {
                    System.err.format("ERROR: %s", error);
                }
            }
        }
        this.setMetadados(dados);
    }
    
    //Método construtor que pega o caminho absoluto do arquivo, seta e as variaveis do objeto imagem
    public Imagem(String url) throws IOException, ImageProcessingException{
        File img = new File(url);
        FileNameMap fileNameMap = URLConnection.getFileNameMap();//Classe para pegar a extensão da imagem
        
        this.extensao = fileNameMap.getContentTypeFor(img.getAbsolutePath());//Pegando a extensão da imagem e salvando em 'extensao'
        
        this.setTamanho(img.length());//Setando tamanho da imagem
        this.setExtensao(this.extensao);//Setando extensao da imagem
        this.setUrl(img.getAbsolutePath());//Setando caminho absoluto da imagem
        this.setNome(img.getName());//Setando nome da imagem
        this.setConteudo(null);//Setando conteudo por enquanto como nulo
                               //(O conteudo será setado diretamente no banco através do método adicionar() da classe ImagemDAO)        
        this.salvarMetadados(url);//Seta a string com as especificações dos metadados da imagem        
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExtensao() {
        return extensao;
    }

    public void setExtensao(String extensao) {
        this.extensao = extensao;
    }

    public Blob getConteudo() {
        return conteudo;
    }

    public void setConteudo(Blob conteudo) {
        this.conteudo = conteudo;
    }

    public long getTamanho() {
        return tamanho;
    }

    public void setTamanho(long tamanho) {
        this.tamanho = tamanho;
    }
    
    
}
