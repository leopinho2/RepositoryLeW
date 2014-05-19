package br.com.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import net.miginfocom.swing.MigLayout;

public class Janela {
	
	public static void criarJanela(){
		
		telaLogin();
				
//		JLabel label = new JLabel("Escreve Ai:");
//				
//		JFrame janela = new JFrame("Janela");
//		
//		JPanel painel = new JPanel();
//		
//		painel.setLayout(new MigLayout());
//		
//		JButton botao = new JButton("Botão");
//		
//		janela.setLayout(new MigLayout());		
//		
//		JTextField campo = new JTextField(20);
//		
//		painel.add(label, "width :10:");
//		painel.add(campo, "width :500:, wrap");
//		painel.add(botao, "width :100: , height :30:");
//
//		janela.add(painel);
//		
//		janela.setSize(500, 400);
//		
//		janela.setLocation(400, 200);
//		
//		janela.setVisible(true);
//		
//		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		criarJanela();
	}
	
	public static void telaLogin(){
		
		JLabel labelLogin = new JLabel("Login:");
		final JTextField fieldLogin = new JTextField(20);
		
		JLabel labelSenha = new JLabel("Senha:");
		JPasswordField fieldSenha = new JPasswordField(20);
		
		JButton botaoLogar = new JButton("Logar");		
		
		final JFrame janela = new JFrame("Login");
		
		botaoLogar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(janela, "Bem Vindo");
			}
		});
		
		JPanel painel = new JPanel();
		
		painel.setLayout(new MigLayout());		
		
		painel.add(labelLogin, "width :10:, gapleft 30, gaptop 30");
		painel.add(fieldLogin, "width :100:, wrap, gapleft 30");
		painel.add(labelSenha, "width :10:, gapleft 30");
		painel.add(fieldSenha, "width :100:, wrap, gapleft 30");		
		painel.add(botaoLogar, "width :180: , height :30:, gapleft 30, span");

		janela.add(painel);
		
		janela.setSize(270, 200);
		
		janela.setLocation(500, 300);
		
		janela.setVisible(true);
		
		janela.setResizable(false);
		
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
