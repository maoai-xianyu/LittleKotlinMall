package mall.kotlin.com.ordercenter.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_address_item.view.*
import mall.kotlin.com.baselibrary.ext.onClick
import mall.kotlin.com.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import mall.kotlin.com.ordercenter.R
import mall.kotlin.com.ordercenter.data.protocol.ShipAddress

/*
    收货地址数据适配
 */
class ShipAddressAdapter(context: Context) : BaseRecyclerViewAdapter<ShipAddress, ShipAddressAdapter.ViewHolder>(context) {


    var mOptClickListener: OnOptClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_address_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]
        holder.itemView.mSetDefaultTv.isSelected = (model.shipIsDefault == 0)
        holder.itemView.mShipNameTv.text = model.shipUserName + "     " + model.shipUserMobile
        holder.itemView.mShipAddressTv.text = model.shipAddress

        holder.itemView.mSetDefaultTv.onClick {

            mOptClickListener?.let {

                it.onSetDefault(model)
            }
        }

        holder.itemView.mEditTv.onClick {
            mOptClickListener?.let {

                it.onEdit(model)
            }
        }

        holder.itemView.mDeleteTv.onClick {
            mOptClickListener?.let {
                it.onDelete(model)
            }
        }

    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)


    interface OnOptClickListener {
        fun onSetDefault(address: ShipAddress)
        fun onEdit(address: ShipAddress)
        fun onDelete(address: ShipAddress)
    }

}
