package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post

class PostRepositoryInMemoryImpl : PostRepository {
    private var nextId = 1L
    private var posts = listOf(
        Post(
            id = nextId++,
            author = "БТПИТ",
            content = "ГБПОУ ВО «БТПИТ» образовано в соответствии с  постановлением правительства Воронежской области от 20 мая 2015 года № 401 в результате реорганизации в  форме слияния государственного образовательного бюджетного учреждения среднего профессионального образования Воронежской области «Борисоглебский индустриальный техникум», государственного образовательного бюджетного учреждения среднего профессионального образования Воронежской области «Борисоглебский техникум информатики и вычислительной техники» и государственного образовательного бюджетного учреждения начального профессионального образования Воронежской области «Профессиональное училище № 34 г. Борисоглебска», зарегистрировано в качестве юридического лица 11 сентября 2015 г ",
            published = "21 мая в 18:36",
            likedByMe = false,
            amountlike = 0,
            sharecount = 100,
            link = "https://youtu.be/u58mdgo0T6s?si=U_Kdzizi1el9J2LG"
        ),
        Post(
            id = nextId++,
            author = "ВК",
            content = "Социальная сет ВКонтакте» (международное название — VK) — российская социальная сеть со штаб-квартирой в Санкт-Петербурге Сайт доступен на 82 языках, особенно популярен среди русскоязычных пользователей.",
            published = "18 сентября в 10:12",
            likedByMe = false,
            amountlike = 0,
            sharecount = 100,
            link = "https://youtu.be/XVFh1Lz3B-g?si=dRuu-QvOOhYlrve2"
        ),
        Post(
            id = nextId++,
            author = "BMW Сайт",
            content = "В логотипе компании чёрное кольцо было заимствовано от логотипа завода авиационных моторов Раппа. Белые и голубые сектора указывают на флаг и герб Баварии, в свою очередь основанных на гербе рода Виттельсбахов, правивших Баварией с конца XII по начало XX века",
            published = "23 сентября в 10:12",
            likedByMe = false,
            sharecount = 100,
            amountlike = 0,
            link = ""

        ),
    ).reversed()

    private val data = MutableLiveData(posts)

    override fun getAll(): LiveData<List<Post>> = data
    override fun shareById(id: Long) {
        posts = posts.map {
            if (it.id != id) it else
                it.copy(sharecount = it.sharecount+1)
        }
        data.value = posts
    }

    override fun save(post: Post) {
        if (post.id == 0L) {
            posts = listOf(
                post.copy(
                    id = nextId++,
                    author = "Me",
                    likedByMe = false,
                    published = "now"
                )
            ) + posts
            data.value = posts
            return
        }
        posts = posts.map {
            if (it.id != post.id) it else it.copy(content = post.content)
        }
        data.value = posts
    }

    override fun likeById(id: Long) {
        posts = posts.map {
            if (it.id != id) it else {
                if (it.likedByMe)
                    it.amountlike--
                else
                    it.amountlike++
                it.copy(likedByMe = !it.likedByMe)
            }
        }
        data.value = posts
    }
    override fun removeById(id: Long) {
        posts = posts.filter { it.id != id }
        data.value = posts
    }
}