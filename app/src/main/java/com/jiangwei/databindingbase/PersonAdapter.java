package com.jiangwei.databindingbase;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * author: jiangwei18 on 17/5/5 18:51 email: jiangwei18@baidu.com Hi: jwill金牛
 */

public class PersonAdapter extends RecyclerView.Adapter<BindingViewHolder> {

    private static final int PERSON_TYPE_ONE = 1;
    private static final int PERSON_TYPE_TWO = 2;
    private OnItemClickListener mListener;
    private List<Person> mLists;
    private LayoutInflater mInflater;

    public PersonAdapter(Context context) {
        mLists = new ArrayList<>();
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setmListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }

    public interface OnItemClickListener {
        void onClick(Person person);
    }

    @Override
    public int getItemViewType(int position) {
        if (mLists.get(position).name.equals("姜威")) {
            return PERSON_TYPE_ONE;
        } else {
            return PERSON_TYPE_TWO;
        }
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = null;
        if (viewType == PERSON_TYPE_ONE) {
            binding = DataBindingUtil.inflate(mInflater, R.layout.item_one, parent, false);
        } else {
            binding = DataBindingUtil.inflate(mInflater, R.layout.item_two, parent, false);
        }
        return new BindingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        final Person person = mLists.get(position);
        holder.getBinding().setVariable(BR.person, person);
        holder.getBinding().executePendingBindings();
        holder.getBinding().getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onClick(person);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mLists.size();
    }

    public void addAll(List<Person> lists) {
        if (lists != null && lists.size() != 0) {
            mLists.addAll(lists);
        }
    }

    public void add(Person p) {
        mLists.add(p);
        notifyItemInserted(mLists.size());
    }

    public void remove() {
        if (mLists.size() == 0) {
            return;
        }
        mLists.remove(0);
        notifyItemRemoved(0);
    }
}
