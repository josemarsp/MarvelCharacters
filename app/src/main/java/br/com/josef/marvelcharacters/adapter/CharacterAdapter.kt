package br.com.josef.marvelcharacters.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.josef.marvelcharacters.R
import br.com.josef.marvelcharacters.interfaces.OnClick
import br.com.josef.marvelcharacters.model.dataclass.Result
import com.bumptech.glide.Glide

class CharacterAdapter(
    private var characterList: MutableList<Result>,
    private val listener: OnClick,
    private val context: Context
) : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = characterList[position]
        holder.onBind(result)
        holder.itemView.setOnClickListener { _: View? -> listener.click(result) }
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

//    fun atualizaLista(novaLista: MutableList<Result>) {
//        if (characterList.isEmpty()) {
//            characterList = novaLista
//        } else {
//            characterList.addAll(novaLista)
//        }
//        notifyDataSetChanged()
//    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageComic: ImageView
        private val txtTitulo: TextView

        fun onBind(result: Result) {
            txtTitulo.text = result.name
            val imagem = result.thumbnail.path + "/portrait_uncanny." + result.thumbnail.extension
            val uri = Uri.parse(imagem.replace("http:", "https:"))
            Glide.with(context)
                .load(uri)
                .fitCenter()
                .into(imageComic)
        }

        init {
            imageComic = itemView.findViewById(R.id.imgComic)
            txtTitulo = itemView.findViewById(R.id.txtComic)
        }
    }
}