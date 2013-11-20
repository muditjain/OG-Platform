/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.security.irs;

import java.util.Map;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.id.ExternalId;
import com.opengamma.util.ArgumentChecker;

/**
 * A floating rate swap leg.
 */
@BeanDefinition
public class FloatingInterestRateSwapLeg extends InterestRateSwapLeg {

  /**
   * Custom rates, e.g. an initial rate
   */
  @PropertyDefinition
  private Rate _customRates;

  /**
   * The rate averaging method
   */
  @PropertyDefinition
  private RateAveragingMethod _rateAveragingMethod;

  /**
   * The cap.
   */
  //TODO: should be a complex type
  @PropertyDefinition
  private Double _capRate;

  /**
   * The floor.
   */
  //TODO: should be a complex type
  @PropertyDefinition
  private Double _floorRate;

  /**
   * The gearing.
   */
  // Will this vary?
  @PropertyDefinition(validate = "notNull")
  private double _gearing;

  /**
   * The spreads, can be null, constant or complex.
   */
  @PropertyDefinition
  private Rate _spreadSchedule;

  /**
   * The floating rate convention.
   */
  @PropertyDefinition(validate = "notNull")
  private FloatingInterestRateSwapLegConvention _convention;

  /**
   * The schedule for the dates on this leg.
   * Allows the conventions to be overridden with specific dates.
   */
  @PropertyDefinition
  private FloatingInterestRateSwapLegSchedule _schedule;

  @Override
  public <T> T accept(InterestRateSwapLegVisitor<T> visitor) {
    return visitor.visitFloatingInterestRateSwapLeg(this);
  }

  /**
   * Get the floating rate id
   *
   * @return the floating rate id
   */
  public ExternalId getFloatingReferenceRateId() {
    ArgumentChecker.isTrue(!getConvention().getExternalIdBundle().isEmpty(), "FloatingRateId not set");
    return getConvention().getExternalIdBundle().iterator().next().getExternalId();
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code FloatingInterestRateSwapLeg}.
   * @return the meta-bean, not null
   */
  public static FloatingInterestRateSwapLeg.Meta meta() {
    return FloatingInterestRateSwapLeg.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(FloatingInterestRateSwapLeg.Meta.INSTANCE);
  }

  @Override
  public FloatingInterestRateSwapLeg.Meta metaBean() {
    return FloatingInterestRateSwapLeg.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets custom rates, e.g. an initial rate
   * @return the value of the property
   */
  public Rate getCustomRates() {
    return _customRates;
  }

  /**
   * Sets custom rates, e.g. an initial rate
   * @param customRates  the new value of the property
   */
  public void setCustomRates(Rate customRates) {
    this._customRates = customRates;
  }

  /**
   * Gets the the {@code customRates} property.
   * @return the property, not null
   */
  public final Property<Rate> customRates() {
    return metaBean().customRates().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the rate averaging method
   * @return the value of the property
   */
  public RateAveragingMethod getRateAveragingMethod() {
    return _rateAveragingMethod;
  }

  /**
   * Sets the rate averaging method
   * @param rateAveragingMethod  the new value of the property
   */
  public void setRateAveragingMethod(RateAveragingMethod rateAveragingMethod) {
    this._rateAveragingMethod = rateAveragingMethod;
  }

  /**
   * Gets the the {@code rateAveragingMethod} property.
   * @return the property, not null
   */
  public final Property<RateAveragingMethod> rateAveragingMethod() {
    return metaBean().rateAveragingMethod().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the capRate.
   * @return the value of the property
   */
  public Double getCapRate() {
    return _capRate;
  }

  /**
   * Sets the capRate.
   * @param capRate  the new value of the property
   */
  public void setCapRate(Double capRate) {
    this._capRate = capRate;
  }

  /**
   * Gets the the {@code capRate} property.
   * @return the property, not null
   */
  public final Property<Double> capRate() {
    return metaBean().capRate().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the floorRate.
   * @return the value of the property
   */
  public Double getFloorRate() {
    return _floorRate;
  }

  /**
   * Sets the floorRate.
   * @param floorRate  the new value of the property
   */
  public void setFloorRate(Double floorRate) {
    this._floorRate = floorRate;
  }

  /**
   * Gets the the {@code floorRate} property.
   * @return the property, not null
   */
  public final Property<Double> floorRate() {
    return metaBean().floorRate().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the gearing.
   * @return the value of the property, not null
   */
  public double getGearing() {
    return _gearing;
  }

  /**
   * Sets the gearing.
   * @param gearing  the new value of the property, not null
   */
  public void setGearing(double gearing) {
    JodaBeanUtils.notNull(gearing, "gearing");
    this._gearing = gearing;
  }

  /**
   * Gets the the {@code gearing} property.
   * @return the property, not null
   */
  public final Property<Double> gearing() {
    return metaBean().gearing().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the spreads, can be null, constant or complex.
   * @return the value of the property
   */
  public Rate getSpreadSchedule() {
    return _spreadSchedule;
  }

  /**
   * Sets the spreads, can be null, constant or complex.
   * @param spreadSchedule  the new value of the property
   */
  public void setSpreadSchedule(Rate spreadSchedule) {
    this._spreadSchedule = spreadSchedule;
  }

  /**
   * Gets the the {@code spreadSchedule} property.
   * @return the property, not null
   */
  public final Property<Rate> spreadSchedule() {
    return metaBean().spreadSchedule().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the floating rate convention.
   * @return the value of the property, not null
   */
  public FloatingInterestRateSwapLegConvention getConvention() {
    return _convention;
  }

  /**
   * Sets the floating rate convention.
   * @param convention  the new value of the property, not null
   */
  public void setConvention(FloatingInterestRateSwapLegConvention convention) {
    JodaBeanUtils.notNull(convention, "convention");
    this._convention = convention;
  }

  /**
   * Gets the the {@code convention} property.
   * @return the property, not null
   */
  public final Property<FloatingInterestRateSwapLegConvention> convention() {
    return metaBean().convention().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the schedule for the dates on this leg.
   * Allows the conventions to be overridden with specific dates.
   * @return the value of the property
   */
  public FloatingInterestRateSwapLegSchedule getSchedule() {
    return _schedule;
  }

  /**
   * Sets the schedule for the dates on this leg.
   * Allows the conventions to be overridden with specific dates.
   * @param schedule  the new value of the property
   */
  public void setSchedule(FloatingInterestRateSwapLegSchedule schedule) {
    this._schedule = schedule;
  }

  /**
   * Gets the the {@code schedule} property.
   * Allows the conventions to be overridden with specific dates.
   * @return the property, not null
   */
  public final Property<FloatingInterestRateSwapLegSchedule> schedule() {
    return metaBean().schedule().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public FloatingInterestRateSwapLeg clone() {
    return (FloatingInterestRateSwapLeg) super.clone();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      FloatingInterestRateSwapLeg other = (FloatingInterestRateSwapLeg) obj;
      return JodaBeanUtils.equal(getCustomRates(), other.getCustomRates()) &&
          JodaBeanUtils.equal(getRateAveragingMethod(), other.getRateAveragingMethod()) &&
          JodaBeanUtils.equal(getCapRate(), other.getCapRate()) &&
          JodaBeanUtils.equal(getFloorRate(), other.getFloorRate()) &&
          JodaBeanUtils.equal(getGearing(), other.getGearing()) &&
          JodaBeanUtils.equal(getSpreadSchedule(), other.getSpreadSchedule()) &&
          JodaBeanUtils.equal(getConvention(), other.getConvention()) &&
          JodaBeanUtils.equal(getSchedule(), other.getSchedule()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(getCustomRates());
    hash += hash * 31 + JodaBeanUtils.hashCode(getRateAveragingMethod());
    hash += hash * 31 + JodaBeanUtils.hashCode(getCapRate());
    hash += hash * 31 + JodaBeanUtils.hashCode(getFloorRate());
    hash += hash * 31 + JodaBeanUtils.hashCode(getGearing());
    hash += hash * 31 + JodaBeanUtils.hashCode(getSpreadSchedule());
    hash += hash * 31 + JodaBeanUtils.hashCode(getConvention());
    hash += hash * 31 + JodaBeanUtils.hashCode(getSchedule());
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(288);
    buf.append("FloatingInterestRateSwapLeg{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  @Override
  protected void toString(StringBuilder buf) {
    super.toString(buf);
    buf.append("customRates").append('=').append(JodaBeanUtils.toString(getCustomRates())).append(',').append(' ');
    buf.append("rateAveragingMethod").append('=').append(JodaBeanUtils.toString(getRateAveragingMethod())).append(',').append(' ');
    buf.append("capRate").append('=').append(JodaBeanUtils.toString(getCapRate())).append(',').append(' ');
    buf.append("floorRate").append('=').append(JodaBeanUtils.toString(getFloorRate())).append(',').append(' ');
    buf.append("gearing").append('=').append(JodaBeanUtils.toString(getGearing())).append(',').append(' ');
    buf.append("spreadSchedule").append('=').append(JodaBeanUtils.toString(getSpreadSchedule())).append(',').append(' ');
    buf.append("convention").append('=').append(JodaBeanUtils.toString(getConvention())).append(',').append(' ');
    buf.append("schedule").append('=').append(JodaBeanUtils.toString(getSchedule())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code FloatingInterestRateSwapLeg}.
   */
  public static class Meta extends InterestRateSwapLeg.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code customRates} property.
     */
    private final MetaProperty<Rate> _customRates = DirectMetaProperty.ofReadWrite(
        this, "customRates", FloatingInterestRateSwapLeg.class, Rate.class);
    /**
     * The meta-property for the {@code rateAveragingMethod} property.
     */
    private final MetaProperty<RateAveragingMethod> _rateAveragingMethod = DirectMetaProperty.ofReadWrite(
        this, "rateAveragingMethod", FloatingInterestRateSwapLeg.class, RateAveragingMethod.class);
    /**
     * The meta-property for the {@code capRate} property.
     */
    private final MetaProperty<Double> _capRate = DirectMetaProperty.ofReadWrite(
        this, "capRate", FloatingInterestRateSwapLeg.class, Double.class);
    /**
     * The meta-property for the {@code floorRate} property.
     */
    private final MetaProperty<Double> _floorRate = DirectMetaProperty.ofReadWrite(
        this, "floorRate", FloatingInterestRateSwapLeg.class, Double.class);
    /**
     * The meta-property for the {@code gearing} property.
     */
    private final MetaProperty<Double> _gearing = DirectMetaProperty.ofReadWrite(
        this, "gearing", FloatingInterestRateSwapLeg.class, Double.TYPE);
    /**
     * The meta-property for the {@code spreadSchedule} property.
     */
    private final MetaProperty<Rate> _spreadSchedule = DirectMetaProperty.ofReadWrite(
        this, "spreadSchedule", FloatingInterestRateSwapLeg.class, Rate.class);
    /**
     * The meta-property for the {@code convention} property.
     */
    private final MetaProperty<FloatingInterestRateSwapLegConvention> _convention = DirectMetaProperty.ofReadWrite(
        this, "convention", FloatingInterestRateSwapLeg.class, FloatingInterestRateSwapLegConvention.class);
    /**
     * The meta-property for the {@code schedule} property.
     */
    private final MetaProperty<FloatingInterestRateSwapLegSchedule> _schedule = DirectMetaProperty.ofReadWrite(
        this, "schedule", FloatingInterestRateSwapLeg.class, FloatingInterestRateSwapLegSchedule.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "customRates",
        "rateAveragingMethod",
        "capRate",
        "floorRate",
        "gearing",
        "spreadSchedule",
        "convention",
        "schedule");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -1790094686:  // customRates
          return _customRates;
        case 154998811:  // rateAveragingMethod
          return _rateAveragingMethod;
        case 551552978:  // capRate
          return _capRate;
        case -1712455924:  // floorRate
          return _floorRate;
        case -91774989:  // gearing
          return _gearing;
        case 1343931690:  // spreadSchedule
          return _spreadSchedule;
        case 2039569265:  // convention
          return _convention;
        case -697920873:  // schedule
          return _schedule;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends FloatingInterestRateSwapLeg> builder() {
      return new DirectBeanBuilder<FloatingInterestRateSwapLeg>(new FloatingInterestRateSwapLeg());
    }

    @Override
    public Class<? extends FloatingInterestRateSwapLeg> beanType() {
      return FloatingInterestRateSwapLeg.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code customRates} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Rate> customRates() {
      return _customRates;
    }

    /**
     * The meta-property for the {@code rateAveragingMethod} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<RateAveragingMethod> rateAveragingMethod() {
      return _rateAveragingMethod;
    }

    /**
     * The meta-property for the {@code capRate} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Double> capRate() {
      return _capRate;
    }

    /**
     * The meta-property for the {@code floorRate} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Double> floorRate() {
      return _floorRate;
    }

    /**
     * The meta-property for the {@code gearing} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Double> gearing() {
      return _gearing;
    }

    /**
     * The meta-property for the {@code spreadSchedule} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Rate> spreadSchedule() {
      return _spreadSchedule;
    }

    /**
     * The meta-property for the {@code convention} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<FloatingInterestRateSwapLegConvention> convention() {
      return _convention;
    }

    /**
     * The meta-property for the {@code schedule} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<FloatingInterestRateSwapLegSchedule> schedule() {
      return _schedule;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -1790094686:  // customRates
          return ((FloatingInterestRateSwapLeg) bean).getCustomRates();
        case 154998811:  // rateAveragingMethod
          return ((FloatingInterestRateSwapLeg) bean).getRateAveragingMethod();
        case 551552978:  // capRate
          return ((FloatingInterestRateSwapLeg) bean).getCapRate();
        case -1712455924:  // floorRate
          return ((FloatingInterestRateSwapLeg) bean).getFloorRate();
        case -91774989:  // gearing
          return ((FloatingInterestRateSwapLeg) bean).getGearing();
        case 1343931690:  // spreadSchedule
          return ((FloatingInterestRateSwapLeg) bean).getSpreadSchedule();
        case 2039569265:  // convention
          return ((FloatingInterestRateSwapLeg) bean).getConvention();
        case -697920873:  // schedule
          return ((FloatingInterestRateSwapLeg) bean).getSchedule();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -1790094686:  // customRates
          ((FloatingInterestRateSwapLeg) bean).setCustomRates((Rate) newValue);
          return;
        case 154998811:  // rateAveragingMethod
          ((FloatingInterestRateSwapLeg) bean).setRateAveragingMethod((RateAveragingMethod) newValue);
          return;
        case 551552978:  // capRate
          ((FloatingInterestRateSwapLeg) bean).setCapRate((Double) newValue);
          return;
        case -1712455924:  // floorRate
          ((FloatingInterestRateSwapLeg) bean).setFloorRate((Double) newValue);
          return;
        case -91774989:  // gearing
          ((FloatingInterestRateSwapLeg) bean).setGearing((Double) newValue);
          return;
        case 1343931690:  // spreadSchedule
          ((FloatingInterestRateSwapLeg) bean).setSpreadSchedule((Rate) newValue);
          return;
        case 2039569265:  // convention
          ((FloatingInterestRateSwapLeg) bean).setConvention((FloatingInterestRateSwapLegConvention) newValue);
          return;
        case -697920873:  // schedule
          ((FloatingInterestRateSwapLeg) bean).setSchedule((FloatingInterestRateSwapLegSchedule) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

    @Override
    protected void validate(Bean bean) {
      JodaBeanUtils.notNull(((FloatingInterestRateSwapLeg) bean)._gearing, "gearing");
      JodaBeanUtils.notNull(((FloatingInterestRateSwapLeg) bean)._convention, "convention");
      super.validate(bean);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
