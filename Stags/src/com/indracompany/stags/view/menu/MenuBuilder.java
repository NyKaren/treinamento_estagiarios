package com.indracompany.stags.view.menu;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import com.indracompany.stags.bo.ClienteBO;
import com.indracompany.stags.bo.MidiaBO;
import com.indracompany.stags.bo.TransacaoBO;
import com.indracompany.stags.bo.ab.ClienteBOIf;
import com.indracompany.stags.bo.ab.MidiaBOIf;
import com.indracompany.stags.bo.ab.TransacaoBOIf;
//import com.indracompany.stags.bo.ab.TransacaoBOIf;
import com.indracompany.stags.model.ClienteModel;
import com.indracompany.stags.model.MidiaModel;
import com.indracompany.stags.model.TipoDeMidiaModel;
import com.indracompany.stags.model.TransacaoModel;

public class MenuBuilder {
		private ClienteBOIf clienteBO;
		private MidiaBOIf midiaBO;
		private TransacaoBOIf transacaoBO;
    	String quebraLinha = System.getProperty("line.separator");
		
		public MenuBuilder(){
			clienteBO = new ClienteBO();
			midiaBO = new MidiaBO();
			transacaoBO = new TransacaoBO();
		}
		public String executarMenuInicial() {
			
			System.out.println("\t\tMenu de op��es:");
			System.out.println("\t1. Inserir cliente");
			System.out.println("\t2. Editar cliente");
			System.out.println("\t3. Excluir cliente");
			System.out.println("\t4. Inserir m�dia");
			System.out.println("\t5. Editar m�dia");
			System.out.println("\t6. Excluir m�dia");
			System.out.println("\t7. Buscar cliente");
			System.out.println("\t8. Buscar m�dia por nome");
			System.out.println("\t9. Buscar m�dia por c�digo");
			System.out.println("\t10. Vender");
			System.out.println("\t11. Alugar");
			System.out.println("\t0. Sair");
			
			return pedirEntrada("\nInsira sua op��o: ");
		}
		
		public void inserirCliente() throws Exception {
			String nome="", cpf="";
			ClienteModel pModel = new ClienteModel();
			nome = pedirEntrada(quebraLinha + "Digite nome: ");
			pModel.setNome(nome);
			String idade = pedirEntrada(quebraLinha + "Digite idade: ");
			if(idade  != null && !idade.equals("")){
				int valor = Integer.parseInt(idade);
				pModel.setIdade(valor);
				cpf = pedirEntrada(quebraLinha + "Digite cpf: ");
				pModel.setCpf(cpf);
	//			Data autom�tica
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				Date date = new Date();
				pModel.setDataRegistro(dateFormat.format(date));
				clienteBO.validate(pModel);
				clienteBO.inserir(pModel);
                System.out.printf(quebraLinha + "Cliente inserido com sucesso." + quebraLinha + quebraLinha);
			} else {
				throw new Exception("Formato inv�lido.");
			}
//			exibirTodos();
		}
		
		public void editarCliente(ClienteModel pModel) throws Exception {
			String nome = pedirEntrada(quebraLinha + "Digite nome do cliente a ser editado: ");
			pModel = clienteBO.buscarCliente(nome);			
			if(pModel !=null){				
				boolean continuar = true;
				do{
		        	try{
						String opcaoSelecionadaMenuEditar = executarMenuEditar();
							switch(opcaoSelecionadaMenuEditar){
							case "a":
								String opcao1 = pedirEntrada(quebraLinha + "Digite novo nome: ");
								if(opcao1 != null && !opcao1.equals("")){
									pModel.setNome(opcao1);
									clienteBO.validate(pModel);
									clienteBO.editar(pModel);
					                System.out.printf(quebraLinha + "Nome editado com sucesso." + quebraLinha);
								}
								break;
								
							case "b":
								Integer opcao2 = Integer.parseInt(pedirEntrada(quebraLinha + "Digite nova idade: "));
								if(opcao2 != null && !opcao2.equals("")){
									pModel.setIdade(opcao2);
									clienteBO.validate(pModel);
									clienteBO.editar(pModel);
					                System.out.printf(quebraLinha + "Idade editada com sucesso." + quebraLinha);
								}
								break;
								
							case "c":
								String opcao3 = pedirEntrada(quebraLinha + "Digite novo CPF: ");;
								if(opcao3 != null && !opcao3.equals("")){
									pModel.setCpf(opcao3);
									clienteBO.validate(pModel);
									clienteBO.editar(pModel);
					                System.out.printf(quebraLinha + "CPF editado com sucesso." + quebraLinha);
								}
								break;
								
							case "d":
			                	continuar = MainExecutorMenu.voltarPrograma();
								break;
	
							default:
			                    System.out.printf("Voc� digitou uma op��o inv�lida.");
			                    System.lineSeparator();
								break;
							}
		            }catch(Exception ex){
			        	System.err.println("LOG DE ERRO: "+ex.getMessage());
			        }					
				}while(continuar);
			}
			}
		
		public void excluirCliente(ClienteModel pModel) throws Exception {
			String nome = pedirEntrada(quebraLinha + "Digite nome do cliente a ser exclu�do: ");
			pModel = clienteBO.buscarCliente(nome);
			try {
				if(pModel != null && !pModel.equals("")){
					clienteBO.excluir(pModel);
					System.out.println("Cliente removido com sucesso: "+pModel.toString());
				}
			} catch (Exception e) {
				throw new Exception("Erro ao tentar excluir um cliente -> "+e.getMessage());
			}
		}
		
		//Segue a parte de m�dia
		public void inserirMidia() throws Exception {
			MidiaModel pModel = new MidiaModel();
			String nome = pedirEntrada(quebraLinha + "Digite nome: ");
			try {
				pModel.setNome(nome);
				String descricao = pedirEntrada(quebraLinha + "Digite descri��o: ");
				pModel.setDescricao(descricao);
				String codBarras = pedirEntrada(quebraLinha + "Digite c�digo de barras: ");
				pModel.setCodigoBarras(codBarras);
				Double valorVenda = Double.parseDouble(pedirEntrada(quebraLinha + "Digite valor de venda: "));
				pModel.setValorVenda(valorVenda);
				Double valorAluguel = Double.parseDouble(pedirEntrada(quebraLinha + "Digite valor de aluguel: "));
				pModel.setValorAluguel(valorAluguel);
	//			Data autom�tica
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				Date date = new Date();
				pModel.setDataRegistro(dateFormat.format(date));
	//			Pedindo data:
	//			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	//			String dataRegistroStr = pedirEntrada("\nDigite data(Formato: dd/MM/yyyy): ");
	//			java.sql.Date dataRegistro = new java.sql.Date(format.parse(dataRegistroStr).getTime());
	//			pModel.setDataRegistro(dataRegistro);						
				Integer quantidadequantidadeEstoque = Integer.parseInt(pedirEntrada(quebraLinha + "Digite quantidade em estoque: "));
				pModel.setQuantidadeEstoque(quantidadequantidadeEstoque);
				tipoMidiaInserirMenu(pModel);	
				midiaBO.validate(pModel);			
				midiaBO.inserir(pModel);
	//			exibirTodos();
				System.out.println("M�dia inserida com sucesso: "+pModel.toString());
			} catch (Exception e) {
				throw new Exception("Erro ao tentar adicionar uma m�dia -> "+e.getMessage());
			}
		}
		
		public void editarMidia(MidiaModel pModel) throws Exception {
			String nome = pedirEntrada(quebraLinha + "Digite nome da m�dia a ser editada: ");
			pModel = midiaBO.buscarMidia(nome);			
			if(pModel !=null){
				boolean continuar = true;
				do{
					String opcaoSelecionadaMenuEditarMidia = executarMenuEditarMidia();
					try {
						switch(opcaoSelecionadaMenuEditarMidia){
						case "a":
							String opcaoa = pedirEntrada(quebraLinha + "Digite novo nome: ");
							pModel.setNome(opcaoa);
							midiaBO.validate(pModel);
							midiaBO.editar(pModel);							
							break;

						case "b":
							Double opcaob = Double.parseDouble(pedirEntrada(quebraLinha + "Digite novo valor de venda: "));
							pModel.setValorVenda(opcaob);
							midiaBO.validate(pModel);
							midiaBO.editar(pModel);
							break;
							
						case "c":
							Double valorAluguel = Double.parseDouble(pedirEntrada(quebraLinha + "Digite novo valor de aluguel: "));
							pModel.setValorAluguel(valorAluguel);
							midiaBO.validate(pModel);
							midiaBO.editar(pModel);
							break;
							
						case "d":							
							tipoMidiaEditarMenu(pModel); 
							break;
						
						case "e":					
							Integer quantidadequantidadeEstoque = Integer.parseInt(pedirEntrada(quebraLinha + "Digite nova quantidade em estoque: "));
							pModel.setQuantidadeEstoque(quantidadequantidadeEstoque);
							midiaBO.validate(pModel);
							midiaBO.editar(pModel);
							break;
							
						case "f":		
		                	continuar = MainExecutorMenu.voltarPrograma();
//		        			Data autom�tica
		        			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		        			Date date = new Date();
		        			pModel.setDataRegistro(dateFormat.format(date));
							break;
							
						default:
		                    System.out.printf("Voc� digitou uma op��o inv�lida.");
		                    System.lineSeparator();
		                    break;
						}
						break;
					} catch (Exception e) {
						throw new Exception("Erro ao tentar editar uma m�dia -> "+e.getMessage());
					}						
				}while(continuar);
			    }			
		}
		
		public void tipoMidiaInserirMenu(MidiaModel pModel) throws Exception {
			if(pModel !=null){
				boolean continuar = true;
				do{
					try {
						String opcaoSelecionadaMenuTipoMidia = executarMenuInserirTipoMidia();
							switch(opcaoSelecionadaMenuTipoMidia){
							case "a":	
								pModel.setTipoMidia(TipoDeMidiaModel.DVD);			
								continuar = false;
								break;
								
							case "b":
								pModel.setTipoMidia(TipoDeMidiaModel.BLURAY);
								continuar = false;
								break;
								
							case "c":					
								pModel.setTipoMidia(TipoDeMidiaModel.STREAMING);
								continuar = false;
								break;
								
							default:
			                    System.out.printf("Voc� digitou uma op��o inv�lida.");
			                    System.lineSeparator();
			                    break;
							}
						} catch (Exception e) {
							throw new Exception("Erro ao tentar estabelecer tipo de m�dia -> "+e.getMessage());  	
						}
					} while(continuar); 
			} else {
				throw new Exception("N�o � poss�vel realizar a a��o.");
			}			    
		}
		
		public void tipoMidiaEditarMenu(MidiaModel pModel) throws Exception {			
			if(pModel !=null){
				boolean continuar = true;
				do{
					String opcaoSelecionadaMenuTipoMidia = executarMenuEditarTipoMidia();
					try {
						switch(opcaoSelecionadaMenuTipoMidia){
						case "a":	
							pModel.setTipoMidia(TipoDeMidiaModel.DVD); 
							continuar = false;
							break;
							
						case "b":	
							pModel.setTipoMidia(TipoDeMidiaModel.BLURAY); 
							continuar = false;
							break;
							
						case "c":			
							pModel.setTipoMidia(TipoDeMidiaModel.STREAMING); 
							continuar = false;
							break;
							
						case "d":
		                	continuar = MainExecutorMenu.voltarPrograma();
		                	break;	
		                	
						default:
		                    System.out.printf("Voc� digitou uma op��o inv�lida.");
		                    System.lineSeparator();
		                    break;
		                    
						}
					} catch (Exception e) {
						throw new Exception("Erro ao tentar estabelecer tipo de m�dia -> "+e.getMessage());	
					}
				}while(continuar);
			    }			
		}	
		
		public void excluirMidia(MidiaModel pModel) throws Exception {
			String nome = pedirEntrada(quebraLinha + "Digite nome da m�dia a ser exclu�da: ");
			try {
				pModel = midiaBO.buscarMidia(nome);
				if(pModel != null && !pModel.equals("")){
					midiaBO.excluir(pModel);					
				}
				System.out.println("M�dia removida com sucesso: "+pModel.toString());
			} catch (Exception e) {	
				throw new Exception("Erro ao tentar excluir uma m�dia -> "+e.getMessage());
			}
		}
		
		public void buscarCliente(ClienteModel pModel) throws Exception {
			String nome = pedirEntrada(quebraLinha + "Digite nome do cliente a detalhar: ");
			try {
				if(pModel.getAtivo() != false){
					clienteBO.buscarCliente(nome);	
				} else 
					throw new Exception("Cliente n�o encontrado. ");
			} catch (Exception e) {	
				throw new Exception("Erro ao tentar buscar um cliente -> "+e.getMessage());
			}
		}		
		
		public void buscarMidia(MidiaModel pModel) throws Exception {
			String nome = pedirEntrada(quebraLinha + "Digite nome da m�dia a detalhar: ");
			try {
				if(pModel.getAtivo() != false){
					midiaBO.buscarMidia(nome);		
				} else 
					throw new Exception("M�dia n�o encontrado. ");
			} catch (Exception e) {	
				throw new Exception("Erro ao tentar buscar uma m�dia -> "+e.getMessage());
			}
		}		
		
		public void buscar(MidiaModel pModel) throws Exception {
			try {
				try {
					Long codigo = Long.parseLong(pedirEntrada(quebraLinha + "Digite c�digo da m�dia a detalhar: "));
					if(pModel.getAtivo() != false){
						midiaBO.buscar(codigo);		
					} else 
						throw new Exception("M�dia n�o encontrada. ");					
				} catch (Exception e) {	
					throw new Exception("Formato inv�lido ");
				}
			} catch (Exception e) {	
				throw new Exception("Erro ao tentar buscar uma m�dia -> "+e.getMessage());
			}
		}
		
		//m�todo vender
		public void vender(ClienteModel pModel, MidiaModel lModel) {
			try{
				TransacaoModel tModel = new TransacaoModel();
				String nomeMidia = null;
				try {
					String nomeCliente = pedirEntrada(quebraLinha + "Digite nome do cliente a comprar: ");
					pModel = clienteBO.buscarCliente(nomeCliente);
					tModel.setCliente(pModel);				
				}catch (Exception e) {	
					throw new Exception("Cliente inv�lido ");
				}
				try {
					Double acumular = null;
					do{
						nomeMidia = pedirEntrada(quebraLinha + "Digite nome da m�dia a comprar (para cancelar, digite N): ");
					    if(!nomeMidia.equalsIgnoreCase("N")){
							lModel = midiaBO.buscarMidia(nomeMidia);
							// acumular t� dando erro no debug
							acumular = acumular + lModel.getValorVenda();
							transacaoBO.setValorTotal(acumular);
					    	tModel.addMidia(lModel);
					    	System.out.printf("M�dia adicionada com sucesso. ");
					    }		                    
					}while(nomeMidia != null && !nomeMidia.equalsIgnoreCase("N"));
//					Data autom�tica
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
					Date date = new Date();
					pModel.setDataRegistro(dateFormat.format(date));
					try {
						clienteBO.validate(pModel);
						midiaBO.validate(lModel);					
					} catch (Exception e) {	
						throw new Exception("N�o validados. ");
					}
					try {
						transacaoBO.inserir(tModel);
						System.out.printf(quebraLinha + "\nValor total da compra: " + transacaoBO.getValorTotal() + "Compra realizada com sucesso." + quebraLinha + quebraLinha);					
					} catch (Exception e) {	
						throw new Exception("Transa��o n�o pode ser realizada. ");
					}
				}catch (Exception e) {	
					throw new Exception("M�dia inv�lida ");
				}
	       }catch(Exception ex){
	        	System.err.println("LOG DE ERRO: "+ex.getMessage());
	       }						
		}
		
		//m�todo alugar
				public void alugar(ClienteModel pModel, MidiaModel lModel) {
					try{
						TransacaoModel tModel = new TransacaoModel();
						String nomeMidia = null;
						try {
							String nomeCliente = pedirEntrada(quebraLinha + "Digite nome do cliente a alugar: ");
							pModel = clienteBO.buscarCliente(nomeCliente);
							tModel.setCliente(pModel);				
						}catch (Exception e) {	
							throw new Exception("Cliente inv�lido ");
						}
						try {
							Double acumular = null;
							do{
								nomeMidia = pedirEntrada(quebraLinha + "Digite nome da m�dia a alugar (para cancelar, digite N): ");
							    if(!nomeMidia.equalsIgnoreCase("N")){
									lModel = midiaBO.buscarMidia(nomeMidia);
									acumular = acumular + lModel.getValorAluguel();
									transacaoBO.setValorTotal(acumular);
							    	tModel.addMidia(lModel);
							    	System.out.printf("M�dia adicionada com sucesso. ");
							    }		                    
							}while(nomeMidia != null && !nomeMidia.equalsIgnoreCase("N"));
						}catch (Exception e) {	
							throw new Exception("M�dia inv�lida ");
						}
//						Data autom�tica
						DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
						Date date = new Date();
						pModel.setDataRegistro(dateFormat.format(date));
						try {
							clienteBO.validate(pModel);
							midiaBO.validate(lModel);					
						} catch (Exception e) {	
							throw new Exception("N�o validados. ");
						}
						try {
							transacaoBO.inserir(tModel);
							System.out.printf(quebraLinha + "\nValor total da aluguel: " + transacaoBO.getValorTotal() + "Aluguel realizado com sucesso." + quebraLinha + quebraLinha);					
						} catch (Exception e) {	
							throw new Exception("Transa��o n�o pode ser realizada. ");
						}
			       }catch(Exception ex){
			        	System.err.println("LOG DE ERRO: "+ex.getMessage());
			       }						
				}
		
		@SuppressWarnings("resource")
		public String pedirEntrada(String mensagemEntrada) {
			Scanner entrada = new Scanner(System.in);
			System.out.print(mensagemEntrada);
			return entrada.nextLine();
		}
		
		public String executarMenuEditar() {
		       System.out.println("\t\tMenu de op��es:");
		       System.out.println("\ta. Editar nome");
		       System.out.println("\tb. Editar idade");
		       System.out.println("\tc. Editar cpf");
		       System.out.println("\td. Sair");
		        		
		       return pedirEntrada(quebraLinha + "Insira sua op��o: ");
		}
		
		public String executarMenuEditarMidia() {
		       System.out.println("\t\tMenu de op��es:");
		       System.out.println("\ta. Editar nome");
		       System.out.println("\tb. Editar valor de venda");
		       System.out.println("\tc. Editar valor de aluguel");
		       System.out.println("\td. Editar tipo de m�dia");
		       System.out.println("\te. Editar quantidade em estoque");
		       System.out.println("\tf. Sair");
		        		
		       return pedirEntrada(quebraLinha + "Insira sua op��o: ");
		}
		
		public String executarMenuEditarTipoMidia() {
		       System.out.println("\t\tTipo de m�dia a editar:");
		       System.out.println("\ta. DVD");
		       System.out.println("\tb. BLU-RAY");
		       System.out.println("\tc. STREAMING");
		       System.out.println("\td. Sair");
		        		
		       return pedirEntrada(quebraLinha + "Insira sua op��o: ");
		}
		
		public String executarMenuInserirTipoMidia() {
		       System.out.println("\t\tTipo de m�dia a inserir:");
		       System.out.println("\ta. DVD");
		       System.out.println("\tb. BLU-RAY");
		       System.out.println("\tc. STREAMING");
		        		
		       return pedirEntrada(quebraLinha + "Insira sua op��o: ");
		}
		
		public void exibirTodos() throws Exception{
			List<ClienteModel> listaCliente = clienteBO.listar();
			if(listaCliente != null && listaCliente.size()>0){
				for(ClienteModel cliente:listaCliente){
					System.out.println(cliente);
				}
			}
			
			List<MidiaModel> listaMidia = midiaBO.listar();
			if(listaMidia != null && listaMidia.size()>0){
				for(MidiaModel midia:listaMidia){
					System.out.println(midia);
				}
			}
		}
}