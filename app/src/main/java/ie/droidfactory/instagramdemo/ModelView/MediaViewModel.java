package ie.droidfactory.instagramdemo.ModelView;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import javax.inject.Inject;

import ie.droidfactory.instagramdemo.model.MediaRecent;
import ie.droidfactory.instagramdemo.network.ApiUtils;
import ie.droidfactory.instagramdemo.network.ServiceFactory;
import ie.droidfactory.instagramdemo.network.ServiceInstagram;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * TODO: inject ServiceInstagram
 */
public class MediaViewModel extends ViewModel {

    private static final String TAG = MediaViewModel.class.getSimpleName();

    private MutableLiveData<MediaRecent> mutableLiveDataMediaRecent;

    public LiveData<MediaRecent> getMediaRecent(ServiceInstagram service, String accssToken, boolean refreshData){
        if(null==mutableLiveDataMediaRecent){
            mutableLiveDataMediaRecent = new MutableLiveData<>();
            loadDataFromInstagram(service, accssToken);
        }else if(refreshData) loadDataFromInstagram(service, accssToken);
        return mutableLiveDataMediaRecent;
    }

    private void loadDataFromInstagram(ServiceInstagram service, String accessToken) {
        Call<MediaRecent> call = service.getMediaRecent(accessToken);
        call.enqueue(new Callback<MediaRecent>() {
            @Override
            public void onResponse(@NonNull Call<MediaRecent> call, @NonNull Response<MediaRecent> response) {
                mutableLiveDataMediaRecent.setValue(response.body());
                Log.d(TAG, "http response code: "+response.body().getMeta().getCode());
            }
            @Override
            public void onFailure(@NonNull Call<MediaRecent> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
