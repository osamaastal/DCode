package com.oya.doubleq_client.ui.product_details;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.oya.doubleq_client.R;
import com.oya.doubleq_client.ui.home.SliderAdapter;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.oya.doubleq_client.ui.product_details.ImagesAdapter.*;

public class ImagesAdapter extends
        SliderViewAdapter<ImagesAdapter.ImagesAdapterVH> {

    private Context context;
    private List<String> mSliderItems = new ArrayList<>();

    public ImagesAdapter(Context context) {
        this.context = context;
    }

    public void renewItems(List<String> sliderModels) {
        this.mSliderItems = sliderModels;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        this.mSliderItems.remove(position);
        notifyDataSetChanged();
    }

    public void addItem(String sliderModel) {
        this.mSliderItems.add(sliderModel);
        notifyDataSetChanged();
    }
    public void setList(List<String> mSliderItems){
        if (mSliderItems==null)
            return;
        this.mSliderItems = mSliderItems;
        notifyDataSetChanged();
    }

    @Override
    public ImagesAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_slider, null);
        return new ImagesAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(ImagesAdapterVH viewHolder, int position) {
        //        viewHolder.textViewDescription.setVisibility(View.GONE);
        Glide.with(context)
                .load( mSliderItems.get(position))
                .fitCenter()
                .into(viewHolder.imageViewBackground);
        viewHolder.itemView.setOnClickListener(v -> {

                }
        );
    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return mSliderItems.size();
    }

    class ImagesAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        ImageView imageGifContainer;
        TextView textViewDescription;

        public ImagesAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            imageGifContainer = itemView.findViewById(R.id.iv_gif_container);
            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
            this.itemView = itemView;
        }
    }

}
