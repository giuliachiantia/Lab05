package it.polito.tdp.anagrammi.model;

import java.util.HashSet;
import java.util.Set;

import it.polito.tdp.anagrammi.DAO.AnagrammaDao;

public class Model {
	
	private AnagrammaDao anagrammaDao;
	Set<Anagramma> listaAnagrammi = new HashSet<Anagramma>();
	
	public Model() {
		this.anagrammaDao=new AnagrammaDao();
	}
	
	public Set<Anagramma> getListaAnagrammi() {
		return listaAnagrammi;
	}
	
	public void setListaAnagrammi(Set<Anagramma> listaAnagrammi) {
		this.listaAnagrammi = listaAnagrammi;
	}
	
	public void anagramma(String s) {
		anagramma_ricorsiva("", 0, s);
	}

	private void anagramma_ricorsiva(String parziale, int livello, String rimanenti) {
		if (rimanenti.length() == 0) { /* caso terminale */
			
			Anagramma a=new Anagramma(parziale);
			if(anagrammaDao.isCorrect(a.getAnagramma())) {
				a.setCorrect(true);
			}
			this.listaAnagrammi.add(a);
		} else {
			for (int pos = 0; pos < rimanenti.length(); pos++) {
				String nuova_parziale = parziale + rimanenti.charAt(pos);
				String nuova_rimanenti = rimanenti.substring(0, pos) + rimanenti.substring(pos + 1);
				anagramma_ricorsiva(nuova_parziale, livello + 1, nuova_rimanenti);
			}
		}
	}

}
