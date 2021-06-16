package com.example.testapi.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testapi.Drawer;
import com.example.testapi.R;
import com.example.testapi.pokeapi.JsonPlaceHolderApi;
import com.example.testapi.pokeapi.Post;
import com.example.testapi.pokeapi.Species;
import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ComparerFragment extends Fragment  {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comparer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull  View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addDataComp(""+1,1);
        addDataComp(""+4,2);

        SearchView busca1 = (SearchView)getView().findViewById(R.id.compsearchView1);
        SearchView busca = (SearchView)getView().findViewById(R.id.compsearchView);

        busca1.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query != ""){
                    query = query.substring(0, 1).toLowerCase() + query.substring(1);
                    addDataComp(query, 1);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        busca.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query != ""){
                    query = query.substring(0, 1).toLowerCase() + query.substring(1);
                    addDataComp(query, 2);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    public void addDataComp(String poke, int OoT) { //PARAMETRO EXTRA PARA DECIDIR QUAL DOS DOIS
        int checker = OoT;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi apiService = retrofit.create(JsonPlaceHolderApi.class);
        Call<Post> call = apiService.getPokemon(poke);

        //MAIN COMPARER
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()) {

                    Post post = response.body();

                    TextView pokename;
                    TextView pokeid;

                    ImageView sprite;

                    TextView textohp;
                    TextView textoatk;
                    TextView textodfc;
                    TextView textospcatk;
                    TextView textospcdfc;
                    TextView textospd;

                    ProgressBar barrahp;
                    ProgressBar barraatk;
                    ProgressBar barradfc;
                    ProgressBar barraspcatk;
                    ProgressBar barraspcdfc;
                    ProgressBar barraspd;

                    if (checker == 1){
                        pokename = getView().findViewById(R.id.comppokeName1);
                        pokeid = getView().findViewById(R.id.comppokeID1);

                        sprite = getView().findViewById(R.id.compiv_result1);

                        textohp = getView().findViewById(R.id.comphptext1);
                        textoatk = getView().findViewById(R.id.compatktext1);
                        textodfc = getView().findViewById(R.id.compdfctext1);
                        textospcatk = getView().findViewById(R.id.compspcatktext1);
                        textospcdfc = getView().findViewById(R.id.compspcdfctext1);
                        textospd = getView().findViewById(R.id.compspd1);

                        barrahp = getView().findViewById(R.id.comphpbar1);
                        barraatk = getView().findViewById(R.id.compatkbar1);
                        barradfc = getView().findViewById(R.id.compdfcbar1);
                        barraspcatk = getView().findViewById(R.id.compspcatkbar1);
                        barraspcdfc = getView().findViewById(R.id.compspcdfcbar1);
                        barraspd = getView().findViewById(R.id.compspdbar1);
                    }
                    else{
                        pokename = getView().findViewById(R.id.comppokeName);
                        pokeid = getView().findViewById(R.id.comppokeID);

                        sprite = getView().findViewById(R.id.compiv_result);

                        textohp = getView().findViewById(R.id.comphptext);
                        textoatk = getView().findViewById(R.id.compatktext);
                        textodfc = getView().findViewById(R.id.compdfctext);
                        textospcatk = getView().findViewById(R.id.compspcatktext);
                        textospcdfc = getView().findViewById(R.id.compspcdfctext);
                        textospd = getView().findViewById(R.id.compspd);

                        barrahp = getView().findViewById(R.id.comphpbar);
                        barraatk = getView().findViewById(R.id.compatkbar);
                        barradfc = getView().findViewById(R.id.compdfcbar);
                        barraspcatk = getView().findViewById(R.id.compspcatkbar);
                        barraspcdfc = getView().findViewById(R.id.compspcdfcbar);
                        barraspd = getView().findViewById(R.id.compspdbar);
                    }

                    String capname = post.getName();
                    capname = capname.substring(0, 1).toUpperCase() + capname.substring(1);
                    pokename.setText(capname);

                    pokeid.setText("#"+post.getId());

                    String spriteUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + post.getId() + ".png";
                    LoadImage loadImage = new LoadImage(sprite);
                    loadImage.execute(spriteUrl);

                    String hpstr = post.getStats().get(0).getBase_stat();
                    String atkstr = post.getStats().get(1).getBase_stat();
                    String dfcstr = post.getStats().get(2).getBase_stat();
                    String spcatkstr = post.getStats().get(3).getBase_stat();
                    String spcdefstr = post.getStats().get(4).getBase_stat();
                    String spdstr = post.getStats().get(5).getBase_stat();

                    if (hpstr.length() == 2){
                        textohp.setText("Hp..............."+hpstr);
                    }else{
                        textohp.setText("Hp.............."+hpstr);
                    }

                    if (atkstr.length() == 2){
                        textoatk.setText("Attack..........."+atkstr);
                    }else{
                        textoatk.setText("Attack.........."+atkstr);
                    }

                    if (dfcstr.length() == 2){
                        textodfc.setText("Defense.........."+dfcstr);
                    }else{
                        textodfc.setText("Defense........."+dfcstr);
                    }

                    if (spcatkstr.length() == 2){
                        textospcatk.setText("Special-Attack..."+spcatkstr);
                    }else{
                        textospcatk.setText("Special-Attack.."+spcatkstr);
                    }

                    if (spcdefstr.length() == 2){
                        textospcdfc.setText("Special-Defense.."+spcdefstr);
                    }else{
                        textospcdfc.setText("Special-Defense."+spcdefstr);
                    }

                    if (spdstr.length() == 2){
                        textospd.setText("Special-Defense.."+spdstr);
                    }else{
                        textospd.setText("Special-Defense."+spdstr);
                    }

                    barrahp.getProgressDrawable().setColorFilter(Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
                    barraatk.getProgressDrawable().setColorFilter(Color.rgb(224, 78, 16), android.graphics.PorterDuff.Mode.SRC_IN);
                    barradfc.getProgressDrawable().setColorFilter(Color.YELLOW, android.graphics.PorterDuff.Mode.SRC_IN);
                    barraspcatk.getProgressDrawable().setColorFilter(Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);
                    barraspcdfc.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
                    barraspd.getProgressDrawable().setColorFilter(Color.MAGENTA, android.graphics.PorterDuff.Mode.SRC_IN);

                    barrahp.setProgress(Integer.parseInt(hpstr));
                    barraatk.setProgress(Integer.parseInt(atkstr));
                    barradfc.setProgress(Integer.parseInt(dfcstr));
                    barraspcatk.setProgress(Integer.parseInt(spcatkstr));
                    barraspcdfc.setProgress(Integer.parseInt(spcdefstr));
                    barraspd.setProgress(Integer.parseInt(spdstr));

                }
                else{
                    Toast.makeText(getActivity(),"Este Pokemon não existe!",Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });

    }

    private class LoadImage extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;
        public LoadImage(ImageView ivResult){

            this.imageView = ivResult;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String urLink = strings[0];
            Bitmap bitmap = null;
            try {
                InputStream inputStream = new java.net.URL(urLink).openStream();

                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
        }
    }



}

