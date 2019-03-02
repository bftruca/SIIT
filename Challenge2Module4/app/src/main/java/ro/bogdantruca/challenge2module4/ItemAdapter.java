package ro.bogdantruca.challenge2module4;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    private List<Item> mItems;
    private Context mContext;

    public ItemAdapter(List<Item> items, Context context) {
        mItems = items;
        mContext = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);

        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
        Item currentItem = mItems.get(i);

        itemViewHolder.getTextViewFirstName().setText(currentItem.getFirstName());
        itemViewHolder.getTextViewLastName().setText(currentItem.getLastName());

        if (i % 2 != 0)
            itemViewHolder.getLinearLayoutBackground().setBackgroundColor(Color.parseColor("#FAFAFA"));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
