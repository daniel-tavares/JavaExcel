package controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import enun.UnidadeMedidaEnum;
import jxl.Sheet;
import modelo.Produto;
import util.ConverterDados;


public class ProdutoController {
	 
	Sheet sheet;
	Integer numeroMaximoLinha; 
	List<Produto> listaProduto=new ArrayList<Produto>();
	
	public ProdutoController(Sheet sheet, Integer numeroMaximoLinha) {
		this.sheet=sheet;
		this.numeroMaximoLinha=numeroMaximoLinha;
	}
	
	
	public ProdutoController buildProdutos() throws Exception{
		 
		 String codigoSubProdutoAuxiliar="";
		 Integer codigoSubProduto=0;
                 
		 for(int linha=2; linha<this.numeroMaximoLinha;linha++){
			
				 Produto prod=new Produto();
				
				 //1. ID_PRODUTO
				 prod.setID_PRODUTO(sheet.getCell(0,linha).getContents());
				 
				 
				 //2. ID_UNIDADE_MEDIDA 
				 prod.setID_UNIDADE_MEDIDA(ConverterDados.getIntegerToString(UnidadeMedidaEnum.getUnidadeMedidaEnum((String)sheet.getCell(6,linha).getContents()).getCodigo()));
	
				
				 //3. CODIGO_PRODUTO
				 if(sheet.getCell(3,linha).getContents().equals(""))
					prod.setCODIGO_PRODUTO("0");
				 else	
					prod.setCODIGO_PRODUTO(sheet.getCell(3,linha).getContents());
				 
				 //3. DESCRICAO_PRODUTO 
				 prod.setDESCRICAO_PRODUTO(sheet.getCell(4,linha).getContents().replace("- não justificar crédito presumido na interestadual", "").trim());
				 
				 
				 //4. CODIGO_SUB_PRODUTO
				 if(!codigoSubProdutoAuxiliar.equals((String) sheet.getCell(3,linha).getContents())){
					   prod.setCODIGO_SUB_PRODUTO(sheet.getCell(3,linha).getContents()+"01");
					   codigoSubProdutoAuxiliar=sheet.getCell(3,linha).getContents();
					   codigoSubProduto=1;
				 }else{
					 	codigoSubProduto++;
						prod.setCODIGO_SUB_PRODUTO(sheet.getCell(3,linha).getContents()+ConverterDados.completeToLeft(ConverterDados.getIntegerToString(codigoSubProduto), '0',2));	
						codigoSubProdutoAuxiliar=	sheet.getCell(3,linha).getContents();
				 }
				 
				 
	
				 //5.  DESCRICAO_SUB_PRODUTO
				 if(sheet.getCell(5,linha).getContents().equals("") || sheet.getCell(5,linha).getContents().equals("."))
					prod.setDESCRICAO_SUB_PRODUTO("PADRÃO");
				 else
					prod.setDESCRICAO_SUB_PRODUTO(sheet.getCell(5,linha).getContents().replace("- não justificar crédito presumido na interestadual",""));
				 
				 //6. ID_SITUACAO
				    prod.setID_SITUACAO("1");
				 
				 //7. CODIGO_CEST
					prod.setCODIGO_CEST("0");
					
				 
				 //8. PRECO_MINIMO_INTERNO
				 if(sheet.getCell(7,linha).getContents().equals("-") || sheet.getCell(7,linha).getContents().equals(""))
				   prod.setPRECO_MINIMO_INTERNO("0,00");
				 else
	 			   prod.setPRECO_MINIMO_INTERNO(sheet.getCell(7,linha).getContents());
					
				 
				//9. PRECO_MINIMO_INTERNO
				 if(sheet.getCell(8,linha).getContents().equals("-") || sheet.getCell(8,linha).getContents().equals("") )
					prod.setPRECO_MINIMO_INTERESTADUAL("0,00");
				 else
					prod.setPRECO_MINIMO_INTERESTADUAL(sheet.getCell(8,linha).getContents());
				
				
				 
				//10. TRAT_TRIB_ICMS_DEBITO	 
				if(sheet.getCell(9,linha).getContents().equals("") || sheet.getCell(9,linha).getContents().equals("-"))
				   prod.setTRAT_TRIB_ICMS_DEBITO(ConverterDados.formataMoedaWs(new BigDecimal(0).doubleValue()));
				else
				   prod.setTRAT_TRIB_ICMS_DEBITO(String.valueOf(Double.parseDouble(sheet.getCell(9,linha).getContents().replace(",", "."))/100).replace(".", ","));
					
				
				//11. TRAT_TRIB_ICMS_CREDITO		
			    if(sheet.getCell(10,linha).getContents().equals("") || sheet.getCell(10,linha).getContents().equals("-"))
				   prod.setTRAT_TRIB_ICMS_CREDITO(ConverterDados.formataMoedaWs(new BigDecimal(0).doubleValue()));
				else
				   prod.setTRAT_TRIB_ICMS_CREDITO(String.valueOf((Double.parseDouble(sheet.getCell(10,linha).getContents().replace(",", "."))/100)).replace(".",","));
				
	
				  //12. ID_GTA
				  if(sheet.getCell(11,linha).getContents().equals("SIM"))
					 prod.setID_GTA("1");
				  else
					 prod.setID_GTA("0");
					 
				  //13. ID_DOF	
				  if(sheet.getCell(12,linha).getContents().equals("SIM"))	
					 prod.setID_DOF("1");
				  else
					 prod.setID_DOF("0"); 
				
				 prod.setID_EXIGE_DETALHAMENTO("0");  // UPDATE
				 
				 //Valores default:
				 
				 prod.setID_SITUACAO("1");
				 prod.setID_EXCLUSAO_LOGICA("0");
				 prod.setDT_ALTERACAO("sysdate");
				 prod.setCODIGO_CEST("0");
				 prod.setTIPO_CALCULO_CREDITO_PRESUMIDO("0"); //UPDATE
				 prod.setTRAT_TRIB_CRED_PRES_INTERNO(null);//UPDATE
				 prod.setTRAT_TRIB_CRED_PRES_INTEREST(null);//UPDATE
				 prod.setTRAT_TRIB_ICMS_CRED_FRETE(null);
				 prod.setTRAT_TRIB_ICMS_INTERESTADUAL(null);
				 prod.setTRAT_TRIB_ICMS_INTERNO(null);
				 prod.setTRAT_TRIB_ICMS_INTRA_ST(null);
				 prod.setTRAT_TRIB_IPI(null);
				 prod.setTRAT_TRIB_MVA(null);
				 prod.setTRAT_TRIB_MVA_INTEREST(null);
				 prod.setTRAT_TRIB_MVA_INTERNO(null);
				 prod.setTRAT_TRIB_RED_BC(null);
				 prod.setTRAT_TRIB_RED_BC_INTERNO(null);
				 prod.setTRAT_TRIB_RED_BC_INTEREST(null);
				 prod.setTRAT_TRIB_REDU_ALIQ_INTERNA(null);
				 prod.setTRAT_TRIB_REDU_ALIQ_INTEREST(null);
				 
				 //if(!sheet.getCell(0,linha).getContents().equals(""))
				 listaProduto.add(prod);   
			  
		 }
		 
		 return this;
		        
    }
	
	public String gerarScriptInsert(){
		StringBuilder sb=new StringBuilder();
		
		for (Produto produto : listaProduto) {
			 sb.append("INSERT INTO nfae.tab_produto VALUES (");
		     sb.append(produto.getID_PRODUTO()+",");
		     sb.append(produto.getID_UNIDADE_MEDIDA()+",");
		     sb.append(produto.getCODIGO_PRODUTO()+",");
		     sb.append("\'"+produto.getDESCRICAO_PRODUTO()+"\',");
		     sb.append(produto.getCODIGO_SUB_PRODUTO()+",");
		     sb.append("\'"+produto.getDESCRICAO_SUB_PRODUTO()+"\',");
		     sb.append(produto.getID_SITUACAO()+",");
		     sb.append(produto.getCODIGO_CEST()+",");
		     sb.append(ConverterDados.tratarValorMoeda(produto.getPRECO_MINIMO_INTERNO())+","); 
		     sb.append(ConverterDados.tratarValorMoeda(produto.getPRECO_MINIMO_INTERESTADUAL())+",");
		     sb.append(produto.getID_GTA()+",");//ID_GTA
		     sb.append(produto.getID_DOF()+",");//ID_DOF
		     sb.append(produto.getTRAT_TRIB_ICMS_INTERNO()+",");//TRAT_TRIB_ICMS_INTERNO
		     sb.append(produto.getTRAT_TRIB_ICMS_INTERESTADUAL()+",");//TRAT_TRIB_ICMS_INTERESTADUAL
		     sb.append(produto.getTRAT_TRIB_MVA()+",");//TRAT_TRIB_MVA
		     sb.append(produto.getTRAT_TRIB_RED_BC()+",");//TRAT_TRIB_RED_BC
		     sb.append(produto.getTRAT_TRIB_IPI()+",");//TRAT_TRIB_IPI
		     sb.append(produto.getTRAT_TRIB_ICMS_INTRA_ST()+",");//TRAT_TRIB_ICMS_INTRA_ST
		     sb.append(produto.getDT_ALTERACAO()+",");//DT_ALTERACAO
		     sb.append(produto.getID_EXIGE_DETALHAMENTO()+",");//ID_EXIGE_DETALHAMENTO
		     sb.append("\'"+produto.getTRAT_TRIB_ICMS_DEBITO()+"\',");//TRAT_TRIB_ICMS_DEBITO
		     sb.append("\'"+produto.getTRAT_TRIB_ICMS_CREDITO()+"\',");//TRAT_TRIB_ICMS_CREDITO
		     sb.append(produto.getTRAT_TRIB_RED_BC_INTERNO()+",");//TRAT_TRIB_RED_BC_INTERNO
		     sb.append(produto.getTRAT_TRIB_RED_BC_INTEREST()+",");//TRAT_TRIB_RED_BC_INTEREST
		     sb.append(produto.getTRAT_TRIB_CRED_PRES_INTERNO()+",");//TRAT_TRIB_CREDITO_PRESUMIDO
		     sb.append(produto.getTRAT_TRIB_ICMS_CRED_FRETE()+",");//TRAT_TRIB_ICMS_CRED_FRETE
		     sb.append(produto.getTRAT_TRIB_MVA_INTERNO()+",");//TRAT_TRIB_MVA_INTERNO
		     sb.append(produto.getTRAT_TRIB_MVA_INTEREST()+",");//TRAT_TRIB_MVA_INTEREST
		     sb.append(produto.getTRAT_TRIB_REDU_ALIQ_INTERNA()+",");//TRAT_TRIB_REDU_ALIQ_INTERNA
		     sb.append(produto.getTRAT_TRIB_REDU_ALIQ_INTEREST()+",");//TRAT_TRIB_REDU_ALIQ_INTEREST
		     sb.append(produto.getID_EXCLUSAO_LOGICA()+",");//ID_EXCLUSAO_LOGICA
		     sb.append(produto.getTIPO_CALCULO_CREDITO_PRESUMIDO()+",");
		     sb.append(produto.getTRAT_TRIB_CRED_PRES_INTEREST());//TRAT_TRIB_CRED_PRES_INTEREST
		     sb.append(");\n");
			}
		
	
		return sb.toString();
    }
	
	
}
