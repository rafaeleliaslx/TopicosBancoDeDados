package br.ufpi.tbd.primeirotrabalho.teste;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.sql.Blob;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.drew.imaging.ImageProcessingException;

import br.ufpi.tbd.primeirotrabalho.controller.ImagemController;
import br.ufpi.tbd.primeirotrabalho.model.Imagem;

import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private String[] TableCollumnNames = {"ID", "URL", "Nome", "Extensao", "Conteudo", "Tamanho", "Largura", "Altura", "Criação", "ISO", "Abertura", "Velocidade"};
	private JScrollPane scrollPane;
	private JPanel datapanel;
	private JButton btnNewButton;
	private JPanel panel;
	private JButton btnAtualizarTabela;
	/*
    private int id;
    private String url;
    private String extensao;
    private Blob conteudo;
    private long tamanho;
    private String nome;
    private String metadados;
    private String largura = "";
    private String altura = "";
    private String criacao = "";
    private String iso = "";
    private String abertura = "";
    private String velocidade = "";
	*/

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 464);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane);
		
		DefaultTableModel model = new DefaultTableModel(TableCollumnNames,0);
		
		datapanel = new JPanel();
		//contentPane.add(panel, BorderLayout.NORTH);
		datapanel.setMinimumSize(new Dimension(100, 100));
		datapanel.setBorder(new TitledBorder(null, "Dados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		datapanel.setLayout(new BorderLayout(0, 0));
		
				table = new JTable(model);
				scrollPane = new JScrollPane(table);
				datapanel.add(scrollPane, BorderLayout.CENTER);
		
				splitPane.setLeftComponent(datapanel);
				
				panel = new JPanel();
				datapanel.add(panel, BorderLayout.NORTH);
				panel.setLayout(new GridLayout(0, 1, 0, 0));
				
				btnNewButton = new JButton("Adicionar Nova Imagem");
				panel.add(btnNewButton);
				
				btnAtualizarTabela = new JButton("Atualizar Tabela");
				panel.add(btnAtualizarTabela);
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						JFileChooser fc = new JFileChooser();
				        int ret = fc.showOpenDialog(null);
				        if(ret == fc.APPROVE_OPTION){
				        	File f = fc.getSelectedFile();
				        	String path = f.getPath();
				        	System.out.println(path);
				        	ImagemController ic = new ImagemController();
				            Imagem img;
							try {
								img = new Imagem(path);
								ic.adicionar(img);
							} catch (ImageProcessingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				        }
					}
				});
		
		
		
		
		
	}

}
