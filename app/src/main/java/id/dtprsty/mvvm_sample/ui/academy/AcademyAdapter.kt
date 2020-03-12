package id.dtprsty.mvvm_sample.ui.academy

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.dtprsty.mvvm_sample.R
import id.dtprsty.mvvm_sample.data.CourseEntity
import kotlinx.android.synthetic.main.items_academy.view.*

class AcademyAdapter(
    private val callback: AcademyAdapter1Callback,
    private var items: MutableList<CourseEntity> = mutableListOf()
) : RecyclerView.Adapter<AcademyAdapter.AcademyAdapter1ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcademyAdapter1ViewHolder {
        return AcademyAdapter1ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.items_academy, parent, false)
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: AcademyAdapter1ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    interface AcademyAdapter1Callback {
        fun onClick(model: CourseEntity)
    }

    inner class AcademyAdapter1ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(model: CourseEntity) {
            with(itemView) {
                tvTitle.text = model.title
                tvDescription.text = model.description
                tvDate.text = resources.getString(R.string.deadline_date, model.deadline)
                setOnClickListener {
                    callback.onClick(model)
                }
                Glide.with(context)
                    .load(model.imagePath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(ivPoster)
            }
        }
    }
}
