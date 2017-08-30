package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import jxl.Sheet;
import modelo.Produto;
import modelo.ReceitaComExterior;
import modelo.ReceitaInterestadual;
import modelo.ReceitaInterna;


public class ReceitaController {
	 
	Sheet sheet;
	Integer numeroMaximoLinha; 
	List<ReceitaInterna> listaReceitaInterna=new ArrayList<ReceitaInterna>();
	List<ReceitaInterestadual> listaReceitaInterestadual=new ArrayList<ReceitaInterestadual>();
	List<ReceitaComExterior> listaReceitaComExterior=new ArrayList<ReceitaComExterior>();
	
	public ReceitaController(Sheet sheet, Integer numeroMaximoLinha) {
		this.sheet=sheet;
		this.numeroMaximoLinha=numeroMaximoLinha;
	}
	
	
	public ReceitaController buildReceita(){
		
		 int id_receita_interna=1;
		 int id_receita_interestadual=1;
		 for(int linha=2; linha<this.numeroMaximoLinha;linha++){
			  
			if(!sheet.getCell(0, linha).getContents().equals("")){  
				if(!sheet.getCell(14, linha).getContents().equals("")){ 
				  ReceitaInterna rInterna=new ReceitaInterna();
				  rInterna.setID_RECEITA(id_receita_interna);
				  rInterna.setID_PRODUTO(sheet.getCell(0, linha).getContents());
				  rInterna.setCODIGO_RECEITA(sheet.getCell(14, linha).getContents());
				  rInterna.setID_CODIGO_PADRAO("0");
				  listaReceitaInterna.add(rInterna);
				  id_receita_interna++;	
				}  
				  
				if(!sheet.getCell(14, linha).getContents().equals("")){ 
				  ReceitaInterestadual rInterestadual=new ReceitaInterestadual();
				  rInterestadual.setID_RECEITA(id_receita_interestadual);
				  rInterestadual.setID_PRODUTO(sheet.getCell(0, linha).getContents());
				  rInterestadual.setCODIGO_RECEITA(sheet.getCell(15, linha).getContents());
				  rInterestadual.setID_CODIGO_PADRAO("0");
				  listaReceitaInterestadual.add(rInterestadual);
				  id_receita_interestadual++;
				}
				
				/*if(!sheet.getCell(16, linha).getContents().equals("")){ 
				  TAB_RECEITA_COM_EXTERIOR rComercioExterior=new TAB_RECEITA_COM_EXTERIOR();
				  rComercioExterior.setID_RECEITA(id_receita);
				  rComercioExterior.setID_PRODUTO(sheet.getCell(0, linha).getContents());
				  rComercioExterior.setCODIGO_RECEITA(sheet.getCell(16, linha).getContents());
				  rComercioExterior.setID_CODIGO_PADRAO("0");
				  listaReceitaComExterior.add(rComercioExterior);
				} */ 
			}
		 }
	
	   return this;
	}
	
	
	public String gerarScriptInsert(){
		StringBuilder sb=new StringBuilder();
		
		for (ReceitaInterna interna : listaReceitaInterna) {
			sb.append("INSERT INTO NFAE.TAB_RECEITA_INTERNA VALUES("+interna.getID_RECEITA()+","+interna.getID_PRODUTO()+","+interna.getCODIGO_RECEITA()+","+interna.getID_CODIGO_PADRAO()+");\n");
		}
		
			sb.append("\n\n");
		
		for (ReceitaInterestadual interestadual : listaReceitaInterestadual) {
			sb.append("INSERT INTO NFAE.TAB_RECEITA_INTERESTADUAL VALUES("+interestadual.getID_RECEITA()+","+interestadual.getID_PRODUTO()+","+interestadual.getCODIGO_RECEITA()+","+interestadual.getID_CODIGO_PADRAO()+");\n");
		}
		
			
		return sb.toString();
	}


}
