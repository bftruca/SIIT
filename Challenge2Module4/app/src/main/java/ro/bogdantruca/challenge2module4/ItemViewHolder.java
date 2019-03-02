package ro.bogdantruca.challenge2module4;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ItemViewHolder extends RecyclerView.ViewHolder {
    private TextView mTextViewFirstName;
    private TextView mTextViewLastName;
    private LinearLayout mLinearLayoutBackground;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);

        mTextViewFirstName = itemView.findViewById(R.id.text_view_first_name);
        mTextViewLastName = itemView.findViewById(R.id.text_view_last_name);
        mLinearLayoutBackground = itemView.findViewById(R.id.linear_layout_background);
    }

    public TextView getTextViewFirstName() {
        return mTextViewFirstName;
    }

    public void setTextViewFirstName(TextView textViewFirstName) {
        mTextViewFirstName = textViewFirstName;
    }

    public TextView getTextViewLastName() {
        return mTextViewLastName;
    }

    public void setTextViewLastName(TextView textViewLastName) {
        mTextViewLastName = textViewLastName;
    }

    public LinearLayout getLinearLayoutBackground() {
        return mLinearLayoutBackground;
    }

    public void setLinearLayoutBackground(LinearLayout linearLayoutBackground) {
        mLinearLayoutBackground = linearLayoutBackground;
    }
}
