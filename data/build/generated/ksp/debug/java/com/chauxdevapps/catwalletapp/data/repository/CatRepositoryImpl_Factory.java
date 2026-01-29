package com.chauxdevapps.catwalletapp.data.repository;

import com.chauxdevapps.catwalletapp.data.source.CatRemoteDataSource;
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
public final class CatRepositoryImpl_Factory implements Factory<CatRepositoryImpl> {
  private final Provider<CatRemoteDataSource> remoteDataSourceProvider;

  public CatRepositoryImpl_Factory(Provider<CatRemoteDataSource> remoteDataSourceProvider) {
    this.remoteDataSourceProvider = remoteDataSourceProvider;
  }

  @Override
  public CatRepositoryImpl get() {
    return newInstance(remoteDataSourceProvider.get());
  }

  public static CatRepositoryImpl_Factory create(
      Provider<CatRemoteDataSource> remoteDataSourceProvider) {
    return new CatRepositoryImpl_Factory(remoteDataSourceProvider);
  }

  public static CatRepositoryImpl newInstance(CatRemoteDataSource remoteDataSource) {
    return new CatRepositoryImpl(remoteDataSource);
  }
}
