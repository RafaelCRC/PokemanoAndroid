package com.example.testapi.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testapi.Drawer;
import com.example.testapi.R;
import com.example.testapi.pokeapi.JsonPlaceHolderApi;
import com.example.testapi.pokeapi.MoveInner;
import com.example.testapi.pokeapi.MovesGet;
import com.example.testapi.pokeapi.Post;
import com.example.testapi.pokeapi.Species;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class StatsFragment extends Fragment {

    private int idstats;
    private int movecount = 0;
    public int movemax = 0;


    public StatsFragment(int idstats) {
        this.idstats = idstats;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stats, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //int fragid = ((Drawer) getActivity()).globalid;

        
        addDataSkills(""+idstats);
        addDataMoves(""+idstats);

        Button up = getView().findViewById(R.id.StatsUp);
        Button bot = getView().findViewById(R.id.StatsBot);

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movecount++;
                if (movecount >= movemax){
                    movecount = movemax-1;
                }else{
                    addDataMoves(""+idstats);
                }

            }
        });

        bot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movecount--;
                if (movecount < 0){
                    movecount = 0;
                }else{
                    addDataMoves(""+idstats);
                }

            }
        });


    }

    public void addDataSkills(String poke) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi apiService = retrofit.create(JsonPlaceHolderApi.class);
        Call<Post> call = apiService.getPokemon(poke);
        Call<Species> callsp = apiService.getSpecies(poke);
        //MAIN
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()) {


                    Post post = response.body();


                    String hpstr = post.getStats().get(0).getBase_stat();
                    String atkstr = post.getStats().get(1).getBase_stat();
                    String dfcstr = post.getStats().get(2).getBase_stat();
                    String spcatkstr = post.getStats().get(3).getBase_stat();
                    String spcdefstr = post.getStats().get(4).getBase_stat();
                    String spdstr = post.getStats().get(5).getBase_stat();

                    TextView statviewhp = getView().findViewById(R.id.hptext);
                    TextView statviewatk = getView().findViewById(R.id.atktext);
                    TextView statviewdfc = getView().findViewById(R.id.dfctext);
                    TextView statviewspcatk = getView().findViewById(R.id.spcatktext);
                    TextView statviewspcdef = getView().findViewById(R.id.spcdfctext);
                    TextView statviewspd = getView().findViewById(R.id.spdtext);

                    if (hpstr.length() == 2){
                        statviewhp.setText("Hp..............."+hpstr);
                    }else{
                        statviewhp.setText("Hp.............."+hpstr);
                    }

                    if (atkstr.length() == 2){
                        statviewatk.setText("Attack..........."+atkstr);
                    }else{
                        statviewatk.setText("Attack.........."+atkstr);
                    }

                    if (dfcstr.length() == 2){
                        statviewdfc.setText("Defense.........."+dfcstr);
                    }else{
                        statviewdfc.setText("Defense........."+dfcstr);
                    }

                    if (spcatkstr.length() == 2){
                        statviewspcatk.setText("Special-Attack..."+spcatkstr);
                    }else{
                        statviewspcatk.setText("Special-Attack.."+spcatkstr);
                    }

                    if (spcdefstr.length() == 2){
                        statviewspcdef.setText("Special-Defense.."+spcdefstr);
                    }else{
                        statviewspcdef.setText("Special-Defense."+spcdefstr);
                    }

                    if (spdstr.length() == 2){
                        statviewspd.setText("Special-Defense.."+spdstr);
                    }else{
                        statviewspd.setText("Special-Defense."+spdstr);
                    }


                    ProgressBar pbhp = getView().findViewById(R.id.hpbar);
                    ProgressBar pbatk = getView().findViewById(R.id.atkbar);
                    ProgressBar pbdfc = getView().findViewById(R.id.dfcbar);
                    ProgressBar pbspcatk = getView().findViewById(R.id.spcatkbar);
                    ProgressBar pbspcdfc = getView().findViewById(R.id.spcdfcbar);
                    ProgressBar pbspd = getView().findViewById(R.id.spdbar);

                    pbhp.getProgressDrawable().setColorFilter(Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
                    pbatk.getProgressDrawable().setColorFilter(Color.rgb(224, 78, 16), android.graphics.PorterDuff.Mode.SRC_IN);
                    pbdfc.getProgressDrawable().setColorFilter(Color.YELLOW, android.graphics.PorterDuff.Mode.SRC_IN);
                    pbspcatk.getProgressDrawable().setColorFilter(Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);
                    pbspcdfc.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
                    pbspd.getProgressDrawable().setColorFilter(Color.MAGENTA, android.graphics.PorterDuff.Mode.SRC_IN);

                    pbhp.setProgress(Integer.parseInt(hpstr));
                    pbatk.setProgress(Integer.parseInt(atkstr));
                    pbdfc.setProgress(Integer.parseInt(dfcstr));
                    pbspcatk.setProgress(Integer.parseInt(spcatkstr));
                    pbspcdfc.setProgress(Integer.parseInt(spcdefstr));
                    pbspd.setProgress(Integer.parseInt(spdstr));



                }

            }


            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
        //SPECIES
        callsp.enqueue(new Callback<Species>() {
            @Override
            public void onResponse(Call<Species> call, Response<Species> response) {
                if (response.isSuccessful()) {

                    Species spc = response.body();
                    TextView flavor = getView().findViewById(R.id.flavorText);
                    int langcount = 0;
                    String flavortest = spc.getFlavor_text_entries().get(langcount).getLanguage().getUrl();
                    while (!flavortest.equals("https://pokeapi.co/api/v2/language/9/")){
                        langcount++;
                        flavortest = spc.getFlavor_text_entries().get(langcount).getLanguage().getUrl();
                    }
                    String flvrStr = spc.getFlavor_text_entries().get(langcount).getFlavor_text();
                    flavor.setText(flvrStr);
                }
            }

            @Override
            public void onFailure(Call<Species> call, Throwable t) {

            }
        });
    }

    public void addDataMoves(String poke) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi apiService = retrofit.create(JsonPlaceHolderApi.class);
        Call<MovesGet> call = apiService.getMovesGet(poke);
        //MAIN MOVES
        call.enqueue(new Callback<MovesGet>() {
            @Override
            public void onResponse(Call<MovesGet> call, Response<MovesGet> response) {
                if (response.isSuccessful()) {

                    MovesGet mov = response.body();

                    TextView movesTotal = getView().findViewById(R.id.MovesTotal);
                    TextView moveName = getView().findViewById(R.id.moveName);
                    TextView learnAt = getView().findViewById(R.id.moveLvlLearn);

                    int total = mov.getMoves().size();
                    movemax = total;
                    if (movemax > 0){
                        movesTotal.setText("Moves: " + (movecount+1) + "/" + total);
                        moveName.setText(mov.getMoves().get(movecount).getMove().getName());
                        learnAt.setText("Learn: LVL "+mov.getMoves().get(movecount).getVersion_group_details().get(0).getLevel_learned_at());
                        String urlnumber = mov.getMoves().get(movecount).getMove().getUrl(); //30
                        urlnumber = urlnumber.substring(31, urlnumber.length()-1);
                        innermove(urlnumber);

                    }
                    TextView type1 = getView().findViewById(R.id.type1);
                    TextView type2 = getView().findViewById(R.id.type2);

                    type1.setText("Type1: " + mov.getTypes().get(0).getType().getName());
                    int typesize = mov.getTypes().size();
                    if (typesize == 2){
                        type2.setText("Type2: " + mov.getTypes().get(1).getType().getName());
                    }


                }
            }

            @Override
            public void onFailure(Call<MovesGet> call, Throwable t) {

            }
        });



    }

    private void innermove(String n){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi apiService = retrofit.create(JsonPlaceHolderApi.class);
        Call<MoveInner> moveInnerCall = apiService.getMoveInner(n);
        moveInnerCall.enqueue(new Callback<MoveInner>() {
            @Override
            public void onResponse(Call<MoveInner> call, Response<MoveInner> response) {
                if (response.isSuccessful()) {

                    MoveInner inner = response.body();

                    TextView accuracy = getView().findViewById(R.id.accText);
                    TextView power = getView().findViewById(R.id.powerText);
                    TextView PP = getView().findViewById(R.id.PPText);
                    TextView movetype = getView().findViewById(R.id.moveType);;

                    String accu = inner.getAccuracy();
                    String pow = inner.getPower();
                    String pp = inner.getPp();

                    movetype.setText(inner.getType().getName());

                    if (accu != null){
                        if (accu.length() == 2){
                            accuracy.setText("Accuracy......" + accu);
                        }
                        else{
                            accuracy.setText("Accuracy....." + accu);
                        }
                    }
                    if (pow != null){
                        if (pow.length() == 2){
                            power.setText("Power........." + pow);
                        }
                        else{
                            power.setText("Power........" + pow);
                        }
                    }
                    if (pp != null){
                        if (pp.length() == 2){
                            PP.setText("PP............" + pp);
                        }
                        else{
                            PP.setText("PP..........." + pp);
                        }
                    }

                }

            }

            @Override
            public void onFailure(Call<MoveInner> call, Throwable t) {

            }
        });

    }


}

