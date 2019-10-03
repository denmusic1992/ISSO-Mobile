package com.development.aisisso.isso_i;

import android.content.Context;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.development.aisisso.isso_i.model.TreeNode;

public class SelectableHeaderHolder extends TreeNode.BaseNodeViewHolder<IconTreeItemHolder.IconTreeItem> {
    private TextView tvValue;
    private ImageView arrowView;
    public IconTreeItemHolder.IconTreeItem item;

    public SelectableHeaderHolder(Context context) {
        super(context);
    }

    @Override
    public View createNodeView(final TreeNode node, IconTreeItemHolder.IconTreeItem value) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.layout_selectable_header, null, false);

        tvValue = (TextView) view.findViewById(R.id.node_value);
        tvValue.setText(value.text);
        if(!PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()).getString("Theme", "0").equals("0"))
            tvValue.setTextColor(Color.BLACK);
        arrowView = (ImageView) view.findViewById(R.id.arrow_icon);
        if (node.isLeaf()) {
            arrowView.setVisibility(View.GONE);
        }
        toggle(false);
        return view;
    }

    @Override
    public void toggle(boolean active) {
        arrowView.setImageDrawable(context.getResources().getDrawable(active ? R.drawable.minus : R.drawable.plus));
    }

    public static class IconTreeItem {
        public String text;
        public int C_GR_CONSTR;

        public IconTreeItem(String text, int C_GR_CONSTR) {
            this.text = text;
            this.C_GR_CONSTR = C_GR_CONSTR;
        }
    }
}