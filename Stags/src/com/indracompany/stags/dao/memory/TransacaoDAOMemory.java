package com.indracompany.stags.dao.memory;

import java.util.List;

import com.indracompany.stags.dao.TransacaoDAOIf;
import com.indracompany.stags.dao.util.DataBase;
import com.indracompany.stags.model.ClienteModel;
import com.indracompany.stags.model.MidiaModel;
import com.indracompany.stags.model.TransacaoModel;

public class TransacaoDAOMemory implements TransacaoDAOIf {
	@Override
	public void inserir(TransacaoModel tModel) throws Exception {
		tModel.setCodigo(DataBase.getCodigoTransacao());
		DataBase.getListaTransacao().add(tModel);
	}

	@Override
	public List<TransacaoModel> listar() throws Exception {
		return DataBase.getListaTransacao();
	}	

	@Override
	public List<TransacaoModel> getValorTotal(TransacaoModel tModel) throws Exception {
		return DataBase.getListaTransacao();
	}

	@Override
	public void incrementarValorTotal(Double pModel) throws Exception {
		DataBase.getListaTransacao();		
	}

	@Override
	public void setValorTotal(Double mModel) throws Exception {
		DataBase.getListaTransacao();
	}
	
	@Override
	public String exibirHistoricoAluguelCliente(ClienteModel pModel) throws Exception {
		try{
			int a=0;
			// quando passa pra esse for d� erro!
			for(TransacaoModel transacao  : listar()){
				if(transacao.getCliente().equals(pModel) && transacao.getCompra()==false){
					System.out.println("Transa��o encontrada: "+transacao.toString());
					a++;
				}
			}
			if(a != 0) {
				return("Transa��o encontrada. ");
			}
			throw new Exception("Transa��o n�o encontrada. ");
		} catch (Exception e) {
			throw new Exception("Erro -> "+e.getMessage());
		}
	}

	@Override
	public String exibirHistoricoAluguelMidia(MidiaModel pModel) throws Exception {
		try{
			int a=0;
			// quando passa pra esse for d� erro! igual ao hist�rico de aluguel por cliente
			for(TransacaoModel transacao  : listar()){
				if(transacao.listaMidia.equals(pModel) && transacao.getCompra()==false){
					System.out.println("Transa��o encontrada: "+transacao.toString());
					a++;
				}
			}
			if(a != 0) {
				return("Transa��o encontrada. ");
			}
			throw new Exception("Transa��o n�o encontrada. ");
		} catch (Exception e) {
			throw new Exception("Erro -> "+e.getMessage());
		}
	}
}
