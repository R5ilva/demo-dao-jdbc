package model.builder;

import model.entity.Consulta;

public class DiretorProntuario {
	
	private IProntuarioBuilder prontuarioBuilder;
	
	public DiretorProntuario(IProntuarioBuilder prontuarioBuilder) {
		this.prontuarioBuilder = prontuarioBuilder;
	}
	
	public void buildProntuario(Integer id, Consulta consulta, String observacao, String Procedimento, String exames, String evolucao) {
		prontuarioBuilder.buildId(id);
		prontuarioBuilder.buildConsulta(consulta);
		prontuarioBuilder.buildObservacao(observacao);
		prontuarioBuilder.buildProcedimento(Procedimento);
		prontuarioBuilder.buildExames(exames);
		prontuarioBuilder.buildEvolucao(evolucao);
	}
}
