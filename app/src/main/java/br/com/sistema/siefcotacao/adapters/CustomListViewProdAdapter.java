package br.com.sistema.siefcotacao.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.sistema.siefcotacao.R;
import br.com.sistema.siefcotacao.vo.Prd_ProdutoVO;

/**
 * Created by root on 05/05/15.
 */
public class CustomListViewProdAdapter extends ArrayAdapter<Prd_ProdutoVO> {
    Context context;
    public CustomListViewProdAdapter(Context context, int resource, List<Prd_ProdutoVO> items) {
        super(context, resource, items);
        this.context = context;
    }
    private class ViewHolder{
        TextView txtId;
        TextView txtDescricao;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Prd_ProdutoVO rowitem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if(convertView == null){
            convertView = mInflater.inflate(R.layout.list_item_listview_conc_prod,null);
            holder = new ViewHolder();
            holder.txtDescricao = (TextView) convertView.findViewById(R.id.list_item_listview_conc_produto_descricao);
            holder.txtId = (TextView) convertView.findViewById(R.id.list_item_listview_conc_produto_id);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtDescricao.setText(rowitem.getNmproduto());
        holder.txtId.setText(rowitem.getCdproduto().toString());

        return convertView;
    }
}
