package com.jbirdvegas.mgdorks;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class DorkViewHolder extends RecyclerView.ViewHolder {
    public DorkViewHolder(View view) {
        super(view);
        ghdbId = (TextView) view.findViewById(R.id.dork_row_ghdb_id);
        category = (TextView) view.findViewById(R.id.dork_row_category);
        queryString = (TextView) view.findViewById(R.id.dork_row_querystring);
        shortDescription = (TextView) view.findViewById(R.id.dork_row_short_description);
        textualDescription = (TextView) view.findViewById(R.id.dork_row_textual_description);
    }

    public TextView ghdbId;
    public TextView category;
    public TextView queryString;
    public TextView shortDescription;
    public TextView textualDescription;
}
