package com.example.imagecrud;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class StoreListAdapter extends BaseAdapter {

    private Context context;
    private  int layout;
    private ArrayList<Store> foodsList;


    public StoreListAdapter(Context context, int layout, ArrayList<Store> foodsList) {
        this.context = context;
        this.layout = layout;
        this.foodsList = foodsList;
    }

    @Override
    public int getCount() {
        return foodsList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView txtItemName,txtDiscription,txtPhone,txtPrice,txtCatagory;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txtItemName = (TextView) row.findViewById(R.id.txtItemName);
            holder.txtDiscription = (TextView) row.findViewById(R.id.txtDiscription);
            holder.imageView = (ImageView) row.findViewById(R.id.imgFood);
            holder.txtPhone = (TextView) row.findViewById(R.id.txtPhone);
            holder.txtPrice = (TextView) row.findViewById(R.id.txtPrice);


            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        Store store = foodsList.get(position);

        holder.txtItemName.setText(store.getItemname());
        holder.txtDiscription.setText(store.getDiscription());

        byte[] foodImage = store.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
        holder.imageView.setImageBitmap(bitmap);

        holder.txtPhone.setText(store.getPhone());
        holder.txtPrice.setText(store.getPrice());




        return row;
    }
}
