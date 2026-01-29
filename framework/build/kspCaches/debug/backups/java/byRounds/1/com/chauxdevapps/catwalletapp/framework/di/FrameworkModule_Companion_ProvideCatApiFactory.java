package com.chauxdevapps.catwalletapp.framework.di;

import com.chauxdevapps.catwalletapp.framework.request.CatApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

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
public final class FrameworkModule_Companion_ProvideCatApiFactory implements Factory<CatApi> {
  private final Provider<Retrofit> retrofitProvider;

  public FrameworkModule_Companion_ProvideCatApiFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public CatApi get() {
    return provideCatApi(retrofitProvider.get());
  }

  public static FrameworkModule_Companion_ProvideCatApiFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new FrameworkModule_Companion_ProvideCatApiFactory(retrofitProvider);
  }

  public static CatApi provideCatApi(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(FrameworkModule.Companion.provideCatApi(retrofit));
  }
}
