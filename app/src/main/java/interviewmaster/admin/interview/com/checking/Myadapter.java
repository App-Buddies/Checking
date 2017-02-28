package interviewmaster.admin.interview.com.checking;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


class MyAdapte extends RecyclerView.Adapter<MyAdapte.ViewHldr> {
    Context context;
    List<Employee> array;
    LayoutInflater layoutInflater;
    Example item;

    public MyAdapte(Context activity, List<Employee> array) {
        this.context = activity;
        this.array = array;
        layoutInflater = LayoutInflater.from(context.getApplicationContext());
    }

    @Override
    public ViewHldr onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.custom_layout, parent, false);
        ViewHldr viewHldr = new ViewHldr(view, parent.getContext(), array);
        return viewHldr;
    }

    int i = 0;

    @Override
    public void onBindViewHolder(final ViewHldr holder, final int position) {


        holder.firstName.setText(array.get(position).getFirstName());
        holder.lastName.setText(array.get(position).getLastName());
        holder.designation.setText(array.get(position).getDesignation());
        if (!TextUtils.isEmpty(array.get(position).getImageURL())) {
            Picasso.with(holder.context)
                    .load(array.get(position).getImageURL())
                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .into(holder.imageView, new Callback() {
                        @Override
                        public void onSuccess() {
                        }

                        @Override
                        public void onError() {
                            Picasso.with(context)
                                    .load(array.get(position).getImageURL())
                                    .error(R.mipmap.ic_launcher)
                                    .into(holder.imageView, new Callback() {
                                        @Override
                                        public void onSuccess() {

                                        }

                                        @Override
                                        public void onError() {
                                            Log.v("Picasso", "Could not fetch image");
                                        }
                                    });
                        }
                    });
        } else {
            Picasso.with(context)
                    .load(R.mipmap.ic_launcher)
                    .into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        if (array.size() == 0) {
            return 0;
        } else {
            return array.size();
        }
    }

    public class ViewHldr extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView firstName, lastName, designation, city;
        List<Employee> array = new ArrayList<>();
        Context context;
        ImageView imageView;

        public ViewHldr(View itemView, Context activity, List<Employee> array) {
            super(itemView);
            this.context = activity;
            this.array = array;
            firstName = (TextView) itemView.findViewById(R.id.textFirstName);
            lastName = (TextView) itemView.findViewById(R.id.textSecondName);
            designation = (TextView) itemView.findViewById(R.id.textDesignation);
            city = (TextView) itemView.findViewById(R.id.textCity);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);


        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            // Employee array = this.array.get(position);
            //   String firstname = array.getFirstName();
            // communicator.respond(firstname);
        }
    }


}