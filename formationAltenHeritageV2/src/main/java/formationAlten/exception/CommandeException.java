package formationAlten.exception;

public class CommandeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CommandeException() {
		
	}
	
	public CommandeException(String message) {
		super(message);
	}
}
