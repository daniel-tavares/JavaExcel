package br.controller;

import java.util.ArrayList;
import java.util.List;
import br.modelo.TAB_PRODUTO;
import br.util.ConverterDados;
import jxl.Sheet;


public class CreditoPresumidoController {
	 
	Sheet sheet;
	Integer numeroMaximoLinha; 
	List<TAB_PRODUTO> listaProduto=new ArrayList<TAB_PRODUTO>();
	
	public CreditoPresumidoController(Sheet sheet, Integer numeroMaximoLinha) {
		this.sheet=sheet;
		this.numeroMaximoLinha=numeroMaximoLinha;
	}
	
	
	public String gerarScriptUpdate(){
		StringBuilder sb=new StringBuilder();
		
		for (int linha = 6; linha < numeroMaximoLinha; linha++) {
			 String tipo="";
			 if(ConverterDados.padronizaString(sheet.getCell(11,linha).getContents()).equals("CALCULADOSOBREOVALORDAOPERACAO"))
				tipo="1"; //base de calculo
			 else if(ConverterDados.padronizaString(sheet.getCell(11,linha).getContents()).equals("CALCULADOSOBREODEBITODOICMS"))
				tipo="2"; //valor do icms 	
			 else
			    tipo="0";	  
				 
			sb.append("UPDATE NFAE.TAB_PRODUTO p SET p.TIPO_CALCULO_CREDITO_PRESUMIDO="+tipo+", p.TRAT_TRIB_CRED_PRES_INTERNO="+sheet.getCell(15,linha).getContents()+" WHERE p.ID_PRODUTO="+sheet.getCell(0,linha).getContents()+";\n");
			
		}
		
		return sb.toString();
	}
	
	
	
}
