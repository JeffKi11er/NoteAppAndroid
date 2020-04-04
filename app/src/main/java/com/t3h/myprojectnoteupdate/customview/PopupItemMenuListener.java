package com.t3h.myprojectnoteupdate.customview;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;

import com.t3h.myprojectnoteupdate.R;
import com.t3h.myprojectnoteupdate.item.Item;

public class PopupItemMenuListener implements View.OnClickListener {
    private Item item;
    private Context context;

    public PopupItemMenuListener(Item item, Context context) {
        this.item = item;
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        PopupMenu popupMenu = new PopupMenu(context,v);
        popupMenu.getMenuInflater().inflate(R.menu.menu_main,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menu_delete:
                        return true;
                    case R.id.menu_pin:
                        return true;
                    case R.id.menu_share:
                        return true;
                }
                return false;
            }
        });
    }
}
