/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.security.future;

import java.util.Map;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.financial.security.FinancialSecurity;
import com.opengamma.util.money.Currency;
import com.opengamma.util.time.Expiry;

/**
 * An abstract base class for future securities.
 */
@BeanDefinition
public abstract class FutureSecurity extends FinancialSecurity {

  /** Serialization version. */
  private static final long serialVersionUID = 1L;

  /**
   * The security type.
   */
  public static final String SECURITY_TYPE = "FUTURE";

  /**
   * The expiry.
   */
  @PropertyDefinition(validate = "notNull")
  private Expiry _expiry;
  /**
   * The trading exchange.
   */
  @PropertyDefinition(validate = "notNull")
  private String _tradingExchange;
  /**
   * The settlement exchange.
   */
  @PropertyDefinition(validate = "notNull")
  private String _settlementExchange;
  /**
   * The currency.
   */
  @PropertyDefinition(validate = "notNull")
  private Currency _currency;
  /**
   * The unit amount. This represents the PNL of a single long contract if its price increases by 1.0. Also known as the 'Point Value'. 
   */
  @PropertyDefinition
  private double _unitAmount;

  /**
   * The future category.
   */
  @PropertyDefinition(validate = "notNull")
  private String _contractCategory;


  /**
   * Creates an empty instance.
   * <p>
   * The security details should be set before use.
   */
  protected FutureSecurity() {
    super(SECURITY_TYPE);
  }

  protected FutureSecurity(Expiry expiry, String tradingExchange, String settlementExchange, Currency currency, double unitAmount, String category) {
    super(SECURITY_TYPE);
    setExpiry(expiry);
    setTradingExchange(tradingExchange);
    setSettlementExchange(settlementExchange);
    setCurrency(currency);
    setUnitAmount(unitAmount);
    setContractCategory(category);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code FutureSecurity}.
   * @return the meta-bean, not null
   */
  public static FutureSecurity.Meta meta() {
    return FutureSecurity.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(FutureSecurity.Meta.INSTANCE);
  }

  @Override
  public FutureSecurity.Meta metaBean() {
    return FutureSecurity.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -1289159373:  // expiry
        return getExpiry();
      case -661485980:  // tradingExchange
        return getTradingExchange();
      case 389497452:  // settlementExchange
        return getSettlementExchange();
      case 575402001:  // currency
        return getCurrency();
      case 1673913084:  // unitAmount
        return getUnitAmount();
      case -666828752:  // contractCategory
        return getContractCategory();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -1289159373:  // expiry
        setExpiry((Expiry) newValue);
        return;
      case -661485980:  // tradingExchange
        setTradingExchange((String) newValue);
        return;
      case 389497452:  // settlementExchange
        setSettlementExchange((String) newValue);
        return;
      case 575402001:  // currency
        setCurrency((Currency) newValue);
        return;
      case 1673913084:  // unitAmount
        setUnitAmount((Double) newValue);
        return;
      case -666828752:  // contractCategory
        setContractCategory((String) newValue);
        return;
    }
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  protected void validate() {
    JodaBeanUtils.notNull(_expiry, "expiry");
    JodaBeanUtils.notNull(_tradingExchange, "tradingExchange");
    JodaBeanUtils.notNull(_settlementExchange, "settlementExchange");
    JodaBeanUtils.notNull(_currency, "currency");
    JodaBeanUtils.notNull(_contractCategory, "contractCategory");
    super.validate();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      FutureSecurity other = (FutureSecurity) obj;
      return JodaBeanUtils.equal(getExpiry(), other.getExpiry()) &&
          JodaBeanUtils.equal(getTradingExchange(), other.getTradingExchange()) &&
          JodaBeanUtils.equal(getSettlementExchange(), other.getSettlementExchange()) &&
          JodaBeanUtils.equal(getCurrency(), other.getCurrency()) &&
          JodaBeanUtils.equal(getUnitAmount(), other.getUnitAmount()) &&
          JodaBeanUtils.equal(getContractCategory(), other.getContractCategory()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(getExpiry());
    hash += hash * 31 + JodaBeanUtils.hashCode(getTradingExchange());
    hash += hash * 31 + JodaBeanUtils.hashCode(getSettlementExchange());
    hash += hash * 31 + JodaBeanUtils.hashCode(getCurrency());
    hash += hash * 31 + JodaBeanUtils.hashCode(getUnitAmount());
    hash += hash * 31 + JodaBeanUtils.hashCode(getContractCategory());
    return hash ^ super.hashCode();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the expiry.
   * @return the value of the property, not null
   */
  public Expiry getExpiry() {
    return _expiry;
  }

  /**
   * Sets the expiry.
   * @param expiry  the new value of the property, not null
   */
  public void setExpiry(Expiry expiry) {
    JodaBeanUtils.notNull(expiry, "expiry");
    this._expiry = expiry;
  }

  /**
   * Gets the the {@code expiry} property.
   * @return the property, not null
   */
  public final Property<Expiry> expiry() {
    return metaBean().expiry().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the trading exchange.
   * @return the value of the property, not null
   */
  public String getTradingExchange() {
    return _tradingExchange;
  }

  /**
   * Sets the trading exchange.
   * @param tradingExchange  the new value of the property, not null
   */
  public void setTradingExchange(String tradingExchange) {
    JodaBeanUtils.notNull(tradingExchange, "tradingExchange");
    this._tradingExchange = tradingExchange;
  }

  /**
   * Gets the the {@code tradingExchange} property.
   * @return the property, not null
   */
  public final Property<String> tradingExchange() {
    return metaBean().tradingExchange().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the settlement exchange.
   * @return the value of the property, not null
   */
  public String getSettlementExchange() {
    return _settlementExchange;
  }

  /**
   * Sets the settlement exchange.
   * @param settlementExchange  the new value of the property, not null
   */
  public void setSettlementExchange(String settlementExchange) {
    JodaBeanUtils.notNull(settlementExchange, "settlementExchange");
    this._settlementExchange = settlementExchange;
  }

  /**
   * Gets the the {@code settlementExchange} property.
   * @return the property, not null
   */
  public final Property<String> settlementExchange() {
    return metaBean().settlementExchange().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the currency.
   * @return the value of the property, not null
   */
  public Currency getCurrency() {
    return _currency;
  }

  /**
   * Sets the currency.
   * @param currency  the new value of the property, not null
   */
  public void setCurrency(Currency currency) {
    JodaBeanUtils.notNull(currency, "currency");
    this._currency = currency;
  }

  /**
   * Gets the the {@code currency} property.
   * @return the property, not null
   */
  public final Property<Currency> currency() {
    return metaBean().currency().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the unit amount. This represents the PNL of a single long contract if its price increases by 1.0. Also known as the 'Point Value'.
   * @return the value of the property
   */
  public double getUnitAmount() {
    return _unitAmount;
  }

  /**
   * Sets the unit amount. This represents the PNL of a single long contract if its price increases by 1.0. Also known as the 'Point Value'.
   * @param unitAmount  the new value of the property
   */
  public void setUnitAmount(double unitAmount) {
    this._unitAmount = unitAmount;
  }

  /**
   * Gets the the {@code unitAmount} property.
   * @return the property, not null
   */
  public final Property<Double> unitAmount() {
    return metaBean().unitAmount().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the future category.
   * @return the value of the property, not null
   */
  public String getContractCategory() {
    return _contractCategory;
  }

  /**
   * Sets the future category.
   * @param contractCategory  the new value of the property, not null
   */
  public void setContractCategory(String contractCategory) {
    JodaBeanUtils.notNull(contractCategory, "contractCategory");
    this._contractCategory = contractCategory;
  }

  /**
   * Gets the the {@code contractCategory} property.
   * @return the property, not null
   */
  public final Property<String> contractCategory() {
    return metaBean().contractCategory().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code FutureSecurity}.
   */
  public static class Meta extends FinancialSecurity.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code expiry} property.
     */
    private final MetaProperty<Expiry> _expiry = DirectMetaProperty.ofReadWrite(
        this, "expiry", FutureSecurity.class, Expiry.class);
    /**
     * The meta-property for the {@code tradingExchange} property.
     */
    private final MetaProperty<String> _tradingExchange = DirectMetaProperty.ofReadWrite(
        this, "tradingExchange", FutureSecurity.class, String.class);
    /**
     * The meta-property for the {@code settlementExchange} property.
     */
    private final MetaProperty<String> _settlementExchange = DirectMetaProperty.ofReadWrite(
        this, "settlementExchange", FutureSecurity.class, String.class);
    /**
     * The meta-property for the {@code currency} property.
     */
    private final MetaProperty<Currency> _currency = DirectMetaProperty.ofReadWrite(
        this, "currency", FutureSecurity.class, Currency.class);
    /**
     * The meta-property for the {@code unitAmount} property.
     */
    private final MetaProperty<Double> _unitAmount = DirectMetaProperty.ofReadWrite(
        this, "unitAmount", FutureSecurity.class, Double.TYPE);
    /**
     * The meta-property for the {@code contractCategory} property.
     */
    private final MetaProperty<String> _contractCategory = DirectMetaProperty.ofReadWrite(
        this, "contractCategory", FutureSecurity.class, String.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "expiry",
        "tradingExchange",
        "settlementExchange",
        "currency",
        "unitAmount",
        "contractCategory");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -1289159373:  // expiry
          return _expiry;
        case -661485980:  // tradingExchange
          return _tradingExchange;
        case 389497452:  // settlementExchange
          return _settlementExchange;
        case 575402001:  // currency
          return _currency;
        case 1673913084:  // unitAmount
          return _unitAmount;
        case -666828752:  // contractCategory
          return _contractCategory;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends FutureSecurity> builder() {
      throw new UnsupportedOperationException("FutureSecurity is an abstract class");
    }

    @Override
    public Class<? extends FutureSecurity> beanType() {
      return FutureSecurity.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code expiry} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Expiry> expiry() {
      return _expiry;
    }

    /**
     * The meta-property for the {@code tradingExchange} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> tradingExchange() {
      return _tradingExchange;
    }

    /**
     * The meta-property for the {@code settlementExchange} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> settlementExchange() {
      return _settlementExchange;
    }

    /**
     * The meta-property for the {@code currency} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Currency> currency() {
      return _currency;
    }

    /**
     * The meta-property for the {@code unitAmount} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Double> unitAmount() {
      return _unitAmount;
    }

    /**
     * The meta-property for the {@code contractCategory} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> contractCategory() {
      return _contractCategory;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}