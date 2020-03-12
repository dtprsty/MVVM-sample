package id.dtprsty.mvvm_sample.ui.reader.list

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.dtprsty.mvvm_sample.R
import id.dtprsty.mvvm_sample.data.ModuleEntity
import kotlinx.android.synthetic.main.items_module_list.view.*
import java.util.*

class ModuleListAdapter internal constructor(
    private val listener: ModuleListAdapterListener,
    private var items: MutableList<ModuleEntity> = mutableListOf()
) : RecyclerView.Adapter<ModuleListAdapter.ModuleListAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModuleListAdapterViewHolder {
        return ModuleListAdapterViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.items_module_list, parent, false)
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ModuleListAdapterViewHolder, position: Int) {
        holder.bind(items[position])
    }

    interface ModuleListAdapterListener {
        fun onClick(position: Int, model: ModuleEntity)
    }

    inner class ModuleListAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(model: ModuleEntity) {
            with(itemView) {
                tvTitle.text = model.title
                setOnClickListener {
                    listener.onClick(model.position, model)
                }
            }
        }
    }
}
