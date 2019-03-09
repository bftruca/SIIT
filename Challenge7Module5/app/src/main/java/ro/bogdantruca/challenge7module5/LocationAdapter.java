package ro.bogdantruca.challenge7module5;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import static ro.bogdantruca.challenge7module5.R.drawable.ic_launcher_background;

public class LocationAdapter extends RecyclerView.Adapter<LocationViewHolder> {
    private List<Location> mLocations;
    private Context mContext;

    public LocationAdapter(List<Location> locations, Context context) {
        mLocations = locations;
        mContext = context;
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item,
                viewGroup, false);

        return new LocationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder locationViewHolder, int i) {
        Location currentLocation = mLocations.get(i);

        locationViewHolder.getTextViewDate().setText(currentLocation.getStringDate());
        locationViewHolder.getTextViewLocation().setText(currentLocation.getStringLocation());

        Picasso.get().load(currentLocation.getStringImageUrl())
                .placeholder(ic_launcher_background)
                .error(ic_launcher_background)
                .into(locationViewHolder.getImageViewLocationPicture());
    }

    @Override
    public int getItemCount() {
        return mLocations.size();
    }
}
