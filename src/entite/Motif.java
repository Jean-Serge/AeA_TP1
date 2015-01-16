package entite;

public class Motif {
	
	private String motif;
	
	public Motif(String texte) {
		this.motif = texte;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}
	
	public int SizeofMotif() {
		return this.motif.length();
	}
	
	public Motif Complementary() {
		return null;
	}
	
	public Motif Reverse() {
		return null;
	}
	
	public Motif ReverseComplementary() {
		return null;
	}
	
	public char CharComplementary(char c) {
		switch (c) {
		case 'A': return 'T';
		case 'T': return 'A';
		case 'G': return 'C';
		case 'C': return 'G';
		default: return '0';
		}
	}
	
}
