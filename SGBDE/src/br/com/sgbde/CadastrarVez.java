package br.com.sgbde;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;
import br.com.util.RicardoUtils;

public class CadastrarVez {
	
	JLabel labelPessoa;		
	JComboBox comboPessoa;
	JLabel labelData;
	JTextField fieldData;	
	JLabel labelIda;
	JTextField fieldIda;	
	JLabel labelVolta;
	JTextField fieldVolta;	
	JButton botaoCadastrar;			
	JButton botaoData;			
	JButton botaoHoraIda;			
	JButton botaoHoraVolta;			
	JDialog janela;
	SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat formataHora = new SimpleDateFormat("HH:mm");
	SimpleDateFormat bancoData = new SimpleDateFormat("yyyy_MM_dd");	
	
	public CadastrarVez(JFrame frame){
		
		JPanel painel = new JPanel();
		
		Color cor = new Color(230, 232, 250);	
		Color corMarrom = new Color(142,107,35);
		
		TitledBorder borda = BorderFactory.createTitledBorder("Cadastrar Vez");
		
		labelPessoa = new JLabel("Pessoa:");
		labelPessoa.setForeground(corMarrom);
		comboPessoa = new JComboBox(RicardoUtils.preencheCombo().toArray());
		comboPessoa.setBackground(Color.CYAN);
		
		labelData = new JLabel("Data:");
		labelData.setForeground(corMarrom);
		fieldData = new JTextField(20);
		
		labelIda = new JLabel("Ida:");
		labelIda.setForeground(corMarrom);
		fieldIda = new JTextField(20);
		
		labelVolta = new JLabel("Volta:");
		labelVolta.setForeground(corMarrom);
		fieldVolta = new JTextField(20);
		
		botaoCadastrar = new JButton("Cadastrar");
		botaoCadastrar.setForeground(cor);
		botaoCadastrar.setBackground(corMarrom);	
		botaoCadastrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cadastrarVez();
			}
		});
		
		botaoData = new JButton();
		botaoData.setBackground(Color.yellow);
		botaoData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fieldData.setText(formataData.format( Calendar.getInstance().getTime()));
			}
		});
		
		botaoHoraIda = new JButton();			
		botaoHoraIda.setBackground(Color.yellow);
		botaoHoraIda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fieldIda.setText(formataHora.format( Calendar.getInstance().getTime()));
			}
		});
		
		botaoHoraVolta = new JButton();			
		botaoHoraVolta.setBackground(Color.yellow);
		botaoHoraVolta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fieldVolta.setText(formataHora.format( Calendar.getInstance().getTime()));
			}
		});
		
		janela = new JDialog(frame,"Cadastrar Vez",true);
				
		painel.setLayout(new MigLayout());		
		painel.setBorder(borda);
		painel.setBackground(cor);
		
		painel.add(labelPessoa, "width :10:");
		painel.add(comboPessoa, "width :100:, wrap, gapleft 30");
		painel.add(labelData, "width :10:");
		painel.add(fieldData, "width :100:, gapleft 30, split 2");		
		painel.add(botaoData, "width 20::, wrap");		
		painel.add(labelIda, "width :10:");
		painel.add(fieldIda, "width :100:, gapleft 30, split 2");
		painel.add(botaoHoraIda, "width 20::, wrap");
		painel.add(labelVolta, "width :10:");
		painel.add(fieldVolta, "width :100:, gapleft 30, split 2");
		painel.add(botaoHoraVolta, "width 20::, wrap");
		painel.add(botaoCadastrar, "width :170: , height :30:, span");

		janela.add(painel);
		
		janela.setSize(230, 250);		
		janela.setLocation(420, 320);		
		janela.setVisible(true);		
		janela.setResizable(false);		
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	public void cadastrarVez(){
		Statement statement = Conexao.conectar();
		if( (comboPessoa.getSelectedItem() != null && !comboPessoa.getSelectedItem().equals("")) && 
			(fieldData.getText() != null && !fieldData.getText().equals("")) &&
			(fieldIda.getText() != null && !fieldIda.getText().equals("")) &&
			(fieldVolta.getText() != null && !fieldVolta.getText().equals(""))){
			
			Statement s = Conexao.conectar();
			
			String sql = "select id from dbe_paciente where nome = '" + comboPessoa.getSelectedItem() + "';";
			
			try {
				
				ResultSet r = s.executeQuery(sql);
			
				r.next();
										 
			    java.sql.Date dataJDBC = new java.sql.Date(formataData.parse(fieldData.getText()).getTime());			        
			    String dataISO = bancoData.format(dataJDBC);
			       
				sql = "insert into dbe_vez(paciente_id, data, tempo) values(" 
					+ r.getInt("id") + ", '" + dataISO + "', "
					+ RicardoUtils.totalTempo(fieldIda.getText(), fieldVolta.getText()) + ");";
				
				statement.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "Vez cadastrada com sucesso!!! ", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
				janela.dispose();
			
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
			
		} else {
			JOptionPane.showMessageDialog(null, "Preencha Todos os Campos", "Erro", JOptionPane.WARNING_MESSAGE);
		}
	}

}
