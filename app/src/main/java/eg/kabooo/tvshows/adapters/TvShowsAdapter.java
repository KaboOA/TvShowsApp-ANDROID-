package eg.kabooo.tvshows.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import eg.kabooo.tvshows.R;
import eg.kabooo.tvshows.databinding.TvShowListItemBinding;
import eg.kabooo.tvshows.listeners.TvShowListener;
import eg.kabooo.tvshows.pojo.TvShow;

public class TvShowsAdapter extends RecyclerView.Adapter<TvShowsAdapter.TvShowViewHolder> {

    private List<TvShow> tvShowList;
    private LayoutInflater inflater;
    private TvShowListener tvShowListener;

    public TvShowsAdapter(TvShowListener listener) {
        this.tvShowListener = listener;
    }

    @NonNull
    @Override
    public TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        TvShowListItemBinding b = DataBindingUtil.inflate(inflater, R.layout.tv_show_list_item, parent, false);
        return new TvShowViewHolder(b);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowViewHolder holder, int position) {
        holder.bindTvShow(tvShowList.get(position));
    }

    @Override
    public int getItemCount() {
        return (tvShowList == null) ? 0 : tvShowList.size();
    }

    public class TvShowViewHolder extends RecyclerView.ViewHolder {
        private TvShowListItemBinding tvShowListItemBinding;

        public TvShowViewHolder(TvShowListItemBinding tvShowListItemBinding) {
            super(tvShowListItemBinding.getRoot());
            this.tvShowListItemBinding = tvShowListItemBinding;

        }

        public void bindTvShow(TvShow tvShow) {
            tvShowListItemBinding.setTvShow(tvShow);
            tvShowListItemBinding.executePendingBindings();
            tvShowListItemBinding.getRoot().setOnClickListener(v -> tvShowListener.onTvShowClicked(tvShow));
        }
    }

    public void setList(List<TvShow> tvShowList) {
        this.tvShowList = tvShowList;
        notifyDataSetChanged();
    }

}
