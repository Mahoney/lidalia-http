package uk.org.lidalia.net;

public class AbsoluteHierarchicalUri extends AbsoluteUri implements HierarchicalUri {

	protected AbsoluteHierarchicalUri(Scheme scheme) {
		super(scheme);
	}

	@Override
	public SchemeSpecificPart getSchemeSpecificPart() {
		return null;
	}
}
