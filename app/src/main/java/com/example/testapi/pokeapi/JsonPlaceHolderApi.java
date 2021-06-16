package com.example.testapi.pokeapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {

    @GET("api/v2/pokemon/{id}")
    Call<Post> getPokemon(@Path("id") String id);

    @GET("api/v2/pokemon-species/{id}")
    Call<Species> getSpecies(@Path("id") String id);

    @GET("api/v2/pokemon/{id}")
    Call<MovesGet> getMovesGet(@Path("id") String id);

    @GET("api/v2/move/{id}")
    Call<MoveInner> getMoveInner(@Path("id") String id);

}
