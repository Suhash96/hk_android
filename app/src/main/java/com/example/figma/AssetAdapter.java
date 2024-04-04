package com.example.figma;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AssetAdapter extends ArrayAdapter<String> {
    private ArrayList<String> assets;
    private LayoutInflater inflater;
    private Map<String, Integer> itemCounts;

    public AssetAdapter(Context context, ArrayList<String> assets) {
        super(context, 0, assets);
        this.assets = assets;
        inflater = LayoutInflater.from(context);
        itemCounts = new HashMap<>();
        // Initialize counts for all items to 0 initially
        for (String asset : assets) {
            itemCounts.put(asset, 0);
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_layout, parent, false);
            holder = new ViewHolder();
            holder.assetName = convertView.findViewById(R.id.asset_name);
            holder.countText = convertView.findViewById(R.id.count_text);
            holder.decreaseButton = convertView.findViewById(R.id.decrease_button);
            holder.increaseButton = convertView.findViewById(R.id.increase_button);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final String asset = assets.get(position);
        holder.assetName.setText(asset);
        holder.countText.setText(String.valueOf(itemCounts.get(asset)));

        // Example of setting onClickListener for decrease button
        holder.decreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle decrease count action here
                int count = itemCounts.get(asset);
                if (count > 0) {
                    itemCounts.put(asset, count - 1);
                    notifyDataSetChanged(); // Notify adapter about data change
                }
            }
        });

        // Example of setting onClickListener for increase button
        holder.increaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle increase count action here
                int count = itemCounts.get(asset);
                itemCounts.put(asset, count + 1);
                notifyDataSetChanged(); // Notify adapter about data change
            }
        });

        return convertView;
    }

    // Method to get the count for a specific item
    public int getCountForItem(String item) {
        if (itemCounts.containsKey(item)) {
            return itemCounts.get(item);
        }
        return 0; // Default count is 0 if the item is not found
    }

    private static class ViewHolder {
        TextView assetName;
        TextView countText;
        Button decreaseButton;
        Button increaseButton;
    }
}
