package eg.kabooo.tvshows.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import eg.kabooo.tvshows.R;
import eg.kabooo.tvshows.databinding.EpisodeListItemBinding;
import eg.kabooo.tvshows.pojo.Episode;

public class EpisodesAdapter extends RecyclerView.Adapter<EpisodesAdapter.EpisodeViewHolder> {

    private List<Episode> episodeList;
    private LayoutInflater inflater;

    public EpisodesAdapter() {
    }

    @NonNull
    @Override
    public EpisodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }

        EpisodeListItemBinding b = DataBindingUtil.inflate(inflater, R.layout.episode_list_item, parent, false);
        return new EpisodeViewHolder(b);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeViewHolder holder, int position) {
        holder.bindData(episodeList.get(position));
    }

    @Override
    public int getItemCount() {
        return (episodeList == null) ? 0 : episodeList.size();
    }

    public static class EpisodeViewHolder extends RecyclerView.ViewHolder {


        private EpisodeListItemBinding b;

        public EpisodeViewHolder(EpisodeListItemBinding b) {
            super(b.getRoot());
            this.b = b;
        }

        public void bindData(Episode episode) {
            b.setEpisodeDetails(episode);
            b.executePendingBindings();
        }
    }

    public void setList(List<Episode> episodeList) {
        this.episodeList = episodeList;
        notifyDataSetChanged();
    }

}
