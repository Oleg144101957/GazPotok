package ru.gz.po.tok41.data

import com.google.firebase.database.FirebaseDatabase
import ru.gz.po.tok41.domain.grey.PostErrorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostErrorRepositoryImpl @Inject constructor() : PostErrorRepository {

    private val database = FirebaseDatabase.getInstance().reference

    override suspend fun postError(message: ErrorMessage) {
        withContext(Dispatchers.IO) {
            val uniqueKey = "error_${System.currentTimeMillis()}"
            database.child(uniqueKey).setValue(message.message)
        }
    }
}


