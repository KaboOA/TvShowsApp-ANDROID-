package eg.kabooo.tvshows.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.text.HtmlCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.widget.ViewPager2;

import java.util.Locale;

import eg.kabooo.tvshows.R;
import eg.kabooo.tvshows.adapters.ImageSliderAdapter;
import eg.kabooo.tvshows.databinding.ActivityTvShowDetailsBinding;
import eg.kabooo.tvshows.pojo.TvShow;
import eg.kabooo.tvshows.responses.TvShowDetailsResponse;
import eg.kabooo.tvshows.utilities.TempDataHolder;
import eg.kabooo.tvshows.veiwmodels.MostPopularTvShowDetailsViewModel;
import eg.kabooo.tvshows.veiwmodels.WatchListViewModel;

public class TvShowDetailsActivity extends AppCompatActivity {

    private ActivityTvShowDetailsBinding b;
    private MostPopularTvShowDetailsViewModel showDetailsViewModel;
    private WatchListViewModel watchListViewModel;
    private TvShow tvShow;
    private boolean isInWatchList = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityTvShowDetailsBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        doInitialization();
    }

    private void check() {
        watchListViewModel.getTvShowFromWatchList(String.valueOf(tvShow.getId()))
                .observe(this, o -> {
                    isInWatchList = true;
                    b.fabImage.setImageResource(R.drawable.ic_baseline_check_24);
                });
    }

    private void doInitialization() {
        showDetailsViewModel = ViewModelProviders.of(TvShowDetailsActivity.this).get(MostPopularTvShowDetailsViewModel.class);
        watchListViewModel = ViewModelProviders.of(TvShowDetailsActivity.this).get(WatchListViewModel.class);
        tvShow = (TvShow) getIntent().getSerializableExtra("tvShow");

        b.imageBack.setOnClickListener(v -> onBackPressed());
        b.tvName.setSelected(true);

        check();
        getTvShowDetails();
    }

    private void getTvShowDetails() {
        b.setIsLoading(true);
        showDetailsViewModel.getMostPopularTvShows(String.valueOf(tvShow.getId())).observe(TvShowDetailsActivity.this, o -> {
            b.setIsLoading(false);
            loadImage(o.getTvShowsDetails().getPictures());
            b.setImageURL(o.getTvShowsDetails().getImagePath());
            b.imageTvShow.setVisibility(View.VISIBLE);

            setText(o);
        });
    }

    private void loadImage(String[] pictures) {
        b.viewPager.setOffscreenPageLimit(1);
        b.viewPager.setAdapter(new ImageSliderAdapter(pictures));
        b.viewPager.setVisibility(View.VISIBLE);
        b.viewFaddingEdge.setVisibility(View.VISIBLE);
        setupSliderIndicators(pictures.length);
        b.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentSlideIndicator(position);
            }
        });
    }

    private void setupSliderIndicators(int count) {
        ImageView[] indicators = new ImageView[count];

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        layoutParams.setMargins(8, 0, 8, 0);
        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.background_slider_indicator_inactive));
            indicators[i].setLayoutParams(layoutParams);
            b.layoutSlider.addView(indicators[i]);
        }
        b.layoutSlider.setVisibility(View.VISIBLE);
        setCurrentSlideIndicator(0);
    }

    private void setCurrentSlideIndicator(int position) {
        int childCount = b.layoutSlider.getChildCount();

        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) b.layoutSlider.getChildAt(i);
            if (i == position) {
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.background_slider_indicator_inactive));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.background_slider_indecator_active));
            }
        }
    }

    private void setText(TvShowDetailsResponse o) {
        b.setTvShow(tvShow);
        b.setTvRating(String.format(Locale.getDefault(), "%.2f", Double.parseDouble(o.getTvShowsDetails().getRating())));
        b.setTvDescription(String.valueOf(HtmlCompat.fromHtml(o.getTvShowsDetails().getDescription(), HtmlCompat.FROM_HTML_MODE_LEGACY)));
        b.setTvRunTime(o.getTvShowsDetails().getRuntime() + " MIN");

        if (o.getTvShowsDetails().getGenres() != null) {
            b.setTvGener(o.getTvShowsDetails().getGenres()[0]);
        } else {
            b.setTvGener("N/A");
        }

        b.tvReadMore.setOnClickListener(v -> {
            if (b.tvReadMore.getText().toString().equals("Read More...")) {
                b.tvDesc.setMaxLines(Integer.MAX_VALUE);
                b.tvDesc.setEllipsize(null);
                b.tvReadMore.setText(R.string.read_less);
            } else {
                b.tvDesc.setMaxLines(4);
                b.tvDesc.setEllipsize(TextUtils.TruncateAt.END);
                b.tvReadMore.setText(R.string.read_more);
            }
        });
        b.toWebsiteBtn.setOnClickListener(v -> websiteBtnIntent(o));
        b.eposidesBtn.setOnClickListener(v -> episodesBtnIntent());
        b.fabImage.setOnClickListener(v -> {
            if (isInWatchList) {
                watchListViewModel.delete(tvShow, b, this);
                isInWatchList = false;
            } else {
                watchListViewModel.addToWatchList(tvShow, b, TvShowDetailsActivity.this);
            }
            TempDataHolder.isUpdatedWatchList = true;
        });

        b.setIsVisible(true);
    }

    private void websiteBtnIntent(TvShowDetailsResponse o) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(o.getTvShowsDetails().getUrl()));
        startActivity(intent);
    }

    private void episodesBtnIntent() {
        Intent intent = new Intent(TvShowDetailsActivity.this, EpisodesActivity.class);
        intent.putExtra("tvShow", tvShow);
        startActivity(intent);
    }

}