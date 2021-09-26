package com.example.imagecrud;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class CarpenterStoreList extends AppCompatActivity {

    private List<The_Slide_Items_Model_Class> listItems;
    private ViewPager page;
    private TabLayout tabLayout;

    GridView gridView;
    ArrayList<Store> list;
    StoreListAdapter adapter = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_list_activity);

        gridView = (GridView) findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new StoreListAdapter(this, R.layout.food_items, list);
        gridView.setAdapter(adapter);

        // get all data from sqlite
        Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM FOOD");
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String itemname = cursor.getString(1);
            String discription = cursor.getString(2);
            byte[] image = cursor.getBlob(3);
            String phone=cursor.getString(4);
            String price=cursor.getString(5);



            list.add(new Store(itemname, discription, image, id, phone, price));
        }
        adapter.notifyDataSetChanged();

        ///Sliding image part

        page = findViewById(R.id.my_pager) ;
        tabLayout = findViewById(R.id.my_tablayout);

        // Make a copy of the slides you'll be presenting.
        listItems = new ArrayList<>() ;
        listItems.add(new The_Slide_Items_Model_Class(R.drawable.item1,""));
        listItems.add(new The_Slide_Items_Model_Class(R.drawable.item2,""));
        listItems.add(new The_Slide_Items_Model_Class(R.drawable.item3,""));
        listItems.add(new The_Slide_Items_Model_Class(R.drawable.item4,""));
        listItems.add(new The_Slide_Items_Model_Class(R.drawable.item5,""));


        The_Slide_items_Pager_Adapter itemsPager_adapter = new The_Slide_items_Pager_Adapter(this, listItems);
        page.setAdapter(itemsPager_adapter);

        // The_slide_timer
        java.util.Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new The_slide_timer(),2000,3000);
        tabLayout.setupWithViewPager(page,true);
    }

    public class The_slide_timer extends TimerTask {
        @Override
        public void run() {

            CarpenterStoreList.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (page.getCurrentItem()< listItems.size()-1) {
                        page.setCurrentItem(page.getCurrentItem()+1);
                    }
                    else
                        page.setCurrentItem(0);
                }
            });
        }


//        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
//
//                CharSequence[] items = {"Update", "Delete"};
//                AlertDialog.Builder dialog = new AlertDialog.Builder(FoodList.this);
//
//                dialog.setTitle("Choose an action");
//                dialog.setItems(items, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int item) {
//                        if (item == 0) {
//                            // update
//                            Cursor c = MainActivity.sqLiteHelper.getData("SELECT id FROM FOOD");
//                            ArrayList<Integer> arrID = new ArrayList<Integer>();
//                            while (c.moveToNext()){
//                                arrID.add(c.getInt(0));
//                            }
//                            // show dialog update at here
//                            showDialogUpdate(FoodList.this, arrID.get(position));
//
//                        } else {
//                            // delete
//                            Cursor c = MainActivity.sqLiteHelper.getData("SELECT id FROM FOOD");
//                            ArrayList<Integer> arrID = new ArrayList<Integer>();
//                            while (c.moveToNext()){
//                                arrID.add(c.getInt(0));
//                            }
//                            showDialogDelete(arrID.get(position));
//                        }
//                    }
//                });
//                dialog.show();
//                return true;
//            }
//        });
//    }
//
//    ImageView imageViewFood;
//    private void showDialogUpdate(Activity activity, final int position){
//
//        final Dialog dialog = new Dialog(activity);
//        dialog.setContentView(R.layout.update_food_activity);
//        dialog.setTitle("Update");
//
//        imageViewFood = (ImageView) dialog.findViewById(R.id.imageViewFood);
//        final EditText edtName = (EditText) dialog.findViewById(R.id.edtName);
//        final EditText edtPrice = (EditText) dialog.findViewById(R.id.edtPrice);
//        Button btnUpdate = (Button) dialog.findViewById(R.id.btnUpdate);
//
//        // set width for dialog
//        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);
//        // set height for dialog
//        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.7);
//        dialog.getWindow().setLayout(width, height);
//        dialog.show();
//
//        imageViewFood.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // request photo library
//                ActivityCompat.requestPermissions(
//                        FoodList.this,
//                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                        888
//                );
//            }
//        });
//
//        btnUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    MainActivity.sqLiteHelper.updateData(
//                            edtName.getText().toString().trim(),
//                            edtPrice.getText().toString().trim(),
//                            MainActivity.imageViewToByte(imageViewFood),
//                            position
//                    );
//                    dialog.dismiss();
//                    Toast.makeText(getApplicationContext(), "Update successfully!!!",Toast.LENGTH_SHORT).show();
//                }
//                catch (Exception error) {
//                    Log.e("Update error", error.getMessage());
//                }
//                updateFoodList();
//            }
//        });
//    }
//
//    private void showDialogDelete(final int idFood){
//        final AlertDialog.Builder dialogDelete = new AlertDialog.Builder(FoodList.this);
//
//        dialogDelete.setTitle("Warning!!");
//        dialogDelete.setMessage("Are you sure you want to this delete?");
//        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                try {
//                    MainActivity.sqLiteHelper.deleteData(idFood);
//                    Toast.makeText(getApplicationContext(), "Delete successfully!!!",Toast.LENGTH_SHORT).show();
//                } catch (Exception e){
//                    Log.e("error", e.getMessage());
//                }
//                updateFoodList();
//            }
//        });
//
//        dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        dialogDelete.show();
//    }
//
//    private void updateFoodList(){
//        // get all data from sqlite
//        Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM FOOD");
//        list.clear();
//        while (cursor.moveToNext()) {
//            int id = cursor.getInt(0);
//            String name = cursor.getString(1);
//            String price = cursor.getString(2);
//            byte[] image = cursor.getBlob(3);
//
//            list.add(new Food(name, price, image, id));
//        }
//        adapter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//
//        if(requestCode == 888){
//            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                Intent intent = new Intent(Intent.ACTION_PICK);
//                intent.setType("image/*");
//                startActivityForResult(intent, 888);
//            }
//            else {
//                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
//            }
//            return;
//        }
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        if(requestCode == 888 && resultCode == RESULT_OK && data != null){
//            Uri uri = data.getData();
//            try {
//                InputStream inputStream = getContentResolver().openInputStream(uri);
//                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                imageViewFood.setImageBitmap(bitmap);
//
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//
//        super.onActivityResult(requestCode, resultCode, data);
//
    }
}
