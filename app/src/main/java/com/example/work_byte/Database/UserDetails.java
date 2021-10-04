package com.example.work_byte.Database;

import android.provider.BaseColumns;
import android.widget.EditText;

public class UserDetails {
//    public UserDetails(String first_name, EditText l_name, EditText email, EditText m_number, EditText work_area, EditText password, EditText re_password) {
//    }

//    public UserDetails(EditText f_name, EditText l_name, EditText email, EditText m_number, EditText work_area, EditText password, EditText re_password){
//
//        String first_name = f_name.getText().toString();
//
//    }

    public static class User implements BaseColumns{

        public static final String TABLE_NAME = "users";

        public static String getTableName() {
            return TABLE_NAME;
        }

        public static String getFirst_name() {
            return first_name;
        }

        public static String getLast_name() {
            return last_name;
        }

        public static String getEmail() {
            return email;
        }

        public static String getMobile() {
            return mobile;
        }

        public static String getWorkArea() {
            return workArea;
        }

        public static String getPassword() {
            return password;
        }

        public static String getRe_password() {
            return re_password;
        }

        public static String getAddress() {
            return address;
        }

        public static String getExperience() {
            return experience;
        }

        public static String getCategory() {
            return category;
        }

        public static  String first_name = "f_name";
        public static  String last_name = "l_name";
        public static  String email = "email";

        public static String getPro_image() {
            return pro_image;
        }

        public static void setPro_image(String pro_image) {
            User.pro_image = pro_image;
        }

        public static  String mobile = "m_number";
        public static  String workArea = "work_area";
        public static  String password = "password";
        public static  String re_password = "re_password";
        public static  String address = "address";
        public static  String experience = "experience";
        public static  String category = "category";
        public static String pro_image = "pro_image";
    }



}

