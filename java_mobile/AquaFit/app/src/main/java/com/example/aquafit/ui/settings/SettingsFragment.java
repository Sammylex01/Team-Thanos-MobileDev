package com.example.aquafit.ui.settings;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.aquafit.R;
import java.util.ArrayList;

public class SettingsFragment extends Fragment {
	View view;
    RecyclerView recyclerView;
    ArrayList<Item> arrayList;
    CustomAdapter adapter;
	public class Item{
		String text;
		int amount;
		public Item(String text,int amount){
			this.text =text;
			this.amount = amount;
		}
	}
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
		arrayList = new ArrayList<Item>();
		arrayList.add(new Item("1 cup = 4 liters",1));
		arrayList.add(new Item("2 cups = 8 liters",2));
		
		adapter = new CustomAdapter(arrayList,R.layout.item_list_big);
        recyclerView.setAdapter(adapter);    
        return view;
    }


    public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{

       int cardLayout;
	   ArrayList<Item> arrayList;
        public CustomAdapter(ArrayList<Item> arrayList,int cardLayout) {
            this.arrayList = arrayList;
			this.cardLayout = cardLayout;
        }
        @Override
        public  ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(getContext()).inflate(cardLayout, viewGroup, false);
            return new ViewHolder(view);
        }
        @Override
        public  void onBindViewHolder(ViewHolder viewHolder, int position) {
            viewHolder.name.setText(arrayList.get(position).text);
            viewHolder.image.setImageResource(R.drawable.ic_stars_white_24dp);
        }
        @Override
        public int getItemCount() {
            return arrayList.size();
        }
		
        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView name;
            ImageView image;
            public ViewHolder(View itemView) {
                super(itemView);
                name = (TextView) itemView.findViewById(R.id.name);
                image = (ImageView) itemView.findViewById(R.id.image);
            }
        }
    }
}
