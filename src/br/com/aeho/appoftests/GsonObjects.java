package br.com.aeho.appoftests;

import java.util.Collection;

public class GsonObjects {

	public class Tribo {
		Collection<Pessoa> pessoa;
	}

	public class Pessoa {
		String nome;
		String sobrenome;

		@Override
		public String toString() {
			return nome + " " + sobrenome;
		}
	}

}
