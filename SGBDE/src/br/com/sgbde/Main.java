package br.com.sgbde;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class Main {
	
	public static void main(String args[]){
		//coment wand
		//aqui um comentario novo
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

}
