package uk.org.lidalia.net;

import java.util.Arrays;
import java.util.List;

import uk.org.lidalia.lang.Immutable;

public final class FullyQualifiedDomain extends Host {

	private final TopLevelDomainLabel topLevelDomainLabel;
	private final List<DomainLabel> subdomains;
	
	public FullyQualifiedDomain(TopLevelDomainLabel topLevelDomainLabel, DomainLabel... domainLabels) {
		this.topLevelDomainLabel = topLevelDomainLabel;
		this.subdomains = Arrays.asList(domainLabels);
	}
	
	public TopLevelDomainLabel getTopLevelDomainLabel() {
		return topLevelDomainLabel;
	}
	
	public List<DomainLabel> getSubdomains() {
		return subdomains;
	}

	@Override
	public FullyQualifiedDomain toImmutable() {
		return this;
	}
}
