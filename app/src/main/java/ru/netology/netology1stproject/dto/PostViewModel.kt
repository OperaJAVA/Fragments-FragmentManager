package ru.netology.netology1stproject.dto


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ru.netology.netology1stproject.repository.PostRepository
import ru.netology.netology1stproject.repository.PostRepositoryFileImpl


private val empty = Post(
    id = 0,
    content = "",
    author = "",
    likedByMe = false,
    likeCount = 0,
    shareByMe = false,
    shareCount = 0,
    watchCount = 0,
    published = "",
    video = null
)

class PostViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PostRepository = PostRepositoryFileImpl(application)

    val data = repository.getAll()
    val edited = MutableLiveData(empty)

    fun changeContentAndSave(content: String) {
        edited.value?.let {
            val text = content.trim()
            if (content != it.content) {
                repository.save(it.copy(content = content))
            }
            edited.value = empty
        }
    }

    fun edit(post: Post) {
        edited.value = post
    }

    fun likeById(id: Long) = repository.likeById(id)
    fun shareById(id: Long) = repository.shareById(id)
    fun removeById(id: Long) = repository.removeById(id)

}

