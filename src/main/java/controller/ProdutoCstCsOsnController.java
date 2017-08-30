package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jxl.Sheet;
import modelo.ProdutoCsosn;
import modelo.ProdutoCst;
import util.ConverterDados;


public class ProdutoCstCsOsnController {
	
	List<String> cstComercioExterior=Arrays.asList("00","20","40","41","50","51");
	List<String> cstInterno=Arrays.asList("00","20","40","41","50","51");
	List<String> cstInterestadual=Arrays.asList("00","20","40","41","50","51");
	int id_produto_cst=1;
	
	
	
	
	Sheet sheet;
	Integer numeroMaximoLinha; 
	List<ProdutoCst> listaProdutoCst=new ArrayList<ProdutoCst>();
	List<ProdutoCsosn> listaProdutoCsosn=new ArrayList<ProdutoCsosn>();
	
	public ProdutoCstCsOsnController(Sheet sheet, Integer numeroMaximoLinha) {
		this.sheet=sheet;
		this.numeroMaximoLinha=numeroMaximoLinha;
	}
	
	
	public void criarObjeto(List<String> listaCst, Integer tipoOperacao,int linha){
		for (String cst : listaCst) {
			if(!ConverterDados.padronizaString(getJustificativa(cst, tipoOperacao, linha)).equals(ConverterDados.padronizaString("NÃO EXIBIR ESTE CST"))){
				ProdutoCst pcst=new ProdutoCst();
				pcst.setID_PRODUTO_CST(ConverterDados.getIntegerToString(id_produto_cst));
				pcst.setID_PRODUTO(sheet.getCell(0,linha).getContents());
				pcst.setID_CST(cst);
				pcst.setID_TIPO_OPERACAO(ConverterDados.getIntegerToString(tipoOperacao));  
				
				pcst.setJUSTIFICATIVA_TRAT_TRIB((getJustificativa(cst, tipoOperacao, linha).equals(""))?"":getJustificativa(cst, tipoOperacao, linha)); 
				listaProdutoCst.add(pcst);
				id_produto_cst++;
							
			}
		}
	}
	
	public ProdutoCstCsOsnController buildCstCsosn(){
		
		
		 for(int linha=2; linha<this.numeroMaximoLinha;linha++){
			String idProduto=sheet.getCell(0, linha).getContents();
			System.out.println(idProduto+"-"+sheet.getCell(48, linha).getContents()+"-"+sheet.getCell(49, linha).getContents()+"-"+sheet.getCell(50, linha).getContents());
			
			
			String remove="[ABCDEFGHIJKLMNOPQRSTUVWYXZabcdefghijklmnopqrstuvywxzçÇÃ(),.;]";
			String conteudo=sheet.getCell(54, linha).getContents().replaceAll(remove, "").replaceAll("[' ']", "");
			
			//if(idProduto.equals("6")){
				if(conteudo.equals("")){ //O produto terá todos os CSTs
					criarObjeto(cstInterno, 1, linha);
					criarObjeto(cstInterestadual, 3, linha);
					criarObjeto(cstComercioExterior, 2, linha);
				}else{
					String valor=sheet.getCell(54, linha).getContents();
					List<String> listInter=new ArrayList<String>();
					List<String> listIntra=new ArrayList<String>();
					List<String> listExterior=new ArrayList<String>();
					String padrao=null;
					String padrao2=null;
					String padrao3=null;
					System.out.println(valor);
					//INTER
					int index=valor.indexOf("INTRA");
					String sub=valor.substring(0,index);
					padrao=sub.trim().replaceAll(remove, "").replace("INTER", "").replaceAll("[' ']", "");
					
					//INTRA
					int index2=valor.indexOf("EXTERIOR");
					if(index2!=-1){
					 String sub2=valor.substring(index+4,index2);
					 padrao2=sub2.trim().replaceAll(remove, "").replace("INTRA", "").replaceAll("[' ']", "");
				     
					 //EXTERIOR
					 String sub3=valor.substring(index2+6,valor.length());
					 padrao3=sub3.trim().replaceAll(remove, "").replace("INTRA", "").replaceAll("[' ']", "");
					}else{
						String sub2=valor.substring(index+4,valor.length());
						padrao2=sub2.trim().replaceAll(remove, "").replace("INTRA", "").replaceAll("[' ']", "");
					}
					
					
					for (int i = 0; i < padrao.length(); i+=2) {
						listInter.add(padrao.substring(i, i+2));
					}
					
					for (int i = 0; i < padrao2.length(); i+=2) {
						listIntra.add(padrao2.substring(i, i+2));
					}
					
					if(index2!=-1){
						for (int i = 0; i < padrao3.length(); i+=2) {
							listExterior.add(padrao3.substring(i, i+2));
						}
					}
					
					criarObjeto(listInter, 1, linha);
					criarObjeto(listIntra, 3, linha);
					criarObjeto(listExterior, 2, linha);
					
				}
			}
		
		// }
	
	   return this;
	}
	
	
	
	public String getJustificativa(String cst, Integer tipoOperacao, int linha){
		
		switch (tipoOperacao) {
		case 1: //INTERNA
			
			if(cst.equals("00")){
		       return sheet.getCell(24, linha).getContents();		
			}else if(cst.equals("20")){
				return sheet.getCell(25, linha).getContents();	
			}else if(cst.equals("40")){
				return sheet.getCell(26, linha).getContents();	
			}else if(cst.equals("41")){
				return sheet.getCell(27, linha).getContents();	
			}else if(cst.equals("50")){
				return sheet.getCell(28, linha).getContents();	
			}else if(cst.equals("51")){
				return sheet.getCell(29, linha).getContents();	
			}
			
			return "null";
			
		case 2: // COMERCIO EXTERIOR
			if(cst.equals("00")){
			       return sheet.getCell(48, linha).getContents();		
				}else if(cst.equals("20")){
					return sheet.getCell(49, linha).getContents();	
				}else if(cst.equals("40")){
					return sheet.getCell(50, linha).getContents();	
				}else if(cst.equals("41")){
					return sheet.getCell(51, linha).getContents();	
				}else if(cst.equals("50")){
					return sheet.getCell(52, linha).getContents();	
				}else if(cst.equals("51")){
					return sheet.getCell(53, linha).getContents();	
				}
			return "null";	
		
		case 3: // INTERESTADUAL
			if(cst.equals("00")){
			       return sheet.getCell(18, linha).getContents();		
				}else if(cst.equals("20")){
					return sheet.getCell(19, linha).getContents();	
				}else if(cst.equals("40")){
					return sheet.getCell(20, linha).getContents();	
				}else if(cst.equals("41")){
					return sheet.getCell(21, linha).getContents();	
				}else if(cst.equals("50")){
					return sheet.getCell(22, linha).getContents();	
				}else if(cst.equals("51")){
					return sheet.getCell(23, linha).getContents();	
				}
			return "null";

			
		default:
			return "null";
		}
		
	}
	
	
	
	public String gerarScriptInsert(){
		StringBuilder sb=new StringBuilder();
		sb.append("\n\n-- INTERNO\n\n");
		for (ProdutoCst cst : listaProdutoCst) {
			   sb.append("INSERT INTO NFAE.TAB_PRODUTO_CST VALUES("+cst.getID_PRODUTO_CST()+","+cst.getID_PRODUTO()+",'"+cst.getID_CST()+"',"+cst.getID_TIPO_OPERACAO()+",'"+cst.getJUSTIFICATIVA_TRAT_TRIB()+"');\n");
		}
		
		
		
	     return sb.toString();
	}
	
}
