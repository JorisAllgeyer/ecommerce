package fr.doranco.ecommerce.model.dao.exceptions;

public class DuplicateEntryExcpetion extends Exception {

	private static final long serialVersionUID = 1L;
	public DuplicateEntryExcpetion(String errMessage, Throwable err) {
		super(errMessage, err);
	}

}
