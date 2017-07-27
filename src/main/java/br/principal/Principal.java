package br.principal;

import java.io.File;
import java.io.IOException;

import br.controller.CfopController;
import br.controller.CreditoPresumidoController;
import br.controller.NCMController;
import br.controller.ProdutoController;
import br.controller.ProdutoCstCsOsnController;
import br.controller.ProdutoRemetenteController;
import br.controller.ReceitaController;
import br.util.ExportDisco;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

public class Principal {
    static final String CAMINHO_ARQUIVO_PRODUTO_DAIF_GERAL="/home/danieltavares/Documentos/NFAE/A_PROCESSAR/V4-DAIF.xls";
    static final String CAMINHO_ARQUIVO_PRODUTO_DFI="/home/danieltavares/Documentos/NFAE/A_PROCESSAR/V4-DFI.xls";
    static final String CAMINHO_ARQUIVO_PRODUTO_DTR="/home/danieltavares/Documentos/NFAE/A_PROCESSAR/V4-DTR.xls";
    static final String CAMINHO_ARQUIVO_CRED_PRESUMIDO="/home/danieltavares/Documentos/NFAE/A_PROCESSAR/NFAe_creditoPresumido_18-7-2017.xls";
    static final String CAMINHO_CFOP="/home/danieltavares/Documentos/NFAE/A_PROCESSAR/CFOP.xls";
      
    static final String CAMINHO_DESTINO_PRODUTO="/home/danieltavares/Documentos/NFAE/SCRIPT/TAB_PRODUTO.sql";
    static final String CAMINHO_DESTINO_CRED_PRESUMIDO="/home/danieltavares/Documentos/NFAE/SCRIPT/CRED_PRESUMIDO.sql";
    static final String CAMINHO_DESTINO_NCM="/home/danieltavares/Documentos/NFAE/SCRIPT/TAB_NCM.sql";
    static final String CAMINHO_DESTINO_RECEITA="/home/danieltavares/Documentos/NFAE/SCRIPT/RECEITAS.sql";
    static final String CAMINHO_DESTINO_TIPO_REMETENTE="/home/danieltavares/Documentos/NFAE/SCRIPT/TAB_PRDUTO_REMETENTE.sql";
    static final String CAMINHO_DESTINO_CST_CSOSN="/home/danieltavares/Documentos/NFAE/SCRIPT/PRDUTO_CST_CSOSN.sql";
    static final String CAMINHO_DESTINO_CFOP="/home/danieltavares/Documentos/NFAE/SCRIPT/TAB_CFOP.sql";
    
    static final int NUMERO_LINHA_PLANILHA_PRODUTO=868;
    static final int NUMERO_LINHA_PLANILHA_CRED_PRESUMIDO=20;  
    
    static ExportDisco export=new ExportDisco();
    WorkbookSettings ws = new WorkbookSettings();
    
    
    public Principal() {
    	ws.setEncoding("Cp1252");
	}
    
    //PRODUTO
    public void processarProduto() throws Exception{
    	Sheet sheet = getSheet(CAMINHO_ARQUIVO_PRODUTO_DAIF_GERAL, 0);
		
		ProdutoController pc=new ProdutoController(sheet, NUMERO_LINHA_PLANILHA_PRODUTO);
		export.criarArquivo(CAMINHO_DESTINO_PRODUTO, pc.buildProdutos().gerarScriptInsert());
    }
    
    //CREDITO PRESUMIDO
    public void processarCreditoPresumido() throws Exception{
		Sheet sheet = getSheet(CAMINHO_ARQUIVO_CRED_PRESUMIDO, 0);
    
		CreditoPresumidoController cpc=new CreditoPresumidoController(sheet, NUMERO_LINHA_PLANILHA_CRED_PRESUMIDO);
		export.criarArquivo(CAMINHO_DESTINO_CRED_PRESUMIDO, cpc.gerarScriptUpdate());
    }
    
    //RECEITA
    public void processarReceita() throws BiffException, IOException{
		Sheet sheet = getSheet(CAMINHO_ARQUIVO_PRODUTO_DAIF_GERAL, 0);
	    
		ReceitaController rc=new ReceitaController(sheet, NUMERO_LINHA_PLANILHA_PRODUTO);
		export.criarArquivo(CAMINHO_DESTINO_RECEITA, rc.buildReceita().gerarScriptInsert());

    }
    
    //NCM
    public void processarNCM() throws BiffException, IOException{
    	Sheet sheet = getSheet(CAMINHO_ARQUIVO_PRODUTO_DFI, 0);
	    
		NCMController ncm=new NCMController(sheet, NUMERO_LINHA_PLANILHA_PRODUTO);
		export.criarArquivo(CAMINHO_DESTINO_NCM, ncm.buildNCM().gerarScriptInsert());
    }
    	
    //TIPO_REMETENTE
    public void processarTipoRemetente() throws BiffException, IOException{
    	Sheet sheet = getSheet(CAMINHO_ARQUIVO_PRODUTO_DFI, 0);
	    
		ProdutoRemetenteController prc=new ProdutoRemetenteController(sheet, NUMERO_LINHA_PLANILHA_PRODUTO);
		export.criarArquivo(CAMINHO_DESTINO_TIPO_REMETENTE,  prc.buildPRodutoRemetente().gerarScriptInsert());
    }
    
    

    //CST E CSOSN
    public void processarProdutoCstCsosn() throws BiffException, IOException{
    	Sheet sheet = getSheet(CAMINHO_ARQUIVO_PRODUTO_DTR, 0);
	    
    	ProdutoCstCsOsnController pcst=new ProdutoCstCsOsnController(sheet, NUMERO_LINHA_PLANILHA_PRODUTO);
		export.criarArquivo(CAMINHO_DESTINO_CST_CSOSN,  pcst.buildCstCsosn().gerarScriptInsert());
    }   
    
    //CFOP
    public void processarCFOP() throws BiffException, IOException{
    	Sheet sheet = getSheet(CAMINHO_CFOP, 0);
	    
    	CfopController c=new CfopController(sheet, 562);
		export.criarArquivo(CAMINHO_DESTINO_CFOP,  c.buildCfop().gerarScriptInsert());
    }       
    
    
    public Sheet getSheet(String caminho, Integer numeroAba) throws BiffException, IOException{
    	Workbook workbook= Workbook.getWorkbook(new File(caminho),ws);
    	return workbook.getSheet(numeroAba);
    }
    
	public static void main(String[] args) {
		
		try {
			Principal p =new Principal();
			p.processarProduto();	
			p.processarCreditoPresumido();
			p.processarReceita();
			p.processarNCM();	
			p.processarTipoRemetente();
            p.processarProdutoCstCsosn();
            p.processarCFOP();
			System.out.println("Processo finalizado.");
				
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}  
	    
	     
		
	
	}
}
