package eg.kabooo.tvshows.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import eg.kabooo.tvshows.R;
import eg.kabooo.tvshows.databinding.WatchlistListItemBinding;
import eg.kabooo.tvshows.listeners.RemoveInterface;
import eg.kabooo.tvshows.listeners.TvShowListener;
import eg.kabooo.tvshows.pojo.TvShow;
import eg.kabooo.tvshows.veiwmodels.WatchListViewModel;

public class WatchListAdapter extends RecyclerView.Adapter<WatchListAdapter.MyViewHolder> {

    private List<TvShow> episodeList;
    private LayoutInflater inflater;
    public RemoveInterface tvShowListener;
    TvShowListener listener;

    public WatchListAdapter(RemoveInterface listener, TvShowListener listener2) {
        this.tvShowListener = listener;
        this.listener = listener2;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        WatchlistListItemBinding b = DataBindingUtil.inflate(inflater, R.layout.watchlist_list_item, parent, false);
        return new MyViewHolder(b);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindData(episodeList.get(position));
    }

    @Override
    public int getItemCount() {
        return (episodeList == null) ? 0 : episodeList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private WatchlistListItemBinding b;

        public MyViewHolder(WatchlistListItemBinding b) {
            super(b.getRoot());
            this.b = b;
        }

        public void bindData(TvShow tvShow) {
            b.setTvShow(tvShow);
            b.executePendingBindings();

            b.deleteImage.setOnClickListener(v -> tvShowListener.removeFromWatchList(tvShow, getAdapterPosition()));
            b.getRoot().setOnClickListener(v -> listener.onTvShowClicked(tvShow));
        }

    }

    public void setList(List<TvShow> episodeList) {
        this.episodeList = episodeList;
        notifyDataSetChanged();
    }


}
