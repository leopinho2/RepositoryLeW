package br.com.sgbde;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import net.miginfocom.swing.MigLayout;
import br.com.util.RicardoUtils;

public class SGBDE {

	JLabel labelPessoa;
	JComboBox comboPessoa;	
	JLabel labelDataInicio;
	JTextField fieldDataInicio;	
	JLabel labelDataFim;
	JTextField fieldDataFim;	
	JLabel labelNome;
	JLabel labelValorNome;	
	JLabel labelRG;
	JLabel labelValorRG;	
	JButton botaoCadastrarPessoa;			
	JButton botaoCadastrarVez;			
	JButton botaoMostrarTodos;
	JButton botaoExibirInfo;
	JButton botaoEsconderInfo;
	JButton botaoDataInicio;
	JButton botaoDataFim;
	JLabel labelEstatisticas;
	JLabel labelTotalHoras;
	JLabel labelValorTotalHoras;
	JLabel labelTotalVezes;
	JLabel labelValorTotalVezes;
	JLabel labelTempoMedio;
	JLabel labelValorTempoMedio;
	JLabel labelGrafico;
	JFrame janela;
	SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat bancoData = new SimpleDateFormat("yyyy-MM-dd");
	JPanel painelDados = new JPanel();
	
	public SGBDE(){
		
		JPanel painelCadastrar = new JPanel();
		JPanel painelProcurar = new JPanel();
		
		Color cor = new Color(230, 232, 250);		
		Color corMarrom = new Color(142,107,35);	
				
        TitledBorder bordaCadastrar = BorderFactory.createTitledBorder("Cadastrar");    	
        TitledBorder bordaProcurar = BorderFactory.createTitledBorder("Procurar");    	
        TitledBorder bordaDados = BorderFactory.createTitledBorder("Dados");    	
		
		labelPessoa = new JLabel("Pessoa:     ");
		comboPessoa = new JComboBox(RicardoUtils.preencheCombo().toArray());
		comboPessoa.setBackground(Color.CYAN);
		
		labelDataInicio = new JLabel("Data Início:");
		fieldDataInicio = new JTextField(20);
		
		labelDataFim = new JLabel("Data Fim:   ");
		fieldDataFim = new JTextField(20);
		
		labelNome = new JLabel("Nome:");
		labelValorNome = new JLabel();
		
		labelRG = new JLabel("RG:");
		labelValorRG = new JLabel();
		
		botaoCadastrarPessoa = new JButton("Cadastrar Pessoa");		
		botaoCadastrarPessoa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new CadastrarPessoa(janela);
			}
		});
		
		botaoCadastrarVez = new JButton("Cadastrar Vez");
		botaoCadastrarVez.setForeground(cor);
		botaoCadastrarVez.setBackground(corMarrom);
		botaoCadastrarVez.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new CadastrarVez(janela);
			}
		});
		
		botaoMostrarTodos = new JButton("Mostrar Todos");
		botaoMostrarTodos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mostrarTodos();
			}
		});
		
		botaoExibirInfo = new JButton("Exibir Info");
		botaoExibirInfo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				exibirInfo();
			}
		});
		
		botaoEsconderInfo = new JButton("Esconder Info");
		botaoEsconderInfo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				esconderInfo();
			}
		});
		
		botaoDataInicio = new JButton("");
		botaoDataInicio.setBackground(Color.yellow);
		botaoDataInicio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				fieldDataInicio.setText(formataData.format( Calendar.getInstance().getTime()));
			}
		});
		
		botaoDataFim = new JButton("");
		botaoDataFim.setBackground(Color.yellow);
		botaoDataFim.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				fieldDataFim.setText(formataData.format( Calendar.getInstance().getTime()));
			}
		});
		
		labelEstatisticas = new JLabel("Estatísticas:");
		labelEstatisticas.setFont( new Font("Courier", Font.BOLD, 20));
		labelTotalHoras = new JLabel("Total de Horas:");
		labelValorTotalHoras = new JLabel();
		labelTotalVezes = new JLabel("Total de Vezes:");
		labelValorTotalVezes = new JLabel();
		labelTempoMedio = new JLabel("Tempo Médio:");
		labelValorTempoMedio = new JLabel();
		labelGrafico = new JLabel("Gráfico:");
		labelGrafico.setFont( new Font("Courier", Font.BOLD, 20));
		
		janela = new JFrame("SGBDE");	
				
		janela.setIconImage(new ImageIcon(getClass().getResource("Imagens/bixinho2.png")).getImage());
		
		Container container = janela.getContentPane();
		container.setLayout(new MigLayout());
		container.setBackground(cor);
						
		painelCadastrar.setLayout(new MigLayout());
		painelCadastrar.setBorder(bordaCadastrar);
		painelCadastrar.setBackground(cor);
		
		painelProcurar.setLayout(new MigLayout());
		painelProcurar.setBorder(bordaProcurar);
		painelProcurar.setBackground(cor);
		
		painelDados.setLayout(new MigLayout());
		painelDados.setBorder(bordaDados);
		painelDados.setBackground(cor);
		
		painelCadastrar.add(botaoCadastrarPessoa, "width :100: , height :30:");
		painelCadastrar.add(botaoCadastrarVez, "width :100: , height :30:");
		painelCadastrar.add(botaoMostrarTodos, "width :100: , height :30:");
		
		painelProcurar.add(labelPessoa, "width :10:, split 2");
		painelProcurar.add(comboPessoa, "width 50::");
		painelProcurar.add(botaoExibirInfo, "width :100:, height :30:, wrap");
		painelProcurar.add(labelDataInicio, "width :10:, split 3");
		painelProcurar.add(fieldDataInicio, "width 100::");
		painelProcurar.add(botaoDataInicio, "width 20::, wrap");
		painelProcurar.add(labelDataFim, "width :10:, split 3");
		painelProcurar.add(fieldDataFim, "width 100::");
		painelProcurar.add(botaoDataFim, "width 20::");
		
		painelDados.add(labelNome, "width :10:, split 5");
		painelDados.add(labelValorNome, "width :100:");
		painelDados.add(labelRG, "width :10:");		
		painelDados.add(labelValorRG, "width :100:");		
		painelDados.add(botaoEsconderInfo, "width :100:, height :30:, wrap");		
		painelDados.add(labelEstatisticas, "width :10:, center, wrap");
		painelDados.add(labelTotalHoras, "width :10:, split 2");
		painelDados.add(labelValorTotalHoras, "width :100:, wrap");
		painelDados.add(labelTotalVezes, "width :10:, split 2");
		painelDados.add(labelValorTotalVezes, "width :100:, wrap");
		painelDados.add(labelTempoMedio, "width :10:, split 2");
		painelDados.add(labelValorTempoMedio, "width :100:, wrap");
		painelDados.add(labelGrafico, "width :10:, wrap, center");

		container.add(painelCadastrar, "wrap");
		container.add(painelProcurar, "wrap");
		container.add(painelDados);
		
		janela.setSize(415, 290);		
		janela.setLocation(360, 0);		
		janela.setVisible(true);		
//		janela.setResizable(false);		
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void exibirInfo(){
		Statement s = Conexao.conectar();
		String sql = "select * from dbe_paciente where nome = '" + 
			comboPessoa.getSelectedItem() + "';";
		int id = 0;
		try {
			ResultSet r = s.executeQuery(sql);
			while(r.next()){
				labelValorNome.setText(r.getString("nome"));
				labelValorRG.setText(r.getString("rg"));
				id = r.getInt("id");
			}
			
			if((fieldDataInicio != null && !fieldDataInicio.getText().equals("")) &&
				(fieldDataFim != null && !fieldDataFim.getText().equals(""))){
				
				java.sql.Date dataJDBCInicio = new java.sql.Date(formataData.parse(fieldDataInicio.getText()).getTime());			        
			    String dataISOInicio = bancoData.format(dataJDBCInicio);
			    java.sql.Date dataJDBCFim = new java.sql.Date(formataData.parse(fieldDataFim.getText()).getTime());			        
			    String dataISOFim = bancoData.format(dataJDBCFim);
				
				sql = "select * from dbe_vez where date(data) >= '" + 
					dataISOInicio + "' and date(data) <= '" + 
					dataISOFim + "' and paciente_id = " + id;
				
			} else if((fieldDataInicio != null && !fieldDataInicio.getText().equals(""))){
				
				java.sql.Date dataJDBCInicio = new java.sql.Date(formataData.parse(fieldDataInicio.getText()).getTime());			        
			    String dataISOInicio = bancoData.format(dataJDBCInicio);
				
				sql = "select * from dbe_vez where date(data) >= '" + 
				dataISOInicio + "' and paciente_id = " + id;
				
			} else if((fieldDataFim != null && !fieldDataFim.getText().equals(""))) {
				
				java.sql.Date dataJDBCFim = new java.sql.Date(formataData.parse(fieldDataFim.getText()).getTime());			        
			    String dataISOFim = bancoData.format(dataJDBCFim);
			    
				sql = "select * from dbe_vez where date(data) <= '" + 
				dataISOFim + "' and paciente_id = " + id;
				
			} else {			
				sql = "select tempo from dbe_vez where paciente_id = " + id;			
			}
			
			r = s.executeQuery(sql);
			
			int totalTempo = 0;
			int quantidadeVezes = 0;
			
			while(r.next()){
				totalTempo += r.getInt("tempo");
				quantidadeVezes++;
			}
			
			if(quantidadeVezes > 0){
				labelValorTotalHoras.setText(RicardoUtils.converteTempoEmString(totalTempo));
				labelValorTotalVezes.setText("" + quantidadeVezes);
				labelValorTempoMedio.setText(RicardoUtils.converteTempoEmString((totalTempo / quantidadeVezes)));
			} else {
				labelValorTotalHoras.setText("00:00");
				labelValorTotalVezes.setText("0");
				labelValorTempoMedio.setText("00:00");
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		criaGrafico(painelDados, id);
		
		janela.setSize(415, 750);
	}
	
	public void esconderInfo(){		
		janela.setSize(415, 290);
	}
	
	public void mostrarTodos(){		
		Statement s = Conexao.conectar();
		String sql = "select * from dbe_paciente";
		ArrayList<String> res = new ArrayList<String>();		
		
		String[] colunas = {"Nome", "RG"};
				
		try {			
			ResultSet r = s.executeQuery(sql);
			while(r.next()){
				res.add(r.getString("nome") + ";" + r.getString("rg"));				
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		String[][]  valores = new String[res.size()][2];
		int cont = 0;
		
		for (String string : res) {
			String[] parte = string.split(";");
			
			valores[cont][0] = parte[0];
			valores[cont][1] = parte[1];
			
			cont++;
		}
		
		JTable table = new JTable(valores, colunas);
		Dimension d = new Dimension(80,80);
		table.setPreferredScrollableViewportSize(d);
		table.setEnabled(false);
		
		JOptionPane.showMessageDialog(null,new JScrollPane(table),"Pacientes", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void main(String args[]){
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
			System.out.println(e);
		} 
		
		new SGBDE();
		
	}
	
	private CategoryDataset createDataset(int id) { 
		DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
		
		Statement s = Conexao.conectar();
		String sql = "select data,tempo from dbe_vez where paciente_id = " + id + 
						" order by data asc;";
		ArrayList<BeanDataTempo> dataTempo = new ArrayList<BeanDataTempo>();				
		int[] vezes = null;		
		
		try {			
			ResultSet r = s.executeQuery(sql);
			while(r.next()){
				BeanDataTempo bean = new BeanDataTempo();
				bean.setData(r.getString("data"));
				bean.setTempo(r.getInt("tempo"));
				dataTempo.add(bean);
			}			
		} catch(Exception e){
			e.printStackTrace();
		}		
		
		ArrayList<BeanDataTempo> valores = new ArrayList<BeanDataTempo>();
		String dataAtual = "";
		boolean mesmaData = true;
		int total = 0;
		for (BeanDataTempo bean : dataTempo) {
			if(dataAtual.equals("") || bean.getData().equals(dataAtual)){
				total
				
				mesmaData = true;
			}			
		}
		
		
		int i = 0;
		for (String string : datas) {
			dataset.addValue(vezes[i], "presunto", "Tempo" + i);
			i++;
		}
		return dataset; 
	}
	
	public void criaGrafico(JPanel painel, int id) {
		CategoryDataset cds = createDataset(id);
		String titulo = "Gráfico de Teste";
		String eixoy = "Valores";
		String textoLegenda = "Legenda:";
		boolean legenda = true;
		boolean tooltips = true;
		boolean urls = true;
//		JFreeChart graf = ChartFactory.createBarChart3D(titulo, 
//				textoLegenda, eixoy, cds, PlotOrientation.VERTICAL, legenda, tooltips, urls);
		JFreeChart graf = ChartFactory.createLineChart(titulo ,textoLegenda, 
				eixoy, cds,  PlotOrientation.VERTICAL, legenda, tooltips, urls);
		ChartPanel myChartPanel = new ChartPanel(graf, true);
		myChartPanel.setSize(painel.getWidth(),painel.getHeight());
		myChartPanel.setVisible(true);
		painel.add(myChartPanel); 
		painel.revalidate();
		painel.repaint(); 
	}

}
