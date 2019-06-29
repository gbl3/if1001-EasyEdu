package com.example.easyedu.posts

import androidx.room.*
import com.example.easyedu.posts.Post

@Dao
interface PostsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserirPosts(vararg post: Post)

    @Update
    fun atualizarPosts(vararg post: Post)

    @Delete
    fun removerPosts(vararg post: Post)

    @Query("SELECT * FROM posts")
    fun todosPosts() : Array<Post>

    @Query("SELECT * FROM posts WHERE msg LIKE :q")
    fun buscaPostPelaMsg(q : String) : List<Post>

    @Query("SELECT * FROM posts WHERE id LIKE :q")
    fun buscaPostPeloId(q : String) : List<Post>
}