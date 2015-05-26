package br.com.sistema.siefcotacao.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.sistema.siefcotacao.R;
import br.com.sistema.siefcotacao.dao.Cpr_Cot_Conc_ProdDao;
import br.com.sistema.siefcotacao.vo.Cpr_Cot_Conc_ProdVO;

/**
 * Created by root on 05/05/15.
 */
public class CustomListViewCotacaoProdutoAdapter extends ArrayAdapter<Cpr_Cot_Conc_ProdVO> {
    Context context;
    Cpr_Cot_Conc_ProdDao dao = null;
    ViewHolder holder = null;

    public CustomListViewCotacaoProdutoAdapter(Context context, int resource, List<Cpr_Cot_Conc_ProdVO> items) {
        super(context, resource, items);
        this.context = context;
        dao = new Cpr_Cot_Conc_ProdDao(this.context);
    }

    private class ViewHolder{
        TextView txtid;
        TextView txtcdproduto;
        TextView txtnmproduto;
        EditText txtvalor;
        CheckBox ckbpromocao;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Cpr_Cot_Conc_ProdVO rowitem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        //if(convertView == null){
            convertView = mInflater.inflate(R.layout.list_item_listview_cotacao_produto,null);
            holder = new ViewHolder();
            //holder.txtid = (TextView) convertView.findViewById(R.id.list_item_listview_cotacao_produto_id);
            holder.txtnmproduto = (TextView) convertView.findViewById(R.id.list_item_listview_cotacao_produto_nmproduto);
            holder.txtcdproduto = (TextView) convertView.findViewById(R.id.list_item_listview_cotacao_produto_cdproduto);
            holder.txtvalor = (EditText) convertView.findViewById(R.id.list_item_listview_cotacao_produto_valor);
            holder.ckbpromocao = (CheckBox) convertView.findViewById(R.id.checkBox_list_item_listview_cotacao_produto);
            convertView.setTag(holder);
        //} else {
        //    holder = (ViewHolder) convertView.getTag();
        //}

        //holder.txtid.setText(rowitem.getId_cot_conc_prod().toString());
        holder.txtcdproduto.setText(rowitem.getCdproduto().toString());
        holder.txtnmproduto.setText(rowitem.getNm_p_pesquisa().substring(0,30));

        Cpr_Cot_Conc_ProdDao dao_conc_prod = new Cpr_Cot_Conc_ProdDao(this.context);
        Cpr_Cot_Conc_ProdVO vo = new Cpr_Cot_Conc_ProdVO();
        vo.setId_cot_conc_prod(rowitem.getId_cot_conc_prod());

        Cpr_Cot_Conc_ProdVO rVO = new Cpr_Cot_Conc_ProdVO();
        rVO = dao_conc_prod.getAllList(vo);

        if (rVO.getValor() != null && rVO.getValor() > 0){
            holder.txtvalor.setText(rVO.getValor().toString());
        }
        if (rVO.getPromocao_conc() != null && rVO.getPromocao_conc().equals("V")){
            holder.ckbpromocao.setChecked(true);
        } else {
            holder.ckbpromocao.setChecked(false);
        }

        holder.txtvalor.addTextChangedListener(new textWatcher(rowitem.getId_cot_conc_prod(), position));
        holder.txtvalor.setOnFocusChangeListener(new focusChange(convertView));
        holder.ckbpromocao.setOnCheckedChangeListener(new checkboxClick(rowitem.getId_cot_conc_prod(), position));

        return convertView;
    }
    private class checkboxClick implements CompoundButton.OnCheckedChangeListener{
        private int id;
        private int position;

        public checkboxClick(int id, int position) {
            this.id = id;
            this.position = position;
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            Cpr_Cot_Conc_ProdVO rowitem = getItem(position);
            Cpr_Cot_Conc_ProdVO vo = new Cpr_Cot_Conc_ProdVO();
            vo.setId_cot_conc_prod(rowitem.getId_cot_conc_prod());

            if (isChecked){
                vo.setPromocao_conc("V");
            } else {
                vo.setPromocao_conc("F");
            }

            dao.update(vo);
        }
    }
    private class focusChange implements View.OnFocusChangeListener{
        View convertView;
        private focusChange(View convertView){
            this.convertView = convertView;
        }

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            RelativeLayout relativeLayout = (RelativeLayout) convertView.findViewById(R.id.relativeLayout_cotacao_produto);
            if (hasFocus){
                relativeLayout.setBackgroundColor(Color.LTGRAY);
            } else {
                relativeLayout.setBackgroundColor(Color.TRANSPARENT);
            }
        }
    }
    private class textWatcher implements TextWatcher{
        private int id;
        private int position;

        private textWatcher(int id, int position){
            this.position = position;
            this.id = id;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            float fValor = 0;
            try {
                fValor = Float.parseFloat(s.toString());
            } catch (Exception e){

            }

            Cpr_Cot_Conc_ProdVO rowitem = getItem(position);
            Cpr_Cot_Conc_ProdVO vo = new Cpr_Cot_Conc_ProdVO();
            vo.setId_cot_conc_prod(rowitem.getId_cot_conc_prod());
            vo.setValor(fValor);
            dao.update(vo);


        }
    }
}
