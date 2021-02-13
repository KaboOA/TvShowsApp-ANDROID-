package eg.kabooo.tvshows.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import eg.kabooo.tvshows.adapters.TvShowsAdapter;
import eg.kabooo.tvshows.databinding.ActivityMainBinding;
import eg.kabooo.tvshows.listeners.RemoveInterface;
import eg.kabooo.tvshows.listeners.TvShowListener;
import eg.kabooo.tvshows.pojo.TvShow;
import eg.kabooo.tvshows.veiwmodels.MostPopularTvShowViewModel;

public class MainActivity extends AppCompatActivity implements TvShowListener {

    private MostPopularTvShowViewModel viewModel;
    private TvShowsAdapter adapter;
    private ActivityMainBinding b;
    private List<TvShow> tvShowList;
    private int currentPage = 1;
    private int totalPages = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        doInitialization();
    }

    private void doInitialization() {
        viewModel = ViewModelProviders.of(this).get(MostPopularTvShowViewModel.class);
        adapter = new TvShowsAdapter(this);
        tvShowList = new ArrayList<>();

        b.homeRecyclerView.setHasFixedSize(true);
        adapter.setList(tvShowList);
        b.homeRecyclerView.setAdapter(adapter);
        b.imageSearch.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SearchActivity.class)));
        b.imageWatchList.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, WatchListActivity.class)));

        onScroll();
        getData();
    }


    private void getData() {
        toggleLoading();
        viewModel.getMostPopularTvShows(currentPage).observe(this, o -> {
            toggleLoading();
            totalPages = o.getPages();
            int oldCount = tvShowList.size();
            tvShowList.addAll(o.getTvShows());
            adapter.notifyItemRangeInserted(oldCount, tvShowList.size());
        });
    }

    private void toggleLoading() {
        if (currentPage == 1) {
            b.setIsLoading(b.getIsLoading() == null || !b.getIsLoading());
        } else {
            b.setIsLoadingMore(b.getIsLoadingMore() == null || !b.getIsLoadingMore());
        }
    }

    @Override
    public void onTvShowClicked(TvShow tvShow) {
        Intent intent = new Intent(MainActivity.this, TvShowDetailsActivity.class);
        intent.putExtra("tvShow", tvShow);
        startActivity(intent);
    }

    private void onScroll() {
        b.homeRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!b.homeRecyclerView.canScrollVertically(1)) {
                    if (currentPage <= totalPages) {
                        currentPage += 1;
                        getData();
                    }
                }
            }
        });

    }

}