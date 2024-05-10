package com.ntp.onlineshop.adapter;

import android.content.Context;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.ntp.onlineshop.domain.PopularDomain;

import java.util.ArrayList;

public class MyItemTouchHelper extends ItemTouchHelper.SimpleCallback {

    private Context mContext;
    PopularAdapter mAdapter;
    ArrayList<PopularDomain> items = new ArrayList<>();

    public MyItemTouchHelper(Context context, PopularAdapter adapter,ArrayList<PopularDomain> items) {
        super(0, ItemTouchHelper.RIGHT);
        this.mContext = context;
        this.mAdapter = adapter;
        this.items = items;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        String title = items.get(position).getTitle(); // Thay "getName()" bằng phương thức lấy tên của item của bạn
        items.remove(position);
        //mAdapter.notifyItemRemoved(position);
        Toast.makeText(mContext, "Swiped left on " + title, Toast.LENGTH_SHORT).show();
        mAdapter.notifyDataSetChanged();
    }
}
