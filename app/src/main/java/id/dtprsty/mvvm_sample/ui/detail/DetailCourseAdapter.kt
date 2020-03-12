package id.dtprsty.mvvm_sample.ui.detail

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.dtprsty.mvvm_sample.R
import id.dtprsty.mvvm_sample.data.ModuleEntity
import kotlinx.android.synthetic.main.items_academy.view.*
import java.util.*

class DetailCourseAdapter(var items: MutableList<ModuleEntity> = mutableListOf()) :
    RecyclerView.Adapter<DetailCourseAdapter.DetailCourseAdapterViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailCourseAdapterViewHolder {
        return DetailCourseAdapterViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.items_module_list, parent, false)
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: DetailCourseAdapterViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class DetailCourseAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(model: ModuleEntity) {
            with(itemView) {
                tvTitle.text = model.title
            }
        }
    }
}
