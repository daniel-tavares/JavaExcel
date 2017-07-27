package br.controller;

import java.util.ArrayList;
import java.util.List;

import br.modelo.TAB_PRODUTO_REMETENTE;
import br.util.ConverterDados;
import jxl.Sheet;

public class ProdutoRemetenteController {
	Sheet sheet;
	Integer numeroMaximoLinha; 
	List<TAB_PRODUTO_REMETENTE> listaProdutoRemetente=new ArrayList<TAB_PRODUTO_REMETENTE>();
	
	public ProdutoRemetenteController(Sheet sheet, Integer numeroMaximoLinha) {
		this.sheet=sheet;
		this.numeroMaximoLinha=numeroMaximoLinha;
	}
	
	public ProdutoRemetenteController buildPRodutoRemetente(){
		   
		 int id_produto_remetente=1; 
		 for(int linha=1; linha<this.numeroMaximoLinha;linha++){
			 System.out.println(sheet.getCell(31, linha).getContents());
			
			//ORGAO PUBLICO
			if(sheet.getCell(30, linha).getContents().equals("X")){
			   TAB_PRODUTO_REMETENTE pr=new TAB_PRODUTO_REMETENTE();
			   pr.setID_PRODUTO_REMETENTE(ConverterDados.getIntegerToString(id_produto_remetente));
			   pr.setID_PRODUTO(sheet.getCell(0, linha).getContents());
			   pr.setID_TIPO_REMETENTE("6");
			   id_produto_remetente++;
			   listaProdutoRemetente.add(pr);
			}
			
			//PRODUTOR RURAL PF
			if(sheet.getCell(31, linha).getContents().equals("X")){
			   TAB_PRODUTO_REMETENTE pr=new TAB_PRODUTO_REMETENTE();
			   pr.setID_PRODUTO_REMETENTE(ConverterDados.getIntegerToString(id_produto_remetente));
			   pr.setID_PRODUTO(sheet.getCell(0, linha).getContents());
			   pr.setID_TIPO_REMETENTE("1");
			   id_produto_remetente++;
			   listaProdutoRemetente.add(pr);
			}
			
			// PF NÂO CONTRIB ICMS
			if(sheet.getCell(32, linha).getContents().equals("X")){
			   TAB_PRODUTO_REMETENTE pr=new TAB_PRODUTO_REMETENTE();
			   pr.setID_PRODUTO_REMETENTE(ConverterDados.getIntegerToString(id_produto_remetente));
			   pr.setID_PRODUTO(sheet.getCell(0, linha).getContents());
			   pr.setID_TIPO_REMETENTE("2");
			   id_produto_remetente++;
			   listaProdutoRemetente.add(pr);
			}
			
			//MEI
			if(sheet.getCell(33, linha).getContents().equals("X")){
			   TAB_PRODUTO_REMETENTE pr=new TAB_PRODUTO_REMETENTE();
			   pr.setID_PRODUTO_REMETENTE(ConverterDados.getIntegerToString(id_produto_remetente));
			   pr.setID_PRODUTO(sheet.getCell(0, linha).getContents());
			   pr.setID_TIPO_REMETENTE("3");
			   id_produto_remetente++;
			   listaProdutoRemetente.add(pr);
			}
			
			//PJ NÃO CONTRIB ICMS	
			if(sheet.getCell(34, linha).getContents().equals("X")){
			   TAB_PRODUTO_REMETENTE pr=new TAB_PRODUTO_REMETENTE();
			   pr.setID_PRODUTO_REMETENTE(ConverterDados.getIntegerToString(id_produto_remetente));
			   pr.setID_PRODUTO(sheet.getCell(0, linha).getContents());
			   pr.setID_TIPO_REMETENTE("4");
			   id_produto_remetente++;
			   listaProdutoRemetente.add(pr);
			}
			
			//ACAO FISCAL
			if(sheet.getCell(35, linha).getContents().equals("X")){
			   TAB_PRODUTO_REMETENTE pr=new TAB_PRODUTO_REMETENTE();
			   pr.setID_PRODUTO_REMETENTE(ConverterDados.getIntegerToString(id_produto_remetente));
			   pr.setID_PRODUTO(sheet.getCell(0, linha).getContents());
			   pr.setID_TIPO_REMETENTE("5");
			   id_produto_remetente++;
			   listaProdutoRemetente.add(pr);
			}
			 
		 }
		  
		return this;
	}
	
	
	
	public String gerarScriptInsert(){
		StringBuilder sb=new StringBuilder();
		
		sb.append("--Produtor Rural Pessoa Física\n\n");
		
		for (TAB_PRODUTO_REMETENTE tp : listaProdutoRemetente) {
			if(tp.getID_TIPO_REMETENTE().equals("1"))
			sb.append("INSERT INTO NFAE.TAB_PRODUTO_REMETENTE VALUES("+tp.getID_PRODUTO_REMETENTE()+","+tp.getID_TIPO_REMETENTE()+","+tp.getID_PRODUTO()+");\n");
		}
		
		sb.append("-- PF não contribuinte ICMS\n\n");
		
		for (TAB_PRODUTO_REMETENTE tp : listaProdutoRemetente) {
			if(tp.getID_TIPO_REMETENTE().equals("2"))
			sb.append("INSERT INTO NFAE.TAB_PRODUTO_REMETENTE VALUES("+tp.getID_PRODUTO_REMETENTE()+","+tp.getID_TIPO_REMETENTE()+","+tp.getID_PRODUTO()+");\n");
		}
		
		sb.append("-- MEI\n\n");
		
		for (TAB_PRODUTO_REMETENTE tp : listaProdutoRemetente) {
			if(tp.getID_TIPO_REMETENTE().equals("3"))
			sb.append("INSERT INTO NFAE.TAB_PRODUTO_REMETENTE VALUES("+tp.getID_PRODUTO_REMETENTE()+","+tp.getID_TIPO_REMETENTE()+","+tp.getID_PRODUTO()+");\n");
		}
		
		sb.append("-- PJ não contribuinte ICMS\n\n");
		
		for (TAB_PRODUTO_REMETENTE tp : listaProdutoRemetente) {
			if(tp.getID_TIPO_REMETENTE().equals("4"))
			sb.append("INSERT INTO NFAE.TAB_PRODUTO_REMETENTE VALUES("+tp.getID_PRODUTO_REMETENTE()+","+tp.getID_TIPO_REMETENTE()+","+tp.getID_PRODUTO()+");\n");
		}
		
		sb.append("-- Ação Fiscal\n\n");
		
		for (TAB_PRODUTO_REMETENTE tp : listaProdutoRemetente) {
			if(tp.getID_TIPO_REMETENTE().equals("5"))
			sb.append("INSERT INTO NFAE.TAB_PRODUTO_REMETENTE VALUES("+tp.getID_PRODUTO_REMETENTE()+","+tp.getID_TIPO_REMETENTE()+","+tp.getID_PRODUTO()+");\n");
		}
		
		sb.append("-- Órgão Público\n\n");
		
		for (TAB_PRODUTO_REMETENTE tp : listaProdutoRemetente) {
			if(tp.getID_TIPO_REMETENTE().equals("6"))
			sb.append("INSERT INTO NFAE.TAB_PRODUTO_REMETENTE VALUES("+tp.getID_PRODUTO_REMETENTE()+","+tp.getID_TIPO_REMETENTE()+","+tp.getID_PRODUTO()+");\n");
		}
		
		
	    return sb.toString();
	}
}
