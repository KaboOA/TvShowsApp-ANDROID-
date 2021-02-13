package eg.kabooo.tvshows.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import eg.kabooo.tvshows.R;
import eg.kabooo.tvshows.adapters.WatchListAdapter;
import eg.kabooo.tvshows.databinding.ActivityWatchListBinding;
import eg.kabooo.tvshows.listeners.RemoveInterface;
import eg.kabooo.tvshows.listeners.TvShowListener;
import eg.kabooo.tvshows.pojo.TvShow;
import eg.kabooo.tvshows.utilities.TempDataHolder;
import eg.kabooo.tvshows.veiwmodels.WatchListViewModel;

public class WatchListActivity extends AppCompatActivity implements RemoveInterface, TvShowListener {

    private ActivityWatchListBinding b;
    private WatchListViewModel viewModel;
    private WatchListAdapter adapter;
    private List<TvShow> tvShowList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityWatchListBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        doInitialization();
    }

    private void doInitialization() {
        viewModel = ViewModelProviders.of(this).get(WatchListViewModel.class);
        adapter = new WatchListAdapter(this, this);
        tvShowList = new ArrayList<>();

        b.backImage.setOnClickListener(v -> onBackPressed());
        b.watchListRecyclerView.setAdapter(adapter);

        getData();
    }

    private void getData() {
        b.setIsLoading(true);
        viewModel.getWatchList().observe(this, o -> {
            tvShowList.addAll(o);
            adapter.setList(o);
            b.setIsLoading(false);
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (TempDataHolder.isUpdatedWatchList) {
            getData();
            TempDataHolder.isUpdatedWatchList = false;
        }
    }

    @Override
    public void removeFromWatchList(TvShow tvShow, int position) {
        viewModel.deleteWatchList(tvShow, this, tvShowList, adapter, position);
    }

    @Override
    public void onTvShowClicked(TvShow tvShow) {
        Intent intent = new Intent(WatchListActivity.this, TvShowDetailsActivity.class);
        intent.putExtra("tvShow", tvShow);
        startActivity(intent);
    }
}