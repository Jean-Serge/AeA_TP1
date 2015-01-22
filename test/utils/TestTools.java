package utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestTools {

	/**
	 * Teste la fonction static isNumber permettant de savoir
	 * si une chaîne de caractère est un integer. 
	 * Utilisée lors de la lecture des arguments pour chercher 
	 * le paramètre N.
	 */
	@Test
	public void testIsNumber(){
		assertTrue(Tools.isInteger("45"));
		assertFalse(Tools.isInteger("toto"));
		assertTrue(Tools.isInteger("0"));
		assertFalse(Tools.isInteger(""));
	}
}
