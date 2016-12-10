package me.baron.weatherstyle.views.adapters;

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
import me.baron.weatherstyle.R;
import me.baron.weatherstyle.models.db.models.style.Forecast;

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
        holder.dateTextView.setText(forecast.getWeek());
        holder.weatherIconImageView.setImageResource(R.mipmap.ic_launcher);
        holder.tempTextView.setText(forecast.getTempMin() + "~" + forecast.getTempMax() + "℃");
    }

    @Override
    public int getItemCount() {
        return forecasts == null ? 0 : forecasts.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_date_tv)
        TextView dateTextView;
        @BindView(R.id.item_weather_icon_iv)
        ImageView weatherIconImageView;
        @BindView(R.id.item_temp_tv)
        TextView tempTextView;

        ViewHolder(View itemView, ForecastAdapter adapter) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> adapter.onItemHolderClick(ViewHolder.this));
        }
    }
}
