package dab;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import dab.execption.IllegalCardUseException;
import dab.execption.IllegalOperationException;
import mockit.Expectations;
import mockit.Mocked;

public class TestSession {

	private Session session;
	
	@Mocked Banque banque;
	@Mocked Carte carte;
	@Mocked Distributeur distributeur;
	
	@Test
	public void testConstructor() {
		session = new Session(banque, carte, distributeur, 54);
		assertNotNull(session);
	}
	
	@Test
	public void testStartSession1() {
		new Expectations() {{
			carte.getNumero();
			result = 12;
			banque.authentifier(12, 13);
			result = true;
			carte.getCodeSecurite();
			result = 13;
		}};
		session = new Session(banque, carte, distributeur, 54);
		session.startSession();
		assertTrue(session.isConnecte());
	}
	
	@Test
	public void testStartSession2() {
		new Expectations() {{
			carte.getNumero();
			result = 12;
			banque.authentifier(12, 13);
			result = true;
			carte.getCodeSecurite();
			result = 13;
		}};
		session = new Session(banque, carte, distributeur, 54);
		session.startSession();
		session.startSession();
		assertTrue(session.isConnecte());
	}
	
	@Test
	public void testvaliderOperation1() throws IllegalCardUseException, IllegalOperationException {
		new Expectations() {{
			distributeur.sendPin(54);
			result = 1234;
			carte.getPin();
			result = 1234;
			banque.autoriser(54, "coucou");
			result = true;
		}};
		session = new Session(banque, carte, distributeur, 54);
		session.validerOperation("coucou");
	}
	
	@Test(expected = IllegalCardUseException.class)
	public void testvaliderOperation2() throws IllegalCardUseException, IllegalOperationException {
		new Expectations() {{
			distributeur.sendPin(54);
			result = 1234;
			times = 4;
			carte.getPin();
			result = 12345;
			times= 4;
		}};
		session = new Session(banque, carte, distributeur, 54);
		session.validerOperation("coucou");
	}
	
	@Test(expected = IllegalOperationException.class)
	public void testvaliderOperation3() throws IllegalCardUseException, IllegalOperationException {
		new Expectations() {{
			distributeur.sendPin(54);
			result = 1234;
			carte.getPin();
			result = 1234;
			banque.autoriser(54, "coucou");
			result = false;
		}};
		session = new Session(banque, carte, distributeur, 54);
		session.validerOperation("coucou");
	}
	
	@Test
	public void testvaliderOperation4() throws IllegalCardUseException, IllegalOperationException {
		new Expectations() {{
			distributeur.sendPin(54);
			result = 12345;
			distributeur.sendPin(54);
			result = 1234;
			carte.getPin();
			result = 1234;
			banque.autoriser(54, "coucou");
			result = true;
		}};
		session = new Session(banque, carte, distributeur, 54);
		session.validerOperation("coucou");
	}
}
