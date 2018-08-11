package dfdashboardtest.com.myapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import dfdashboardtest.com.myapplication.DETOverviewOfCandidates;
import dfdashboardtest.com.myapplication.HostelFeesActivity;
import dfdashboardtest.com.myapplication.R;
import dfdashboardtest.com.myapplication.ScheduleActivity;
import dfdashboardtest.com.myapplication.models.App;


public class AdapterGrid extends RecyclerView.Adapter<AdapterGrid.ViewHolder> {

    private static final String TAG = "Adapter" ;
    private List<App> mApps;
    private boolean mHorizontal;
    private boolean mPager;
    private Context mContext;
    View v;
   // SharedPreference mSharedPreferences;
  //  SharedPreferences.Editor mEditor;

  //  String ACADEMICSKey =(mSharedPreferences.getString(SectionListDataAdapter.ACADEMICSKey,));

    public AdapterGrid(boolean horizontal, boolean pager, List<App> apps) {
        mHorizontal = horizontal;
        mApps = apps;
        mPager = pager;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        /*return mHorizontal ? new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter, parent, false)) :
                new ViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.adapter_vertical, parent, false)); */

         //   return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_grid, parent, false));
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_grid, parent, false);
        ViewHolder mh = new ViewHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        App app = mApps.get(position);
        holder.imageView.setImageResource(app.getDrawable());
        holder.tvTitle.setText(app.getName());

      //  holder.ratingTextView.setText(String.valueOf(app.getRating()));
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mApps.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView imageView;
        public TextView tvTitle;
   //     public ImageView likeImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            imageView = (ImageView) itemView.findViewById(R.id.itemImage);
         //   likeImageView = (ImageView) itemView.findViewById(R.id.likeImageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(v.getContext(), tvTitle.getText(), Toast.LENGTH_SHORT).show();
                    match_the_Option(tvTitle.getText().toString().trim());
                }
            });
        }

        void match_the_Option(String clicked_icon) {
            if (clicked_icon.equals("DET-MIS")) {
                Intent i = new Intent(v.getContext(), DETOverviewOfCandidates.class);
                v.getContext().startActivity(i);
                ((Activity) v.getContext()).finish();
            }
            if (clicked_icon.equals("SCHEDULER")) {
                Intent i = new Intent(v.getContext(), ScheduleActivity.class);
                v.getContext().startActivity(i);
                ((Activity) v.getContext()).finish();
            }

            if (clicked_icon.equals("HOSTEL"))
            {
                Intent i = new Intent(v.getContext(), HostelFeesActivity.class);
                v.getContext().startActivity(i);
                ((Activity) v.getContext()).finish();
            }

        }


        @Override
        public void onClick(View v) {
            Log.d("App", mApps.get(getAdapterPosition()).getName());
        }
    }

}

