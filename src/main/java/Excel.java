//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.OutputStream;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//
//import br.util.ConverterDados;
//import jxl.Sheet;
//import jxl.Workbook;
//
//
//public class Excel {
//
//	public static void main(String[] args) throws Exception {
//		 Workbook workbook = Workbook.getWorkbook(new File("C:\\Users\\Daniel Tavares\\Documents\\prod_customizado.xls"));  
//	     List<TAB_PRODUTO> listaProduto=new ArrayList<TAB_PRODUTO>();
//	     
//		 Sheet sheet = workbook.getSheet(0); 
//		 
//	
//		 int idProduto=1; 
//		 int idNCM=1;
//		 int subproduto=1;
//		 String codigoAuxiliar="";
//		 
//		 for (int l = 1; l <= 582; l++) {
//				TAB_PRODUTO prod=new TAB_PRODUTO();
//				prod.setID_PRODUTO(idProduto);
//				prod.setID_UNIDADE_MEDIDA(sheet.getCell(0,l).getContents());
//				
//				if(sheet.getCell(2,l).getContents().equals(""))
//					prod.setCODIGO_PRODUTO("0");
//				else	
//				    prod.setCODIGO_PRODUTO(sheet.getCell(2,l).getContents());
//				
//				prod.setDESCRICAO_PRODUTO(sheet.getCell(3,l).getContents());
//				
//				
//				if(!codigoAuxiliar.equals((String) sheet.getCell(2,l).getContents())){
//				   prod.setCODIGO_SUB_PRODUTO(sheet.getCell(2,l).getContents()+"01");
//				   codigoAuxiliar=	sheet.getCell(2,l).getContents();
//				   subproduto=1;
//				}else{
//					subproduto++;
//					prod.setCODIGO_SUB_PRODUTO(sheet.getCell(2,l).getContents()+ConverterDados.completeToLeft(ConverterDados.getIntegerToString(subproduto), '0',2));	
//				    codigoAuxiliar=	sheet.getCell(2,l).getContents();
//				}
//				
//				if(sheet.getCell(4,l).getContents().equals(""))
//					prod.setDESCRICAO_SUB_PRODUTO("PADR�O");
//				else
//					prod.setDESCRICAO_SUB_PRODUTO(sheet.getCell(4,l).getContents());
//				
//				
//				if(sheet.getCell(5,l).getContents().equals("-") || sheet.getCell(5,l).getContents().equals(""))
//				   prod.setPRECO_MINIMO_INTERNO("0,00");
//				else
//				   prod.setPRECO_MINIMO_INTERNO(sheet.getCell(5,l).getContents());
//				
//				if(sheet.getCell(6,l).getContents().equals("-") || sheet.getCell(6,l).getContents().equals("") )
//					prod.setPRECO_MINIMO_INTERESTADUAL("0,00");
//				else
//				    prod.setPRECO_MINIMO_INTERESTADUAL(sheet.getCell(6,l).getContents());
//				prod.setID_GTA(sheet.getCell(7,l).getContents());
//				prod.setID_DOF(sheet.getCell(8,l).getContents());
//			    prod.setID_EXIGE_DETALHAMENTO(sheet.getCell(9,l).getContents());
//			    
//			    if(sheet.getCell(10,l).getContents().equals("") || sheet.getCell(10,l).getContents().equals("-"))
//			        prod.setTRAT_TRIB_ICMS_DEBITO(ConverterDados.formataMoedaWs(new BigDecimal(0).doubleValue()));
//			    else
//			    	prod.setTRAT_TRIB_ICMS_DEBITO(String.valueOf(Double.parseDouble(sheet.getCell(10,l).getContents().replace(",", "."))/100).replace(".", ","));
//				
//			   
//			    
//			    if(sheet.getCell(11,l).getContents().equals("") || sheet.getCell(11,l).getContents().equals("-"))
//			        prod.setTRAT_TRIB_ICMS_CREDITO(ConverterDados.formataMoedaWs(new BigDecimal(0).doubleValue()));
//			    else
//			    	prod.setTRAT_TRIB_ICMS_CREDITO(String.valueOf((Double.parseDouble(sheet.getCell(11,l).getContents().replace(",", "."))/100)).replace(".",","));
//			    
//				StringBuilder sb =new StringBuilder();
//				if(!sheet.getCell(12,l).getContents().equals(""))
//				  sb.append("Exce��o Opera��o Interestadual: "+sheet.getCell(12,l).getContents());
//				if(!sheet.getCell(13,l).getContents().equals(""))
//				  sb.append("Legisla��o Opera��o Interestadual: "+sheet.getCell(13,l).getContents());
//				if(!sheet.getCell(14,l).getContents().equals(""))
//				   sb.append("Notas Opera��o Interestadual: "+sheet.getCell(14,l).getContents());
//				if(!sheet.getCell(15,l).getContents().equals(""))
//				   sb.append("Exce��o Opera��o Interna: "+sheet.getCell(15,l).getContents());
//				if(!sheet.getCell(16,l).getContents().equals(""))
//				   sb.append("Legisla��o Opera��o Interna: "+sheet.getCell(16,l).getContents());
//				if(!sheet.getCell(17,l).getContents().equals(""))
//				   sb.append("Notas Opera��o Interna: "+sheet.getCell(17,l).getContents());
//				
//				prod.setJUSTIFICATIVA_TRATAMENTO_TRIB(sb.toString());		
//				
//				prod.setID_SITUACAO("1");
//				prod.setID_EXCLUSAO_LOGICA("0");
//				prod.setDT_ALTERACAO("sysdate");
//				prod.setCODIGO_CEST("0");
//				prod.setTRAT_TRIB_CREDITO_PRESUMIDO(null);
//				prod.setTRAT_TRIB_ICMS_CRED_FRETE(null);
//				
//				prod.setTRAT_TRIB_ICMS_INTERESTADUAL(null);
//				prod.setTRAT_TRIB_ICMS_INTERNO(null);
//				prod.setTRAT_TRIB_ICMS_INTRA_ST(null);
//				prod.setTRAT_TRIB_IPI(null);
//				prod.setTRAT_TRIB_MVA(null);
//				prod.setTRAT_TRIB_MVA_INTEREST(null);
//				prod.setTRAT_TRIB_MVA_INTEREST(null);
//				prod.setTRAT_TRIB_MVA_INTERNO(null);
//				prod.setTRAT_TRIB_RED_BC(null);
//				prod.setTRAT_TRIB_RED_BC_INTERNO(null);
//				prod.setTRAT_TRIB_RED_BC_INTEREST(null);
//				prod.setTRAT_TRIB_REDU_ALIQ_INTERNA(null);
//				prod.setTRAT_TRIB_REDU_ALIQ_INTEREST(null);
//				
//				
//				Receita receita =new Receita();
//				receita.setID_CODIGO_RECEITA(ConverterDados.getIntegerToString(idProduto));
//				receita.setCODIGO_RECEITA(sheet.getCell(19,l).getContents());
//				receita.setCOD_RECEITA_INTERESTADUAL(sheet.getCell(20,l).getContents());
//				receita.setID_CODIGO_PADRAO("1");
//				receita.setID_PRODUTO(ConverterDados.getIntegerToString(idProduto));
//				
//			    prod.setReceita(receita);
//			    
//			    List<TAB_NCM> listaNCM=new ArrayList<TAB_NCM>();
//			    String[] valores=sheet.getCell(18,l).getContents().split(",");
//		       		for (String ncm : valores) {
//						TAB_NCM n=new TAB_NCM();
//						n.setID_NCM(ConverterDados.getIntegerToString(idNCM));
//						n.setID_PRODUTO(ConverterDados.getIntegerToString(prod.getID_PRODUTO()));
//						n.setCODIGO_NCM(ncm.replaceAll("[.]", ""));
//		       			listaNCM.add(n);
//		       			idNCM++;
//					}
//				idProduto++;
//				
//				prod.setListaNcm(listaNCM);
//				listaProduto.add(prod);
//			}
//	
//		 
//		 
//		 
//		 StringBuilder sb=new StringBuilder();
//		 StringBuilder sbReceita=new StringBuilder();
//		 
//		 StringBuilder sbNcm=new StringBuilder();
//		 
//		 for (TAB_PRODUTO produto : listaProduto) {
//			 sb.append("INSERT INTO nfae.tab_produto VALUES (");
//		     sb.append(produto.getID_PRODUTO()+",");//ID_PRODUTO
//		     sb.append(produto.getID_UNIDADE_MEDIDA()+",");//ID_UNIDADE_MEDIDA
//		     sb.append(produto.getCODIGO_PRODUTO()+",");//CODIGO_PRODUTO
//		     sb.append("\'"+produto.getDESCRICAO_PRODUTO()+"\',");//DESCRICAO_PRODUTO
//		     sb.append(produto.getCODIGO_SUB_PRODUTO()+",");//CODIGO_SUB_PRODUTO
//		     sb.append("\'"+produto.getDESCRICAO_SUB_PRODUTO()+"\',");//DESCRICAO_SUB_PRODUTO
//		     sb.append(produto.getID_SITUACAO()+",");//ID_SITUACAO
//		     sb.append(produto.getCODIGO_CEST()+",");//CODIGO_CEST
//		     sb.append(tratarValor(produto.getPRECO_MINIMO_INTERNO())+",");//PRECO_MINIMO_INTERNO
//		     sb.append(tratarValor(produto.getPRECO_MINIMO_INTERESTADUAL())+",");//PRECO_MINIMO_INTERESTADUAL
//		     sb.append(produto.getID_GTA()+",");//ID_GTA
//		     sb.append(produto.getID_DOF()+",");//ID_DOF
//		     sb.append(produto.getTRAT_TRIB_ICMS_INTERNO()+",");//TRAT_TRIB_ICMS_INTERNO
//		     sb.append(produto.getTRAT_TRIB_ICMS_INTERESTADUAL()+",");//TRAT_TRIB_ICMS_INTERESTADUAL
//		     sb.append(produto.getTRAT_TRIB_MVA()+",");//TRAT_TRIB_MVA
//		     sb.append(produto.getTRAT_TRIB_RED_BC()+",");//TRAT_TRIB_RED_BC
//		     sb.append(produto.getTRAT_TRIB_IPI()+",");//TRAT_TRIB_IPI
//		     sb.append(produto.getTRAT_TRIB_ICMS_INTRA_ST()+",");//TRAT_TRIB_ICMS_INTRA_ST
//		     sb.append(produto.getDT_ALTERACAO()+",");//DT_ALTERACAO
//		     sb.append(produto.getID_EXIGE_DETALHAMENTO()+",");//ID_EXIGE_DETALHAMENTO
//		     sb.append("\'"+produto.getTRAT_TRIB_ICMS_DEBITO()+"\',");//TRAT_TRIB_ICMS_DEBITO
//		     sb.append("\'"+produto.getTRAT_TRIB_ICMS_CREDITO()+"\',");//TRAT_TRIB_ICMS_CREDITO
//		     sb.append(produto.getTRAT_TRIB_RED_BC_INTERNO()+",");//TRAT_TRIB_RED_BC_INTERNO
//		     sb.append(produto.getTRAT_TRIB_RED_BC_INTEREST()+",");//TRAT_TRIB_RED_BC_INTEREST
//		     sb.append(produto.getTRAT_TRIB_CREDITO_PRESUMIDO()+",");//TRAT_TRIB_CREDITO_PRESUMIDO
//		     sb.append(produto.getTRAT_TRIB_ICMS_CRED_FRETE()+",");//TRAT_TRIB_ICMS_CRED_FRETE
//		     sb.append(produto.getTRAT_TRIB_MVA_INTERNO()+",");//TRAT_TRIB_MVA_INTERNO
//		     sb.append(produto.getTRAT_TRIB_MVA_INTEREST()+",");//TRAT_TRIB_MVA_INTEREST
//		     sb.append(produto.getTRAT_TRIB_REDU_ALIQ_INTERNA()+",");//TRAT_TRIB_REDU_ALIQ_INTERNA
//		     sb.append(produto.getTRAT_TRIB_REDU_ALIQ_INTEREST()+",");//TRAT_TRIB_REDU_ALIQ_INTEREST
//		     sb.append(produto.getID_EXCLUSAO_LOGICA()+",");//ID_EXCLUSAO_LOGICA
//		     sb.append("\'"+produto.getJUSTIFICATIVA_TRATAMENTO_TRIB()+"\'");//JUSTIFICATIVA_TRATAMENTO_TRIB
//		     sb.append(");\n");
//		     
//		     
//		     sbReceita.append("INSERT INTO nfae.tab_codigo_receita values (");
//		     sbReceita.append(produto.getReceita().getID_CODIGO_RECEITA()+",");
//    		 sbReceita.append(produto.getReceita().getID_PRODUTO()+",");
//    		 sbReceita.append(produto.getReceita().getCODIGO_RECEITA()+",");
//    		 sbReceita.append(produto.getReceita().getID_CODIGO_PADRAO()+",");
//    		 sbReceita.append(produto.getReceita().getCOD_RECEITA_INTERESTADUAL());
//    		 sbReceita.append(");\n");
//		     
//
//    		 for (TAB_NCM ncm : produto.getListaNcm()) {
//				sbNcm.append("INSERT INTO nfae.tab_ncm VALUES (");
//				sbNcm.append(ncm.getID_NCM()+",");
//				sbNcm.append(ncm.getID_PRODUTO()+",");
//				sbNcm.append("\'"+ncm.getCODIGO_NCM().trim()+"\'");
//				sbNcm.append(");\n");
//			}
//		 }
//	     
//		 OutputStream os =new FileOutputStream(new File("C:\\Users\\Daniel Tavares\\Documents\\TabProduto.sql"));
//		 os.write(sb.toString().getBytes());
//		 os.flush();
//		 os.close();
//		 
//		 
//		 OutputStream os2 =new FileOutputStream(new File("C:\\Users\\Daniel Tavares\\Documents\\TabCodigoReceita.sql"));
//		 os2.write(sbReceita.toString().getBytes());
//		 os2.flush();
//		 os2.close();
//		 
//		 OutputStream os3 =new FileOutputStream(new File("C:\\Users\\Daniel Tavares\\Documents\\TabNcm.sql"));
//		 os3.write(sbNcm.toString().getBytes());
//		 os3.flush();
//		 os3.close();
//		 System.out.println("Finalizado com sucesso..");
//		 
//		 
//	}
//	
//	
//	public  static String tratarValor(String valor){
//		if(valor!=null && !valor.equals(""))
//			return "\'"+valor.replace("$", "").replace(".","").trim()+"\'";
//		
//		return null;
//	}
//}
