package br.com.sgbde;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

public class CadastrarPessoa {

	JLabel labelNome;
	JTextField fieldNome;	
	JLabel labelRG;
	JTextField fieldRG;	
	JButton botaoCadastrar;			
	JDialog janela;
	
	public CadastrarPessoa(final JFrame frame){
		
		JPanel painel = new JPanel();
		
		Color cor = new Color(230, 232, 250);	
		
		labelNome = new JLabel("Nome:");
		fieldNome = new JTextField(20);
		
		labelRG = new JLabel("RG:");
		fieldRG = new JTextField(20);
		
		botaoCadastrar = new JButton("Cadastrar");	
		botaoCadastrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cadastrarPessoa();
				frame.dispose();
				new SGBDE();
			}
		});
		
		janela = new JDialog(frame,"Cadastrar Pessoa",true);
		TitledBorder borda = BorderFactory.createTitledBorder("Cadastrar Pessoa");
				
		painel.setLayout(new MigLayout());	
		painel.setBackground(cor);
		painel.setBorder(borda);
		
		painel.add(labelNome, "width :10:");
		painel.add(fieldNome, "width :100:, gapleft 30, wrap");
		painel.add(labelRG, "width :10:");
		painel.add(fieldRG, "width :100:, gapleft 30, wrap");		
		painel.add(botaoCadastrar, "width 170:: , height :30:, span");

		janela.add(painel);
		janela.setSize(220, 180);
		janela.setLocation(530, 270);
		janela.setVisible(true);
		janela.setResizable(false);
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	public void cadastrarPessoa(){
		Statement statement = Conexao.conectar();
		if( (fieldNome.getText() != null && !fieldNome.getText().equals("")) 
				&& (fieldRG.getText() != null && !fieldRG.getText().equals(""))){
			String sql = "insert into dbe_paciente(nome, rg) values('" + fieldNome.getText() + "', '" + fieldRG.getText() + "');";
		
			try {
				statement.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "Paciente Cadastrado com Sucesso!!! ", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
				Conexao.desconectar(statement.getConnection());
				statement.close();						
				janela.dispose();
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Preencha Nome e RG", "Erro", JOptionPane.WARNING_MESSAGE);
		}
	}
	
}
