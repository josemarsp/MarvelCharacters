package br.com.josef.marvelcharacters.interfaces

import br.com.josef.marvelcharacters.model.dataclass.MarvelResult

interface OnClick {
    fun click(marvelResult: MarvelResult?)
}