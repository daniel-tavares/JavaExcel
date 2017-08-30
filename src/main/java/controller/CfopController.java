package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import jxl.Sheet;
import modelo.Cfop;
import modelo.Produto;
import modelo.ReceitaComExterior;
import modelo.ReceitaInterestadual;
import modelo.ReceitaInterna;
import util.ConverterDados;


public class CfopController {
	 
	Sheet sheet;
	Integer numeroMaximoLinha; 
	List<Cfop> listaCfop=new ArrayList<Cfop>();
	List<String> listaCfopReceitaBrutaAcumulada_R=Arrays.asList("5101","5102","5103","5104","5105","5106","5109","5110","5111","5112","5113","5114","5115","5116","5117","5118","5119","5120","5122","5123","5124","5125","5301","5302","5303","5304","5305","5306","5307","5351","5352","5353","5354","5355","5356","5357","5359","5401","5402","5403","5405","5651","5652","5653","5654","5655","5656","5932","5933","6101","6102","6103","6104","6105","6106","6107","6108","6109","6110","6111","6112","6113","6114","6115","6116","6117","6118","6119","6120","6122","6123","6124","6125","6301","6302","6303","6304","6305","6306","6307","6351","6352","6353","6354","6355","6356","6357","6359","6360","6401","6402","6403","6404","6501","6502","6651","6652","6653","6654","6655","6656","6667","6932","5501","5502","7101","7102","7105","7106","7127","7301","7358","7501","7651","7654","7667");
	List<String> listaCfopReceitaBrutaAcumulada_D=Arrays.asList("1201","1202","1203","1204","1205","1206","1207","1410","1411","1660","1661","1662","2201","2202","2203","2204","2205","2206","2207","2410","2411","2660","2661","2662","3201","3202","3205","3206","3207","3211");
	List<String> listaDevolucao=Arrays.asList("1201","1202","1203","1204","1208","1209","1410","1411","1503","1504","1505","1506","1553","1660","1661","1662","1903","1918","1919","2201","2202","2203","2204","2208","2209","2410","2411","2503","2504","2505","2506","2553","2660","2661","2662","2903","2918","2919","3201","3202","3211","3503","3553","5201","5202","5208","5209","5210","5410","5411","5412","5413","5503","5553","5555","5556","5660","5661","5662","5918","5919","6201","6202","6208","6209","6210","6410","6411","6412","6413","6503","6553","6555","6556","6660","6661","6662","6918","6919","7201","7202","7210","7211","7553","7556");
	List<String> listaDocReferenciado=Arrays.asList("1203","1204","1208","1209","1503","1504","1505","1506","1553","1660","1661","1662","1903","1918","1919","2201","2202","2203","2204","2208","2209","2410","2411","2503","2504","2505","2506","2553","2660","2661","2662","2903","2918","2919","3201","3202","3211","3503","3553","5201","5202","5208","5209","5210","5410","5411","5412","5413","5503","5553","5555","5556","5660","5661","5662","5918","5919","6201","6202","6208","6209","6210","6410","6411","6412","6413","6503","6553","6555","6556","6660","6661","6662","6918","6919","7201","7202","7210","7211","7553","7556");
	
	public CfopController(Sheet sheet, Integer numeroMaximoLinha) {
		this.sheet=sheet;
		this.numeroMaximoLinha=numeroMaximoLinha;
	}
	
	
	public CfopController buildCfop(){
		
		 int id_cfop=1;
		 for(int linha=1; linha<this.numeroMaximoLinha;linha++){
		    String cfop=((String)sheet.getCell(0, linha).getContents()).replaceAll("[.]", "").trim();
			String inicio=cfop.substring(0, 1);
			
			Cfop tabcfop=new Cfop();
			tabcfop.setID_CFOP(ConverterDados.getIntegerToString(id_cfop));
			tabcfop.setCODIGO(cfop);
			tabcfop.setDESCRICAO(sheet.getCell(1, linha).getContents());
		    
		    if(inicio.equals("1") || inicio.equals("2") || inicio.equals("3"))
		    	tabcfop.setNATUREZA_OPERACAO("1"); //SAIDA
		    else if(inicio.equals("5") || inicio.equals("6") || inicio.equals("7"))
		    	tabcfop.setNATUREZA_OPERACAO("0"); //ENTRADA
		    
		    if(inicio.equals("1") || inicio.equals("5"))
		    	tabcfop.setTIPO_OPERACAO("1"); //operacao interna
		    else if(inicio.equals("2") || inicio.equals("6"))
		    	tabcfop.setTIPO_OPERACAO("3"); //operacao interestadal
		    else if(inicio.equals("3") || inicio.equals("7"))
		    	tabcfop.setTIPO_OPERACAO("2"); //operacao com o exterior
		    	
		    
		    if(listaCfopReceitaBrutaAcumulada_D.contains(cfop))
		      tabcfop.setRECEITA_BRUTA_ACUMULADA("D");
		    else if(listaCfopReceitaBrutaAcumulada_R.contains(cfop))
		    	tabcfop.setRECEITA_BRUTA_ACUMULADA("R");
		    else tabcfop.setRECEITA_BRUTA_ACUMULADA("");
		    
		    if(listaDevolucao.contains(cfop))
		    	tabcfop.setDEVOLUCAO("S");
		    else tabcfop.setDEVOLUCAO("N");
		    
		    
		    if(listaDocReferenciado.contains(cfop))
		       tabcfop.setDOCUMENTO_REFERENCIADO("S");
		    else tabcfop.setDOCUMENTO_REFERENCIADO("N");
		    
		    tabcfop.setID_EXCLUSAO_LOGICA("0");
		    id_cfop++;
		    listaCfop.add(tabcfop);
		 }
	
	   return this;
	}
	
	
	public String gerarScriptInsert(){
		StringBuilder sb=new StringBuilder();
		
		for (Cfop tc : listaCfop) {
			sb.append("INSERT INTO NFAE.TAB_CFOP VALUES("+tc.getID_CFOP()+","+tc.getCODIGO()+",'"+tc.getDESCRICAO()+"',"+tc.getNATUREZA_OPERACAO()+","+tc.getTIPO_OPERACAO()+","+tc.getID_EXCLUSAO_LOGICA()+",'"+tc.getRECEITA_BRUTA_ACUMULADA()+"','"+tc.getDEVOLUCAO()+"','"+tc.getDOCUMENTO_REFERENCIADO()+"');\n");
		}
		return sb.toString();
	}


}
