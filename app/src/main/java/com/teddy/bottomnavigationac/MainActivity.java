package com.teddy.bottomnavigationac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.widget.Toast;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class MainActivity extends AppCompatActivity {

    //inisialisasi variabel
    MeowBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //assign variabel
        bottomNavigation = findViewById(R.id.bottom_navigation);

        //Add menu item
        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_notification));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_about));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                //inisialisasi fragmen
                Fragment fragment = null;

                //check condition
                switch (item.getId()){
                    case 1:
                        //when id is 1
                        //tampilkan fragmen notifikasi
                        fragment = new NotificationFragment();
                        break;
                    case 2 :
                        //when id is 2
                        //tampilkan fragmen home
                        fragment = new HomeFragment();
                        break;

                    case 3 :
                        //when id is 3
                        //tampilkan fragmen about
                        fragment = new AboutFragment();
                        break;
                }
                // Load fragment
                loadFragment(fragment);
            }
        });

        //Set Notification count
        bottomNavigation.setCount(1,"10");

        //Set Home fragment initially selected
        bottomNavigation.show(2,true);

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                // Display Toast
                Toast.makeText(getApplicationContext()
                        ,"Kamu Klik"+ item.getId()
                        ,Toast.LENGTH_SHORT).show();
            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                //Display Toast
                Toast.makeText(getApplicationContext()
                        ,"Kamu Tidak memilih"+ item.getId()
                        ,Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void loadFragment(Fragment fragment) {
        //Ganti Fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,fragment)
                .commit();

    }
}