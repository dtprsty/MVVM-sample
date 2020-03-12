package id.dtprsty.mvvm_sample.ui.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.dtprsty.mvvm_sample.R
import id.dtprsty.mvvm_sample.data.CourseEntity
import id.dtprsty.mvvm_sample.ui.academy.AcademyViewModel
import id.dtprsty.mvvm_sample.utils.DataDummy
import id.dtprsty.mvvm_sample.ui.bookmark.BookmarkAdapter.BookmarkFragmentCallback
import id.dtprsty.mvvm_sample.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_bookmark.*

/**
 * A simple [Fragment] subclass.
 */
class BookmarkFragment : Fragment(), BookmarkFragmentCallback {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookmark, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if(activity != null){
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[BookmarkViewModel::class.java]
            val courses = viewModel.getBookmark()
            val adapter = BookmarkAdapter(this, courses)

            with(rvBookmark){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }

    override fun onShareClick(model: CourseEntity) {
        if(activity != null){
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder
                .from(activity!!)
                .apply {
                    setType(mimeType)
                    setChooserTitle(getString(R.string.share_apps))
                    setText(resources.getString(R.string.share_text, model.title))
                    startChooser()
                }
        }
    }

}
