package ie.droidfactory.instagramdemo.ModelView;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;
import ie.droidfactory.instagramdemo.model.MediaRecent;
import ie.droidfactory.instagramdemo.network.ApiUtils;
import ie.droidfactory.instagramdemo.network.ServiceFactory;
import ie.droidfactory.instagramdemo.network.ServiceInstagram;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MediaViewModel extends ViewModel {

    private static final String TAG = MediaViewModel.class.getSimpleName();

    private MutableLiveData<MediaRecent> mutableLiveDataMediaRecent;

    public LiveData<MediaRecent> loadMediaRecent(String accssToken){
        if(null==mutableLiveDataMediaRecent){
            mutableLiveDataMediaRecent = new MutableLiveData<>();
            getMediaRecent(accssToken);
        }
        return mutableLiveDataMediaRecent;
    }

    private void getMediaRecent(String accessToken) {
        ServiceInstagram service  = ServiceFactory.createService(ServiceInstagram.class, ApiUtils.ENDPOINT_SELF);
        Call<MediaRecent> call = service.getMediaRecent(accessToken);

        call.enqueue(new Callback<MediaRecent>() {
            @Override
            public void onResponse(Call<MediaRecent> call, Response<MediaRecent> response) {
                Log.d(TAG, "http response code: "+response.body().getMeta().getCode());
                mutableLiveDataMediaRecent.setValue(response.body());
            }

            @Override
            public void onFailure(Call<MediaRecent> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
