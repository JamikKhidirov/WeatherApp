import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.compose.foundation.layout.ContextualFlowRow


class NetworkChangesReceiver(
    private val onNewtworkColback: () -> Unit
): BroadcastReceiver(){


    override fun onReceive(context: Context?, intent: Intent?) {
        if (isNetworAvailable(context)){
            onNewtworkColback()
        }
    }

    //Функция которая проверяет есть ли интернет
    private fun isNetworAvailable(context: Context?): Boolean{
        if (context == null) return false
        val connectivityManager = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

}