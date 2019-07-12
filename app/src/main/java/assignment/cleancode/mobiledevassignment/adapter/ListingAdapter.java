package assignment.cleancode.mobiledevassignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import assignment.cleancode.mobiledevassignment.BuildConfig;
import assignment.cleancode.mobiledevassignment.R;
import assignment.cleancode.mobiledevassignment.databinding.RowListingsBinding;
import assignment.cleancode.mobiledevassignment.managers.AppManager;
import assignment.cleancode.mobiledevassignment.models.MovieListing;


public class ListingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private MutableLiveData<List<MovieListing>> items;
    private Context context;
    private RequestOptions imageOptions;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;
    private FooterListingHolder footerHolder;
    private OnClickListener onClickListener;
    private AppManager appManager;

    public ListingAdapter(Context context, AppManager appManager, MutableLiveData<List<MovieListing>> items, OnClickListener onClickListener) {
        this.context = context;
        this.items = items;
        imageOptions = new RequestOptions()
                .placeholder(R.drawable.img_loading_pics)
                .error(R.color.black)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);
        this.onClickListener = onClickListener;
        this.appManager = appManager;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.progressbar_small, parent, false);
            return new FooterListingHolder(v);
        } else if (viewType == TYPE_ITEM) {
            RowListingsBinding binding = DataBindingUtil.inflate(
                    LayoutInflater.from(context),
                    R.layout.row_listings,
                    parent, false);

            return new ItemListingHolder(binding.getRoot());
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof FooterListingHolder) {

            footerHolder = (FooterListingHolder) holder;

        } else if (holder instanceof ItemListingHolder) {

            final ItemListingHolder listingHolder = (ItemListingHolder) holder;

            if (items.getValue() != null) {

                final MovieListing MovieListing = items.getValue().get(position);
                listingHolder.binding.setMovie(MovieListing);
                listingHolder.binding.setAppManager(appManager);
                listingHolder.binding.executePendingBindings();

                listingHolder.binding.itemCardListing.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onClickListener.onItemClick(listingHolder.getAdapterPosition(), MovieListing, listingHolder.binding);
                    }
                });

                String imgUrl = BuildConfig.IMG_BASE_URL + MovieListing.getPosterPath();
                Glide.with(context)
                        .load(imgUrl)
                        .apply(imageOptions)
                        .into(listingHolder.binding.thumbIv);
            }
        }
    }

    public void setData(List<MovieListing> dataItems) {
        if (dataItems == null)
            return;
        this.items.setValue(dataItems);
        notifyDataSetChanged();
    }

    public List<MovieListing> getData() {
        return items.getValue();
    }


    @Override
    public int getItemCount() {
        return items.getValue() != null ? items.getValue().size() : 0;
    }


    @Override
    public int getItemViewType(int position) {
        if (isPositionFooter(position)) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }


    public void notifyItemChange(int position) {
        this.notifyItemChanged(position);
    }

    public void setFooterVisibility(int visibility) {

        if (footerHolder != null && footerHolder.proressBar != null)
            footerHolder.proressBar.setVisibility(visibility);
    }


    private boolean isPositionFooter(int position) {
        return position == items.getValue().size() - 1;
    }


    class FooterListingHolder extends RecyclerView.ViewHolder {
        ProgressBar proressBar;

        public FooterListingHolder(View itemView) {
            super(itemView);
            this.proressBar = itemView.findViewById(R.id.progress_small);
        }
    }

    public class ItemListingHolder extends RecyclerView.ViewHolder {
        RowListingsBinding binding;

        ItemListingHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }


    }


    public interface OnClickListener {

        void onItemClick(int position, MovieListing MovieListing, RowListingsBinding binding);


    }
}
