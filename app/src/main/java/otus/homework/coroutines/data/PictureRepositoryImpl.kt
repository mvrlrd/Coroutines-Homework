package otus.homework.coroutines.data

import otus.homework.coroutines.data.model.Picture
import otus.homework.coroutines.data.services.PicturesService
import otus.homework.coroutines.presentation.PictureRepository
import otus.homework.coroutines.presentation.Result
import retrofit2.Retrofit
import kotlin.random.Random

class PictureRepositoryImpl(private val retrofit: Retrofit) : PictureRepository{
    override suspend fun getImage(): Result<String> {
        val pictureService = retrofit.create(PicturesService::class.java)
        return try {
            val pic = pictureService.getRandomPicture(Endpoints.PICTURE_URL +"?q=$query"+"&"+ "key=$KEY"+"&"+"image_type=$IMAGE_TYPE")
            Result.Success(data = getRandomImage(pic))
        }catch (e: Throwable){
            Result.Error(throwable = e)
        }
    }

    private fun getRandomImage(pic: Picture): String{
       return if (pic.totalHits< PICS_ON_PAGE){
            pic.hits[Random.nextInt(pic.totalHits)].imageURL
        }else{
            pic.hits[Random.nextInt(PICS_ON_PAGE)].imageURL
       }
    }

    companion object{
        private const val PICS_ON_PAGE = 20
        private const val KEY = "15813887-db28635529fd4ce8ef9aa7dbd"
        private const val query = "cats"
        private const val IMAGE_TYPE = "photo"
    }
}