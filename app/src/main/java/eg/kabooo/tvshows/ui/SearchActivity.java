package eg.kabooo.tvshows.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import eg.kabooo.tvshows.adapters.TvShowsAdapter;
import eg.kabooo.tvshows.databinding.ActivitySearchBinding;
import eg.kabooo.tvshows.listeners.TvShowListener;
import eg.kabooo.tvshows.pojo.TvShow;
import eg.kabooo.tvshows.veiwmodels.SearchViewModel;

public class SearchActivity extends AppCompatActivity implements TvShowListener {

    private ActivitySearchBinding b;
    private SearchViewModel viewModel;
    private List<TvShow> tvShowList;
    private TvShowsAdapter adapter;
    private int totalPages = 1;
    private int currentPage = 1;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        doInitialization();
    }

    private void doInitialization() {
        viewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        tvShowList = new ArrayList<>();
        adapter = new TvShowsAdapter(this);

        b.searchRecyclerView.setHasFixedSize(true);
        adapter.setList(tvShowList);
        b.searchRecyclerView.setAdapter(adapter);
        b.backImage2.setOnClickListener(v -> onBackPressed());
        b.edtTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (timer != null) {
                    timer.cancel();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().trim().isEmpty()) {
                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            new Handler(Looper.getMainLooper()).post(() -> {
                                currentPage = 1;
                                totalPages = 1;
                                getData(s.toString());
                            });
                        }
                    }, 800);
                } else {
                    tvShowList.clear();
                    adapter.notifyDataSetChanged();
                }
            }
        });

        onScroll();
        b.edtTxt.requestFocus();
    }

    private void getData(String search) {
        toggleLoading();
        viewModel.getSearchedResult(search, currentPage).observe(this, o -> {
            toggleLoading();

            totalPages = o.getPages();
            int oldCount = tvShowList.size();

            tvShowList.addAll(o.getTvShows());
            adapter.notifyItemRangeInserted(oldCount, tvShowList.size());
        });
    }

    @Override
    public void onTvShowClicked(TvShow tvShow) {
        Intent intent = new Intent(SearchActivity.this, TvShowDetailsActivity.class);
        intent.putExtra("tvShow", tvShow);
        startActivity(intent);
    }

    private void toggleLoading() {
        if (currentPage == 1) {
            b.setIsLoading(b.getIsLoading() == null || !b.getIsLoading());
        } else {
            b.setIsLoadingMore(b.getIsLoadingMore() == null || !b.getIsLoadingMore());
        }
    }

    private void onScroll() {
        b.searchRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollVertically(1)) {
                    if (!b.edtTxt.getText().toString().isEmpty()) {
                        if (currentPage < totalPages) {
                            currentPage += 1;
                            getData(b.edtTxt.getText().toString());
                        }
                    }
                }
            }
        });
    }

}