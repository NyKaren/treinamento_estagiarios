package com.indracompany.stags.view.menu;

import com.indracompany.stags.model.ClienteModel;
import com.indracompany.stags.model.MidiaModel;

public class MainExecutorMenu {
		    
	    public static void main (String [] Args) throws Exception {
	    	String quebraLinha = System.getProperty("line.separator");
			boolean continuar=true;
	    	String opcao;
	        ClienteModel cliente = new ClienteModel();
	        MidiaModel midia = new MidiaModel();
	        MenuBuilder menuBuilder = new MenuBuilder();
	        do{
	            opcao = menuBuilder.executarMenuInicial();
	            
	            try{
		            switch( opcao )
		            {
		                case "1":
		                	menuBuilder.inserirCliente(cliente);
		                    break;        
		                    
		                case "2":
		                	menuBuilder.editarCliente(cliente);
		                    break;
		                    
		                case "3":
		                	menuBuilder.excluirCliente(cliente);	                   
		                    break;
		                    
		                case "0":
		                	continuar = finalizarPrograma();
		                    break;
		                default:
		                    System.out.printf("Voc� digitou uma op��o inv�lida.");
		                    System.lineSeparator();
		            }       
	            }catch(Exception ex){
	            	System.out.println("Erro -> "+ex.getMessage());
	            }
	        } while( continuar );  
	    }

		private static boolean finalizarPrograma() {
			boolean continuar;
			continuar = false;
			System.out.println("Programa finalizado.");
			return continuar;
		}
}
