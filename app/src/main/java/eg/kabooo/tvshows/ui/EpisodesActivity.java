package eg.kabooo.tvshows.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.os.Bundle;

import eg.kabooo.tvshows.R;
import eg.kabooo.tvshows.adapters.EpisodesAdapter;
import eg.kabooo.tvshows.databinding.ActivityEpisodesBinding;
import eg.kabooo.tvshows.pojo.TvShow;
import eg.kabooo.tvshows.veiwmodels.MostPopularTvShowDetailsViewModel;

public class EpisodesActivity extends AppCompatActivity {

    private EpisodesAdapter adapter;
    private ActivityEpisodesBinding b;
    private MostPopularTvShowDetailsViewModel viewModel;
    private TvShow tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityEpisodesBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        doInitialization();
    }

    private void doInitialization() {
        adapter = new EpisodesAdapter();
        viewModel = ViewModelProviders.of(this).get(MostPopularTvShowDetailsViewModel.class);
        tvShow = (TvShow) getIntent().getSerializableExtra("tvShow");

        b.episodesRecyclerView.setAdapter(adapter);
        b.closeImg.setOnClickListener(v -> finish());
        getData();
    }

    private void getData() {
        viewModel.getMostPopularTvShows(String.valueOf(tvShow.getId()))
                .observe(this, o -> {
                    adapter.setList(o.getTvShowsDetails().getEpisodes());
                    b.titleTxt.setText(String.format("Episodes | %s", tvShow.getName()));
                });
    }

}