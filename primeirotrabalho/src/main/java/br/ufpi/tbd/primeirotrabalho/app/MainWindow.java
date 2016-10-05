package br.ufpi.tbd.primeirotrabalho.app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.drew.imaging.ImageProcessingException;

import br.ufpi.tbd.primeirotrabalho.controller.ImagemController;
import br.ufpi.tbd.primeirotrabalho.model.Imagem;

import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.BoxLayout;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private String[] TableCollumnNames = { "ID", "URL", "Nome", "Extensao", "Conteudo", "Tamanho", "Largura", "Altura",
			"Criação", "ISO", "Abertura", "Velocidade" };
	private JScrollPane scrollPane;
	private JPanel datapanel;
	private JButton btnNewButton;
	private JPanel panel;
	private JButton btnAtualizarTabela;
	private ImagemController ic;
	private JPanel displayPanel;
	private JLabel imglbl;
	private JButton btnRemoverImagem;
	private JButton btnDetalhes;
	/*
	 * private int id; private String url; private String extensao; private Blob
	 * conteudo; private long tamanho; private String nome; private String
	 * metadados; private String largura = ""; private String altura = "";
	 * private String criacao = ""; private String iso = ""; private String
	 * abertura = ""; private String velocidade = "";
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
		ic = new ImagemController();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 464);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane);

		DefaultTableModel model = new DefaultTableModel(TableCollumnNames, 0);

		datapanel = new JPanel();
		// contentPane.add(panel, BorderLayout.NORTH);
		datapanel.setMinimumSize(new Dimension(100, 100));
		datapanel.setBorder(new TitledBorder(null, "Dados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		datapanel.setLayout(new BorderLayout(0, 0));

		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int id = (int) table.getValueAt(table.getSelectedRow(), 0);
				Imagem img = null;
				try {
					img = ic.getImg(id);
					Blob conteudo = img.getConteudo();
					byte[] imageBytes = conteudo.getBytes(1, (int) conteudo.length());
					BufferedImage myPicture = ImageIO.read(new ByteArrayInputStream(imageBytes));
					imglbl.setIcon(new ImageIcon(myPicture));
					//imglbl.setText(img.getNome());
					imglbl.repaint();
					System.out.println(img.getNome());

				} catch (ClassNotFoundException | ImageProcessingException | SQLException | IOException e) {
					e.printStackTrace();
				}
			}
		});
		scrollPane = new JScrollPane(table);
		datapanel.add(scrollPane, BorderLayout.CENTER);

		splitPane.setLeftComponent(datapanel);

		panel = new JPanel();
		datapanel.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		btnNewButton = new JButton("Adicionar Nova Imagem");
		panel.add(btnNewButton);

		btnAtualizarTabela = new JButton("Atualizar Tabela");
		btnAtualizarTabela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateTable();
			}

		});
		panel.add(btnAtualizarTabela);

		btnRemoverImagem = new JButton("Remover Imagem");
		btnRemoverImagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id = (int) table.getValueAt(table.getSelectedRow(), 0);
				try {
					ic.deletar(id);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				updateTable();
			}
		});
		panel.add(btnRemoverImagem);

		btnDetalhes = new JButton("Detalhes");
		btnDetalhes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = (int) table.getValueAt(table.getSelectedRow(), 0);
				Imagem img;
				try {
					img = ic.getImg(id);
					DetailsWindow dw = new DetailsWindow(img.getMetadados());
					dw.setVisible(true);
				} catch (ClassNotFoundException | ImageProcessingException | SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel.add(btnDetalhes);

		displayPanel = new JPanel(null);
		displayPanel.setBorder(new TitledBorder(null, "Display", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane.setRightComponent(displayPanel);
		displayPanel.setLayout(new BorderLayout(0, 0));

		imglbl = new JLabel("...");
		displayPanel.add(imglbl, BorderLayout.CENTER);
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				int ret = fc.showOpenDialog(null);
				if (ret == fc.APPROVE_OPTION) {
					File f = fc.getSelectedFile();
					String path = f.getPath();
					System.out.println(path);
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
				updateTable();
			}
		});
		updateTable();

	}

	private void updateTable() {
		ArrayList<Imagem> allImg = null;
		try {
			allImg = ic.getAllImg();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ImageProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DefaultTableModel mdl = new DefaultTableModel(TableCollumnNames, allImg.size());
		int i = 0;
		for (Imagem img : allImg) {
			System.out.println(img.getExtensao());
			mdl.setValueAt(img.getId(), i, 0);
			mdl.setValueAt(img.getUrl(), i, 1);
			mdl.setValueAt(img.getNome(), i, 2);
			mdl.setValueAt(img.getExtensao(), i, 3);
			// mdl.setValueAt(0, i, 4);
			mdl.setValueAt(img.getConteudo(), i, 4);
			mdl.setValueAt(img.getTamanho(), i, 5);
			mdl.setValueAt(img.getLargura(), i, 6);
			mdl.setValueAt(img.getAltura(), i, 7);
			mdl.setValueAt(img.getCriacao(), i, 8);
			mdl.setValueAt(img.getIso(), i, 9);
			mdl.setValueAt(img.getAbertura(), i, 10);
			mdl.setValueAt(img.getVelocidade(), i, 11);
			i++;
		}
		table.setModel(mdl);
	}
}
