package dab;

import dab.execption.IllegalCardUseException;
import dab.execption.IllegalOperationException;

public class Session {

	private final int CARD_TRY = 4;

	private Banque banque;
	private Carte carte;
	private Distributeur distributeur;

	private boolean connecte = false;
	private int numeroSession;

	public Session(Banque banque, Carte carte, Distributeur distributeur, int numeroSession) {
		this.banque = banque;
		this.carte = carte;
		this.distributeur = distributeur;
		this.numeroSession = numeroSession;
	}

	public void startSession() {
		if (!connecte)
			connecte = banque.authentifier(carte.getNumero(), carte.getCodeSecurite());
	}

	public void validerOperation(String op) throws IllegalCardUseException, IllegalOperationException {
		int nbTry = 0;
		while (nbTry < CARD_TRY) {
			if (distributeur.sendPin(numeroSession) == carte.getPin()) {
				if (!banque.autoriser(numeroSession, op))
					throw new IllegalOperationException();
				return;
			}
			nbTry++;
		}
		throw new IllegalCardUseException();
	}

	public boolean isConnecte() {
		return connecte;
	}

}
