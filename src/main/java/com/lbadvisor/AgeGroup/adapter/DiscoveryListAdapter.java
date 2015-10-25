package com.lbadvisor.AgeGroup.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.lbadvisor.AgeGroup.R;
import com.lbadvisor.AgeGroup.bean.GroupListInfo;
import com.lbadvisor.AgeGroup.utils.CountDownTimer;
import com.lbadvisor.AgeGroup.view.RatingBarView;
import java.util.ArrayList;


/**
 * Created by likun on 15/10/22.
 */
public class DiscoveryListAdapter extends MyBaseAdapter<GroupListInfo> {


    public DiscoveryListAdapter(ArrayList<GroupListInfo> list, Context context) {
        super(list, context);
    }

    @Override
    public View creatView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_discovery_list, null);
            holder.people = (TextView) convertView.findViewById(R.id.tv_group_people_number);
            holder.remainingAccount = (TextView) convertView.findViewById(R.id.tv_remaining_amount);
            holder.remainingPeople = (TextView) convertView.findViewById(R.id.tv_remaining_people);
            holder.ratingbar = (RatingBarView) convertView.findViewById(R.id.ratingbar);
            holder.countdown = (TextView) convertView.findViewById(R.id.tv_countdown_time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
//        CountDownTimer countDownTimer = new CountDownTimer(holder.countdown,list.get(position).getCountdowntime());
        CountDownTimer.newInstance().setCountDownTime(holder.countdown, list.get(position).getCountdowntime());
        holder.ratingbar.setStar(list.get(position).getRatingbar());
        holder.people.setText(list.get(position).getPeople() + " 人起");
        holder.remainingAccount.setText(list.get(position).getRemainingAccount() + " 元");
        holder.remainingPeople.setText(list.get(position).getRemainingPeople() + " 人");
//        holder.countdown.setText(list.get(position).getCountdowntime());
        return convertView;
    }

    private class ViewHolder {
        TextView people, remainingAccount, remainingPeople, countdown;
        RatingBarView ratingbar;
    }
}
