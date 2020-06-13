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
import android.widget.*;
import java.util.*;
import android.content.*;
import com.example.aquafit.ui.settings.SettingsFragment.*;
import android.view.*;
import android.os.*;
import android.view.View.*;

public class SettingsFragment extends Fragment {
	View view;
    RecyclerView recyclerView;
    private CustomAdapter adapter;
	
	private CustomAdapter amountAdapter;

	private ArrayList<SettingsFragment.Item> arrayList;

	private Button saveButton;
	public class Item{
		String text1;
		String text2;
		double amount;
		public Item(String text,String text2,double amount){
			this.text1 =text;
			this.text2 = text2;
			this.amount = amount;
		}
	}
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);
        RecyclerView amountList = view.findViewById(R.id.home_fragmentAmountList);
		RecyclerView intervalList = view.findViewById(R.id.home_fragmentIntervalList);
		
		arrayList = new ArrayList<Item>();
		arrayList.add(new Item("1 lt","2 cups",1));
		arrayList.add(new Item("2 lts","4 cups",2));
		arrayList.add(new Item("3 lts","6 cups",3));
		arrayList.add(new Item("4 lts","8 cups",4));
		final TimerManager timer = new TimerManager(getContext());
		saveButton = view.findViewById(R.id.home_fragmentSaveChanges);
		saveButton.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					timer.setTimer(50,50);
				}
				
	});
		amountAdapter = new CustomAdapter(arrayList, new OnItemClickListener(){

				@Override
				public void onItemClick(SettingsFragment.CustomAdapter a, SettingsFragment.CustomAdapter.ViewHolder b, int position)
				{
					if(position == a.selected)return;
					Item p = arrayList.get(position);
					ArrayList<Item> inte = SettingsFragment.this.arrayList;
					for(Item i:inte){
						i.text2 = String.format("%.2f cups\neach",(((p.amount*i.amount)/6.0)));
					}
					adapter.notifyDataSetChanged();
					if(a.selectedView != null)
						a.selectedView.setSelected(false);
					b.itemView.setSelected(true);
					a.selectedView = b.itemView;
					a.selected = position;
					saveButton.setEnabled(true);
				}
			});
		
		arrayList = new ArrayList<Item>();
		arrayList.add(new Item("30 mins","",0.5));
		arrayList.add(new Item("1 hr","",1));
		arrayList.add(new Item("2 hrs","",2));
		arrayList.add(new Item("3 hrs","",3));
		arrayList.add(new Item("4 hrs","",4));
		adapter  =new CustomAdapter(arrayList,null);
		
		amountList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
		intervalList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
		amountList.setItemAnimator(new DefaultItemAnimator());
		intervalList.setItemAnimator(new DefaultItemAnimator());
		amountList.setAdapter(amountAdapter);
		intervalList.setAdapter(adapter);
        return view;
    }
	public abstract static class OnItemClickListener{
		public abstract void onItemClick(CustomAdapter a,CustomAdapter.ViewHolder b,int position);
	}
	private class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{
		List<Item> arrayList;
		int selected=-1;
		View selectedView;
	
		private OnItemClickListener itemClickListener;

		public CustomAdapter(List<Item> items,OnItemClickListener b){
			this.arrayList =items;
			this.itemClickListener = b;
		}
		@Override
        public  ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_settings, viewGroup, false);
            return new ViewHolder(view);
        }
        @Override
        public  void onBindViewHolder(final ViewHolder viewHolder, int position) {
            viewHolder.text1.setText(arrayList.get(position).text1);
            viewHolder.text2.setText(arrayList.get(position).text2);
			viewHolder.itemView.setSelected(position==selected);
			if(itemClickListener != null)
				viewHolder.setOnClickListener(itemClickListener);
        }
        @Override
        public int getItemCount() {
            return arrayList.size();
        }
		public class ViewHolder extends RecyclerView.ViewHolder {
            TextView text1;
            TextView text2;
            public ViewHolder(View itemView) {
                super(itemView);
                text1 = (TextView) itemView.findViewById(R.id.item_settingsMajor);
				text2 = (TextView) itemView.findViewById(R.id.item_settingsMinor);
            }
			public void setOnClickListener(final OnItemClickListener itemClickListener){
				this.itemView.setOnClickListener(new View.OnClickListener(){
						@Override
						public void onClick(View p1)
						{
							itemClickListener.onItemClick(CustomAdapter.this,ViewHolder.this,getPosition());
						}


					}
				);
			}
        }
	}
	
}
