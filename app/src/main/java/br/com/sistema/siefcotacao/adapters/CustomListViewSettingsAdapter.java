package br.com.sistema.siefcotacao.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.sistema.siefcotacao.R;
import br.com.sistema.siefcotacao.beans.RowItem_ListView_Settings;

/**
 * Created by root on 05/05/15.
 */
public class CustomListViewSettingsAdapter extends ArrayAdapter<RowItem_ListView_Settings> {
    Context context;
    public CustomListViewSettingsAdapter(Context context, int resource, List<RowItem_ListView_Settings> items) {
        super(context, resource, items);
        this.context = context;
    }
    private class ViewHolder{
        ImageView imageView;
        TextView txtTitle;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        RowItem_ListView_Settings rowitem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if(convertView == null){
            convertView = mInflater.inflate(R.layout.list_item_listview_settings,null);
            holder = new ViewHolder();
            holder.txtTitle = (TextView) convertView.findViewById(R.id.list_item_listview_settings_title);
            holder.imageView = (ImageView) convertView.findViewById(R.id.list_item_listview_settings_icon);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtTitle.setText(rowitem.getTitle());
        holder.imageView.setImageResource(rowitem.getImageId());

        return convertView;
    }
}
