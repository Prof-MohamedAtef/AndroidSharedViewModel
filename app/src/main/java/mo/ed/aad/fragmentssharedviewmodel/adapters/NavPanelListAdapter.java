package mo.ed.aad.fragmentssharedviewmodel.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import mo.ed.aad.fragmentssharedviewmodel.R;


public class NavPanelListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private OnItemClickListener mListener;

    private final String[] menus;
    private int[] menuImg = {

            R.drawable.location_menu_icon,
            R.drawable.circle_blue_shape,
            R.drawable.circle_blue_shape,
            R.drawable.ic_home_black_24dp,
            R.drawable.circle_blue_shape,
            R.drawable.circle_blue_shape,
            R.drawable.circle_blue_shape,
    };

    public NavPanelListAdapter(Context con) {

        inflater = LayoutInflater.from(con);
        menus = new String[]{con.getString(R.string.locations),
                con.getString(R.string.notifications),
                con.getString(R.string.account),
                con.getString(R.string.schedule),
                con.getString(R.string.history),
                con.getString(R.string.settings),
                con.getString(R.string.sign_out),};
    }

    public void setOnItemClickListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public int getCount() {
        return menus.length;
    }

    @Override
    public Object getItem(int i) {
        return menus.length;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(final int i, View convertview, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertview == null) {

            convertview = inflater.inflate(R.layout.nav_list_template, null);
            holder = new ViewHolder();
            holder.imgMenu = (ImageView) convertview.findViewById(R.id.menu_img);
            holder.txtMenu = (TextView) convertview.findViewById(R.id.mene_id);
            holder.linearLayout = (LinearLayout) convertview.findViewById(R.id.linearLayout);
            convertview.setTag(holder);

        } else {
            holder = (ViewHolder) convertview.getTag();
        }

        holder.txtMenu.setText(menus[i]);
        holder.imgMenu.setImageResource(menuImg[i]);
        if (i == menus.length - 2) {
            holder.linearLayout.setPadding(0, 0, 0, 20);
        }
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onItemClick(i);
                }
            }
        });
        return convertview;
    }

    public class ViewHolder {

        ImageView imgMenu;
        TextView txtMenu;
        LinearLayout linearLayout;

    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}