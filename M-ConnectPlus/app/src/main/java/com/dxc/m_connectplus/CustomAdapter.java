package com.dxc.m_connectplus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by pakkirai on 11/7/2017.
 */
public class CustomAdapter extends ArrayAdapter<ATM> {

    private List<ATM> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView slno;
        TextView atmname;
        TextView address;

    }

    public CustomAdapter(List<ATM> data, Context context) {
        super(context, R.layout.row_item, data);
        this.dataSet = data;
        this.mContext=context;
    }



    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ATM atm = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.vertical_row, parent, false);
            viewHolder.slno = (TextView) convertView.findViewById(R.id.slno);
            viewHolder.atmname = (TextView) convertView.findViewById(R.id.atmname);
            viewHolder.address = (TextView) convertView.findViewById(R.id.address);


            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

      //  Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        //result.startAnimation(animation);
        lastPosition = position;

        viewHolder.slno.setText(dataSet.get(position).getId());
        viewHolder.atmname.setText(dataSet.get(position).getName());
        viewHolder.address.setText(dataSet.get(position).getAddress());

        // Return the completed view to render on screen
        return convertView;
    }
}