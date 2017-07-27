package br.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class ExportDisco {

	public void criarArquivo(String caminho, String arquivo){
		try{	 
			OutputStream os =new FileOutputStream(new File(caminho));
			
			 os.write(arquivo.getBytes());
			 os.flush();
			 os.close();
		}catch (Exception e) {
			e.printStackTrace();	
		} 
	}
}
