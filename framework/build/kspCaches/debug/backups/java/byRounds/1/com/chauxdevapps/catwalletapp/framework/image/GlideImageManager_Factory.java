package com.chauxdevapps.catwalletapp.framework.image;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class GlideImageManager_Factory implements Factory<GlideImageManager> {
  @Override
  public GlideImageManager get() {
    return newInstance();
  }

  public static GlideImageManager_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static GlideImageManager newInstance() {
    return new GlideImageManager();
  }

  private static final class InstanceHolder {
    private static final GlideImageManager_Factory INSTANCE = new GlideImageManager_Factory();
  }
}
