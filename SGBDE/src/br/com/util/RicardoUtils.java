package br.com.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.sgbde.Conexao;

public class RicardoUtils{
	
	public static int[] converteTempoEmInt(String tempo){
		int[] conversao = new int[2];
		String[] parte = tempo.split(":");
		conversao[0] = Integer.parseInt(parte[0]);
		conversao[1] = Integer.parseInt(parte[1]);	
		
		return conversao;
	}
	
	public static int tempoInt(String tempo){
		int[] iTempo = converteTempoEmInt(tempo);
		
		int valorHora = iTempo[0];
		int valorMinuto = iTempo[1];
		
		return ((valorHora)*60) + valorMinuto;
	}
	
	public static int totalTempo(String tempo, String tempo2){
		int[] iTempo = converteTempoEmInt(tempo);
		int[] iTempo2 = converteTempoEmInt(tempo2);
		
		int valorHora = iTempo2[0] - iTempo[0];
		int valorMinuto = iTempo2[1] - iTempo[1];	
		
		return ((valorHora)*60) + valorMinuto;
	}
	
	public static String converteTempoEmString(int tempo){
		int horas = tempo / 60;
		int minutos = tempo % 60;		
		
		String sMinutos = (String) (minutos > 9 ? "" + minutos : ("0" + minutos));		
		String sHoras = (String) (horas > 9 ? "" + horas : ("0" + horas));
		
		return sHoras + ":" + sMinutos;
	}
	
	public static List<String> preencheCombo(){
		List<String> pacientes = new ArrayList<String>();
		Statement s = Conexao.conectar();
		String sql = "select nome from dbe_paciente";
		try {
			ResultSet r = s.executeQuery(sql);
			while(r.next()){
				pacientes.add(r.getString("nome"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pacientes;
	}

}