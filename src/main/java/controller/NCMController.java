package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Sheet;
import modelo.Ncm;
import util.ConverterDados;

public class NCMController {
	Sheet sheet;
	Integer numeroMaximoLinha; 
	List<Ncm> listaNCM=new ArrayList<Ncm>();
	Map<String,String> listGTA=new HashMap<String, String>();
	Map<String,String> listDOF=new HashMap<String, String>();
	Map<String,String> listDETALHAMENTO=new HashMap<String, String>();
	
	
	public NCMController(Sheet sheet, Integer numeroMaximoLinha) {
		this.sheet=sheet;
		this.numeroMaximoLinha=numeroMaximoLinha;
	}
	
	public NCMController buildNCM(){
		   
		 int id_ncm=1; 
		 for(int linha=2; linha<this.numeroMaximoLinha;linha++){

			 String[] ncms=sheet.getCell(17,linha).getContents().replaceAll("[e]", ";").replaceAll("[,]", ";").replaceAll("[.]", "").split(";");
			 for (String ncm : ncms) {
				if(!ncm.equals("")) {
				 Ncm n=new Ncm();
				 n.setID_NCM(ConverterDados.getIntegerToString(id_ncm));
				 n.setCODIGO_NCM(ncm.trim());
				 n.setID_PRODUTO(sheet.getCell(0,linha).getContents());
				 id_ncm++; 	
				 listaNCM.add(n);
				}
			 }
			 
			 
		    if(sheet.getCell(11,linha).getContents().equals("SIM")){
		    	listGTA.put(sheet.getCell(0,linha).getContents(), "1");
		    }else{
		    	listGTA.put(sheet.getCell(0,linha).getContents(), "0");
		    }
		    
		    if(sheet.getCell(12,linha).getContents().equals("SIM")){
		    	listDOF.put(sheet.getCell(0,linha).getContents(), "1");
		    }else{
		    	listDOF.put(sheet.getCell(0,linha).getContents(), "0");
		    }
		    
		    if(sheet.getCell(13,linha).getContents().equals("SIM")){
		    	listDETALHAMENTO.put(sheet.getCell(0,linha).getContents(), "1");
		    }else{
		    	listDETALHAMENTO.put(sheet.getCell(0,linha).getContents(), "0");
		    }
			 
			 
		 }

		return this;
	}
	
	
	
	public String gerarScriptInsert(){
		StringBuilder sb=new StringBuilder();
		
		for (Ncm ncm : listaNCM) {
			sb.append("INSERT INTO NFAE.TAB_NCM VALUES("+ncm.getID_NCM()+","+ncm.getID_PRODUTO()+",'"+ncm.getCODIGO_NCM()+"');\n");
		}
		sb.append("\n\n");
		for (String gta : listGTA.keySet()) {
				sb.append("UPDATE NFAE.TAB_PRODUTO p SET p.ID_GTA="+listGTA.get(gta)+" WHERE p.ID_PRODUTO= "+gta+";\n");
		}
		sb.append("\n\n");
		for (String dof : listDOF.keySet()) {
			sb.append("UPDATE NFAE.TAB_PRODUTO p SET p.ID_DOF="+listDOF.get(dof)+" WHERE p.ID_PRODUTO= "+dof+";\n");
		}
		
		sb.append("\n\n");
		for (String det : listDETALHAMENTO.keySet()) {
			sb.append("UPDATE NFAE.TAB_PRODUTO p SET p.ID_EXIGE_DETALHAMENTO="+listDETALHAMENTO.get(det)+" WHERE p.ID_PRODUTO= "+det+";\n");
		}
	    return sb.toString();
	}
}
