/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.bbg.component;

import java.util.Map;

import net.sf.ehcache.CacheManager;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.opengamma.bbg.BloombergConnector;
import com.opengamma.bbg.livedata.BloombergLiveDataServer;
import com.opengamma.bbg.livedata.faketicks.BySchemeFakeSubscriptionSelector;
import com.opengamma.bbg.livedata.faketicks.ByTypeFakeSubscriptionSelector;
import com.opengamma.bbg.livedata.faketicks.CombiningBloombergLiveDataServer;
import com.opengamma.bbg.livedata.faketicks.FakeSubscriptionBloombergLiveDataServer;
import com.opengamma.bbg.livedata.faketicks.FakeSubscriptionSelector;
import com.opengamma.bbg.livedata.faketicks.UnionFakeSubscriptionSelector;
import com.opengamma.bbg.referencedata.ReferenceDataProvider;
import com.opengamma.component.ComponentRepository;
import com.opengamma.component.factory.livedata.AbstractStandardLiveDataServerComponentFactory;
import com.opengamma.core.id.ExternalSchemes;
import com.opengamma.id.ExternalScheme;
import com.opengamma.livedata.entitlement.LiveDataEntitlementChecker;
import com.opengamma.livedata.entitlement.UserEntitlementChecker;
import com.opengamma.livedata.resolver.DistributionSpecificationResolver;
import com.opengamma.livedata.server.AbstractLiveDataServer;
import com.opengamma.livedata.server.LiveDataServerMBean;
import com.opengamma.livedata.server.distribution.JmsSenderFactory;
import com.opengamma.provider.livedata.LiveDataMetaData;
import com.opengamma.provider.livedata.LiveDataServerTypes;
import com.opengamma.security.user.HibernateUserManager;
import com.opengamma.security.user.UserManager;
import com.opengamma.util.db.DbConnector;

/**
 * Component factory to create a Bloomberg server.
 */
@BeanDefinition
public class BloombergLiveDataServerComponentFactory extends AbstractStandardLiveDataServerComponentFactory {

  /**
   * The Bloomberg connector.
   */
  @PropertyDefinition(validate = "notNull")
  private BloombergConnector _bloombergConnector;
  /**
   * The database connector for user entitlement.
   */
  @PropertyDefinition(validate = "notNull")
  private DbConnector _dbConnector;
  /**
   * The reference data provider
   */
  @PropertyDefinition(validate = "notNull")
  private ReferenceDataProvider _referenceDataProvider;
  /**
   * The cache manager.
   */
  @PropertyDefinition(validate = "notNull")
  private CacheManager _cacheManager;
  /**
   * The maximum number of real tickers to subscribe to.
   */
  @PropertyDefinition(validate = "notNull")
  private Integer _subscriptionTickerLimit;

  //-------------------------------------------------------------------------
  @Override
  protected AbstractLiveDataServer initServer(ComponentRepository repo) {
    // real server
    BloombergLiveDataServer realServer = new BloombergLiveDataServer(getBloombergConnector(), getReferenceDataProvider(), getCacheManager());
    if (getSubscriptionTickerLimit() != null) {
      realServer.setSubscriptionLimit(getSubscriptionTickerLimit());
    }
    
    // plugins
    DistributionSpecificationResolver distSpecResolver = realServer.getDefaultDistributionSpecificationResolver();
    UserManager userManager = new HibernateUserManager(getDbConnector());
    LiveDataEntitlementChecker entitlementChecker = new UserEntitlementChecker(userManager, distSpecResolver);
    JmsSenderFactory senderFactory = new JmsSenderFactory(getJmsConnector());
    
    realServer.setDistributionSpecificationResolver(distSpecResolver);
    realServer.setEntitlementChecker(entitlementChecker);
    realServer.setMarketDataSenderFactory(senderFactory);
    repo.registerLifecycle(realServer);
    repo.registerMBean(new LiveDataServerMBean(realServer));
    
    // fake server
    FakeSubscriptionBloombergLiveDataServer fakeServer = new FakeSubscriptionBloombergLiveDataServer(realServer, getCacheManager());
    repo.registerLifecycle(fakeServer);
    repo.registerMBean(new LiveDataServerMBean(fakeServer));
    
    // combined
    // TODO: stop using this selector, everything should switch to explicit weak, but we have to wait for that change to propagate
    FakeSubscriptionSelector selectorVolatility = new ByTypeFakeSubscriptionSelector(
        ImmutableSet.of("SWAPTION VOLATILITY", "OPTION VOLATILITY"));
    FakeSubscriptionSelector selectorWeak = new BySchemeFakeSubscriptionSelector(
        ImmutableSet.of(ExternalSchemes.BLOOMBERG_BUID_WEAK, ExternalSchemes.BLOOMBERG_TICKER_WEAK));
    FakeSubscriptionSelector selector = new UnionFakeSubscriptionSelector(selectorVolatility, selectorWeak);
    
    CombiningBloombergLiveDataServer combinedServer = new CombiningBloombergLiveDataServer(fakeServer, realServer, selector, getCacheManager());
    combinedServer.setDistributionSpecificationResolver(distSpecResolver);
    combinedServer.setEntitlementChecker(entitlementChecker);
    combinedServer.setMarketDataSenderFactory(senderFactory);
    repo.registerMBean(new LiveDataServerMBean(combinedServer));
    return combinedServer;
  }

  @Override
  protected LiveDataMetaData createMetaData(ComponentRepository repo) {
    ImmutableList<ExternalScheme> schemes = ImmutableList.of(ExternalSchemes.BLOOMBERG_BUID, ExternalSchemes.BLOOMBERG_TICKER,
        ExternalSchemes.BLOOMBERG_TCM, ExternalSchemes.BLOOMBERG_BUID_WEAK, ExternalSchemes.BLOOMBERG_TICKER_WEAK);
    return new LiveDataMetaData(schemes, LiveDataServerTypes.STANDARD, "Bloomberg");
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code BloombergLiveDataServerComponentFactory}.
   * @return the meta-bean, not null
   */
  public static BloombergLiveDataServerComponentFactory.Meta meta() {
    return BloombergLiveDataServerComponentFactory.Meta.INSTANCE;
  }
  static {
    JodaBeanUtils.registerMetaBean(BloombergLiveDataServerComponentFactory.Meta.INSTANCE);
  }

  @Override
  public BloombergLiveDataServerComponentFactory.Meta metaBean() {
    return BloombergLiveDataServerComponentFactory.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case 2061648978:  // bloombergConnector
        return getBloombergConnector();
      case 39794031:  // dbConnector
        return getDbConnector();
      case -1788671322:  // referenceDataProvider
        return getReferenceDataProvider();
      case -1452875317:  // cacheManager
        return getCacheManager();
      case 268743028:  // subscriptionTickerLimit
        return getSubscriptionTickerLimit();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case 2061648978:  // bloombergConnector
        setBloombergConnector((BloombergConnector) newValue);
        return;
      case 39794031:  // dbConnector
        setDbConnector((DbConnector) newValue);
        return;
      case -1788671322:  // referenceDataProvider
        setReferenceDataProvider((ReferenceDataProvider) newValue);
        return;
      case -1452875317:  // cacheManager
        setCacheManager((CacheManager) newValue);
        return;
      case 268743028:  // subscriptionTickerLimit
        setSubscriptionTickerLimit((Integer) newValue);
        return;
    }
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  protected void validate() {
    JodaBeanUtils.notNull(_bloombergConnector, "bloombergConnector");
    JodaBeanUtils.notNull(_dbConnector, "dbConnector");
    JodaBeanUtils.notNull(_referenceDataProvider, "referenceDataProvider");
    JodaBeanUtils.notNull(_cacheManager, "cacheManager");
    JodaBeanUtils.notNull(_subscriptionTickerLimit, "subscriptionTickerLimit");
    super.validate();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      BloombergLiveDataServerComponentFactory other = (BloombergLiveDataServerComponentFactory) obj;
      return JodaBeanUtils.equal(getBloombergConnector(), other.getBloombergConnector()) &&
          JodaBeanUtils.equal(getDbConnector(), other.getDbConnector()) &&
          JodaBeanUtils.equal(getReferenceDataProvider(), other.getReferenceDataProvider()) &&
          JodaBeanUtils.equal(getCacheManager(), other.getCacheManager()) &&
          JodaBeanUtils.equal(getSubscriptionTickerLimit(), other.getSubscriptionTickerLimit()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(getBloombergConnector());
    hash += hash * 31 + JodaBeanUtils.hashCode(getDbConnector());
    hash += hash * 31 + JodaBeanUtils.hashCode(getReferenceDataProvider());
    hash += hash * 31 + JodaBeanUtils.hashCode(getCacheManager());
    hash += hash * 31 + JodaBeanUtils.hashCode(getSubscriptionTickerLimit());
    return hash ^ super.hashCode();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the Bloomberg connector.
   * @return the value of the property, not null
   */
  public BloombergConnector getBloombergConnector() {
    return _bloombergConnector;
  }

  /**
   * Sets the Bloomberg connector.
   * @param bloombergConnector  the new value of the property, not null
   */
  public void setBloombergConnector(BloombergConnector bloombergConnector) {
    JodaBeanUtils.notNull(bloombergConnector, "bloombergConnector");
    this._bloombergConnector = bloombergConnector;
  }

  /**
   * Gets the the {@code bloombergConnector} property.
   * @return the property, not null
   */
  public final Property<BloombergConnector> bloombergConnector() {
    return metaBean().bloombergConnector().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the database connector for user entitlement.
   * @return the value of the property, not null
   */
  public DbConnector getDbConnector() {
    return _dbConnector;
  }

  /**
   * Sets the database connector for user entitlement.
   * @param dbConnector  the new value of the property, not null
   */
  public void setDbConnector(DbConnector dbConnector) {
    JodaBeanUtils.notNull(dbConnector, "dbConnector");
    this._dbConnector = dbConnector;
  }

  /**
   * Gets the the {@code dbConnector} property.
   * @return the property, not null
   */
  public final Property<DbConnector> dbConnector() {
    return metaBean().dbConnector().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the reference data provider
   * @return the value of the property, not null
   */
  public ReferenceDataProvider getReferenceDataProvider() {
    return _referenceDataProvider;
  }

  /**
   * Sets the reference data provider
   * @param referenceDataProvider  the new value of the property, not null
   */
  public void setReferenceDataProvider(ReferenceDataProvider referenceDataProvider) {
    JodaBeanUtils.notNull(referenceDataProvider, "referenceDataProvider");
    this._referenceDataProvider = referenceDataProvider;
  }

  /**
   * Gets the the {@code referenceDataProvider} property.
   * @return the property, not null
   */
  public final Property<ReferenceDataProvider> referenceDataProvider() {
    return metaBean().referenceDataProvider().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the cache manager.
   * @return the value of the property, not null
   */
  public CacheManager getCacheManager() {
    return _cacheManager;
  }

  /**
   * Sets the cache manager.
   * @param cacheManager  the new value of the property, not null
   */
  public void setCacheManager(CacheManager cacheManager) {
    JodaBeanUtils.notNull(cacheManager, "cacheManager");
    this._cacheManager = cacheManager;
  }

  /**
   * Gets the the {@code cacheManager} property.
   * @return the property, not null
   */
  public final Property<CacheManager> cacheManager() {
    return metaBean().cacheManager().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the maximum number of real tickers to subscribe to.
   * @return the value of the property, not null
   */
  public Integer getSubscriptionTickerLimit() {
    return _subscriptionTickerLimit;
  }

  /**
   * Sets the maximum number of real tickers to subscribe to.
   * @param subscriptionTickerLimit  the new value of the property, not null
   */
  public void setSubscriptionTickerLimit(Integer subscriptionTickerLimit) {
    JodaBeanUtils.notNull(subscriptionTickerLimit, "subscriptionTickerLimit");
    this._subscriptionTickerLimit = subscriptionTickerLimit;
  }

  /**
   * Gets the the {@code subscriptionTickerLimit} property.
   * @return the property, not null
   */
  public final Property<Integer> subscriptionTickerLimit() {
    return metaBean().subscriptionTickerLimit().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code BloombergLiveDataServerComponentFactory}.
   */
  public static class Meta extends AbstractStandardLiveDataServerComponentFactory.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code bloombergConnector} property.
     */
    private final MetaProperty<BloombergConnector> _bloombergConnector = DirectMetaProperty.ofReadWrite(
        this, "bloombergConnector", BloombergLiveDataServerComponentFactory.class, BloombergConnector.class);
    /**
     * The meta-property for the {@code dbConnector} property.
     */
    private final MetaProperty<DbConnector> _dbConnector = DirectMetaProperty.ofReadWrite(
        this, "dbConnector", BloombergLiveDataServerComponentFactory.class, DbConnector.class);
    /**
     * The meta-property for the {@code referenceDataProvider} property.
     */
    private final MetaProperty<ReferenceDataProvider> _referenceDataProvider = DirectMetaProperty.ofReadWrite(
        this, "referenceDataProvider", BloombergLiveDataServerComponentFactory.class, ReferenceDataProvider.class);
    /**
     * The meta-property for the {@code cacheManager} property.
     */
    private final MetaProperty<CacheManager> _cacheManager = DirectMetaProperty.ofReadWrite(
        this, "cacheManager", BloombergLiveDataServerComponentFactory.class, CacheManager.class);
    /**
     * The meta-property for the {@code subscriptionTickerLimit} property.
     */
    private final MetaProperty<Integer> _subscriptionTickerLimit = DirectMetaProperty.ofReadWrite(
        this, "subscriptionTickerLimit", BloombergLiveDataServerComponentFactory.class, Integer.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
      this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "bloombergConnector",
        "dbConnector",
        "referenceDataProvider",
        "cacheManager",
        "subscriptionTickerLimit");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 2061648978:  // bloombergConnector
          return _bloombergConnector;
        case 39794031:  // dbConnector
          return _dbConnector;
        case -1788671322:  // referenceDataProvider
          return _referenceDataProvider;
        case -1452875317:  // cacheManager
          return _cacheManager;
        case 268743028:  // subscriptionTickerLimit
          return _subscriptionTickerLimit;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends BloombergLiveDataServerComponentFactory> builder() {
      return new DirectBeanBuilder<BloombergLiveDataServerComponentFactory>(new BloombergLiveDataServerComponentFactory());
    }

    @Override
    public Class<? extends BloombergLiveDataServerComponentFactory> beanType() {
      return BloombergLiveDataServerComponentFactory.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code bloombergConnector} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<BloombergConnector> bloombergConnector() {
      return _bloombergConnector;
    }

    /**
     * The meta-property for the {@code dbConnector} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<DbConnector> dbConnector() {
      return _dbConnector;
    }

    /**
     * The meta-property for the {@code referenceDataProvider} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ReferenceDataProvider> referenceDataProvider() {
      return _referenceDataProvider;
    }

    /**
     * The meta-property for the {@code cacheManager} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<CacheManager> cacheManager() {
      return _cacheManager;
    }

    /**
     * The meta-property for the {@code subscriptionTickerLimit} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Integer> subscriptionTickerLimit() {
      return _subscriptionTickerLimit;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
