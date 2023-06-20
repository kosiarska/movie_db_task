package pl.tretowicz.moviedbdemo.ui.list.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import coil.transform.CircleCropTransformation
import pl.tretowicz.moviedb.R
import pl.tretowicz.moviedb.databinding.ItemMovieBinding
import pl.tretowicz.moviedbdemo.domain.model.Movie
import pl.tretowicz.moviedbdemo.ui.list.MovieListViewModel

object MoviesComparator : DiffUtil.ItemCallback<Movie>() {
  override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
    return oldItem.id == newItem.id
  }

  override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
    return oldItem == newItem
  }
}

class MovieItemViewHolder(
  val itemBinding: ItemMovieBinding
) : RecyclerView.ViewHolder(itemBinding.root) {

  fun bind(movie: Movie?) {
    itemBinding.apply {
      title.text = movie?.title.orEmpty()
      image.load(MovieListViewModel.IMAGES_PATH + movie?.posterPath.orEmpty()) {
        crossfade(250)
        placeholder(R.drawable.ic_launcher_background)
        transformations(CircleCropTransformation())
        error(R.drawable.ic_launcher_background)
        scale(Scale.FILL)
      }
      description.text = movie?.overview.orEmpty().ifEmpty {
        itemBinding.root.context.getString(R.string.no_description)
      }
      toggleLike.setImageResource(
        if (movie?.isLiked == true) {
          R.drawable.baseline_star_rate_24
        } else {
          R.drawable.baseline_star_border_24
        }
      )
    }
  }
}

class MovieListAdapter(
  diffCallback: DiffUtil.ItemCallback<Movie> = MoviesComparator,
  private val onItemClick: (Movie?) -> Unit,
  private val onToggleLike: (Movie?) -> Unit
) : PagingDataAdapter<Movie, MovieItemViewHolder>(diffCallback) {

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): MovieItemViewHolder {
    val itemBinding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return MovieItemViewHolder(itemBinding)
  }

  override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
    val item = getItem(position)
    holder.bind(item)
    holder.itemBinding.root.setOnClickListener {
      onItemClick(getItem(holder.bindingAdapterPosition))
    }
    holder.itemBinding.toggleLike.setOnClickListener {
      onToggleLike(getItem(holder.bindingAdapterPosition))
    }
  }

  @SuppressLint("NotifyDataSetChanged")
  fun setLikedMovies(likedMovies: List<Long>) {
    for (index in 0 until itemCount) {
      val item = getItem(index)
      item?.isLiked = likedMovies.contains(item?.id)
    }
    notifyDataSetChanged()
  }
}
