package com.t3h.myprojectnoteupdate.utils;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.t3h.myprojectnoteupdate.item.Item;

import java.util.List;

public class NoteDistinc extends DiffUtil.Callback {

    List<Item>oldItemList;
    List<Item>newItemList;

    public NoteDistinc(List<Item> oldItemList, List<Item> newItemList) {
        this.oldItemList = oldItemList;
        this.newItemList = newItemList;
    }

    @Override
    public int getOldListSize() {
        return oldItemList.size();
    }

    @Override
    public int getNewListSize() {
        return newItemList.size();
    }

    @Override
    public boolean areItemsTheSame(int i, int i1) {
        return oldItemList.get(i).getId() ==newItemList.get(i1).getId();
    }

    @Override
    public boolean areContentsTheSame(int i, int i1) {
        return oldItemList.get(i).equals(newItemList.get(i1));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
