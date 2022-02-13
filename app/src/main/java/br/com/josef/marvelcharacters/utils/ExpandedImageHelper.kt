package br.com.josef.marvelcharacters.utils

import android.app.Dialog
import android.content.Context
import android.net.Uri
import android.view.Window
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.josef.marvelcharacters.R
import com.bumptech.glide.Glide


class ExpandedImageHelper {
    companion object {

        @JvmStatic
        fun uriImage(optionalUri: Uri?, context: Context) {
            val dialog = Dialog(context)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.dialog_show_image_zoom)
            val imv = dialog.findViewById<ImageView>(R.id.img_to_show)
            val btnOk = dialog.findViewById<ImageView>(R.id.btn_alert_ok)
            loadImage(imv, optionalUri, context)
            btnOk.setOnClickListener { dialog.dismiss() }
            dialog.show()
            dialog.window?.setLayout(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT
            )
        }

        private fun loadImage(view: ImageView, uri: Uri?, context: Context) {
            Glide.with(context)
                .load(uri)
                .dontTransform()
                .into(view)
        }
    }
}
