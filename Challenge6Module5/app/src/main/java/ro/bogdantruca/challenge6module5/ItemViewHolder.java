package ro.bogdantruca.challenge6module5;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ItemViewHolder extends RecyclerView.ViewHolder {
    private TextView mTextViewFirst;
    private TextView mTextViewSecond;
    private LinearLayout mLinearLayoutBackground;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);

        mTextViewFirst = itemView.findViewById(R.id.textView1);
        mTextViewSecond = itemView.findViewById(R.id.textView2);
        mLinearLayoutBackground = itemView.findViewById(R.id.linear_layout_background);
    }

    public TextView getTextViewFirst() {
        return mTextViewFirst;
    }

    public void setTextViewFirst(TextView textViewFirst) {
        mTextViewFirst = textViewFirst;
    }

    public TextView getTextViewSecond() {
        return mTextViewSecond;
    }

    public void setTextViewSecond(TextView textViewSecond) {
        mTextViewSecond = textViewSecond;
    }
}
