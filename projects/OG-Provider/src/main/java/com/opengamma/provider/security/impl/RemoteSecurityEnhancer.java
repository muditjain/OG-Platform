/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.provider.security.impl;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.opengamma.core.security.Security;
import com.opengamma.provider.security.SecurityEnhancer;
import com.opengamma.provider.security.SecurityEnhancerRequest;
import com.opengamma.provider.security.SecurityEnhancerResult;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.rest.AbstractRemoteClient;

/**
 * Provides access to a remote security enhancer.
 * <p>
 * This is a client that connects to a security enhancer at a remote URI.
 */
public class RemoteSecurityEnhancer extends AbstractRemoteClient implements SecurityEnhancer {

  /**
   * Creates an instance.
   * 
   * @param baseUri  the base target URI for all RESTful web services, not null
   */
  public RemoteSecurityEnhancer(final URI baseUri) {
    super(baseUri);
  }

  //-------------------------------------------------------------------------
  // delegate convenience methods to request/result method
  // code copied from AbstractSecurityEnhancer due to lack of multiple inheritance
  @Override
  public Security enhanceSecurity(Security security) {
    SecurityEnhancerRequest request = SecurityEnhancerRequest.create(security);
    SecurityEnhancerResult result = enhanceSecurities(request);
    return Iterables.getOnlyElement(result.getResultList());
  }

  @Override
  public List<Security> enhanceSecurities(List<Security> securities) {
    SecurityEnhancerRequest request = SecurityEnhancerRequest.create(securities);
    SecurityEnhancerResult result = enhanceSecurities(request);
    return result.getResultList();
  }

  @Override
  public <R> Map<R, Security> enhanceSecurities(Map<R, Security> securities) {
    List<R> keyList = Lists.newArrayList();
    List<Security> securityList = Lists.newArrayList();
    for (Entry<R, Security> entry : securities.entrySet()) {
      keyList.add(entry.getKey());
      securityList.add(entry.getValue());
    }
    securityList = enhanceSecurities(securityList);
    Map<R, Security> result = Maps.newHashMap();
    for (int i = 0; i < keyList.size(); i++) {
      result.put(keyList.get(i), securityList.get(i));
    }
    return result;
  }

  //-------------------------------------------------------------------------
  @Override
  public SecurityEnhancerResult enhanceSecurities(SecurityEnhancerRequest request) {
    ArgumentChecker.notNull(request, "request");
    
    URI uri = DataSecurityEnhancerResource.uriGet(getBaseUri());
    return accessRemote(uri).post(SecurityEnhancerResult.class, request);
  }

}
