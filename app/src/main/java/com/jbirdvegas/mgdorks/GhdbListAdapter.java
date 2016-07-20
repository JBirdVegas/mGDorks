package com.jbirdvegas.mgdorks;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;
import java.util.List;

public class GhdbListAdapter extends RecyclerView.Adapter<DorkViewHolder> {
    private final List<Dork> mDorks;
    private final WeakReference<View.OnClickListener> mClickListener;

    public GhdbListAdapter(List<Dork> dorks, View.OnClickListener clickListener) {
        mDorks = dorks;
        mClickListener = new WeakReference<>(clickListener);
    }

    @Override
    public DorkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dork_row_view, null);
        return new DorkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DorkViewHolder viewHolder, int position) {
        Dork dork = mDorks.get(position);

        // set the text
        viewHolder.ghdbId.setText(viewHolder.ghdbId.getContext().getString(R.string.label_row_ghdb, dork.ghdbId));
        viewHolder.category.setText(viewHolder.ghdbId.getContext().getString(R.string.label_row_category, dork.category));
        viewHolder.queryString.setText(viewHolder.ghdbId.getContext().getString(R.string.label_row_query_string, dork.queryString));
        viewHolder.shortDescription.setText(dork.shortDescription);
        viewHolder.textualDescription.setText(dork.textualDescription);

        // some Google Dork devs are lazy and use the query string as the short description
        // if that happens let's just hide the short description
        if (dork.queryString.equals(dork.shortDescription)) {
            viewHolder.shortDescription.setVisibility(View.GONE);
        }

        View.OnClickListener clickListener = mClickListener.get();
        if (clickListener != null) {
            // set onClick
            viewHolder.ghdbId.setOnClickListener(clickListener);
            viewHolder.category.setOnClickListener(clickListener);
            viewHolder.queryString.setOnClickListener(clickListener);
            viewHolder.shortDescription.setOnClickListener(clickListener);
            viewHolder.textualDescription.setOnClickListener(clickListener);
        }

        // set the tag as the querystring for all views
        viewHolder.ghdbId.setTag(dork);
        viewHolder.category.setTag(dork);
        viewHolder.queryString.setTag(dork);
        viewHolder.shortDescription.setTag(dork);
        viewHolder.textualDescription.setTag(dork);
    }

    @Override
    public int getItemCount() {
        return mDorks != null ? mDorks.size() : 0;
    }
}
