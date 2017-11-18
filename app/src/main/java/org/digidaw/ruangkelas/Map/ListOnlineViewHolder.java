package org.digidaw.ruangkelas.Map;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.digidaw.ruangkelas.ItemClickListener;
import org.digidaw.ruangkelas.R;
import org.w3c.dom.Text;

/**
 * Created by STURMBANNFUEHRER on 16/11/2017.
 */

public class ListOnlineViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtUsername;
    ItemClickListener itemClickListener;

    public ListOnlineViewHolder(View itemView){
        super(itemView);
        txtUsername = (TextView) itemView.findViewById(R.id.txtUsername);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition());
    }
}
