package br.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import br.modelo.TAB_PRODUTO;
import br.modelo.TAB_RECEITA_COM_EXTERIOR;
import br.modelo.TAB_RECEITA_INTERESTADUAL;
import br.modelo.TAB_RECEITA_INTERNA;
import jxl.Sheet;


public class ProdutoCsosnController {
	 
	Sheet sheet;
	Integer numeroMaximoLinha; 
	List<TAB_RECEITA_INTERNA> listaReceitaInterna=new ArrayList<TAB_RECEITA_INTERNA>();
	List<TAB_RECEITA_INTERESTADUAL> listaReceitaInterestadual=new ArrayList<TAB_RECEITA_INTERESTADUAL>();
	List<TAB_RECEITA_COM_EXTERIOR> listaReceitaComExterior=new ArrayList<TAB_RECEITA_COM_EXTERIOR>();
	
	public ProdutoCsosnController(Sheet sheet, Integer numeroMaximoLinha) {
		this.sheet=sheet;
		this.numeroMaximoLinha=numeroMaximoLinha;
	}
	
	
	public ProdutoCsosnController buildReceita(){
		
		 int id_receita_interna=1;
		 int id_receita_interestadual=1;
		 for(int linha=2; linha<=this.numeroMaximoLinha;linha++){
			  
			if(!sheet.getCell(0, linha).getContents().equals("")){  
				if(!sheet.getCell(14, linha).getContents().equals("")){ 
				  TAB_RECEITA_INTERNA rInterna=new TAB_RECEITA_INTERNA();
				  rInterna.setID_RECEITA(id_receita_interna);
				  rInterna.setID_PRODUTO(sheet.getCell(0, linha).getContents());
				  rInterna.setCODIGO_RECEITA(sheet.getCell(14, linha).getContents());
				  rInterna.setID_CODIGO_PADRAO("0");
				  listaReceitaInterna.add(rInterna);
				  id_receita_interna++;	
				}  
				  
				if(!sheet.getCell(15, linha).getContents().equals("")){ 
				  TAB_RECEITA_INTERESTADUAL rInterestadual=new TAB_RECEITA_INTERESTADUAL();
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
		
		for (TAB_RECEITA_INTERNA interna : listaReceitaInterna) {
			sb.append("INSERT INTO NFAE.TAB_RECEITA_INTERNA VALUES("+interna.getID_RECEITA()+","+interna.getID_PRODUTO()+","+interna.getCODIGO_RECEITA()+","+interna.getID_CODIGO_PADRAO()+");\n");
		}
		
			sb.append("\n\n");
		
		for (TAB_RECEITA_INTERESTADUAL interestadual : listaReceitaInterestadual) {
			sb.append("INSERT INTO NFAE.TAB_RECEITA_INTERESTADUAL VALUES("+interestadual.getID_RECEITA()+","+interestadual.getID_PRODUTO()+","+interestadual.getCODIGO_RECEITA()+","+interestadual.getID_CODIGO_PADRAO()+");\n");
		}
		
			
		return sb.toString();
	}
	
}
