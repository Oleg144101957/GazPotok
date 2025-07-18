package gf.na.chisle.nia.domain.grey

import android.content.Context

interface FBAttrProvider {

    suspend fun provideFB(context: Context, appID: String, appToken: String): String
}