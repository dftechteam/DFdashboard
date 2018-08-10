package dfdashboardtest.com.myapplication.Adapter;

/**
 * Created by pratap.kesaboyina on 24-12-2014.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dfdashboardtest.com.myapplication.DETOverviewOfCandidates;
import dfdashboardtest.com.myapplication.HostelFeesActivity;
import dfdashboardtest.com.myapplication.R;
import dfdashboardtest.com.myapplication.ScheduleActivity;
import dfdashboardtest.com.myapplication.models.App;

public class SectionListDataAdapter extends RecyclerView.Adapter<SectionListDataAdapter.SingleItemRowHolder> {

    private ArrayList<App> itemsList;
    private List<App> mApps;
    private Context mContext;

    private String[] mValues;
    View v;


    public SectionListDataAdapter(Context context, ArrayList<App> itemsList) {
        this.itemsList = itemsList;
        this.mContext = context;
        this.mValues = mValues;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_single_card, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(final SingleItemRowHolder holder, final int i) {

       // SingleItemModel singleItem = itemsList.get(i);

        App app = itemsList.get(i);
        holder.tvTitle.setText(itemsList.get(i).getName());
        holder.itemImage.setImageResource(itemsList.get(i).getDrawable());
        holder.itemImage.setTag(itemsList.get(i).getDrawable());
      /*  holder.likeImageView.setTag(R.drawable.ic_likenew);

        holder.likeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                MainActivity mainActivity = new MainActivity();


                int id = (int)holder.likeImageView.getTag();
                if( id == R.drawable.ic_likenew){

                    holder.likeImageView.setTag(R.drawable.ic_likednew);
                    holder.likeImageView.setImageResource(R.drawable.ic_likednew);
                    mainActivity.addWishlistImageUri(mValues[i]);
                    Toast.makeText(v.getContext(),holder.tvTitle.getText()+" added to favourites",Toast.LENGTH_SHORT).show();

                }else{

                    holder.likeImageView.setTag(R.drawable.ic_likenew);
                    holder.likeImageView.setImageResource(R.drawable.ic_likenew);
                    mainActivity.removeWishlistImageUri(i);
                    Toast.makeText(v.getContext(),holder.tvTitle.getText()+" removed from favourites",Toast.LENGTH_SHORT).show();


                }

            }
        });*/
       /* Glide.with(mContext)
                .load(feedItem.getImageURL())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .error(R.drawable.bg)
                .into(feedListRowHolder.thumbView);*/
    }

    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView tvTitle;

        protected ImageView itemImage;

      //  protected ImageView likeImageView;

        public SingleItemRowHolder(View view) {
            super(view);

            this.tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            this.itemImage = (ImageView) view.findViewById(R.id.itemImage);
        /*    this.likeImageView = (ImageView) view.findViewById(R.id.likeImageView);
*/

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(v.getContext(), tvTitle.getText(), Toast.LENGTH_SHORT).show();
                    match_the_Option(tvTitle.getText().toString().trim());
                }
            });

      /*      likeImageView = (ImageView) view.findViewById(R.id.likeImageView);*/
            //  shareImageView = (ImageView) v.findViewById(R.id.shareImageView);

        }

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


}