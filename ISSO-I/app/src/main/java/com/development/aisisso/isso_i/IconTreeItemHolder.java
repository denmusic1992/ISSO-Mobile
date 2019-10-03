package com.development.aisisso.isso_i;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.development.aisisso.isso_i.model.TreeNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import dalvik.system.DexFile;

public class IconTreeItemHolder extends TreeNode.BaseNodeViewHolder<IconTreeItemHolder.IconTreeItem> {
    private ImageView arrowView;
    private View view;
    private TreeNode node;

    public IconTreeItemHolder(Context context) {
        super(context);
    }

    @Override
    public View createNodeView(TreeNode node, final IconTreeItem value) {
        this.node = node;
        final LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.layout_icon_node, null, false);
        TextView tvValue = (TextView) view.findViewById(R.id.node_value);
        tvValue.setText(value.text);
        if(!PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()).getString("Theme", "0").equals("0"))
            tvValue.setTextColor(Color.BLACK);

        arrowView = (ImageView) view.findViewById(R.id.arrow_icon);
        tvValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> array = getClassesOfPackage("com.development.aisisso.isso_i");
                String className = findClassNameInArray(array, value.SYS_NAME);
                Class<?> c = null;
                if(className != null) {
                    try {
                        c = Class.forName("com.development.aisisso.isso_i." + className);
                    } catch (ClassNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                Intent intent = new Intent(context, c);
                intent.putExtra("C_ISSO", value.C_ISSO);
                intent.putExtra("C_GR_CONSTR", value.C_GR_CONSTR);
                intent.putExtra("DESCRIPTION", !value.text.contains("[") ? value.text : value.text.substring(0, value.text.indexOf("[")));
                /*intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);*/
                context.startActivity(intent);
            }
        });
        LinearLayout iconsLayout = (LinearLayout) view.findViewById(R.id.linLayoutForIcons);
        iconsLayout.removeAllViews();
        for(int i = 0; i <= node.getLevel() - 2; i++ ) {
            if(i == 0 && !node.isRoot()) {
                ImageView img = new ImageView(view.getContext());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                if(MainActivity.theme.equals("0"))
                    img.setImageResource(R.drawable.no_icon_dark);
                else
                    img.setImageResource(R.drawable.no_icon_light);
                iconsLayout.addView(img, params);
            }
            else if(node.getLevel() > 2) {
                ImageView img = new ImageView(view.getContext());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                if(MainActivity.theme.equals("0")) {
                    if(!node.getParent().isLastChild())
                        img.setImageResource(R.drawable.cross_parent_dark);
                    else
                        img.setImageResource(R.drawable.no_icon_dark);
                }
                else {
                    if(!node.getParent().isLastChild())
                        img.setImageResource(R.drawable.cross_parent_light);
                    else
                        img.setImageResource(R.drawable.no_icon_dark);
                }
                iconsLayout.addView(img, params);
            }
        }
        boolean active = false;
        if (node.isLeaf()) {
            if(node.isLastChild()) {
                if(MainActivity.theme.equals("0"))
                    arrowView.setImageDrawable(context.getResources().getDrawable(R.drawable.neutral_last_dark));
                else
                    arrowView.setImageDrawable(context.getResources().getDrawable(R.drawable.neutral_last_light));
            }
            else  {
                if(MainActivity.theme.equals("0"))
                    arrowView.setImageDrawable(context.getResources().getDrawable(R.drawable.neutral_dark));
                else
                    arrowView.setImageDrawable(context.getResources().getDrawable(R.drawable.neutral_light));
            }
        }
        else {
            if(node.isLastChild() && !node.getParent().isRoot()) {
                if(MainActivity.theme.equals("0"))
                    arrowView.setImageDrawable(context.getResources().getDrawable(active ? R.drawable.minus_end_dark : R.drawable.plus_end_dark));
                else
                    arrowView.setImageDrawable(context.getResources().getDrawable(active ? R.drawable.minus_end_light : R.drawable.plus_end_light));
            }
            else if(!node.isLastChild()) {
                if(MainActivity.theme.equals("0"))
                    arrowView.setImageDrawable(context.getResources().getDrawable(active ? R.drawable.minus_dark : R.drawable.plus_dark));
                else
                    arrowView.setImageDrawable(context.getResources().getDrawable(active ? R.drawable.minus_light : R.drawable.plus_light));
            }
        }
        if(node.getParent().isRoot()) {
            if(MainActivity.theme.equals("0"))
                arrowView.setImageDrawable(context.getResources().getDrawable(active ? R.drawable.minus_root_dark : R.drawable.plus_root_dark));
            else
                arrowView.setImageDrawable(context.getResources().getDrawable(active ? R.drawable.minus_root_light : R.drawable.plus_root_light));
        }
        //toggle(false);
        /*new Handler().post(new Runnable() {
            @Override
            public void run() {
                (view.findViewById(R.id.mainItemLinLayout)).getLayoutParams().height = arrowView.getLayoutParams().height;
            }
        });*/
        /*(view.findViewById(R.id.mainItemLinLayout)).getLayoutParams().height = 30;*/
        return view;
    }

    public String findClassNameInArray(ArrayList<String> array, String sysName) {
        String finalClassName = "ABDM_TableLayout";
        for(String item : array)
            if(item.contains(sysName)) {
                finalClassName = item;
            }
        return finalClassName;
    }

    public ArrayList<String>  getClassesOfPackage(String packageName) {
        ArrayList<String> classes = new ArrayList<>();
        try {
            String packageCodePath = view.getContext().getPackageCodePath();
            DexFile df = new DexFile(packageCodePath);
            for (Enumeration<String> iter = df.entries(); iter.hasMoreElements(); ) {
                String className = iter.nextElement();
                if (className.contains(packageName)) {
                    classes.add(className.substring(className.lastIndexOf(".") + 1, className.length()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return classes;
    }

    @Override
    public void toggle(boolean active) {
        if (node.isLeaf()) {
            if(node.isLastChild()) {
                if(MainActivity.theme.equals("0"))
                    arrowView.setImageDrawable(context.getResources().getDrawable(R.drawable.neutral_last_dark));
                else
                    arrowView.setImageDrawable(context.getResources().getDrawable(R.drawable.neutral_last_light));
            }
            else  {
                if(MainActivity.theme.equals("0"))
                    arrowView.setImageDrawable(context.getResources().getDrawable(R.drawable.neutral_dark));
                else
                    arrowView.setImageDrawable(context.getResources().getDrawable(R.drawable.neutral_light));
            }
        }
        else {
            if(node.isLastChild() && !node.getParent().isRoot()) {
                if(MainActivity.theme.equals("0"))
                    arrowView.setImageDrawable(context.getResources().getDrawable(active ? R.drawable.minus_end_dark : R.drawable.plus_end_dark));
                else
                    arrowView.setImageDrawable(context.getResources().getDrawable(active ? R.drawable.minus_end_light : R.drawable.plus_end_light));
            }
            else if (!node.isLastChild()){
                if(MainActivity.theme.equals("0"))
                    arrowView.setImageDrawable(context.getResources().getDrawable(active ? R.drawable.minus_dark : R.drawable.plus_dark));
                else
                    arrowView.setImageDrawable(context.getResources().getDrawable(active ? R.drawable.minus_light : R.drawable.plus_light));
            }
        }
        if(node.getParent().isRoot()) {
            if(MainActivity.theme.equals("0"))
                arrowView.setImageDrawable(context.getResources().getDrawable(active ? R.drawable.minus_root_dark : R.drawable.plus_root_dark));
            else
                arrowView.setImageDrawable(context.getResources().getDrawable(active ? R.drawable.minus_root_light : R.drawable.plus_root_light));
        }
    }


    public static class IconTreeItem {
        public String SYS_NAME;
        public String text;
        public int C_GR_CONSTR;
        public int C_ISSO;

        public IconTreeItem(String text, int C_GR_CONSTR, int C_ISSO, String SYS_NAME) {
            this.text = text;
            this.C_GR_CONSTR = C_GR_CONSTR;
            this.C_ISSO = C_ISSO;
            this.SYS_NAME = SYS_NAME;
        }
    }
}
