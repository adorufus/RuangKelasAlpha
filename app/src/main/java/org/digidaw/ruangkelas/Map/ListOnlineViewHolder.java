package org.digidaw.ruangkelas.Map;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.digidaw.ruangkelas.R;
import org.w3c.dom.Text;

/**
 * Created by STURMBANNFUEHRER on 16/11/2017.
 */

public class ListOnlineViewHolder extends RecyclerView.ViewHolder {
    public TextView txtUsername;

    public ListOnlineViewHolder(View itemView){
        super(itemView);
        txtUsername = (TextView) itemView.findViewById(R.id.txtUsername);
    }
}
