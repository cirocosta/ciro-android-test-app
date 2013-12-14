package br.com.aeho.appoftests;

import java.util.Collection;

public class GsonObjects {

	public class Tribo {
		Collection<Pessoa> pessoa;
		Collection<Individuo> individuo;
	}

	public class Individuo {
		String alma;

		@Override
		public String toString() {
			return "alma=" + alma;
		}
	}

	public class Pessoa {
		String nome;
		String sobrenome;
		String endereco;

		@Override
		public String toString() {
			return nome + " " + sobrenome + " " + endereco;
		}
	}
	
	public class Audit{
		Collection<AuditObject> lista;
	}
	
	public class AuditObject {
		Collection<String> valores;
		Collection<String> colunas;
		String operacao;
		
		@Override
		public String toString() {
			return "operacao=" + operacao;
		}
	}

}
