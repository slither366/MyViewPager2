package com.example.myviewpager2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    ViewPager2 viewPager2;
    TabLayout tabLayout;
    OrdersPagerAdapter ordersPagerAdapter;
    TabLayoutMediator tabLayoutMediator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Iniciar Componentes
        initComponents();
        //Implementando ViewPager2
        viewPager2.setAdapter(ordersPagerAdapter);
        implementLayout();
        updateBagDrawableClicked();
    }

    private void initComponents() {
        viewPager2 = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        ordersPagerAdapter = new OrdersPagerAdapter(this);
    }

    private void implementLayout() {
        tabLayoutMediator = new TabLayoutMediator(
                tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0: {
                        String tituloTab = "Pendientes";
                        implementBagDrawable(tab,tituloTab, R.color.colorAccent, R.drawable.ic_pending, 0);
                        break;
                    }
                    case 1: {
                        String tituloTab = "Confirmados";
                        implementBagDrawable(tab,tituloTab, R.color.colorAccent, R.drawable.ic_confirmed, 1);
                        break;
                    }
                    case 2: {
                        String tituloTab = "Delivered";
                        implementBagDrawable(tab,tituloTab, R.color.colorAccent, R.drawable.ic_delivered, 2);
                        break;
                    }

                }
            }
        }
        );
        tabLayoutMediator.attach();
    }

    private void implementBagDrawable(TabLayout.Tab tab, String tituloTab, int colorAccent, int ic_pending,  int flatPosTab){
        tab.setText(tituloTab);
        tab.setIcon(ic_pending);
        BadgeDrawable badgeDrawable = tab.getOrCreateBadge();
        badgeDrawable.setBackgroundColor(
                ContextCompat.getColor(getApplicationContext(), colorAccent)
        );
        if(flatPosTab==1){
            badgeDrawable.setNumber(8);
        }
        if(flatPosTab==2){
            badgeDrawable.setNumber(100);
            badgeDrawable.setMaxCharacterCount(3);
        }
        badgeDrawable.setVisible(true);
    }

    private void updateBagDrawableClicked() {
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                BadgeDrawable badgeDrawable = tabLayout.getTabAt(position).getOrCreateBadge();
                badgeDrawable.setVisible(false);
            }
        });
    }

}
