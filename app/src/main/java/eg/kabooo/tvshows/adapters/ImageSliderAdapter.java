package eg.kabooo.tvshows.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import eg.kabooo.tvshows.R;
import eg.kabooo.tvshows.databinding.ItemContainerSliderBinding;
import eg.kabooo.tvshows.databinding.TvShowListItemBinding;

public class ImageSliderAdapter extends RecyclerView.Adapter<ImageSliderAdapter.ImageSliderViewHolder> {

    private String[] photos;
    private LayoutInflater inflater;

    public ImageSliderAdapter(String[] photos) {
        this.photos = photos;
    }

    @NonNull
    @Override
    public ImageSliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        ItemContainerSliderBinding b = DataBindingUtil.inflate(inflater, R.layout.item_container_slider, parent, false);
        return new ImageSliderViewHolder(b);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageSliderViewHolder holder, int position) {
        holder.bindImage(photos[position]);
    }

    @Override
    public int getItemCount() {
        return photos.length;
    }

    static class ImageSliderViewHolder extends RecyclerView.ViewHolder {
        private ItemContainerSliderBinding b;

        public ImageSliderViewHolder(ItemContainerSliderBinding b) {
            super(b.getRoot());
            this.b = b;
        }

        public void bindImage(String imageURL) {
            b.setImageURL(imageURL);
        }
    }


}
