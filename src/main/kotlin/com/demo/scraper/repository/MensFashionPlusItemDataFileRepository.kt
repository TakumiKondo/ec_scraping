package com.demo.scraper.repository

import org.springframework.stereotype.Repository

// TODO: 6. ItemDataFileRepositoryを抽象クラスにして、サイト固有のフィールドを持たなくする
// TODO: 7. サイト固有のフィールドを持つ[XXX]DataFileRepositoryを実装する
@Repository
class MensFashionPlusItemDataFileRepository : ItemDataFileRepository("data/MensFashionPlusItem.data")