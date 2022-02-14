package br.com.josef.marvelcharacters.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.josef.marvelcharacters.R
import br.com.josef.marvelcharacters.model.dataclass.MarvelResult
import br.com.josef.marvelcharacters.utils.ExpandedImageHelper
import br.com.josef.marvelcharacters.utils.urlForBasetImages
import br.com.josef.marvelcharacters.utils.urlForPortraitImages
import com.bumptech.glide.Glide

class SeriesAdapter(
    private var comicsList: MutableList<MarvelResult>,
//    private val listener: OnClick,
    private val context: Context
) : RecyclerView.Adapter<SeriesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = comicsList[position]
        holder.onBind(result)
        holder.itemView.setOnClickListener {
            ExpandedImageHelper
            .uriImage(urlForBasetImages(result), context)
        }
    }

    override fun getItemCount(): Int {
        return comicsList.size
    }

//    fun atualizaLista(novaLista: MutableList<MarvelResult>) {
//        if (comicsList.isEmpty()) {
//            comicsList = novaLista
//        } else {
//            comicsList.addAll(novaLista)
//        }
//        notifyDataSetChanged()
//    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageComic: ImageView
        private val txtTitulo: TextView

        fun onBind(marvelResult: MarvelResult) {
            txtTitulo.text = marvelResult.title
            Glide.with(context)
                .load(urlForPortraitImages(marvelResult))
                .dontTransform()
                .into(imageComic)
        }

        init {
            imageComic = itemView.findViewById(R.id.imgComic)
            txtTitulo = itemView.findViewById(R.id.txtComic)
        }
    }
}