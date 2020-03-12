package id.dtprsty.mvvm_sample.ui.bookmark

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.dtprsty.mvvm_sample.R
import id.dtprsty.mvvm_sample.data.CourseEntity
import id.dtprsty.mvvm_sample.ui.detail.DetailCourseActivity
import kotlinx.android.synthetic.main.items_academy.view.ivPoster
import kotlinx.android.synthetic.main.items_academy.view.tvDate
import kotlinx.android.synthetic.main.items_academy.view.tvDescription
import kotlinx.android.synthetic.main.items_academy.view.tvTitle
import kotlinx.android.synthetic.main.items_bookmark.view.*
import org.jetbrains.anko.startActivity


class BookmarkAdapter(private val callback: BookmarkFragmentCallback, private var items: MutableList<CourseEntity> = mutableListOf()) :
    RecyclerView.Adapter<BookmarkAdapter.BookmarkAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkAdapterViewHolder {
        return BookmarkAdapterViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.items_bookmark, parent, false)
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: BookmarkAdapterViewHolder, position: Int) {
        holder.bind(items[position])
    }

    interface BookmarkFragmentCallback {
        fun onShareClick(model: CourseEntity)
    }

    inner class BookmarkAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(model: CourseEntity) {
            with(itemView) {
                tvTitle.text = model.title
                tvDescription.text = model.description
                tvDate.text = resources.getString(R.string.deadline_date, model.deadline)
                setOnClickListener {
                    context.startActivity<DetailCourseActivity>(DetailCourseActivity.EXTRA_COURSE to model.courseId)
                }
                ivShare.setOnClickListener {
                    callback.onShareClick(model)
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
