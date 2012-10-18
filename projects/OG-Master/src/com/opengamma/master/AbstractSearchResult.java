/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master;

import java.util.Collection;
import java.util.Map;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.id.VersionCorrection;
import com.opengamma.util.PublicSPI;

/**
 * Result from searching for documents.
 * <p>
 * The returned documents will match the search criteria.
 * See {@link AbstractSearchRequest} for more details.
 * 
 * @param <D>  the type of the document
 */
@PublicSPI
@BeanDefinition
public abstract class AbstractSearchResult<D extends AbstractDocument> extends AbstractDocumentsResult<D> {

  /**
   * The version-correction that the data represents, not null.
   * This defaults to LATEST, but should be set to the actual version-correction of the results.
   */
  @PropertyDefinition(validate = "notNull")
  private VersionCorrection _versionCorrection = VersionCorrection.LATEST;

  /**
   * Creates an instance.
   */
  public AbstractSearchResult() {
  }

  /**
   * Creates an instance from a collection of documents.
   * 
   * @param documents  the collection of documents to add, not null
   */
  public AbstractSearchResult(Collection<D> documents) {
    super(documents);
  }

  //-------------------------------------------------------------------------
  /**
   * Gets the first document, or null if no documents.
   * @return the first document, null if none
   */
  public D getFirstDocument() {
    return getDocuments().size() > 0 ? getDocuments().get(0) : null;
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code AbstractSearchResult}.
   * @param <R>  the bean's generic type
   * @return the meta-bean, not null
   */
  @SuppressWarnings("unchecked")
  public static <R extends AbstractDocument> AbstractSearchResult.Meta<R> meta() {
    return AbstractSearchResult.Meta.INSTANCE;
  }
  static {
    JodaBeanUtils.registerMetaBean(AbstractSearchResult.Meta.INSTANCE);
  }

  @SuppressWarnings("unchecked")
  @Override
  public AbstractSearchResult.Meta<D> metaBean() {
    return AbstractSearchResult.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -2031293866:  // versionCorrection
        return getVersionCorrection();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -2031293866:  // versionCorrection
        setVersionCorrection((VersionCorrection) newValue);
        return;
    }
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      AbstractSearchResult<?> other = (AbstractSearchResult<?>) obj;
      return JodaBeanUtils.equal(getVersionCorrection(), other.getVersionCorrection()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(getVersionCorrection());
    return hash ^ super.hashCode();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the version-correction that the data represents, not null if correctly created.
   * @return the value of the property
   */
  public VersionCorrection getVersionCorrection() {
    return _versionCorrection;
  }

  /**
   * Sets the version-correction that the data represents, not null if correctly created.
   * @param versionCorrection  the new value of the property
   */
  public void setVersionCorrection(VersionCorrection versionCorrection) {
    this._versionCorrection = versionCorrection;
  }

  /**
   * Gets the the {@code versionCorrection} property.
   * @return the property, not null
   */
  public final Property<VersionCorrection> versionCorrection() {
    return metaBean().versionCorrection().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code AbstractSearchResult}.
   */
  public static class Meta<D extends AbstractDocument> extends AbstractDocumentsResult.Meta<D> {
    /**
     * The singleton instance of the meta-bean.
     */
    @SuppressWarnings("rawtypes")
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code versionCorrection} property.
     */
    private final MetaProperty<VersionCorrection> _versionCorrection = DirectMetaProperty.ofReadWrite(
        this, "versionCorrection", AbstractSearchResult.class, VersionCorrection.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
      this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "versionCorrection");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -2031293866:  // versionCorrection
          return _versionCorrection;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends AbstractSearchResult<D>> builder() {
      throw new UnsupportedOperationException("AbstractSearchResult is an abstract class");
    }

    @SuppressWarnings({"unchecked", "rawtypes" })
    @Override
    public Class<? extends AbstractSearchResult<D>> beanType() {
      return (Class) AbstractSearchResult.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code versionCorrection} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<VersionCorrection> versionCorrection() {
      return _versionCorrection;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
