package ro.bogdantruca.challenge6module5;

import android.content.Context;
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

        int number = currentItem.getIntNumberOfOrder();
        itemViewHolder.getTextViewFirst().setText(String.valueOf(number));
        itemViewHolder.getTextViewSecond().setText(currentItem.getStringToDo());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
