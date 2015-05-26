package br.com.sistema.siefcotacao.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.sistema.siefcotacao.CotacaoProdutoActivity;
import br.com.sistema.siefcotacao.R;
import br.com.sistema.siefcotacao.vo.Cpr_Cot_ConcorrenteVO;
import br.com.sistema.siefcotacao.vo.Prd_ProdutoVO;

/**
 * Created by root on 05/05/15.
 */
public class CustomListViewConcAdapter extends ArrayAdapter<Cpr_Cot_ConcorrenteVO> {
    Context context;
    ViewHolder holder = null;
    public CustomListViewConcAdapter(Context context, int resource, List<Cpr_Cot_ConcorrenteVO> items) {
        super(context, resource, items);
        this.context = context;
    }
    private class ViewHolder{
        TextView txtId;
        TextView txtDescricao;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Cpr_Cot_ConcorrenteVO rowitem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        //if(convertView == null){
            convertView = mInflater.inflate(R.layout.list_item_listview_conc_prod,null);
            holder = new ViewHolder();
            holder.txtDescricao = (TextView) convertView.findViewById(R.id.list_item_listview_conc_produto_descricao);
            holder.txtId = (TextView) convertView.findViewById(R.id.list_item_listview_conc_produto_id);
            convertView.setTag(holder);
        //} else {
        //    holder = (ViewHolder) convertView.getTag();
        //}

        holder.txtDescricao.setText(rowitem.getNmconcorrente());
        holder.txtId.setText(rowitem.getId_cot_concorrente().toString());

        convertView.setOnClickListener(new clickItem(rowitem));


        return convertView;
    }
    private class clickItem implements AdapterViewCompat.OnClickListener{
        Cpr_Cot_ConcorrenteVO rowitem;
        private clickItem(Cpr_Cot_ConcorrenteVO rowitem){
            this.rowitem = rowitem;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getContext(), CotacaoProdutoActivity.class);
            intent.putExtra(Cpr_Cot_ConcorrenteVO.KEY_id_cot_concorrente,rowitem.getId_cot_concorrente());
            intent.putExtra(Cpr_Cot_ConcorrenteVO.KEY_nmconcorrente,rowitem.getNmconcorrente());
            getContext().startActivity(intent);
        }
    }
}
