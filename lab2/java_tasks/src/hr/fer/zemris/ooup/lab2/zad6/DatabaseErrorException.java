package hr.fer.zemris.ooup.lab2.zad6;

/**
 * Database error exception.
 * @author Viran
 *
 */
public class DatabaseErrorException extends RuntimeException {

	/**
	 * Auto-generated serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	
	private int errorCode;
	
	/**
	 * DatabaseErrorException constructor.
	 * @param string Exception message.
	 * @param errorCode Error code.
	 */
	public DatabaseErrorException(String string, int errorCode) {
		super(string);
		this.errorCode=errorCode;
	}

	@Override
	public String getMessage() {
		return super.getMessage()+"\nError code:"+this.errorCode;
	}
	
	/**
	 * Get the error code raised.
	 * @return Error code.
	 */
	public int getErroCode(){
		return this.errorCode;
	}

}
