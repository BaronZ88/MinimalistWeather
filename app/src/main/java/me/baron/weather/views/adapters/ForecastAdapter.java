package me.baron.weather.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.baron.library.adapter.BaseRecyclerViewAdapter;
import me.baron.weather.R;
import me.baron.weather.models.db.entities.style.Forecast;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         16/6/23
 */
public class ForecastAdapter extends BaseRecyclerViewAdapter<ForecastAdapter.ViewHolder> {

    private List<Forecast> forecasts;

    public ForecastAdapter(List<Forecast> forecasts) {
        this.forecasts = forecasts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_forecast, parent, false);
        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(ForecastAdapter.ViewHolder holder, int position) {
        Forecast forecast = forecasts.get(position);
        holder.weekTextView.setText(forecast.getWeek());
        holder.weatherIconImageView.setImageResource(R.mipmap.ic_launcher);
        holder.tempMaxTextView.setText(forecast.getTempMax() + "°");
        holder.tempMinTextView.setText(forecast.getTempMin()+"°");
    }

    @Override
    public int getItemCount() {
        return forecasts == null ? 0 : forecasts.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.week_text_view)
        TextView weekTextView;
        @BindView(R.id.weather_icon_image_view)
        ImageView weatherIconImageView;
        @BindView(R.id.temp_max_text_view)
        TextView tempMaxTextView;
        @BindView(R.id.temp_min_text_view)
        TextView tempMinTextView;

        ViewHolder(View itemView, ForecastAdapter adapter) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> adapter.onItemHolderClick(ViewHolder.this));
        }
    }
}
