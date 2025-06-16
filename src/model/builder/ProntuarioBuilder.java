package model.builder;

import model.entity.Consulta;
import model.entity.Prontuario;

public class ProntuarioBuilder implements IProntuarioBuilder{
	
	private Prontuario prontuario = new Prontuario();
	
	@Override
	public void buildId(Integer id) {
		prontuario.setId(id);
		
	}

	@Override
	public void buildConsulta(Consulta consulta) {
		prontuario.setConsulta(consulta);
		
	}

	@Override
	public void buildObservacao(String observacao) {
		prontuario.setObservacao(observacao);
		
	}

	@Override
	public void buildProcedimento(String procedimento) {
		prontuario.setProcedimento(procedimento);
		
	}

	@Override
	public void buildEvolucao(String evolucao) {
		prontuario.setEvolucao(evolucao);
		
	}
	
	@Override
	public void buildExames(String exames) {
		prontuario.setExames(exames);
		
	}

	@Override
	public Prontuario buildProntuario() {
		return prontuario;
	}

	

}
