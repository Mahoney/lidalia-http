package uk.org.lidalia.net;

public abstract class AbsoluteUri implements Uri {

	private final Scheme scheme;
	
	protected AbsoluteUri(Scheme scheme) {
		this.scheme = scheme;
	}
	
	public Scheme getScheme() {
		return scheme;
	}
}
