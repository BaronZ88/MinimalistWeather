package me.baron.weatherstyle.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.baron.library.adapter.BaseRecyclerViewAdapter;
import me.baron.weatherstyle.R;
import me.baron.weatherstyle.models.style.Weather;


public class CityManagerAdapter extends BaseRecyclerViewAdapter<CityManagerAdapter.ViewHolder> {

    private final List<Weather> weatherList;

    public CityManagerAdapter(List<Weather> weatherList) {
        this.weatherList = weatherList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_city_manager, parent, false);
        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.city.setText(weatherList.get(position).getCityName());
        holder.weather.setText(weatherList.get(position).getRealTime().getWeather());
        holder.aqi.setText(String.valueOf(weatherList.get(position).getAqi().getAqi()));
    }

    @Override
    public int getItemCount() {
        return weatherList == null ? 0 : weatherList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.item_tv_city)
        TextView city;
        @Bind(R.id.item_tv_weather)
        TextView weather;
        @Bind(R.id.item_tv_aqi)
        TextView aqi;

        ViewHolder(View itemView, CityManagerAdapter adapter) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> adapter.onItemHolderClick(ViewHolder.this));
        }
    }
}
