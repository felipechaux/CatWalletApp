package com.chauxdevapps.catwalletapp.framework.request;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class RetrofitCatRemoteDataSource_Factory implements Factory<RetrofitCatRemoteDataSource> {
  private final Provider<CatApi> catApiProvider;

  public RetrofitCatRemoteDataSource_Factory(Provider<CatApi> catApiProvider) {
    this.catApiProvider = catApiProvider;
  }

  @Override
  public RetrofitCatRemoteDataSource get() {
    return newInstance(catApiProvider.get());
  }

  public static RetrofitCatRemoteDataSource_Factory create(Provider<CatApi> catApiProvider) {
    return new RetrofitCatRemoteDataSource_Factory(catApiProvider);
  }

  public static RetrofitCatRemoteDataSource newInstance(CatApi catApi) {
    return new RetrofitCatRemoteDataSource(catApi);
  }
}
